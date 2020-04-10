package com.sayee.sxsy.newModules.office.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.sayee.sxsy.newModules.office.entity.SysOffice;
import com.sayee.sxsy.newModules.office.service.SysOfficeService;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysOfficeController {
@Autowired
    SysOfficeService service;
@ResponseBody
@RequestMapping(value = "ysj/findByOffice",method = RequestMethod.POST)
public ResponsesUtils fandOffoce(@RequestBody JSONObject object){
    SysOffice sysOffice = object.toJavaObject(SysOffice.class);
    return service.findByOffice(sysOffice);


}
    @ResponseBody
    @RequestMapping(value = "ysj/findByParentId",method = RequestMethod.POST)
    public ResponsesUtils findByParentId(@RequestBody JSONObject object){
        String id = object.getString("id");
        return service.findByParentId(id);
    }
    @ResponseBody
    @RequestMapping(value = "ysj/findHospitalByCity")
    public ResponsesUtils findHospitalByCity(@RequestBody JSONObject jsonObject){

        SysOffice sysOffice = jsonObject.toJavaObject(SysOffice.class);

        return ResponsesUtils.ok(service.findHospitalByCity(sysOffice));



    }

}
