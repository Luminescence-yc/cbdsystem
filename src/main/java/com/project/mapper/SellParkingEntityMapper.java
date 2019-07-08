package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.SellParkingEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:出售车位
 * @Description:出售车位mapper
 * @date 2019年05月31日 9:23
 */

public interface SellParkingEntityMapper extends BaseMapper<SellParkingEntity> {
    /**
     * 动态查询
     *
     * @param condition 查询参数
     * @param page      分页参数
     * @return 键值对对象
     */
    @Select("<script> SELECT  sp.id as id ,sp.releaseTime as releaseTime," +
            " sp.sellPrice as sellPrice,sp.sellStatus as sellStatus," +
            " pp.id as pp_id,pp.propertyNum,pp.address as pp_address,pp.areaNum," +
            " pp.parkingNum,pp.propertyImage,pp.`status` as pp_status,pp.applyDate," +
            " p.id as p_id,p.username,p.relName,p.address as p_address,p.tel, " +
            " p.idCard,p.jobDescription,p.email,p.complainNum,p.tradeNum " +
            " FROM t_sellparking as sp LEFT JOIN t_personalparking as pp " +
            " on  sp.parkingId=pp.id LEFT JOIN t_personal as p " +
            " on pp.personalId=p.id <where>" +
            " and pp.personalId <![CDATA[ != ]]> #{condition.personalId}" +
            "<if test='condition.startTime!=null'> and releaseTime<![CDATA[ >= ]]> #{condition.startTime} </if>" +
            "<if test='condition.endTime!=null'> and releaseTime <![CDATA[ <= ]]> #{condition.endTime} </if>" +
            "<if test='condition.address!=null'> and pp.address like concat('%', #{condition.address},'%' )</if>" +
            "<if test='condition.startSellPrice!=null'> and sellPrice<![CDATA[ >= ]]> #{condition.startSellPrice} </if>" +
            "<if test='condition.endSellPrice!=null'> and sellPrice <![CDATA[ <= ]]>  #{condition.endSellPrice} </if>" +
            "<if test='condition.sellStatus!=null'> and sellStatus =  #{condition.sellStatus} </if>" +
            "</where ></script>")
    @Results({
            @Result(property = "personalParkingEntity.address", column = "pp_address"),
            @Result(property = "personalParkingEntity.id", column = "pp_id"),
            @Result(property = "personalParkingEntity.propertyNum", column = "propertyNum"),
            @Result(property = "personalParkingEntity.areaNum", column = "areaNum"),
            @Result(property = "personalParkingEntity.parkingNum", column = "parkingNum"),
            @Result(property = "personalParkingEntity.status", column = "pp_status"),
            @Result(property = "personalParkingEntity.applyDate", column = "applyDate"),
            @Result(property = "personalParkingEntity.propertyImage", column = "propertyImage"),
            @Result(property = "personalParkingEntity.personalEntity.id", column = "p_id"),
            @Result(property = "personalParkingEntity.personalId", column = "p_id"),
            @Result(property = "personalParkingEntity.personalEntity.username", column = "username"),
            @Result(property = "personalParkingEntity.personalEntity.relName", column = "relName"),
            @Result(property = "personalParkingEntity.personalEntity.address", column = "p_address"),
            @Result(property = "personalParkingEntity.personalEntity.tel", column = "tel"),
            @Result(property = "personalParkingEntity.personalEntity.idCard", column = "idCard"),
            @Result(property = "personalParkingEntity.personalEntity.jobDescription", column = "jobDescription"),
            @Result(property = "personalParkingEntity.personalEntity.email", column = "email"),
            @Result(property = "personalParkingEntity.personalEntity.complainNum", column = "complainNum"),
            @Result(property = "personalParkingEntity.personalEntity.tradeNum", column = "tradeNum")
    })
    public List<SellParkingEntity> selectSellParkingByCondition(@Param("condition") Map<String, String> condition, Page<SellParkingEntity> page);


