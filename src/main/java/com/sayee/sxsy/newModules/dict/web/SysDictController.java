package com.sayee.sxsy.newModules.dict.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.newModules.dict.entity.SysDict;
import com.sayee.sxsy.newModules.dict.service.SysDicrService;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysDictController {
    @Autowired
    SysDicrService service;
    @ResponseBody
    @RequestMapping(value = "ysj/fandByDictType",method = RequestMethod.POST)
    public ResponsesUtils fandByType(@RequestBody JSONObject jsonObject){
        SysDict sysDict = jsonObject.toJavaObject(SysDict.class);
        return service.fandByType(sysDict);
    }


}
