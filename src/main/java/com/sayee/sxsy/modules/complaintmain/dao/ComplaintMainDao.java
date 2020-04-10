/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.complaintmain.dao;

import com.sayee.sxsy.common.persistence.CrudDao;
import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.modules.complaintmain.entity.ComplaintMain;
import com.sayee.sxsy.modules.sys.entity.User;
import com.sayee.sxsy.test.entity.TestTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 纠纷调解DAO接口
 * @author lyt
 * @version 2019-06-04
 */
@MyBatisDao
public interface ComplaintMainDao extends CrudDao<ComplaintMain> {
    /**
     * 根据身份证 医院 获取案件是否重复
     */
    public List<ComplaintMain> getRepeat(ComplaintMain complaintMain);
    /**
     * 获取我的待办数目
     */
    public Long findCount(@Param("loginName") String loginName);
    /**
     * 获取我的待办数据
     */
    public List<ComplaintMain> selfList(@Param("loginName") String loginName);
    /*
    * 获取全部医调委人员信息
    * */
    public List<String> rootFindUserId();
    /**
     * 获取某表的主键
     */
    public String getKey(@Param("complaintMainId") String complaintMainId, @Param("key") String key, @Param("table") String table);
    /**
     * 获取我的已办数据
     */
    public List<ComplaintMain> getMyDone(@Param("loginName") String loginName);
    /**
     * 通过实例id 获取主表信息
     */
    public ComplaintMain getMain(@Param("ins") String ins);

    /**
     * 获取案件类型条数
     */
    public List<Map<String, Object>> findTypeInfo(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);
    /**
     * 不同医院级别 不同的投诉数量
     */
    public List<Map<String, Object>> findGrade(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);
    /**
     * 获取每月数据
     */
    List<Map<String, Object>> getEveryMonthData(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);
    /**
     * 获取山西省各地案件数量
     */
    public List<Map<String,Object>> findAreaName(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);

    /**
     * 获取所有已完成案件
     * @param year
     * @param beginMonthDate
     * @return
     */
    public List<ComplaintMain> findCompleted(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);

    /**
     * 获取所有未完成案件
     * @param year
     * @param beginMonthDate
     * @return
     */
    public Long findAllEvent(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);
    /**
     * 获取各个专业的案件数量
     */
    public List<Map<String,Object>> findDepartment(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);
    /**
     * 调解数据统计中的 投诉类型统计
     */
    List<Map<String, Object>> findTypeInfoTj(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);
    /**
     * 调解数据统计中的 各医院登记 数量
     */
    List<Map<String, Object>> findGradeTj(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);
    /**
     * 调解数据统计中的 每月数量统计
     */
    List<Map<String, Object>> getEveryMonthDataTj(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);
    /**
     * 调解数据统计中的 各市区数据统计
     */
    List<Map<String, Object>> findAreaNameTj(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);
    /**
     * 调解数据统计中的 各专业数据统计
     */
    List<Map<String, Object>> findDepartmentTj(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);
    /**
     * 各专业数据统计的专业名称转换
     */
    public TestTree findDepartmentNewName(@Param("newNameId") String newNameId);

    public String findDepartmentName(@Param("newNameId") String newNameId);

    /**
     * 调解数据统计中的 责任度 饼状图
     */
    List<Map<String, Object>> findDutyTj(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);
    /**
     * 调解数据统计中的 赔偿金额比例 饼状图
     */
    Map<String, Object> findAmountRatioTj(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);
    /*
    根据城市获取各个城市的总赔付额
     */
    List<Map<String, String>> findCityAmountRatio(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);

    /*
    根据科室获取各个科室的总赔付额
     */
    List<Map<String, String>> findDepartmentAmountRatio(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);
    List<String> fiveYearAmountRatio(@Param("year") String year, @Param("beginMonthDate") String beginMonthDate, @Param("endMonthDate") String endMonthDate, @Param("officeId") String officeId);

    List getMachine(@Param("reportingTime") String reportingTime, @Param("endReportingTime") String endReportingTime, @Param("areaId") String areaId,@Param("hospitalId") String hospitalId);
}