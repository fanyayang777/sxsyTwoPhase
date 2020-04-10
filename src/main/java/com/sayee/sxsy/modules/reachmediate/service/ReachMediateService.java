/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.reachmediate.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import com.google.common.collect.Lists;
import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.ObjectUtils;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.common.utils.WordExportUtil;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;
import com.sayee.sxsy.modules.program.dao.MediateProgramDao;
import com.sayee.sxsy.modules.program.entity.MediateProgram;
import com.sayee.sxsy.modules.record.dao.MediateRecordDao;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.recordinfo.dao.RecordInfoDao;
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;
import com.sayee.sxsy.modules.recordinfo.service.RecordInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.Role;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.DictUtils;
import com.sayee.sxsy.modules.sys.utils.FileBaseUtils;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.reachmediate.entity.ReachMediate;
import com.sayee.sxsy.modules.reachmediate.dao.ReachMediateDao;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 达成调解Service
 * @author lyt
 * @version 2019-06-14
 */
@Service
@Transactional(readOnly = true)
public class ReachMediateService extends CrudService<ReachMediateDao, ReachMediate> {

	@Autowired
	private PreOperativeConsentService preOperativeConsentService;

	@Autowired
	private MediateRecordDao mediateRecordDao;		//调解志DAO层

	@Autowired
	private RecordInfoDao recordInfoDao;		//笔录业务层

	@Autowired
	private MediateProgramDao mediateProgramDao;		//程序表DAO层

	@Autowired
	private ActTaskService actTaskService;

	@Autowired
	private PreOperativeConsentService getPreOperativeConsentService;
	@Autowired
	private ComplaintMainService complaintMainService;

	public ReachMediate get(String id) {
		ReachMediate reachMediate = super.get(id);
		//调解志 明细查询
		MediateRecord mediateRecord = new MediateRecord();
		mediateRecord.setRelationId(reachMediate.getReachMediateId());
		reachMediate.setMediateEvidenceList(mediateRecordDao.findReachMediateList(mediateRecord));
		//调节会议查询
		MediateProgram mediateProgram = new MediateProgram();
		mediateProgram.setRelationId(reachMediate.getReachMediateId());
		reachMediate.setMediateProgramList(mediateProgramDao.findList(mediateProgram));
		return reachMediate;
	}
	
	public List<ReachMediate> findList(ReachMediate reachMediate) {
		reachMediate.setUser(UserUtils.getUser());
		return super.findList(reachMediate);
	}
	
	public Page<ReachMediate> findPage(Page<ReachMediate> page, ReachMediate reachMediate) {
		List<Role> roleList=UserUtils.getRoleList();//获取当前登陆人角色
		List<String> aa= ObjectUtils.convert(roleList.toArray(),"enname",true);
		User user=UserUtils.getUser();
		if (user.isAdmin() || aa.contains("quanshengtiaojiebuzhuren") || aa.contains("yitiaoweizhuren")
				|| aa.contains("yitiaoweifuzhuren")|| aa.contains("shengzhitiaojiebuzhuren/fuzhuren")|| aa.contains("yitiaoweizhuren")
		){
			//最大权限的人员 也看 区域
			if (!"山西省".equals(user.getAreaName())){
				//工作站 主任 副主任 看自己 的员工
				List<String> list=new ArrayList<String>();
				List<User> listUser=UserUtils.getUserByOffice(user.getOffice().getId());
				for (User people:listUser) {
					list.add(people.getLoginName());
				}
				if (list.size()>0){
					reachMediate.setList(list);
				}else {
					list.add(user.getLoginName());
					reachMediate.setList(list);
				}
			}
		}else if((  aa.contains("gongzuozhanzhuren/fuzhuren")) ){
			//工作站 主任 副主任 看自己 的员工
			List<String> list=new ArrayList<String>();
			List<User> listUser=UserUtils.getUserByOffice(user.getOffice().getId());
			for (User people:listUser) {
				list.add(people.getLoginName());
			}
			if (list.size()>0){
				reachMediate.setList(list);
			}else {
				list.add(user.getLoginName());
				reachMediate.setList(list);
			}
		}else if(aa.contains("szcz") || aa.contains("szjc") || aa.contains("szjz") || aa.contains("szgj") ||aa.contains("szyq") ||aa.contains("szsz") ||aa.contains("szxc") || aa.contains("szdt") || aa.contains("szll") ||aa.contains("szxy") || aa.contains("szyc") ||aa.contains("szlf") ||aa.contains("szybzg") ||aa.contains("szebzg")){
			List<Office> officeList = Lists.newArrayList();// 按明细设置数据范围s
			for (Role role:roleList) {
				for (Office office:role.getOfficeList()) {
					officeList.add(UserUtils.getOfficeId(office.getId()));//将获得的 明细 添加到list;
				}
			}
			//工作站 主任 副主任 看自己 的员工
			Set<String> list=new HashSet<String>();
			for (Office office:officeList) {
				List<User> listUser=UserUtils.getUserByOffice(office.getId());
				for (User people:listUser) {
					list.add(people.getLoginName());
				}
			}
			//添加 自己的loginName
			list.add(UserUtils.getUser().getLoginName());
			if (list.size()>0){
				reachMediate.setList(new ArrayList(list));
			}else {
				list.add(user.getLoginName());
				reachMediate.setList(new ArrayList(list));
			}
		}else {//不是管理员查看自己创建的
			reachMediate.setUser(UserUtils.getUser());
		}
		return super.findPage(page, reachMediate);
	}
	
