package com.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.RentHistoryBean;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: IRentHistoryService
 * @Description: 出租历史记录 业务接口
 * @date 2019年05月30日 14:35
 */
public interface IRentHistoryService {

    /**
     * 查询个人所有出租历史交易记录，并分页
     * @param personalId 当前登录用户ID
     * @param page 当前页数
     * @param size 每页显示的条数
     * @return 分页对象
     */
     Page<RentHistoryBean> showRentOutHistoryPageAllByRentOutPersonalId( int personalId,int page,int size);

    /**
     * 查询个人所有租赁历史交易记录，并分页
     * @param personalId 当前登录用户ID
     * @param page 当前页数
     * @param size 每页显示的条数
     * @return 分页对象
     */
    Page<RentHistoryBean> showLeaseHistoryPageAllByLeasePersonalId( int personalId,int page,int size);
}
