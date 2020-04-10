package com.sayee.sxsy.newModules.interfaceUtils;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.mediate.entity.Perform;
import com.sayee.sxsy.common.utils.CacheUtils;
import com.sayee.sxsy.common.utils.EhCacheUtils;
import com.sayee.sxsy.newModules.utils.RandomUtils;


import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import com.sayee.sxsy.newModules.utils.SendCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendCodeInterface {
    @RequestMapping(value = "code")
    @ResponseBody
    public ResponsesUtils SendCode(@RequestBody JSONObject obj) {
        ResponsesUtils responsesUtils = new ResponsesUtils();
        if (obj.equals("") && obj == null) {
            return ResponsesUtils.build(500, "参数不正确或为空");
        }
        Integer random = new RandomUtils().Random();
        String tel = obj.getString("tel");
        try {
            responsesUtils = new SendCodeUtils().SendCode(tel, new RandomUtils().Random());
        } catch (Exception e) {
            e.printStackTrace();
        }
        CacheUtils.put(tel,random);
        return responsesUtils;
    }

}
