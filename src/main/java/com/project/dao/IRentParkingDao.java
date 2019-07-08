package com.project.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.PersonalEntity;
import com.project.entity.RentParkingEntity;
import com.project.entity.SellParkingEntity;
import org.apache.ibatis.annotations.Select;


import java.util.List;
import java.util.Map;


/**
 * @author 朱 骞/陈云龙
 * @version v1.0
 * @ClassName: IRentParkingDao
 * @Description: 出租车位信息持久层接口
 * @date 2019年05月31日 15:06
 */
public interface IRentParkingDao {
    /**
     * 连接个人用户表,
     * 根据map值，动态查询出租车位信息，并且分页
     *
     * @param map  包含动态查询的值，和分页页数，显示条数
     * @param page 分页对象
     * @return 返回出租车位分页对象
     */
    public List<RentParkingEntity> showRentParkingInfo(Page<RentParkingEntity> page, Map<String, String> map);

    /**
     * 添加出租车位
     *
     * @param rentParkingEntity 出租车位实体类
     * @return 返回0失败，非0成功
     */
    public int addRentParking(RentParkingEntity rentParkingEntity);

    /**
     * 查询招租车位信息（出租车位、出租车位留言联表）
     *
     * @param id   当前用户Id
     * @param page 当前页数和条数
     * @return 招租车位分页对象
     * @throws Exception 异常处理
     */
    public List<RentParkingEntity> getAllRentParkingInformation(int id, Page<RentParkingEntity> page);


    /**
     * 成交（删除出租车租车位信息）
     *
     * @param id 出租车为Id
     * @return 1成功，0返回
     * @throws Exception 异常处理
     */
    public int deleteRentParking(int id);

    /**
     * 查询租赁交易信息（出租车位信息、个人用户）
     * 封装字段：出租车位信息、租界开始时间、租界结束时间、预定人数
     *
     * @param id   当前登录用户id
     * @param page 当前页数和条数
     * @return 租赁交易集合分页
     * @throws Exception 异常处理
     */
    public List<RentParkingEntity> getAllRent(int id, Page<RentParkingEntity> page);

    /**
     * 统计出租留言总数
     *
     * @param id 出租留言id
     * @return 0失败，1成功
     */
    public int countRentMessage(int id);


    /**
     * 根据个人车位ID查询出租车位信息
     *
     * @param queryWrapper 个人车位ID
     * @return 出租车位对象
     * @throws Exception 异常处理
     */
    public RentParkingEntity findRentParkingByParkingId(QueryWrapper<RentParkingEntity> queryWrapper) throws Exception;

    /**
     * 根据出租车位ID查询出租车位信息
     *
     * @param id 出租车位Id
     * @return 出租车位对象
     * @throws Exception 异常处理
     */
    public RentParkingEntity findRentParkingById(int id) throws Exception;
}
