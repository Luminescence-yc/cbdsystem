package com.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.ComplaintBean;
import com.project.bean.PersonalBean;
import com.project.bean.RentHistoryBean;
import com.project.bean.SellHistoryBean;
import com.project.dao.*;
import com.project.entity.*;
import com.project.service.IComplaintService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨彪
 * @ClassName: 业务层实现类
 * @Description:
 * @date 2019年05月31日 16:15
 */
@Service
public class ComplaintServiceImpl implements IComplaintService {
    @Autowired
    private IComplaintDao complaintDao;
    @Autowired
    private IMessageDao messageDao;
    @Autowired
    private IPersonalDao personalDao;
    @Autowired
    private ISellHistoryDao sellHistoryDao;
    @Autowired
    private IRentHistoryDao rentHistoryDao;

    @Override
    public IPage<ComplaintBean> findComplaintByStatus(int page, int size) {
        Page<ComplaintEntity> pageinfo = new Page<ComplaintEntity>(page, size);
        Page<ComplaintBean> beanPage = new Page<ComplaintBean>();

        Page<ComplaintEntity> pageEntity = pageinfo.setRecords(complaintDao.findComplaintByStatus(pageinfo));
        List<ComplaintEntity> records = pageEntity.getRecords();
        List<ComplaintBean> list = new ArrayList<>();
        BeanUtils.copyProperties(pageEntity, beanPage);
        for (int i = 0; i < records.size(); i++) {
            ComplaintBean complaintBean = new ComplaintBean();
            complaintBean.setPersonalComplainantBean(new PersonalBean());
            complaintBean.setPersonalByUpholdingBean(new PersonalBean());
            complaintBean.setSellhistoryBean(new SellHistoryBean());
            complaintBean.setRenthistoryBean(new RentHistoryBean());
            BeanUtils.copyProperties(records.get(i), complaintBean);
            if (records.get(i).getPersonalComplaint() != null){
                BeanUtils.copyProperties(records.get(i).getPersonalComplaint(), complaintBean.getPersonalComplainantBean());
            }
            if (records.get(i).getPersonalByUpholding() != null){
                BeanUtils.copyProperties(records.get(i).getPersonalByUpholding(), complaintBean.getPersonalByUpholdingBean());
            }
            if (records.get(i).getSellhistoryEntity() != null){
                BeanUtils.copyProperties(records.get(i).getSellhistoryEntity(), complaintBean.getSellhistoryBean());
            }
            if (records.get(i).getRenthistoryEntity() != null){
                BeanUtils.copyProperties(records.get(i).getRenthistoryEntity(), complaintBean.getRenthistoryBean());
            }

            list.add(complaintBean);
        }
        beanPage.setRecords(list);
        return beanPage;
    }

