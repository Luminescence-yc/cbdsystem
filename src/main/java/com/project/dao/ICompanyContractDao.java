package com.project.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.CompanyContractBean;
import com.project.entity.CompanyContractEntity;
import com.project.entity.CompanyEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:ICompanyContractDao
 * @Description: 租户合约持久接口
 * @date 2019年05月30日 14:05
 */
public interface ICompanyContractDao {
    /**
     * 添加租户合约
     * @param contractEntity 租户合约实体
     * @return 返回1表示添加成功，返回0表示添加失败
     */
    public int add(CompanyContractEntity contractEntity) throws Exception;

    /**
    * 通过租户合约id查看租户合约
     * @param id 租户合约id
     * @return 租户合约对象
     */
    public CompanyContractEntity findById(int id) throws Exception;
    /**
     * 根据租户合约id修改租户合约（续约）
     * @param contractEntity 租户合约对象
     * @return 返回0修改失败，返回1修改成功
     */
    public int update(CompanyContractEntity contractEntity) throws Exception;
    /**
     * 删除合约
     * @param id 租户合约id
     * @return 返回0删除失败，返回1删除成功
     */
    public int delete(int id) throws Exception;
    /**
     * 动态条件查询
     * @param condition 动态条件集合
     * @return 租户合约对象
     */
    public List<CompanyContractEntity> findByCondition(Page<CompanyContractEntity> page,Map<String, String>condition) throws Exception;

    /**
     * 根据合同生效日期和结束日期查询
     * @param page 分页
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 租户合约实体
     * @throws Exception
     */
    public List<CompanyContractEntity> findByStartAndEnd(Page<CompanyContractEntity> page,String startDate,String endDate) throws Exception;

    /**
     * 多条件动态查询
     * @param page 分页
     * @param condition 动态条件集合 开始日期和结束日期 s登录企业用户id
     * @return Exception
     */
    public List<CompanyContractBean>findByCompanyIdAndStartAndEndDate(Page<CompanyContractBean>page, Map<String,String>condition)throws Exception;
}
