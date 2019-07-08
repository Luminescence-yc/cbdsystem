$(function () {
    layui.use(['table', 'layer'], function () {
        var table = layui.table
            , layer = layui.layer;
        //定义一个从数据库读取管理员数据的函数
        var req = function () {
            table.render({
                //数据所放位置
                elem: '#demo'
                , height: 400
                , url: '../../../admin/getAdminNotAll' //数据接口
                , method: 'post'
                //页数
                , limit: 10
                , toolbar: '#toolbarDemo'
                //每页显示数据的条数可选项
                , limits: [10, 20, 30]
                , request: {
                    //分页参数
                    pageName: 'page'//页码的参数名称，默认是page
                    , limitName: 'size'//每页显示数量参数名，默认是limit
                }
                , where: {
                    /*insectName:insectName,//采用键值对的方式发送
                    hostName:hostName*/
                }
                //对返回的数据的处理动作
                , parseData: function (res) { //res 即为原始返回的数据
                    return {
                        "status": true, //解析接口状态
                        //数据条数
                        "count": res.data.page.total, //解析数据长度
                        //数据集体的值
                        "list": res.data.page.records //解析数据列表
                    };
                }
                , response: {
                    statusName: 'status' //规定数据状态的字段名称，默认：code
                    , statusCode: true //规定成功的状态码，默认：0
                    , msgName: 'msg' //规定状态信息的字段名称，默认：msg
                    , countName: 'count' //规定数据总数的字段名称，默认：count
                    , dataName: 'list' //规定数据列表的字段名称，默认：data
                }
                , cols: [[ //表头
                    {field: "id", title: '管理员id'}
                    , {field: "userId", title: '用户id', hide: true}
                    , {field: "password", title: '用户密码', hide: true}
                    , {field: "username", title: '姓名'}
                    , {field: "realName", title: '真实姓名'}
                    , {field: "tel", title: '电话号码'}
                ]]
                , page: true //开启分页
            });
        };
        req();
        var data;
        //监听行单击事件（单击事件为：rowDouble）
        table.on('row(test)', function (obj) {
            data = obj.data;
            //标注选中样式
            obj.tr.addClass('yanse').siblings().removeClass('yanse');
        });

        $("#updateManagement").click(function () {
            //判断点击后是否有数据
            if (data == null) {
                layer.open({
                    title: '提示'
                    , content: '请选中行'
                });
            } else {
                $.ajax({
                    type: "post",
                    url: '../../../admin/findAdminById',
                    data: {"id": data.id, "userId": data.userId},
                    dataType: "json",
                    success: function (resultData) {
                        if (resultData.length != 0 && resultData != null) {
                            //把查询到值传入相对应的位置
                            $("#show17").val(resultData.data.adminBean.password);
                            $("#show18").val(resultData.data.adminBean.tel);
                            $("#show19").val(resultData.data.adminBean.username);
                            $("#show20").val(resultData.data.adminBean.realName);
                            //遍历得到的权限集合
                            for (var i = 0; i < 4; i++) {
                                if (resultData.data.adminBean.list[i] != null) {
                                    //得到权限的字符串
                                    var date = resultData.data.adminBean.list[i].roleName;
                                    console.log(date);
                                    //定义checked的默认值
                                    var checked = true;
                                    if ("用户管理员" == (date)) {
                                        //把对应的属性修改成选中
                                        $("#show13").attr("checked", checked);
                                    } else if ("车位管理员" == date) {
                                        $("#show14").attr("checked", checked);
                                    } else if ("合同管理员" == date) {
                                        $("#show15").attr("checked", checked);
                                    } else if ("投诉管理员" == date) {
                                        $("#show16").attr("checked", checked);
                                    }
                                }
                            }
                            layer.open({
                                type: 1,
                                skin: 'layui-layer-rim', //加上边框
                                area: ['420px', '600px'], //宽高
                                content: $("#updete"),
                                end: function () {
                                    checked = false;
                                    //把勾选状态清除
                                    $("#show13").attr("checked", checked);
                                    $("#show14").attr("checked", checked);
                                    $("#show15").attr("checked", checked);
                                    $("#show16").attr("checked", checked);
                                    $("#updete").css('display', 'none');

                                }

                            });


                        } else {
                            layer.msg("数据未找到");
                        }
                    }
                });
            }
        })
        $("#updateAdmin").click(function () {

            //定义4个变量，对应四个权限的状态（未勾选）
            var item1=-1;
            var item2=-1;
            var item3=-1;
            var item4=-1;
                //判断复选框是否被勾选了
            if ($('#show13').is(':checked') == true) {
                //改变变量的值，对应的状态就改变（在后台判断是否为1）
                item1 = 1;
            }
            if ($('#show14').is(':checked') == true) {
                item2 = 1;
            }
            if ($('#show15').is(':checked') == true) {
                item3 = 1;
            }
            if ($('#show16').is(':checked') == true) {
                item4 = 1;
            }
            $.ajax({
                type: "post",
                url: '../../../admin/updateAdmin',
                data: {
                    //把数字付给对应的属性，在后台判断数字是否是-1
                    "userAdmin": item1,
                    "parkingAdmin": item2,
                    "contractAdmin": item3,
                    "complainAdmin": item4,
                    "userId": data.userId
                },
                dataType: "json",
                success: function (resultData) {
                    if (resultData.code == 200) {
                        layer.msg("修改成功");
                    } else {
                        layer.msg("修改失败");
                    }
                }
            });
        });



        $("#addManagement").click(function () {
            layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['420px', '600px'], //宽高
                content: $("#add"),
                end: function () {
                    $("#add").css('display', 'none');
                }
            });

        });
        $("#addAdmin").click(function () {

            var item1=-1;
            var item2=-1;
            var item3=-1;
            var item4=-1;

            if ($('#show9').is(':checked') == true) {
                item1 = 1;
            }
            if ($('#show10').is(':checked') == true) {
                item2 = 1;
            }
            if ($('#show11').is(':checked') == true) {
                item3 = 1;
            }
            if ($('#show12').is(':checked') == true) {
                item4 = 1;
            }

            $.ajax({
                type: "post",
                url: '../../../admin/addAdmin',
                data: {
                    "password": $("#addPassword").val(),
                    "tel": $("#addTel").val(),
                    "username": $("#addNum").val(),
                    "realName": $("#addRealName").val(),
                    "userAdmin": item1,
                    "parkingAdmin": item2,
                    "contractAdmin": item3,
                    "complainAdmin": item4
                },
                dataType: "json",
                success: function (resultData) {
                    if (resultData.code == 200) {
                        req();
                        layer.msg("添加成功");
                    } else {
                        layer.msg("添加失败");
                    }
                }
            });
        });




        $(".back").click(function () {
            window.location.href = "controlAuthorityManagement.html";
        });
        //查看管理员信息
        $("#checkDetailsManagement").click(function () {
            if (data == null) {
                layer.open({
                    title: '提示'
                    , content: '请选中行'
                });
            } else {
                $.ajax({
                    type: "post",
                    url: '../../../admin/findAdminById',
                    data: {"id": data.id, "userId": data.userId},
                    dataType: "json",
                    success: function (resultData) {
                        if (resultData.length != 0 && resultData != null) {
                            //把查询到值传入相对应的位置
                            $("#show1").val(resultData.data.adminBean.password);
                            $("#show2").val(resultData.data.adminBean.tel);
                            $("#show3").val(resultData.data.adminBean.username);
                            $("#show4").val(resultData.data.adminBean.realName);
                            //遍历得到的权限集合
                            for (var i = 0; i < 4; i++) {
                                if (resultData.data.adminBean.list[i] != null) {
                                    //得到权限的字符串
                                    var date = resultData.data.adminBean.list[i].roleName;
                                    console.log(date);
                                    var checked = true;
                                    if ("用户管理员" == (date)) {
                                        //把对应的属性修改成选中
                                        $("#show5").attr("checked", checked);
                                    } else if ("车位管理员" == date) {
                                        $("#show6").attr("checked", checked);
                                    } else if ("合同管理员" == date) {
                                        $("#show7").attr("checked", checked);
                                    } else if ("投诉管理员" == date) {
                                        $("#show8").attr("checked", checked);
                                    }
                                }
                            }
                           // 弹出模态框
                            layer.open({
                                type: 1,
                                skin: 'layui-layer-rim', //加上边框
                                area: ['420px', '600px'], //宽高
                                content: $("#checkDetails"),
                                end: function () {
                                    checked = false;
                                    //把勾选状态清除
                                    $("#show5").attr("checked", checked);
                                    $("#show5").attr("checked", checked);
                                    $("#show5").attr("checked", checked);
                                    $("#show5").attr("checked", checked);
                                    $("#checkDetails").css('display', 'none');
                                }
                            });
                        } else {
                            layer.msg("数据未找到");
                        }
                    }
                });
            }
        });

        //删除
        $("#deleteManagement").click(function () {
            if (data == null) {
                layer.open({
                    title: '提示'
                    , content: '请选中行'
                });
                // return false;
            } else {
                alert("确定删除？");
                $.ajax({
                    type: "post",
                    url: '../../../admin/deleteAdmin',
                    data: {"id": data.id, "userId": data.userId},
                    dataType: "json",
                    success: function (resultData) {
                        if (resultData.code == 200) {
                            console.log(resultData);
                            layer.msg('删除成功', {
                                offset: '15px'
                                ,icon: 1
                            });
                            req();
                        } else {
                            layer.msg('删除失败');
                        }
                    }
                });
            }
        })
    });
});
