$(function () {
    layui.use(['jquery', 'table', 'laydate'], function () {
        var $ = layui.jquery;
        var table = layui.table;
        var laydate = layui.laydate;
        var id;
        //执行一个laydate实例
        laydate.render({
            elem: '#startTime' //指定元素
        });

        laydate.render({
            elem: '#endTime'
        });


        //栏目展示隐藏
        $('.showSubBtn').on('click', function () {
            var _this = $(this);
            var id = _this.attr('data-id');
            var parent = _this.parents('.parent');
            var child = $('.child-node-' + id);
            var childAll = $('tr[parentid=' + id + ']');
            if (parent.hasClass('collapsed')) {
                _this.html('-');
                parent.addClass('expanded').removeClass('collapsed');
                child.css('display', '');
            } else {
                _this.html('+');
                parent.addClass('collapsed').removeClass('expanded');
                child.css('display', 'none');
                childAll.addClass('collapsed').removeClass('expanded').css('display', 'none');
                childAll.find('.showSubBtn').html('+');
            }

        });

        $("#searchBtn").click(function () {
            var startApplyDate = $("#startTime").val();
            var endApplyDate = $("#endTime").val();
            var username = $("#username").val();

            tableInit(startApplyDate, endApplyDate, username);

        });

        tableInit(null, null, null);

        function tableInit(startApplyDate, endApplyDate, username) {
            table.render({
                elem: '#table111'//表格的id值
                , id: 'idTest'//当需要获取选中数据时所需的id即lay-data="{id: 'idTest'}"中的idTest，注意这里的id不用写#
                , url: '/personalParking/showRentParkingInfo' //数据接口
                , method: 'post'//请求方式
                , toolbar: '#toolbarDemo'
                , limit: 10//每页显示的条数
                , limits: [10, 20, 30]//可选择的每页显示条数
                //修改请求参数
                , request: {
                    pageName: 'page'//页码的参数名称，默认是page
                    , limitName: 'size'//每页显示数量参数名，默认是limit
                }
                //发给服务端的其他参数
                , where: {
                    status: '待审核',
                    username: username,
                    endApplyDate: endApplyDate,
                    startApplyDate: startApplyDate
                },
                done: function () {//表格渲染后执行的方法
                    $("#accept").click(function () {
                        // console.log(id);
                        if (id == null) {
                            layer.open({
                                title: '提示'
                                , content: '请选中行'
                            });
                            return false;
                        }
                        layer.open({
                            title: "车位信息审核",
                            type: 2,
                            content: "auditStall.html?" + id,
                            area: ['700px', '600px']
                        });
                    });
                }
                //处理数据回调函数
                , parseData: function (res) { //res 即为原始返回的数据
                    // console.log(res);
                    return {
                        "status": res.code, //解析接口状态
                        "count": res.data.pages.total, //解析数据长度
                        "list": res.data.pages.records //解析数据列表
                    };
                }
                //设置响应回来的数据参数名称和状态，它将对应上面回调函数中解析数据的参数名
                , response: {
                    statusName: 'status' //规定数据状态的字段名称，默认：code
                    , statusCode: 200 //规定成功的状态码，默认：0
                    , msgName: 'msg' //规定状态信息的字段名称，默认：msg
                    , countName: 'count' //规定数据总数的字段名称，默认：count
                    , dataName: 'list' //规定数据列表的字段名称，默认：data
                }
                , cols: [[ //表头
                    {type: 'radio'},//单选框开启按钮
                    {field: 'id', title: 'ID', sort: true, fixed: 'left'}
                    , {
                        field: 'personalEntity', title: '用户名', templet: function (d) {
                            return d.personalEntity.username;
                        }
                    }
                    , {
                        field: 'address', title: '车辆位置'
                    }
                    , {field: 'applyDate', title: '申请时间', sort: true}
                ]]
                , page: true //开启分页
            });
            //监听行单击事件（单击事件为：rowDouble）
            table.on('row(test)', function (obj) {
                data = obj.data;
                id = data.id;
                // console.log(data);
                //标注选中样式
                obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
            });
        }
    });
});