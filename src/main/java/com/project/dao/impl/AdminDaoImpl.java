package com.project.dao.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.IAdminDao;
import com.project.entity.*;
import com.project.mapper.AdminEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 庞培波
 * @version 1.0
 * @ClassName
 * @Description
 * @date 2019年05月31日10:26
 */
@ClassType(describe = "管理员")
@Repository
public class AdminDaoImpl implements IAdminDao {

    @Autowired
    private AdminEntityMapper adminEntityMapper;

    @Override
    public int findAdminByUsername(String username) {
        if (adminEntityMapper.findAdminByUsername(username) != null) {
            return 0;
        }
        return 1;
    }

    @Override
    public int findByUserId(int userId) {
        return adminEntityMapper.findByUserId(userId);
    }

    @Override
    public List<AdminEntity> findAdminByAll(Page<AdminEntity> page) throws Exception {
        //通过mapper的方法获取需要的数据
        List<AdminEntity> adminEntities = adminEntityMapper.findAdminByAll(page);
        //返回管理员集合
        return adminEntities;
    }

    @Override
    public int addAdmin(AdminEntity adminEntity) throws Exception {
        return adminEntityMapper.insert(adminEntity);
    }

    @Override
    public int updateAdmin(int adminId, String adminTel) throws Exception {
        return adminEntityMapper.updateById(adminId, adminTel);
    }

    @Override
    public int deleteAdmin(int id) throws Exception {
        return adminEntityMapper.deleteById(id);
    }

    @Override
    public AdminEntity findAdminById(int id) throws Exception {
        //获取管理员对象
        AdminEntity adminEntity = adminEntityMapper.selectById(id);
        //返回管理员对象
        return adminEntity;
    }
}
