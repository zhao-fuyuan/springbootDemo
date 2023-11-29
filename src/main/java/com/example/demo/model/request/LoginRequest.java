package com.example.demo.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
@Getter
@Setter
public class LoginRequest {
    @ApiModelProperty("code")
    private String code;

    @ApiModelProperty("userName")
    private String userName;

    @ApiModelProperty("avatarurl")
    private String avatarurl;

    @ApiModelProperty("type")
    private Integer type;

}