    @Override
    public ComplaintBean findById(int id) {
        ComplaintEntity complaintEntity = complaintDao.findById(id);
        ComplaintBean complaintBean = new ComplaintBean();
        complaintBean.setComplaintDate(complaintEntity.getComplaintDate());
        complaintBean.setStatus(complaintEntity.getStatus());
        complaintBean.setComplaintReason(complaintEntity.getComplaintReason());
        try {
            //封装投诉者Bean类
            PersonalEntity personalEntity = personalDao.findOnePersonalInformation(complaintEntity.getPersonalComplaint().getId());
            PersonalBean personalBean = new PersonalBean();
            personalBean.setAddress(personalEntity.getAddress());
            personalBean.setUsername(personalEntity.getUsername());
            personalBean.setRelName(personalEntity.getRelName());
            personalBean.setTel(personalEntity.getTel());
            personalBean.setIdCard(personalEntity.getIdCard());
            personalBean.setJobDescription(personalEntity.getJobDescription());
            personalBean.setEmail(personalEntity.getEmail());
            complaintBean.setPersonalComplainantBean(personalBean);

            //封装被投诉者Bean类
            PersonalEntity personalEntity2 = personalDao.findOnePersonalInformation(complaintEntity.getPersonalByUpholding().getId());
            PersonalBean personalBean2 = new PersonalBean();
            personalBean2.setAddress(personalEntity2.getAddress());
            personalBean2.setUsername(personalEntity2.getUsername());
            personalBean2.setRelName(personalEntity2.getRelName());
            personalBean2.setTel(personalEntity2.getTel());
            personalBean2.setIdCard(personalEntity2.getIdCard());
            personalBean2.setJobDescription(personalEntity2.getJobDescription());
            personalBean2.setEmail(personalEntity2.getEmail());
            complaintBean.setPersonalByUpholdingBean(personalBean2);




            //封装出租历史记录对象
            RentHistoryEntity rentHistoryEntity = rentHistoryDao.findByRentPersonalId(complaintEntity.getRenthistoryEntity().getId());
            RentHistoryBean rentHistoryBean = new RentHistoryBean();
            rentHistoryBean.setAddress(rentHistoryEntity.getAddress());
            rentHistoryBean.setRentEndTime(rentHistoryEntity.getRentEndTime());
            complaintBean.setRenthistoryBean(rentHistoryBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return complaintBean;
    }

    @Override
    public int updateStatus(int id, String status) {
        MessageEntity messageEntity = new MessageEntity();
        ComplaintEntity complaintEntity = complaintDao.findById(id);
        //投诉者
        int personal1 = complaintEntity.getPersonalByUpholding().getId();
        String personalName1 = complaintEntity.getPersonalByUpholding().getUsername();
        //被投诉者
        int personal2 = complaintEntity.getPersonalComplaint().getId();
        String personalName2 = complaintEntity.getPersonalByUpholding().getUsername();
        try {
            if ("投诉生效".equals(status)) {
                //添加  返回给投诉用户的系统消息给消息实体类
                messageEntity.setPersonalid(personal1);
                messageEntity.setMessagetitle("投诉");
                messageEntity.setContent("你对" + personalName2 + "的投诉成功");
                messageEntity.setMessagetime(new Date(System.currentTimeMillis()));
                messageEntity.setMessagetype("系统消息");
                messageDao.addMessage(messageEntity);

                //添加  返回给被投诉用户的系统消息给消息实体类
                messageEntity.setPersonalid(personal2);
                /*                messageEntity.setMessagetitle("投诉");*/
                messageEntity.setContent("你被" + personalName1 + "投诉成功");
/*                messageEntity.setMessagetime(new Date(System.currentTimeMillis()));
                messageEntity.setMessagetype("系统消息");*/
                messageDao.addMessage(messageEntity);
                //修改用户的被投诉次数
                personalDao.updateComplainNumById(personal2);
            } else if ("投诉失败".equals(status)) {
                //添加  返回给投诉用户的系统消息给消息实体类
                messageEntity.setPersonalid(personal1);
                messageEntity.setMessagetitle("投诉");
                messageEntity.setContent("你对" + personalName2 + "的投诉失败");
                messageEntity.setMessagetime(new Date(System.currentTimeMillis()));
                messageEntity.setMessagetype("系统消息");
                messageDao.addMessage(messageEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        complaintDao.updateStatus(id, status);
        return 1;
    }

    @Override
    public int addComplaint(ComplaintBean complaintBean) {
        ComplaintEntity complaintEntity = new ComplaintEntity();
        PersonalEntity personalComplaint = new PersonalEntity();
        PersonalEntity personalByUpholding = new PersonalEntity();
        SellHistoryEntity sellHistoryEntity = new SellHistoryEntity();
        RentHistoryEntity rentHistoryEntity = new RentHistoryEntity();

        personalComplaint.setId(complaintBean.getPersonalComplainantId());
        personalByUpholding.setId(complaintBean.getPersonalByUpholdingId());
        sellHistoryEntity.setId(complaintBean.getSellhistoryId());
        rentHistoryEntity.setId(complaintBean.getRenthistoryId());

        complaintEntity.setPersonalComplaint(personalComplaint);
        complaintEntity.setPersonalByUpholding(personalByUpholding);
        complaintEntity.setSellhistoryEntity(sellHistoryEntity);
        complaintEntity.setRenthistoryEntity(rentHistoryEntity);

        complaintEntity.setComplaintReason(complaintBean.getComplaintReason());
        complaintEntity.setComplaintDate(complaintBean.getComplaintDate());
        complaintEntity.setStatus(complaintBean.getStatus());
        complaintDao.addComplaint(complaintEntity);
        return 1;
    }
}
