$(function () {
    let companyName, username, floor, contactPerson, tel;
    var checkedID = 0, userId = 0;//选中行ID;
    layui.use(['table', 'layer'], function () {
        var table = layui.table,
            layer = layui.layer;
        //获取选中行
        table.on('row()', function (obj) {
            console.log(obj);
            //获取选中行数据的ID
            checkedID = obj.data.id;
            userId = obj.data.userId;
            // console.log(checkedID);
            //给选中行添加样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });
        flush();
        drugg();
        //条件查询按钮
        $("#pageFindByage").click(function () {
            companyName = document.getElementById("companyName").value;
            username = document.getElementById("username").value;
            floor = document.getElementById("floor").value;
            contactPerson = document.getElementById("contactPerson").value;
            tel = document.getElementById("tel").value;
            flush();
        });

        function drugg() {
            $.ajax({
                type: "post",
                url: "/company/findCompanyById",
                dataType: "json",
                data: {id: checkedID},
                success: function (data) {
                    console.log(data);
                    console.log(id);
                    if (data === "ok") {
                        window.location.href = "enterpriseUser.html";
                    }
                }
            })
        }
        //表格数据刷新
        function flush() {
            table.render({
                done: function () { //渲染完成后回调函数
                    checkedID=null;
                },
                parseData: function (res) {//res 即为原始返回的数据
                    console.log(res);
                    // console.log(username);
                    return {
                        "code": 0, //解析接口状态
                        "msg": "", //解析提示文本
                        "count": res.data.pages.total,//解析数据长度
                        "data": res.data.pages.records //解析数据列表
                    };
                },
                url: '/company/findCompanyByCondition' //数据接口
                , elem: '#demo'
                , method: 'post'
                , toolbar: '#toolbarDemo'
                , where: {
                    companyName: companyName, username: username
                    , floor: floor, contactPerson: contactPerson, tel: tel
                }
                , request: {
                    pageName: 'page' //页码的参数名称，默认：page
                    , limitName: 'size' //每页数据量的参数名，默认：limit
                },
                cols: [[
                    {field: 'companyName', title: '企业名称'}
                    , {field: 'userId', title: "用户id"}
                    , {field: 'username', title: '企业用户名'}
                    , {field: 'floor', title: '企业楼层位置'}
                    , {field: 'contactPerson', title: '企业联系人'}
                    , {field: 'tel', title: '企业联系电话'}
                ]],
                page: true //开启分页
            });

            $('#addBtn').click(function () {
                openModak('addSuiness.html', '新增企业');
                flush();
            })
            $('#updateBtn').click(function () {
                if (checkedID > 0) {
                    openModak('updateSuiness.html?id=' + checkedID + '&userId=' + userId, '修改企业');
                } else {
                    layer.alert("请选中行", {
                        title: '提示'
                    })
                }
            })
            //删除按钮
            $("#deleBtn").click(function () {
                if (checkedID > 0) {
                    layer.confirm('确定删除吗？', {
                        btn: ['确定', '取消'] //按钮
                    }, function () {
                        layer.closeAll('dialog');
                        $.ajax({
                            type: "post",
                            url: "/company/deleteCompany",
                            data: {'id': checkedID, 'userId': userId},
                            dataType: "json",
                        })
                        flush();
                        return false;
                    });
                } else {
                    layer.alert("请选中行", {
                        title: '提示'
                    })
                }
            });
        }
    });

    function openModak(url, title) {
        layui.use(['layer'], function () {
            var layer = layui.layer
            layer.open({
                type: 2,//类型
                offset: '50px',
                area: ['500px', '600px'],//定义宽和高
                title: title,//题目
                shadeClose: false,//点击遮罩层关闭
                content: url//打开的内容
            });
        })
    }
});
