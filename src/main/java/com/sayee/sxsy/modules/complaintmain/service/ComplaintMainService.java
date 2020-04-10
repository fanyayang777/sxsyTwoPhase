/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintmain.service;

import java.util.*;

import com.google.common.collect.Lists;
import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.utils.BaseUtils;
import com.sayee.sxsy.common.utils.DateUtils;
import com.sayee.sxsy.common.utils.ObjectUtils;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.complaintdetail.entity.ComplaintMainDetail;
import com.sayee.sxsy.modules.complaintmain.web.ComplaintMainController;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.Role;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import com.sayee.sxsy.test.dao.TestTreeDao;
import com.sayee.sxsy.test.entity.TestTree;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;

import javax.servlet.jsp.jstl.core.Config;

/**
 * 纠纷调解Service
 * @author lyt
 * @version 2019-06-04
 */
@Service
@Transactional(readOnly = true)
public class ComplaintMainService extends CrudService<ComplaintMainDao, ComplaintMain> {
	@Autowired
	private ComplaintMainDao complaintMainDao;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private TaskService taskService;
	@Autowired
	private TestTreeDao testTreeDao;

	public ComplaintMain get(String id) {
		return super.get(id);
	}

	public List<ComplaintMain> findList(ComplaintMain complaintMain) {
		return super.findList(complaintMain);
	}

	public Page<ComplaintMain> findPage(Page<ComplaintMain> page, ComplaintMain complaintMain) {
		return super.findPage(page, complaintMain);
	}

	@Transactional(readOnly = false)
	public synchronized void save(ComplaintMain complaintMain) {
		if(StringUtils.isBlank(complaintMain.getComplaintMainId())){
			complaintMain.preInsert();
			//防止案件编码 重复，在 查找 库一遍
			complaintMain.setCaseNumber(BaseUtils.getCode("year","4","COMPLAINT_MAIN","case_number"));
			complaintMain.setComplaintMainId(complaintMain.getId());
			dao.insert(complaintMain);
		}else{
			complaintMain.preUpdate();
			dao.update(complaintMain);
		}
//		super.save(complaintMain);
	}

	@Transactional(readOnly = false)
	public void delete(ComplaintMain complaintMain) {
		super.delete(complaintMain);
	}
	/**
	 * 获取我的待办数目
	 */
	public Long findCount(User user) {
		return  complaintMainDao.findCount(user.getLoginName());
	}

	public Page<ComplaintMain> selfList(Page<ComplaintMain> page, ComplaintMain complaintMain) {
		//  List<Task> taskList  = taskService.createTaskQuery().taskAssignee(assignee).list();
		List<ComplaintMain> list=complaintMainDao.selfList(complaintMain.getUser().getLoginName());

		for (ComplaintMain main : list) {
				boolean s2 = main.getInvolveDepartment().matches(".*[a-z]+.*");
				if(s2==true){
					String departmentName = complaintMainDao.findDepartmentName(main.getInvolveDepartment());
					main.setInvolveDepartment(departmentName);
				}
		}
		page.setList(list);
		page.setCount(list.size());
		complaintMain.setPage(page);
		//对 集合进行处理，把节点的 主键拿到 可是使其点击 详情与处理 按钮
		this.format(list);
		return page;
	}

