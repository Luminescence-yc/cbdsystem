package com.project.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.ComplaintBean;
import com.project.entity.ComplaintEntity;
import com.project.entity.ContractEntity;
import com.project.entity.ResponseTimeEntity;

import java.util.List;

/**
 * @author 杨彪
 * @version v1.0
 * @ClassName IComplaintDao
 * @Description  投诉持久接口
 * @date 2019年05月31日 09:45
 */
public interface IComplaintDao {
    /**
     * 分页查询投诉信息
     * Dao层要连表查询用户信息（名字）
     * @return 投诉信息的分页对象集合
     */
    public List<ComplaintEntity> findComplaintByStatus(Page<ComplaintEntity> page);

    /**
     *  按投诉ID查询对应的事件对象，投诉方对象、被投诉方对象（都是查询个人用户表）
     *
     * @param id 投诉受理实体类id
     * @return 投诉信息bean 封装信息比较多
     */
    public  ComplaintEntity findById(int id);

    /**
     * 判断status 如果为投诉生效  根据投诉受理id修改投诉状态：“受理”为：“投诉生效”，修改个人用户被投诉次数+1；并添加给消息实体类
     * status 如果为投诉无效  根据投诉受理id修改投诉状态：“受理”为：“投诉无效”；并添加给消息实体类
     * @param id  投诉受理实体类id
     * @param status 投诉受理状态
     */
    public void updateStatus(int id, String status);

    /**
     * 添加投诉
     * @param complaintEntity 投诉信息对象
     * @return
     */
    public void addComplaint(ComplaintEntity complaintEntity);
}
