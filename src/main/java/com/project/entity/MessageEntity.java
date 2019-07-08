package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * @ClassName:消息实体类
 * @Desription:消息实体类
 * @author :yangcheng
 * @version :1.0
 * @date :2019年5月30日
 */
@Data
@TableName("t_message")
public class MessageEntity {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 个人用户id
     */
    private Integer personalid;
    /**
     * 消息标题
     */
    private String messagetitle;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息时间
     */
    private Date messagetime;
    /**
     * 消息类型
     */
    private String messagetype;
}