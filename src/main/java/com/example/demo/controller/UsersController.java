package com.example.demo.controller;

import com.example.demo.api.common.ResponseError;
import com.example.demo.manager.UsersManager;
import com.example.demo.model.po.Users;
import com.example.demo.model.response.UsersResponse;
import com.example.demo.response.ResponseUtil;
import com.example.demo.response.ResultWrapper;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1")
@Slf4j
public class UsersController {
    @Resource
    private UsersManager userManager;


    @GetMapping("/users/list")
    public ResultWrapper<UsersResponse> getUsers(@RequestParam Integer pageNo, @RequestParam Integer pageSize){
        UsersResponse res = userManager.getUsers(pageNo,pageSize);
        return ResponseUtil.success(res);
    }
    @PostMapping("/user/register")
    public ResultWrapper<Void> initUser(@RequestBody Users request){
        userManager.initUser(request);
        if(1==2){
            return ResponseUtil.error(ResponseError.SYSTEM_ERROR);
        }
        return ResponseUtil.success("success");
    }
}
