package com.example.demo.service;

import com.example.demo.model.po.Users;

import java.util.List;

public interface IUsersService {

    List<Users> getUsersAll();

    List<Users> getUsers(Integer pageSize, Integer pageNo);

    Integer getUsersCount();

    int createUser(Users request);
}
