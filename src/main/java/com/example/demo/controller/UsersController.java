package com.example.demo.controller;

import cn.hutool.jwt.JWT;
import com.example.demo.manager.UsersManager;
import com.example.demo.model.po.Users;
import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.request.updateUserRequest;
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
import org.springframework.beans.factory.annotation.Value;
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


    @GetMapping("/users/list")
    public ResultWrapper<List<UsersResponse>> getUsers(@RequestParam Integer pageNo, @RequestParam Integer pageSize){
        List<UsersResponse> res = userManager.getUsers(pageNo,pageSize);
        return ResponseUtil.success(res);
    }
    @GetMapping("/start")
    public ResultWrapper<UsersResponse> start(HttpServletRequest httpServletRequest){
        UsersResponse res = userManager.getUser(httpServletRequest);
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
        return userManager.doLogin(request,httpServletRequest);
    }
    @PostMapping("/user/update")
    public ResultWrapper<Void> updateUser(@RequestBody updateUserRequest request,
                                          HttpServletRequest httpServletRequest){
        userManager.updateUser(request,httpServletRequest);
        return ResponseUtil.success("success");
    }
}
