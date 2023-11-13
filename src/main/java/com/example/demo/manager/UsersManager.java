package com.example.demo.manager;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.po.Users;
import com.example.demo.model.response.UsersResponse;
import com.example.demo.response.TokenResponse;
import com.example.demo.service.impl.UsersServiceImpl;
import com.example.demo.util.RequestUtils;
import cn.hutool.json.JSONObject;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    @Transactional
    public List<UsersResponse> getUsers(Integer pageNo, Integer pageSize){
//        List<UsersPO> result = usersServiceImpl.getUsersAll();

        List<Users> poList = usersServiceImpl.getUsers((pageNo-1)*pageSize,pageSize);
        Integer total = usersServiceImpl.getUsersCount();
        log.info("Users Total is {}",total);
        return poList.stream().map(item -> {
            UsersResponse response = new UsersResponse();
            BeanUtils.copyProperties(item, response);
            return response;
        }).collect(Collectors.toList());
    }

    public int initUser(Users request){
        return usersServiceImpl.createUser(request);
    }

    public TokenResponse thirdPartLogin(String appid, String secret, String code,Users user, HttpServletRequest request) {

        String result = RequestUtils.getOpenIdByCode(appid, secret, code);
        System.out.println("result:" + result);
        // 提取openid
        String openid = new JSONObject(result).getStr("openid");
        String userJson = JSON.toJSONString(user);
        JwtBuilder jwtBuilder = Jwts.builder(); //获得JWT构造器

        Map<String,Object> map=new Hashtable<>();

        map.put("user",userJson);

        String accessToken = jwtBuilder.setSubject("user") //设置用户数据

                .setIssuedAt(new Date()) //设置jwt生成时间

                .setId("1") //设置id为token id

                .setClaims(map) //通过map传值

                .setExpiration(new Date(System.currentTimeMillis() + 500000)) //设置token有效期

                .signWith(SignatureAlgorithm.HS256, "user") //设置token加密方式和密码

                .compact(); //生成token字符串
//        String accessToken = String.valueOf(RequestUtils.getAccessToken(appid, secret));
        log.info(accessToken);
//        log.info(openid);

        TokenResponse token = new TokenResponse();
        token.setAccessToken(accessToken);
        return token;
    }

    }


