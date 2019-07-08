package com.project.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.RentHistoryEntity;

import java.util.List;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: IRentHistoryDao
 * @Description: 出租历史记录 持久接口
 * @date 2019年05月30日 14:52
 */
public interface IRentHistoryDao {

    /**
     * 查询，个人用户所有 出租历史记录
     * @param personalId  当前登录用户ID
     * @param page 分页对象
     * @return 出租历史记录 对象集合
     * @throws Exception
     */
     List<RentHistoryEntity> showRentOutHistoryByRentOutPersonalId(int personalId, Page<RentHistoryEntity> page) throws Exception;

    /**
     * 查询，个人用户所有 租赁历史记录
     * @param personalId  当前登录用户ID
     * @param page 分页对象
     * @return 出租历史记录 对象集合
     * @throws Exception
     */
    List<RentHistoryEntity> showLeaseHistoryByLeasePersonalId(int personalId,Page<RentHistoryEntity> page) throws Exception;
    /**
     * 添加 个人出租历史记录
     * @param rentHistoryEntity 出租历史对象
     * @return 成功返回 1
     * @throws Exception
     */
     int addRentHistory(RentHistoryEntity rentHistoryEntity,int rid,int hid)throws Exception;

    /**
     * 根据ID查询 出租历史记录
     * @param id 用户出租历史记录 ID
     * @return 出租对象
     * @throws Exception
     */
     RentHistoryEntity findByRentPersonalId(int id)throws Exception;

    /**
     * 根据ID查询 租赁历史记录
     * @param id 用户租赁历史记录 ID
     * @return 出租对象
     * @throws Exception
     */
     RentHistoryEntity findByHirePersonalId(int id)throws Exception;
}
