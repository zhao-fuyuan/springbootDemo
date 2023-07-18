package com.example.demo.manager;

import com.example.demo.model.po.Users;
import com.example.demo.model.response.UsersResponse;
import com.example.demo.response.ResponseUtil;
import com.example.demo.response.ResultWrapper;
import com.example.demo.service.impl.UsersServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Component
@Slf4j
public class UsersManager {
    @Resource
    private UsersServiceImpl usersServiceImpl;

    @Transactional
    public UsersResponse getUsers(Integer pageNo, Integer pageSize){
//        List<UsersPO> result = usersServiceImpl.getUsersAll();

        List<Users> result = usersServiceImpl.getUsers((pageNo-1)*pageSize,pageSize);
        Integer total = usersServiceImpl.getUsersCount();
        log.info("Users Total is {}",total);
        UsersResponse usersResult = new UsersResponse();
        usersResult.setUsersList(result);
        usersResult.setTotal(total);
        return usersResult;
    }

    public int initUser(Users request){
        return usersServiceImpl.createUser(request);
    }
}
