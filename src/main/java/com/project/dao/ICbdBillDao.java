package com.project.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.CbdBillEntity;
import com.project.entity.CountEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: ICbdBillDao
 * @Description: 平台账单 持久接口
 * @date 2019年05月31日 0:03
 */
public interface ICbdBillDao {

    /**
     * 动态查询，平台账单
     *
     * @param page
     * @param condition 查询条件 起始时间，结束时间
     * @return 平台账单对象集合
     * @throws Exception
     */
    List<CbdBillEntity> findCbdBillInfoByCondition(Page<CbdBillEntity> page, Map<String, String> condition) throws Exception;

    /**
     * 添加超级管理员 账单
     * @param cbdBillEntity 平台账单对象
     * @return 添加成功返回 1
     * @throws Exception
     */
    int addAdminBill(CbdBillEntity cbdBillEntity)throws Exception;

    /**
     * 统计 超级管理员交易总次数、交易总支出、交易总收入
     //* @param companyId 超级管理员ID
     * @return 统计对象
     * @throws Exception
     */
    CountEntity countAdmin ()throws Exception;

}
