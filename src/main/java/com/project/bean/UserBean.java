package com.project.bean;

import lombok.Data;

import java.util.List;

/**
 * @author 万晓川
 * @version v1.0
 * @ClassName UserBean
 * @Description 用户Bean
 * @date 2019年05月30日 14:50
 */
@Data
public class UserBean {
    /**
     * 角色Bean集合
     */
    private List<RoleBean> roleBeans;
    /**
     * 用户Id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 加盐
     */
    private String salt;
}
