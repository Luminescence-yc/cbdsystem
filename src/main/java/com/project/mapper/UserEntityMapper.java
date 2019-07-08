package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.entity.UserEntity;
import org.apache.ibatis.annotations.*;

/**
 * @author 万晓川
 * @version v1.0
 * @ClassName UserEntityMapper
 * @Description 用户映射器
 * @date 2019年05月31日 14:09
 */
public interface UserEntityMapper extends BaseMapper<UserEntity> {
    @Insert("insert into t_user values (null,#{username},#{password},#{salt});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUser(UserEntity userEntity);

    /**
     * 根据用户Id查找用户
     *
     * @param userId 用户Id
     * @return 用户实体类
     */
    @Select("select *,id as userId from t_user where id=#{userId};")
    @Results({@Result(property = "roleEntities", column = "userId",
            many = @Many(select = "com.project.mapper.RoleEntityMapper.selectRoleById"))})
    UserEntity selectByUserId(@Param("userId") int userId);

    /**
     * 根据用户Id查找该用户的盐
     *
     * @param userId 用户Id
     * @return 用户实体类
     */
    @Select("select salt from t_user where id=#{userId};")
    String selectSaltByUserId(@Param("userId") int userId);

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户实体类
     */
    @Select("select *,id as userId from t_user where username=#{username}")
    @Results({@Result(property = "roleEntities", column = "userId",
            many = @Many(select = "com.project.mapper.RoleEntityMapper.selectRoleById"))})
    UserEntity selectByUsername(@Param("username") String username);

    /**
     * 根据用户Id修改密码
     *
     * @param id       用户Id
     * @param password 新密码
     * @return 非0成功，0失败
     */
    @Update("update t_user set password=#{password} where id=#{id}")
    int updatePwd(@Param("id") int id, @Param("password") String password);

    /**
     * 根据用户Id修改用户名
     *
     * @param id       用户Id
     * @param username 用户名
     * @return 非0成功，0失败
     */
    @Update("update t_user set username=#{username} where id=#{id}")
    int updateUsername(@Param("id") int id, @Param("username") String username);
}