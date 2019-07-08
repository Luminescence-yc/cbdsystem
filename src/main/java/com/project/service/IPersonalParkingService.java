package com.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.PersonalParkingBean;
import com.project.entity.PersonalParkingEntity;

import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:个人车位
 * @Description:个人车位业务层接口
 * @date 2019年05月30日 12:50
 */
public interface IPersonalParkingService {
    /**
     * 查询个人车位信息
     *
     * @param map 查询条件，以及分页条数，页数
     * @return 返回个人车位分页对象
     */
    public IPage<PersonalParkingBean> showPersonalParkingInfo(Map<String, String> map);

    /**
     * 根据个人用户Id查询个人车位信息
     *
     * @param map        分页条数，页数
     * @param personalId 个人用户Id
     * @return 返回个人车位分页对象
     */
    public IPage<PersonalParkingBean> showPersonalParkingInfoPersonalId(Map<String, String> map, int personalId);

    /**
     * 添加个人车位
     *
     * @param personalParkingEntity 个人车位实体类
     * @return 返回0失败，非0成功
     */
    public int addPersonalParking(PersonalParkingEntity personalParkingEntity);

    /**
     * 根据个人车位id修改对应数据的车位状态，前台点击出售，出租或者下架都需要修改状态
     *
     * @param id     个人车位id
     * @param status 车位状态
     * @return 返回0失败，非0成功
     */
    public int updatePersonalParkingStatus(int id, String status);


    /**
     * 根据个人车位ID 查询车位对象，同时封装个人用户对象信息
     *
     * @param personalParkingId 个人车位ID
     * @return 个人车位对象及个人用户信息Bean对象
     */
    public PersonalParkingBean getPersonalParkingAndPersonalByPersonalParkingId
    (int personalParkingId);

    /**
     * 根据车位id下架出租车位，删除出租留言表和出租信息表
     *
     * @param parkingId 车位id
     * @return 返回0失败，非0成功
     */
    public int shelvesRentParkingByParkingId(int parkingId);

    /**
     * 根据车位id下架出售车位，删除出售留言表和出售信息表
     *
     * @param parkingId 车位id
     * @return 返回0失败，非0成功
     */
    public int shelvesSellParkingByParkingId(int parkingId);

    /**
     * 买卖车交易之后把个人车位的用户id修改为买车人的id，把状态修改为未发布
     *
     * @param personalId 卖车人id
     * @param id         买车人id
     * @param status     状态
     * @return 返回0失败，非0成功
     */
    public int updatePersonalParkingPersonalIdAndStatusByPersonalId(int personalId, int id, String status);

}
