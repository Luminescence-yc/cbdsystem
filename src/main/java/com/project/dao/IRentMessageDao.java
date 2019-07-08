package com.project.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.PersonalEntity;
import com.project.entity.RentMessageEntity;
import com.project.entity.RentParkingEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 陈云龙
 * @version v1.0
 * @ClassName:租车预定留言
 * @Description:租车预定留言持久层接口
 * @date 2019年05月31日 17:45
 */
public interface IRentMessageDao {
    /**
     * 增加出租预定留言，
     *
     * @param rentMessageEntity 出租预定留言实
     *                          /体类（包含出租方和租方个人用户id，和留言）
     * @return 返回0失败，非0成功
     */
    public int addRentMessage(RentMessageEntity rentMessageEntity);

    /**
     * 当车位出租成功之后，删除当前对应的留言
     *
     * @param id 出租预定留言的id
     * @return 返回0失败，非0成功
     */
    public int deleteRentMessageById(int id);

    /**
     * 根据出租用户id查询，租车用户留言以及资料（连接个人用户表）
     *
     * @param page 分页对象
     * @param map  出租用户id,页数，条数
     * @return 返回出租预定留言车位分页对象
     */
    public List<RentMessageEntity> showRentMessageInfo(Page<RentMessageEntity> page, Map<String, String> map);

    /**
     * 根据出租车位id删除对应留言表
     *
     * @param id 出租车位id和留言id
     * @return 返回0失败，非0成功
     */
    public int deleteRentMessageByRentId(int id);

    /**
     * 查询租赁预定用户信息（个人用户、出租车位留言联表）
     *
     * @param condition 查询参数
     * @param page      分页对象
     * @param rentId    出租车位id
     * @return 租赁预定用户分页对象
     * 要封装信息字段：职业、电话、信誉度、留言、真实姓名、身份证号码、
     * @throws Exception
     */
    public List<RentMessageEntity> getRentParkingByAll(int rentId, Page<RentMessageEntity> page, Map<String, String> condition) throws Exception;

}