	//区域案件
	public Page<ComplaintMain> workstation(Page<ComplaintMain> page, ComplaintMain complaintMain) {
		List<Role> roleList=UserUtils.getRoleList();//获取当前登陆人角色
		List<String> aa= ObjectUtils.convert(roleList.toArray(),"enname",true);
		System.out.println(aa);
		User user=UserUtils.getUser();
		if (user.isAdmin() || aa.contains("quanshengtiaojiebuzhuren") || aa.contains("yitiaoweizhuren")
				|| aa.contains("yitiaoweifuzhuren")|| aa.contains("shengzhitiaojiebuzhuren/fuzhuren")|| aa.contains("yitiaoweizhuren")
		){	//!aa.contains("dept") &&
			List<String> list= new ArrayList<String>();
			list = complaintMainDao.rootFindUserId();
			List<ComplaintMain> anjian=new ArrayList<>();
			for (String str:list) {
				anjian.addAll(complaintMainDao.selfList(str));
			}
			for (ComplaintMain main : anjian) {
				String[] split = main.getInvolveDepartment().split(",");
				String departmentNewName = "";
				for (String s : split) {
					boolean br = s.matches(".*[a-z]+.*");
					if(br){
						departmentNewName += complaintMainDao.findDepartmentName(s)+"   ";
					}
				}
				try{
					main.setInvolveDepartment(departmentNewName);
				}catch (Exception e){
				}
			}
			page.setList(anjian);
			page.setCount(list.size());

		}else if((aa.contains("gongzuozhanzhuren/fuzhuren")) ){
			//工作站 主任 副主任 看自己 的员工
			List<String> list=new ArrayList<String>();
			List<User> listUser=UserUtils.getUserByOffice(user.getOffice().getId());
			for (User people:listUser) {
				list.add(people.getLoginName());
			}
			List<ComplaintMain> anjian=new ArrayList<>();
			for (String str:list) {
				anjian=complaintMainDao.selfList(str);
			}
			for (ComplaintMain main : anjian) {
				String[] split = main.getInvolveDepartment().split(",");
				String departmentNewName = "";
				for (String s : split) {
					boolean br = s.matches(".*[a-z]+.*");
					if(br){
						departmentNewName += complaintMainDao.findDepartmentName(s)+"   ";
					}
				}
				try{
					main.setInvolveDepartment(departmentNewName);
				}catch (Exception e){
				}
			}
			page.setList(anjian);
			page.setCount(list.size());
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
			List<ComplaintMain> anjian=new ArrayList<>();
			for (String str:list) {
				anjian.addAll(complaintMainDao.selfList(str));
			}
			for (ComplaintMain main : anjian) {
				String[] split = main.getInvolveDepartment().split(",");
				String departmentNewName = "";
				for (String s : split) {
					boolean br = s.matches(".*[a-z]+.*");
					if(br){
						departmentNewName += complaintMainDao.findDepartmentName(s)+"   ";
					}
				}
				try{
					main.setInvolveDepartment(departmentNewName);
				}catch (Exception e){
				}
			}
			page.setList(anjian);
			page.setCount(list.size());
		}else {//不是管理员查看自己创建的
//			assessAppraisal.setUser(UserUtils.getUser());
		}

		complaintMain.setPage(page);
		//对 集合进行处理，把节点的 主键拿到 可是使其点击 详情与处理 按钮
		this.format(page.getList());
		System.out.println("page.list:"+page.getList());
		return page;
	}

//	public List<Map<String,Object>> selfList(User user) {
//		List<Map<String,Object>> list=complaintMainDao.selfList(user.getLoginName());
//		//对 集合进行处理，把节点的 主键拿到 可是使其点击 详情与处理 按钮
//		this.format(list);
//    	return list;
//	}

