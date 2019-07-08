$(function () {
    layui.use(['table', 'layer', 'jquery', 'form'], function () {
        var num=0;
        var table = layui.table,
            layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;
        var checkedId;
        //获取选中行
        table.on('row()', function (obj) {
            //获取选中行数据的ID
            checkedId = obj.data.id;
            //给选中行添加样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });
        var req = function () {
            //第一个实例
            table.render({
                elem: '#contract'
                , height: 443
                , toolbar: '#toolbarDemo'
                , url: '/companyContract/findByCondition' //数据接口
                , size: 'sm' //小尺寸的表格
                , request: {
                    pageName: 'page' //页码的参数名称，默认：page
                    , limitName: 'size' //每页数据量的参数名，默认：limit
                }
                , where: {
                    companyName: $("#companyName").val(),
                    contractNum: $("#contractNum").val(),
                    companyPrice: $("#companyPrice").val()
                }
                , page: true //开启分页
                , parseData: function (res) { //res 即为原始返回的数据
                    return {
                        "code": 0, //解析接口状态
                        "msg": "", //解析提示文本
                        "count": res.data.pages.total, //解析数据长度
                        "data": res.data.pages.records //解析数据列表
                    };
                }
                , cols: [[ //表头
                    {field: 'id', title: '序号', sort: true, fixed: 'left'}
                    , {
                        field: 'companyName', title: '企业名称', align: 'center', templet: function (d) {
                            return d.companyEntity.companyName
                        }
                    }
                    , {
                        field: 'contactPerson', title: '企业联系人', align: 'center', templet: function (d) {
                            return d.companyEntity.contactPerson
                        }
                    }
                    , {
                        field: 'tel', title: '电话', align: 'center', templet: function (d) {
                            return d.companyEntity.tel
                        }
                    }
                ]]
            });
        };
        req();
        $("#contractFind").click(function () {
            req();
        });

        /*新增租户合约弹出模态框*/
        $("#add").click(function () {
            $.ajax({
                type: "post",
                url: "/company/findCompanyByCondition",
                data: {page: 0, size: 100},
                dataType: "json",
                success: function (data) {
                    console.log(data.data);
                    var opt;
                    for (var i = 0; i < data.data.pages.records.length; i++) {
                        opt = $('<option value=' + data.data.pages.records[i].id + '>' + data.data.pages.records[i].companyName+ '</option>')
                        $("#company").append(opt);
                    }
                    form.render('select');
                }
            });
            layer.open({
                type: 1,
                title: '用户信息',
                area: ['600px', '700px'],
                shadeClose: false, //点击遮罩关闭
                content: $('#addmain'),
                end: function () {
                    $('#addmain').css("display", "none");
                }
            });
        });
        /*续约弹出模态框*/
        $("#addContract").click(function () {
            // //获取选中行
            // table.on('row()', function (obj) {
            //     //获取选中行数据的ID
            //     checkedID = obj.data.id;
            //     //给选中行添加样式
            //     obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
            // });
            $.ajax({
                type: "post",
                url: "/companyContract/find",
                data: {id: checkedId},
                dataType: "json",
                success: function (data) {
                    //console.log(data);
                    $("#a").val(data.data.contractBean.companyBean.companyName);
                    $("#b").val(data.data.contractBean.companyBean.contactPerson);
                    $("#c").val(data.data.contractBean.companyBean.tel);
                    $("#d").val(data.data.contractBean.startDate);
                    $("#e").val(data.data.contractBean.endDate);
                    $("#f").val(data.data.contractBean.companyPrice);
                }
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
            $.ajax({
                type: "post",
                url: "/companyContract/updates",
                data: {oldContractNum:$("#a").val(),
                    contactPerson:$("#b").val(),
                    contactTel:$("#c").val(),
                    startDate:$("#d").val(),
                    endDate:$("#e").val(),
                    companyPrice:$("#f").val(),
                },
                dataType: "json",
                success: function (data) {
                    layer.open({
                        title: '提示'
                        ,content: '续约成功',
                        yes:function () {
                            window.parent.location.reload();//刷新父页面
                        }
                    });
                }
            })
        })
        /*合约详情模态框*/
        $("#detailsContract").click(function () {
            if (checkedId <= 0) {
                layer.open({
                    title: '提示'
                    , content: '请选中行'
                });
                return false;
            } else {
                $.ajax({
                    type: "post",
                    url: "/companyContract/find",
                    data: {id: checkedId},
                    dataType: "json",
                    success: function (data) {
                        //console.log(data)
                        $("#fcompanyName").val(data.data.contractBean.companyBean.companyName);
                        $("#fcompanyNum").val(data.data.contractBean.oldContractNum);
                        $("#fcompanyPrice").val(data.data.contractBean.companyPrice);
                        $("#fcompanyStart").val(data.data.contractBean.startDate);
                        $("#fcompanyEnd").val(data.data.contractBean.endDate);
                    }
                })
            }
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
            // //获取选中行
            // table.on('row()', function (obj) {
            //     //获取选中行数据的ID
            //     checkedID = obj.data.id;
            //     //给选中行添加样式
            //     obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
            // });
            $.ajax({
                type: "post",
                url: "/companyContract/find",
                data: {"id": checkedId},
                dataType: "json",
                success: function (data) {
                    $("#a1").val(data.data.contractBean.companyBean.contractNum);
                    $("#b1").val(data.data.contractBean.companyBean.contactPerson);
                    $("#c1").val(data.data.contractBean.companyBean.contactTel);
                    $("#d1").val(data.data.contractBean.companyPrice);
                    $("#e1").val(data.data.contractBean.startDate);
                    $("#f1").val(data.data.contractBean.endDate);
                }
            })
            $("#zzzz").click(function () {
                $.ajax({
                    type: "post",
                    url: "/companyContract/deletes",
                    data: {contractNum:$("#a1").val(),
                        contactPerson:$("#b1").val(),
                        contactTel:$("#c1").val(),
                        companyPrice:$("#d1").val(),
                        startDate:$("#e1").val(),
                        endDate:$("#f1").val()
                    },
                    dataType: "json",
                    success: function (data) {
                        layer.open({
                            title: '提示'
                            ,content: '成功',
                            yes:function () {
                                window.parent.location.reload();//刷新父页面
                            }
                        });
                    }
                })
            })

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

        var checkStatus;
        /*添加车位*/
        $("#addcar").click(function () {
            //获取选中行
            table.on('row()', function (obj) {
                //获取选中行数据的ID
                checkedID = obj.data.id;
                //给选中行添加样式
                obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
            });
            //车位所在地址
            $.ajax({
                type: "post",
                url: "/cbdParking/findAllAddress",
                //data: {page: 0,size:100},
                dataType: "json",
                success: function (data) {
                    //console.log(data);
                    var opt;
                    for (var i = 0; i < data.data.list.length; i++) {
                        opt = $('<option value=' + data.data.list[i] + '>' + data.data.list[i] + '</option>');
                        $("#select1").append(opt);
                    }
                    form.render('select');
                }
            });

            layer.open({
                type: 1,
                title: '用户信息',
                area: ['600px', '700px'],
                shadeClose: false, //点击遮罩关闭
                content: $('#addCard'),
                end: function () {
                    $('#addCard').css("display", "none");
                }
            });

            var area;  //车位区域编号
            var address;//车位地址
            flush();
            //表格渲染
            function flush() {
                table.render({
                    elem: '#addCardTable',
                    url: '/cbdParking/findCbdParkingByArea' //数据接口
                    , size: 'sm' //小尺寸的表格
                    , height: 340
                    , request: {
                        pageName: 'page' //页码的参数名称，默认：page
                        , limitName: 'size' //每页数据量的参数名，默认：limit
                    },
                    id: 'idTest'
                    , where: {
                        address: address,
                        area: area
                    },
                    done: function () {
                        checkedID = null;
                    },
                    parseData: function (res) { //res 即为原始返回的数据
                        //console.log(res);
                        return {
                            "code": 0, //解析接口状态
                            "count": res.data.pages.total, //解析数据长度
                            "data": res.data.pages.records //解析数据列表
                        };
                    },
                    cols: [[
                        {type: 'checkbox'},
                        {field: 'id', title: 'cbd车位id'},//隐藏ID列
                        {field: 'areaNum', title: '区域编号'},
                        {field: 'parkingNum', title: '车位号码', sort: true}
                    ]],
                    page: true

                });
            }
            /*获得区域编号*/
            form.on('select(select1)', function (data) {
                //console.log(data.value); //得到被选中的值
                address = data.value;
                $.ajax({
                    type: "post",
                    url: "/cbdParking/findAreaByAddress",
                    data: {address: data.value},
                    dataType: "json",
                    success: function (data) {
                        //console.log(data);
                        $("#select2").html('<option value="" selected>全部</option>');
                        var opt;
                        for (var i = 0; i < data.data.list.length; i++) {
                            opt = $('<option value=' + data.data.list[i] + '>' + data.data.list[i] + '</option>');
                        }
                        $("#select2").append(opt);
                        form.render('select');
                    }
                });
                flush();
            });
            form.on('select(select2)', function (data) {
                //console.log(data);
                area = data.value;//得到被选中的值
                flush();
            });
            //添加
            $('#add_card').click(function () {
                checkStatus = table.checkStatus('idTest');
                console.log(checkStatus.data);
                //console.log(checkStatus.data.length);
                num=checkStatus.data.length;
                 $('#num').val(num);
                layer.close(layer.index);
                /*  var index=parent.layer.getFrameIndex(window.name);
                  parent.layer.close(index);*/
            });

        });
        
        $("#addPicture").click(function () {
            
        })
        /*新增租户合约弹出模态框按钮 新增合约*/
        $('#addAgreement').click(function () {
            if(checkStatus.data.length==0){
                layer.alert("请添加车位", {
                    title: '提示'
                })
            }
            else{
                var list=[];
                for (var i = 0; i < checkStatus.data.length; i++) {
                    list.push(checkStatus.data[i].id);
                }
                var params={
                    cbdParkingId:list
                };
                $.ajax({
                    type: "post",
                    url: "/companyContract/addCompanyContract",
                    data: {
                        companyId: $("#company").val(),
                        startDate: $("#startDate").val(),
                        endDate: $("#endDate").val(),
                        companyPrice:$("#companyContractPrice").val(),
                        cbdParkingId:JSON.stringify(params),
                        contractPicture:"123.jpg"
                    },
                    dataType: "json",
                    contentType:'application/json;charset=utf-8',
                    success: function (data) {
                        //console.log(data);
                        $("#select2").html('<option value="" selected>全部</option>');
                        var opt;
                        for (var i = 0; i < data.data.list.length; i++) {
                            opt = $('<option value=' + data.data.list[i] + '>' + data.data.list[i] + '</option>');
                        }
                        $("#select2").append(opt);
                        form.render('select');
                    }
                });
            }

        });
    });
});