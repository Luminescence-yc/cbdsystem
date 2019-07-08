$(function () {
    var id = 0;
    $("#table").bootstrapTable({
        url: "/sellHistory/showSellHistoryPageAllByPersonalId",//请求的url地址
        method: "post",//请求方式
        // striped:true,//是否显示行间隔色
        pageSize: 10,//每页显示的数量
        pageList: [10, 20],//提供选择每页显示多少条
        showRefresh: false,//是否显示刷新按钮
        cache: false,//是否使用缓存
        height:500,//定高 500px
        showToggle: false,//是否显示详细视图和列表视图的切换按钮
        pagination: true,//是否显示分页
        pageNumber: 1,//初始化显示第几页，默认第1页
        clickToSelect:true,
        singleSelect: true,//复选框只能选择一条记录
        sidePagination: 'server',//分页显示方式，可以选择客户端和服务端（server|client）
        contentType: "application/x-www-form-urlencoded",//使用post请求时必须加上
        dataType: "json",//接收的数据类型
        queryParamsType: 'limit',//参数格式，发送标准的Restful类型的请求
        //得到查询参数即发给服务端的参数
        queryParams: function (params) {
            var nowPage = params.offset;
            return {
                page: (params.offset / params.limit) + 1,//第几页
                size: params.limit,//每页显示多少条
            }
        },
        onLoadSuccess:function(){
            $("#ifm",parent.document).attr("height",$(document).height());
        },
        //回调函数
        responseHandler: function (res) {
            console.log(res);//查看从服务端得到的数据
            return {
                "total": res.data.pages.total,//当采用服务端分时必须给total赋值即从服务端得到总条数，表格将自动根据总条数进行分页
                "rows": res.data.pages.records//每行显示的数据集合，注意必须是（total和rows不能是其他的字段，否则将不能显示数据或分页）
            }
        },
        columns: [{
            field: "select", checkbox: true
        },//开启选择框
            {field: "parkingMessage", title: "出售的车位信息", sortable: true},//表头，其中field值是返回JSON格式中对应的属性
            {
                field: "username",
                title: "出售车位用户名",
                sortable: true,
                formatter: function (value, row, index) {
                    return row.sellPersonal.username;
                }
            },
            {field: "externalPrice", title: "出售价格", sortable: true},
            {
                field: "username",
                title: "购买车位用户名",
                sortable: true,
                formatter: function (value, row, index) {
                    return row.buyPersonal.username;
                }
            },
            {field: "sellDate", title: " 交易时间", sortable: true},
            ]
    });
    //获取选中的行
    $("#table").on("click-row.bs.table", function (e, row, ele) {
        id = row.id;
    });

    $("#btn1").click(function () {
        window.location.href = "lookLeaseRecord.html";
    })
    $("#btn2").click(function () {
        window.location.href = "leaseRecord.html";
    })
    $("#btn3").click(function () {
        window.location.href = "buyAndSellRecord.html";
    })
    $("#btn4").click(function () {
        var a = $("#table").bootstrapTable('getSelections');

        if (a.length == 1) {
            //b.val(a[0].id);
        } else {
            alert("请选中一行")
            return false;
        }
    });
    // $("#sure").click(function () {
    //     $.ajax({
    //         type: "post",
    //         url: "/complaint/addComplaint",
    //         dataType: "json",
    //         data: {
    //             complaintReason: $("#complaintReason").val(),
    //             userId: id
    //         },
    //         success: function (result) {
    //             window.location.href = "buyAndSellRecord.html";
    //         }
    //     });
    // })
    $("#ifm",parent.document).attr("height",$(document).height());
});