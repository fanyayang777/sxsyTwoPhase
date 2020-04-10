package com.sayee.sxsy.newModules.utils;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.sayee.sxsy.common.config.Global;
import com.sayee.sxsy.common.utils.EhCacheUtils;
import net.sf.ehcache.Cache;

import java.util.HashMap;
import java.util.Map;


/**
 * 发送验证码
 *
 * @author liuxuanlin
 */
public class SendCodeUtils {

    public ResponsesUtils SendCode(String tel, Integer random) throws Exception {

        Map<Object, Object> map = new HashMap<>();
        String sccessKeyId = Global.getSccessKeyId();
        String secert = Global.getSecert();
        String singName = Global.getSingName();
        String code = "{\"code\":" + random + "}";
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", sccessKeyId, secert);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest requests = new CommonRequest();
        requests.setSysMethod(MethodType.POST);
        requests.setSysDomain("dysmsapi.aliyuncs.com");
        requests.setSysVersion("2017-05-25");
        requests.setSysAction("SendSms");
        requests.putQueryParameter("RegionId", "cn-hangzhou");
        requests.putQueryParameter("PhoneNumbers", tel);

        requests.putQueryParameter("SignName", "医视界");

        requests.putQueryParameter("TemplateCode", "SMS_186555012");
        requests.putQueryParameter("TemplateParam", code);


        try {
            CommonResponse responses = client.getCommonResponse(requests);
            map.put("codedata", responses.getData());

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        System.out.println(tel);
        System.out.println(random);
        EhCacheUtils.remove(tel);

        EhCacheUtils.getCacheManager().getCache("YSJ_CODE");
        EhCacheUtils.put("YSJ_CODE", tel, random);
        Object o = EhCacheUtils.get("YSJ_CODE",tel);
        System.out.println(o.toString());

        return ResponsesUtils.ok(map);
    }
}
