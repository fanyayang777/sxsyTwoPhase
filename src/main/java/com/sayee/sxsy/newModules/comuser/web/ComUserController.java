package com.sayee.sxsy.newModules.comuser.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.newModules.comuser.entity.SysComuser;
import com.sayee.sxsy.newModules.comuser.service.SysComuserService;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ComUserController {
    @Autowired
    SysComuserService service;
    @ResponseBody
    @RequestMapping(value = "ysj/updateComUser")
    public ResponsesUtils updateComUser(@RequestBody JSONObject jsonObject){
        SysComuser sysComuser = jsonObject.toJavaObject(SysComuser.class);
        return  service.savaUpdateComUser(sysComuser);
    }
}
