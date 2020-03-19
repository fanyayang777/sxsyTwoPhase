/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.summaryinfo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import com.google.common.collect.Lists;
import com.sayee.sxsy.common.utils.*;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.machine.dao.MachineAccountDao;
import com.sayee.sxsy.modules.machine.entity.MachineAccount;
import com.sayee.sxsy.modules.machine.service.MachineAccountService;
import com.sayee.sxsy.modules.medicalofficeemp.entity.MedicalOfficeEmp;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;
import com.sayee.sxsy.modules.perform.entity.PerformAgreement;
import com.sayee.sxsy.modules.sign.entity.SignAgreement;
import com.sayee.sxsy.modules.signtype.entity.SignTypeInfo;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.Role;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.DictUtils;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.summaryinfo.entity.SummaryInfo;
import com.sayee.sxsy.modules.summaryinfo.dao.SummaryInfoDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 案件总结Service
 * @author gbq
 * @version 2019-06-17
 */
@Service
@Transactional(readOnly = true)
public class SummaryInfoService extends CrudService<SummaryInfoDao, SummaryInfo>  {
	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	private MachineAccountService machineAccountService;
	@Autowired
	private MachineAccountDao machineAccountDao;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private SummaryInfoDao summaryInfoDao;
	public SummaryInfo get(String id) {
		return super.get(id);
	}
	
	public List<SummaryInfo> findList(SummaryInfo summaryInfo) {

		return super.findList(summaryInfo);
	}
	public List<SummaryInfo> findListSummmary(SummaryInfo summaryInfo){
		List<SummaryInfo> listSummmary = summaryInfoDao.findListSummmary(summaryInfo);
		return listSummmary;
	}

