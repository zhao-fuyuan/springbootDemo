package com.example.demo.model.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Table(name = "app")
public class AppPO {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * app名称
     */
    private String name;
    /**
     * 业务服务初始化path
     */
    private String initPath;
    /**
     * 登录path
     */
    private String loginPath;

    /**
     * 业务服务初始化url
     */
    @Column(name = "init_url")
    private String initUrl;

    @Column(name = "login_url")
    private String loginUrl;

    @Column(name = "sms_provider")
    private String smsProvider;

    /**
     * 梦网用户id
     */
    @Column(name = "montnets_user_id")
    private String montnetsUserId;

    /**
     * 梦网用户密码
     */
    @Column(name = "montnets_pwd")
    private String montnetsPwd;

    /**
     * 梦网发送url
     */
    @Column(name = "montnets_send_url")
    private String montnetsSendUrl;

    /**
     * 微信appid
     */
    @Column(name = "wx_app_id")
    private String wxAppId;

    /**
     * 微信appsecret
     */
    @Column(name = "wx_app_secret")
    private String wxAppSecret;

    @Column(name = "wx_mini_app_id")
    private String wxMiniAppId;

    @Column(name = "wx_mini_app_secret")
    private String wxMiniAppSecret;

    @Column(name = "wx_js_app_id")
    private String wxJsAppId;

    @Column(name = "wx_js_app_secret")
    private String wxJsAppSecret;

    @Column(name = "qq_app_id")
    private String qqAppId;

    @Column(name = "qq_app_key")
    private String qqAppKey;

    @Column(name = "access_key")
    private String accessKey;

    @Column(name = "refresh_key")
    private String refreshKey;

    @Column(name = "register_key")
    private String registerKey;

    @Column(name = "mobile_required")
    private Integer mobileRequired;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
