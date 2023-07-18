package com.example.demo.api.common;

import com.example.demo.api.common.impl.IResponseError;
import lombok.Getter;

@Getter
public enum ResponseError implements IResponseError {

    // 100000 系统错误
    SYSTEM_ERROR("100000", "系统错误", ""),
    RATELIMIT_ERROR("-9", "限流拦截", ""),

    // 200000 通用错误
    SIGNATURE_EMPTY_ERROR("200000", "签名为空", ""),
    SIGNATURE_INVALID_ERROR("200001", "签名错误", ""),
    SIGNATURE_TIMEOUT_ERROR("200002", "签名过期", ""),

    ACCESS_TOKEN_EMPTY_ERROR("200100", "accessToken为空", ""),
    ACCESS_TOKEN_EXPIRED_ERROR("200101", "accessToken过期，请刷新", ""),
    REFRESH_TOKEN_EXPIRED_ERROR("200102", "refreshToken过期，请重新登录", ""),
    REGISTER_TOKEN_EXPIRED_ERROR("200103", "registerToken已过期", ""),
    REGISTER_TOKEN_NULL_ERROR("200104", "registerToken为空", ""),

    SEND_CODE_TYPE_ERROR("200200", "验证码类型错误", ""),
    SEND_CODE_FAIL_ERROR("200201", "验证码发送失败", ""),
    SMS_CODE_VERIFY_ERROR("200203", "验证码错误", "验证码错误"),
    MESSAGE_SEND_ERROR("200204", "短信发送错误", "短信发送错误"),
    PARAM_ERROR("200205", "参数不在可选", "参数错误"),
    PARAMETER_FORMAT_ERROR("200206", "参数格式不正确", ""),

    MOBILE_FORMAT_ERROR("200300", "手机号格式错误", "手机号格式错误"),
    MOBILE_STATUS_CHECK_ERROR("200301", "手机号状态异常，解封失败", "手机号状态异常，解封失败"),
    MOBILE_SAME_ERROR("200302", "不能和原手机号相同", "不能和原手机号相同"),
    MOBILE_CHECK_COUNT_ERROR("200303","号码检测次数今天达上限了", "号码检测次数今天达上限了"),
    MOBIEL_CHANGE_STATUS_ERROR("200304", "您的手机号状态异常，更换手机号失败", "您的手机号状态异常，更换手机号失败"),
    AUTHMOBILE_FIT_ERROR("200305", "授权号码与填写号码不一致", "号码验证失败"),

    DATA_UPDATE_ERROR("200400", "数据更新失败", ""),
    DATA_INSERT_ERROR("200401", "数据插入失败", ""),
    DATA_DELETE_ERROR("200402", "数据删除失败", ""),
    LOCK_GET_FAIL_ERROR("200403", "锁获取失败", ""),
    UPDATE_LOGIN_EMPTY_ERROR("200404", "更新列表为空", ""),
    DATE_EXIST_ERROR("200405", "数据已存在", ""),

    IMAGE_UPLOAD_ERROR("200500", "图片上传失败", ""),

    REMOTE_METHOD_ERROR("200600", "远程方法调用失败", ""),
    FALLBACK_DATA_ERROR("200601", "降级数据不存在", ""),

    DATA_ENCRYPT_ERROR("200700", "数据加密失败", ""),

    VERSION_TOO_LOW("200800", "当前版本太低，请升级新版本", "当前版本太低，请升级新版本"),

    WEIXIN_ORDER_ERROR("400011","该微信工单不存在",""),

    // 500000 业务错误
    // 第三方信息相关
    WX_CODE_TO_SESSION_ERROR("500000", "微信接口调用失败", ""),
    USER_THIRD_PART_INFO_GET_ERROR("500001", "用户第三方信息获取失败", ""),




    WX_SEND_REDPACK_ERROR("500504", "发送微信红包失败", ""),
    WX_RETURN_CODE_NULL_ERROR("500505", "xml中不存在return code", ""),

