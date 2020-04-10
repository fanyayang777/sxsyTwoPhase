package com.sayee.sxsy.newModules.sysarea.service;

import com.sayee.sxsy.newModules.sysarea.dao.SysAreaMapper;
import com.sayee.sxsy.newModules.sysarea.entity.SysArea;
import com.sayee.sxsy.newModules.sysarea.entity.SysAreaExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysAreaService {
    @Autowired
    SysAreaMapper mapper;
    public List<SysArea> fandByType(SysArea sysArea){
        SysAreaExample example = new SysAreaExample();

        example.createCriteria().andParentIdEqualTo(sysArea.getParentId());

        example.createCriteria().andTypeEqualTo(sysArea.getType());

        return mapper.selectByExample(example);
    }
}
