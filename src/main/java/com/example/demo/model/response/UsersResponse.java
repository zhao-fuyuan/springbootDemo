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

    @ApiModelProperty("avatarurl")
    private String avatarurl;

    @ApiModelProperty("status")
    private Integer status;

}
