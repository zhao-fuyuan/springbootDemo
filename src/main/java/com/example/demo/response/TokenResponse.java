package com.example.demo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@ApiModel("token响应对象")
@Getter
@Setter
public class TokenResponse {
    private static final long serialVersionUID = -6646296347317063545L;

    @ApiModelProperty("登录token")
    private String accessToken;

    @ApiModelProperty("刷新token")
    private String refreshToken;

    @ApiModelProperty("注册token")
    private String registerToken;

    @ApiModelProperty("小程序/公众号 openId")
    private String openId;

    @ApiModelProperty("小程序用户是否已获取到unionId 1-有 0-没有")
    private Integer hasUnionId;

    /**
     * 返回的token类型
     * register - 当前未注册，返回registerToken
     * login - 当前已注册，返回accessToken
     */
    @ApiModelProperty("token类型 0-注册 1-登录")
    private Integer type;

    @ApiModelProperty("额外信息")
    private Object extraInfo;

    private Long userId;

    /**
     * 1-手机验证码注册，2-微信号码注册
     */
    @ApiModelProperty("1-手机验证码注册，2-微信号码注册")
    private Integer miniAppRegWay;

    @ApiModelProperty("当前操作 1-注册 2-登录")
    private Integer currentOperation;
}
