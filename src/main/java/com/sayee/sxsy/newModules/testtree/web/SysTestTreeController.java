package com.sayee.sxsy.newModules.testtree.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.newModules.testtree.entity.TestTree;
import com.sayee.sxsy.newModules.testtree.service.SysTestTreeService;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 调解类别
 */
@Controller
public class SysTestTreeController {

    @Autowired
    SysTestTreeService service;
@ResponseBody
@RequestMapping(value = "ysj/findMediateTypeByPid",method = RequestMethod.POST)
    public ResponsesUtils findMediateTypeByPid(@RequestBody JSONObject jsonObject){
        TestTree testTree = jsonObject.toJavaObject(TestTree.class);

       return ResponsesUtils.ok(service.findMediateTypeByPid(testTree));
    }
}
