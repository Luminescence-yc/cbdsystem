package com.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.RentParkingBean;
import com.project.entity.RentParkingEntity;

import java.util.Map;

/**
 * @author 陈云龙/朱骞
 * @version v1.0
 * @ClassName:出租车位
 * @Description:出租车位业务层接口
 * @date 2019年05月30日 11:13
 */
public interface IRentParkingService {
    /**
     * 连接个人用户表，计算信用度封装进Bean
     * 根据map值，动态查询出租车位信息，并且分页
     *
     * @param map 包含动态查询的值，和分页页数，显示条数
     * @return 返回出租车位分页对象
     */
    public Page<RentParkingBean> showRentParkingInfo(Map<String, String> map);

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
     * @param page 当前页数
     * @param size 当前条数
     * @return 招租车位分页对象
     * @throws Exception 异常处理
     */
    public IPage<RentParkingBean> getAllRentParkingInformation(int id, int page, int size);

    /**
     * 处理招租（删除信息）
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
     * @param page 当前页数
     * @param size 当前条数
     * @return 租赁交易集合分页
     * @throws Exception 异常处理
     */
    public IPage<RentParkingBean> getAllRent(int id, int page, int size);
}
