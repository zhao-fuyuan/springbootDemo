package com.example.demo.model.bo;

import lombok.Data;

@Data
public class ThirdPartUserInfoBO {
    private String openId;

    private String unionId;

    private String sessionKey;

    private String avatar;

    private String nickname;
}
