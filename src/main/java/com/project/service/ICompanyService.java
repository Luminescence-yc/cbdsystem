package com.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CbdParkingBean;
import com.project.bean.CompanyBean;
import com.project.bean.CompanyContractBean;
import com.project.bean.UserBean;
import com.project.entity.CompanyEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 刘再桦
 * @version v1.0
 * @ClassName ICompanyService
 * @Desription 企业用户 业务接口
 * @date 2019/5/30 11:22
 */
public interface ICompanyService {
    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return 0存在，非0不存在
     */
    public int findCompanyByUsername(String username);

    /**
     * 根据用户Id查找企业用户Id
     *
     * @param userId 用户Id
     * @return 企业用户Id
     */
    public int findByUserId(int userId);

    /**
     * 动态查询方法
     * 企业名称、企业用户名、楼层位置、联系人、联系电话。传入空值查询所有
     *
     * @param condition 条件对象集合
     * @return 企业用户分页对象集合
     */
    Page<CompanyBean> findCompanyByCondition(Map<String, String> condition);

    /**
     * 按企业ID查询企业信息
     *
     * @param id 企业id
     * @return 企业Bean对象
     */
    CompanyBean findCompanyById(int id);

    /**
     * 添加企业
     * 传入参数：企业登录名、企业密码（需要写入用户表）
     * 传入参数：企业名称、楼层位置、联系人、联系电话（需要写入企业表）。
     *
     * @param companyBean 企业用户bean
     * @return 成功返回非0，失败0。
     */
    int addCompany(CompanyBean companyBean);

    /**
     * 按企业ID修改企业
     * 传入动态参数：企业登录名、登录密码、（需要修改用户表）
     * 传入动态参数：企业联系人、企业联系电话。（需要修改企业表）
     *
     * @param companyBean 企业用户对象(包含企业ID)
     * @return 成功返回非0，失败0。
     */
    int updateCompany(CompanyBean companyBean);

    /**
     * 按企业ID删除企业对象
     * 1、先删除企业对象（调企业Dao层删除方法）
     * 2、再删除企业用户对象（调用户Dao层删除方法）
     *
     * @param id 企业id
     * @return 成功返回非0，失败0。
     */
    int deleteCompany(int id);
}
