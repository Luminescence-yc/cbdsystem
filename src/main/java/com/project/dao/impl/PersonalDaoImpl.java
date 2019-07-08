package com.project.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.IPersonalDao;

import com.project.entity.PersonalEntity;
import com.project.mapper.PersonalEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 朱 骞
 * @version v1.0
 * @ClassName: PersonalDaoImpl
 * @Description:
 * @date 2019年05月31日 10:25
 */
@ClassType(describe = "个人用户信息")
@Component
@Repository
public class PersonalDaoImpl implements IPersonalDao {
    @Autowired
    private PersonalEntityMapper personalEntityMapper;

    @Override
    public List<PersonalEntity> findPersonalByUsername(QueryWrapper<PersonalEntity>queryWrapper) {
        return  personalEntityMapper.selectList(queryWrapper);
    }

    @Override
    public int findByUserId(int userId) {
        return personalEntityMapper.findByUserId(userId);
    }

    @Override
    public int addPersonal(PersonalEntity personalEntity)throws Exception {
        return personalEntityMapper.insert(personalEntity);
    }

    @Override
    public PersonalEntity findOnePersonalInformation(int id) throws Exception {
        return personalEntityMapper.selectById(id);
    }

    @Override
    public int updatePersonalInfoById(PersonalEntity personalEntity) throws Exception {
        int i=personalEntityMapper.updatePersonalInfoById(personalEntity);
        return i;
    }





    @Override
    public int updateComplainNumById(int id) throws Exception {
        int i=personalEntityMapper.updateComplainNumById(id);
        return i;
    }
}
