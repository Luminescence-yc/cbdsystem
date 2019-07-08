$(function () {
    $('.form_datetime').datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        todayBtn: 1,
        autoclose: 1
    });
    tableInit(null, null);
    $("#searchBtn").click(function () {
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        tableInit(startTime, endTime);

    });
    $.ajax({
        type: "post",
        url: "/companyBill/CompanyCount",
        dataType: "json",
        success: function (result) {
            console.log(result)
            $("#total_number").html(result.data.count.dealNum);
            $("#total_out").html(result.data.count.expenditure);
            $("#total_in").html(result.data.count.generalIncome);
        }
    });

    function tableInit(startTime, endTime) {
        $("#tb").bootstrapTable("destroy");
        $("#tb").bootstrapTable({
            url: "/companyBill/showCompanyBillPageByCondition",//请求的url地址
            method: "post",//请求方式
            pageSize: 10,//每页显示的数量
            pageList: [10, 20],//提供选择每页显示多少条
            showRefresh: false,//是否显示刷新按钮
            cache: false,//是否使用缓存
            height:500,//定高 500px
            showToggle: false,//是否显示详细视图和列表视图的切换按钮
            pagination: true,//是否显示分页
            pageNumber: 1,//初始化显示第几页，默认第1页
            singleSelect: true,//复选框只能选择一条记录
            sidePagination: 'server',//分页显示方式，可以选择客户端和服务端（server|client）
            contentType: "application/x-www-form-urlencoded",//使用post请求时必须加上
            dataType: "json",//接收的数据类型
            //queryParamsType: 'limit',//参数格式，发送标准的Restful类型的请求
            //得到查询参数即发给服务端的参数
            queryParams: function (params) {
                var nowPage = params.offset;
                console.log(startTime)
                return {
                    startTime:startTime,
                    endTime:endTime,
                    page: (params.offset / params.limit) + 1,//第几页
                    size: params.limit,//每页显示多少条
                }
            },
            onLoadSuccess:function(){
                $("#ifm",parent.document).attr("height",$(document).height());
            },
            //回调函数
            responseHandler: function (res) {
                return {
                    "total": res.data.pages.total,//当采用服务端分时必须给total赋值即从服务端得到总条数，表格将自动根据总条数进行分页
                    "rows": res.data.pages.records //每行显示的数据集合，注意必须是（total和rows不能是其他的字段，否则将不能显示数据或分页）
                }
            },
            columns: [{
                field: "select", checkbox: true
            },//开启选择框
                {field: "tradeDate", title: "交易日期", sortable: true},//表头，其中field值是返回JSON格式中对应的属性
                {field: "tradeTime", title: "交易时间", sortable: true},
                {field: "expend", title: "支出", sortable: true},
                {field: "income", title: "收入", sortable: true},
                {field: "remark", title: "交易备注", sortable: true}]
        });
    }

    $('.form_datetime').datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        todayBtn: 1,
        autoclose: 1
    });
});
