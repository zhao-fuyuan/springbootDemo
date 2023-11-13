package com.example.demo.controller;

import cn.hutool.jwt.JWT;
import com.example.demo.manager.UsersManager;
import com.example.demo.model.po.Users;
import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.response.UsersResponse;
import com.example.demo.response.ResponseUtil;
import com.example.demo.response.ResultWrapper;
import com.example.demo.response.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.service.impl.UsersServiceImpl;


@RestController
@RequestMapping("/v1")
@Slf4j
public class UsersController {
    @Resource
    private UsersManager userManager;
    @Resource
    private UsersServiceImpl usersServiceImpl;


    @GetMapping("/users/list")
    public ResultWrapper<List<UsersResponse>> getUsers(@RequestParam Integer pageNo, @RequestParam Integer pageSize){
        List<UsersResponse> res = userManager.getUsers(pageNo,pageSize);
        return ResponseUtil.success(res);
    }
    @PostMapping("/user/register")
    public ResultWrapper<Void> initUser(@RequestBody Users request){
        userManager.initUser(request);
        return ResponseUtil.success("success");
    }
    @PostMapping("/users/login")
    public ResultWrapper<TokenResponse> miniAppLogin(@RequestBody LoginRequest request,
                                                     HttpServletRequest httpServletRequest) {
        log.info("PassportController#thirdPartLogin-request code:{},type:{}",request.getCode(),request.getType());
        log.info("request is {},{}",request.getUserName(),request.getPassWord());
        Users user = usersServiceImpl.getUserByUserName(request.getUserName());

        if (user.getPassword().isEmpty()){
            return ResponseUtil.error("505","用户名或密码错误","用户名或密码错误");
        }
        if (user.getPassword().equals(request.getPassWord())) {
            TokenResponse token = userManager.thirdPartLogin("wx245a7eb6cdfe0bcc", "da96b5f8ff01455bdfd5e46470bf8b12", request.getCode(), user, httpServletRequest);
            return ResponseUtil.success(token);
        }
        return ResponseUtil.error("505","用户名或密码错误","用户名或密码错误");
    }
}
