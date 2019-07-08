package com.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.PersonalParkingEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:个人车位
 * @Description:个人车位Mapper
 * @date 2019年05月31日 17：00
 */
public interface PersonalParkingEntityMapper extends BaseMapper<PersonalParkingEntity> {
    /**
     * 查询个人车位信息
     *
     * @param map  查询条件
     * @param page 分页对象
     * @return 返回个人车位对象集合
     */
    @Select(value = "<script> SELECT * FROM t_personalparking parking join t_personal personal " +
            "on parking.personalId=personal.id " +
            "where 1=1" +
            "<if test='map.username!=null'> and personal.username like concat('%',#{map.username},'%') </if>" +
            "<if test='map.status!=null'> and parking.status = #{map.status} </if>" +
            "<if test='map.startApplyDate!=null'> and parking.applyDate &gt;= #{map.startApplyDate} </if>" +
            "<if test='map.endApplyDate!=null'> and parking.applyDate &lt;= #{map.endApplyDate} </if>" +
            "</script>")
    @Results({
            @Result(property = "personalEntity", column = "personalId",
                    one = @One(select = "com.project.mapper.PersonalEntityMapper.selectById"))//查询用户方法
    })
    public List<PersonalParkingEntity> findPersonalParkingInfo(@Param("page") Page<PersonalParkingEntity> page, @Param("map") Map<String, String> map);

    /**
     * 通过车位id修改车位状态
     *
     * @param id     车位id
     * @param status 车位状态
     * @return 返回0失败，非0成功
     */
    @Update(value = "UPDATE t_personalparking SET STATUS=#{status} WHERE id=#{id}")
    public int updatePersonalParkingByPersonalId(@Param("id") int id, @Param("status") String status);

    /**
     * 通过用户id查找车位信息
     *
     * @param page       分页对象
     * @param personalId 用户id
     * @return 车位实体类
     */
    @Select(value = "SELECT * FROM t_personalparking WHERE personalId=#{personalId} ")
    public List<PersonalParkingEntity> findPersonalParkingByPersonalId(@Param("page") Page<PersonalParkingEntity> page, @Param("personalId") int personalId);

    /**
     * 通过个人车位地址查找个人车位对象集合
     *
     * @param address 个人车位地址
     * @return 个人车位对象集合
     */
    @Select(value = "SELECT * FROM t_personalparking WHERE address LIKE #{address}")
    public List<PersonalParkingEntity> findPersonalParkingByAddress(String address);

    /**
     * 根据个人车位ID 查询车位对象，同时封装个人用户对象信息
     *
     * @param personalParkingId 个人车位ID
     * @return 个人车位对象及个人用户信息
     */
    @Select("SELECT pp.id as pp_id,pp.propertyNum,pp.address,pp.areaNum," +
            " pp.parkingNum,pp.propertyImage,pp.`status`,pp.applyDate," +
            " p.id as p_id,p.username,p.relName,p.address as p_address," +
            " p.tel,p.idCard,p.jobDescription,p.email,p.complainNum,p.tradeNum " +
            " FROM t_personalparking as pp LEFT JOIN t_personal p " +
            "on pp.personalId=p.id where pp.id=#{personalParkingId}")
    @Results({
            @Result(property = "id", column = "pp_id"),
            @Result(property = "personalEntity.id", column = "p_id"),
            @Result(property = "personalId", column = "p_id"),
            @Result(property = "personalEntity.username", column = "username"),
            @Result(property = "personalEntity.relName", column = "relName"),
            @Result(property = "personalEntity.address", column = "p_address"),
            @Result(property = "personalEntity.tel", column = "tel"),
            @Result(property = "personalEntity.idCard", column = "idCard"),
            @Result(property = "personalEntity.jobDescription", column = "jobDescription"),
            @Result(property = "personalEntity.email", column = "email"),
            @Result(property = "personalEntity.complainNum", column = "complainNum"),
            @Result(property = "personalEntity.tradeNum", column = "tradeNum")
    })
    public PersonalParkingEntity getPersonalParkingAndPersonalByPersonalParkingId
    (@Param("personalParkingId") int personalParkingId);


    @Insert("insert into t_personalparking values (null,#{personalId} ,#{propertyNum} ,#{address} ,#{areaNum} ,#{parkingNum} ,#{propertyImage} ,#{status} ,curdate())")
    public int addPersonalParkingEntity(PersonalParkingEntity personalParkingEntity);

    /**
     *  买卖车交易之后把个人车位的用户id修改为买车人的id，把状态修改为未发布
     * @param personalId 卖车人id
     * @param id  买车人id
     * @param status 状态
     * @return 返回0失败，非0成功
     */
    @Update("UPDATE t_personalparking SET personalId=#{id} ,STATUS=#{status}  WHERE personalId=#{personalId} ")
    public int updatePersonalParkingPersonalIdAndStatusByPersonalId
    (@Param("personalId") int personalId,@Param("id") int id,@Param("status") String status);
}