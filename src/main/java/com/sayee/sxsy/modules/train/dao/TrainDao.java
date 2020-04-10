/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.train.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.train.entity.Train;

/**
 * 培训视频DAO接口
 * @author wjm
 * @version 2020-03-31
 */
@MyBatisDao
public interface TrainDao extends CrudDao<Train> {
	
}