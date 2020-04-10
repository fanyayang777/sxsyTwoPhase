package com.sayee.sxsy.api.train.dao;

import com.sayee.sxsy.api.train.entity.Train;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * @Description
 */
@MyBatisDao
public interface TrainDao extends CrudDao<Train> {


}
