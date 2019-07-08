package com.project.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.AdminBean;

import java.util.Map;


/**
 * @author 庞培波
 * @version 1.0
 * @ClassName IAdminService
 * @Description 管理员管理接口
 * @date 2019年05月30日11:22
 */
public interface IAdminService {
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
     * admin表中的姓名、电话。
     * 两表联查
     * 后台管理员界面需要删除权限列。bean删除对应属性。
     *
     * @param condition 分页条件
     * @return 用户分页集合
     */
    public Page<AdminBean> findAdminByAll(Map<String, String> condition);

    /**
     * 添加管理员方法
     * 调用Dao层日志接口的添加日志方法。（亦可采用AOP）
     * 根据超级管理员填写的新管理员信息进行添加新的管理员
     *
     * @param adminBean 管理员实体bean
     *                  user表中、登录工号、密码
     *                  admin表中、电话号码、姓名。
     *                  user_role表中、用户和管理员id对应。
     *                  role表中、角色，权限名
     *                  四表联增
     * @return 非0成功，0失败。
     */
    public int addAdmin(AdminBean adminBean);

    /**
     * 按管理员ID修改管理员的电话和密码
     *
     * @param adminBean 包含被修改管理员的id，还有权限对象的值
     * @return 非0成功，0失败。
     */
    public int updateAdmin(AdminBean adminBean);

    /**
     * 修改管理员权限
     *
     * @param adminBean 包含被修改用户的id，还有权限对象的值
     * @return 非0成功，0失败。
     */
    public int updateRole(AdminBean adminBean);

    /**
     * 按管理员ID删除管理员对象
     * user表中、登录工号、密码
     * admin表中、电话号码、姓名。
     * user_role表中、用户和管理员id对应。
     * role表中、角色，权限名
     * 四表联删
     *
     * @param id     被删除管理员id
     * @param userId 被删除用户id
     * @return 非0成功，0失败。
     */
    public int deleteAdmin(int id, int userId);

    /**
     * 按管理员ID查询管理员对象
     * 四表联查
     *
     * @param id 管理员id
     * @param id 用户id
     * @return 管理员对象
     * 五表联查
     */
    public AdminBean findAdminById(int id, int userId);

}
