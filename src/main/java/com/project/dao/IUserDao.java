package com.project.dao;

import com.project.entity.RoleEntity;
import com.project.entity.UserEntity;

import java.util.List;

/**
 * @author 万晓川
 * @version v1.0
 * @ClassName IUserDao
 * @Description 用户持久接口
 * @date 2019年05月31日 11:00
 */
public interface IUserDao {

    /**
     * 添加用户
     *
     * @param userEntity 用户实体类
     * @return 非0成功，0失败
     */
    int addUser(UserEntity userEntity);

    /**
     * 修改用户角色（权限）
     *
     * @param userId       用户id
     * @param roleEntities 角色实体集合
     * @return 非0成功，0失败
     */
    int updateUserRole(int userId, List<RoleEntity> roleEntities);

    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @return 非0成功，0失败
     */
    int deleteUser(int userId);

    /**
     * 根据用户Id查找用户实体类
     *
     * @param id 用户Id
     * @return 用户实体类
     */
    UserEntity findById(int id);

    /**
     * 根据用户名查找用户实体类
     *
     * @param name 用户名
     * @return 用户实体类
     */
    UserEntity getByUsername(String name);

    /**
     * 根据用户Id修改用户密码
     *
     * @param userId   用户Id
     * @param password 密码
     * @return 非0成功，0失败
     */
    int updatePwd(int userId, String password);

    /**
     * 根据用户Id修改用户名
     *
     * @param userId   用户Id
     * @param username 用户名
     * @return 非0成功，0失败
     */
    int updateUsername(int userId, String username);
}
