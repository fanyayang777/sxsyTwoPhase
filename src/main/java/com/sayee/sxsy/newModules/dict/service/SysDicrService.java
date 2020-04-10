package com.sayee.sxsy.newModules.dict.service;

import com.sayee.sxsy.newModules.dict.dao.SysDictMapper;
import com.sayee.sxsy.newModules.dict.entity.SysDict;
import com.sayee.sxsy.newModules.dict.entity.SysDictExample;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDicrService {
    @Autowired
    SysDictMapper mapper;
    public ResponsesUtils fandByType(SysDict sysDict){
        SysDictExample example = new SysDictExample();
        example.createCriteria().andTypeEqualTo(sysDict.getType());
        List<SysDict> sysDicts = mapper.selectByExample(example);
        if (sysDicts.size()==0){
           return ResponsesUtils.build(500,"未找到数据");
        }
        return ResponsesUtils.ok(sysDicts);
    }
}
