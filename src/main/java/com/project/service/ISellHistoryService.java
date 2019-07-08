package com.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.SellHistoryBean;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: ISellHistoryService
 * @Description: 出售历史交易记录 业务接口
 * @date 2019年05月30日 13:34
 */
public interface ISellHistoryService {

    /**
     * 查询出售历史交易记录，并分页
     * @param page 当前页数
     * @param size 每页显示的条数
     * @param personalId 当前登录用户ID
     * @return 分页对象
     */
     Page<SellHistoryBean> showSellHistoryPageAllByPersonalId(int personalId,int page,int size);

}
