package com.example.demo.model.po;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import javax.persistence.*;

@Getter
@Setter
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String openid;

    private String email;



    private String password;

    /**
     * 用户状态：1正常2注销
     */
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

}