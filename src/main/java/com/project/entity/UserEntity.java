package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author 万晓川
 * @version v1.0
 * @ClassName UserEntity
 * @Description 用户实体类
 * @date 2019年05月30日 10:02
 */
@Data
@TableName("t_user")
public class UserEntity {
    /**
     * 用户主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色实体对象集合
     */
    private List<RoleEntity> roleEntities;

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