package com.project.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.AdminEntity;

import java.util.List;

/**
 * @author 庞培波
 * @version 1.0
 * @ClassName
 * @Description
 * @date 2019年05月31日9:20
 */
public interface IAdminDao {
    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return 0存在，非0不存在
     */
    public int findAdminByUsername(String username);

    /**
     * 根据用户Id查找管理员用户Id
     *
     * @param userId 用户Id
     * @return 管理员用户Id
     */
    public int findByUserId(int userId);

    /**
     * 查询所有的管理员（包含分页）
     * 进入管理员管理界面后展示所有管理员信息时进行的无条件查询所有管理员。
     * user表中工号、密码，
     * 管理员表中的姓名、电话。
     * 两表联查
     * 后台管理员界面需要删除权限列。bean删除对应属性。
     *
     * @param page 分页对象
     * @return 用户分页集合
     * @throws Exception
     */
    public List<AdminEntity> findAdminByAll(Page<AdminEntity> page) throws Exception;

    /**
     * 添加管理员方法
     * 调用Dao层日志接口的添加日志方法。（亦可采用AOP）
     * 根据超级管理员填写的新管理员信息进行添加新的管理员
     *
     * @param adminEntity 管理员实体bean
     *                    表1、登录工号、密码
     *                    表2、电话号码、姓名。
     *                    表3、封装权限对象。
     *                    三表联增
     * @return 非0成功，0失败。
     * @throws Exception
     */
    public int addAdmin(AdminEntity adminEntity) throws Exception;

    /**
     * 按管理员ID修改管理员的电话
     * 增加日志
     *
     * @param adminId  被修改管理员的id
     * @param adminTel 要修改的管理员的电话号码
     * @return 非0成功，0失败。
     * @throws Exception
     */
    public int updateAdmin(int adminId, String adminTel) throws Exception;

    /**
     * 按管理员ID删除管理员对象
     *
     * @param id 被删除管理员id
     * @return 非0成功，0失败。
     * @throws Exception
     */
    public int deleteAdmin(int id) throws Exception;

    /**
     * 按管理员ID查询管理员对象
     *
     * @param id 管理员id
     * @return 管理员对象
     * @throws Exception
     */
    public AdminEntity findAdminById(int id) throws Exception;
}
