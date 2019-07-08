package com.project.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.AdminBean;
import com.project.bean.RoleBean;
import com.project.dao.IAdminDao;
import com.project.dao.IUserDao;
import com.project.entity.AdminEntity;
import com.project.entity.ContractEntity;
import com.project.entity.RoleEntity;
import com.project.entity.UserEntity;
import com.project.service.IAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 庞培波
 * @version 1.0
 * @ClassName AdminServiceImpl
 * @Description 管理员业务实现类
 * @date 2019年06月01日15:07
 */
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IAdminDao adminDao;
    @Autowired
    private IUserDao userDao;


    @Override
    public int findAdminByUsername(String username) {
        return adminDao.findAdminByUsername(username);
    }

    @Override
    public int findByUserId(int userId) {
        return adminDao.findByUserId(userId);
    }

    @Override
    public Page<AdminBean> findAdminByAll(Map<String, String> condition) {
        //创建分页对象
        Page<AdminEntity> entityPage = new Page<>(Integer.parseInt(condition.get("page")), Integer.parseInt(condition.get("size")));
        StringBuffer a=new StringBuffer("123");
        a.reverse();
        //创建分页对象
        Page<AdminBean> beanPage = new Page<>();
        List<AdminBean> beanList=new ArrayList<>();
        try {
            List<AdminEntity>entityList=adminDao.findAdminByAll(entityPage);
            for (AdminEntity adminEntity : entityList) {
                AdminBean adminBean=new AdminBean();
                BeanUtils.copyProperties(adminEntity,adminBean);
                beanList.add(adminBean);
            }
            BeanUtils.copyProperties(entityPage,beanPage);
            //获取分页数据并分页
           beanPage.setRecords(beanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回分页对象
        return beanPage;
    }

    @Override
    public int addAdmin(AdminBean adminBean) {
        //创建一个用户实体
        AdminEntity adminEntity = new AdminEntity();
        //把对象中的
        adminEntity.setUsername(adminBean.getUsername());
        adminEntity.setRealName(adminBean.getRealName());
        adminEntity.setTel(adminBean.getTel());

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(adminBean.getPassword());
        userEntity.setUsername(adminBean.getUsername());
        userEntity.setSalt("8765");
        List<RoleEntity> roleEntities = new ArrayList<>();
        List<RoleBean> roleBeans = adminBean.getList();
        try {
            for (int i = 0; i < roleBeans.size(); i++) {
                RoleEntity roleEntity = new RoleEntity();
                BeanUtils.copyProperties(roleBeans.get(i), roleEntity);
                roleEntities.add(roleEntity);
            }
            userEntity.setRoleEntities(roleEntities);
            userDao.addUser(userEntity);
            adminEntity.setUserId(userEntity.getId());
            adminDao.addAdmin(adminEntity);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateAdmin(AdminBean adminBean) {
        try {
                adminDao.updateAdmin(adminBean.getId(), adminBean.getTel());
            if (adminBean.getPassword() != null && adminBean.getPassword().length() != 0) {
                userDao.updatePwd(adminBean.getUserId(), adminBean.getPassword());
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateRole(AdminBean adminBean) {
        List<RoleEntity> roleEntities = new ArrayList<>();
        List<RoleBean> roleBeans = adminBean.getList();
        try {
            for (int i = 0; i < roleBeans.size(); i++) {
                RoleEntity roleEntity = new RoleEntity();
                BeanUtils.copyProperties(roleBeans.get(i), roleEntity);
                roleEntities.add(roleEntity);
            }
            userDao.updateUserRole(adminBean.getUserId(), roleEntities);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteAdmin(int id, int userId) {
        try {
            userDao.deleteUser(userId);
            adminDao.deleteAdmin(id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public AdminBean findAdminById(int id, int userId) {
        AdminBean adminBean = new AdminBean();
        try {
            //按照id获得用户的管理员Entity
            AdminEntity adminEntity = adminDao.findAdminById(id);
            //按照id获得用户的用户Entity
            UserEntity userEntity = userDao.findById(userId);
            List<RoleEntity> roleEntities = userEntity.getRoleEntities();
            List<RoleBean> roleBeans = new ArrayList<>();
            for (int i = 0; i < roleEntities.size(); i++) {
                RoleBean roleBean = new RoleBean();
                BeanUtils.copyProperties(roleEntities.get(i), roleBean);
                roleBeans.add(roleBean);
            }
            adminBean.setId(id);
            adminBean.setUserId(userId);
            //6.5号增加的密码属性
            adminBean.setPassword(userEntity.getPassword());

            adminBean.setUsername(userEntity.getUsername());
            adminBean.setRealName(adminEntity.getRealName());
            adminBean.setTel(adminEntity.getTel());
            adminBean.setList(roleBeans);
        }catch (Exception e){
            e.printStackTrace();
        }
        return adminBean;
    }

}
