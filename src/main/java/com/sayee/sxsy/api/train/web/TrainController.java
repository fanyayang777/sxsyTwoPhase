package com.sayee.sxsy.api.train.web;

import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.R;
import com.sayee.sxsy.api.officeapi.entity.OfficeApi;
import com.sayee.sxsy.api.train.entity.Train;
import com.sayee.sxsy.api.train.service.TrainApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description 机构信息
 */
@Controller
@RequestMapping("${adminPath}/api")
public class TrainController {
    @Autowired
    private TrainApiService trainApiService;
    /*
    *返回list页面
    * */
    @RequestMapping("getVideo")
    @ResponseBody
    public R getVideo(){
        List<Train> train=trainApiService.getVideo("1");
        R r=new R();
        r.put("RtnCode",0);
        r.put("RtnMsg","success");
        r.put("RtnData",train);
        return r;
    }
    /*播放视频 详情页面 展示 介绍 课程路径*/
    @RequestMapping("videoDetail")
    @ResponseBody
    public R videoDetail(@RequestBody JSONObject jsonObject){
        String id=jsonObject.getString("id");
        if(null!= id){
            Train train=trainApiService.videoDetail(id);
            R r=new R();
            r.put("RtnCode",0);
            r.put("RtnMsg","success");
            r.put("RtnData",train);
            return r;
        }else{
            R r=new R();
            r.put("RtnCode",1);
            r.put("RtnMsg","error");
            r.put("RtnData","请先选择科室!");
            return r;
        }
    }
}
