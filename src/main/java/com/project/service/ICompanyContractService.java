package com.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CompanyContractBean;
import com.project.entity.CompanyContractEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:ICompanyContractService
 * @Description:租户合约业务接口
 * @date 2019年05月30日 12:54
 */
public interface ICompanyContractService {
    /**
     * 添加租户合约（调用CBD车位管理的Dao层修改车位状态方法）
     * 先调用查询所有企业的方法
     * @param companyContractBean 租户合约实体
     * @return 返回1表示添加成功，返回0表示添加失败
     */
    public int addCompanyContract(CompanyContractBean companyContractBean);

    /**
     * 通过租户合约id查看租户合约
     * @param id 租户合约id
     * @return 租户合约对象
     */
    public CompanyContractBean findById(int id);

    /**
     * 根据租户合约id修改租户合约（续约）
     * @param companyContractBean 租户合约对象
     * @return 返回0修改失败，返回1修改成功
     */
    public int update(CompanyContractBean companyContractBean);

    /**
     * 解约
     * @param id 租户合约id
     * @return 返回0删除失败，返回1删除成功
     */
    public int delete(int id);

    /**
     * 动态条件查询
     * @param condition 动态条件集合
     * @return 分页对象
     */
    public Page<CompanyContractBean> findCompanyContractByCondition(Map<String,String>condition);


    /**
     * 根据合同生效日期和结束日期查询
     * @param page 分页
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 租户合约实体
     * @throws Exception
     */
    public Page<CompanyContractBean> findByStartAndEnd(Page<CompanyContractEntity> page, String startDate, String endDate) throws Exception;

    /**
     * 多条件动态查询
     * @param condition 动态条件集合 开始日期和结束日期
     * @return Exception
     */
    public Page<CompanyContractBean> findByCompanyIdAndStartAndEndDate(Map<String,String>condition)throws Exception;

}
