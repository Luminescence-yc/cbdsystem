package com.project.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.ContractEntity;
import com.project.entity.PersonalParkingEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:个人车位
 * @Description:个人车位持久层接口
 * @date 2019年05月31日 9:34
 */
public interface IPersonalParkingDao {
    /**
     * 通过车位id修改车位状态
     *
     * @param id     车位id
     * @param status 车位状态
     * @return 返回0失败，非0成功
     */
    public int updatePersonalParkingByPersonalId(int id, String status);

    /**
     * 通过用户id查找车位信息
     *
     * @param page       分页对象
     * @param personalId 用户id
     * @return 车位实体类
     */
    public List<PersonalParkingEntity> findPersonalParkingByPersonalId(Page<PersonalParkingEntity> page, int personalId);

    /**
     * 根据个人车位id删除对应车位
     *
     * @param id 个人车位id
     * @return 返回0失败，非0成功
     */
    public int deletePersonalParking(int id);

    /**
     * 添加个人车位
     *
     * @param personalParkingEntity 个人车位实体类
     * @return 返回0失败，非0成功
     */
    public int addPersonalParking(PersonalParkingEntity personalParkingEntity);

    /**
     * 查询个人车位信息
     *
     * @param map  动态查询条件
     * @param page 分页对象
     * @return 返回个人车位对象集合
     */
    public List<PersonalParkingEntity> findPersonalParkingInfo(Page<PersonalParkingEntity> page, Map<String, String> map);

    /**
     * 通过个人车位地址查找个人车位对象集合
     *
     * @param address 个人车位地址
     * @return 个人车位对象集合
     */
    public List<PersonalParkingEntity> findPersonalParkingByAddress(String address);

    /**
     * 根据个人车位ID 查询车位对象，同时封装个人用户对象信息
     *
     * @param personalParkingId 个人车位ID
     * @return 个人车位对象及个人用户信息
     */
    public PersonalParkingEntity getPersonalParkingAndPersonalByPersonalParkingId
    (int personalParkingId);

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
