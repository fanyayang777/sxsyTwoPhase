package com.sayee.sxsy.newModules.testtree.dao;

import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.newModules.testtree.entity.TestTree;
import com.sayee.sxsy.newModules.testtree.entity.TestTreeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface TestTreeMapper {
    int countByExample(TestTreeExample example);

    int deleteByExample(TestTreeExample example);

    int deleteByPrimaryKey(String id);

    int insert(TestTree record);

    int insertSelective(TestTree record);

    List<TestTree> selectByExample(TestTreeExample example);

    TestTree selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TestTree record, @Param("example") TestTreeExample example);

    int updateByExample(@Param("record") TestTree record, @Param("example") TestTreeExample example);

    int updateByPrimaryKeySelective(TestTree record);

    int updateByPrimaryKey(TestTree record);
}