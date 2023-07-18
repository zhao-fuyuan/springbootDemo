package com.example.demo.model.response;

import com.example.demo.model.po.Users;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Date;

@Getter
@Setter
public class UsersResponse {
    @ApiModelProperty("用户列表")
    private List<Users> usersList;

    @ApiModelProperty("用户总数")
    private Integer total;
}
