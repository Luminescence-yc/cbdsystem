package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: RentHistoryEntity
 * @Description: 出租历史记录实体类
 * @date 2019年05月30日 10:30
 */
@Data
@TableName("t_renthistory")
public class RentHistoryEntity {

    /**出租历史记录主键 ID*/
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**关联出租个人用户外键 ID*/
    private PersonalEntity rentPersonal;

    /**关联租赁个人用户外键 ID*/
    private PersonalEntity hirePersonal;

    /** 关联出租个人用户外键 ID*/
    private Integer rentPersonalId;

    /**关联租赁个人用户外键 ID*/
    private Integer hirePersonalId;

    /** 车位所在小区地址*/
    private String address;

    /**区域编号*/
    private String areaNum;

    /**车位号码*/
    private String parkingNum;

    /**租借开始时间*/
    private String rentStartTime;

    /**租赁结束时间*/
    private String rentEndTime;
}