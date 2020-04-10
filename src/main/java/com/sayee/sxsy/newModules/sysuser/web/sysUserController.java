package com.sayee.sxsy.newModules.sysuser.web;


import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.common.utils.CacheUtils;
import com.sayee.sxsy.newModules.office.entity.SysOffice;
import com.sayee.sxsy.newModules.sysuser.entity.SysUser;
import com.sayee.sxsy.newModules.sysuser.service.sysUserService;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class sysUserController {
    @Autowired
    sysUserService service;

    @ResponseBody
    @RequestMapping(value = "ysj/fandUserByOofficeId")
    public ResponsesUtils fandUserByOofficeId(@RequestBody String[] str) {

        List<SysUser> sysUsers = service.fandByOfficeId(str);

        if (sysUsers.size() == 0) {
            return ResponsesUtils.build(200, "暂无数据");
        }
        return ResponsesUtils.ok(sysUsers);

    }
    @ResponseBody
    @RequestMapping(value = "ysj/saveUser")
    public ResponsesUtils saveUser(@RequestBody JSONObject jsonObject){

        String code = jsonObject.getString("code");
        SysUser sysUser = jsonObject.toJavaObject(SysUser.class);

        SysOffice sysOffice = jsonObject.toJavaObject(SysOffice.class);
        Integer integer = service.saveUser(sysUser,sysOffice,code);
        if (integer==500){
            return ResponsesUtils.build(500,"验证码错误");
        }
        if (integer==0) {
            return ResponsesUtils.build(500,"失败");
        }
        CacheUtils.remove("YSJ_CODE",sysUser.getLoginName());
        return ResponsesUtils.ok();
    }
}
