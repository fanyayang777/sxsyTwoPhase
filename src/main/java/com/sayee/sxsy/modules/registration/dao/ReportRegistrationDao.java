/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.registration.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.registration.entity.ReportRegistration;
import org.apache.ibatis.annotations.Param;

/**
 * 报案登记DAO接口
 * @author lyt
 * @version 2019-06-05
 */
@MyBatisDao
public interface ReportRegistrationDao extends CrudDao<ReportRegistration> {

    public void insertzf( @Param("acceId1") String acceId1,@Param("itemId1") String itemId1,@Param("files1") String files1,@Param("fjtype") String fjtype);

    public void updateLinkMan(@Param("nextLinkMan") String nextLinkMan,@Param("reportRegistrationId")  String reportRegistrationId);
//    public void findFj(ReportRegistration reportRegistration);
}