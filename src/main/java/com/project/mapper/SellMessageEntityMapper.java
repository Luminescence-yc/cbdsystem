package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.SellMessageEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:出售车位预定留言
 * @Description:出售车位预定留言mapper
 * @date 2019年06月01日 10:30
 */
public interface SellMessageEntityMapper extends BaseMapper<SellMessageEntity> {
    /**
     * 根据出售车位id删除对应留言表
     *
     * @param id 出售车位id
     * @return 返回0失败，非0成功
     */
    @Delete(value = "delete from t_sellmessage where sellId=#{id}")
    public int deleteSellMessageBySellId(@Param("id") int id);

    /**
     * 根据出售车位id和留言用户id删除对应留言
     *
     * @param sellId     出售车位id
     * @param personalId 留言用户id
     * @return 返回0失败，非0成功
     */
    @Delete(value = "delete from t_sellmessage where sellId=#{sellId} and personalId=#{personalId}")
    public int deleteSellMessageByPersonIdAndSellId(@Param("sellId") int sellId, @Param("personalId") int personalId);

    /**
     * 查询出售用户预定信息（个人用户信息和出售留言）
     *
     * @param id        出售车位id
     * @param page      分页对象
     * @param condition 分页集合
     * @return 个人用户集合
     * 出售车位id查所有留言，留言id 查询对应个人用户
     */
    @Select("SELECT " +
            "s.sellMessage," +
            "p.relName," +
            "p.id," +
            "p.complainNum," +
            "p.tradeNum," +
            "p.jobDescription," +
            "p.tel," +
            "p.idCard," +
            "p.username," +
            "s.id AS messageId FROM " +
            "t_sellmessage AS s LEFT JOIN " +
            "t_personal AS p ON s.personalId=p.id " +
            "WHERE s.sellId=#{id}")
    @Results({
            @Result(property = "id", column = "messageId"),
            @Result(property = "personalEntity.id", column = "id"),
            @Result(property = "personalEntity.jobDescription", column = "jobDescription"),
            @Result(property = "personalEntity.tel", column = "tel"),
            @Result(property = "personalEntity.complainNum", column = "complainNum"),
            @Result(property = "personalEntity.tradeNum", column = "tradeNum"),
            @Result(property = "personalEntity.username", column = "username"),
            @Result(property = "personalEntity.relName", column = "relName"),
            @Result(property = "personalEntity.idCard", column = "idCard")
    })

    public List<SellMessageEntity> getSellParkingByAll(@Param("id") int id, Page<SellMessageEntity> page, Map<String, String> condition);
    @Insert("insert into t_sellMessage values (null,#{personalId} ,#{sellId} ,#{sellMessage} )")
    public int addSellMessage(SellMessageEntity SellMessageEntity);
}