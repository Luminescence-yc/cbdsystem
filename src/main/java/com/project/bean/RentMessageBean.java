package com.project.bean;

import com.project.entity.PersonalEntity;
import com.project.entity.RentParkingEntity;
import lombok.Data;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:请求响应时间bean
 * @Description:出租车位预定留言封装前端需要的数据
 * @date 2019年05月31日 9:08
 */
@Data
public class RentMessageBean {
    /**
     * 出租车位留言主键id
     */
    private Integer id;
    /**
     * 个人用户对象
     */
    private PersonalEntity personalEntity;
    /**
     * 租车用户id
     */
    private int personalId;
    /**
     * 出租车位对象
     */
    private RentParkingEntity rentParkingEntity;
    /**
     * 出租车位Id
     */
    private int rentId;
    /**
     * 出租者用户对象
     */
    private PersonalEntity rentPersonalEntity;
    /**
     * 出租者用户Id
     */
    private int rentPersonalId;
    /**
     * 出租车位留言
     */
    private String rentMessage;
    /**
     * 用户名
     */
    private String username;
    /**
     * 投诉次数
     */
    private Integer complainNum;
    /**
     * 交易次数
     */
    private Integer tradeNum;
    /**
     * 职位描述
     */
    private String jobDescription;
    /**
     * 电话
     */
    private String tel;
}