	public Page<SummaryInfo> findPage(Page<SummaryInfo> page, SummaryInfo summaryInfo) {
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
				summaryInfo.setList(list);
			}else {
				list.add(user.getLoginName());
				summaryInfo.setList(list);
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
				summaryInfo.setList(new ArrayList(list));
			}else {
				list.add(user.getLoginName());
				summaryInfo.setList(new ArrayList(list));
			}
		}else {//不是管理员查看自己创建的
			summaryInfo.setUser(UserUtils.getUser());
		}
		return super.findPage(page, summaryInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(SummaryInfo summaryInfo, HttpServletRequest request) {
		if(StringUtils.isBlank(summaryInfo.getCreateBy().getId())){
			summaryInfo.preInsert();
			summaryInfo.setSummaryId(summaryInfo.getId());
			summaryInfo.setSummaryTime(DateUtils.formatDateTime(summaryInfo.getCreateDate()));
			dao.insert(summaryInfo);
			this.fj(summaryInfo,request);
		}else{
			summaryInfo.preUpdate();
			dao.update(summaryInfo);
		}
		//如果存在 签署协议的 主键 ，则修改签署协议那会应该填写的时间
		if (summaryInfo.getPerformAgreement()!=null && StringUtils.isNotBlank(summaryInfo.getPerformAgreement().getPerformAgreementId())){
			PerformAgreement performAgreement=summaryInfo.getPerformAgreement();
			dao.updatePerform(performAgreement);
			//修改台账
			MachineAccount machineAccount = machineAccountService.getM(summaryInfo.getComplaintMainId());
			if(machineAccount!=null){
				machineAccount.preUpdate();
				machineAccount.setClaimSettlementTime(performAgreement.getClaimSettlementTime()==null?"":performAgreement.getClaimSettlementTime());
				machineAccount.setCompensateTime(performAgreement.getInsurancePayTime()==null?"":performAgreement.getInsurancePayTime());
				//int assessTime=m.getAssessTime()==null ? 0 : Integer.valueOf(m.getAssessTime()) ;
				//m.setFlowDays(this.TianShu(m.getRatifyAccord(),m.getAcceptanceTime(),StringUtils.toInteger(m.getAssessTime())));//流转天数  公式=签署协议时间-受理时间 剔除评估天数
				machineAccount.setHospitalAmount(performAgreement.getHospitalPayAmount()==null?"0":performAgreement.getHospitalPayAmount());
				//保险赔付时间
				machineAccount.setInsurancePayTime(performAgreement.getInsurancePayTime()==null ? "" :performAgreement.getInsurancePayTime());
				//医院赔付时间
				machineAccount.setHospitalPayTime(performAgreement.getHospitalPayTime()==null ? "" :performAgreement.getHospitalPayTime());
				//理赔流转天数（公式=保险赔付时间-提交理赔时间）
				machineAccount.setSettlementFlowDays(machineAccountService.TianShu(machineAccount.getInsurancePayTime(),machineAccount.getClaimSettlementTime(),0));
				//提交理赔天数(公式=提交理赔时间-协议生效时间）
				machineAccount.setClaimSettlementDay(machineAccountService.TianShu(machineAccount.getClaimSettlementTime(),performAgreement.getTakeEffectTime(),0));
				machineAccountDao.update(machineAccount);
			}
		}

		if ("yes".equals(summaryInfo.getComplaintMain().getAct().getFlag())){
			//List<Act> list = actTaskService.todoList(assessApply.getComplaintMain().getAct());
			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness= UserUtils.get(summaryInfo.getNextLinkMan());
			var.put("assess_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(summaryInfo.getComplaintMain().getAct().getTaskId(), summaryInfo.getComplaintMain().getAct().getProcInsId(), summaryInfo.getComplaintMain().getAct().getComment(), summaryInfo.getComplaintMain().getCaseNumber(), var);
		}
//		super.save(summaryInfo);
	}

	//保存附件
	public void fj(SummaryInfo summaryInfo,HttpServletRequest request) {
		//从前台获取附件
		String files1 = request.getParameter("files1");
		String files2 = request.getParameter("files2");
		String files3 = request.getParameter("files3");
		String files4 = request.getParameter("files4");
		String files5 = request.getParameter("files5");
		String files6 = request.getParameter("files6");
		String files7 = request.getParameter("files7");
		String files8 = request.getParameter("files8");
		String files9 = request.getParameter("files9");
		String files10 = request.getParameter("files10");
		String files11 = request.getParameter("files11");
		String files12 = request.getParameter("files12");
		String files13 = request.getParameter("files13");
		String files14 = request.getParameter("files14");
		String files15 = request.getParameter("files15");
		String files16 = request.getParameter("files16");
		String files17 = request.getParameter("files17");
		String files18 = request.getParameter("files18");
		String files19 = request.getParameter("files19");
		//附件的主键
		String acceId = null;

		//从前台获取到的附件类型
		String fjtype1 = request.getParameter("fjtype1");
		String fjtype2 = request.getParameter("fjtype2");
		String fjtype3 = request.getParameter("fjtype3");
		String fjtype4 = request.getParameter("fjtype4");
		String fjtype5 = request.getParameter("fjtype5");
		String fjtype6 = request.getParameter("fjtype6");
		String fjtype7 = request.getParameter("fjtype7");
		String fjtype8 = request.getParameter("fjtype8");
		String fjtype9 = request.getParameter("fjtype9");
		String fjtype10 = request.getParameter("fjtype10");
		String fjtype11 = request.getParameter("fjtype11");
		String fjtype12 = request.getParameter("fjtype12");
		String fjtype13 = request.getParameter("fjtype13");
		String fjtype14 = request.getParameter("fjtype14");
		String fjtype15 = request.getParameter("fjtype15");
		String fjtype16 = request.getParameter("fjtype16");
		String fjtype17 = request.getParameter("fjtype17");
		String fjtype18 = request.getParameter("fjtype18");
		String fjtype19 = request.getParameter("fjtype19");
		//itemId1关联评估鉴定表的主键
		String itemId = summaryInfo.getSummaryId();
		//保存或更新
		if (StringUtils.isNotBlank(files1)) {
			String acceId1 = request.getParameter("acceId1");
			if (StringUtils.isNotBlank(acceId1)) {
				preOperativeConsentService.updatefj(files1, itemId, fjtype1);
			} else {
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId, itemId, files1, fjtype1);
			}
		} else {
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(), "25");
		}
		if(StringUtils.isNotBlank(files2)){
			String acceId2 = request.getParameter("acceId2");
			if(StringUtils.isNotBlank(acceId2)){
				preOperativeConsentService.updatefj(files2,itemId,fjtype1);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files2,fjtype2);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"26");
		}
		if(StringUtils.isNotBlank(files3)){
			String acceId3 = request.getParameter("acceId3");
			if(StringUtils.isNotBlank(acceId3)){
				preOperativeConsentService.updatefj(files3,itemId,fjtype3);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files3,fjtype3);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"27");
		}
		if(StringUtils.isNotBlank(files4)){
			String acceId4 = request.getParameter("acceId4");
			if(StringUtils.isNotBlank(acceId4)){
				preOperativeConsentService.updatefj(files4,itemId,fjtype4);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files4,fjtype4);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"28");
		}
		if(StringUtils.isNotBlank(files5)){
			String acceId5 = request.getParameter("acceId5");
			if(StringUtils.isNotBlank(acceId5)){
				preOperativeConsentService.updatefj(files5,itemId,fjtype5);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files5,fjtype5);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"29");
		}
		if(StringUtils.isNotBlank(files6)){
			String acceId6 = request.getParameter("acceId6");
			if(StringUtils.isNotBlank(acceId6)){
				preOperativeConsentService.updatefj(files6,itemId,fjtype6);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files6,fjtype6);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"30");
		}
		if(StringUtils.isNotBlank(files7)){
			String acceId7 = request.getParameter("acceId7");
			if(StringUtils.isNotBlank(acceId7)){
				preOperativeConsentService.updatefj(files7,itemId,fjtype7);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files7,fjtype7);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"31");
		}
		if(StringUtils.isNotBlank(files8)){
			String acceId8 = request.getParameter("acceId8");
			if(StringUtils.isNotBlank(acceId8)){
				preOperativeConsentService.updatefj(files8,itemId,fjtype8);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files8,fjtype8);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"32");
		}
		if(StringUtils.isNotBlank(files9)){
			String acceId9 = request.getParameter("acceId9");
			if(StringUtils.isNotBlank(acceId9)){
				preOperativeConsentService.updatefj(files9,itemId,fjtype9);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files9,fjtype9);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"33");
		}
		if(StringUtils.isNotBlank(files10)){
			String acceId10 = request.getParameter("acceId10");
			if(StringUtils.isNotBlank(acceId10)){
				preOperativeConsentService.updatefj(files10,itemId,fjtype10);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files10,fjtype10);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"34");
		}
		if(StringUtils.isNotBlank(files11)){
			String acceId11 = request.getParameter("acceId11");
			if(StringUtils.isNotBlank(acceId11)){
				preOperativeConsentService.updatefj(files11,itemId,fjtype11);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files11,fjtype11);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"35");
		}
		if(StringUtils.isNotBlank(files12)){
			String acceId12 = request.getParameter("acceId12");
			if(StringUtils.isNotBlank(acceId12)){
				preOperativeConsentService.updatefj(files12,itemId,fjtype12);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files12,fjtype12);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"36");
		}
		if(StringUtils.isNotBlank(files13)){
			String acceId13 = request.getParameter("acceId13");
			if(StringUtils.isNotBlank(acceId13)){
				preOperativeConsentService.updatefj(files13,itemId,fjtype13);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files13,fjtype13);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"37");
		}
		if(StringUtils.isNotBlank(files14)){
			String acceId14 = request.getParameter("acceId14");
			if(StringUtils.isNotBlank(acceId14)){
				preOperativeConsentService.updatefj(files14,itemId,fjtype14);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files14,fjtype14);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"38");
		}
		if(StringUtils.isNotBlank(files15)){
			String acceId15 = request.getParameter("acceId15");
			if(StringUtils.isNotBlank(acceId15)){
				preOperativeConsentService.updatefj(files15,itemId,fjtype15);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files15,fjtype15);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"39");
		}
		if(StringUtils.isNotBlank(files16)){
			String acceId16 = request.getParameter("acceId16");
			if(StringUtils.isNotBlank(acceId16)){
				preOperativeConsentService.updatefj(files16,itemId,fjtype16);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files16,fjtype16);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"40");
		}
		if(StringUtils.isNotBlank(files17)){
			String acceId17 = request.getParameter("acceId17");
			if(StringUtils.isNotBlank(acceId17)){
				preOperativeConsentService.updatefj(files17,itemId,fjtype17);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files17,fjtype17);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"41");
		}
		if(StringUtils.isNotBlank(files18)){
			String acceId18 = request.getParameter("acceId18");
			if(StringUtils.isNotBlank(acceId18)){
				preOperativeConsentService.updatefj(files18,itemId,fjtype18);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files18,fjtype18);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"42");
		}
		if(StringUtils.isNotBlank(files19)){
			String acceId19 = request.getParameter("acceId19");
			if(StringUtils.isNotBlank(acceId19)){
				preOperativeConsentService.updatefj(files19,itemId,fjtype19);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files19,fjtype19);
			}
		}else{
			preOperativeConsentService.delefj(summaryInfo.getSummaryId(),"43");
		}
	}

	@Transactional(readOnly = false)
	public void delete(SummaryInfo summaryInfo) {
		super.delete(summaryInfo);
	}

    public Map<String,Object> getViewDetail(String complaintMainId) {
		Map<String,Object> map=dao.getViewDetail(complaintMainId);
		return map;
    }

	public String getFileNum(HttpServletRequest request) {
		SummaryInfo summaryInfo=new SummaryInfo();
		//卷宗编号
			List<SummaryInfo> list = this.findListSummmary(summaryInfo);
			String number="";
			if(list.size()==0){
				String code = BaseUtils.getCode("time", "4", "SUMMARY_INFO", "file_number");
				String num=code.substring(0,4);
				number=num+"-0001";
			}else{
				int max=0;
				for (int i=0;i<list.size();i++) {
					String fileNumber = list.get(i).getFileNumber();
					String n=fileNumber.substring(5,9);
					int n1=Integer.valueOf(n);
					if(n1>max){
						max=n1;
					}
				}
				int n2=max+1;
				String format = String.format("%0" + 4 + "d", n2);
				String code1 = BaseUtils.getCode("time", "4", "SUMMARY_INFO", "file_number");
				String time= code1.substring(0,4);
				number =time+"-"+format;
			}
			return number;
	}

	//导出
	public String exportWord(SummaryInfo summaryInfo, String export, String print, HttpServletRequest request, HttpServletResponse response){
		summaryInfo =get(summaryInfo.getSummaryId());
		String agreementExplain2 = request.getParameter("agreementExplain");
		WordExportUtil wordExportUtil = new WordExportUtil();
		String path = request.getSession().getServletContext().getRealPath("/");
		String modelPath = path;
		String returnPath="";
		String newFileName = "无标题文件.docx";
		String savaPath=path;
		String pdfPath=path;
		Map<String, Object> params = new HashMap<String, Object>();
		//判断有无案件编号
		//判断有无案件编号
		String num=null;
		if(summaryInfo.getComplaintMain()!=null){
			num=summaryInfo.getComplaintMain().getCaseNumber()==null?"":summaryInfo.getComplaintMain().getCaseNumber()+"/";
		}else{
			num="";
		}
		if("summ".equals(export)){
			if(summaryInfo!=null) {
				params.put("name", summaryInfo.getComplaintMain() == null ? "" : summaryInfo.getComplaintMain().getPatientName()+"与"+ UserUtils.getOfficeId(summaryInfo.getComplaintMain().getInvolveHospital()).getName() );
				params.put("number", summaryInfo.getFileNumber() == null ? "" : summaryInfo.getFileNumber());
				params.put("acTime", summaryInfo.getAcceptanceTime() == null ? "" : summaryInfo.getAcceptanceTime());
				params.put("sumTime", summaryInfo.getAcceptanceTime()==null ? "" :summaryInfo.getAcceptanceTime());
				params.put("flowDays", summaryInfo.getFlowDays()==null ? "" :summaryInfo.getFlowDays());
				params.put("radio", summaryInfo.getResponsibilityRatio() == null ? "" : summaryInfo.getResponsibilityRatio());
				params.put("meetingFrequency", summaryInfo.getMeetingFrequency() == null ? "" : summaryInfo.getMeetingFrequency());
				params.put("mediateResult", summaryInfo.getMediateResult() == null ? "" : ("1".equals(summaryInfo.getMediateResult()) ? "成功" :  "2".equals(summaryInfo.getMediateResult()) ? "终止" : "销案" ) );
				params.put("mediatePass", summaryInfo.getMediatePass() == null ? "" : summaryInfo.getMediatePass());
				params.put("summary", summaryInfo.getSummary() == null ? "" : summaryInfo.getSummary());
				params.put("other", summaryInfo.getOther() == null ? "" : summaryInfo.getOther());
			}else{
				params.put("name", "");
				params.put("number", "");
				params.put("acTime", "");
				params.put("sumTime", "");
				params.put("flowDays", "");
				params.put("radio", "");
				params.put("meetingFrequency", "");
				params.put("mediateResult","");
				params.put("mediatePass", "");
				params.put("summary","");
				params.put("other","");
			}
			path += "/doc/summary.docx";  //模板文件位置
			modelPath += "/doc/summary.docx";
			savaPath +="/userfiles/summary/"+num+"summary.docx";
			pdfPath +="/userfiles/summary/"+num+"summary.pdf";
			returnPath="/userfiles/summary/"+num+"summary.pdf";
			newFileName="结案总结.docx";
		}
		try{
			File file =new File(request.getSession().getServletContext().getRealPath("/")+"/userfiles/summary/"+num);
			if (!file.exists()){
				file.mkdirs();
			}
			List<String[]> testList = new ArrayList<String[]>();
			String fileName= new String(newFileName.getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
			wordExportUtil.getWord(path,modelPath,savaPath,print,params,testList,fileName,response);
			wordExportUtil.doc2pdf(savaPath,new FileOutputStream(pdfPath));
			System.out.println("转pdf成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnPath;
	}

}