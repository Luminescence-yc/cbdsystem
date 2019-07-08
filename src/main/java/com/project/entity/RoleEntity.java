package com.project.entity;

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
@TableName("t_role")
public class RoleEntity {

    /**
     * 角色主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 权限实体对象集合
     */
    private List<PowerEntity> powerEntities;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色
     */
    private String role;
}