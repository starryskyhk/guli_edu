package com.starrysky.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.starrysky.msmservice.service.MsmService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 韩坤
 * @create 2020-06-02-2:50
 * @Description:
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(String PhoneNumbers, String templateCode, Map<String,Object> param) {

        if(StringUtils.isEmpty(PhoneNumbers)) return false;

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4G7iZUzRmyK9vFy5Xo3m", "GEZAlWF2rfuyVHMGdIqsz27qqkMQAv");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);

        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        //设置发送手机号
        request.putQueryParameter("PhoneNumbers", PhoneNumbers);
        //设置签名名称
        request.putQueryParameter("SignName", "学业预警系统");
        //设置阿里云模板名称
        request.putQueryParameter("TemplateCode", templateCode);
        //设置验证码
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