    // 用户相关
    CURRENT_USER_NO_VALID_ERROR("500100", "当前用户无效", ""),
    USER_MOBILE_EXISTS_ERROR("500101", "该手机号已注册", "该手机号已注册"),
    USER_MOBILE_LANDING_EXISTS_ERROR("500102", "该手机号已存在，无法再次领取", "该手机号已存在，无法再次领取"),
    USER_HIDDEN_INFO_ERROR("500103","用户已经隐藏相亲信息","用户已经隐藏相亲信息"),
    USER_INIT_STATUS_ERROR("500104", "用户初始化状态错误", ""),
    USER_CHILD_IS_NOT_STATUS_ERROR("500105", "用户孩子未初始化", ""),
    USER_NOT_CHILD_ERROR("500106", "该用户还未填写孩子信息哦", "该用户还未填写孩子信息哦"),
    USER_CHILD_NOT_TAG_ERROR("500107", "孩子还没有标签信息哦", "孩子还没有标签信息哦"),
    USER_NOT_EXIST_ERROR("500108", "用户信息不存在", "用户信息不存在"),
    USER_LOGOUT_INFO_ERROR("500109","用户已注销","用户已注销"),
    USER_AUTHENTICATION_NAME_ERROR("500110", "用户实名认证姓名错误", "请填写正确的姓名"),
    USER_AUTHENTICATION_ID_NUM_ERROR("500111", "用户实名认证身份证号错误", "请填写正确身份证号码"),
    USER_AUTHENTICATION_FAIL_ERROR("500112", "实名认证调用失败", "认证失败"),
    USER_AUTHENTICATION_EXISTS_ERROR("500113", "用户身份已认证过", "用户已认证"),
    USER_BAN_INFO_ERROR("500114", "用户被处罚封禁了","用户被处罚封禁了"),
    USER_AUTHENTICATIO_ID_EXSIST_ERROR("500115", "该身份证已被使用", "该身份证已被使用"),
    USER_AUTHENTICATIO_TRANSPORT_ERROR("500116", "身份验证传输错误", "请重新操作"),
    USER_AUTHENTICATIO_PHOTO_ERROR("500117", "人像认证失败", "认证失败"),
    USER_ACTIVE_HIDING_LIMIT_ERROR("500118", "7天内只能开启一次隐藏。您可以联系管家帮您解决。", "7天内只能开启一次隐藏。您可以联系管家帮您解决。"),


    USER_FORBIDDEN_ERROR("500119", "用户不在该功能范围", "用户不在该功能范围"),
    USER_AUTHENTICATIO_COUNT_ERROR("500120", "今日认证次数达上限", "今日认证次数达上限"),
    USER_AUTHENTICATIO_INFO_ERROR("500121", "认证信息错误", "请正确填写认证信息"),
    USER_AUTHENTICATIO_SECOND_ERROR("500122", "人脸信息不符，请重试", "人脸信息不符，请重试"),
    USER_AUTHENTICATION_IDNUM_EXISTS_ERROR("500123", "身份证已认证", "您所认证的身份证和姓名已绑定其他相亲卡"),
    USER_AUTHENTICATION_LIMIT_ERROR("500124", "认证次数已达上限", "认证次数已达上限"),
    USER_AUTHENTICATION_STATUS_ERROR("500125", "非会员且未付费", "非会员且未付费"),
    USER_REAL_COMMIT_EXIT_ERROR("500126", "信息真实承诺已存在", "信息真实承诺已存在"),

    USER_CARD_BLOCK_EXIST_ERROR("500130", "已屏蔽该用户", "已屏蔽该用户"),
    USER_CARD_BLOCK_TYPE_EXIST_ERROR("500131", "屏蔽类型不存在", "屏蔽类型不存在"),

    USER_CLOSE_ACCOUNT_ERROR("500140", "暂不允许注销", "暂不允许注销"),

    SAFE_ORDER_NOT_EXIST_ERROR("500150", "验证信息不存在", "验证信息不存在"),
    SAFE_ORDER_CARD_ERROR("500151", "选择卡片错误", "选择卡片错误"),


    IM_BLOCK_USER_ERROR("500160", "拉黑用户失败", "拉黑用户失败"),
    IM_UNBLOCK_USER_ERROR("500161", "取消拉黑用户失败", "取消拉黑用户失败"),


    //限制
    INFO_UPDATE_LIMIT_ERROR("500122","自己只能修改信息一次","自己只能修改信息一次"),
    CONTACT_LIMIT_ERROR("500123","当前联系次数已达上限，请联系今日已解锁的相亲对象，明日即可解锁更多联系方式","当前联系次数已达上限，请联系今日已解锁的相亲对象，明日即可解锁更多联系方式"),
    USER_LIMIT_STRATEGY_EMPTY_ERROR("500124", "用户限制浏览策略为空", ""),
    SPECIAL_GUEST_CONTACT_LIMIT_ERROR("500125","每天只能联系1个嘉宾",""),
    SPECIAL_GUEST_CONTACT_VIP_ERROR("500126","只有VIP可以联系嘉宾",""),
    SPECIAL_GUEST_BE_CONTACT_LIMIT_ERROR("500127","嘉宾已被多次联系，请明天再来",""),
    CONTACT_VIEW_FROM_ERROR("500128", "联系viewFrom错误", ""),
    UPDATE_SECOND_LIMIT_ERROR("500129","已经修改过啦","老家城市每天只能修改一次"),
    UPDATE_FROM_ERROR("500130","修改来源错误","修改来源错误"),
    CONTACT_GENDER_ERROR("500131", "禁止解锁同性联系方式", "禁止解锁同性联系方式"),
    CONTACT_RESTRICTION_PENALTY("500132", "限制联系家长", ""),

