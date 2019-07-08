$(function () {
    var date;
    var checkedID=0;//选中行ID;
    layui.use(['table','layer'], function () {
        var table = layui.table
            ,layer = layui.layer;
        table.on('row(test)', function (obj)  {
            date = obj.data;
            checkedID=obj.data.id;
            console.log(checkedID);
            //标注选中样式
            obj.tr.addClass('yanse').siblings().removeClass('yanse');
        });
        table.render({
            elem: '#test'
            , url: '/complaint/findComplaintByStatus' //数据接口
            , method: 'post'
            , toolbar:'#toolbarDemo'
            , request: {
                pageName: 'currentPage'//页码的参数名称，默认是page
                , limitName: 'pageSize'//每页显示数量参数名，默认是limit
            },
            parseData: function (res) { //res 即为原始返回的数据
                //console.log(res);
                return {
                    "code": 0, //解析接口状态
                    "count": res.data.page.total, //解析数据长度
                    "data": res.data.page.records //解析数据列表
                };
            },
            done:function () {
                date="";
                $("#Accept").click(function () {
                    if (date == null||date=="") {
                        layer.open({
                            title: '提示'
                            , content: '请选中行'
                        });
                        return false;
                    }
                    window.location.href="../html/complaint-detail.html?"+checkedID;
                });
            },
            cols: [[ //表头

                {
                    field: 'personalComplainantBean', title: '投诉方', width:"20%",templet: function (data) {
                        console.log(date);
                        return data.personalComplainantBean.username;
                    }
                }
                ,{
                    field: 'personalByUpholdingBean', title: '被投诉方', width:"20%",templet: function (data) {
                        return data.personalByUpholdingBean.username;
                    }
                }
                , {field: 'complaintReason', width:"20%",  title: '投诉理由'}
                , {field: 'complaintDate',  width:"20%",    title: '投诉时间'}
                , {field: 'status',  width:"20%",    title: '受理状态'}
            ]],
            page: true //开启分页

        });




    });

});