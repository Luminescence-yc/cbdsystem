package com.project.dao.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.IPersonalBillDao;
import com.project.entity.CountEntity;
import com.project.entity.PersonalBillEntity;
import com.project.mapper.PersonalBillEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: IPersonalBillDaoImpl
 * @Description: 个人账单 持久实现类
 * @date 2019年06月01日 11:34
 */
@ClassType(describe = "个人账单")
@Component
public class PersonalBillDaoImpl implements IPersonalBillDao {

    @Autowired
    private PersonalBillEntityMapper personalbillEntityMapper;

    @Override
    public List<PersonalBillEntity> findPersonalBillInfoByCondition(Page<PersonalBillEntity> page,Map<String, String> condition) throws Exception {
        return personalbillEntityMapper.findPersonalBillInfoByCondition(page,condition);
    }

    @Override
    public int addUserBill(PersonalBillEntity personalBillEntity) throws Exception {
        return personalbillEntityMapper.addUserBill(personalBillEntity);
    }

    @Override
    public CountEntity countPersonal(int personalId) throws Exception {
        return personalbillEntityMapper.countPersonal(personalId);
    }
}
