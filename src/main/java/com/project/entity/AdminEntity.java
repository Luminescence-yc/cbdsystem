package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 庞培波
 * @version v1.0
 * @ClassName AdminEntity
 * @Description 管理员实体类
 * @date 2019年05月30日10:28
 */
@Data
@TableName("t_admin")
public class AdminEntity {
    /**
     * 管理员id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 关联用户外键
     */
        private int userId;

    /**
     * 工号(来自于用户表)
     */
    private String username;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 电话
     */
    private String tel;
}