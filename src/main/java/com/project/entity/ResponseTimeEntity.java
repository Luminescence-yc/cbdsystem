package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_responsetime")
public class ResponseTimeEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String functionPackage;

    private String function;

    private Integer responseTime;

}