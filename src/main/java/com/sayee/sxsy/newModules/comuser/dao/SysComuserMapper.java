package com.sayee.sxsy.newModules.comuser.dao;

import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.newModules.comuser.entity.SysComuser;
import com.sayee.sxsy.newModules.comuser.entity.SysComuserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface SysComuserMapper {
    int countByExample(SysComuserExample example);

    int deleteByExample(SysComuserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysComuser record);

    int insertSelective(SysComuser record);

    List<SysComuser> selectByExample(SysComuserExample example);

    SysComuser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysComuser record, @Param("example") SysComuserExample example);

    int updateByExample(@Param("record") SysComuser record, @Param("example") SysComuserExample example);

    int updateByPrimaryKeySelective(SysComuser record);

    int updateByPrimaryKey(SysComuser record);
}