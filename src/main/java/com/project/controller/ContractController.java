package com.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.bean.ContractBean;
import com.project.service.IContractService;
import com.project.service.IPersonalParkingService;
import com.project.service.ISellParkingService;
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

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘晶晶
 * @version v1.0
 * @ClassName: ContractController
 * @Description: 合同控制器
 * @date 2019年06月02日 16:54
 */
@Api("合同页面控制器")
@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private IContractService contractService;

    @Autowired
    private ISellParkingService iSellParkingService;

    @Autowired
    private IPersonalParkingService iPersonalParkingService;

    private Logger logger = LoggerFactory.getLogger(ContractController.class);

    @ApiOperation("添加合同的请求方法")
    @ApiImplicitParam(name = "contractEntity", value = "合同实体类", dataType = "ContractEntity")
    @RequestMapping("addContract")
    public ResponseData addContract(ContractBean contractBean, HttpSession session) {
        logger.info("收到addContract请求：" + contractBean.toString());
        contractBean.setSellPersonalId((Integer) session.getAttribute("id"));
        int i = contractService.addContract(contractBean);
        int x = iSellParkingService.updateSellStatusById(contractBean.getSellId(), "交易中");
        if (i == 0 && x == 0) {
            logger.info("发送addContract响应：" + ResponseData.notFound());
            return ResponseData.notFound();
        }
        logger.info("发送addContract响应：" + ResponseData.ok());
        return ResponseData.ok();
    }

    @ApiOperation("动态条件查询合同的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "申请开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "申请结束时间", dataType = "String"),
            @ApiImplicitParam(name = "contractNum", value = "合同编号", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "当前显示的条数", dataType = "String")
    })
    @RequestMapping("findAllByCondition")
    public ResponseData findAllByCondition(String startTime, String endTime, String contractNum, String page, String size) {
        logger.info("收到findAllByCondition的请求:" + "startTime:" + startTime + "endTime:" + endTime +
                "contractNum:" + contractNum + "page:" + page + "size:" + size);
        System.out.println(page + "   " + size);
        if (startTime.length() == 0) {
            startTime = null;
        }
        if (endTime.length() == 0) {
            endTime = null;
        }
        if (contractNum.length() == 0) {
            contractNum = null;
        }
        Map<String, String> map = new HashMap<>(10);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("contractNum", contractNum);
        map.put("page", page);
        map.put("size", size);
        IPage<ContractBean> pages = contractService.findContractByCondition(map);
        ResponseData responseData = new ResponseData();
        responseData.setCode(200);
        responseData.getData().put("pages", pages);
        logger.info("发送findAllByCondition的响应:" + responseData);
        return responseData;
    }

    @ApiOperation("通过合同ID修改合同状态的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "合同主键ID", dataType = "int"),
            @ApiImplicitParam(name = "status", value = "合同的转态", dataType = "String"),
            @ApiImplicitParam(name = "buyPersonalId", value = "买车用户id", dataType = "int"),
            @ApiImplicitParam(name = "sellPersonalId", value = "卖车用户id", dataType = "int"),
            @ApiImplicitParam(name = "sellParkingId", value = "卖车车位id", dataType = "int")
    })
    @RequestMapping("updateStatus")
    public ResponseData updateStatus(int id) {
        logger.info("收到updateStatus的请求:" + "id:" + id);
        contractService.updateStatus(id, "1");
        logger.info("发送updateStatus的响应:" + ResponseData.ok());
        return ResponseData.ok();
    }

    @ApiOperation("通过合同ID修改买家意见的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session", value = "session", dataType = "HttpSession"),
            @ApiImplicitParam(name = "id", value = "合同id", dataType = "int"),
            @ApiImplicitParam(name = "buyerAgree", value = "买家意见", dataType = "int"),
            @ApiImplicitParam(name = "sellPersonalId", value = "卖车用户id", dataType = "int"),
            @ApiImplicitParam(name = "sellParkingId", value = "卖车车位id", dataType = "int")
    })
    @RequestMapping("updateBuyerAgree")
    public ResponseData updateBuyerAgree(HttpSession session, int id, int buyerAgree, int sellPersonalId, int sellParkingId) {
        logger.info("收到updateBuyerAgree的请求:" + "id:" + id);
        ContractBean contractBean = new ContractBean();
        contractBean.setId(id);
        contractBean.setBuyerAgree(buyerAgree);
        contractBean.setSellPersonalId(sellPersonalId);
        contractBean.setBuyPersonalId((Integer) session.getAttribute("id"));
        contractBean.setSellId(sellParkingId);
        contractService.updateBuyer(contractBean);
        logger.info("发送updateBuyerAgree的响应:" + ResponseData.ok());
        return ResponseData.ok();
    }

    @ApiOperation("通过合同ID修改卖家意见的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "session", value = "session", dataType = "HttpSession"),
            @ApiImplicitParam(name = "id", value = "合同id", dataType = "int"),
            @ApiImplicitParam(name = "buyerAgree", value = "买家意见", dataType = "int"),
            @ApiImplicitParam(name = "sellPersonalId", value = "卖车用户id", dataType = "int"),
            @ApiImplicitParam(name = "sellParkingId", value = "卖车车位id", dataType = "int")
    })
    @RequestMapping("updateSellerAgree")
    public ResponseData updateSellerAgree(HttpSession session, int id, int sellerAgree, int buyPersonalId, int sellParkingId) {
        logger.info("收到updateSellerAgree的请求:" + "id:" + id);
        ContractBean contractBean = new ContractBean();
        contractBean.setId(id);
        contractBean.setSellerAgree(sellerAgree);
        contractBean.setSellPersonalId((Integer) session.getAttribute("id"));
        contractBean.setBuyPersonalId(buyPersonalId);
        contractBean.setSellId(sellParkingId);
        contractService.updateSeller(contractBean);
        logger.info("发送updateSellerAgree的响应:" + ResponseData.ok());
        return ResponseData.ok();
    }

    @ApiOperation("查询未受理的合同的请求方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "String"),
            @ApiImplicitParam(name = "size", value = "显示当前页数的条数", dataType = "String")
    })
    @RequestMapping("findNoAccept")
    public ResponseData findNoAccept(String page, String size) {
        logger.info("收到findNoAccept的请求:" + "page:" + page + "size:" + size);
        IPage<ContractBean> pages = contractService.findNotAcceptContract(Integer.parseInt(page), Integer.parseInt(size));
        ResponseData responseData = new ResponseData();
        responseData.getData().put("pages", pages);
        logger.info("发送findNoAccept的响应：" + responseData);
        return responseData;

    }

    @ApiOperation("通过出售车位ID查询合同的请求方法")
    @ApiImplicitParam(name = "id", value = "出售车位ID", dataType = "int")
    @RequestMapping("findAllBySellId")
    public ResponseData findAllBySellId(int id) {
        logger.info("收到findAllBySellId的请求:" + "id:" + id);
        ContractBean contractBean = contractService.findContractBySellId(id);
        if (contractBean != null) {
            ResponseData responseData = new ResponseData();
            responseData.getData().put("contract", contractBean);
            logger.info("发送findAllBySellId的响应:" + responseData);
            return responseData;
        }
        logger.info("发送findAllBySellId的响应:" + ResponseData.unauthorized());
        return ResponseData.unauthorized();

    }

    @ApiOperation("通过合同id删除合同方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "合同id", dataType = "int"),
            @ApiImplicitParam(name = "sellId", value = "车位id", dataType = "int")
    })
    @RequestMapping("deleteContractById")
    public ResponseData deleteContractById(int id,int sellParkingId) {
        logger.info("收到deleteContractById的请求:" + "id:" + id);
        contractService.deleteContractById(id);
        iSellParkingService.updateSellStatusById(sellParkingId,"出售中");
        logger.info("发送deleteContractById的响应:" + ResponseData.unauthorized());
        return ResponseData.ok();
    }
}
