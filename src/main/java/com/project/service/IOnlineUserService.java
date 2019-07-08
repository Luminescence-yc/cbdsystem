package com.project.service;

import com.project.entity.OnlineUserEntity;

import java.util.List;

/**
 * 在线人数业务接口
 * @author 罗亚辉
 * @date 2019/5/30 11:51
 */
public interface IOnlineUserService {
    /**
     * 添加在线人数
     * @param onlineUserEntity 在线人数对象
     * @return 添加成功返回1，失败返回0
     *
     */
    int addOnlineUser(OnlineUserEntity onlineUserEntity);

    /**
     * 查询所有每天最大在线人数
     * @return 每天在线人数集合
     */
    List<OnlineUserEntity> findAllDayMaxOnlineUser();

    /**
     * 根据日期查询在线人数
     * @param day 日期
     * @return 在线人数结果集
     */
    List<OnlineUserEntity> findOnlineUserByDay(String day);
}
