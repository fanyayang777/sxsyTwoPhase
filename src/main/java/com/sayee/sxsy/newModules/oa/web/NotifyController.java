package com.sayee.sxsy.newModules.oa.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.newModules.oa.service.NotifyService;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotifyController {
    @Autowired
    NotifyService service;
    @ResponseBody
    @RequestMapping(value = "ysj/getNotify",method = RequestMethod.POST)
    public ResponsesUtils getNotify(@RequestBody JSONObject jsonObject){
        Integer page = jsonObject.getInteger("page");
        Integer size = jsonObject.getInteger("size");
        ResponsesUtils responsesUtils = service.getNotify(page, size);

        return responsesUtils;

    }
}
