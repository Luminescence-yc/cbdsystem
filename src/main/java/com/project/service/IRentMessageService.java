package com.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.PersonalBean;
import com.project.bean.RentMessageBean;
import com.project.bean.RentParkingBean;
import com.project.entity.RentMessageEntity;

import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:出租预定留言
 * @Description:出租预定留言业务层接口
 * @date 2019年05月30日 12:34
 */
public interface IRentMessageService {
    /**
     * 增加出租预定留言，
     *
     * @param rentMessageEntity 出租预定留言实
     *                          /体类（包含出租方和租方个人用户id，和留言）
     * @return 返回0失败，非0成功
     */
    public int addRentMessage(RentMessageEntity rentMessageEntity);

    /**
     * 连接个人用户表，封装个人用户信息到Bean
     * 根据map值，动态查询出租车位预定留言信息，并且分页
     *
     * @param map 包含出租用户id，和分页页数，显示条数
     * @return 返回出租车位预定留言分页对象
     */
    public Page<RentMessageBean> showRentMessageInfo(Map<String, String> map);

    /**
     * 根据出租车位id删除对应留言表
     * @param rentId 出租车位id
     * @param rentPersonId 出租用户id
     * @param hirePersonId 租赁用户id
     * @param messageId 出租车位id
     * @return 返回0失败，非0成功
     */
    public int deleteRentMessageByRentId(int messageId,int rentId,int rentPersonId,int hirePersonId);
    /**
     * 查询租赁预定用户信息（个人用户、出租车位留言联表）处理招租
     *
     * @param page 当前页数和条数
     * @param size 当前页数和条数
     * @param rentId 出租车位id
     * @return 租赁预定用户分页对象
     * 要封装信息字段：职业、电话、信誉度、留言、真实姓名、身份证号码、
     */
    public IPage<RentMessageBean> getRentParkingByAll(int rentId, int page, int size);

}
