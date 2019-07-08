package com.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.SellParkingBean;

import java.util.Map;

/**
 * @author 陈云龙/朱骞
 * @version v1.1
 * @ClassName:出售车位
 * @Description:出售车位业务层接口
 * @date 2019年05月30日 12:44
 * <p>
 * 修改人：费宇
 * 修改日期：2019年05月31日 15:05
 * 修改内容：整理方法参数
 */
public interface ISellParkingService {
    /**
     * 1、主页买卖车位页面查询方法（分页） ；查询参数：发布时间、出售价格、地址或null
     *
     * @param condition 包含动态查询的值，和分页页数，显示条数
     * @return 返回出售车位分页对象
     * 返回对象参数：个人车位表：车位地址
     * 出售车位表：发布时间、价格、状态（固定为出售中）
     * 个人用户表：信誉度
     */
    public Page<SellParkingBean> showSellParkingEntityInfo(Map<String, String> condition);

    /**
     * 添加出售车位
     * 根据个人车位ID查询车位地址，
     * 出售车位表：车位ID、发布时间（默认当前日期）、出售价格、状态（默认出售中）
     *
     * @param sellParkingBean 出售车位实体类（个人用户id）
     * @return 返回0失败，非0成功
     */
    public int addSellParking(SellParkingBean sellParkingBean);

    /**
     * 查询卖车交易信息（出售车位、出售车位留言表联表）
     *
     * @param personalId 当前登录用户Id
     * @param current    当前页数
     * @param size       每页显示条数
     * @return 出售车位分页对象
     * 车位地址、出售价格、出售状态、预定人数（统计留言表数量）
     */
    public Page<SellParkingBean> getSellParkingByPage(int personalId, int current, int size);

    /**
     * 处理成交（删除信息）
     * 按车位ID 删除 出售信息表信息
     *
     * @param id 出售车位id
     * @return 1成功，0返回
     */
    public int deleteSellParkingById(int id);

    /**
     * 查询买车交易信息（个人车位、出售车位联表）
     *
     * @param personalId 当前登录用户Id
     * @param current    当前页数
     * @param size       每页显示条数
     * @return 买车交易分页集合
     * <p>
     * 车位地址、出售价格、出售状态
     */
    public Page<SellParkingBean> getBuyParkingByPage(int personalId, int current, int size);

    /**
     * 页面点击交易之后根据卖车车位id修改卖车车位状态为交易中
     *
     * @param id     卖车车位id
     * @param status 卖车车位状态
     * @return 1成功，0返回
     */
    public int updateSellStatusById(int id, String status);
}
