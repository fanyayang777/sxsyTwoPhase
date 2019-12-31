package com.sayee.sxsy.api.messagetemplete.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sayee.sxsy.api.common.HttpRequest;
import com.sayee.sxsy.api.messagetemplete.entity.SendTemplateMessage;
import com.sayee.sxsy.api.messagetemplete.entity.TemplateData;

import java.util.Map;

/**
 * @Description 消息模版
 */
public class MessageTemplete {
    private String grant_type = "client_credential";
    public  String APPID;
    public  String APPSECRET;
    public MessageTemplete(String appId,String appSecret){
        this.APPID=appId;
        this.APPSECRET=appSecret;
    }
    private String getAccessToken() {
        String params = "grant_type=" + grant_type + "&secret=" + APPSECRET + "&appid=" + APPID;
        String sendGet = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", params);
        JSONObject ob = JSON.parseObject(sendGet);
        //拿到accesstoken
        String accesstoken = (String) ob.get("access_token");
        return accesstoken;
    }

    public static final String SEND_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";

    private JSONObject sendTemplateMessage(String touser, String template_id, String page, String formid, Map<String, TemplateData> map) {
        String accesstoken = getAccessToken();
        SendTemplateMessage sendTemplateMessage = new SendTemplateMessage();
        //拼接数据
        sendTemplateMessage.setTouser(touser);
        sendTemplateMessage.setTemplate_id(template_id);
        sendTemplateMessage.setPage(page);
        sendTemplateMessage.setForm_id(formid);
        sendTemplateMessage.setData(map);
        sendTemplateMessage.setEmphasis_keyword("");
        String json = JSONObject.toJSONString(sendTemplateMessage);
        String ret = HttpRequest.sendPost(SEND_TEMPLATE_MESSAGE + accesstoken, json);
        return JSON.parseObject(ret);
    }
}