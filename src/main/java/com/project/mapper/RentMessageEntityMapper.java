package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.PersonalEntity;
import com.project.entity.RentMessageEntity;
import com.project.entity.RentParkingEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName: RentMessageEntityMapper
 * @Description: 出租车位预定留言Mapper
 * @date 2019年06月04日 10:50
 */
public interface RentMessageEntityMapper extends BaseMapper<RentMessageEntity> {
    /**
     * 根据出租用户id查询，租车用户留言以及资料（连接个人用户表）
     *
     * @param page 分页对象,出租用户id
     * @param map  出租用户id,当前条数，页数
     * @return 返回出租预定留言车位分页对象
     */
    @Select(value = "SELECT r.id as id,r.personalId as personalId,r.rentId as rentId," +
            "r.rentMessage as rentMessage,r.rentPersonalId as rentPersonalId,p.id ," +
            "p.address as address,p.complainNum ,p.email ," +
            "p.idCard ,p.jobDescription ,p.relName ," +
            "p.tel ,p.tradeNum,p.userId ," +
            "p.username  from t_rentmessage AS r JOIN t_personal AS p " +
            "ON r.personalId=p.id WHERE r.rentPersonalId=#{map.rentPersonalId}")
    @Results({
            @Result(property = "personalEntity.id", column = "rentPersonalId"),
            @Result(property = "personalEntity.username", column = "p.username"),
            @Result(property = "personalEntity.relName", column = "p.relName"),
            @Result(property = "personalEntity.address", column = "p.address"),
            @Result(property = "personalEntity.tel", column = "p.tel"),
            @Result(property = "personalEntity.idCard", column = "p.idCard"),
            @Result(property = "personalEntity.jobDescription", column = "p.jobDescription"),
            @Result(property = "personalEntity.email", column = "p.email"),
            @Result(property = "personalEntity.complainNum", column = "p.complainNum"),
            @Result(property = "personalEntity.tradeNum", column = "p.tradeNum")
    })
    public List<RentMessageEntity> selectRentMessageInfo(Page<RentMessageEntity> page, @Param("map") Map<String, String> map);

    /**
     * 根据出租车位id删除对应留言表
     *
     * @param id 出租车位id
     * @return 返回0失败，非0成功
     */
    @Delete(value = "delete from t_rentmessage where rentId=#{rentId}")
    public int deleteRentMessageByRentId(@Param("rentId") int id);


    /**
     * 查询租赁用户预定信息（个人用户信息和出租留言）
     *
     * @param condition 查询集合
     * @param page      分页对象
     * @param rentId    出租车位id
     * @return 个人用户集合
     */
    @Select("SELECT r.rentMessage,p.id,p.complainNum,p.tradeNum,p.jobDescription,p.tel,p.username,r.id AS messageId FROM t_rentmessage AS r JOIN t_personal AS p ON p.id=r.personalId WHERE r.rentId=#{rentId}")
    @Results({
            @Result(property = "id", column = "messageId"),
            @Result(property = "personalEntity.id", column = "id"),
            @Result(property = "personalEntity.jobDescription", column = "jobDescription"),
            @Result(property = "personalEntity.tel", column = "tel"),
            @Result(property = "personalEntity.complainNum", column = "complainNum"),
            @Result(property = "personalEntity.tradeNum", column = "tradeNum"),
            @Result(property = "personalEntity.username", column = "username")
    })
    public List<RentMessageEntity> getRentParkingByAll(@Param("rentId") int rentId, Page<RentMessageEntity> page, Map<String, String> condition);

}