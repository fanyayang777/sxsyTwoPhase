/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.sign.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.common.utils.WordExportUtil;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;
import com.sayee.sxsy.modules.gen.entity.GenTableColumn;
import com.sayee.sxsy.modules.mediate.entity.MediateEvidence;
import com.sayee.sxsy.modules.medicalofficeemp.dao.MedicalOfficeEmpDao;
import com.sayee.sxsy.modules.medicalofficeemp.entity.MedicalOfficeEmp;
import com.sayee.sxsy.modules.patientlinkemp.dao.PatientLinkEmpDao;
import com.sayee.sxsy.modules.patientlinkemp.entity.PatientLinkEmp;
import com.sayee.sxsy.modules.program.dao.MediateProgramDao;
import com.sayee.sxsy.modules.program.entity.MediateProgram;
import com.sayee.sxsy.modules.program.service.MediateProgramService;
import com.sayee.sxsy.modules.record.entity.MediateRecord;
import com.sayee.sxsy.modules.recordinfo.dao.RecordInfoDao;
import com.sayee.sxsy.modules.recordinfo.entity.RecordInfo;
import com.sayee.sxsy.modules.recordinfo.service.RecordInfoService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import com.sayee.sxsy.modules.typeinfo.entity.TypeInfo;
import com.sayee.sxsy.modules.typeinfo.service.TypeInfoService;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.sign.entity.SignAgreement;
import com.sayee.sxsy.modules.sign.dao.SignAgreementDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

/**
 * 签署协议Service
 * @author zhangfan
 * @version 2019-06-14
 */
@Service
@Transactional(readOnly = true)
public class SignAgreementService extends CrudService<SignAgreementDao, SignAgreement> {
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	private PatientLinkEmpDao patientLinkEmpDao;
	@Autowired
	SignAgreementDao signAgreementDao;
	@Autowired
	private MedicalOfficeEmpDao medicalOfficeEmpDao;
	@Autowired
	private MediateProgramDao mediateProgramDao;
	@Autowired
	private MediateProgramService mediateProgramService;
	@Autowired
	private RecordInfoDao recordInfoDao;
	@Autowired
	private RecordInfoService recordInfoService;
	@Autowired
	private ComplaintMainService complaintMainService;
	@Autowired
	TypeInfoService typeInfoService;
	public SignAgreement get(String id) {
		SignAgreement signAgreement=super.get(id);
		//患方 明细查询
		PatientLinkEmp patientLinkEmp=new PatientLinkEmp();
		patientLinkEmp.setRelationId(signAgreement.getSignAgreementId());
		patientLinkEmp.setLinkType("1");
		signAgreement.setPatientLinkEmpList(patientLinkEmpDao.findList(patientLinkEmp));
		//患方代理人
		PatientLinkEmp patientLinkD=new PatientLinkEmp();
		patientLinkD.setRelationId(signAgreement.getSignAgreementId());
		patientLinkD.setLinkType("2");
		signAgreement.setPatientLinkDList(patientLinkEmpDao.findList(patientLinkD));
		//医方人
		MedicalOfficeEmp medicalOfficeEmp=new MedicalOfficeEmp();
		medicalOfficeEmp.setRelationId(signAgreement.getSignAgreementId());
		signAgreement.setMedicalOfficeEmpList(medicalOfficeEmpDao.findList(medicalOfficeEmp));
		if(signAgreement.getMediateProgram()!=null){
			signAgreement.setMediateProgram(mediateProgramService.get(signAgreement.getMediateProgram().getMediateProgramId()));
		}
		if(signAgreement.getRecordInfo()!=null){
			signAgreement.setRecordInfo(recordInfoService.get(signAgreement.getRecordInfo().getRecordId()));
		}


		return signAgreement;
	}

	public List<SignAgreement> findList(SignAgreement signAgreement) {
		//获取当前登陆用户
		signAgreement.setUser(UserUtils.getUser());
		return super.findList(signAgreement);
	}

	public Page<SignAgreement> findPage(Page<SignAgreement> page, SignAgreement signAgreement) {
		//获取当前登陆用户
		signAgreement.setUser(UserUtils.getUser());
		return super.findPage(page, signAgreement);
	}

