/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.insuranceslip.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.insuranceslip.entity.InsuranceSlip;

/**
 * 投保单DAO接口
 * @author yang
 * @version 2020-03-29
 */
@MyBatisDao
public interface InsuranceSlipDao extends CrudDao<InsuranceSlip> {
	
}