package com.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CbdBillBean;
import com.project.entity.CountEntity;

import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: ICbdBillService
 * @Description: 超级管理员查看的后台账单 业务接口
 * @date 2019年05月30日 14:38
 */
public interface ICbdBillService {

    /**
     * 查询-超级管理员交易记录 动态查询并分页
     * @param condition 查询条件，如果查询所有，则条件为空。以起始终止时间为条件查询
     * @return 分页对象,返回对象需要封装为Bean对象
     */
     Page<CbdBillBean> showAdminPageByCondition(Map<String, String> condition);

    /**
     * 统计超级管理员的交易总次数、交易总支出、交易总收入
    // * @param countBean 投诉对象
     * @return 交易统计Bean,包含交易总笔数，支出金额统计，收入金额总计
     */
     CountEntity adminCount();

}
