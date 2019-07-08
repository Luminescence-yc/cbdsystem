package com.project.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.SellHistoryEntity;

import java.util.List;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: ISellHistoryDao
 * @Description: 出售历史交易记录 持久接口
 * @date 2019年05月31日 11:27
 */
public interface ISellHistoryDao {

    /**
     * 根据当前用户ID  查询当前用户的所有出售历史交易记录
     * @param personalId 当前用户ID
     * @param page 分页对象
     * @return 出售历史记录 对象集合
     * @throws Exception
     */
    List<SellHistoryEntity> findSellHistoryByPersonalId(int personalId , Page<SellHistoryEntity> page) throws Exception;

    /**
     * 添加 个人出售历史记录
     * @param sellHistoryEntity 出售历史对象
     * @return 成功返回 1
     * @throws Exception
     */
    int addSellHistory(SellHistoryEntity sellHistoryEntity)throws Exception;

    /**
     * 根据ID查询 出售历史记录
     * @param id 用户出售历史记录
     * @return  出售历史对象
     * @throws Exception
     */
    SellHistoryEntity findBySellerId(int id)throws Exception;

    /**
     *  根据ID查询 购买历史记录
     * @param id 用户购买历史记录
     * @return 出售历史对象
     * @throws Exception
     */
    SellHistoryEntity findByBuyerId(int id)throws Exception;
}
