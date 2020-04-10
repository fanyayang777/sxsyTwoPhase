package com.sayee.sxsy.newModules.comuser.web;

import com.alibaba.fastjson.JSONObject;

import com.sayee.sxsy.common.utils.CacheUtils;
import com.sayee.sxsy.modules.sys.service.SystemService;
import com.sayee.sxsy.newModules.comuser.entity.SysComuser;

import com.sayee.sxsy.newModules.comuser.service.SysComuserService;
import com.sayee.sxsy.newModules.utils.RandomUtils;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import com.sayee.sxsy.newModules.utils.SendCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Cache;

@Controller
public class RegisterController {
    @Autowired
    SysComuserService service;
    @ResponseBody
    @RequestMapping(value = "ysj/register")
    public ResponsesUtils register(@RequestBody JSONObject jsonObject) {

        SysComuser comuser = jsonObject.toJavaObject(SysComuser.class);
        String code = jsonObject.getString("code");

        System.out.println(comuser.getLoginName());
        String random = CacheUtils.get("YSJ_CODE",comuser.getLoginName()).toString();
        System.out.println(random);


        if (!code.equals(random)) {
            return ResponsesUtils.build(500, "验证码输入错误");
        }
        String hashPassword = SystemService.entryptPassword(comuser.getPassword());
        comuser.setPassword(hashPassword);
        return  service.save(comuser);

    }

}
