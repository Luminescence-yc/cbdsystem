$(function () {
    //个人资料刷新
    var push = function(){
        $.ajax({
            type: "post",
            url: '../../../admin/showAdminMessage',
            dataType: "json",
            success: function (resultData) {
                if (resultData.length != 0 && resultData != null) {
                    //把查询到值传入相对应的位置
                    $("#userId").val(resultData.data.adminBean.username);
                    $("#userName").val(resultData.data.adminBean.realName);
                    $("#userTel").val(resultData.data.adminBean.tel);
                    var str = "";
                    //遍历权限集合
                    for (var i = 0; i < 4; i++) {
                        //判断集合内的内容
                        if (resultData.data.adminBean.list[i] != null) {
                            //得到权限的字符串
                            var date = resultData.data.adminBean.list[i].roleName;
                            console.log(date);
                            if ("个人用户" == date){
                                str += "个人管理";
                            } else if ("企业用户" == date){
                                str += "  企业管理";
                            } else if ("用户管理员" == date){
                                str += "  用户管理";
                            } else if ("车位管理员" == date){
                                str += "  车位管理";
                            } else if ("合同管理员" == date){
                                str += "  合同管理";
                            } else if ("投诉管理员" == date){
                                str += "  投诉管理";
                            } else if ("超级管理员" == date){
                                str += "  超级管理";
                            }
                        }
                    }
                    $("#userAccount").val(str);
                    //遍历得到的权限集合
                }
            }
        });
    }
    //进入页面自动刷新
    push();
    //修改个人电话页面信息展示
    $("#updateMessage").click(function () {
        //刷新页面
        push();
    })

    //提交个人电话信息
    $("#userCommit").click(function () {
        $.ajax({
            type: "post",
            url: '../../../admin/updateAdminTelById',
            data: {
                "tel": $("#userTel").val()
            },
            dataType: "json",
            success: function (resultData) {
                if (resultData.code == 200) {
                    layer.msg('修改成功');
                } else {
                    layer.msg("修改失败");
                }
            }
        });
    });





    //修改个人密码页面信息展示
    $("#updatePassword").click(function () {
        $.ajax({
            type: "post",
            url: '../../../admin/showAdminMessage',
            dataType: "json",
            success: function (resultData) {
                if (resultData.length != 0 && resultData != null) {
                    //把查询到值传入相对应的位置
                    $("#passwordId").val(resultData.data.adminBean.username);
                }
            }
        });
    })

    //提交修改密码信息
    $("#commitPassword").click(function () {
        //判断两次输入的密码是否一致
        if ($("#newPassword").val() == $("#againNewPassword").val()){
            $.ajax({
                type: "post",
                url: '../../../admin/updateAdminTelById',
                data: {
                    "password": $("#newPassword").val()
                },
                dataType: "json",
                success: function (resultData) {
                    console.log(resultData);
                    if (resultData.code == 200) {
                        layer.msg('修改成功');
                    } else {
                        layer.msg("修改失败");
                    }
                }
            });
        }else {
            layer.msg("密码不一致，请重新输入");
        }

    })
});

