package com.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.PersonalBillBean;
import com.project.entity.CountEntity;

import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: IPersonalBillService
 * @Description:  个人账单 业务接口
 * @date 2019年05月30日 11:36
 */
public interface IPersonalBillService {

    /**
     * 动态查询，当查询条件为null时，显示所有个人账单交易记录，并分页
     *
     * @param condition 查询条件
     * @return 分页对象
     */
     Page<PersonalBillBean> showPersonalPageByCondition(Map<String, String> condition);

    /**
     * 统计个人账单的交易总次数、交易总支出、交易总收入
     * @return 交易统计Bean
     */
    CountEntity personalCount(int personalId);

}

