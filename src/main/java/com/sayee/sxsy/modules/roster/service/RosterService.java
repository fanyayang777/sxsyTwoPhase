/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.roster.service;

import java.util.List;

import com.sayee.sxsy.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sayee.sxsy.common.persistence.Page;
import com.sayee.sxsy.common.service.CrudService;
import com.sayee.sxsy.modules.roster.entity.Roster;
import com.sayee.sxsy.modules.roster.dao.RosterDao;

/**
 * 花名册Service
 * @author yang
 * @version 2020-03-30
 */
@Service
@Transactional(readOnly = true)
public class RosterService extends CrudService<RosterDao, Roster> {
	@Autowired
	private RosterDao RosterDao;

	public Roster get(String id) {
		return super.get(id);
	}
	
	public List<Roster> findList(Roster roster) {
		return super.findList(roster);
	}
	
	public Page<Roster> findPage(Page<Roster> page, Roster roster) {
		return super.findPage(page, roster);
	}
	
	@Transactional(readOnly = false)
	public void save(Roster roster) {
		if (StringUtils.isBlank(roster.getRosterId())){
			roster.preInsert();
			roster.setRosterId(roster.getId());
			dao.insert(roster);
		}else {
			roster.preUpdate();
			dao.update(roster);
		}
		super.save(roster);
	}
	
	@Transactional(readOnly = false)
	public void delete(Roster roster) {
		super.delete(roster);
	}

	/*
	 *执业资格证号验重
	 *
	 * */
	@Transactional(readOnly = false)
	public boolean checkFileNumber(String practiceNumber) {
		if (StringUtils.isNotBlank(practiceNumber)) {
			Roster roster = dao.checkpracticeNumber(practiceNumber);
			if (roster != null) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public Page<Roster> getRoster(Page<Roster> page,Roster roster){
//        machineAccount.getSqlMap().get("",);
		roster.setPage(page);
		page.setList(RosterDao.findRoster(roster));
		return page;
	}
}