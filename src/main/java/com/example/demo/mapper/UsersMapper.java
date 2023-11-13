package com.example.demo.mapper;

import com.example.demo.model.po.Users;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface UsersMapper extends BaseMapper<Users> {

    List<Users> getUsers(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    Users getUserByUserName(@Param("userName")String userName);

    Integer getUsersCount();

}