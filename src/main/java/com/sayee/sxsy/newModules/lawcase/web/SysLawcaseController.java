package com.sayee.sxsy.newModules.lawcase.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.common.utils.JsonUtil;
import com.sayee.sxsy.newModules.lawcase.entity.LawCase;

import com.sayee.sxsy.newModules.lawcase.service.SyslawCaseService;
import com.sayee.sxsy.newModules.utils.CaseUtils;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SysLawcaseController {
    @Autowired
    SyslawCaseService service;

    @ResponseBody
    @RequestMapping(value = "ysj/fandLawByType", method = RequestMethod.POST)
    public ResponsesUtils fandByType(@RequestBody JSONObject jsonObject) {
        LawCase lawCase = jsonObject.toJavaObject(LawCase.class);
        return service.fandByType(lawCase);
    }

    @ResponseBody
    @RequestMapping(value = "ysj/fandCaseByType", method = RequestMethod.POST)
    public ResponsesUtils fandCaseByType(@RequestBody JSONObject jsonObject) {
        CaseUtils caseUtils = jsonObject.toJavaObject(CaseUtils.class);



        return service.fandCaseByType(caseUtils);

    }

}
