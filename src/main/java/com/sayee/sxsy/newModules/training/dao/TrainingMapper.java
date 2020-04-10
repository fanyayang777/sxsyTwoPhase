package com.sayee.sxsy.newModules.training.dao;

import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.newModules.training.entity.Training;
import com.sayee.sxsy.newModules.training.entity.TrainingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface TrainingMapper {
    int countByExample(TrainingExample example);

    int deleteByExample(TrainingExample example);

    int deleteByPrimaryKey(String trainingId);

    int insert(Training record);

    int insertSelective(Training record);

    List<Training> selectByExample(TrainingExample example);

    Training selectByPrimaryKey(String trainingId);

    int updateByExampleSelective(@Param("record") Training record, @Param("example") TrainingExample example);

    int updateByExample(@Param("record") Training record, @Param("example") TrainingExample example);

    int updateByPrimaryKeySelective(Training record);

    int updateByPrimaryKey(Training record);
}