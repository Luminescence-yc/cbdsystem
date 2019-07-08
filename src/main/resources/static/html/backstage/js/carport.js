
$(function () {
layui.use('table', function(){
    var table = layui.table;
    var id;
    table.render({
        elem: '#test'
        ,height: 400
        ,id:'idTest'
        ,url:'../../cbdParking/findByArea'
        ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,request: {
                pageName: 'page' //页码的参数名称，默认：page
                , limitName: 'size' //每页数据量的参数名，默认：limit
            }
        ,parseData: function (res) { //res 即为原始返回的数据
                console.log(res)
                return {
                    "code": res.code, //解析接口状态
                    "msg": 0, //解析提示文本
                    "status": res.code, //解析接口状态
                    "count": res.data.pages.total, //解析数据长度
                    "list": res.data.pages.records //解析数据列表
                };
            }
        ,response: {
                statusName: 'status' //规定数据状态的字段名称，默认：code
                , statusCode: 200 //规定成功的状态码，默认：04a
                , msgName: 'hint' //规定状态信息的字段名称，默认：msg
                , countName: 'count' //规定数据总数的字段名称，默认：count
                , dataName: 'list' //规定数据列表的字段名称，默认：data
            }
        // ,where: {//动态查询条件
        //     address: address,
        //     areaNum: areaNum,
        //
        //     }
        ,cols: [[
            {field:'id', title: 'ID', sort: true}
            ,{field:'parkingNum', title: '车位编号'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
            ,{field:'address', title: '车位所在地址'}

        ]]
        ,page:true
    });
    //获取选中行
    table.on('row()', function (obj) {
        //获取选中行数据的ID
        console.log(obj.data.id);
        id = obj.data.id;
        //给选中行添加样式
        obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');

    });
    $("#carport").click(function () {
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

    var data;
    //监听行单击事件（单击事件为：rowDouble）
    table.on('row(test)', function (obj) {
        data = obj.data;
        //标注选中样式
        obj.tr.addClass('yanse').siblings().removeClass('yanse');
    });
    $("#search").click(function () {
        if (data == null) {
            layer.open({
                title: '提示'
                , content: '请选中行'
            });
            return false;
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
                $("#searchCarport").css('display', 'none');
            }
        });
    })
});


})