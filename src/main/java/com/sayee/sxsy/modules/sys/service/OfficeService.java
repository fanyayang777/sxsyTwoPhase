/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import com.sayee.sxsy.common.utils.StringUtils;
import com.sayee.sxsy.modules.sys.entity.Area;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.service.TreeService;
import com.sayee.sxsy.modules.sys.dao.OfficeDao;
import com.sayee.sxsy.modules.sys.entity.Office;
import com.sayee.sxsy.modules.sys.utils.UserUtils;

/**
 * 机构Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {

	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}

	public List<Office> findList(Boolean isAll,String officeType){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeListType(officeType);
		}
	}

	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
		if(office != null){
			if(StringUtils.isNotBlank(office.getRemarks())){
				office.setParentIds(office.getParentIds()+"%");
				return dao.findByRemarksIdsLike(office);
			}
			Area area=new Area();
			if (office.getArea()!=null){
				area=office.getArea();
			}else {
				area=UserUtils.getUser().getCompany().getArea();
			}
			area.setName("");
			area.setAreaId(StringUtils.isBlank(area.getAreaId())?area.getId() : area.getAreaId());
			office.setArea(area);
			office.setParentIds(office.getParentIds()+"%");
			if("2".equals(office.getArea().getId())){
				return dao.rootFindByParentIdsLike(office);
			}
			return dao.findByParentIdsLike(office);

		}
		return  new ArrayList<Office>();
	}

	@Transactional(readOnly = false)
	public void save(Office office) {
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}

	@Transactional(readOnly = false)
	public void delete(Office office) {
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}

}
