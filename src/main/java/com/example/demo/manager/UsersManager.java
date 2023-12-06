package com.example.demo.manager;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.example.demo.model.po.Users;
import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.request.updateUserRequest;
import com.example.demo.model.response.UsersResponse;
import com.example.demo.response.ResponseUtil;
import com.example.demo.response.ResultWrapper;
import com.example.demo.response.TokenResponse;
import com.example.demo.service.impl.UsersServiceImpl;
import com.example.demo.util.RequestUtils;
import cn.hutool.json.JSONObject;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UsersManager {
    @Resource
    private UsersServiceImpl usersServiceImpl;
    @Value("${spring.social.weixin.app-id}")
    private String appid;

    @Value("${spring.social.weixin.app-secret}")
    private String appSecret;

    @Resource
    private UsersManager userManager;

    @Transactional
    public List<UsersResponse> getUsers(Integer pageNo, Integer pageSize) {
//        List<UsersPO> result = usersServiceImpl.getUsersAll();

        List<Users> poList = usersServiceImpl.getUsers((pageNo - 1) * pageSize, pageSize);
        Integer total = usersServiceImpl.getUsersCount();
        log.info("Users Total is {}", total);
        return poList.stream().map(item -> {
            UsersResponse response = new UsersResponse();
            BeanUtils.copyProperties(item, response);
            return response;
        }).collect(Collectors.toList());
    }

    public UsersResponse getUser(HttpServletRequest request){
        String token = request.getHeader("accessToken");
        JwtParser jwtParser = Jwts.parser();
//        Object user = jwtParser.setSigningKey("user").parseClaimsJws(token).getBody().get("user");
        String user =  jwtParser.setSigningKey("user").parseClaimsJws(token).getBody().get("user").toString();
        String[] personDate = user.split(",");
        UsersResponse response = new UsersResponse();
        response.setUsername(personDate[1].split("=")[1]);
        response.setAvatarurl(personDate[3].split("=")[1]);
        response.setStatus(Integer.valueOf(personDate[4].split("=")[1]));
        return response;
    }

    public int updateUser(updateUserRequest userRequest,HttpServletRequest request){
        String token = request.getHeader("accessToken");
        JwtParser jwtParser = Jwts.parser();
//        Object user = jwtParser.setSigningKey("user").parseClaimsJws(token).getBody().get("user");
        String user =  jwtParser.setSigningKey("user").parseClaimsJws(token).getBody().get("user").toString();
        String[] personDate = user.split(",");
        return usersServiceImpl.updateUserById(userRequest.getUsername(),userRequest.getAvatarurl(), Integer.valueOf(personDate[0].split("=")[1]));
    }

    public int initUser(Users request) {
        return usersServiceImpl.createUser(request);
    }

    public TokenResponse thirdPartLogin(String appid, String secret, String code, Users user, HttpServletRequest request) {

//        String userJson = JSON.toJSONString(user);
        JwtBuilder jwtBuilder = Jwts.builder(); //获得JWT构造器

        Map<String, Object> map = new Hashtable<>();

        map.put("user", user);

        String accessToken = jwtBuilder.setSubject("user") //设置用户数据

                .setIssuedAt(new Date()) //设置jwt生成时间

                .setId("1") //设置id为token id

                .setClaims(map) //通过map传值

                .setExpiration(new Date(System.currentTimeMillis() + 2592000)) //设置token有效期

                .signWith(SignatureAlgorithm.HS256, "user") //设置token加密方式和密码

                .compact(); //生成token字符串
//        String accessToken = String.valueOf(RequestUtils.getAccessToken(appid, secret));
        log.info(accessToken);
//        log.info(openid);

        TokenResponse token = new TokenResponse();
        token.setAccessToken(accessToken);
        return token;
    }

    public ResultWrapper<TokenResponse> doLogin(LoginRequest request,
                                                HttpServletRequest httpServletRequest) {
        log.info("PassportController#thirdPartLogin-request code:{},type:{}", request.getCode(), request.getType());
        String result = RequestUtils.getOpenIdByCode(appid, appSecret, request.getCode());
        System.out.println("result:" + result);
        // 提取openid
        String openid = new JSONObject(result).getStr("openid");
        Users user = usersServiceImpl.getUserByOpenId(openid);
//        Users user = usersServiceImpl.getUserByUserName(request.getUserName());
        if (ObjectUtil.isEmpty(user)) {
            user = new Users();
            user.setOpenid(openid);
            user.setUsername("微信用户");
            user.setStatus(1);
            user.setAvatarurl("https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132");
            userManager.initUser(user);
        }
        TokenResponse token = this.thirdPartLogin(appid, appSecret, request.getCode(), user, httpServletRequest);
        return ResponseUtil.success(token);

    }
}

