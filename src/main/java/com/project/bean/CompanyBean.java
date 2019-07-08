package com.project.bean;

import lombok.Data;

/**
 * @author 刘再桦
 * @version v1.0
 * @ClassName CompanyBean
 * @Description 企业实体Bean
 * @date 2019年05月30日 13:56
 */
@Data
public class CompanyBean {
    /**
     * 企业id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 企业名称
     */
    private String companyName;
    /**
     * 企业用户名
     */
    private String username;
    /**
     * 企业用户密码
     */
    private String password;
    /**
     * 企业楼层位置
     */
    private String floor;
    /**
     * 企业联系人
     */
    private String contactPerson;
    /**
     * 联系人电话
     */
    private String tel;
}
