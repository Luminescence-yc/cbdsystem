$(function () {
   $.ajax({
       type:"post",
       url:"/personalInfo/getPersonalById",
       dataType:"json",
       success:function (datas) {
           $("#username").val(datas.data.personal.username);
           $("#realName").val(datas.data.personal.relName);
           $("#address").val(datas.data.personal.address);
           $("#tel").val(datas.data.personal.tel);
           $("#idCard").val(datas.data.personal.idCard);
           $("#profession").val(datas.data.personal.jobDescription);
           $("#email").val(datas.data.personal.email)
       }
   });
    $("#btn_info").click(function () {
        $.ajax({
            type:"post",
            url:"/personalInfo/getPersonalById",
            dataType:"json",
            success:function (datas) {
                $("#personalId").val(datas.data.personal.id);
                $("#username1").val(datas.data.personal.username);
                $("#realName1").val(datas.data.personal.relName);
                $("#address1").val(datas.data.personal.address);
                $("#tel1").val(datas.data.personal.tel);
                $("#idCard1").val(datas.data.personal.idCard);
                $("#profession1").val(datas.data.personal.jobDescription);
                $("#email1").val(datas.data.personal.email)
            }
        });
    })
    $("#updatePersonal").click(function () {
        $.ajax({
            type:"post",
            url:"/personalInfo/updatePersonalInfoById",
            data:{
                "address":$("#address1").val(),
                "tel":$("#tel1").val(),
                "jobDescription":$("#profession1").val(),
                "email":$("#email1").val(),
                "password":$("#password").val()},
            dataType:"json",
            success:function (datas) {
                console.log(datas)
                if(datas.message=="ok"){
                    alert("修改成功")
                    window.location.href="personal.html"
                }else {
                    alert("修改失败")
                }
            }
        })
    })
})
