package com.example.demo.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import com.alibaba.druid.wall.violation.ErrorCode;


public class RequestUtils {
    // 获取AccessToken
    public static JSONObject getAccessToken(String appId, String appSecret) {
        String apiUrl = StrUtil.format(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={}&secret={}",
                appId, appSecret
        );
        String body = HttpRequest.get(apiUrl).execute().body();
//        ThrowUtils.throwIf(body == null, ErrorCode.OPERATION_ERROR);
        return new JSONObject(body);
    }

    // 获取session_key和openid
    public static String getOpenIdByCode(String appId, String secret, String code) {
        String apiUrl = StrUtil.format(
                "https://api.weixin.qq.com/sns/jscode2session?appid={}&secret={}&js_code={}&grant_type=authorization_code",
                appId, secret, code
        );
        String body = HttpRequest.get(apiUrl).execute().body();
//        ThrowUtils.throwIf(body == null, ErrorCode.OPERATION_ERROR);
        return body;
    }
}
