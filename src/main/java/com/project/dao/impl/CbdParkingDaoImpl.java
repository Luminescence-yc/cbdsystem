package com.project.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.ICbdParkingDao;
import com.project.entity.CbdParkingEntity;
import com.project.mapper.CbdparkingEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:
 * @Description:
 * @date 2019年05月31日 9:55
 */
@ClassType(describe = "平台车位")
@Repository
public class CbdParkingDaoImpl implements ICbdParkingDao {
    @Autowired
    private CbdparkingEntityMapper entityMapper;

    @Override
    public CbdParkingEntity findCbdParkingById(int id) throws Exception {
        CbdParkingEntity parkingEntity = entityMapper.selectById(id);
        return parkingEntity;
    }

    @Override
    public int findNoRentCbdParkingByExternalcontractId(int externalContractId, String status) throws Exception {
        int count = entityMapper.findNoRentCbdParkingByExternalcontractId(externalContractId, status);
        return count;
    }

    @Override
    public List<CbdParkingEntity> findCbdParkingByCondition(Page<CbdParkingEntity> page, Map<String, String> condition) throws Exception {
        List<CbdParkingEntity> list = entityMapper.findCbdParkingByCondition(page,condition);
        return list;
    }

    @Override
    public List<String> findAllAddress() {
        return entityMapper.findAllAddress();
    }

    @Override
    public List<String> findAreaByAddress(String address) throws Exception {
        return entityMapper.findAreaByAddress(address);
    }

    @Override
    public List<CbdParkingEntity> findCbdParkingByArea(Page<CbdParkingEntity> page, String address, String area) throws Exception {
        return entityMapper.findCbdParkingByArea(page, address, area);
    }

    @Override
    public int addParking(CbdParkingEntity cbdParkingEntity, List<Integer> parkingNumList) throws Exception {
        int result = entityMapper.addCbdParking(cbdParkingEntity,parkingNumList);
        return result;
    }

    @Override
    public int updateParking(List<Integer> list, int companyId, int companyContractId, String status) throws Exception {
        int result = entityMapper.updateParking(list, companyId, companyContractId, status);
        return result;
    }

    @Override
    public int updateParkingNo(int companyContractId, String status) throws Exception {
        int result = entityMapper.updateParkingNo(companyContractId, status);
        return result;
    }

    @Override
    public int updateParkingFinalDate(int companyContractId, String finalDate) throws Exception {
        int result = entityMapper.updateParkingFinalDate(companyContractId, finalDate);
        return result;
    }

    @Override
    public int delete(int id) throws Exception {
        int result = entityMapper.deleteById(id);
        return result;
    }

    /**
     * 通过车位地址查询空余车位
     * @param page    分页
     * @param address 车位地址
     * @return
     */
    @Override
    public List<CbdParkingEntity> findByAddress(Page<CbdParkingEntity> page, String address) throws Exception {
        return entityMapper.findCbdParkingByAddress(page,address);
    }


}
