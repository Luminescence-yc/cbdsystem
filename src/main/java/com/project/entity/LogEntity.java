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
@TableName("t_log")
public class LogEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String operationTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());

    private String ip="0.0.0.0";

    private String operationRecord;

    private String logBelong;

    private String logType;
}