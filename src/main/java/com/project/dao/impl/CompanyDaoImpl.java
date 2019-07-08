package com.project.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.bean.CbdParkingBean;
import com.project.bean.CompanyBean;
import com.project.bean.CompanyContractBean;
import com.project.dao.ICompanyDao;
import com.project.entity.CbdParkingEntity;
import com.project.entity.CompanyContractEntity;
import com.project.entity.CompanyEntity;
import com.project.mapper.CbdparkingEntityMapper;
import com.project.mapper.CompanyEntityMapper;
import com.project.mapper.CompanycontractEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author 刘再桦
 * @version v1.0
 * @ClassName
 * @Description
 * @date 2019年05月31日 11:28
 */
@ClassType(describe = "企业")
@Configuration
public class CompanyDaoImpl implements ICompanyDao {
    @Autowired
    private CompanyEntityMapper companyEntityMapper;

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return 0存在，非0不存在
     */
    @Override
    public int findCompanyByUsername(String username) {
        if(companyEntityMapper.findCompanyByUsername(username)!=null) {
            return 0;
        }
        return 1;
    }

    /**
     * 根据用户Id查找企业用户Id
     *
     * @param userId 用户Id
     * @return 企业用户Id
     */
    @Override
    public int findByUserId(int userId) {
        return companyEntityMapper.findByUserId(userId);
    }

    /**
     * 动态查询方法
     * 企业名称、企业用户名、楼层位置、联系人、联系电话。传入空值查询所有
     *
     * @param condition 条件对象集合
     * @return 企业用户分页对象集合
     */
    @Override
    public List<CompanyEntity> findCompanyByCondition(Page<CompanyEntity> page,Map<String, String> condition) {
        return companyEntityMapper.findCompanyByCondition(page,condition);
    }

    /**
     * 按企业ID查询企业信息
     *
     * @param id 企业id
     * @return 企业Bean对象
     */
    @Override
    public CompanyEntity findCompanyById(int id) {
        return companyEntityMapper.findCompanyById(id);
    }

    /**
     * 添加企业
     * 传入参数：企业登录名、企业密码（需要写入用户表）
     * 传入参数：企业名称、楼层位置、联系人、联系电话（需要写入企业表）。
     *
     * @param companyEntity 企业用户实体类
     * @return 成功返回非0，失败0。
     */
    @Override
    public int addCompany(CompanyEntity companyEntity) {
        return companyEntityMapper.addCompany(companyEntity);
    }

    /**
     * 按企业ID修改企业
     * 传入动态参数：企业登录名、登录密码、（需要修改用户表）
     * 传入动态参数：企业联系人、企业联系电话。（需要修改企业表）
     *
     * @param companyEntity 企业用户对象(包含企业ID)
     * @return 成功返回非0，失败0。-----
     */
    @Override
    public int updateCompany(CompanyEntity companyEntity) {
        return companyEntityMapper.updateCompany(companyEntity);
    }

    /**
     * 按企业ID删除企业对象
     * 1、先删除企业对象（调企业Dao层删除方法）
     * 2、再删除企业用户对象（调用户Dao层删除方法）
     *
     * @param id 企业id
     * @return 成功返回非0，失败0。
     */
    @Override
    public int deleteCompany(int id) {
        return companyEntityMapper.deleteCompany(id);
    }

}
