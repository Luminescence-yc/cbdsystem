package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 朱 骞
 * @version v1.0
 * @ClassName:个人用户实体类
 * @Description: 实体类
 * @date 2019年5月30日9：37
 */
@Data
@TableName("t_personal")
public class PersonalEntity {
    /**
     * 个人用户编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 关联用户外键
     */
    @TableField(exist = false)
    private UserEntity userEntity;
    /**
     * 用户外键
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 真实姓名
     */
    private String relName;
    /**
     * 家庭地址
     */
    private String address;
    /**
     * 电话
     */
    private String tel;
    /**
     * 身份证号码
     */
    private String idCard;
    /**
     * 职位描述
     */
    private String jobDescription;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 投诉次数
     */
    private Integer complainNum=0;
    /**
     * 交易次数
     */
    private Integer tradeNum=0;
}