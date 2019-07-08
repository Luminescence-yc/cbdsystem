package com.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.bean.ExternalContractBean;
import com.project.entity.ExternalContractEntity;
import com.project.service.IExternalContractService;
import com.project.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李杰郊
 * @version v1.0
 * @ClassName:ExternalContractConterller
 * @Description:外部合约控制器
 * @date 2019年06月03日 22:13
 */
@Api("外部合约控制器")
@RequestMapping(value = "/findExternalContract")
@RestController
public class ExternalContractConterller {
    @Autowired
    private IExternalContractService contractService;
    private Logger logger = LoggerFactory.getLogger(ContractController.class);

    @ApiOperation("动态查询外部合约处理方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyName", value = "合同单位", dataType = "String"),
            @ApiImplicitParam(name = "address", value = "车位地址", dataType = "String"),
            @ApiImplicitParam(name = "companyPrice", value = "成交价格", dataType = "String"),
            @ApiImplicitParam(name = "page",value = "当前页数",dataType = "String"),
            @ApiImplicitParam(name = "size",value = "当前显示的条数",dataType = "String")
    })
    @RequestMapping("findPages")
    public ResponseData findExternalContract(String companyName,String address
            ,String companyPrice,String page, String size){
        logger.info("收到findPages的请求:" + "companyName:" + companyName + "address:" + address +
                "companyPrice:" + companyPrice + "page:" + page + "size:" + size);
        if("".equals(companyName)&& companyName.length()==0){
            companyName=null;
        }
        if("".equals(address)&&address.length()==0){
            address=null;
        }
        if("".equals(companyPrice)&&companyPrice.length()==0){
            companyPrice=null;
        }
        Map<String,String> condition=new HashMap<>(10);
        condition.put("companyName",companyName);
        condition.put("address",address);
        condition.put("companyPrice",companyPrice);
        condition.put("page",page);
        condition.put("size",size);
      Page<ExternalContractBean> page1= contractService.findByCondition(condition);
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.getData().put("pages", page1);
        logger.info("发送findPages的响应:" + responseData);
        return responseData;
    }
@ApiOperation("通过外部合约id查看信息处理方法")
@ApiImplicitParams({
        @ApiImplicitParam(name = "id",value = "外部合约编号",dataType = "String")
})
@RequestMapping("findById")
public ResponseData findById(int id){
    logger.info("收到findById的请求:" + "id:" + id);
   ExternalContractBean contractBean= contractService.findById(id);
    ResponseData responseData = new ResponseData();
    responseData.setCode(200);
    responseData.getData().put("contractBean", contractBean);
    logger.info("发送findById的响应:" + responseData);
    return responseData;

}
@ApiOperation("外部合约解约处理方法")
@ApiImplicitParams({
        @ApiImplicitParam(name = "id",value = "外部合约编号",dataType = "String")
})
@RequestMapping("deletes")
public ResponseData terminate(int id){
    logger.info("收到deletes的请求:" + "id:" + id);
  int result=contractService.delete(id);
  if(result==1){
      ResponseData responseData = new ResponseData();
      responseData.setCode(200);
      responseData.setMessage("ok");
      logger.info("发送deletes的响应:" + responseData);
      return responseData;
  }
  return ResponseData.unauthorized();
}
@ApiOperation("外部合约续约处理方法")
@ApiImplicitParams({
        @ApiImplicitParam(name = "ExternalContractEntity",value = "外部合约实体类",dataType = "ExternalContractEntity")
})
@RequestMapping("contract")
public ResponseData Contract(ExternalContractBean contractBean){
    logger.info("收到contract的请求:" + "contractBean:" + contractBean.toString());
    int result=contractService.update(contractBean);
        if(result==0){
            logger.info("发送contract响应：" + ResponseData.notFound());
            return ResponseData.notFound();
        }
    logger.info("发送contract响应：" + ResponseData.ok());
    return ResponseData.ok();
}

@ApiOperation("新增外部合约处理方法")
@ApiImplicitParams({
        @ApiImplicitParam(name = "ExternalContractEntity",value = "外部合约实体baen",dataType = "ExternalContractEntity")
})
@RequestMapping("add")
public ResponseData update(ExternalContractBean contractBean){
    logger.info("收到add的请求:" + "contractBean:" + contractBean.toString());
     int result=contractService.addExternalContract(contractBean);
     if(result!=0){
         logger.info("发送add响应：" + ResponseData.ok());
         return ResponseData.ok();
     }
    logger.info("发送add响应：" + ResponseData.notFound());
    return ResponseData.notFound();
}

}
