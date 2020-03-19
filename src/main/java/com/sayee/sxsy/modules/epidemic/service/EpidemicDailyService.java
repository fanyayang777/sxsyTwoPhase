/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.epidemic.service;

import java.util.*;

import com.google.common.collect.Lists;
import com.sayee.sxsy.common.utils.DateUtils;
import com.sayee.sxsy.common.utils.ObjectUtils;
import com.sayee.sxsy.modules.epidemic.entity.Statis;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.entity.Role;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.modules.sys.service.SystemService;
import com.sayee.sxsy.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.epidemic.entity.EpidemicDaily;
import com.sayee.sxsy.modules.epidemic.dao.EpidemicDailyDao;
import com.sayee.sxsy.modules.epidemic.entity.EgressInfo;
import com.sayee.sxsy.modules.epidemic.dao.EgressInfoDao;
import sun.plugin2.util.SystemUtil;

/**
 * 疫情日报Service
 * @author zhangfan
 * @version 2020-02-11
 */
@Service
@Transactional(readOnly = true)
public class EpidemicDailyService extends CrudService<EpidemicDailyDao, EpidemicDaily> {

	@Autowired
	private EgressInfoDao egressInfoDao;

	@Autowired
	private SystemService systemService;
	public EpidemicDaily get(String id) {
		EpidemicDaily epidemicDaily = super.get(id);
		EgressInfo egressInfo=new EgressInfo();
		egressInfo.setEpidemicId(epidemicDaily.getEpidemicId());
		epidemicDaily.setEgressInfoList(egressInfoDao.findList(egressInfo));
		return epidemicDaily;
	}
	
	public List<EpidemicDaily> findList(EpidemicDaily epidemicDaily) {
		return super.findList(epidemicDaily);
	}
	
