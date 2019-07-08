$(function () {
    layui.use(['table', 'layer', 'jquery'], function () {
        var table = layui.table,
            layer = layui.layer,
            $ = layui.jquery;
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
                elem: '#carTable'
                , height: 443
                ,toolbar:'#toolbarDemo'
                , url: '/companyContract/findByCondition' //数据接口
                , size: 'sm' //小尺寸的表格
                ,request: {
                    pageName: 'page' //页码的参数名称，默认：page
                    ,limitName: 'size' //每页数据量的参数名，默认：limit
                }
                , where: {
                    companyName: $("#companyName").val(),
                    contractNum: $("#contractNum").val(),
                    companyPrice: $("#companyPrice").val()
                }
                , page: true //开启分页
                , parseData: function (res) { //res 即为原始返回的数据
                    console.log(res);
                    return {
                        "code": 0, //解析接口状态
                        "msg": "", //解析提示文本
                        "count": res.data.pages.total, //解析数据长度
                        "data": res.data.pages.records //解析数据列表
                    };
                }
                , cols: [[ //表头

                    {field: 'id', title: 'cbd车位id'},//隐藏ID列
                    {field: 'id', title: '区域编号'},
                    {field: 'id', title: '车位号码', sort: true}
                ]]
            });
        };
        req();
        $("#contractFind").click(function () {
            req();
        });



        /*添加车位*/
        $("#addcar").click(function () {
            //获取选中行
            table.on('row()', function (obj) {
                //获取选中行数据的ID
                checkedID = obj.data.id;
                //给选中行添加样式
                obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
            });
            //表格渲染
            table.render({
                elem: '#addCardTable',
                height: 200,
                url: '../json/data.json',
                done: function () {
                    checkedID = null;
                },
                plarseData: function (res) { //res 即为原始返回的数据
                    return {
                        "code": 0, //解析接口状态
                        "count": 100, //解析数据长度
                        "data": res.data //解析数据列表
                    };
                },
                cols: [[
                    {field: 'id', title: 'cbd车位id'},//隐藏ID列
                    {field: 'id', title: '区域编号'},
                    {field: 'id', title: '车位号码', sort: true}
                ]],
                page: true
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


        })
    });

})