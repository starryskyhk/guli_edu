package com.starrysky.msmservice.service;

import java.util.Map;

/**
 * @author 韩坤
 * @create 2020-06-02-2:50
 * @Description:
 */
public interface MsmService {
    boolean send(String PhoneNumbers, String templateCode, Map<String,Object> param);
}
