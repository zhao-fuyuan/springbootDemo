package com.example.demo.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsersRequest {
    @ApiModelProperty("用户username")
    private String username;

    @ApiModelProperty("mail")
    private String email;

    @ApiModelProperty("password")
    private String password;

}