	public Page<EpidemicDaily> findPage(Page<EpidemicDaily> page, EpidemicDaily epidemicDaily) {
		List<Role> roleList= UserUtils.getRoleList();//获取当前登陆人角色
		List<String> aa= ObjectUtils.convert(roleList.toArray(),"enname",true);
		User user=UserUtils.getUser();
		if (user.isAdmin() || aa.contains("quanshengtiaojiebuzhuren") || aa.contains("yitiaoweizhuren")
				|| aa.contains("yitiaoweifuzhuren")|| aa.contains("shengzhitiaojiebuzhuren/fuzhuren")|| aa.contains("yitiaoweizhuren")
		){	//按照部门进行过滤
			if(StringUtils.isNotBlank(epidemicDaily.getOfficeId())){
				List<String> list=new ArrayList<String>();
				List<User> listUser=UserUtils.getUserByOffice(epidemicDaily.getOfficeId());
				for (User people:listUser) {
					list.add(people.getId());
				}
				if (list.size()>0){
					epidemicDaily.setList(list);
				}else {
					list.add(user.getId());
					epidemicDaily.setList(list);
				}
			}
		}else if((  aa.contains("gongzuozhanzhuren/fuzhuren")) ){
			//工作站 主任 副主任 看自己 的员工
			List<String> list=new ArrayList<String>();
			List<User> listUser=UserUtils.getUserByOffice(user.getOffice().getId());
			for (User people:listUser) {
				list.add(people.getId());
			}
			if (list.size()>0){
				epidemicDaily.setList(list);
			}else {
				list.add(user.getId());
				epidemicDaily.setList(list);
			}
		}else if(aa.contains("szcz") || aa.contains("szjc") || aa.contains("szjz") || aa.contains("szgj") ||aa.contains("szyq") ||aa.contains("szsz") ||aa.contains("szxc") || aa.contains("szdt") || aa.contains("szll") ||aa.contains("szxy") || aa.contains("szyc") ||aa.contains("szlf") ||aa.contains("szybzg") ||aa.contains("szebzg")){
			List<Office> officeList = Lists.newArrayList();// 按明细设置数据范围s
			for (Role role:roleList) {
				for (Office office:role.getOfficeList()) {
					if(StringUtils.isNotBlank(epidemicDaily.getOfficeId()) ){
						if ( office.getId().equals(epidemicDaily.getOfficeId())){
							officeList.add(UserUtils.getOfficeId(epidemicDaily.getOfficeId()));//将获得的 明细 添加到list;
						}
					}else {
						officeList.add(UserUtils.getOfficeId(office.getId()));//将获得的 明细 添加到list;
					}
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
			if(StringUtils.isBlank(epidemicDaily.getOfficeId())){
				list.add(UserUtils.getUser().getId());
			}
			if (list.size()>0){
				epidemicDaily.setList(new ArrayList(list));
			}else {
				if(StringUtils.isBlank(epidemicDaily.getOfficeId())){
					list.add(user.getId());
				}else {
					list.add("");
				}
				epidemicDaily.setList(new ArrayList(list));
			}
		}else {//不是管理员查看自己创建的
			epidemicDaily.setUser(user);
		}
		Page<EpidemicDaily> newPage=super.findPage(page, epidemicDaily);
		List<EpidemicDaily> list=newPage.getList();
		List<EpidemicDaily> remove=new ArrayList<>();
		for (EpidemicDaily ep:list) {
			if (!user.getId().equals(ep.getCreateBy().getId())){//创建人不是  过滤别人完成的
				if (StringUtils.isBlank(ep.getWorkRemark())){
					remove.add(ep);
				}
			}
		}
		list.removeAll(remove);
		newPage.setList(list);
		return newPage;
	}
	
	@Transactional(readOnly = false)
	public void save(EpidemicDaily epidemicDaily) {
		for (EgressInfo egressInfo : epidemicDaily.getEgressInfoList()){
			if("1".equals(egressInfo.getRelation())){
				epidemicDaily.setTemperature(egressInfo.getTemperature());
				epidemicDaily.setUserName(egressInfo.getEgressName());
				epidemicDaily.setHealthCondition(egressInfo.getHealthCondition());
				epidemicDaily.setIsEgress(egressInfo.getIsEgress());
			}
		}

		if (StringUtils.isBlank(epidemicDaily.getEpidemicId())){
			epidemicDaily.preInsert();
			epidemicDaily.setEpidemicId(epidemicDaily.getId());
			dao.insert(epidemicDaily);
		}else {
			epidemicDaily.preUpdate();
			dao.update(epidemicDaily);
		}
		//super.save(epidemicDaily);
		for (EgressInfo egressInfo : epidemicDaily.getEgressInfoList()){
			if (egressInfo.getEgressId() == null){
				continue;
			}
			if (EgressInfo.DEL_FLAG_NORMAL.equals(egressInfo.getDelFlag())){
				egressInfo.setEgressAge(StringUtils.isNumber(egressInfo.getEgressAge()) ? egressInfo.getEgressAge() : "0");
				if (StringUtils.isBlank(egressInfo.getEgressId())){
					egressInfo.setEpidemicId(epidemicDaily.getEpidemicId());
					egressInfo.preInsert();
					egressInfo.setEgressId(egressInfo.getId());
					egressInfoDao.insert(egressInfo);
				}else{
					egressInfo.setEpidemicId(epidemicDaily.getEpidemicId());
					egressInfo.preUpdate();
					egressInfoDao.update(egressInfo);
				}
			}else{
				egressInfoDao.delete(egressInfo);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(EpidemicDaily epidemicDaily) {
		super.delete(epidemicDaily);
		EgressInfo egressInfo=new EgressInfo();
		egressInfo.setEpidemicId(epidemicDaily.getEpidemicId());
		egressInfoDao.delete(egressInfo);
	}

	@Transactional(readOnly = false)
	public EgressInfo getAcquiesce(EgressInfo egressInfo) {
		return egressInfoDao.getAcquiesce(egressInfo);
	}


	public Page<Statis> findStatistics(Page<Statis> page, EpidemicDaily epidemicDaily) {
		Statis statis=new Statis();
		List<Role> roleList= UserUtils.getRoleList();//获取当前登陆人角色
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
				list.add(people.getId());
			}
			if (list.size()>0){
				epidemicDaily.setList(list);
			}else {
				list.add(user.getId());
				epidemicDaily.setList(list);
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
			//添加 自己的id
			list.add(UserUtils.getUser().getId());
			if (list.size()>0){
				epidemicDaily.setList(new ArrayList(list));
			}else {
				list.add(user.getId());
				epidemicDaily.setList(new ArrayList(list));
			}
		}else {//不是 普通调解员 看 统计页面
			User user1=new User();
			user1.setId("");
			epidemicDaily.setUser(user1);
		}
		if (StringUtils.isBlank(epidemicDaily.getBeginFillingDate())){
			epidemicDaily.setBeginFillingDate(DateUtils.getDate());
		}
		List<Statis> list=dao.findStatistics(epidemicDaily);
		for (Statis s:list) {
			List<User> listUser= systemService.findUserByOfficeId(s.getDeptId());
			s.setYingbao(String.valueOf(listUser.size()));
		}
		/*List<EpidemicDaily> remove=new ArrayList<>();
		for (EpidemicDaily ep:list) {
			if (!user.getId().equals(ep.getCreateBy().getId())){//创建人不是  过滤别人完成的
				if (StringUtils.isBlank(ep.getWorkRemark())){
					remove.add(ep);
				}
			}
		}
		list.removeAll(remove);*/
		page.setList(list);
		return page;
	}

}