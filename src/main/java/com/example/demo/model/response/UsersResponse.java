package com.example.demo.model.response;

import com.example.demo.model.po.Users;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UsersResponse {

    @ApiModelProperty("用户")
    private String username;

    @ApiModelProperty("email")
    private String email;

    @ApiModelProperty("status")
    private Integer status;

}
