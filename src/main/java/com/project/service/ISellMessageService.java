package com.project.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.SellMessageBean;
import com.project.entity.SellMessageEntity;

import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:出售车位预定留言
 * @Description:出售车位预定留言业务层接口
 * @date 2019年05月30日 12:45
 */
public interface ISellMessageService {
    /**
     * 增加出售车位预定留言，
     *
     * @param sellMessageEntity 出售车位预定留言实体类（包含出租方和租方个人用户id，和留言）
     * @return 返回0失败，非0成功
     */
    public int addSellMessage(SellMessageEntity sellMessageEntity);

    /**
     * 根据出售车位id删除对应留言表
     *
     * @param id 出售车位id
     * @return 返回0失败，非0成功
     */
    public int deleteSellMessageByRentId(int id);

    /**
     * 查询卖车预定用户信息（个人用户、出售车位留言联表）卖车处理
     *
     * @param id   出售车位id
     * @param page 当前页数和条数
     * @param size 当前页数和条数
     * @return 租赁预定用户分页对象
     * 要封装信息字段：职业、电话、信誉度、留言、真实姓名、身份证号码、
     */
    public IPage<SellMessageBean> getSellParkingByAll(int id, int page, int size);
}
