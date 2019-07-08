package com.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CbdParkingBean;
import com.project.entity.CbdParkingEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName: ICbdparkingService
 * @Description: cbd车位管理业务接口
 * @date 2019年05月30日 10:00
 */
public interface ICbdParkingService {
    /**
     * 通过车位id查询车位
     *
     * @param id 车位id
     * @return 车位实体对象--返回Bean对象
     */
    public CbdParkingBean findById(int id);

    /**
     * 通过动态条件查询车位，如果传入null，查全部。
     *
     * @param condition 条件查询集合：车位区域、车位地址、车位状态、企业Id
     * @return 分页对象集合
     */
    public Page<CbdParkingBean> findCbdParkingByCondition(Map<String, String> condition);

    /**
     * 添加车位
     *
     * @param cbdParkingBean 车位Bean
     * @return 非0成功，0失败
     */
    public int addParking(CbdParkingBean cbdParkingBean);

    /**
     * 查找所有车位的地址集合
     * @return 地址集合
     */
    public List<String> findAllAddress();
    /**
     * 通过地址查找区域集合
     *
     * @param address 车位地址
     * @return 区域集合
     * @throws Exception
     */
    public List<String> findAreaByAddress(String address) throws Exception;

    /**
     * 通过地址和区域查找车位集合
     *
     * @param address 车位地址
     * @param area    区域编号
     * @param page    当前页数
     * @param size    每页条数
     * @return 分页对象集合
     * @throws Exception
     */
    public Page<CbdParkingBean> findCbdParkingByArea(String address, String area, int page, int size) throws Exception;


    /**
     * 通过车位地址查询空余车位
     * @param address 车位地址
     * @return
     */
    public Page<CbdParkingBean>findByAddress(String address,int page,int size) throws Exception;
}
