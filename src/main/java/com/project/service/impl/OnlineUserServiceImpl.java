package com.project.service.impl;

import com.project.dao.IOnlineUserDao;
import com.project.entity.OnlineUserEntity;
import com.project.service.IOnlineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 罗亚辉
 * @ClassName:
 * @Description:
 * @date 2019年05月31日 13:15
 */
@Service
public class OnlineUserServiceImpl implements IOnlineUserService {
    @Autowired
    private IOnlineUserDao onlineUserDao;
    @Override
    public int addOnlineUser(OnlineUserEntity onlineUserEntity) {
        return onlineUserDao.addOnlineUser(onlineUserEntity);
    }

    @Override
    public List<OnlineUserEntity> findAllDayMaxOnlineUser() {
        return onlineUserDao.findAllDayMaxOnlineUser();
    }

    @Override
    public List<OnlineUserEntity> findOnlineUserByDay(String day) {
        return onlineUserDao.findOnlineUserByDay(day);
    }
}
