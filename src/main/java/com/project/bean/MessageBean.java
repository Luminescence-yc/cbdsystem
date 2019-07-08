package com.project.bean;

import lombok.Data;

import java.sql.Date;

/**
 * @author yangcheng
 * @ClassName:
 * @Description:
 * @date 2019年06月03日 10:22
 */
@Data
public class MessageBean {
    /**
     * 主键id
     */
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
