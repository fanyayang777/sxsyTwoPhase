package com.sayee.sxsy.newModules.sysarea.web;


import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.newModules.sysarea.entity.SysArea;
import com.sayee.sxsy.newModules.sysarea.service.SysAreaService;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class sysAreaController {

    @Autowired
    SysAreaService service;

    @ResponseBody
    @RequestMapping(value = "ysj/cityList", method = RequestMethod.POST)
    public ResponsesUtils cityList(@RequestBody JSONObject jsonObject) {
        SysArea sysArea = jsonObject.toJavaObject(SysArea.class);

        return ResponsesUtils.ok(service.fandByType(sysArea));

    }
}
