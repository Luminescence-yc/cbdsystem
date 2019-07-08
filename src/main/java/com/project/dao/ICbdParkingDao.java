package com.project.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.CbdParkingEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName: ICbdParkingDao
 * @Description: cbd车位管理持久接口
 * @date 2019年05月30日 10:17
 */
public interface ICbdParkingDao {
    /**
     * 通过车位id查询车位
     *
     * @param id 车位id
     * @return 车位实体对象--返回Bean对象
     * @throws Exception
     */
    public CbdParkingEntity findCbdParkingById(int id) throws Exception;

    /**
     * 根据外部合约Id查找出租的车位数量
     *
     * @param externalContractId 外部合约Id
     * @param status             车位状态
     * @return 车位数量
     * @throws Exception
     */
    public int findNoRentCbdParkingByExternalcontractId(int externalContractId, String status) throws Exception;

    /**
     * 通过动态条件查询车位，如果传入null，查全部。
     *
     * @param page      分页对象
     * @param condition 条件查询集合：车位区域和车位地址
     * @return 对象集合
     * @throws Exception
     */
    public List<CbdParkingEntity> findCbdParkingByCondition(Page<CbdParkingEntity> page, Map<String, String> condition) throws Exception;

    /**
     * 查找所有车位的地址集合
     * @return 地址集合
     */
    public List<String> findAllAddress();

    /**
     * 通过地址查找区域集合
     *
     * @param address 车位地址
     * @return
     * @throws Exception
     */
    public List<String> findAreaByAddress(String address) throws Exception;

    /**
     * 通过地址和区域查找车位集合
     *
     * @param page      分页对象
     * @param address 车位地址
     * @param area    区域编号
     * @return
     * @throws Exception
     */
    public List<CbdParkingEntity> findCbdParkingByArea(Page<CbdParkingEntity> page,String address, String area) throws Exception;

    /**
     * 添加车位
     *
     * @param cbdParkingEntity 车位实体对象
     * @param parkingNumList 车位编号集合
     * @return 返回1表示添加成功，返回0表示添加失败
     * @throws Exception
     */
    public int addParking(CbdParkingEntity cbdParkingEntity,List<Integer> parkingNumList) throws Exception;

    /**
     * 通过车位id修改状态为租赁中
     *
     * @param list              车位id集合
     * @param companyId         企业id
     * @param companyContractId 租户合约id
     * @param status            车位状态
     * @return 非0成功，0失败
     * @throws Exception
     */
    public int updateParking(List<Integer> list, int companyId, int companyContractId, String status) throws Exception;

    /**
     * 通过租户合同id修改状态为未租赁
     *
     * @param companyContractId 租户合约id
     * @param status            车位状态
     * @return 非0成功，0失败
     * @throws Exception
     */
    public int updateParkingNo(int companyContractId, String status) throws Exception;

    /**
     * 通过租户合同id修改最长可租日期
     *
     * @param companyContractId 租户合约id
     * @param finalDate         最长可租日期
     * @return 非0成功，0失败
     * @throws Exception
     */
    public int updateParkingFinalDate(int companyContractId, String finalDate) throws Exception;

    /**
     * 通过车位id删除车位
     *
     * @param id 车位id
     * @return 非0成功，0失败
     * @throws Exception
     */
    public int delete(int id) throws Exception;

    /**
     * 通过车位地址查询空余车位
     * @param page 分页
     * @param address 车位地址
     * @return
     */
    public List<CbdParkingEntity>findByAddress(Page<CbdParkingEntity> page,String address) throws Exception;
}
