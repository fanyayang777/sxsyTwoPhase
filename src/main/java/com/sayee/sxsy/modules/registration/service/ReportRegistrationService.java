/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.registration.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import com.google.common.collect.Lists;
import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.ObjectUtils;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.common.utils.WordExportUtil;
import com.sayee.sxsy.modules.act.entity.Act;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.complaint.dao.ComplaintInfoDao;
import com.sayee.sxsy.modules.complaint.entity.ComplaintInfo;
import com.sayee.sxsy.modules.complaintdetail.dao.ComplaintMainDetailDao;
import com.sayee.sxsy.modules.complaintdetail.entity.ComplaintMainDetail;
import com.sayee.sxsy.modules.complaintdetail.service.ComplaintMainDetailService;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.service.ComplaintMainService;
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
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import com.sayee.sxsy.modules.registration.dao.ReportRegistrationDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 报案登记Service
 * @author lyt
 * @version 2019-06-05
 */
@Service
@Transactional(readOnly = true)
public class ReportRegistrationService extends CrudService<ReportRegistrationDao, ReportRegistration> {

	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private ComplaintMainDao complaintMainDao;
	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	ComplaintMainService complaintMainService;
	@Autowired
	ComplaintMainDetailDao complaintMainDetailDao;
	@Autowired
	ComplaintInfoDao complaintInfoDao;

	public ReportRegistration get(String id) {
		return super.get(id);
	}

	public List<ReportRegistration> findList(ReportRegistration reportRegistration) {
		return super.findList(reportRegistration);
	}

