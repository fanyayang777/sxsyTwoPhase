/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.roster.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.roster.entity.Roster;

import java.util.List;

/**
 * 花名册DAO接口
 * @author yang
 * @version 2020-03-30
 */
@MyBatisDao
public interface RosterDao extends CrudDao<Roster> {
    /**
     * 执业资格证号验重
     * @param practiceNumber
     * @return
     */
    public Roster checkpracticeNumber(String practiceNumber);

    /**
     * 导出台账
     */
    public List<Roster> findRoster(Roster roster);
}