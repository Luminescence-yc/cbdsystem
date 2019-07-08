package com.project.bean;


import com.project.entity.RoleEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 庞培波
 * @version 1.0
 * @ClassName AdminBean
 * @Description 管理员实体bean
 * @date 2019年05月30日14:07
 */
@Data
public class AdminBean {
    /**
     * 管理员id
     */
    private Integer id;
    /**
     * 关联用户外键
     */
    private int userId;
    /**
     * 管理员工号(来自于用户表的username)
     */
    private String username;

    /**
     * 用户密码(来自于用户表的password)
     */
    private String password;
    /**
     * 用户姓名
     */
    private String realName;

    /**
     * 电话
     */
    private String tel;

    /**
     * 权限名称（通过管理员表的id获取管理员对象，
     * 再通过管理员对象得到用户对象，
     * 再通过用户对象获得角色集合，
     * 再通过角色集合获得权限集合，
     * 再得到权限里面的权限名集合）
     */
    private List<RoleBean> list=new ArrayList<>();
    /**
     * 用户管理员
     */
    private String userAdmin="-1";
    /**
     * 车位管理员
     */
    private String parkingAdmin="-1";
    /**
     * 合同管理员
     */
    private String contractAdmin="-1";
    /**
     * 投诉管理员
     */
    private String complainAdmin="-1";


}
