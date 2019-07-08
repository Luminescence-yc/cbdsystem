package com.project.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.dao.IOnlineUserDao;
import com.project.entity.OnlineUserEntity;
import com.project.mapper.OnlineUserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 罗亚辉
 * @ClassName:
 * @Description:
 * @date 2019年05月31日 12:40
 */
@Repository
public class OnlineUserDaoImpl implements IOnlineUserDao {
    @Autowired
    private OnlineUserEntityMapper onlineUserEntityMapper;

    @Override
    public int addOnlineUser(OnlineUserEntity onlineUserEntity) {
        return onlineUserEntityMapper.insert(onlineUserEntity);
    }

    @Override
    public List<OnlineUserEntity> findAllDayMaxOnlineUser() {
        return onlineUserEntityMapper.findAllDayMaxOnlineUser();
    }

    @Override
    public List<OnlineUserEntity> findOnlineUserByDay(String day) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("countDate",day);
        return onlineUserEntityMapper.selectList(queryWrapper);
    }
}
