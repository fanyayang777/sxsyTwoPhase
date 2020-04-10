package com.sayee.sxsy.newModules.complaintman.dao;

import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.newModules.complaintman.entity.ComplaintMain;
import com.sayee.sxsy.newModules.complaintman.entity.ComplaintMainExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface ComplaintMainMapper {
    int countByExample(ComplaintMainExample example);

    int deleteByExample(ComplaintMainExample example);

    int deleteByPrimaryKey(String complaintMainId);

    int insert(ComplaintMain record);

    int insertSelective(ComplaintMain record);

    List<ComplaintMain> selectByExample(ComplaintMainExample example);

    ComplaintMain selectByPrimaryKey(String complaintMainId);

    int updateByExampleSelective(@Param("record") ComplaintMain record, @Param("example") ComplaintMainExample example);

    int updateByExample(@Param("record") ComplaintMain record, @Param("example") ComplaintMainExample example);

    int updateByPrimaryKeySelective(ComplaintMain record);

    int updateByPrimaryKey(ComplaintMain record);
}