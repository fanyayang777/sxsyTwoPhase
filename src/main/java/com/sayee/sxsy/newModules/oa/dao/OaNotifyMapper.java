package com.sayee.sxsy.newModules.oa.dao;

import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.newModules.oa.entity.OaNotify;
import com.sayee.sxsy.newModules.oa.entity.OaNotifyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface OaNotifyMapper {
    int countByExample(OaNotifyExample example);

    int deleteByExample(OaNotifyExample example);

    int deleteByPrimaryKey(String id);

    int insert(OaNotify record);

    int insertSelective(OaNotify record);

    List<OaNotify> selectByExampleWithBLOBs(OaNotifyExample example);

    List<OaNotify> selectByExample(OaNotifyExample example);

    OaNotify selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OaNotify record, @Param("example") OaNotifyExample example);

    int updateByExampleWithBLOBs(@Param("record") OaNotify record, @Param("example") OaNotifyExample example);

    int updateByExample(@Param("record") OaNotify record, @Param("example") OaNotifyExample example);

    int updateByPrimaryKeySelective(OaNotify record);

    int updateByPrimaryKeyWithBLOBs(OaNotify record);

    int updateByPrimaryKey(OaNotify record);
}