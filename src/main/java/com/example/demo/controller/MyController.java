package com.example.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.response.ResultWrapper;
import com.example.demo.response.ResponseUtil;

@RestController
public class MyController {
    @GetMapping("/hello")
    public ResultWrapper hello() {
        return ResponseUtil.success("hello, World!");
    }
}