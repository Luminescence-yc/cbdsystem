package com.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.PersonalBean;

/**
 * @author 朱 骞
 * @version v1.2
 * @ClassName: IPersonalService
 * @Description: 个人用户业务层接口
 * @date 2019年05月30日 13:48
 * <p>
 * 修改日期：2019年5月31日 9:30
 * 修改人：费宇
 * 修改内容：增加注册方法；增加判断用户名是否存在方法
 */
public interface IPersonalService {
    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return 0存在，非0不存在
     */
    public int findPersonalByUsername(String username);

    /**
     * 根据用户Id查找个人用户Id
     *
     * @param userId 用户Id
     * @return 个人用户Id
     */
    public int findByUserId(int userId);

    /**
     * 注册用户方法（需要调用户添加方法和个人用户添加方法）
     *
     * @param personalBean 用户bean
     *                     需要封装用户表（User）：用户名、密码、加密盐
     *                     个人用户表（Personal）：真实姓名、家庭住址、职业描述、
     *                     身份证号、邮箱地址、手机号码。
     * @return 非0成功 0失败
     */
    public int registerUserAndPersonal(PersonalBean personalBean);


    /**
     * 查询个人用户和用户信息
     *
     * @param id 当前登录用户Id
     * @return 个人用户对象
     * 要封装信息字段：用户名、真实姓名、家庭住址、职业描述、
     * 身份证号、邮箱地址、手机号码。
     */
    public PersonalBean getPersonalById(int id);

    /**
     * 修改个人用户信息
     *
     * @param personalBean 修改对象信息
     * @return 1成功，0返回
     */
    public int updatePersonalInfoById(PersonalBean personalBean);



}
