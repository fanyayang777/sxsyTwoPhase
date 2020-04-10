/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.perform.service;

import java.util.*;

import com.google.common.collect.Lists;
import com.sayee.sxsy.common.utils.IdGen;
import com.sayee.sxsy.common.utils.ObjectUtils;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.act.service.ActTaskService;
import com.sayee.sxsy.modules.surgicalconsentbook.service.PreOperativeConsentService;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.Role;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.perform.entity.PerformAgreement;
import com.sayee.sxsy.modules.perform.dao.PerformAgreementDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 履行协议Service
 * @author lyt
 * @version 2019-06-14
 */
@Service
@Transactional(readOnly = true)
public class PerformAgreementService extends CrudService<PerformAgreementDao, PerformAgreement> {

	@Autowired
	private PreOperativeConsentService preOperativeConsentService;
	@Autowired
	private ActTaskService actTaskService;
	public PerformAgreement get(String id) {
		return super.get(id);
	}
	
	public List<PerformAgreement> findList(PerformAgreement performAgreement) {
		performAgreement.setUser(UserUtils.getUser());
		return super.findList(performAgreement);
	}
	
	public Page<PerformAgreement> findPage(Page<PerformAgreement> page, PerformAgreement performAgreement) {
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
					performAgreement.setList(list);
				}else {
					list.add(user.getLoginName());
					performAgreement.setList(list);
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
				performAgreement.setList(list);
			}else {
				list.add(user.getLoginName());
				performAgreement.setList(list);
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
				performAgreement.setList(new ArrayList(list));
			}else {
				list.add(user.getLoginName());
				performAgreement.setList(new ArrayList(list));
			}
		}else {//不是管理员查看自己创建的
			performAgreement.setUser(UserUtils.getUser());
		}
		return super.findPage(page, performAgreement);
	}
	
	@Transactional(readOnly = false)
	public void save(PerformAgreement performAgreement) {
		performAgreement.setAgreementPayAmount(StringUtils.isNumber(performAgreement.getAgreementPayAmount()) ==true ? performAgreement.getAgreementPayAmount() : "0");
		performAgreement.setHospitalPayAmount(StringUtils.isNumber(performAgreement.getHospitalPayAmount())==true ? performAgreement.getHospitalPayAmount() :"0");
		performAgreement.setInsurancePayAmount(StringUtils.isNumber(performAgreement.getInsurancePayAmount()) ==true ? performAgreement.getInsurancePayAmount():"0");
		if (StringUtils.isBlank(performAgreement.getCreateBy().getId())){		//判断主键Id是否为空
			performAgreement.preInsert();
			performAgreement.setPerformAgreementId(performAgreement.getId());
			dao.insert(performAgreement);
		}else {
			performAgreement.preUpdate();
			dao.update(performAgreement);
		}
		if ("yes".equals(performAgreement.getComplaintMain().getAct().getFlag())){
			Map<String,Object> var=new HashMap<String, Object>();
			var.put("pass","0");
			User assigness= UserUtils.get(performAgreement.getNextLinkMan());
			var.put("summary_user",assigness.getLoginName());
			// 执行流程
			actTaskService.complete(performAgreement.getComplaintMain().getAct().getTaskId(), performAgreement.getComplaintMain().getAct().getProcInsId(), performAgreement.getComplaintMain().getAct().getComment(), performAgreement.getComplaintMain().getCaseNumber(), var);
		}
//		super.save(performAgreement);
	}
	
	@Transactional(readOnly = false)
	public void delete(PerformAgreement performAgreement) {
		super.delete(performAgreement);
	}

	@Transactional(readOnly = false)
	public void savefj(HttpServletRequest request,PerformAgreement performAgreement){
		String files = request.getParameter("files");
		String files1 = request.getParameter("files1");
		String acceId = null;
		String itemId = performAgreement.getPerformAgreementId();
		String fjtype1 = request.getParameter("fjtype1");
		String fjtype2 = request.getParameter("fjtype2");
		if(StringUtils.isNotBlank(files)){
			String acceId1 =request.getParameter("acceId1");
			if(StringUtils.isNotBlank(acceId1)){
				preOperativeConsentService.updatefj(files,itemId,fjtype1);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files,fjtype1);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype1);
		}

		if(StringUtils.isNotBlank(files1)){
			String acceId2=request.getParameter("acceId2");
			if(StringUtils.isNotBlank(acceId2)){
				preOperativeConsentService.updatefj(files1,itemId,fjtype2);
			}else{
				acceId = IdGen.uuid();
				preOperativeConsentService.save1(acceId,itemId,files1,fjtype2);
			}
		}else{
			preOperativeConsentService.delefj(itemId,fjtype2);
		}
	}
}