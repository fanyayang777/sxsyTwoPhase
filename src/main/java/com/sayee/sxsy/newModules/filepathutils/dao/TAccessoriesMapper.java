package com.sayee.sxsy.newModules.filepathutils.dao;

import com.sayee.sxsy.common.persistence.annotation.MyBatisDao;
import com.sayee.sxsy.newModules.filepathutils.entity.TAccessories;
import com.sayee.sxsy.newModules.filepathutils.entity.TAccessoriesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface TAccessoriesMapper {
    int countByExample(TAccessoriesExample example);

    int deleteByExample(TAccessoriesExample example);

    int deleteByPrimaryKey(String acceId);

    int insert(TAccessories record);

    int insertSelective(TAccessories record);

    List<TAccessories> selectByExampleWithBLOBs(TAccessoriesExample example);

    List<TAccessories> selectByExample(TAccessoriesExample example);

    TAccessories selectByPrimaryKey(String acceId);

    int updateByExampleSelective(@Param("record") TAccessories record, @Param("example") TAccessoriesExample example);

    int updateByExampleWithBLOBs(@Param("record") TAccessories record, @Param("example") TAccessoriesExample example);

    int updateByExample(@Param("record") TAccessories record, @Param("example") TAccessoriesExample example);

    int updateByPrimaryKeySelective(TAccessories record);

    int updateByPrimaryKeyWithBLOBs(TAccessories record);

    int updateByPrimaryKey(TAccessories record);
}