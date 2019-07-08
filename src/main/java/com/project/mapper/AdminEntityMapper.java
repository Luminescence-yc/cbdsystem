package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.AdminEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 庞培波
 * @version v1.0
 * @ClassName AdminEntityMapper
 * @Description 管理员映射接口
 * @date 2019年05月31日14:06
 */
public interface AdminEntityMapper extends BaseMapper<AdminEntity> {

    /**
     * 添加管理员方法
     *
     * @param adminEntity 管理员实体类
     * @return 非0成功，0失败。
     */
    @Insert("insert into t_admin values (null,#{user.id},#{username},#{realName},#{tel})")
    int addAdmin(AdminEntity adminEntity);

    @Update("update t_admin set tel=#{tel} where id=#{id};")
    int updateById(@Param("id") int adminId, @Param("tel") String adminTel);

    @Select("select * from t_admin where username != 'superAdmin' ")
    List<AdminEntity> findAdminByAll(Page<AdminEntity> page);

    @Select("select * from t_admin where username=#{username}")
    AdminEntity findAdminByUsername(@Param("username") String username);

    @Select("select id from t_admin where userId=#{userId}")
    int findByUserId(@Param("userId") int userId);
}