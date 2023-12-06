package com.example.demo.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class updateUserRequest {
        @ApiModelProperty("用户username")
        private String username;

        @ApiModelProperty("avatarurl")
        private String avatarurl;

}
