
    $(function () {
        var teamId = location.search;
        var str=null;
        if (teamId.indexOf('?') !== -1) {
            str = teamId.substr(1);
        }
        $.ajax({
            type:"post"
            ,url:"/complaint/findById"
            ,data:{id:str}
            ,dataType:"json"
            ,success:function (data) {
                console.log(data);
                //事件记录
                $("#address").html(data.data.complaint.renthistoryBean.address);
                $("#parkingNum").html(data.data.complaint.renthistoryBean.parkingNum);
                $("#rentStartTime").html(data.data.complaint.renthistoryBean.rentStartTime);
                $("#rentEndTime").html(data.data.complaint.renthistoryBean.rentEndTime);
                //投诉用户
                $("#username1").html(data.data.complaint.personalComplainantBean.username);
                $("#relName1").html(data.data.complaint.personalComplainantBean.relName);
                $("#idCard1").html(data.data.complaint.personalComplainantBean.idCard);
                $("#jobDescription1").html(data.data.complaint.personalComplainantBean.jobDescription);
                $("#tel1").html(data.data.complaint.personalComplainantBean.tel);
                $("#complaintReason").html(data.data.complaint.complaintReason);
                //被投诉用户
                $("#username2").html(data.data.complaint.personalByUpholdingBean.username);
                $("#relName2").html(data.data.complaint.personalByUpholdingBean.relName);
                $("#idCard2").html(data.data.complaint.personalByUpholdingBean.idCard);
                $("#jobDescription2").html(data.data.complaint.personalByUpholdingBean.jobDescription);
                $("#tel2").html(data.data.complaint.personalByUpholdingBean.tel);

            }
        })

        $("#invalid").on('click',function (event) {
            $.ajax({
                type:"post",
                url:"/complaint/updateStatus",
                data:{id:str,status:'投诉生效'},
                dataType:"json",
                success:function(data){
                    if (data!=null) {
                        window.location.href="complaint.html";
                    }
                }
            });
        })
        $("#effect").on('click',function (event) {
            $.ajax({
                type:"post",
                url:"/complaint/updateStatus",
                data:{id:str,status:'投诉无效'},
                dataType:"json",
                success:function(data){
                    if (data!=null) {
                        window.location.href="complaint.html";
                    }
                }
            });
        })
    });
