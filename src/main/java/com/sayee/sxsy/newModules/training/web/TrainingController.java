package com.sayee.sxsy.newModules.training.web;

import com.alibaba.fastjson.JSONObject;

import com.sayee.sxsy.newModules.training.entity.Train;
import com.sayee.sxsy.newModules.training.service.TrainingService;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TrainingController {
    @Autowired
    TrainingService service;

    @ResponseBody
    @RequestMapping(value = "ysj/fandByVidioType",method = RequestMethod.POST)
    public ResponsesUtils fandByVidioType(@RequestBody JSONObject jsonObject){

       return service.fandByVidioType(jsonObject.toJavaObject(Train.class));
    }
}
