package com.project.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: RentHistoryBean
 * @Description: 出租历史记录 Bean
 * @date 2019年05月30日 23:46
 */
@Data
public class RentHistoryBean {
    /**
     * 出租历史记录主键 ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 关联出租个人用户外键 ID
     */
    private PersonalBean rentPersonal;

    /**
     * 关联租赁个人用户外键 ID
     */
    private PersonalBean hirePersonal;

    /**
     * 关联出租个人用户外键 ID
     */
    private Integer rentPersonalId;

    /**
     * 关联租赁个人用户外键 ID
     */
    private Integer hirePersonalId;

    /**
     * 车位地址
     */
    private String address;
    /**
     * 区域编号*
     */
    private String areaNum;

    /**
     * 车位号码
     */
    private String parkingNum;
    /**
     * 租借开始时间
     */
    private String rentStartTime;

    /**
     * 租赁结束时间
     */
    private String rentEndTime;
}