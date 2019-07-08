package com.project.bean;

import com.project.entity.PersonalParkingEntity;
import lombok.Data;


/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:请求响应时间bean
 * @Description:出租车位业务层接口封装前端需要的数据
 * @date 2019年05月31日 9:07
 */
@Data
public class RentParkingBean {
    /**
     * 出租车位表主键id
     */
    private Integer id;
    /**
     * 个人用户对象
     */
    private PersonalBean personalBean;

    /**
     * 个人车位对象
     */
    private PersonalParkingBean personalParkingBean;

    /**
     * 租借开始日期
     */
    private String startTime;
    /**
     * 租借截止日期
     */
    private String endTime;
    /**
     * 信誉度
     */
    private String credibility;
    /**
     * 出租价格
     */
    private Double rentPrice;

    /**
     * 留言人数
     */
    private int countMessage;

    /**
     * 车位地址
     */
    private String address;

    /**
     * 投诉次数
     */
    private Integer complainNum;

    /**
     * 交易次数
     */
    private Integer tradeNum;
    /**
     * 信誉度
     */
    private String credit;
    /**
     * 用户名
     */
    private String username;
    /**
     * 职业
     */
    private String jobDescription;
    /**
     * 出租车位信息
     */
    private String rentParkingAddress;
}
