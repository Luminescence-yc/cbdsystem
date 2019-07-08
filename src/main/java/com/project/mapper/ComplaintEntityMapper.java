package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.ComplaintEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ComplaintEntityMapper extends BaseMapper<ComplaintEntity> {

       @Select("select * from  t_complaint where status='受理中'")
       @Results(value={
               @Result(property = "personalComplaint",column = "complainPersonalId",
                       one= @One(select ="com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll")),
               @Result(property = "personalByUpholding",column = "complainedPersonalId",
                       one= @One(select ="com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll")),
               @Result(property = "sellhistoryEntity",column = "sellHistoryId",
                       one= @One(select ="com.project.mapper.SellHistoryEntityMapper.findSellByPersonalId")),
               @Result(property = "renthistoryEntity",column = "rentHistoryId",
                      one= @One(select ="com.project.mapper.RentHistoryEntityMapper.findByPersonalId"))
       })
       public List<ComplaintEntity> findComplaintByStatus(Page<ComplaintEntity> page);

       @Select("select  * from t_complaint where id=#{id}  ")
       @Results(value={
               @Result(property = "personalComplaint",column = "complainPersonalId",
                       one= @One(select ="com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll")),
               @Result(property = "personalByUpholding",column = "complainedPersonalId",
                       one= @One(select ="com.project.mapper.PersonalEntityMapper.getPersonalAndUserByAll")),
               @Result(property = "renthistoryEntity",column = "rentHistoryId",
                       one= @One(select ="com.project.mapper.RentHistoryEntityMapper.findByPersonalId"))
       })
       public ComplaintEntity findById(int id);

       @Insert("insert into t_complaint(complainPersonalId,complainedPersonalId,rentHistoryId,complaintReason,complaintDate,status)" +
               "values(#{personalComplaint.id},#{personalByUpholding.id},#{renthistoryEntity.id},#{complaintReason},#{complaintDate},#{status})")
       public void addComplaint(ComplaintEntity complaintEntity);
}