	@Transactional(readOnly = false)
	public void save(HttpServletRequest request, SignAgreement signAgreement) {
		//super.save(signAgreement);
		// 保存 多选框，逗号隔开
		this.getCheck(signAgreement);
		//数据保存
		if(StringUtils.isBlank(signAgreement.getCreateBy().getId())){
			//判断主键ID是否为空
			signAgreement.preInsert();
			signAgreement.setSignAgreementId(signAgreement.getId());
			//将主键ID设为UUID
			dao.insert(signAgreement);
			//保存调解程序表
		}else{//如果不为空进行更新

			//修改签署协议
			signAgreement.preUpdate();
			dao.update(signAgreement);
		}
		//保存调解程序表
		this.saveMe(signAgreement);
		//保存笔录
		this.saveRecordInfo(signAgreement);
		//保存患方
		this.detail(signAgreement,"1");
		//保存患方代理人
		this.detail(signAgreement,"2");
		//保存医方
		this.doctorDetail(signAgreement);
		//保存附件
		this.savefj(signAgreement,request);
		if ("yes".equals(signAgreement.getComplaintMain().getAct().getFlag())){
			//List<Act> list = actTaskService.todoList(assessApply.getComplaintMain().getAct());
			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness= UserUtils.get(signAgreement.getNextLinkMan());
			var.put("fulfill_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(signAgreement.getComplaintMain().getAct().getTaskId(), signAgreement.getComplaintMain().getAct().getProcInsId(), signAgreement.getComplaintMain().getAct().getComment(), signAgreement.getComplaintMain().getCaseNumber(), var);
		}
	}

	@Transactional(readOnly = false)
	public void delete(SignAgreement signAgreement) {
		super.delete(signAgreement);
	}


	public void getCheck(SignAgreement signAgreement){
		//调解情况 check
		StringBuffer string=new StringBuffer();
		if (signAgreement.getMediationList()!=null && !signAgreement.getMediationList().isEmpty()){
			for (TypeInfo typeInfo : signAgreement.getMediationList()){
				if (StringUtils.isNotBlank(typeInfo.getLabel()) && "1".equals(typeInfo.getLabel())){
					string.append(typeInfo.getTypeId()).append(",");
				}
			}
		}
		signAgreement.setMediation(String.valueOf(string));
		string.setLength(0);//清空 字符串
		//协议约定事项
		if ( signAgreement.getMeatterList()!=null && !signAgreement.getMeatterList().isEmpty()){
			for (TypeInfo typeInfo : signAgreement.getMeatterList()){
				if (StringUtils.isNotBlank(typeInfo.getLabel()) && "1".equals(typeInfo.getLabel())){
					string.append(typeInfo.getTypeId()).append(",");
				}
			}
		}
		signAgreement.setAgreedMatter(String.valueOf(string));
		string.setLength(0);//清空 字符串
		//履行协议方式
		if (signAgreement.getPerformList()!=null && !signAgreement.getPerformList().isEmpty() ){
			for (TypeInfo typeInfo : signAgreement.getPerformList()){
				if (StringUtils.isNotBlank(typeInfo.getLabel()) && "1".equals(typeInfo.getLabel())){
					string.append(typeInfo.getTypeId()).append(",");
				}
			}
		}
		signAgreement.setPerformAgreementMode(String.valueOf(string));
		string.setLength(0);//清空 字符串
		//协议说明
		if (signAgreement.getAgreementList()!=null && !signAgreement.getAgreementList().isEmpty()){
			for (TypeInfo typeInfo : signAgreement.getAgreementList()){
				if (StringUtils.isNotBlank(typeInfo.getLabel()) && "1".equals(typeInfo.getLabel())){
					string.append(typeInfo.getTypeId()).append(",");
				}
			}
		}
		signAgreement.setAgreementExplain(String.valueOf(string));
	}

	//保存患方联系人
	@Transactional(readOnly = false)
	public void detail(SignAgreement signAgreement,String linkType) {
		//对患方 医方联系人 进行保存
		List<PatientLinkEmp> emp=new ArrayList<PatientLinkEmp>();
		if ("1".equals(linkType)){
			emp=signAgreement.getPatientLinkEmpList();
		}else {
			emp=signAgreement.getPatientLinkDList();
		}
		for (PatientLinkEmp patientLinkEmp : emp){
			if(patientLinkEmp.getId() == null){
				continue;
			}
			if(MediateRecord.DEL_FLAG_NORMAL.equals(patientLinkEmp.getDelFlag()) || "".equals(patientLinkEmp.getDelFlag())){
				if(StringUtils.isBlank(patientLinkEmp.getPatientLinkEmpId())){
					patientLinkEmp.setRelationId(signAgreement.getSignAgreementId());
					patientLinkEmp.preInsert();
					patientLinkEmp.setPatientLinkEmpId(patientLinkEmp.getId());
					patientLinkEmp.setLinkType(linkType);
					patientLinkEmp.setDelFlag("0");
					patientLinkEmpDao.insert(patientLinkEmp);
				}else {
					patientLinkEmp.preUpdate();
					patientLinkEmpDao.update(patientLinkEmp);
				}
			}else{
				patientLinkEmpDao.delete(patientLinkEmp);
			}
		}
	}

	//保存医方联系人
	@Transactional(readOnly = false)
	public void doctorDetail(SignAgreement signAgreement) {
		//对患方 医方联系人 进行保存
		List<MedicalOfficeEmp> emp=new ArrayList<MedicalOfficeEmp>();
		if (signAgreement.getMedicalOfficeEmpList()!=null){
			emp=signAgreement.getMedicalOfficeEmpList();
		}
		for (MedicalOfficeEmp medicalOfficeEmp : emp){
//			if(medicalOfficeEmp.getId() == null){
//				continue;
//			}
			if(MediateRecord.DEL_FLAG_NORMAL.equals(medicalOfficeEmp.getDelFlag()) || "".equals(medicalOfficeEmp.getDelFlag())){
				if(StringUtils.isBlank(medicalOfficeEmp.getMedicalOfficeEmpId())){
					medicalOfficeEmp.setRelationId(signAgreement.getSignAgreementId());
					medicalOfficeEmp.preInsert();
					medicalOfficeEmp.setMedicalOfficeEmpId(medicalOfficeEmp.getId());
					medicalOfficeEmp.setDelFlag("0");
					medicalOfficeEmpDao.insert(medicalOfficeEmp);
				}else {
					medicalOfficeEmp.preUpdate();
					medicalOfficeEmpDao.update(medicalOfficeEmp);
				}
			}else{
				medicalOfficeEmpDao.delete(medicalOfficeEmp);
			}
		}
	}
	/*
	 *对 逗号分割的数据进行  处理  然后放入list中
	 * @param
	 */
	public void label(List<TypeInfo> typeInfos,String data){
		if (StringUtils.isNotBlank(data)){//有数据进行 处理
			String[] asplit=data.split(",");
			for (TypeInfo typeInfo:typeInfos) {// 根据类型 拿到 数据
				for (String typeId : asplit) {//数据库中存着 用 逗号 隔开的数据
					if (typeId.equals(typeInfo.getTypeId())){
						typeInfo.setLabel("1");
						break;
					}
				}
			}
		}
	}

	//保存附件
	public void savefj(SignAgreement signAgreement,HttpServletRequest request){
		String files1 = request.getParameter("files1");
		String files2 = request.getParameter("files2");
		String files3 = request.getParameter("files3");
		String files4 = request.getParameter("files4");
		String acceId = null;
		String itemId = signAgreement.getSignAgreementId();
		String fjtype1 = request.getParameter("fjtype1");
		String fjtype2 = request.getParameter("fjtype2");
		String fjtype3 = request.getParameter("fjtype3");
		String fjtype4 = request.getParameter("fjtype4");
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
	}
	//查询编号
	public List<SignAgreement> selectAgreementNumber(SignAgreement signAgreement){
		List<SignAgreement> signAgreements = signAgreementDao.selectAgreementNumber(signAgreement);
		return signAgreements;
	}
	//调解程序表
	public void saveMe(SignAgreement signAgreement){
		MediateProgram mediateProgram=signAgreement.getMediateProgram();
		ComplaintMain complaintMain = complaintMainService.get(signAgreement.getComplaintMainId());
		if(StringUtils.isBlank(mediateProgram.getMediateProgramId())){
			mediateProgram.setMediateProgramId(IdGen.uuid());
			mediateProgram.setRelationId(signAgreement.getSignAgreementId());
			mediateProgram.setPatient(complaintMain.getPatientName());
			mediateProgram.setDoctor(complaintMain.getInvolveHospital());
			mediateProgram.preInsert();
			mediateProgramDao.insert(mediateProgram);
		}else {
			mediateProgram.preUpdate();
			mediateProgram.setPatient(complaintMain.getPatientName());
			mediateProgram.setDoctor(complaintMain.getInvolveHospital());
			mediateProgram.setRelationId(signAgreement.getSignAgreementId());
			mediateProgramDao.update(mediateProgram);
		}
	}
	//保存笔录
	public void saveRecordInfo(SignAgreement signAgreement){
		RecordInfo recordInfo=signAgreement.getRecordInfo();
		ComplaintMain complaintMain = complaintMainService.get(signAgreement.getComplaintMainId());
		if(StringUtils.isBlank(recordInfo.getRecordId())){
			recordInfo.setRecordId(IdGen.uuid());
			recordInfo.setRelationId(signAgreement.getSignAgreementId());
			recordInfo.setModuleType("4");
			recordInfo.setCause(complaintMain.getPatientName()+"与"+complaintMain.getHospital().getName()+"医疗纠纷，经山西省医疗纠纷人民调解委员会调解员调查、调解后，医患双方自愿达成一致意见，今天，在山西省医疗纠纷人民调解委员会调解员主持下，签署人民调解协议书。");
			recordInfo.preInsert();
			recordInfoDao.insert(recordInfo);
		}else{
			recordInfo.preUpdate();
			recordInfo.setModuleType("4");
			recordInfo.setRelationId(signAgreement.getSignAgreementId());
			recordInfo.setCause(complaintMain.getPatientName()+"与"+complaintMain.getHospital().getName()+"医疗纠纷，经山西省医疗纠纷人民调解委员会调解员调查、调解后，医患双方自愿达成一致意见，今天，在山西省医疗纠纷人民调解委员会调解员主持下，签署人民调解协议书。");
			recordInfoDao.update(recordInfo);
		}
	}

	//导出
	public String exportWord(SignAgreement signAgreement,String export,String print,HttpServletRequest request, HttpServletResponse response){
		WordExportUtil wordExportUtil = new WordExportUtil();
		signAgreement = this.get(signAgreement.getSignAgreementId());
		List<PatientLinkEmp> patientLinkEmpList=new ArrayList<PatientLinkEmp>();
		List<PatientLinkEmp> patientLinkDList = new ArrayList<PatientLinkEmp>();
		List<MedicalOfficeEmp> medicalOfficeEmpList = new ArrayList<MedicalOfficeEmp>();
		TypeInfo typeInfo1 = new TypeInfo();
		TypeInfo typeInfo2 = new TypeInfo();
		TypeInfo typeInfo3 = new TypeInfo();
		TypeInfo typeInfo4 = new TypeInfo();
		if(signAgreement.getPatientLinkEmpList().size()!=0){
			patientLinkEmpList = signAgreement.getPatientLinkEmpList();//患方（甲方）
		}
		if(signAgreement.getPatientLinkDList().size()!=0){
			patientLinkDList = signAgreement.getPatientLinkDList();//患方（法定）代理人
		}
		if(signAgreement.getMedicalOfficeEmpList().size()!=0){
			medicalOfficeEmpList = signAgreement.getMedicalOfficeEmpList();//医方（乙方）
		}
		if(StringUtils.isNotBlank(signAgreement.getMediation())){
			String mediation = signAgreement.getMediation();
			String mediation1 = mediation.substring(0, 32);//调节情况id
			typeInfo1 = typeInfoService.get(mediation1);
		}
		if(StringUtils.isNotBlank(signAgreement.getAgreedMatter())){
			String agreedMatter = signAgreement.getAgreedMatter();
			String agreedMatter1 = agreedMatter.substring(0, 32);//协议约定事项id
			typeInfo2 = typeInfoService.get(agreedMatter1);
		}
		if(StringUtils.isNotBlank(signAgreement.getPerformAgreementMode())){
			String performAgreementMode = signAgreement.getPerformAgreementMode();
			String performAgreementMode1 = performAgreementMode.substring(0, 32);//履行协议方式id
			typeInfo3 = typeInfoService.get(performAgreementMode1);
		}
		if(StringUtils.isNotBlank(signAgreement.getAgreementExplain())){
			String agreementExplain = signAgreement.getAgreementExplain();
			String agreementExplain1 = agreementExplain.substring(0, 32);//协议说明id
			typeInfo4 = typeInfoService.get(agreementExplain1);
		}
		String path = request.getServletContext().getRealPath("/");
		String modelPath = path;
		String returnPath="";
		String newFileName = "无标题文件.docx";
		String savaPath=path;
		String pdfPath=path;
		Map<String, Object> params = new HashMap<String, Object>();
		//判断有无案件编号
		String num=null;
		if(signAgreement.getComplaintMain()!=null){
			num=signAgreement.getComplaintMain().getCaseNumber()==null?"":signAgreement.getComplaintMain().getCaseNumber()+"/";
		}else{
			num="";
		}
		if("agreementExport".equals(export)){
			if(patientLinkEmpList.size()!=0){
				params.put("pName",patientLinkEmpList.get(0).getPatientLinkName());
				if("1".equals(patientLinkEmpList.get(0).getPatientRelation())){
					params.put("pRlation","本人");
				}else if("2".equals(patientLinkEmpList.get(0).getPatientRelation())){
					params.put("pRlation","夫妻");
				}else if("3".equals(patientLinkEmpList.get(0).getPatientRelation())){
					params.put("pRlation","子女");
				}else if("4".equals(patientLinkEmpList.get(0).getPatientRelation())){
					params.put("pRlation","父母");
				}else if("5".equals(patientLinkEmpList.get(0).getPatientRelation())){
					params.put("pRlation","兄妹");
				}else if("6".equals(patientLinkEmpList.get(0).getPatientRelation())){
					params.put("pRlation","亲属");
				}else if("7".equals(patientLinkEmpList.get(0).getPatientRelation())){
					params.put("pRlation","其他");
				}
				params.put("pIdNum",patientLinkEmpList.get(0).getIdNumber());
				params.put("pAdress",patientLinkEmpList.get(0).getPatientLinkAddress());
			}else{
				params.put("pName","");
				params.put("pRlation","");
				params.put("pIdNum","");
				params.put("pAdress","");
			}
			if(patientLinkDList.size()!=0){
				params.put("pdName",patientLinkDList.get(0).getPatientLinkName());
				if("1".equals(patientLinkDList.get(0).getPatientRelation())){
					params.put("pdRelation","本人");
				}else if("2".equals(patientLinkDList.get(0).getPatientRelation())){
					params.put("pdRelation","夫妻");
				}else if("3".equals(patientLinkDList.get(0).getPatientRelation())){
					params.put("pdRelation","子女");
				}else if("4".equals(patientLinkDList.get(0).getPatientRelation())){
					params.put("pdRelation","父母");
				}else if("5".equals(patientLinkDList.get(0).getPatientRelation())){
					params.put("pdRelation","兄妹");
				}else if("6".equals(patientLinkDList.get(0).getPatientRelation())){
					params.put("pdRelation","亲属");
				}else if("7".equals(patientLinkDList.get(0).getPatientRelation())){
					params.put("pdRelation","其他");
				}
				params.put("pdNumber",patientLinkDList.get(0).getIdNumber());
				params.put("pdAdress",patientLinkDList.get(0).getPatientLinkAddress());
			}else{
				params.put("pdName","");
				params.put("pdRelation","");
				params.put("pdNumber","");
				params.put("pdAdress","");
			}
			if (medicalOfficeEmpList.size()!=0){
				params.put("hName",medicalOfficeEmpList.get(0).getMedicalOfficeName());
				params.put("hAdress",medicalOfficeEmpList.get(0).getMedicalOfficeAddress());
				params.put("legal",medicalOfficeEmpList.get(0).getLegalRepresentative());
				params.put("post",medicalOfficeEmpList.get(0).getMedicalOfficePost());
				params.put("agent",medicalOfficeEmpList.get(0).getMedicalOfficeAgent());
				if ("1".equals(medicalOfficeEmpList.get(0).getMedicalOfficeSex())) {
					params.put("hSex","男");
				}else{
					params.put("hSex","女");
				}
				params.put("idCard",medicalOfficeEmpList.get(0).getMedicalOfficeIdcard());
				params.put("company",medicalOfficeEmpList.get(0).getMedicalOfficeCompany());
			}else{
				params.put("hName","");
				params.put("hAdress","");
				params.put("legal","");
				params.put("post","");
				params.put("agent","");
				params.put("hSex","");
				params.put("idCard","");
				params.put("company","");
			}
			if(StringUtils.isNotBlank(signAgreement.getSummaryOfDisputes())){
				params.put("summary",signAgreement.getSummaryOfDisputes());
			}else{
				params.put("summary","");
			}
			if(typeInfo1 != null){
				params.put("tjTypeName",typeInfo1.getTypeName()==null?"":typeInfo1.getTypeName());
				params.put("tjContent",typeInfo1.getContent()==null?"":typeInfo1.getContent());
			}else{
				params.put("tjTypeName","");
				params.put("tjContent","");
			}
			if(typeInfo2 != null){
				params.put("yContent",typeInfo2.getContent()==null?"":typeInfo2.getContent());
			}else{
				params.put("yContent","");
			}
			if(typeInfo3 !=null){
				params.put("lTypeName",typeInfo3.getTypeName()==null?"":typeInfo3.getTypeName());
				params.put("lContent",typeInfo3.getContent()==null?"":typeInfo3.getTypeName());
			}else{
				params.put("lTypeName","");
				params.put("lContent","");
			}
			if(typeInfo4 != null){
				params.put("xTypeName",typeInfo4.getTypeName()==null?"":typeInfo4.getTypeName());
				params.put("xContent",typeInfo4.getContent()==null?"":typeInfo4.getContent());
			}else{
				params.put("xTypeName","");
				params.put("xContent","");
			}
			    //协议号
				params.put("agreementNumber",signAgreement.getAgreementNumber()==null?"":signAgreement.getAgreementNumber());
			path += "/doc/agreement.docx";  //模板文件位置
			modelPath += "/doc/agreement.docx";
			savaPath +="/userfiles/signAgreement/"+num+"agreement.docx";
			pdfPath +="/userfiles/signAgreement/"+num+"agreement.pdf";
			returnPath="/userfiles/signAgreement/"+num+"agreement.pdf";
			newFileName = "山西省医疗纠纷人民调解委员会人民调解协议书.docx";
		}else if ("meeting".equals(export)){
			if(signAgreement.getMediateProgram()!=null) {
				params.put("time", signAgreement.getMediateProgram().getMeetingTime() == null ? "" : signAgreement.getMediateProgram().getMeetingTime());
				params.put("address", signAgreement.getMediateProgram().getAddress() == null ? "" : signAgreement.getMediateProgram().getAddress());
				params.put("case", signAgreement.getComplaintMain().getPatientName() == null || signAgreement.getComplaintMain().getHospital().getName() == null ? "" : signAgreement.getComplaintMain().getPatientName() + "与" + signAgreement.getComplaintMain().getHospital().getName() + "的医疗纠纷。");
				params.put("tiao", signAgreement.getMediateProgram().getMediatorUser().getName() == null ? "" : signAgreement.getMediateProgram().getMediatorUser().getName());
				params.put("pen", signAgreement.getMediateProgram().getClerkuser().getName() == null ? "" : signAgreement.getMediateProgram().getClerkuser().getName());
				params.put("patient", signAgreement.getComplaintMain().getPatientName() == null ? "" : signAgreement.getComplaintMain().getPatientName());
				params.put("doctor", signAgreement.getComplaintMain().getHospital().getName() == null ? "" : signAgreement.getComplaintMain().getHospital().getName());
				params.put("hAvoid", signAgreement.getMediateProgram().getPatientAvoid() == null ? "" : signAgreement.getMediateProgram().getPatientAvoid());
				params.put("yAvoid", signAgreement.getMediateProgram().getDoctorAvoid() == null ? "" : signAgreement.getMediateProgram().getDoctorAvoid());
				params.put("hclear", signAgreement.getMediateProgram().getPatientClear() == null ? "" : signAgreement.getMediateProgram().getPatientClear());
				params.put("yclear", signAgreement.getMediateProgram().getDoctorClear() == null ? "" : signAgreement.getMediateProgram().getDoctorClear());
			}else{
				params.put("time", "");
				params.put("address", "");
				params.put("case", "");
				params.put("tiao", "");
				params.put("pen", "");
				params.put("patient", "");
				params.put("doctor", "");
				params.put("hAvoid", "");
				params.put("yAvoid", "");
				params.put("hclear", "");
				params.put("yclear", "");
			}
			path += "/doc/mediateMeeting.docx";  //模板文件位置
			modelPath += "/doc/mediateMeeting.docx";
			savaPath +="/userfiles/signAgreement/"+num+"mediateMeeting.docx";
			pdfPath +="/userfiles/signAgreement/"+num+"mediateMeeting.pdf";
			returnPath="/userfiles/signAgreement/"+num+"mediateMeeting.pdf";
			newFileName="调解程序表.docx";
		}else if("record".equals(export)){
			if(signAgreement.getRecordInfo()!=null) {
				params.put("startTime", signAgreement.getRecordInfo().getStartTime() == null ? "" : signAgreement.getRecordInfo().getStartTime());
				params.put("endTime", signAgreement.getRecordInfo().getEndTime() == null ? "" : signAgreement.getRecordInfo().getEndTime());
				params.put("address", signAgreement.getRecordInfo().getRecordAddress() == null ? "" : signAgreement.getRecordInfo().getRecordAddress());
				params.put("host", signAgreement.getRecordInfo().getYtwHost().getName() == null ? "" : signAgreement.getRecordInfo().getYtwHost().getName());
				params.put("noteTaker", signAgreement.getRecordInfo().getYtwNoteTaker().getName() == null ? "" : signAgreement.getRecordInfo().getYtwNoteTaker().getName());
				params.put("patient", signAgreement.getRecordInfo().getPatient() == null ? "" : signAgreement.getRecordInfo().getPatient());
				params.put("doctor", signAgreement.getRecordInfo().getDoctor() == null ? "" : signAgreement.getRecordInfo().getDoctor());
				params.put("patName", signAgreement.getComplaintMain().getPatientName() == null ? "" : signAgreement.getComplaintMain().getPatientName());
				params.put("docName", signAgreement.getComplaintMain().getHospital().getName() == null ? "" : signAgreement.getComplaintMain().getHospital().getName());
				params.put("content", signAgreement.getRecordInfo().getRecordContent() == null ? "" : signAgreement.getRecordInfo().getRecordContent());
			}else{
				params.put("startTime", "");
				params.put("endTime", "");
				params.put("address", "");
				params.put("host", "");
				params.put("noteTaker", "");
				params.put("patient", "");
				params.put("doctor", "");
				params.put("patName","");
				params.put("docName", "");
				params.put("content","");
			}
			path += "/doc/signRecord.docx";  //模板文件位置
			modelPath += "/doc/signRecord.docx";
			savaPath +="/userfiles/signAgreement/"+num+"signRecord.docx";
			pdfPath +="/userfiles/signAgreement/"+num+"signRecord.pdf";
			returnPath="/userfiles/signAgreement/"+num+"signRecord.pdf";
			newFileName="签署协议笔录.docx";
		}
		try{
			File file =new File(request.getServletContext().getRealPath("/")+"/userfiles/signAgreement/"+num);
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
}