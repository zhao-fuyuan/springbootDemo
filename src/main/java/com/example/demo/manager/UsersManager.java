package com.example.demo.manager;

import com.example.demo.model.po.Users;
import com.example.demo.model.response.UsersResponse;
import com.example.demo.service.impl.UsersServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
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

    }


