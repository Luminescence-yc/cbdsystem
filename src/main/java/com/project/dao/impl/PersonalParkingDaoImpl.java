package com.project.dao.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.IPersonalParkingDao;
import com.project.entity.PersonalParkingEntity;
import com.project.mapper.PersonalParkingEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:个人车位
 * @Description:个人车位持久层实现类
 * @date 2019年05月31日 14:46
 */
@ClassType(describe = "个人车位")
@Component
public class PersonalParkingDaoImpl implements IPersonalParkingDao {
    @Autowired
    private PersonalParkingEntityMapper personalParkingEntityMapper;

    /**
     * 通过车位id修改车位状态
     *
     * @param id     车位id
     * @param status 车位状态
     * @return 返回0失败，非0成功
     */
    @Override
    public int updatePersonalParkingByPersonalId(int id, String status) {
        return personalParkingEntityMapper.updatePersonalParkingByPersonalId(id, status);
    }

    /**
     * 通过用户id查找车位信息
     *
     * @param page       分页对象
     * @param personalId 用户id
     * @return 车位实体类
     */
    @Override
    public List<PersonalParkingEntity> findPersonalParkingByPersonalId(Page<PersonalParkingEntity> page, int personalId) {
        return personalParkingEntityMapper.findPersonalParkingByPersonalId(page, personalId);
    }

    /**
     * 根据个人车位id删除对应车位
     *
     * @param id 个人车位id
     * @return 返回0失败，非0成功
     */
    @Override
    public int deletePersonalParking(int id) {
        return personalParkingEntityMapper.deleteById(id);
    }

    /**
     * 添加个人车位
     *
     * @param personalParkingEntity 个人车位实体类
     * @return 返回0失败，非0成功
     */
    @Override
    public int addPersonalParking(PersonalParkingEntity personalParkingEntity) {
        return personalParkingEntityMapper.addPersonalParkingEntity(personalParkingEntity);
    }

    /**
     * 查询个人车位信息
     *
     * @param map  动态查询条件
     * @param page 分页对象
     * @return 返回个人车位对象集合
     */
    @Override
    public List<PersonalParkingEntity> findPersonalParkingInfo(Page<PersonalParkingEntity> page, Map<String, String> map) {
        return personalParkingEntityMapper.findPersonalParkingInfo(page, map);
    }

    @Override
    public List<PersonalParkingEntity> findPersonalParkingByAddress(String address) {
        return personalParkingEntityMapper.findPersonalParkingByAddress(address);
    }

    @Override
    public PersonalParkingEntity getPersonalParkingAndPersonalByPersonalParkingId(int personalParkingId) {
        return personalParkingEntityMapper.getPersonalParkingAndPersonalByPersonalParkingId(personalParkingId);
    }

    @Override
    public int updatePersonalParkingPersonalIdAndStatusByPersonalId(int personalId, int id, String status) {
        return personalParkingEntityMapper.updatePersonalParkingPersonalIdAndStatusByPersonalId(personalId, id, status);
    }
}
