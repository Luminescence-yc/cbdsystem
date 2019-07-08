package com.project.dao;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.entity.CompanyContractEntity;
import com.project.entity.ExternalContractEntity;
import java.util.List;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:
 * @Description:
 * @date 2019年05月30日 14:11
 */
public interface IExternalContractDao {

    /**
     * 添加外部合约
     * @param contractEntity 外部合约bean对象
     * @return 返回1表示添加成功，返回0表示添加失败
     *
     */
    public int addExternalContract(ExternalContractEntity contractEntity) throws Exception;

    /**
     * 查看外部合约
     * @param id 外部合约id
     * @return 外部合约对象
     */
    public ExternalContractEntity findById(int id) throws Exception;
    /**
     * 删除外部合约(解约)，根据外部合约id删除CBD车位
     * @param id 外部合约id
     * @return 返回1表示成功，返回0表示失败
     */
    public int delete(int id) throws Exception;

    /**
     * 通过动态条件查询外部合约
     * @param condition 外部合约动态条件集合
     * @return 外部合约对象
     */
    public List<ExternalContractEntity> findByCondition(Page<ExternalContractEntity> page, Map<String, String>condition) throws Exception;
    /**
     * 外部合约续约，新增一条外部合约，封装原合同编号
     * @param contractEntity 外部合约对象
     * @return 返回1表示成功，返回0表示失败
     */
    public int update(ExternalContractEntity contractEntity) throws Exception;

}
