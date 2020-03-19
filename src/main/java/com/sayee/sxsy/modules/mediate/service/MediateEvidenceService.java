/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.mediate.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import com.google.common.collect.Lists;
import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.utils.*;
import com.sayee.sxsy.modules.act.entity.Act;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.auditacceptance.entity.AuditAcceptance;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;
import com.sayee.sxsy.modules.mediateapplyinfo.entity.MediateApplyInfo;
import com.sayee.sxsy.modules.program.dao.MediateProgramDao;
import com.sayee.sxsy.modules.program.entity.MediateProgram;
import com.sayee.sxsy.modules.record.dao.MediateRecordDao;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.record.service.MediateRecordService;
import com.sayee.sxsy.modules.recordinfo.dao.RecordInfoDao;
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;
import com.sayee.sxsy.modules.recordinfo.service.RecordInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.Role;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.DictUtils;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.mediate.entity.MediateEvidence;
import com.sayee.sxsy.modules.mediate.dao.MediateEvidenceDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 质证调解Service
 * @author lyt
 * @version 2019-06-10
 */
@Service
@Transactional(readOnly = true)
public class MediateEvidenceService extends CrudService<MediateEvidenceDao, MediateEvidence> {

	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	private MediateRecordDao mediateRecordDao;		//调解志DAO层
    @Autowired
	private MediateProgramDao mediateProgramDao;		//程序表DAO层
	@Autowired
	private RecordInfoDao recordInfoDao;    //笔录Dao
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private ComplaintMainService complaintMainService;

	public MediateEvidence get(String id) {
		MediateEvidence mediateEvidence=super.get(id);
		//调解志 明细查询
		MediateRecord mediateRecord=new MediateRecord();
		mediateRecord.setRelationId(mediateEvidence.getMediateEvidenceId());
		mediateEvidence.setMediateEvidenceList(mediateRecordDao.findList(mediateRecord));
		//调解会议查询
        MediateProgram mediateProgram = new MediateProgram();
        mediateProgram.setRelationId(mediateEvidence.getMediateEvidenceId());
        mediateEvidence.setMediateProgramList(mediateProgramDao.findList(mediateProgram));
		return mediateEvidence;

	}
	
	public List<MediateEvidence> findList(MediateEvidence mediateEvidence) {
		//获取当前登陆用户
		mediateEvidence.setUser(UserUtils.getUser());
		return super.findList(mediateEvidence);
	}
	
	public Page<MediateEvidence> findPage(Page<MediateEvidence> page, MediateEvidence mediateEvidence) {
		List<Role> roleList=UserUtils.getRoleList();//获取当前登陆人角色
		List<String> aa= ObjectUtils.convert(roleList.toArray(),"enname",true);
		User user=UserUtils.getUser();
		if (user.isAdmin() || aa.contains("quanshengtiaojiebuzhuren") || aa.contains("yitiaoweizhuren")
				|| aa.contains("yitiaoweifuzhuren")|| aa.contains("shengzhitiaojiebuzhuren/fuzhuren")|| aa.contains("yitiaoweizhuren")
		){	//!aa.contains("dept") &&
		}else if((  aa.contains("gongzuozhanzhuren/fuzhuren")) ){
			//工作站 主任 副主任 看自己 的员工
			List<String> list=new ArrayList<String>();
			List<User> listUser=UserUtils.getUserByOffice(user.getOffice().getId());
			for (User people:listUser) {
				list.add(people.getLoginName());
			}
			if (list.size()>0){
				mediateEvidence.setList(list);
			}else {
				list.add(user.getLoginName());
				mediateEvidence.setList(list);
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
				mediateEvidence.setList(new ArrayList(list));
			}else {
				list.add(user.getLoginName());
				mediateEvidence.setList(new ArrayList(list));
			}
		}else {//不是管理员查看自己创建的
			mediateEvidence.setUser(UserUtils.getUser());
		}
		return super.findPage(page, mediateEvidence);
	}
	