	public Page<ReportRegistration> findPage(Page<ReportRegistration> page, ReportRegistration reportRegistration) {
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
				reportRegistration.setList(list);
			}else {
				list.add(user.getLoginName());
				reportRegistration.setList(list);
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
				reportRegistration.setList(new ArrayList(list));
			}else {
				list.add(user.getLoginName());
				reportRegistration.setList(new ArrayList(list));
			}
		}else {//不是管理员查看自己创建的
			reportRegistration.setUser(user);
		}
		//在报案登记的数据 都会管理 实例ID  ，但是存在投诉接待来，没进行保存；  保存了 没进行下一步的
		Page<ReportRegistration> a=super.findPage(page, reportRegistration);
		return super.findPage(page, reportRegistration);
	}
	/* 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<ReportRegistration> findInsPage(Page<ReportRegistration> page, ReportRegistration reportRegistration) {
		reportRegistration.setPage(page);
		page.setList(dao.findList(reportRegistration));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(ReportRegistration reportRegistration, HttpServletRequest request) {
//		if(reportRegistration.getCreateDate()==null){

        if(StringUtils.isBlank(reportRegistration.getCreateBy().getId())){
			//判断主键ID是否为空
			reportRegistration.preInsert();
			reportRegistration.setReportRegistrationId(reportRegistration.getId());
			//将主键ID设为UUID
			dao.insert(reportRegistration);

		}else{//如果不为空进行更新
			//修改报案登记表
			reportRegistration.preUpdate();
			dao.update(reportRegistration);

		}
		//修改主表信息 因为处理的是  主表事由信息的  对主表信息进行修改即可
		ComplaintMain complaintMain=reportRegistration.getComplaintMain();
		complaintMain.preUpdate();
		complaintMain.setComplaintMainId(reportRegistration.getComplaintMainId());
		complaintMain.setPatientMobile(reportRegistration.getPatientMobile());
		complaintMainDao.update(complaintMain);
		//保存附件
		this.savefj(request,reportRegistration);

//		super.save(reportRegistration);
		if ("yes".equals(reportRegistration.getComplaintMain().getAct().getFlag())){

			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","1");
			var.put("datamember_user", "lhh_admin");
			// 执行流程
			actTaskService.complete(reportRegistration.getComplaintMain().getAct().getTaskId(), reportRegistration.getComplaintMain().getAct().getProcInsId(), reportRegistration.getComplaintMain().getAct().getComment(), reportRegistration.getComplaintMain().getCaseNumber(), var);
		}
	}

	@Transactional(readOnly = false)
	public void delete(ReportRegistration reportRegistration) {
		super.delete(reportRegistration);
	}

	@Transactional(readOnly = false)
	public void savefj(String acceId1,String itemId1,String files1,String fjtype){
		super.dao.insertzf(acceId1,itemId1,files1,fjtype);
	}
	//保存附件
	public void savefj(HttpServletRequest request,ReportRegistration reportRegistration){
		String files = request.getParameter("files");
		String acceId = null;
		String itemId1 = reportRegistration.getReportRegistrationId();
		String fjtype1 = request.getParameter("fjtype");
		if(StringUtils.isNotBlank(files)){
			String acceId1=request.getParameter("acceId1");
			if(StringUtils.isNotBlank(acceId1)){
				preOperativeConsentService.updatefj(files,itemId1,fjtype1);
			}else{
				acceId=IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId1,files,fjtype1);
			}
		}else{
			preOperativeConsentService.delefj(itemId1,fjtype1);
		}
	}

	//导出打印
	public String exportWord(ReportRegistration reportRegistration, String export,String print,HttpServletRequest request, HttpServletResponse response){
		WordExportUtil wordExportUtil=new WordExportUtil();
		reportRegistration = this.get(reportRegistration.getReportRegistrationId());
		ComplaintMain complaintMain = new ComplaintMain();
		if(StringUtils.isNotBlank(reportRegistration.getComplaintMainId())){
			complaintMain=complaintMainService.get(reportRegistration.getComplaintMainId());
		}

		String path=request.getServletContext().getRealPath("/");
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
		if(reportRegistration.getComplaintMain()!=null){
			num=reportRegistration.getComplaintMain().getCaseNumber()==null?"":reportRegistration.getComplaintMain().getCaseNumber()+"/";
		}else{
			num="";
		}
		if("reportDis".equals(export)){
			params.put("hospitol",reportRegistration.getComplaintMain()==null?"":reportRegistration.getComplaintMain().getHospital().getName());//医疗机构名称
			params.put("pNum",reportRegistration.getPolicyNumber()==null?"":reportRegistration.getPolicyNumber());//保单号
			params.put("disTime",reportRegistration.getDisputeTime()==null?"":reportRegistration.getDisputeTime());//纠纷发生时间
			if(reportRegistration.getComplaintMain() !=null){
				params.put("g",DictUtils.getDictLabel(reportRegistration.getComplaintMain().getHospitalGrade(),"hospital_grade","无"));//机构等级
			}else{
				params.put("g","");//机构等级
			}

			params.put("areaName",reportRegistration.getComplaintMain()==null?"":reportRegistration.getComplaintMain().getHospital().getArea().getName());//所属城市
			params.put("reportEmp",reportRegistration.getReportEmp()==null?"":reportRegistration.getReportEmp());//报案人姓名
			params.put("pName",reportRegistration.getComplaintMain()==null?"":reportRegistration.getComplaintMain().getPatientName());//患者姓名
			if(reportRegistration.getComplaintMain() !=null){
				if("1".equals(reportRegistration.getComplaintMain().getPatientSex())){
					params.put("sex","男");
				}else if("2".equals(reportRegistration.getComplaintMain().getPatientSex())){
					params.put("sex","女");
				}else{
					params.put("sex","");
				}
			}else{
				params.put("sex","");
			}
			params.put("age",reportRegistration.getComplaintMain()==null?"":reportRegistration.getComplaintMain().getPatientAge());//年龄
			params.put("idCard",reportRegistration.getComplaintMain()==null?"":reportRegistration.getComplaintMain().getPatientCard()==null?"":reportRegistration.getComplaintMain().getPatientCard());//身份证号
			params.put("reTime",reportRegistration.getReportTime()==null?"":reportRegistration.getReportTime());//出险时间
			if(reportRegistration.getComplaintMain() !=null) {
                params.put("de", reportRegistration.getComplaintMain().getTestTree());
			}else{
				params.put("de", "");
			}
			    params.put("invo",reportRegistration.getComplaintMain()==null?"":reportRegistration.getComplaintMain().getInvolveEmployee());

			if("1".equals(reportRegistration.getIsMajor())){
				params.put("isMajor","是");
			}else{
				params.put("isMajor","否");
			}
			params.put("pMobile",reportRegistration.getPatientMobile()==null?"":reportRegistration.getPatientMobile());//患方联系电话
			params.put("hMobile",reportRegistration.getDoctorMobile()==null?"":reportRegistration.getDoctorMobile());//医方联系电话
			params.put("jiufen",reportRegistration.getSummaryOfDisputes()==null?"":reportRegistration.getSummaryOfDisputes());//纠纷概要
			params.put("focus",reportRegistration.getFocus()==null?"":reportRegistration.getFocus());//纠纷焦点
			params.put("patientAsk",reportRegistration.getPatientAsk()==null?"":reportRegistration.getPatientAsk());//患方要求
			params.put("tianbiao",reportRegistration.getDjEmployee()==null?"":reportRegistration.getDjEmployee().getName());//填表人签名
			params.put("tbTime",reportRegistration.getRegistrationTime()==null?"":reportRegistration.getRegistrationTime());//填表日期
			params.put("caseNum",reportRegistration.getComplaintMain()==null?"":reportRegistration.getComplaintMain().getCaseNumber());//报案号
			params.put("nextLink",reportRegistration.getNextLink()==null?"":reportRegistration.getNextLink());//审核人
			params.put("pRelation",reportRegistration.getPatientRelation()==null?"":reportRegistration.getPatientRelation());//审核日期

			path += "/doc/repord.docx";  //模板文件位置
			savaPath +="/userfiles/report/"+num+"repord.docx";
			pdfPath +="/userfiles/report/"+num+"repord.pdf";
			returnPath="/userfiles/report/"+num+"repord.pdf";
			newFileName="报案信息表.docx";
		}
		try{
			File file =new File(request.getServletContext().getRealPath("/")+"/userfiles/report/"+num);
			if (!file.exists()){
				file.mkdirs();
			}
			List<String[]> testList = new ArrayList<String[]>();
			String fileName= new String(newFileName.getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
			wordExportUtil.getWord(path,path,savaPath,print,params,testList,fileName,response);
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