/*预加载函数*/
$(function () {
    //手机号码格式判断，正则表达式。
    var regex = /^(13|14|15|17|18|19)[0-9]{9}$/;
    var phone;
    /*1、监听获取验证码的按钮*/
    $("#loadingButton").click(function () {
        /*2、获取电话号码*/
        phone = $("#phoneNum").val();
        /*3、正则表达式进行电话号码格式校验*/
        if (regex.exec(phone)) {
            /*4、发送ajax请求至后台*/
            $.getJSON("../../../registerCode", {phoneNumber: phone}, registerCode)
        } else {
            alert("电话号码格式不正确，请检查！")
        }
    });

    /*5、发送验证码的回调函数*/
    function registerCode(data) {
        console.log(data);
        if (data.code == 200) {
            alert("验证码已发送，请注意查收！")
        } else {
            /*6、验证码发送失败提示语句*/
            alert(data.message);
        }
    }
    /*-----------------------表单验证插件------------------------------*/
    /*from-register：表单按钮ID*/
    $("#from-register").bootstrapValidator({
        //验证时机，enabled是内容有变化就验证（默认），
        // disabled和submitted是提交再验证
        live: 'enabled',
        //排除无需验证的控件，比如被禁用的或者被隐藏的
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        //指定提交按钮，如果验证失败则变成disabled
        submitButtons: '#registerUser',
        message: '通用的验证失败消息',//好像从来没出现过
        feedbackIcons: {//根据验证结果显示的各种图标
            valid: 'glyphicon glyphicon-ok'
            , invalid: 'glyphicon glyphicon-remove'
            // ,validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            /*输入控件中的name属性--用户名字*/
            username: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {//检测非空,radio也可用
                        message: '不能为空'
                    },
                    regexp: {//正则验证
                        regexp: /^[a-zA-Z0-9_]{5,20}$/,
                        message: '由5-20位英文字母数字下划线组成'
                    },
                    threshold: 6,
                    remote: {//ajax验证。server result:{"valid",true or false}
                        url: "../../../registerIsUsername",
                        message: '用户名已存在,请重新输入!',
                        delay: 3000,//ajax刷新的时间是3秒一次
                        type: 'POST'
                        //自定义提交数据，默认值提交当前input value
                        //,data: {username: $("#register-username").val()}
                    }
                }
            },
            /*输入控件中的name属性--真实姓名*/
            relName: {
                message: '真实姓名验证失败',
                validators: {
                    notEmpty: {//检测非空,radio也可用
                        message: '不能为空'
                    },
                    stringLength: {//检测长度
                        min: 2,
                        max: 30,
                        message: '长度必须在2-30之间'
                    }
                }
            },
            /*输入控件中的name属性--密码*/
            password: {
                message: '密码验证失败',
                validators: {
                    notEmpty: {//检测非空,radio也可用
                        message: '不能为空'
                    },
                    stringLength: {//检测长度
                        min: 6,
                        max: 18,
                        message: '长度必须在6-18之间'
                    }
                }
            },
            /*输入控件中的name属性--密码确认*/
            passwordConfirm: {
                message: '密码验证失败',
                validators: {
                    notEmpty: {//检测非空,radio也可用
                        message: '不能为空'
                    },
                    stringLength: {//检测长度
                        min: 6,
                        max: 18,
                        message: '长度必须在6-18之间'
                    },
                    identical: {//与指定控件内容比较是否相同，比如两次密码不一致
                        field: 'password',//指定控件name
                        message: '两次密码输入不一致'
                    }
                }
            },
            /*输入控件中的name属性--家庭住址*/
            address: {
                message: '地址验证失败',
                validators: {
                    notEmpty: {//检测非空,radio也可用
                        message: '不能为空'
                    },
                    stringLength: {//检测长度
                        min: 3,
                        max: 30,
                        message: '长度必须在3-30之间'
                    }
                }
            },
            /*输入控件中的name属性--身份证号码*/
            idCard: {
                message: '身份证验证失败',
                validators: {
                    notEmpty: {//检测非空,radio也可用
                        message: '不能为空'
                    },
                    regexp: {//正则验证
                        regexp: /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/,
                        message: '长度必须为18位的合法字符'
                    }
                }
            },
            /*输入控件中的name属性--职业*/
            jobDescription: {
                message: '职业验证失败',
                validators: {
                    notEmpty: {//检测非空,radio也可用
                        message: '不能为空'
                    },
                    stringLength: {//检测长度
                        min: 1,
                        max: 18,
                        message: '长度必须在1-18之间'
                    }
                }
            },
            /*输入控件中的name属性--邮箱*/
            email: {
                message: '邮箱验证失败',
                validators: {
                    notEmpty: {//检测非空,radio也可用
                        message: '不能为空'
                    },
                    emailAddress: {//验证email地址
                        message: '不是正确的email地址'
                    }
                }
            },
            /*输入控件中的name属性--电话*/
            tel: {
                message: '电话验证失败',
                validators: {
                    notEmpty: {//检测非空,radio也可用
                        message: '不能为空'
                    },
                    stringLength: {//检测长度
                        min: 11,
                        max: 12,
                        message: '长度必须为11位手机号码'
                    }, regexp: {//正则验证
                        regexp: /^(13|14|15|17|18|19)[0-9]{9}$/,
                        message: '输入的手机号码格式不正确'
                    }
                }
            },
            /*输入控件中的name属性--验证码*/
            salt: {
                message: '验证码不合法',
                validators: {
                    notEmpty: {//检测非空,radio也可用
                        message: '不能为空'
                    },
                    stringLength: {//检测长度
                        min: 6,
                        max: 6,
                        message: '验证码长度必须为6位'
                    }
                }
            }
        }
    });
    /*6、监听注册用户的按钮*/
    $("#registerUser").click(function () {
        //提交验证
        $("#from-register").bootstrapValidator('validate');
        //获取验证结果，如果成功，执行下面代码
        if ($("#from-register").data('bootstrapValidator').isValid()) {
            //序列化获得表单数据，结果为：user_id=12&user_name=John&user_age=20
            var data = $('#from-register').serialize();
            var submitData = decodeURIComponent(data);
            $.ajax({
                //请求服务器地址
                url: "../../../registerPersonal",
                //请求方法
                type: "post",
                //请求类型
                datatype: "json",
                //页面数据
                data: submitData,
                //成功回调函数
                success: function (data) {
                    alert(data.message);
                    if (data.code==200){
                        window.location.href="login.html";
                    }
                }
            });
        }
    })
});

