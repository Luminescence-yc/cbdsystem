package com.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CompanyBean;
import com.project.dao.ICompanyDao;
import com.project.dao.IUserDao;
import com.project.entity.CompanyEntity;
import com.project.entity.RoleEntity;
import com.project.entity.UserEntity;
import com.project.service.ICompanyService;
import com.project.test.Userbean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 刘再桦
 * @version v1.0
 * @ClassName CompanyServiceImpl
 * @Description 企业业务实现类
 * @date 2019年05月31日 11:37
 */
@Service
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    private ICompanyDao companyDao;
    @Autowired
    private IUserDao userDao;

    @Override
    public int findCompanyByUsername(String username) {
        return companyDao.findCompanyByUsername(username);
    }

    @Override
    public int findByUserId(int userId) {
        return companyDao.findByUserId(userId);
    }

    /**
     * 动态查询方法
     * 企业名称、企业用户名、楼层位置、联系人、联系电话。传入空值查询所有
     *
     * @param condition 条件对象集合
     * @return 企业用户分页对象集合
     */
    @Override
    public Page<CompanyBean> findCompanyByCondition(Map<String, String> condition) {
        Page<CompanyEntity> page = new Page<CompanyEntity>(Integer.parseInt(condition.get("page")), Integer.parseInt(condition.get("size")));
        Page<CompanyBean> beanPage = new Page<CompanyBean>();
        try {
            Page<CompanyEntity> entityPage = page.setRecords(companyDao.findCompanyByCondition(page, condition));
            List<CompanyEntity> records = entityPage.getRecords();
            List<CompanyBean> list = new ArrayList<>();
            BeanUtils.copyProperties(entityPage, beanPage);
            for (int i = 0; i < records.size(); i++) {
                CompanyBean companyBean = new CompanyBean();
                Userbean userbean = new Userbean();
                BeanUtils.copyProperties(records.get(i), companyBean);
                BeanUtils.copyProperties(records.get(i).getUserEntity(), userbean);
                companyBean.setUsername(userbean.getUsername());
                companyBean.setUserId(userbean.getId());
                list.add(companyBean);
            }
            beanPage.setRecords(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beanPage;
    }

    /**
     * 按企业ID查询企业信息
     *
     * @param id 企业id
     * @return 企业Bean对象
     */
    @Override
    public CompanyBean findCompanyById(int id) {
        CompanyEntity companyEntity = companyDao.findCompanyById(id);
        CompanyBean companyBean = new CompanyBean();
        companyBean.setUserId(companyEntity.getUserEntity().getId());
        companyBean.setCompanyName(companyEntity.getCompanyName());
        companyBean.setContactPerson(companyEntity.getContactPerson());
        companyBean.setFloor(companyEntity.getFloor());
        companyBean.setTel(companyEntity.getTel());
        companyBean.setUsername(companyEntity.getUserEntity().getUsername());
        return companyBean;
    }

    /**
     * 添加企业
     * 传入参数：企业登录名、企业密码（需要写入用户表）
     * 传入参数：企业名称、楼层位置、联系人、联系电话（需要写入企业表）。
     *
     * @param companyBean 企业用户bean
     * @return 成功返回非0，失败0。
     */
    @Override
    public int addCompany(CompanyBean companyBean) {
        CompanyEntity companyEntity = new CompanyEntity();
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(companyBean, companyEntity);
        userEntity.setUsername(companyBean.getUsername());
        userEntity.setPassword(companyBean.getPassword());
        int a = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;//产生1000-9999的随机数
        userEntity.setSalt(a + "");
        List<RoleEntity> roleEntities = new ArrayList<>();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(2);
        roleEntities.add(roleEntity);
        userEntity.setRoleEntities(roleEntities);
        companyEntity.setUserEntity(userEntity);
        try {
            userDao.addUser(userEntity);
            companyEntity.setUserEntity(userEntity);
            companyDao.addCompany(companyEntity);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 按企业ID修改企业
     * 传入动态参数：企业登录名、登录密码、（需要修改用户表）
     * 传入动态参数：企业联系人、企业联系电话。（需要修改企业表）
     *
     * @param companyBean 企业用户对象(包含企业ID)
     * @return 成功返回非0，失败0。
     */
    @Override
    public int updateCompany(CompanyBean companyBean) {
        CompanyEntity companyEntity = new CompanyEntity();
//        companyEntity.setId(companyBean.getId());
        BeanUtils.copyProperties(companyBean, companyEntity);
        try {
            companyDao.updateCompany(companyEntity);
            userDao.updateUsername(companyBean.getUserId(), companyBean.getUsername());
            if(companyBean.getPassword()!=null&&companyBean.getPassword().length()!=0) {
                userDao.updatePwd(companyBean.getUserId(), companyBean.getPassword());
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
        try {
            return companyDao.deleteCompany(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