	@Transactional(readOnly = false)
	public void save(MediateEvidence mediateEvidence,HttpServletRequest request) {
		ComplaintMain complaintMain = complaintMainService.get(mediateEvidence.getComplaintMainId());
		if(StringUtils.isBlank(mediateEvidence.getCreateBy().getId())){
            mediateEvidence.preInsert();
            mediateEvidence.setMediateEvidenceId(mediateEvidence.getId());
            mediateEvidence.setDoctor(mediateEvidence.getDoctorOffice().getId());
            mediateEvidence.setPatient(complaintMain.getPatientName());
            //将主键ID设为UUID
            dao.insert(mediateEvidence);
            //保存笔录(患方)
//            RecordInfo huanf = mediateEvidence.getRecordInfo();
//            huanf.preInsert();
//            huanf.setRecordId(IdGen.uuid());
//            huanf.setDoctor(mediateEvidence.getDoctor());
//			huanf.setHost(mediateEvidence.getUserId());
//			huanf.setNoteTaker(mediateEvidence.getClerk());
//            huanf.setStartTime(mediateEvidence.getMeetingTime());
//            huanf.setEndTime(mediateEvidence.getMeetingTime());
//            huanf.setRecordAddress(mediateEvidence.getMeetingAddress());
//            huanf.setPatient(mediateEvidence.getPatient());
//            huanf.setRelationId(mediateEvidence.getMediateEvidenceId());
//            huanf.setType("1");
//            huanf.setModuleType("1");
//            recordInfoDao.insert(huanf);
            //保存笔录（医方） 由于医方笔录共用患方的笔录 所以先把共用的信息赋值给医方
//            RecordInfo yif = mediateEvidence.getRecordInfo().getYrecordInfo();
//            yif.setStartTime(huanf.getStartTime());
//            yif.setEndTime(huanf.getEndTime());
//            yif.setRecordAddress(huanf.getRecordAddress());
//            yif.setCause(huanf.getCause());
//            yif.setHost(huanf.getHost());
//            yif.setNoteTaker(huanf.getNoteTaker());
//            yif.setPatient(huanf.getPatient());
//            yif.setDoctor(huanf.getDoctor());
//            yif.setOtherParticipants(huanf.getOtherParticipants());
//            yif.preInsert();
//            yif.setRecordId(IdGen.uuid());
//            yif.setRelationId(mediateEvidence.getMediateEvidenceId());
//            yif.setType("2");
//            yif.setModuleType("1");
//            recordInfoDao.insert(yif);
		}else{
		    //如果不为空进行更新
			//修改报案登记表
			mediateEvidence.preUpdate();
			mediateEvidence.setMeetingAddress(mediateEvidence.getMeetingAddress());
			mediateEvidence.setMeetingTime(mediateEvidence.getMeetingTime());
//			mediateEvidence.setPatient(complaintMain.getPatientName());
			mediateEvidence.setDoctor(mediateEvidence.getDoctorOffice().getId());

			dao.update(mediateEvidence);
			//更新笔录(患方)
//            RecordInfo huanf = mediateEvidence.getRecordInfo();
//            huanf.preUpdate();
//			huanf.setDoctor(mediateEvidence.getDoctor());
//			huanf.setHost(mediateEvidence.getUserId());
//			huanf.setNoteTaker(mediateEvidence.getClerk());
//			huanf.setStartTime(mediateEvidence.getMeetingTime());
//			huanf.setEndTime(mediateEvidence.getMeetingTime());
//			huanf.setRecordAddress(mediateEvidence.getMeetingAddress());
//			huanf.setPatient(mediateEvidence.getPatient());
//            recordInfoDao.update(huanf);
            //更新笔录(医方)
//            RecordInfo yif = mediateEvidence.getRecordInfo().getYrecordInfo();
//            yif.setStartTime(huanf.getStartTime());
//            yif.setEndTime(huanf.getEndTime());
//            yif.setRecordAddress(huanf.getRecordAddress());
//            yif.setCause(huanf.getCause());
//            yif.setHost(huanf.getHost());
//            yif.setNoteTaker(huanf.getNoteTaker());
//            yif.setPatient(huanf.getPatient());
//            yif.setDoctor(huanf.getDoctor());
//            yif.setOtherParticipants(huanf.getOtherParticipants());
//            yif.preUpdate();
//            recordInfoDao.update(yif);
		}
		//保存调解志
		this.tjz(mediateEvidence);
		//保存附件
		this.savefj(request,mediateEvidence);
		//保存调解程序 与 调解志
//		String export=request.getParameter("export");
//        if(StringUtils.isNotBlank(mediateEvidence.getMeetingTime()) && !"meeting".equals(export)){
            this.saveMeeting(mediateEvidence);
//        }
		if ("yes".equals(mediateEvidence.getComplaintMain().getAct().getFlag())){

			Map<String,Object> var=new HashMap<String, Object>();

			var.put("pass",StringUtils.isBlank(mediateEvidence.getNextLink()) ? "0" : mediateEvidence.getNextLink());
			User assigness= UserUtils.get(mediateEvidence.getNextLinkMan());
			var.put("0".equals(MapUtils.getString(var,"pass","0")) ? "evaluation_user" : "sign_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(mediateEvidence.getComplaintMain().getAct().getTaskId(), mediateEvidence.getComplaintMain().getAct().getProcInsId(), mediateEvidence.getComplaintMain().getAct().getComment(), mediateEvidence.getComplaintMain().getCaseNumber(), var);

		}

	}
	
	@Transactional(readOnly = false)
	public void delete(MediateEvidence mediateEvidence) {
		super.delete(mediateEvidence);
	}

    @Transactional(readOnly = false)
    public void tjz(MediateEvidence mediateEvidence){
	    List<MediateRecord> emp = new ArrayList<MediateRecord>();
	    if(mediateEvidence.getMediateEvidenceList()!=null){
	        emp=mediateEvidence.getMediateEvidenceList();
        }
        for (MediateRecord mediateRecord:emp) {
            if(MediateRecord.DEL_FLAG_NORMAL.equals(mediateRecord.getDelFlag())||"".equals(mediateRecord.getDelFlag())){
                if(StringUtils.isBlank(mediateRecord.getMediateRecord())){
                    mediateRecord.setRelationId(mediateEvidence.getMediateEvidenceId());
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
	public void savefj(HttpServletRequest request,MediateEvidence mediateEvidence){
		String files1 = request.getParameter("files1");
		String files2 = request.getParameter("files2");
		String files3 = request.getParameter("files3");
		String files4 = request.getParameter("files4");
		String files5 = request.getParameter("files5");
		String acceId = null;
		String itemId = mediateEvidence.getMediateEvidenceId();
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

	public String exportWord(MediateEvidence mediateEvidence, String export,String print,HttpServletRequest request, HttpServletResponse response) {
		WordExportUtil wordExportUtil=new WordExportUtil();
		mediateEvidence=this.get(mediateEvidence.getMediateEvidenceId());
		String nums = request.getParameter("nums");
		List<MediateProgram> mediateProgramList = mediateEvidence.getMediateProgramList();
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
		int c = Integer.valueOf(StringUtils.isNumeric(nums)==true ? nums : "0");
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
		String path=request.getSession().getServletContext().getRealPath("/");
		String modelPath=path;
		String returnPath="";
		String newFileName="无标题文件.docx";
		String savaPath=path;
		String pdfPath=path;
		Map<String, Object> params = new HashMap<String, Object>();
		//判断有无案件编号
		String num=null;
		if(mediateEvidence.getComplaintMain()!=null){
			num=mediateEvidence.getComplaintMain().getCaseNumber()==null?"":mediateEvidence.getComplaintMain().getCaseNumber()+"/";
		}else{
			num="";
		}
		if ("meeting".equals(export)){
			if(mediateProgramList.size()!=0){
				params.put("time", mediateEvidence.getMediateProgramList().get(c).getMeetingTime()==null?"":mediateEvidence.getMediateProgramList().get(c).getMeetingTime());
				params.put("address", mediateEvidence.getMediateProgramList().get(c).getAddress()==null?"": DictUtils.getDictLabel(mediateEvidence.getMediateProgramList().get(c).getAddress(),"meeting","") );
				params.put("case",mediateEvidence.getComplaintMain().getPatientName()==null||mediateEvidence.getComplaintMain().getHospital().getName()==null?"":mediateEvidence.getComplaintMain().getPatientName()+"与"+mediateEvidence.getComplaintMain().getHospital().getName()+"的医疗纠纷。");
				params.put("tiao",mediateEvidence.getMediateProgramList().get(c).getMediatorUser().getName()==null?"":mediateEvidence.getMediateProgramList().get(c).getMediatorUser().getName());
				params.put("pen",mediateEvidence.getMediateProgramList().get(c).getClerkuser().getName()==null?"":mediateEvidence.getMediateProgramList().get(c).getClerkuser().getName());
				params.put("patient",mediateEvidence.getPatient()==null?"":mediateEvidence.getPatient());
				params.put("doctor", mediateEvidence.getComplaintMain().getHospital().getName()==null?"":mediateEvidence.getComplaintMain().getHospital().getName());
				params.put("hAvoid",mediateEvidence.getMediateProgramList().get(c).getPatientAvoid()==null?"":mediateEvidence.getMediateProgramList().get(c).getPatientAvoid());
				params.put("yAvoid",mediateEvidence.getMediateProgramList().get(c).getDoctorAvoid()==null?"":mediateEvidence.getMediateProgramList().get(c).getDoctorAvoid());
				params.put("hclear",mediateEvidence.getMediateProgramList().get(c).getPatientClear()==null?"":mediateEvidence.getMediateProgramList().get(c).getPatientClear());
				params.put("yclear",mediateEvidence.getMediateProgramList().get(c).getDoctorClear()==null?"":mediateEvidence.getMediateProgramList().get(c).getDoctorClear());
			}else {
				params.put("time","");
				params.put("address","");
				params.put("case","");
				params.put("tiao","");
				params.put("pen","");
				params.put("patient","");
				params.put("doctor","");
				params.put("hAvoid","");
				params.put("yAvoid","");
				params.put("hclear","");
				params.put("yclear","");
			}
			path += "/doc/mediateMeeting.docx";  //模板文件位置
			modelPath += "/doc/mediateMeeting.docx";
			savaPath +="/userfiles/mediateEvidence/"+num+"mediateMeeting.docx";
			pdfPath +="/userfiles/mediateEvidence/"+num+"mediateMeeting.pdf";
			returnPath="/userfiles/mediateEvidence/"+num+"mediateMeeting.pdf";
			newFileName="调解程序表.docx";
		}else if("pContent".equals(export)){
			if(mediateProgramList.size()!=0){
				params.put("date", mediateEvidence.getMediateProgramList().get(b).getMeetingTime()==null?"":mediateEvidence.getMediateProgramList().get(b).getMeetingTime());
				params.put("address", mediateEvidence.getMediateProgramList().get(b).getAddress()==null?"":DictUtils.getDictLabel(mediateEvidence.getMediateProgramList().get(b).getAddress(),"meeting",""));
				params.put("host",mediateEvidence.getMediateProgramList().get(b).getMediatorUser().getName()==null?"":mediateEvidence.getMediateProgramList().get(b).getMediatorUser().getName());
				params.put("note",mediateEvidence.getMediateProgramList().get(b).getClerkuser().getName()==null?"":mediateEvidence.getMediateProgramList().get(b).getClerkuser().getName());
				params.put("patient",mediateEvidence.getComplaintMain().getPatientName()==null?"":mediateEvidence.getComplaintMain().getPatientName());
				params.put("doctor", mediateEvidence.getComplaintMain().getHospital().getName()==null?"":mediateEvidence.getComplaintMain().getHospital().getName());
				params.put("other","");
				params.put("content",mediateEvidence.getMediateProgramList().get(b).getPatientContent()==null?"":mediateEvidence.getMediateProgramList().get(b).getPatientContent());
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
			savaPath +="/userfiles/mediateEvidence/"+num+"tjRecord.docx";
			pdfPath +="/userfiles/mediateEvidence/"+num+"tjRecord.pdf";
			returnPath="/userfiles/mediateEvidence/"+num+"tjRecord.pdf";
			newFileName="调解会患方笔录.docx";
		}else if("dContent".equals(export)){
			if(mediateProgramList.size()!=0){
				params.put("date", mediateEvidence.getMediateProgramList().get(b).getMeetingTime()==null?"":mediateEvidence.getMediateProgramList().get(b).getMeetingTime());
				params.put("address", mediateEvidence.getMediateProgramList().get(b).getAddress()==null?"":DictUtils.getDictLabel(mediateEvidence.getMediateProgramList().get(b).getAddress(),"meeting",""));
				params.put("host",mediateEvidence.getMediateProgramList().get(b).getMediatorUser().getName()==null?"":mediateEvidence.getMediateProgramList().get(b).getMediatorUser().getName());
				params.put("note",mediateEvidence.getMediateProgramList().get(b).getClerkuser().getName()==null?"":mediateEvidence.getMediateProgramList().get(b).getClerkuser().getName());
				params.put("patient",mediateEvidence.getComplaintMain().getPatientName()==null?"":mediateEvidence.getComplaintMain().getPatientName());
				params.put("doctor", mediateEvidence.getComplaintMain().getHospital().getName()==null?"":mediateEvidence.getComplaintMain().getHospital().getName());
				params.put("other","");
				params.put("content",mediateEvidence.getMediateProgramList().get(b).getDoctorContent()==null?"":mediateEvidence.getMediateProgramList().get(b).getDoctorContent());
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
			savaPath +="/userfiles/mediateEvidence/"+num+"tjRecord.docx";
			pdfPath +="/userfiles/mediateEvidence/"+num+"tjRecord.pdf";
			returnPath="/userfiles/mediateEvidence/"+num+"tjRecord.pdf";
			newFileName="调解会医方笔录.docx";
		}
		try{
			File file =new File(request.getSession().getServletContext().getRealPath("/")+"/userfiles/mediateEvidence/"+num);
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
    public void clearDomain(MediateEvidence mediateEvidence){
        mediateEvidence.setMeetingTime("");
        mediateEvidence.setMeetingAddress("");
        mediateEvidence.setDoctor("");
        mediateEvidence.setDoctorUser(new User());
        mediateEvidence.setUserId("");
        mediateEvidence.setYtwUser(new User());
        mediateEvidence.setPatient("");
    }

    public void saveMeeting(MediateEvidence mediateEvidence){
        int cishu=mediateProgramDao.getMax(mediateEvidence.getMediateEvidenceId())+1;
        MediateProgram mediateProgram=new MediateProgram();
        mediateProgram.setAddress(mediateEvidence.getMeetingAddress());
        mediateProgram.setMeetingTime(mediateEvidence.getMeetingTime());
        mediateProgram.setCaseInfo(mediateEvidence.getCaseInfoName());
        mediateProgram.setDoctor(mediateEvidence.getDoctor());
        mediateProgram.setUser(new User());
        mediateProgram.getUser().setId(mediateEvidence.getUserId());
        mediateProgram.setMediator(mediateEvidence.getUserId());
        mediateProgram.setClerk(mediateEvidence.getClerk());
        mediateProgram.setPatient(mediateEvidence.getPatient());
        mediateProgram.setOther(mediateEvidence.getOther());
        mediateProgram.preInsert();
        mediateProgram.setMediateProgramId(mediateProgram.getId());
        mediateProgram.setMeetingFrequency(String.valueOf(cishu));
        mediateProgram.setRelationId(mediateEvidence.getMediateEvidenceId());
        mediateProgram.setDoctorAvoid(mediateEvidence.getDoctorAvoid());
        mediateProgram.setDoctorClear(mediateEvidence.getDoctorClear());
        mediateProgram.setPatientAvoid(mediateEvidence.getPatientAvoid());
        mediateProgram.setPatientClear(mediateEvidence.getPatientClear());
		//患方笔录
		RecordInfo huanf = mediateEvidence.getRecordInfo();
		//医方笔录
		RecordInfo yif = mediateEvidence.getRecordInfo().getYrecordInfo();
		mediateProgram.setPatientContent(huanf.getRecordContent());
		mediateProgram.setDoctorContent(yif.getRecordContent());
		if(StringUtils.isNotBlank(mediateProgram.getMeetingTime())) {
			//有时间 保存，不然保存 调解程序表  不保存 调解志
			mediateProgramDao.insert(mediateProgram);
		}
        //保存一条 调解会的 调解志
		if(StringUtils.isNotBlank(mediateProgram.getMeetingTime())) {
			MediateRecord mediateRecord = new MediateRecord();
			mediateRecord.setTime(mediateEvidence.getMeetingTime());
			mediateRecord.setContent("调解会");
			mediateRecord.setRoleType("3");
			mediateRecord.setWay("3");
			mediateRecord.setResult("");
			mediateRecord.setRelationId(mediateEvidence.getMediateEvidenceId());
			mediateRecord.setMediateRecord(IdGen.uuid());
			mediateRecord.preInsert();
			mediateRecord.setDelFlag("0");
			mediateRecordDao.insert(mediateRecord);
		}
    }

}