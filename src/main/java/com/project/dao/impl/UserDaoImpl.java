package com.project.dao.impl;

import com.project.annotation.ClassType;
import com.project.dao.IUserDao;
import com.project.entity.RoleEntity;
import com.project.entity.UserEntity;
import com.project.mapper.RoleEntityMapper;
import com.project.mapper.UserEntityMapper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 万晓川
 * @version v1.0
 * @ClassName IUserDao
 * @Description 用户持久接口实现类
 * @date 2019年05月31日 14:09
 */
@ClassType(describe = "用户")
@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private RoleEntityMapper roleEntityMapper;

    @Override
    public int addUser(UserEntity userEntity) {
        String newPassword = new SimpleHash("MD5", userEntity.getPassword(), userEntity.getSalt(), 2).toHex();
        userEntity.setPassword(newPassword);
        if (userEntityMapper.insertUser(userEntity) >= 1) {
            List<RoleEntity> list = userEntity.getRoleEntities();
            roleEntityMapper.insertRole(userEntity.getId(), list);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateUserRole(int userId, List<RoleEntity> roleEntities) {
        roleEntityMapper.deleteRoleByUserId(userId);
        roleEntityMapper.insertRole(userId, roleEntities);
        return 0;
    }

    @Override
    public int deleteUser(int userId) {
        if (roleEntityMapper.deleteRoleByUserId(userId) >= 1) {
            return userEntityMapper.deleteById(userId);
        }
        return 0;
    }

    @Override
    public UserEntity findById(int id) {
        return userEntityMapper.selectByUserId(id);
    }

    @Override
    public UserEntity getByUsername(String name) {
        return userEntityMapper.selectByUsername(name);
    }

    @Override
    public int updatePwd(int userId, String password) {
        String salt = userEntityMapper.selectSaltByUserId(userId);
        String newPassword = new SimpleHash("MD5", password, salt, 2).toHex();
        return userEntityMapper.updatePwd(userId, newPassword);
    }

    @Override
    public int updateUsername(int userId, String username) {
        return userEntityMapper.updateUsername(userId, username);
    }
}
