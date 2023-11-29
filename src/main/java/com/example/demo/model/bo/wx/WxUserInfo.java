package com.example.demo.model.bo.wx;

import lombok.Data;

@Data
public class WxUserInfo {
    private String openid;

    private String unionid;

    private String session_key;

    private String headimgurl;

    private String nickname;

    private Integer errcode;

    private String errmsg;
}
