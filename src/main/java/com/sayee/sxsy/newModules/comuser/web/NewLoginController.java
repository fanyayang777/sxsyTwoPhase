package com.sayee.sxsy.newModules.comuser.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.common.utils.CacheUtils;
import com.sayee.sxsy.common.utils.EhCacheUtils;
import com.sayee.sxsy.modules.sys.service.SystemService;
import com.sayee.sxsy.newModules.comuser.entity.SysComuser;
import com.sayee.sxsy.newModules.comuser.service.SysComuserService;
import com.sayee.sxsy.newModules.utils.RandomUtils;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import com.sayee.sxsy.newModules.utils.SendCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class NewLoginController {
    @Autowired
    SysComuserService sysComuserService;

    @ResponseBody
    @RequestMapping(value = "ysj/login")
    public ResponsesUtils Login(@RequestBody JSONObject object) {

        String username = object.getString("username");
        String password = object.getString("password");
        String code = object.getString("code");



        String random = CacheUtils.get("YSJ_CODE",username).toString();

        if (!code.equals(random)) {
            return ResponsesUtils.build(500, "验证码输入错误");
        }
        SysComuser sysComuser = sysComuserService.findByLoginName(username);

        if (sysComuser == null) {
            return ResponsesUtils.build(500, "未注册的用户");
        } else {
            EhCacheUtils.remove(username);

            if (SystemService.validatePassword(password, sysComuser.getPassword())) {
                CacheUtils.remove("YSJ_CODE",username);
                return ResponsesUtils.ok(sysComuser);
            }
            return ResponsesUtils.build(500, "用户名或密码错误");
        }


    }



}
