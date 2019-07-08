package com.project.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.SellParkingBean;
import com.project.entity.SellParkingEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:出售车位
 * @Description:出售车位持久层接口
 * @date 2019年05月31日 9:23
 */

public interface ISellParkingDao {
    /**
     * 根据map值，动态查询出售车位信息
     *
     * @param condition 包含动态查询的值
     * @param page      分页对象
     * @return 返回出售车位对象集合
     * @throws Exception 异常处理
     *                   返回对象参数：个人车位表：车位地址
     *                   出售车位表：发布时间、价格、状态（固定为出售中）
     *                   个人用户表：信誉度
     */
    public List<SellParkingEntity> findSellParkingEntityInfo(Map<String, String> condition
            , Page<SellParkingEntity> page) throws Exception;

    /**
     * 添加出售车位
     * 出售车位表：车位ID、发布时间（默认当前日期）、出售价格、状态（默认出售中）
     *
     * @param sellParkingEntity 出售车位实体类（出售的车位id）
     * @return 返回0失败，非0成功
     * @throws Exception 异常处理
     */
    public int addSellParking(SellParkingEntity sellParkingEntity) throws Exception;

    /**
     * 查询卖车交易信息（出售车位、出售车位留言表联表）
     *
     * @param personalId 当前登录用户Id
     * @param page       分页对象
     * @return 出售车位分页对象
     * 车位地址、出售价格、出售状态、预定人数（统计留言表数量）
     * @throws Exception 异常处理
     */
    public List<SellParkingEntity> getSellParkingByPersonalId(int personalId
            , Page<SellParkingEntity> page) throws Exception;

    /**
     * 处理成交（删除信息）
     * 按车位ID 删除 出售信息表信息
     *
     * @param id 出售车位id
     * @return 1成功，0返回
     * @throws Exception 异常处理
     */
    public int deleteSellParkingById(int id) throws Exception;

    /**
     * 查询买车交易信息（个人车位、出售车位联表）
     *
     * @param personalId 当前用户Id
     * @param page       分页对象
     * @return 买车交易分页集合
     * 车位地址、出售价格、出售状态
     * @throws Exception 异常处理
     */
    public List<SellParkingEntity> getBuyParkingByPersonalId(int personalId
            , Page<SellParkingEntity> page) throws Exception;

    /**
     * 根据个人车位ID查询出售车位信息
     *
     * @param parkingId 个人车位ID
     * @return 出售车位对象
     * @throws Exception 异常处理
     */
    public SellParkingEntity findSellParkingByParkingId(int parkingId) throws Exception;

    /**
     * 页面点击交易之后根据卖车车位id修改卖车车位状态为交易中
     *
     * @param id     卖车车位id
     * @param status 卖车车位状态
     * @return 1成功，0返回
     * @throws Exception 抛出异常
     */
    public int updateSellStatusById(int id, String status) throws Exception;
}