	@Transactional(readOnly = false)
	public void save(ReachMediate reachMediate,HttpServletRequest request) {
		ComplaintMain complaintMain = complaintMainService.get(reachMediate.getComplaintMainId());
		if(StringUtils.isBlank(reachMediate.getCreateBy().getId())){
			reachMediate.preInsert();
			reachMediate.setReachMediateId(reachMediate.getId());
			//将主键设为UUID
			reachMediate.setReaDoctor(reachMediate.getDoctorOffice().getId());
			reachMediate.setReaMeetingTime(reachMediate.getReaMeetingTime());
			reachMediate.setReaAddress(reachMediate.getReaAddress());
			reachMediate.setReaPatient(complaintMain.getPatientName());
			dao.insert(reachMediate);
//			//保存笔录(患方)
//			RecordInfo huanf = reachMediate.getRecordInfo();
//			huanf.preInsert();
//			huanf.setRecordId(IdGen.uuid());
//			huanf.setDoctor(reachMediate.getReaDoctor());
//			huanf.setHost(reachMediate.getReaUserId());
//			huanf.setNoteTaker(reachMediate.getReaClerk());
//			huanf.setStartTime(reachMediate.getReaMeetingTime());
//			huanf.setEndTime(reachMediate.getReaMeetingTime());
//			huanf.setRecordAddress(reachMediate.getReaAddress());
//			huanf.setPatient(reachMediate.getReaPatient());
//			huanf.setRelationId(reachMediate.getReachMediateId());
//			huanf.setType("1");
//			huanf.setModuleType("1");
//			recordInfoDao.insert(huanf);
//			//保存笔录（医方） 由于医方笔录共用患方的笔录 所以先把共用的信息赋值给医方
//			RecordInfo yif = reachMediate.getRecordInfo().getYrecordInfo();
//			yif.setStartTime(huanf.getStartTime());
//			yif.setEndTime(huanf.getEndTime());
//			yif.setRecordAddress(huanf.getRecordAddress());
//			yif.setCause(huanf.getCause());
//			yif.setHost(huanf.getHost());
//			yif.setNoteTaker(huanf.getNoteTaker());
//			yif.setPatient(huanf.getPatient());
//			yif.setDoctor(huanf.getDoctor());
//			yif.setOtherParticipants(huanf.getOtherParticipants());
//			yif.preInsert();
//			yif.setRecordId(IdGen.uuid());
//			yif.setRelationId(reachMediate.getReachMediateId());
//			yif.setType("2");
//			yif.setModuleType("1");
//			recordInfoDao.insert(yif);
		}else{	//如果不为空则进行更新
			//修改达成调解表
			reachMediate.preUpdate();
			reachMediate.setReaDoctor(reachMediate.getDoctorOffice().getId());
			reachMediate.setReaMeetingTime(reachMediate.getReaMeetingTime());
			reachMediate.setReaAddress(reachMediate.getReaAddress());
//			reachMediate.setReaPatient(complaintMain.getPatientName());
			dao.update(reachMediate);
//			//更新笔录(患方)
//			RecordInfo huanf = reachMediate.getRecordInfo();
//			huanf.setDoctor(reachMediate.getReaDoctor());
//			huanf.setHost(reachMediate.getReaUserId());
//			huanf.setNoteTaker(reachMediate.getReaClerk());
//			huanf.setStartTime(reachMediate.getReaMeetingTime());
//			huanf.setEndTime(reachMediate.getReaMeetingTime());
//			huanf.setRecordAddress(reachMediate.getReaAddress());
//			huanf.setPatient(reachMediate.getReaPatient());
//			recordInfoDao.update(huanf);
//			//更新笔录(医方)
//			RecordInfo yif = reachMediate.getRecordInfo().getYrecordInfo();
//			yif.setStartTime(huanf.getStartTime());
//			yif.setEndTime(huanf.getEndTime());
//			yif.setRecordAddress(huanf.getRecordAddress());
//			yif.setCause(huanf.getCause());
//			yif.setHost(huanf.getHost());
//			yif.setNoteTaker(huanf.getNoteTaker());
//			yif.setPatient(huanf.getPatient());
//			yif.setDoctor(huanf.getDoctor());
//			yif.setOtherParticipants(huanf.getOtherParticipants());
//			yif.preUpdate();
//			recordInfoDao.update(yif);
		}
		//保存调解志
		this.tjz(reachMediate);
		this.savefj(request,reachMediate);		//调解志保存
		//保存调解程序 与 调解志
//		String export=request.getParameter("export");
//		if(StringUtils.isNotBlank(reachMediate.getReaMeetingTime())&& "meeting".equals(export)||"no".equals(export)){
			this.saveMeeting(reachMediate);
//		}
		if("yes".equals(reachMediate.getComplaintMain().getAct().getFlag())){
			Map<String,Object> var = new HashMap<String,Object>();
			var.put("pass","0");
			User assigness = UserUtils.get(reachMediate.getNextLinkMan());
			var.put("sign_user",assigness.getLoginName());
			//执行流程
			actTaskService.complete(reachMediate.getComplaintMain().getAct().getTaskId(),reachMediate.getComplaintMain().getAct().getProcInsId(),reachMediate.getComplaintMain().getAct().getComment(),reachMediate.getComplaintMain().getCaseNumber(),var);

		}
//		super.save(reachMediate);
	}
	
