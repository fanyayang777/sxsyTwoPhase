package com.sayee.sxsy.api.mediate.dao;

import com.sayee.sxsy.api.mediate.entity.Summary;
import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;

/**
 * @Description
 */
@MyBatisDao
public interface SummaryDao extends CrudDao<Summary> {
}
