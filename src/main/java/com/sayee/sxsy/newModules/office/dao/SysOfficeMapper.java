package com.sayee.sxsy.newModules.office.dao;

import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.newModules.office.entity.SysOffice;
import com.sayee.sxsy.newModules.office.entity.SysOfficeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface SysOfficeMapper {
    int countByExample(SysOfficeExample example);

    int deleteByExample(SysOfficeExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysOffice record);

    int insertSelective(SysOffice record);

    List<SysOffice> selectByExample(SysOfficeExample example);

    SysOffice selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysOffice record, @Param("example") SysOfficeExample example);

    int updateByExample(@Param("record") SysOffice record, @Param("example") SysOfficeExample example);

    int updateByPrimaryKeySelective(SysOffice record);

    int updateByPrimaryKey(SysOffice record);
}