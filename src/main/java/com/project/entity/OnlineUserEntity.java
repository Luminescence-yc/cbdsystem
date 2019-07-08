package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_onlineuser")
public class OnlineUserEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String countDate=new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

    private String countTime=new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());

    private Integer onlineUserNum;
}