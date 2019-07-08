package com.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.ExternalContractBean;

import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:IExternalContract
 * @Description: 外部合约业务接口
 * @date 2019年05月30日 11:08
 */
public interface IExternalContractService {
    /**
     * 添加外部合约
     *
     * @param externalContractBean 外部合约bean对象
     * @return 返回1表示添加成功，返回0表示添加失败
     * <p>
     * ExternalContractBean
     */
    public int addExternalContract(ExternalContractBean externalContractBean);

    /**
     * 查看外部合约
     *
     * @param id 外部合约id
     * @return 外部合约对象
     */
    public ExternalContractBean findById(int id);

    /**
     * 删除外部合约(解约)，根据外部合约id删除CBD车位
     *
     * @param id 外部合约id
     * @return 返回1表示成功，返回-1表示还有车位在租赁中，不能删除，返回0表示失败
     */
    public int delete(int id);

    /**
     * 通过动态条件查询外部合约
     *
     * @param condition 外部合约动态条件集合
     * @return 分页对象
     */
    public Page<ExternalContractBean> findByCondition(Map<String, String> condition);


    /**
     * 外部合约续约，新增一条外部合约，封装原合同编号
     *
     * @param contractBean 外部合约对象
     *                     //     * @param id 外部合约id
     * @return 返回1表示成功，返回0表示失败
     */
    public int update(ExternalContractBean contractBean);


}