    THE_USER_HAS_BEEN_FAVORITE_ERROR("500133", "您已经收藏了该用户", "您已经收藏了该用户"),
    USER_UNHIDE_ERROR("500134", "022-87387855", "经多位家长反馈，平台因此暂时隐藏了您的相亲卡，如需取消隐藏，请拨打022-87387855进行人工解除。"),
    RESTRICT_MODIFY_DATA("500135", "限制修改资料", "您填写的资料信息内容违反《成家行为准则》，%s前将无法修改资料信息。"),

    IM_DUPLICATE_REPORTING("500136", "您已经举报过了，无须重复操作", "您已经举报过了，无须重复操作"),
    FAILURE_TO_REPORT("500137", "举报失败", "举报失败"),

    // 联系人账户
    CONTACT_ACCOUNT_NOT_CREATED("500201","联系人账户未创建","账户未创建"),
    CONTACT_NOT_ENOUGH_ACCOUNTS("500202","联系次数不足","联系次数不足"),
    CONTACT_DOES_NOT_EXIST("500203","联系人不存在","联系人不存在"),
    CONTACT_INFORMATION_GETTING("500204","正在获取联系方式","正在获取联系方式"),
    CONTACT_FEEDBACK_LIMIT("500205", "联系反馈已达上限", "联系反馈已达上限"),
    CONTACT_FEEDBACK_EXIST("500206", "联系反馈已存在", "联系反馈已存在"),
    CONTACT_FEEDBACK_TYPE_NOT_EXIST("500207", "联系反馈类型不存在", "联系反馈类型不存在"),
    CONTACT_ACCUSE_ERROR("500208", "联系人未解锁", "未解锁不能举报"),
    REPORTS_REACHED_THE_UPPER_LIMIT("500209", "举报次数达到上限", "举报次数达到上限"),
    CONTACT_SHARE_LIMIT_ERROR("500210", "解锁他人分享的相亲卡次数已达上限", "解锁他人分享的相亲卡次数已达上限，明天再来吧。"),
    FAVORITE_SHARE_LIMIT_ERROR("500211", "收藏他人分享的相亲卡次数已达上限", "收藏他人分享的相亲卡次数已达上限，明天再来吧。"),
    SEARCH_DETAIL_ERROR("500212", "查看资料次数已达上限，请明天再来", "查看资料次数已达上限，请明天再来。"),



    //拦截toast
    USER_UNBLOCK_PENDING_ERROR("500294","解封待审核","解封待审核"),
    USER_UNBLOCK_REJECT_ERROR("500295","解封驳回修改","解封驳回修改"),
    USER_BLACKLIST_ERROR("500296", "黑名单用户", "黑名单用户"),
    USER_LASTING_SHUTDOWN_BLOCKED_ERROR("500297", "用户停机被永久封禁", "账号异常，禁止使用"),
    USER_LASTING_NOTEXIST_BLOCKED_ERROR("500298", "用户空号被永久封禁", "账号异常，禁止使用"),
    USER_LASTING_BLOCKED_ERROR("500299", "用户被永久封禁", "账号异常，禁止使用"),
    USER_BLOCKED_ERROR("500300", "用户被封禁", "该用户被封禁"),
    AB_TEST_CONFIG_ERROR("500301", "AB测试配置错误", ""),
    USER_SHOW_LIMIT_TYPE_ERROR("500302", "用户为非限制浏览地区", "用户为非限制浏览地区"),
    USER_DEMAND_FIND_ERROR("500303", "没找到符合要求的相亲对象，建议您放宽筛选条件", "没找到符合要求的相亲对象，建议您放宽筛选条件"),
    USER_DEMAND_NOT_SUPRENME_ERROR("500304", "开通至尊会员才能按要求查询", "开通至尊会员才能按要求查询"),
    NOT_ELIGIBLE_FOR_WITHDRAWAL("500305", "不符合提现条件", "不符合提现条件"),
    USER_DEMAND_REQUEST_ERROR("500306", "精准筛选请求错误", "精准筛选请求错误"),

    // 咨询相关
    CONSULT_EXISTS_ERROR("500400", "用户咨询已预约", ""),

    // vip相关
    VIP_EFFECTIVE_UNIT_ERROR("500500", "VIP生效单位错误", ""),
    VIP_STRATEGY_EMPTY_ERROR("500501", "VIP策略为空", ""),

    RECOMMEND_MEMBER_NOT_EXISTS_ERROR("500600", "推荐会员不存在", ""),

