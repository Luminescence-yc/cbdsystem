package com.project.cbdsystem;

import com.project.dao.IUserDao;
import com.project.entity.RoleEntity;
import com.project.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class shiroTest {
    @Autowired
    private IUserDao iUserDao;

    @Test
    public void register() {
        UserEntity user = new UserEntity();
        user.setUsername("wxc2");
        user.setPassword("123");
        user.setSalt("8766");
        List<RoleEntity> roleEntities = new ArrayList<>();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(3);
        RoleEntity roleEntity1 = new RoleEntity();
        roleEntity1.setId(4);
        roleEntities.add(roleEntity);
        roleEntities.add(roleEntity1);
        user.setRoleEntities(roleEntities);
        iUserDao.addUser(user);
    }
}
