package com.project.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.PersonalBean;
import com.project.entity.PersonalEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 朱 骞
 * @version v1.1
 * @ClassName: IPersonalDao
 * @Description: 个人用户持久层接口
 * @date 2019年05月31日 9:21
 */
public interface IPersonalDao {

    /**
     * 根据用户名判断是否有用户
     *
     * @param queryWrapper 用户名参数
     * @return 用户集合
     */
    public List<PersonalEntity> findPersonalByUsername(QueryWrapper<PersonalEntity> queryWrapper);

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
     * @param personalEntity 用户实体对象
     *                       个人用户表（Personal）：真实姓名、家庭住址、职业描述、
     *                       身份证号、邮箱地址、手机号码、用户表外键。
     * @return 非0成功 0失败
     * @throws Exception 异常抛出
     */
    public int addPersonal(PersonalEntity personalEntity) throws Exception;

    /**
     * 查询个人用户
     *
     * @param id 当前登录用户Id
     * @return 个人用户对象
     * @throws Exception 异常处理
     */
    public PersonalEntity findOnePersonalInformation(int id) throws Exception;

    /**
     * 修改个人用户信息
     *
     * @param personalEntity 当前用户对象
     * @return 1成功，0返回
     * @throws Exception
     */
    public int updatePersonalInfoById(PersonalEntity personalEntity) throws Exception;




    /**
     * 修改投诉次数（每次投诉+1）
     *
     * @param id 用户Id
     * @return 0失败，1成功
     * @throws Exception 异常处理
     */
    public int updateComplainNumById(int id) throws Exception;

    
}
