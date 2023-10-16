package com.example.demo.mapper;

import com.example.demo.model.po.Users;
import org.apache.ibatis.annotations.Param;
import com.example.demo.baseMapper.BaseMapper;

import java.util.List;

public interface UsersMapper extends BaseMapper<Users> {

    List<Users> getUsers(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    Integer getUsersCount();

}