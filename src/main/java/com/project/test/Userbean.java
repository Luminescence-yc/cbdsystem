package com.project.test;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class Userbean {
    private int id;
    private String username;
    private String password;
    private String birthday;
    private String nativePlace;
}
