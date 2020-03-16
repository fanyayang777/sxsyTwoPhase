/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintdetail.service;

import java.util.*;

import com.google.common.collect.Lists;
import com.sayee.sxsy.common.utils.BaseUtils;
import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.ObjectUtils;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.act.utils.ActUtils;
import com.sayee.sxsy.modules.complaint.entity.ComplaintInfo;
import com.sayee.sxsy.modules.complaintmain.dao.ComplaintMainDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.machine.entity.MachineAccount;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.Role;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.complaintdetail.entity.ComplaintMainDetail;
import com.sayee.sxsy.modules.complaintdetail.dao.ComplaintMainDetailDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 医调委投诉接待Service
 * @author zhangfan
 * @version 2019-06-05
 */
@Service
@Transactional(readOnly = true)
public class ComplaintMainDetailService extends CrudService<ComplaintMainDetailDao, ComplaintMainDetail> {
	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private ComplaintMainDao complaintMainDao;
	@Autowired
	private ComplaintMainDetailDao complaintMainDetailDao;
	public ComplaintMainDetail get(String id) {
        return super.get(id);
	}
	
	public List<ComplaintMainDetail> findList(ComplaintMainDetail complaintMainDetail) {
		return super.findList(complaintMainDetail);
	}
	
	public Page<ComplaintMainDetail> findPage(Page<ComplaintMainDetail> page, ComplaintMainDetail complaintMainDetail) {
		List<Role> roleList=UserUtils.getRoleList();//获取当前登陆人角色
		List<String> aa= ObjectUtils.convert(roleList.toArray(),"enname",true);
		User user=UserUtils.getUser();
		if (user.isAdmin() || aa.contains("commission") || aa.contains("DirectorOfMediation")){//是管理员  医调委主任 调解部副主任  查看全部
				//!aa.contains("dept") &&
		}else if((  aa.contains("deputyDirector") ||aa.contains("director")) ){
			//工作站 主任 副主任 看自己 的员工
				List<String> list=new ArrayList<String>();
				List<User> listUser=UserUtils.getUserByOffice(user.getOffice().getId());
				for (User people:listUser) {
					list.add(people.getId());
				}
				if (list.size()>0){
					complaintMainDetail.setList(list);
				}else {
					list.add(user.getId());
					complaintMainDetail.setList(list);
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
					list.add(people.getId());
				}
			}
			//添加 自己的loginName
			list.add(UserUtils.getUser().getId());
			if (list.size()>0){
				complaintMainDetail.setList(new ArrayList(list));
			}else {
				list.add(user.getLoginName());
				complaintMainDetail.setList(new ArrayList(list));
			}
		}else {//不是管理员查看自己创建的
			complaintMainDetail.setUser(user);
		}
		return super.findPage(page, complaintMainDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(ComplaintMainDetail complaintMainDetail,HttpServletRequest request) {
		if (StringUtils.isNotBlank(complaintMainDetail.getComplaintMainDetailId())){
			//说明是 进行修改
			//修改主表信息
			ComplaintMain complaintMain=complaintMainDetail.getComplaintMain();
			complaintMain.preUpdate();
			complaintMain.setComplaintMainId(complaintMainDetail.getComplaintMainId());
			//获得 医院机构的实体类
			Office office=UserUtils.getOfficeId(complaintMain.getInvolveHospital());
			complaintMain.setHospitalGrade(office==null ? "":office.getHospitalGrade());
			complaintMain.setSource("1");
			complaintMainDao.update(complaintMain);
			//修改投诉接待表
			complaintMainDetail.preUpdate();
			complaintMainDetailDao.update(complaintMainDetail);
		}else{
			//进行新增
			//先保存主表
			ComplaintMain complaintMain=complaintMainDetail.getComplaintMain();
			complaintMain.preInsert();
			complaintMain.setComplaintMainId(complaintMain.getId());
			if(StringUtils.isBlank(complaintMain.getPatientAge())){
				complaintMain.setPatientAge("0");
			}
			//获得 医院机构的实体类
			Office office=UserUtils.getOfficeId(complaintMain.getInvolveHospital());
			complaintMain.setHospitalGrade(office==null ? "":office.getHospitalGrade());
			complaintMain.setSource("1");//信息来源
            complaintMain.setCaseNumber(BaseUtils.getCode("year","4","COMPLAINT_MAIN","case_number"));
            complaintMainDao.insert(complaintMain);
			//在保存子表
			complaintMainDetail.preInsert();
			complaintMainDetail.setComplaintMainId(complaintMain.getComplaintMainId());
			complaintMainDetail.setComplaintMainDetailId(complaintMainDetail.getId());
			dao.insert(complaintMainDetail);
		}
		//保存附件
		this.savefj(request,complaintMainDetail);
		if ("yes".equals(complaintMainDetail.getComplaintMain().getAct().getFlag())){
			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","1");
			User assigness= UserUtils.get(complaintMainDetail.getNextLinkMan());
			//logger.debug("用户名======="+assigness);
			var.put("enrollment_user", null==assigness ? "thinkgem" : assigness.getLoginName());
			var.put("id","complaint_main_id");
			// 启动流程
			actTaskService.startProcess("complaint", "COMPLAINT_MAIN", complaintMainDetail.getComplaintMain().getComplaintMainId(), complaintMainDetail.getComplaintMain().getCaseNumber(),var);
			//启动流程的时候 创建一个 隐藏的角色

		}
	}
	
	@Transactional(readOnly = false)
	public void delete(ComplaintMainDetail complaintMainDetail) {
		super.delete(complaintMainDetail);
	}


	@Transactional(readOnly = false)
	public void savefj(HttpServletRequest request, ComplaintMainDetail complaintMainDetail){
		String files1 = request.getParameter("files1");
		String files2 = request.getParameter("files2");
		String acceId = null;
		String itemId = complaintMainDetail.getComplaintMainDetailId();
		String fjtype1 = request.getParameter("fjtype1");
		String fjtype2 = request.getParameter("fjtype2");


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

	}
	@Transactional(readOnly = false)
    public void saveShift(ComplaintMainDetail complaintMainDetail) {
		dao.saveShift(complaintMainDetail);
    }
}