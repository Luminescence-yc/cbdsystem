package com.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.project.bean.PersonalBean;

import com.project.dao.IPersonalDao;
import com.project.dao.IUserDao;
import com.project.entity.PersonalEntity;
import com.project.entity.PowerEntity;
import com.project.entity.RoleEntity;
import com.project.entity.UserEntity;
import com.project.service.IPersonalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 朱 骞
 * @version v1.0
 * @ClassName: PersonalServiceImpl
 * @Description: 个人用户实现类
 * @date 2019年05月31日 16:36
 */
@Repository
public class PersonalServiceImpl implements IPersonalService {
    @Autowired
    private IPersonalDao personalDao;
    @Autowired
    private IUserDao userDao;

    @Override
    public int findPersonalByUsername(String username) {
        List<PersonalEntity> personalEntities = new ArrayList<>();
        QueryWrapper<PersonalEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        personalEntities = personalDao.findPersonalByUsername(queryWrapper);
        int size = personalEntities.size();
        if (size != 0) {
            return size;
        }
        return 0;
    }

    @Override
    public int findByUserId(int userId) {
        return personalDao.findByUserId(userId);
    }

    @Override
    public int registerUserAndPersonal(PersonalBean personalBean) {
        UserEntity userEntity = new UserEntity();
        PersonalEntity personalEntity = new PersonalEntity();
        BeanUtils.copyProperties(personalBean, userEntity);

        List<RoleEntity> roleEntities = new ArrayList<>();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(-1);
        roleEntity.setPowerEntities(Lists.<PowerEntity>newArrayList());
        roleEntity.setRoleName(StringUtils.EMPTY);
        roleEntity.setRole(StringUtils.EMPTY);
        roleEntity.setId(1);
        roleEntities.add(roleEntity);
        userEntity.setRoleEntities(roleEntities);

        userDao.addUser(userEntity);
        BeanUtils.copyProperties(personalBean, personalEntity);
        personalEntity.setUserId(userEntity.getId());
        int i = 0;
        try {
            return personalDao.addPersonal(personalEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    @Override
    public PersonalBean getPersonalById(int id) {
        PersonalBean personalBean = null;
        try {
            PersonalEntity personalEntity = personalDao.findOnePersonalInformation(id);
            personalBean = new PersonalBean();
            personalBean.setAddress(personalEntity.getAddress());
            personalBean.setUsername(personalEntity.getUsername());
            personalBean.setRelName(personalEntity.getRelName());
            personalBean.setTel(personalEntity.getTel());
            personalBean.setIdCard(personalEntity.getIdCard());
            personalBean.setJobDescription(personalEntity.getJobDescription());
            personalBean.setEmail(personalEntity.getEmail());
//            BeanUtils.copyProperties(personalEntity,personalBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personalBean;
    }

    @Override
    public int updatePersonalInfoById(PersonalBean personalBean) {
        PersonalEntity personalEntity = new PersonalEntity();
        try {
            BeanUtils.copyProperties(personalBean, personalEntity);
            if (personalBean.getPassword() != null || personalBean.getPassword().length() != 0) {
        /*        userDao.updatePwd(personalBean.getUserId(),personalBean.getPassword());*/
            }
            personalDao.updatePersonalInfoById(personalEntity);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public IPage<PersonalBean> getSellParkingByAll(int page, int size) {
        return null;
    }


}
