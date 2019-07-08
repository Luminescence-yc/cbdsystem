$(function () {
    layui.use('table', function () {
        table = layui.table;
        var id=0;
        tableInit(null, null);
        $("#find").click(function () {
            var address = $("#address").val();
            var areaNum = $("#areaNum").val();
            // console.log(address,parkingNum);
         tableInit(address, areaNum);

        });

        function tableInit(address, areaNum) {
            // console.log(address,parkingNum);
            table.render({
                elem: '#test',
                url: '/cbdParking/findCondition',
                id: 'idTest',
                toolbar: '#toolbarDemo',
                method: 'post',
                done: function () {
                    id = 0;
                    $("#carport").click(function () {
                        if (id <=0) {
                            layer.open({
                                title: '提示'
                                , content: '请选中行'
                            });
                            return false;
                        }else{
                            $("#addPark").click(function () {
                            $.ajax({
                                type:"post",
                                url:"/cbdParking/addParking",
                                dataType:"json",
                                data:{address:$("#addParking").val(),
                                     parkingNum:$("#parking").val()},
                                    success:function (data) {
                                    console.log(data)
                                   if(data.message=="ok"){
                             window.location.href="../carport.html"
                                   }
                                }
                            })
                            })
                        }
                        layer.open({
                            type: 1,
                            skin: 'layui-layer-rim', //加上边框
                            /*  area: ['600px', '800px'], //宽高*/
                            /* area: ['600px', '500px'],*/
                            title: '添加企业车位',
                            area: '700px',
                            shade: 0.7,
                            maxmin: true,
                            content: $("#addcarport"),
                            end: function () {
                                $("#addcarport").css('display', 'none');
                            }
                        });

                    })
                    $("#batch").click(function () {
                        layer.open({
                            type: 1,
                            title: '批量添加企业车位',
                            skin: 'layui-layer-rim', //加上边框
                            area: '700px',
                            shade: 0.7,
                            maxmin: true,
                            content: $("#batchaddcarport"),
                            end: function () {
                                $("#batchaddcarport").css('display', 'none');
                            }
                        });
                    })
                    $("#search").click(function () {
                        if (id <=0) {
                            layer.open({
                                title: '提示'
                                , content: '请选中行'
                            });
                            return false;
                        }else {
                            $.ajax({
                                type:"post",
                                url:"/cbdParking/find",
                                data:{"id":id},
                                dataType:"json",
                                success:function (data) {
                                    $("#parkingaddress").val(data.data.cbdParkingBean.address)
                                    $("#parkingNum").val(data.data.cbdParkingBean.areaNum)
                                }
                            })
                        }
                        layer.open({
                            type: 1,
                            title: '查看企业车位',
                            skin: 'layui-layer-rim', //加上边框
                            area: '700px',
                            shade: 0.7,
                            maxmin: true,
                            content: $("#searchCarport"),
                            end: function () {
                                $("#search").css('display', 'none');
                            }
                        });
                    })
                },
                request: {
                    pageName: 'page' //页码的参数名称，默认：page
                    , limitName: 'size' //每页数据量的参数名，默认：limit
                },
                parseData: function (res) { //res 即为原始返回的数据
                    return {
                        code: res.code, //解析接口状态
                        msg: 0, //解析提示文本
                        status: res.code, //解析接口状态
                        count: res.data.pages.total, //解析数据长度
                        list: res.data.pages.records //解析数据列表
                    };
                },
                cols: [[
                    {field: 'id', title: 'ID'},//隐藏ID列
                    {field: 'address', title: '车位地址'},
                    {field: 'areaNum', title: '车位区域'}
                ]],
                response: {
                    statusName: 'status' //规定数据状态的字段名称，默认：code
                    , statusCode: 200 //规定成功的状态码，默认：04a
                    , msgName: 'hint' //规定状态信息的字段名称，默认：msg
                    , countName: 'count' //规定数据总数的字段名称，默认：count
                    , dataName: 'list' //规定数据列表的字段名称，默认：data
                },
                where: {//动态查询条件
                    address: address,
                    areaNum: areaNum,
                },
                page: true
            })
        }
        table.on('row()', function (obj) {
            //获取选中行数据的ID
            id = obj.data.id;
            //给选中行添加样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });

        $("#back").click(function () {
          window.location.href="carport.html"
        })

        /*新增租户合约弹出模态框*/
        $("#add").click(function () {
            //获取选中行
            table.on('row()', function (obj) {
                //获取选中行数据的ID
                checkedID = obj.data.id;
                //给选中行添加样式
                obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
            });
            layer.open({
                type: 1,
                title: '用户信息',
                area: ['700px', '700px'],
                shadeClose: false, //点击遮罩关闭
                content: $('#addmain'),
                end: function () {
                    $('#addmain').css("display", "none");
                }
            });
        })

        $("#addParking").click(function () {
            $.ajax({
                type:"post",
                url:"/cbdParking/addParking",
                dataType:"json",
                data:{address:$("#addressParking").val(),
                      areaNum:$("#parking").val(),
                      parkingNum:$("#startDate").val(),
                      count:$("#counts").val()},
                    success:function (datas) {
                        layer.open({
                            title: '提示'
                            ,content: '添加成功',
                            yes:function () {
                                window.parent.location.reload();//刷新父页面
                            }
                        });
                }
            })
        })
        /*续约弹出模态框*/
        $("#addContract").click(function () {
            //获取选中行
            table.on('row()', function (obj) {
                //获取选中行数据的ID
                checkedID = obj.data.id;
                //给选中行添加样式
                obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
            });
            layer.open({
                type: 1,
                title: '用户信息',
                area: ['700px', '700px'],
                shadeClose: false, //点击遮罩关闭
                content: $('#contractExtension'),
                end: function () {
                    $('#contractExtension').css("display", "none");
                }
            });


        })
        /*合约详情模态框*/
        $("#detailsContract").click(function () {
            //获取选中行
            table.on('row()', function (obj) {
                //获取选中行数据的ID
                checkedID = obj.data.id;
                //给选中行添加样式
                obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
            });
            layer.open({
                type: 1,
                title: '用户信息',
                area: ['700px', '700px'],
                shadeClose: false, //点击遮罩关闭
                content: $('#selectContract'),
                end: function () {
                    $('#selectContract').css("display", "none");
                }
            });


        })
        /*解出合同模态框*/
        $("#delContract").click(function () {
            //获取选中行
            table.on('row()', function (obj) {
                //获取选中行数据的ID
                checkedID = obj.data.id;
                //给选中行添加样式
                obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
            });
            layer.open({
                type: 1,
                title: '用户信息',
                area: ['800', '700px'],
                shadeClose: false, //点击遮罩关闭
                content: $('#deleteContract'),
                end: function () {
                    $('#deleteContract').css("display", "none");
                }
            });


        })



    })
})