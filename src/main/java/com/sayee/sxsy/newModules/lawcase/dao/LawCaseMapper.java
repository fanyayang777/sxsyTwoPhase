package com.sayee.sxsy.newModules.lawcase.dao;

import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.newModules.lawcase.entity.LawCase;
import com.sayee.sxsy.newModules.lawcase.entity.LawCaseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface LawCaseMapper {
    int countByExample(LawCaseExample example);

    int deleteByExample(LawCaseExample example);

    int deleteByPrimaryKey(String lawCaseId);

    int insert(LawCase record);

    int insertSelective(LawCase record);

    List<LawCase> selectByExampleWithBLOBs(LawCaseExample example);

    List<LawCase> selectByExample(LawCaseExample example);

    LawCase selectByPrimaryKey(String lawCaseId);

    int updateByExampleSelective(@Param("record") LawCase record, @Param("example") LawCaseExample example);

    int updateByExampleWithBLOBs(@Param("record") LawCase record, @Param("example") LawCaseExample example);

    int updateByExample(@Param("record") LawCase record, @Param("example") LawCaseExample example);

    int updateByPrimaryKeySelective(LawCase record);

    int updateByPrimaryKeyWithBLOBs(LawCase record);

    int updateByPrimaryKey(LawCase record);
}