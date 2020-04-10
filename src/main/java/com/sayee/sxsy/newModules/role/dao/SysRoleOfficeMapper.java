package com.sayee.sxsy.newModules.role.dao;

import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.newModules.role.entity.SysRoleOfficeExample;
import com.sayee.sxsy.newModules.role.entity.SysRoleOfficeKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface SysRoleOfficeMapper {
    int countByExample(SysRoleOfficeExample example);

    int deleteByExample(SysRoleOfficeExample example);

    int deleteByPrimaryKey(SysRoleOfficeKey key);

    int insert(SysRoleOfficeKey record);

    int insertSelective(SysRoleOfficeKey record);

    List<SysRoleOfficeKey> selectByExample(SysRoleOfficeExample example);

    int updateByExampleSelective(@Param("record") SysRoleOfficeKey record, @Param("example") SysRoleOfficeExample example);

    int updateByExample(@Param("record") SysRoleOfficeKey record, @Param("example") SysRoleOfficeExample example);

    void selectByExample();
}