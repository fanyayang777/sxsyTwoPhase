package com.sayee.sxsy.newModules.complaintinfo.dao;

import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.newModules.complaintinfo.entity.ComplaintInfo;
import com.sayee.sxsy.newModules.complaintinfo.entity.ComplaintInfoExample;
import com.sayee.sxsy.newModules.complaintinfo.entity.ComplaintInfoWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface ComplaintInfoMapper {
    int countByExample(ComplaintInfoExample example);

    int deleteByExample(ComplaintInfoExample example);

    int deleteByPrimaryKey(String complaintId);

    int insert(ComplaintInfoWithBLOBs record);

    int insertSelective(ComplaintInfoWithBLOBs record);

    List<ComplaintInfoWithBLOBs> selectByExampleWithBLOBs(ComplaintInfoExample example);

    List<ComplaintInfo> selectByExample(ComplaintInfoExample example);

    ComplaintInfoWithBLOBs selectByPrimaryKey(String complaintId);

    int updateByExampleSelective(@Param("record") ComplaintInfoWithBLOBs record, @Param("example") ComplaintInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ComplaintInfoWithBLOBs record, @Param("example") ComplaintInfoExample example);

    int updateByExample(@Param("record") ComplaintInfo record, @Param("example") ComplaintInfoExample example);

    int updateByPrimaryKeySelective(ComplaintInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ComplaintInfoWithBLOBs record);

    int updateByPrimaryKey(ComplaintInfo record);
}