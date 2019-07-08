package com.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.MessageBean;
import com.project.entity.MessageEntity;

/**
 * @author yangcheng
 * @ClassName:
 * @Description:
 * @date 2019年06月03日 10:16
 */
public interface IMessageService {
    /**
     * 添加消息
     * @param messageEntity 消息实体类
     * @return 影响行数
     */
    public int addMessage(MessageEntity messageEntity);

    /**
     * 查询消息
     * @param id 用户id
     * @return 返回消息对象集合
     */
    public IPage<MessageBean> findMssageByPersonalid(int id, int page, int size);
}
