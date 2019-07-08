package com.project.bean;

import lombok.Data;

/**
 * @author 朱 骞
 * @version v1.0
 * @ClassName: PersonalBean
 * @Description: 个人用户实体类Bean
 * @date 2019年05月31日 9:25
 */
@Data
public class PersonalBean {
    /**
     * 个人用户编号
     */
    private Integer id;
    /**
     * 用户编号
     */
    private Integer userId;
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
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String salt;
    /**
     * 信誉度
     */
    private String credit;
}
