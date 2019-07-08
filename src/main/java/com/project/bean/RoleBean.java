package com.project.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author 万晓川
 * @version v1.0
 * @ClassName RoleEntity
 * @Description 角色实体类
 * @date 2019年05月30日 10:10
 */
@Data
public class RoleBean {
    /**
     * 角色主键ID
     */
    private Integer id;
    /**
     * 权限Bean集合
     */
    private List<PowerBean> powerBeans;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色
     */
    private String role;
}