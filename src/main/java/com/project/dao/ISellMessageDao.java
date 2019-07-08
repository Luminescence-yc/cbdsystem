package com.project.dao;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.PersonalEntity;
import com.project.entity.SellMessageEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:出售车位预定留言
 * @Description:出售车位预定留言持久层接口
 * @date 2019年06月01日 10:28
 */
public interface ISellMessageDao {
    /**
     * 增加出售车位预定留言，
     *
     * @param sellMessageEntity 出售车位预定留言实体类（包含出租方和租方个人用户id，和留言）
     * @return 返回0失败，非0成功
     */
    public int addSellMessage(SellMessageEntity sellMessageEntity);

    /**
     * 当车位出售成功之后，删除当前对应的留言
     *
     * @param id 出售车位预定留言的id
     * @return 返回0失败，非0成功
     */
    public int deleteSellMessage(int id);

    /**
     * 根据出售车位id删除对应留言表
     *
     * @param id 出售车位id
     * @return 返回0失败，非0成功
     */
    public int deleteSellMessageBySellId(int id);

    /**
     * 根据出售车位id和留言用户id删除对应留言
     *
     * @param sellId     出售车位id
     * @param personalId 留言用户id
     * @return 返回0失败，非0成功
     */
    public int deleteSellMessageByPersonIdAndSellId(int sellId, int personalId);

    /**
     * 查询卖车预定用户信息（个人用户、出租车位留言联表）
     *
     * @param id        出租车位id
     * @param page      分页对象
     * @param condition 分页对象
     * @return 租赁预定用户分页对象
     * 要封装信息字段：职业、电话、信誉度、留言、真实姓名、身份证号码、
     * @throws Exception
     */
    public List<SellMessageEntity> getSellParkingByAll(int id, Page<SellMessageEntity> page, Map<String, String> condition) throws Exception;
}
