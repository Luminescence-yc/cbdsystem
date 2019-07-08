$(function () {
    var checkedID = 0;//选中行ID;
    layui.use(['table', 'laydate'], function () {
        var table = layui.table,
            laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#startTime' //指定元素
        });

        laydate.render({
            elem: '#endTime'
        });
        //获取选中行
        table.on('row()', function (obj) {
            //获取选中行数据的ID
            checkedID = obj.data.id;
            //给选中行添加样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });
        var tb=table.render({
            elem: '#log',
            //height: 400,
            url: '/log/findLog',
            toolbar: '#toolbar',//头部工具栏

            request: {
                pageName: 'currentPage' //页码的参数名称，默认：page
                , limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            done: function () { //渲染完成后回调函数

            },
            parseData: function (res) { //res 即为原始返回的数据
                console.log(res);
                if (res.code === -1) {
                    alert(res.message);
                } else if (res.code === 0) {
                    return {
                        "code": 0, //解析接口状态
                        "count": res.data.page.total, //解析数据长度
                        "data": res.data.page.records //解析数据列表
                    };
                }
            },
            cols: [[
                {field: 'username', title: '用户名'},
                {field: 'operationTime', title: '操作日期'},
                {field: 'ip', title: 'IP'},
                {field: 'operationRecord', title: '操作内容'},
                {field: 'logType', title: '操作属性'},
                {field: 'logBelong', title: '操作类型'}
            ]],
            page: true
        });
        //条件查询按钮
        $("#formBtn").click(function () {
            tb.reload({
                where: formToJson()//请求附带参数
            });
        });

        //获取form表单数据，转为json对象
        function formToJson() {
            //序列化表单数据
            var data = $("#logForm").serialize();
            data = decodeURIComponent(data, true);//防止中文乱码
            data = data.replace(/&/g, "','");
            data = data.replace(/=/g, "':'");
            data = "({'" + data + "'})";
            obj = eval(data);   //字符串转json对象
            //去除空数据
            $.each(obj, function (key, value) {
                if (value == null || value.trim() === "") {
                    delete obj[key];
                }
            });
            return obj;
        }
    });

});