	public void format(List<ComplaintMain> list){
		String key="";
		String path="";
		for (ComplaintMain complaintMain: list) {
			if ("enrollment".equals(complaintMain.getTaskDefKey())){
				key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"complaint_main_detail_id","COMPLAINT_MAIN_DETAIL");
				}
				path="/registration/reportRegistration/";
			}else if("check".equals(complaintMain.getTaskDefKey())){
				key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"audit_acceptance_id","AUDIT_ACCEPTANCE");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				path="/auditacceptance/auditAcceptance/";
			}else if("forensics".equals(complaintMain.getTaskDefKey())){
				key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"investigate_evidence_id","INVESTIGATE_EVIDENCE");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				path="/nestigateeividence/investigateEvidence/";
			}else if("mediation".equals(complaintMain.getTaskDefKey())){
				key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"mediate_evidence_id","MEDIATE_EVIDENCE");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				path="/mediate/mediateEvidence/";
			}else if("apply".equals(complaintMain.getTaskDefKey())){
				key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"assess_apply_id","ASSESS_APPLY");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				path="/assessapply/assessApply/";
			}else if("approval".equals(complaintMain.getTaskDefKey())){
				key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"assess_audit_id","ASSESS_AUDIT");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"assess_apply_id","ASSESS_APPLY");
				}
				path="/assessaudit/assessAudit/";
			}else if("evaluation".equals(complaintMain.getTaskDefKey())){
				key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"assess_appraisal_id","ASSESS_APPRAISAL");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				path="/assessappraisal/assessAppraisal/";
			}else if("reach".equals(complaintMain.getTaskDefKey())){
				key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"reach_mediate_id","REACH_MEDIATE");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				path="/reachmediate/reachMediate/";
			}else if("sign".equals(complaintMain.getTaskDefKey())){
				key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"sign_agreement_id","SIGN_AGREEMENT");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				path="/sign/signAgreement/";
			}else if("fulfill".equals(complaintMain.getTaskDefKey())){
				key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"perform_agreement_id","PERFORM_AGREEMENT");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				path="/perform/performAgreement/";
			}else if("summary".equals(complaintMain.getTaskDefKey())){
				key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"summary_id","SUMMARY_INFO");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				path="/summaryinfo/summaryInfo/";
			}else if("assess".equals(complaintMain.getTaskDefKey())){
				key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"assess_id","ASSESS_INFO");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				path="/assessinfo/assessInfo/";
			}else if("feedback".equals(complaintMain.getTaskDefKey())){
				key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"feedback_id","CASE_FEEDBACK");
				if (StringUtils.isBlank(key)){
					key=complaintMainDao.getKey(complaintMain.getComplaintMainId(),"report_registration_id","REPORT_REGISTRATION");
				}
				path="/casefeedback/caseFeedback/";
			}
			if (StringUtils.isNotBlank(key)){
				complaintMain.setKey(key);
				complaintMain.setUrl(path);
			}
		}
	}


	public List<ComplaintMain> getMyDone(String loginName) {
		//我的待办
		List<HistoricTaskInstance> taskList  = historyService.createHistoricTaskInstanceQuery()
				.taskAssignee(loginName)
				.finished()
				.list();
		taskList.sort(new Comparator<HistoricTaskInstance>() {
			@Override
			public int compare(HistoricTaskInstance o1, HistoricTaskInstance o2) {
				Date d1=o1.getEndTime();
				Date d2=o2.getEndTime();
				return d1.compareTo(d2);
			}
		});
		Set<ComplaintMain> list=new HashSet<>();
		for (HistoricTaskInstance his:taskList) {
			//通过实例id 获取主表信息
			ComplaintMain complaintMain=complaintMainDao.getMain(his.getProcessInstanceId());
			if (complaintMain!=null){
				complaintMain.setNodeName(his.getName());
				complaintMain.setTaskDefKey(his.getTaskDefinitionKey());
				list.add(complaintMain);
			}
		}
		//我的发起
		List<HistoricProcessInstance> fqList=historyService.createHistoricProcessInstanceQuery()
				.startedBy(loginName)
				.list();
		for (HistoricProcessInstance processInstance:fqList) {
			//通过实例id 获取主表信息
			ComplaintMain complaintMain=complaintMainDao.getMain(processInstance.getId());
			if (complaintMain!=null){
				list.add(complaintMain);
			}
		}
		//根据登录人 取得登陆人已经办理完成的数据
		//List<ComplaintMain> list=complaintMainDao.getMyDone(loginName);
		//清除查到的  null  值
		list.removeAll(Collections.singleton(null));
		this.format(new ArrayList(list));
		return new ArrayList(list);
	}

	public List<Map<String, Object>> findTypeInfo(User user,String year,String beginMonthDate,String endMonthDate,String type) {

		String areaId =UserUtils.getUser().getCompany().getArea().getId();
		if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
			year= DateUtils.getYear();
		}
		List<Map<String, Object>> list=null;
		if ("tj".equals(type)){
			list=complaintMainDao.findTypeInfoTj(year,beginMonthDate,endMonthDate,areaId);
		}else{
			list=complaintMainDao.findTypeInfo(year,beginMonthDate,endMonthDate,areaId);
		}
		return list;
	}

	public List<Map<String, Object>> findGrade(User user,String year,String beginMonthDate,String endMonthDate,String type) {
	    String areaId=UserUtils.getUser().getCompany().getArea().getId();
		if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
			year= DateUtils.getYear();
		}
		List<Map<String, Object>> list=null;
		if ("tj".equals(type)){
			list=complaintMainDao.findGradeTj(year,beginMonthDate,endMonthDate,areaId);
		}else{
			list=complaintMainDao.findGrade(year,beginMonthDate,endMonthDate,areaId);
		}
		return list;
	}

	public List<Map<String, Object>> getEveryMonthData(User user,String year,String beginMonthDate,String endMonthDate,String type) {
		String officeId="";
		if (user.getRoleList().contains("yydept")){
			officeId=user.getOffice().getId();
		}
		if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
			year= DateUtils.getYear();
		}
		List<Map<String, Object>> list=null;
		if ("tj".equals(type)){
			list=complaintMainDao.getEveryMonthDataTj(year,beginMonthDate,endMonthDate,officeId);
		}else{
			list=complaintMainDao.getEveryMonthData(year,beginMonthDate,endMonthDate,officeId);
		}
		return list;
	}

	/***
	 * 获取山西省各市的案件数量
	 * @param year
	 * @param beginMonthDate,endMonthDate
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<Map<String,Object>> findAreaName (User user,String year,String beginMonthDate,String endMonthDate,String type){

		String areaId= UserUtils.getUser().getCompany().getArea().getId();
		if(StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
			year =  DateUtils.getYear();
		}
		List<Map<String, Object>> list=null;
		if ("tj".equals(type)){
			list=complaintMainDao.findAreaNameTj(year,beginMonthDate,endMonthDate,areaId);
		}else{
			list=complaintMainDao.findAreaName(year,beginMonthDate,endMonthDate,areaId);
		}
		return list;
	}

	public List<ComplaintMain > findCompleted(User user,String year,String beginMonthDate,String endMonthDate) {
		String officeId="";
		if (user.getRoleList().contains("yydept")){
			officeId=user.getOffice().getId();
		}
		if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
			year= DateUtils.getYear();
		}
		//根据登录人 取得登陆人已经办理完成的数据
		List<ComplaintMain> list=complaintMainDao.findCompleted(year,beginMonthDate,endMonthDate,officeId);
		//清除查到的  null  值
		list.removeAll(Collections.singleton(null));
		this.format(list);
		return list;
	}
	public Long findAllEvent(User user,String year,String beginMonthDate,String endMonthDate) {
		String officeId="";
		if (user.getRoleList().contains("yydept")){
			officeId=user.getOffice().getId();
		}
		if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
			year= DateUtils.getYear();
		}
		Long allEvent = complaintMainDao.findAllEvent(year, beginMonthDate,endMonthDate,officeId);
		return allEvent;
	}

	/***
	 * 获取各个专业数据
	 * @param year
	 * @param beginMonthDate,endMonthDate
	 * @return
	 */
	public List<Map<String,Object>> findDepartment(User user,String year,String beginMonthDate,String endMonthDate,String type){

	    String areaId = UserUtils.getUser().getCompany().getArea().getId();
        System.out.println(areaId);
		List<Map<String, Object>> list=null;
		if ("tj".equals(type)){
			list=complaintMainDao.findDepartmentTj(year,beginMonthDate,endMonthDate,areaId);
		}else{
			list=complaintMainDao.findDepartment(year,beginMonthDate,endMonthDate,areaId);
		}
		System.out.println(list);
		return list;
	}
	/**
	 * 专业名称转换
	 */
	public String findDepartmentNewName(String newNameId){
		TestTree departmentNewName = complaintMainDao.findDepartmentNewName(newNameId);
		System.out.println(departmentNewName);
		return departmentNewName.getName();
	}

	/***
	 * 获取责任度 比例统计图
	 * @param year
	 * @param beginMonthDate,endMonthDate
	 * @return
	 */
	public List<Map<String, Object>> findDuty(User user,String year,String beginMonthDate,String endMonthDate,String type) {
		String areaId = UserUtils.getUser().getCompany().getArea().getId();
        System.out.println(areaId);
        if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
			year= DateUtils.getYear();
		}
		List<Map<String, Object>> list=null;
		if ("tj".equals(type)){
			list=complaintMainDao.findDutyTj(year,beginMonthDate,endMonthDate,areaId);
		}else{
			//list=complaintMainDao.findTypeInfo(year,beginMonthDate,endMonthDate);
		}
        System.out.println(list);
        return list;
	}

	/***
	 * 获取 赔偿金额 比例
	 * @param year
	 * @param beginMonthDate,endMonthDate
	 * @return
	 */
	public Map<String, Object> findAmountRatio(User user,String year,String beginMonthDate,String endMonthDate,String type) {
	    String areaId=UserUtils.getUser().getCompany().getArea().getId();
		System.out.println(areaId);
		if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
			year= DateUtils.getYear();
		}
		Map<String, Object> map=null;
		if ("tj".equals(type)){
			map=complaintMainDao.findAmountRatioTj(year,beginMonthDate,endMonthDate,areaId);
		}else{
			//list=complaintMainDao.findTypeInfo(year,beginMonthDate,endMonthDate);
		}
        System.out.println("map:"+map);
        return map;
	}

	/**
	 * 根据城市获得每个城市的赔付总金额
	 * @param user
	 * @param year
	 * @param beginMonthDate
	 * @param endMonthDate
	 * @param type
	 * @return
	 */

	public List<Map<String, String>> findCityAmountRatio(User user,String year,String beginMonthDate,String endMonthDate,String type) {
		String areaId=UserUtils.getUser().getCompany().getArea().getId();
		if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
			year= DateUtils.getYear();
		}
		List<Map<String, String>> cityMap=null;
		if ("tj".equals(type)){
			cityMap=complaintMainDao.findCityAmountRatio(year,beginMonthDate,endMonthDate,areaId);
		}
		System.out.println("cityMap:"+cityMap);
		return cityMap;
	}

	/**
	 * 根据科室获取每个科室的赔付总金额
	 * @param user
	 * @param year
	 * @param beginMonthDate
	 * @param endMonthDate
	 * @param type
	 * @return
	 */
	public List<Map<String, String>> findDepartmentAmountRatio(User user,String year,String beginMonthDate,String endMonthDate,String type) {
		String areaId=UserUtils.getUser().getCompany().getArea().getId();
		if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
			year= DateUtils.getYear();
		}
		List<Map<String, String>> departmentMap=null;
		if ("tj".equals(type)){
			departmentMap=complaintMainDao.findDepartmentAmountRatio(year,beginMonthDate,endMonthDate,areaId);
		}
		System.out.println("departmentMap:"+departmentMap);
		return departmentMap;
	}

	/**
	 * 五年数据
	 * @param user
	 * @param year
	 * @param beginMonthDate
	 * @param endMonthDate
	 * @param type
	 * @return
	 */
	public List<Map<String, String>> fiveYearAmountRatio(User user,String year,String beginMonthDate,String endMonthDate,String type) {
		String areaId=UserUtils.getUser().getCompany().getArea().getId();
		if (StringUtils.isBlank(year) && StringUtils.isBlank(beginMonthDate) && StringUtils.isBlank(endMonthDate)){
			year= DateUtils.getYear();
		}
		List<Map<String, String>> newfiveYearMap=null;
		List<Map<String,String>> newListMap = new ArrayList<Map<String,String>>();
		if ("tj".equals(type)){
			List<String> fiveYearMap=null;
			Integer newYear = Integer.parseInt(year);
			for(int i=0;i<5;i++){
				fiveYearMap=complaintMainDao.fiveYearAmountRatio(newYear.toString(),beginMonthDate,endMonthDate,areaId);
				List value = null;
				String newValue = null;
				//Integer i1 = 0;
				//int i2 = 0;
				try {
					value = ComplaintMainController.convert(fiveYearMap.toArray(), "value", true);
					String regEx="[\n`~!@#$%^&*()+|{}':;'\\[\\]<>/?~！@#￥……&*（）——+|{}【】‘；：”“’。， 、？]";
					String a = "";
					newValue = value.toString().replaceAll(regEx,a);
					//i1 = Integer.parseInt(newValue);
					//i2 = i1 / 10000;
				}catch (Exception e){
				}
				Map<String,String> newMap = new LinkedHashMap<>();
				newMap.put("name","'"+newYear.toString()+"'");
				newMap.put("value",newValue);
				newListMap.add(newMap);
				newYear--;
			}
		}
		System.out.println("newListMap:"+newListMap);
		return newListMap;
	}

	public List<ComplaintMain> getRepeat(String card, String hospital,String complaintMainId) {
		ComplaintMain complaintMain=new ComplaintMain();
		complaintMain.setPatientCard(card);
		complaintMain.setInvolveHospital(hospital);
		complaintMain.setComplaintMainId(complaintMainId);
		return complaintMainDao.getRepeat(complaintMain);
	}
}