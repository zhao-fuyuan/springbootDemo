package com.example.demo.service;

import com.example.demo.model.po.Users;

import java.util.List;

public interface IUsersService {

    List<Users> getUsersAll();

    List<Users> getUsers(Integer pageSize, Integer pageNo);

    Users getUserByUserName(String userName);

    Users getUserByOpenId(String openId);

    Integer updateUserById(String username,String avatarurl,Integer id);

    Integer getUsersCount();

    int createUser(Users request);
}
