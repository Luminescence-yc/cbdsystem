package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.RentParkingEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 朱 骞/陈云龙
 * @version v1.0
 * @ClassName: PersonalEntityMapper
 * @Description: 出租车位Mapper
 * @date 2019年05月31日 9:25
 */
public interface RentParkingEntityMapper extends BaseMapper<RentParkingEntity> {
    /**
     * 连接个人用户表,
     * 根据map值，动态查询出租车位信息，并且分页
     *
     * @param map  包含动态查询的值，和分页页数，显示条数
     * @param page 分页对象
     * @return 返回出租车位分页对象
     */
    @Select(value = "<script>SELECT rp.id AS id,rp.startTime AS startTime," +
            "rp.endTime AS endTime,rp.rentPrice AS rentPrice,pp.id AS pp_id," +
            "pp.propertyNum,pp.address AS pp_address,pp.areaNum,pp.parkingNum," +
            "pp.propertyImage,pp.STATUS AS pp_status,pp.applyDate,p.id AS p_id," +
            "p.username,p.relName,p.address AS p_address,p.tel,p.idCard,p.jobDescription," +
            "p.email,p.complainNum,p.tradeNum  " +
            " FROM t_rentparking AS rp LEFT JOIN t_personalparking AS pp ON  rp.parkingId=pp.id" +
            " LEFT JOIN t_personal AS p ON pp.personalId=p.id where 1=1 " +
            "and pp.personalId <![CDATA[ != ]]> #{map.personalId}" +
            "<if test='map.beginStartTime!=null'> and startTime &gt;= #{map.beginStartTime} </if>" +
            "<if test='map.overStartTime!=null'> and startTime &lt;= #{map.overStartTime} </if>" +
            "<if test='map.beginEndTime!=null'> and endTime &gt;= #{map.beginEndTime} </if>" +
            "<if test='map.overEndTime!=null'> and endTime &lt;= #{map.overEndTime} </if>" +
            "<if test='map.address!=null'> and pp.address like #{map.address} </if>" +
            "<if test='map.startRentPrice!=null'> and rentPrice &gt;= #{map.startRentPrice} </if>" +
            "<if test='map.endRentPrice!=null'> and rentPrice &lt;= #{map.endRentPrice} </if>" +
            "</script>")
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
    public List<RentParkingEntity> selectRentParkingInfo(Page<RentParkingEntity> page, @Param("map") Map<String, String> map);

    /**
     * 查询出租车位集合
     *
     * @param id   出租车位Id
     * @param page 当前页数和条数
     * @return 集合
     */
    @Select(" SELECT * FROM t_personal AS p" +
            "           LEFT JOIN t_personalparking AS pp ON p.id = pp.personalId" +
            "             LEFT JOIN  t_rentparking AS rp ON pp.id=rp.parkingId" +
            "           JOIN t_rentmessage AS rm ON rp.id=rm.rentId WHERE p.id=#{id}")
    @Results({
            @Result(property = "personalEntity.username", column = "username"),
            @Result(property = "personalEntity.jobDescription", column = "jobDescription"),
            @Result(property = "personalEntity.complainNum", column = "complainNum"),
            @Result(property = "personalEntity.tradeNum", column = "tradeNum"),
            @Result(property = "personalParkingEntity.address", column = "address"),
            @Result(property = "personalParkingEntity.areaNum", column = "areaNum"),
            @Result(property = "personalParkingEntity.parkingNum", column = "parkingNum"),
            @Result(property = "startTime", column = "startTime"),
            @Result(property = "endTime", column = "endTime"),
            @Result(property = "rentPrice", column = "rentPrice")
    })
    public List<RentParkingEntity> getAllRent(@Param("id") int id, Page<RentParkingEntity> page);

    /**
     * 查询出租车位
     *
     * @param id 出租车位id
     * @return 1成功，0失败
     */
    @Select("SELECT * FROM t_rentparking WHERE id=#{id}")
    @Results({
            @Result(column = "parkingId", property = "personalParkingEntity", one = @One(select = "com.project.mapper.PersonalParkingEntityMapper.selectById"))
    })
    public RentParkingEntity findById(@Param("id") int id);

    /**
     * 查询招租车位信息（出租车位、个人车位联表）
     *
     * @param id   当前用户Id
     * @param page 当前页数和条数
     * @return 招租车位分页对象
     * @throws Exception 异常处理
     */
    @Select("SELECT r.* FROM t_rentparking AS r " +
            "JOIN t_personalparking AS p ON r.parkingId=p.id WHERE p.personalId=#{id}")
    @Results({
            @Result(column = "parkingId", property = "personalParkingEntity", one = @One(select = "com.project.mapper.PersonalParkingEntityMapper.selectById"))

    })
    public List<RentParkingEntity> getAllRentParkingInformation(@Param("id") int id, Page<RentParkingEntity> page);

    /**
     * 统计出租留言总数
     *
     * @param id 出租留言id
     * @return 0失败，1成功
     */
    @Select("SELECT COUNT(r.rentMessage)FROM t_rentmessage AS r WHERE rentId=#{id}")
    public int countRentMessage(@Param("id") int id);
}