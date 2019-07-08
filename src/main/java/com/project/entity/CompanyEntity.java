package com.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 刘再桦
 * @version v1.0
 * @ClassName CompanyEntity
 * @Desription 企业用户实体类
 * @date 2019/5/30 10:40
 */
@Data
@TableName("t_company")
public class CompanyEntity {
    /**
     * 企业id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 用户实体类
     */
    private UserEntity userEntity;
    /**
     * 企业用户名
     */
    private String username;
    /**
     * 企业名称
     */
    private String companyName;
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