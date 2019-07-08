package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.entity.RoleEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 万晓川
 * @version v1.0
 * @ClassName RoleEntityMapper
 * @Description 角色映射器
 * @date 2019年05月31日 14:09
 */
public interface RoleEntityMapper extends BaseMapper<RoleEntity> {

    /**
     * 为用户添加角色
     *
     * @param userId 用户Id
     * @param roleEntities 角色集合
     * @return 非0成功，0失败
     */
    @Insert("<script>"+
            "insert into t_user_role values"+
            "<foreach collection='roleList' item='item' index='index' separator=','>"+
            "(#{userId}, #{item.id})"+
            "</foreach>"+
            "</script>")
    int insertRole(@Param("userId") int userId, @Param("roleList") List<RoleEntity> roleEntities);

    /**
     * 根据用户Id查找角色实体对象集合
     *
     * @param userId 用户Id
     * @return 角色实体对象集合
     */
    @Select("select r.*,r.id as roleId from t_role as r,t_user_role as ur " +
            "where r.id=ur.roleId and ur.userId=#{userId}")
    @Results({@Result(property = "powerEntities", column = "roleId",
            many = @Many(select = "com.project.mapper.PowerEntityMapper.selectByRoleId"))})
    List<RoleEntity> selectRoleById(@Param("userId") int userId);

    /**
     * 删除用户的所有角色
     *
     * @param userId 用户ID
     * @return 角色实体对象集合
     */
    @Delete("delete from t_user_role where userId=#{userId}")
    int deleteRoleByUserId(@Param("userId") int userId);

}