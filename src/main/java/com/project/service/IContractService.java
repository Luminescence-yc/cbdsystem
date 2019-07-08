package com.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.ContractBean;
import com.project.entity.ContractEntity;

import java.util.Map;

/**
 * @author 刘晶晶
 * @version v1.0
 * @ClassName: IContractService
 * @Description: 合同业务层接口
 * @date 2019年05月30日 11:18
 */
public interface IContractService {
    /**
     * 添加合同
     *
     * @param contractBean 合同实现类
     * @return 返回非0即成功，0失败
     */
    int addContract(ContractBean contractBean);

    /**
     * 根据合同Id修改买家意见，同时判断合同的卖家和买家意见是否都为1，
     * 是则添加交易记录，添加消费记录，然后删除出售车位留言，删除出售车位，删除合同，删除个人车位
     *
     * @param contractBean 合同Bean对象
     * @return 返回非0为成功，0失败
     */
    int updateBuyer(ContractBean contractBean);

    /**
     * 根据合同Id修改卖家意见，同时判断合同的卖家和买家意见是否都为1，
     * 是则添加交易记录，添加消费记录，然后删除出售车位留言，删除出售车位，删除合同，删除个人车位
     *
     * @param contractBean 合同Bean对象
     * @return 返回非0为成功，0失败
     */
    int updateSeller(ContractBean contractBean);

    /**
     * 动态分页查询合同信息
     *
     * @param condition 查询条件
     *                  （包括甲乙双方的用户名、合同申请的起始结束时间、出售车位的位置）
     * @return 合同的分页实体Bean
     * contractBean封装双方用户，出售车位，申请时间
     */
    IPage<ContractBean> findContractByCondition(Map<String, String> condition);

    /**
     * 根据合同的主键ID修改合同的状态（状态分为未受理和已受理）
     *
     * @param id     合同的主键ID
     * @param status 合同状态
     * @return 返回0表示失败，返回1表示成功
     */
    int updateStatus(int id, String status);

    /**
     * 查询未受理的合同
     *
     * @param page 当前页数
     * @param size 每页显示的条数
     * @return 合同Bean的分页对象
     */
    IPage<ContractBean> findNotAcceptContract(int page, int size);

    /**
     * 根据出售车位的ID查询合同
     *
     * @param sellId 出售车位ID
     * @return 合同实体Bean
     */
    ContractBean findContractBySellId(int sellId);

    /**
     * 通过合同id查询合同对象
     *
     * @param id 合同id
     * @return 合同对象
     * 陈云龙添加
     */
    public ContractEntity selectContractById(int id);

    /**
     * 根据合同id删除合同
     *
     * @param id 合同id
     * @return 返回非0位成功，0失败
     * 陈云龙添加
     */
    public int deleteContractById(int id);
}
