package com.project.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.entity.RoleEntity;
import lombok.Data;

/**
 * @version v1.0
 * @author 庞培波
 * @ClassName PowerEntity
 * @Description 权限实体类
 * @date 2019年05月30日10:26
 */
@Data
public class PowerBean {
    /**
     * 权限描述
     */
    private String powerName;

    /**
     * 权限名称
     */
    private String power;
}