    /**
     * 根据用户id查询卖车信息
     *
     * @param personalId 查询参数（用户id）
     * @param page       分页参数
     * @return 卖车记录集合
     */
    @Select("<script> SELECT " +
            " sp.id as id ,sp.releaseTime as releaseTime, sp.sellPrice as sellPrice,sp.sellStatus as sellStatus," +
            " sm.id as sm_id,sm.personalId as sm_personalId,sm.sellId as sellId ,sm.sellMessage as sellMessage," +
            " pp.id as pp_id,pp.personalId as pp_personalId,pp.propertyNum as propertyNum,pp.address as pp_address," +
            " pp.areanum as areanum,pp.parkingNum as parkingNum,pp.propertyImage as propertyImage," +
            " pp.`status` as pp_status,pp.applyDate as applyDate ,count(sm.id) as countMessage " +
            " FROM t_sellparking as sp  left JOIN t_sellmessage as sm on sp.id=sm.sellId " +
            " LEFT JOIN t_personalparking pp on sp.parkingId=pp.id GROUP BY sp.id" +
            " having pp_personalId = #{personalId} " +
            " </script>")
    @Results({
            @Result(property = "personalParkingEntity.address", column = "pp_address"),
            @Result(property = "personalParkingEntity.id", column = "pp_id"),
            @Result(property = "personalParkingEntity.propertyNum", column = "propertyNum"),
            @Result(property = "personalParkingEntity.areaNum", column = "areaNum"),
            @Result(property = "personalParkingEntity.parkingNum", column = "parkingNum"),
            @Result(property = "personalParkingEntity.status", column = "pp_status"),
            @Result(property = "personalParkingEntity.applyDate", column = "applyDate"),
            @Result(property = "personalParkingEntity.propertyImage", column = "propertyImage")
    })
    public List<SellParkingEntity> selectSellParkingByUserId(@Param("personalId") int personalId
            , Page<SellParkingEntity> page);

    /**
     * 根据用户id查询买车信息
     *
     * @param personalId 查询参数（用户id）
     * @param page       分页参数
     * @return 卖车记录集合
     */
    @Select("<script> SELECT " +
            " sp.id as id ,sp.releaseTime as releaseTime, sp.sellPrice as sellPrice,sp.sellStatus as sellStatus," +
            " sm.id as sm_id,sm.personalId as sm_personalId,sm.sellId as sellId ,sm.sellMessage as sellMessage," +
            " pp.id as pp_id,pp.personalId as pp_personalId,pp.propertyNum as propertyNum,pp.address as pp_address," +
            " pp.areanum as areanum,pp.parkingNum as parkingNum,pp.propertyImage as propertyImage," +
            " pp.`status` as pp_status,pp.applyDate as applyDate " +
            " FROM t_sellparking as sp  left JOIN t_sellmessage as sm on sp.id=sm.sellId " +
            " LEFT JOIN t_personalparking pp on sp.parkingId=pp.id GROUP BY sm.id" +
            " having sm_personalId = #{personalId} " +
            " </script>")
    @Results({
            @Result(property = "personalParkingEntity.address", column = "pp_address"),
            @Result(property = "personalParkingEntity.id", column = "pp_id"),
            @Result(property = "personalParkingEntity.propertyNum", column = "propertyNum"),
            @Result(property = "personalParkingEntity.areaNum", column = "areaNum"),
            @Result(property = "personalParkingEntity.parkingNum", column = "parkingNum"),
            @Result(property = "personalParkingEntity.status", column = "pp_status"),
            @Result(property = "personalParkingEntity.applyDate", column = "applyDate"),
            @Result(property = "personalParkingEntity.propertyImage", column = "propertyImage")
    })
    public List<SellParkingEntity> selectBuyParkingByUserId(@Param("personalId") int personalId
            , Page<SellParkingEntity> page);


    @Select("select * from t_sellparking where id=#{id} ")
    @Results({
            @Result(column = "parkingId", property = "personalParkingEntity", one = @One(select = "com.project.mapper.PersonalParkingEntityMapper.selectById"))
    })
    public SellParkingEntity selectById(int id);

    /**
     * 添加出售车位
     * 出售车位表：车位ID、发布时间（默认当前日期）、出售价格、状态（默认出售中）
     *
     * @param sellParkingEntity 出售车位实体类（出售的车位id）
     * @return 返回0失败，非0成功
     */
    @Insert("insert into t_sellparking values (null,#{parkingId} ,#{releaseTime} ,#{sellPrice} ,#{sellStatus})")
    public int addSellParking(SellParkingEntity sellParkingEntity);


    /**
     * 根据个人车位ID查询出售车位信息
     *
     * @param parkingId 个人车位ID
     * @return 出售车位对象
     */
    @Select("select  * from t_sellparking where  parkingId = #{parkingId}")
    public SellParkingEntity findSellParkingByParkingId(int parkingId);


    /**
     * 页面点击交易之后根据卖车车位id修改卖车车位状态为交易中
     *
     * @param id     卖车车位id
     * @param status 卖车车位状态
     * @return 1成功，0返回
     */
    @Update("update t_sellparking set sellStatus=#{status} where id=#{id}")
    public int updateSellStatusById(@Param("id") int id, @Param("status") String status);
}