    MATCH_MAKER_NOT_EXISTS_ERROR("500700","红娘不存在",""),
    MATCH_MAKER_VIDEO_NOT_EXISTS_ERROR("500701","红娘视频不存在",""),

    MATCH_CONDITION_UPDATE_TIME_LIMIT_ERROR("501001", "每个月只可修改一次筛选对象", ""),
    MATCH_CONDITION_NOT_EXISTS_ERROR("501002", "精准匹配筛选条件不存在", ""),

    CAN_NOT_REFRESH_RECOMMEND("501101", "无法刷新推荐", ""),

    ACTIVITY_END_ERROR("502001", "活动已结束", "活动已结束"),

    //卡片相关
    CARD_CLAIM_ERROR("600000","卡片已被领走",""),
    CARD_UPDATE_ERROR("600001","卡片更新异常",""),
    CARD_EXIST_ERROR("600002","卡片不存在","卡片不存在"),

    CHILD_NOT_EXISTS_ERROR("600100", "孩子信息不存在", ""),

    STRATEGY_CONFIG_CONTENT_TYPE_ERROR("600200", "策略配置内容类型错误", ""),

    BANNER_POSITION_ERROR("600300", "banner位置错误", ""),
    BANNER_STRATEGY_ID_ERROR("600301", "banner策略id配置错误", ""),

    //支付相关
    GOODS_SKU_ERROR("700001", "商品不存在或状态错误", ""),
    CREATE_PAYMENT_ERROR("700002", "生成支付单失败", ""),
    ORDER_NOT_EXISTS_ERROR("700003", "订单不存在", ""),
    ORDER_OWNER_ERROR("700004", "订单所属用户错误", ""),
    PAY_LOCATION_NOT_SUPPORT_ERROR("700005", "用户所在城市不支持支付", ""),
    INSUFFICIENT_USER_BALANCE("700006", "余额不足", ""),
    GOODS_SKU_USER_PERMISSION_ERROR("700007", "无法购买该商品", "无法购买该商品"),
    VIP_MOBILE_EXISTS_ERROR("700008", "购买失败，该用户已在联系人列表", ""),
    PAY_SUPREME_VIP_AMOUNT_ERROR("700009", "购买至尊VIP失败，支付金额不正确", ""),
    ADD_SUPREME_VIP_EXISTS_ERROR("700010", "您已是该等级VIP无需再次开通", "您已是该等级VIP无需再次开通"),
    ADD_SUPREME_VIP_NEED_PAY_ERROR("700011", "您需要支付才能开通至尊会员", ""),
    BUTLER_SUPREME_VIP_FOLLOWID_ERROR("700012", "购买失败，管家ID错误", ""),
    NOT_SUPREME_VIP_LEVEL_ERROR("700013", "不是至尊会员", "您没有此权益，无法上传资料"),
    SUPREME_PASS_ERROR("700014","资料审核通过不可更改了哦","资料审核通过不可更改"),
    UPGRADE_USER_LAST_ORDER_NOT_EXISTS("700015", "用户订单不存在，无法升级", "用户订单不存在，无法升级"),
    UPGRADE_USER_LAST_ORDER_EXPIRED("700016", "活动过期", "活动过期"),
    ORDER_BIZ_TYPE_ERROR("700017", "业务类型错误", "业务类型错误"),


    ANTISPAM_INTERCEPT_ERROR("710000","资料检测未通过",""),

    COUPON_RECEIVE_FAIL_ERROR("700100", "用户领取优惠券失败", ""),
    GET_USER_COUPON_FAIL_ERROR("700101", "获取用户优惠券失败", ""),
    USER_COUPON_INVALID_ERROR("700102", "优惠券无效", "优惠券无效"),

    FAKE_ERROR("800000", "假错误", ""),

    //apollo相关
    APOLLO_WECHAT_NOT_EXIST_ERROR("900000", "客服微信号不存在", ""),

    //问卷相关
    QUESTIONNAIRE_RESULT_ERROR("900500", "结果分析中，请等待", "结果分析中，请等待"),
    QUESTIONNAIRE_STATUS_ERROR("900501", "问卷还没有填写完成", "结果分析中，请等待"),

    //企微相关
    WORK_WX_BUTLER_ERROR("900600", "没有可分配的管家","没有可分配的管家"),

    SEND_WISH_LIMIT_ERROR("900700", "当天发送祝福已达到限制", "您发送祝福过于频繁，请明天再来吧。"),
    SEND_WISH_TYPE_ERROR("900701", "祝福不存在", "祝福不存在"),

    ;

    private final String code;
    private final String message;
    private final String toast;

    ResponseError(String code, String message, String toast) {
        this.code = code;
        this.message = message;
        this.toast = toast;
    }


}
