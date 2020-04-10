package com.sayee.sxsy.newModules.testtree.service;


import com.sayee.sxsy.newModules.testtree.dao.TestTreeMapper;
import com.sayee.sxsy.newModules.testtree.entity.TestTree;
import com.sayee.sxsy.newModules.testtree.entity.TestTreeExample;
import jdk.nashorn.internal.runtime.FindProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysTestTreeService {

    @Autowired
    TestTreeMapper mapper;

    public List<TestTree> findMediateTypeByPid(TestTree testTree){

        TestTreeExample example = new TestTreeExample();

        example.createCriteria().andMoldEqualTo(testTree.getMold());

        example.createCriteria().andParentIdEqualTo(testTree.getParentId());

        return mapper.selectByExample(example);
    }
}
