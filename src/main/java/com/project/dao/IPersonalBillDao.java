package com.project.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.CountEntity;
import com.project.entity.PersonalBillEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 刘 洪
 * @version v1.0
 * @ClassName: IPersonalBillDao
 * @Description: 个人账单 持久接口
 * @date 2019年05月30日 13:41
 */
public interface IPersonalBillDao {

    /**
     * 动态查询个人账单信息
     * @param condition 查询条件
     * @param page 分页对象
     * @return 个人账单对象集合
     * @throws Exception
     */
     List<PersonalBillEntity> findPersonalBillInfoByCondition(Page<PersonalBillEntity> page, Map<String, String> condition) throws Exception;

    /**
     * 添加用户 交易记录
     * @param personalBillEntity 用户对象
     * @return 添加成功返回 1
     * @throws Exception
     */
    int addUserBill(PersonalBillEntity personalBillEntity)throws Exception;

    /**
     * 统计 个人交易总次数、交易总支出、交易总收入
     * @param personalId 用户ID
     * @return 统计对象
     * @throws Exception
     */
    CountEntity countPersonal(int personalId)throws Exception;

}
