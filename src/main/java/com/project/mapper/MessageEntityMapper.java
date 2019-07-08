package com.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.MessageEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MessageEntityMapper extends BaseMapper<MessageEntity> {
    /**
     * 添加系统消息
     * @param messageEntity 消息实体类
     * @return 影响行数
     */
    @Insert("insert into t_message values (null,#{personalid} ,#{messagetitle} ,#{content} ,now(),#{messagetype} )")
    public int addMessage(MessageEntity messageEntity);

    /**
     * 查询消息
     * @param id 用户id
     * @return 返回消息对象集合
     */
    @Select("select * from t_message where personalId=#{id} ")
    public List<MessageEntity> findMessageByPersonalid(Page<MessageEntity> page,@Param("id") int id);
}