	@Transactional(readOnly = false)
	public void delete(ReachMediate reachMediate) {
		super.delete(reachMediate);
	}

	@Transactional(readOnly = false)
	public void savefj(HttpServletRequest request,ReachMediate reachMediate){
		String files1 = request.getParameter("files1");
		String files2 = request.getParameter("files2");
		String files3 = request.getParameter("files3");
		String files4 = request.getParameter("files4");
		String files5 = request.getParameter("files5");
		String acceId = null;
		String itemId = reachMediate.getReachMediateId();
		String fjtype1 = request.getParameter("fjtype1");
		String fjtype2 = request.getParameter("fjtype2");
		String fjtype3 = request.getParameter("fjtype3");
		String fjtype4 = request.getParameter("fjtype4");
		String fjtype5 = request.getParameter("fjtype5");
		if(StringUtils.isNotBlank(files1)){
			String acceId1=request.getParameter("acceId1");
			if(StringUtils.isNotBlank(acceId1)){
				preOperativeConsentService.updatefj(files1,itemId,fjtype1);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files1,fjtype1);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype1);
		}

		if(StringUtils.isNotBlank(files2)){
			String acceId2=request.getParameter("acceId2");
			if(StringUtils.isNotBlank(acceId2)){
				preOperativeConsentService.updatefj(files2,itemId,fjtype2);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files2,fjtype2);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype2);
		}

		if(StringUtils.isNotBlank(files3)){
			String acceId3=request.getParameter("acceId3");
			if(StringUtils.isNotBlank(acceId3)){
				preOperativeConsentService.updatefj(files3,itemId,fjtype3);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files3,fjtype3);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype3);
		}

		if(StringUtils.isNotBlank(files4)){
			String acceId4=request.getParameter("acceId4");
			if(StringUtils.isNotBlank(acceId4)){
				preOperativeConsentService.updatefj(files4,itemId,fjtype4);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files4,fjtype4);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype4);
		}

		if(StringUtils.isNotBlank(files5)){
			String acceId5=request.getParameter("acceId5");
			if(StringUtils.isNotBlank(acceId5)){
				preOperativeConsentService.updatefj(files5,itemId,fjtype5);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files5,fjtype5);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype5);
		}
	}
	@Transactional(readOnly = false)
	public void tjz(ReachMediate reachMediate){
		List<MediateRecord> emp = new ArrayList<MediateRecord>();
		if(reachMediate.getMediateEvidenceList()!=null){
			emp=reachMediate.getMediateEvidenceList();
		}
		for (MediateRecord mediateRecord:emp) {
			if(MediateRecord.DEL_FLAG_NORMAL.equals(mediateRecord.getDelFlag())||"".equals(mediateRecord.getDelFlag())){
				if(StringUtils.isBlank(mediateRecord.getMediateRecord())){
					mediateRecord.setRelationId(reachMediate.getReachMediateId());
					mediateRecord.setMediateRecord(IdGen.uuid());
					mediateRecord.preInsert();
					mediateRecord.setDelFlag("0");
					mediateRecordDao.insert(mediateRecord);
				}else{
					mediateRecord.preUpdate();
					mediateRecordDao.update(mediateRecord);
				}
			}else{
				mediateRecordDao.delete(mediateRecord);
			}
		}

	}

	@Transactional(readOnly = false)
	public void findfj(ReachMediate reachMediate,Model model){
		List<Map<String, Object>> filePath = FileBaseUtils.getFilePath(reachMediate.getReachMediateId());
		for(Map<String,Object> map :filePath){
			if("7".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
			}else if("8".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files1",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
			}else if("9".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files2",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
			}else if("10".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files3",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
			}else if("11".equals(MapUtils.getString(map,"fjtype"))){
				model.addAttribute("files4",MapUtils.getString(map,"FILE_PATH",MapUtils.getString(map,"file_path","")));
			}
		}

	}

	public String exportWord(ReachMediate reachMediate, String export,String print,HttpServletRequest request, HttpServletResponse response) {
		WordExportUtil wordExportUtil=new WordExportUtil();
		reachMediate=this.get(reachMediate.getReachMediateId());
		List<MediateProgram> mediateProgramList = reachMediate.getMediateProgramList();
		int a=0;
		int b=0;
		for (int i=0;i<mediateProgramList.size();i++){
			String meetingFrequency = mediateProgramList.get(i).getMeetingFrequency();
			int in = Integer.valueOf(meetingFrequency);
			if(a<in){
				a=in;
				b=i;
			}
		}
		String nums = request.getParameter("nums");
		if (StringUtils.isBlank(nums)){
			nums="0";
		}
		int c = Integer.valueOf(nums);
		if(c<=0 || c>=mediateProgramList.size()){
			c=b;
		}else{
			for (int i=0;i<mediateProgramList.size();i++){
				String meetingFrequency = mediateProgramList.get(i).getMeetingFrequency();
				int in = Integer.valueOf(meetingFrequency);
				if(c==in){
					c=i;
					break;
				}
			}
		}
		System.out.println(a);
		String path=request.getSession().getServletContext().getRealPath("/");
		String modelPath=path;
		String returnPath="";
		String newFileName="无标题文件.docx";
		String savaPath=path;
		String pdfPath=path;
		Map<String, Object> params = new HashMap<String, Object>();
		String area="";
		User user=UserUtils.getUser();
		if ("广东省".equals(user.getAreaName())){
			params.put("area",user.getCompany().getName().substring(0,3));
		}else {
			params.put("area",user.getAreaName());
		}
		//判断有无案件编号
		String num=null;
		if(reachMediate.getComplaintMain()!=null){
			num=reachMediate.getComplaintMain().getCaseNumber()==null?"":reachMediate.getComplaintMain().getCaseNumber()+"/";
		}else{
			num="";
		}
		if ("meeting".equals(export)){
			if(mediateProgramList.size()!=0){
				params.put("time", reachMediate.getMediateProgramList().get(b).getMeetingTime()==null?"":reachMediate.getMediateProgramList().get(b).getMeetingTime());
				params.put("address", reachMediate.getMediateProgramList().get(b).getAddress()==null?"": DictUtils.getDictLabel(reachMediate.getMediateProgramList().get(b).getAddress(),"meeting",""));
				params.put("case",reachMediate.getComplaintMain().getPatientName()==null||reachMediate.getComplaintMain().getHospital().getName()==null?"":reachMediate.getComplaintMain().getPatientName()+"与"+reachMediate.getComplaintMain().getHospital().getName()+"的医疗纠纷。");
				params.put("tiao",reachMediate.getMediateProgramList().get(b).getMediatorUser().getName()==null?"":reachMediate.getMediateProgramList().get(b).getMediatorUser().getName());
				params.put("pen",reachMediate.getMediateProgramList().get(b).getClerkuser().getName()==null?"":reachMediate.getMediateProgramList().get(b).getClerkuser().getName());
				params.put("patient",reachMediate.getReaPatient()==null?"":reachMediate.getReaPatient());
				params.put("doctor", reachMediate.getComplaintMain().getHospital().getName()==null?"":reachMediate.getComplaintMain().getHospital().getName());
				params.put("hAvoid",reachMediate.getMediateProgramList().get(b).getPatientAvoid()==null?"":reachMediate.getMediateProgramList().get(b).getPatientAvoid());
				params.put("yAvoid",reachMediate.getMediateProgramList().get(b).getDoctorAvoid()==null?"":reachMediate.getMediateProgramList().get(b).getDoctorAvoid());
				params.put("hclear",reachMediate.getMediateProgramList().get(b).getPatientClear()==null?"":reachMediate.getMediateProgramList().get(b).getPatientClear());
				params.put("yclear",reachMediate.getMediateProgramList().get(b).getDoctorClear()==null?"":reachMediate.getMediateProgramList().get(b).getDoctorClear());
			}else{
				params.put("time", "");
				params.put("address", "");
				params.put("case","");
				params.put("tiao","");
				params.put("pen","");
				params.put("patient","");
				params.put("doctor", "");
				params.put("hAvoid","");
				params.put("yAvoid","");
				params.put("hclear","");
				params.put("yclear","");
			}

			path += "/doc/mediateMeeting.docx";  //模板文件位置
			modelPath += "/doc/mediateMeeting.docx";
			savaPath +="/userfiles/reachMediate/"+num+"mediateMeeting.docx";
			pdfPath +="/userfiles/reachMediate/"+num+"mediateMeeting.pdf";
			returnPath="/userfiles/reachMediate/"+num+"mediateMeeting.pdf";
			newFileName="调解程序表.docx";
		}else if("pContent".equals(export)){
			if(mediateProgramList.size()!=0){
				params.put("date", reachMediate.getMediateProgramList().get(b).getMeetingTime()==null?"":reachMediate.getMediateProgramList().get(b).getMeetingTime());
				params.put("address", reachMediate.getMediateProgramList().get(b).getAddress()==null?"":DictUtils.getDictLabel(reachMediate.getMediateProgramList().get(b).getAddress(),"meeting",""));
				params.put("host",reachMediate.getMediateProgramList().get(b).getMediatorUser()==null ? "" : reachMediate.getMediateProgramList().get(b).getMediatorUser().getName() == null ? "" :reachMediate.getMediateProgramList().get(b).getMediatorUser().getName());
				params.put("note",reachMediate.getMediateProgramList().get(b).getClerkuser()==null?"":reachMediate.getMediateProgramList().get(b).getClerkuser().getName() ==null ? "" :reachMediate.getMediateProgramList().get(b).getClerkuser().getName());
				params.put("patient",reachMediate.getComplaintMain().getPatientName()==null?"":reachMediate.getComplaintMain().getPatientName());
				params.put("doctor", reachMediate.getComplaintMain().getHospital()==null?"":reachMediate.getComplaintMain().getHospital().getName()==null ? "" :reachMediate.getComplaintMain().getHospital().getName());
				params.put("other","");
				params.put("content",reachMediate.getMediateProgramList().get(b).getPatientContent()==null?"":reachMediate.getMediateProgramList().get(b).getPatientContent());
			}else{
				params.put("date", "");
				params.put("address", "");
				params.put("host","");
				params.put("note","");
				params.put("patient","");
				params.put("doctor", "");
				params.put("other","");
				params.put("content","");
			}
			path += "/doc/tjRecord.docx";  //模板文件位置
			modelPath += "/doc/tjRecord.docx";
			savaPath +="/userfiles/reachMediate/"+num+"tjRecord.docx";
			pdfPath +="/userfiles/reachMediate/"+num+"tjRecord.pdf";
			returnPath="/userfiles/reachMediate/"+num+"tjRecord.pdf";
			newFileName="达成调解患方笔录.docx";

		}else if("dContent".equals(export)){
			if(mediateProgramList.size()!=0){
				params.put("date", reachMediate.getMediateProgramList().get(b).getMeetingTime()==null?"":reachMediate.getMediateProgramList().get(b).getMeetingTime());
				params.put("address", reachMediate.getMediateProgramList().get(b).getAddress()==null?"":DictUtils.getDictLabel(reachMediate.getMediateProgramList().get(b).getAddress(),"meeting",""));
				params.put("host",reachMediate.getMediateProgramList().get(b).getMediatorUser()==null ? "" : reachMediate.getMediateProgramList().get(b).getMediatorUser().getName() == null ? "" :reachMediate.getMediateProgramList().get(b).getMediatorUser().getName());
				params.put("note",reachMediate.getMediateProgramList().get(b).getClerkuser()==null?"":reachMediate.getMediateProgramList().get(b).getClerkuser().getName() ==null ? "" :reachMediate.getMediateProgramList().get(b).getClerkuser().getName());
				params.put("patient",reachMediate.getComplaintMain().getPatientName()==null?"":reachMediate.getComplaintMain().getPatientName());
				params.put("doctor", reachMediate.getComplaintMain().getHospital()==null?"":reachMediate.getComplaintMain().getHospital().getName()==null ? "" :reachMediate.getComplaintMain().getHospital().getName());
//				params.put("host",reachMediate.getMediateProgramList().get(b).getMediatorUser().getName()==null?"":reachMediate.getMediateProgramList().get(b).getMediatorUser().getName());
//				params.put("note",reachMediate.getMediateProgramList().get(b).getClerkuser().getName()==null?"":reachMediate.getMediateProgramList().get(b).getClerkuser().getName());
//				params.put("patient",reachMediate.getComplaintMain().getPatientName()==null?"":reachMediate.getComplaintMain().getPatientName());
//				params.put("doctor", reachMediate.getComplaintMain().getHospital().getName()==null?"":reachMediate.getComplaintMain().getHospital().getName());
				params.put("other","");
				params.put("content",reachMediate.getMediateProgramList().get(b).getDoctorContent()==null?"":reachMediate.getMediateProgramList().get(b).getDoctorContent());
			}else{
				params.put("date", "");
				params.put("address", "");
				params.put("host","");
				params.put("note","");
				params.put("patient","");
				params.put("doctor", "");
				params.put("other","");
				params.put("content","");
			}
			path += "/doc/tjRecord.docx";  //模板文件位置
			modelPath += "/doc/tjRecord.docx";
			savaPath +="/userfiles/reachMediate/"+num+"tjRecord.docx";
			pdfPath +="/userfiles/reachMediate/"+num+"tjRecord.pdf";
			returnPath="/userfiles/reachMediate/"+num+"tjRecord.pdf";
			newFileName="达成调解医方笔录.docx";
		}
		try{
			File file =new File(request.getSession().getServletContext().getRealPath("/")+"/userfiles/reachMediate/"+num);
			if (!file.exists()){
				file.mkdirs();
			}
			List<String[]> testList = new ArrayList<String[]>();
			String fileName= new String(newFileName.getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
			wordExportUtil.getWord(path,modelPath,savaPath,print,params,testList,fileName,response);
			wordExportUtil.doc2pdf(savaPath,new FileOutputStream(pdfPath));
			System.out.println("转pdf成功");
//			if (StringUtils.isNotBlank(printName)){
			//wordExportUtil.wToPdfChange(savaPath,pdfPath);
			//wordExportUtil.PDFprint(new File(pdfPath),printName);
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnPath;
	}
	public void clearDomain(ReachMediate reachMediate){
		reachMediate.setReaMeetingTime("");
		reachMediate.setReaAddress("");
		reachMediate.setReaDoctor("");
		reachMediate.setDoctorUser(new User());
		reachMediate.setReaUserId("");
		reachMediate.setYtwUser(new User());
		reachMediate.setReaPatient("");
	}

	public void saveMeeting(ReachMediate reachMediate){
		int cishu=mediateProgramDao.getMax(reachMediate.getReachMediateId())+1;
		MediateProgram mediateProgram=new MediateProgram();
		mediateProgram.setAddress(reachMediate.getReaAddress());
		mediateProgram.setMeetingTime(reachMediate.getReaMeetingTime());
		mediateProgram.setCaseInfo(reachMediate.getReaCaseInfo());
		mediateProgram.setDoctor(reachMediate.getReaDoctor());
		mediateProgram.setUser(new User());
		mediateProgram.getUser().setId(reachMediate.getReaUserId());
		mediateProgram.setMediator(reachMediate.getReaUserId());
		mediateProgram.setClerk(reachMediate.getReaClerk());
		mediateProgram.setPatient(reachMediate.getReaPatient());
		mediateProgram.setOther(reachMediate.getReaOther());
		mediateProgram.preInsert();
		mediateProgram.setMediateProgramId(mediateProgram.getId());
		mediateProgram.setMeetingFrequency(String.valueOf(cishu));
		mediateProgram.setRelationId(reachMediate.getReachMediateId());
		mediateProgram.setPatientClear(reachMediate.getPatientClear());
		mediateProgram.setPatientAvoid(reachMediate.getPatientAvoid());
		mediateProgram.setDoctorClear(reachMediate.getDoctorClear());
		mediateProgram.setDoctorAvoid(reachMediate.getDoctorAvoid());
		//患方笔录
		RecordInfo huanf = reachMediate.getRecordInfo();
		//医方笔录
		RecordInfo yif = reachMediate.getRecordInfo().getYrecordInfo();
		mediateProgram.setPatientContent(huanf.getRecordContent());
		mediateProgram.setDoctorContent(yif.getRecordContent());
		if(StringUtils.isNotBlank(reachMediate.getReaMeetingTime())) {
			//有时间 保存，不然保存 调解程序表  不保存 调解志
			mediateProgramDao.insert(mediateProgram);
		}
		//保存一条 调解会的 调解志
		if(StringUtils.isNotBlank(reachMediate.getReaMeetingTime())) {
			MediateRecord mediateRecord = new MediateRecord();
			mediateRecord.setTime(reachMediate.getReaMeetingTime());
			mediateRecord.setContent("调解会");
			mediateRecord.setRoleType("3");
			mediateRecord.setWay("3");
			mediateRecord.setResult("");
			mediateRecord.setRelationId(reachMediate.getReachMediateId());
			mediateRecord.setMediateRecord(IdGen.uuid());
			mediateRecord.preInsert();
			mediateRecord.setDelFlag("0");
			mediateRecordDao.insert(mediateRecord);
		}
	}

}