package com.example.demo.service.impl;

import com.example.demo.mapper.UsersMapper;
import com.example.demo.model.po.Users;
import com.example.demo.service.IUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
@Slf4j
public class UsersServiceImpl implements IUsersService {
    @Resource
    private UsersMapper usersMapper;

    @Override
    public List<Users> getUsersAll(){
        return usersMapper.selectAll();
    };

    @Override
    public List<Users> getUsers(Integer pageNo, Integer pageSize) {
        return usersMapper.getUsers(pageNo,pageSize);
    }

    @Override
    public Users getUserByUserName(String userName) {
        return usersMapper.getUserByUserName(userName);
    }

    @Override
    public Integer getUsersCount() {
        return usersMapper.getUsersCount();
    }


    @Override
    public int createUser(Users request) {
        return usersMapper.insertSelective(request);
    }

}
