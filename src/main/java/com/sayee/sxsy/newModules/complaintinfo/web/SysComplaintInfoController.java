package com.sayee.sxsy.newModules.complaintinfo.web;

import com.alibaba.fastjson.JSONObject;

import com.sayee.sxsy.newModules.complaintinfo.entity.ComplaintInfoWithBLOBs;

import com.sayee.sxsy.newModules.complaintinfo.service.SysComplaintInfoService;
import com.sayee.sxsy.newModules.comuser.entity.SysComuser;
import com.sayee.sxsy.newModules.comuser.service.SysComuserService;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class SysComplaintInfoController {
    @Autowired
    SysComplaintInfoService service;
    @Autowired
    SysComuserService sysComuserService;

    @ResponseBody
    @RequestMapping(value = "ysj/complaint", method = RequestMethod.POST)
    public ResponsesUtils upDateComUser(@RequestBody JSONObject jsonObject) {

        return service.save(jsonObject);

    }

    @RequestMapping(value = "ysj/test")
    public ResponsesUtils test( ) {
        service.test123();
        return null;

    }
}
