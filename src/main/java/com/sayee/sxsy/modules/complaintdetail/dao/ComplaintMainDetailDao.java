/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintdetail.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.complaint.entity.ComplaintInfo;
import com.sayee.sxsy.modules.complaintdetail.entity.ComplaintMainDetail;
import org.apache.ibatis.annotations.Param;

/**
 * 医调委投诉接待DAO接口
 * @author zhangfan
 * @version 2019-06-05
 */
@MyBatisDao
public interface ComplaintMainDetailDao extends CrudDao<ComplaintMainDetail> {

    public void saveShift(ComplaintMainDetail complaintMainDetail);

    ComplaintInfo getDe(@Param("complaintMainId") String complaintMainId);
}