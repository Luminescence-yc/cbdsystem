package com.project.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.annotation.ClassType;
import com.project.dao.IComplaintDao;
import com.project.entity.ComplaintEntity;

import com.project.mapper.ComplaintEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author 杨彪
 * @version v1.0
 * @ClassName IComplaintDao
 * @Description  投诉持久接口实现类
 * @date 2019年05月31日 09:45
 */
@ClassType(describe = "投诉信息")
@Component
public class ComplaintDaoImpl implements IComplaintDao {
    @Autowired
    private ComplaintEntityMapper complaintEntityMapper;

    @Override
    public List<ComplaintEntity> findComplaintByStatus(Page<ComplaintEntity> page) {
        List<ComplaintEntity> list=  complaintEntityMapper.findComplaintByStatus(page);
        return   list;
    }

    @Override
    public ComplaintEntity findById(int id) {
        ComplaintEntity complaintEntity= complaintEntityMapper.findById(id);
        return complaintEntity;
    }

    @Override
    public void updateStatus(int id, String status) {
        ComplaintEntity complaintEntity=new ComplaintEntity();
        complaintEntity.setId(id);
        complaintEntity.setStatus(status);
        complaintEntityMapper.updateById(complaintEntity);
    }

    @Override
    public void addComplaint(ComplaintEntity complaintEntity) {
        complaintEntityMapper.addComplaint(complaintEntity);
    }
}
