<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="loginHtml">
<head>
    <meta charset="utf-8">
    <title>登录-员工信息管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="${ctx}/resources/favicon.ico">
    <link rel="stylesheet" href="../../resources/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${ctx}/resources/css/public.css" media="all"/>
</head>
<body class="loginBody">
<form class="layui-form" id="loginFrm" method="post">
    <div class="login_face"><img src="${ctx}/resources/images/face.jpg" class="userAvatar"></div>
    <div class="layui-form-item input-item">
        <label for="e_account">账户</label>
        <input type="text" placeholder="请输入账户名" autocomplete="off" id="e_account" name="e_account" class="layui-input">
    </div>
    <div class="layui-form-item input-item">
        <label for="e_password">密码</label>
        <input type="password" placeholder="请输入密码" autocomplete="off" id="e_password" name="e_password"
               class="layui-input">
    </div>
    <div class="layui-form-item input-item" id="imgCode">
        <label for="code">验证码</label>
        <input type="text" placeholder="请输入验证码" autocomplete="off" id="code" name="code" class="layui-input">
        <img src="${ctx}/auth/changeCode.action" id="imgCode_image">
    </div>
    <div class="layui-form-item">
        <button class="layui-btn layui-block" lay-filter="login" lay-submit id="loginBtn">登录</button>
    </div>
    <div class="layui-form-item">
        <button class="layui-btn layui-btn-warm layui-block" lay-filter="register" lay-submit id="registerBtn">注册
        </button>
    </div>
</form>

<script type="text/javascript" src="${ctx}/resources/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/cache.js"></script>
<script type="text/javascript">
    layui.use(['form', 'layer', 'jquery'], function () {
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

        var data = JSON.parse(window.sessionStorage.getItem("registerUser"));
        if (data) {
            var e_account = document.getElementById("e_account");
            var e_password = document.getElementById("e_password");
            e_account.value = data['e_account'];
            e_password.value = "";
        }
        document.getElementById("loginBtn").onclick = function (ev) {
            $(this).text("登录中...").attr("disabled", "disabled").addClass("layui-disabled");
            var account = document.getElementById("e_account").value;
            var password = document.getElementById("e_password").value;
            var code = document.getElementById("code").value;
            $.get("${ctx }/auth/login_by_password.action", {
                e_account: account,
                e_password: password,
                code: code
            }, function (res) {
                if (res['code'] === 0) {
                    window.localStorage.setItem("userInfo", JSON.stringify(res['data']));
                    window.location.href = '/index/toMain.action';
                } else {
                    layer.confirm(res['msg'], {icon: 3, title: '提示信息'}, function (index) {
                        layer.close(index);
                        location.reload();
                    });
                }
            });
        };

        document.getElementById("registerBtn").onclick = function (ev) {
            window.open("/index/toRegister.action");
            // window.location.href = '/index/toLogin.action';
        };


        //表单输入效果
        $(".loginBody .input-item").click(function (e) {
            e.stopPropagation();
            $(this).addClass("layui-input-focus").find(".layui-input").focus();
        })
        $(".loginBody .layui-form-item .layui-input").focus(function () {
            $(this).parent().addClass("layui-input-focus");
        })
        $(".loginBody .layui-form-item .layui-input").blur(function () {
            $(this).parent().removeClass("layui-input-focus");
            if ($(this).val() != '') {
                $(this).parent().addClass("layui-input-active");
            } else {
                $(this).parent().removeClass("layui-input-active");
            }
        });

        var image = document.getElementById("imgCode_image");
        image.onclick = function (ev) {
            $("#imgCode_image").attr("src", "http://localhost:8080/auth/changeCode.action?t=" + +Math.random());
        };


    })

</script>
</body>
</html>