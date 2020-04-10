package com.sayee.sxsy.newModules.middleutils.dao;

import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.newModules.middleutils.entity.ComuserComplaintinfoidExample;
import com.sayee.sxsy.newModules.middleutils.entity.ComuserComplaintinfoidKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface ComuserComplaintinfoidMapper {
    int countByExample(ComuserComplaintinfoidExample example);

    int deleteByExample(ComuserComplaintinfoidExample example);

    int deleteByPrimaryKey(ComuserComplaintinfoidKey key);

    int insert(ComuserComplaintinfoidKey record);

    int insertSelective(ComuserComplaintinfoidKey record);

    List<ComuserComplaintinfoidKey> selectByExample(ComuserComplaintinfoidExample example);

    int updateByExampleSelective(@Param("record") ComuserComplaintinfoidKey record, @Param("example") ComuserComplaintinfoidExample example);

    int updateByExample(@Param("record") ComuserComplaintinfoidKey record, @Param("example") ComuserComplaintinfoidExample example);
}