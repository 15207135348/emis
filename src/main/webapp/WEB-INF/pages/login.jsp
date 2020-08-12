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
    <link rel="icon" href="${ctx}/resources/favicon1.ico">
    <link rel="stylesheet" href="../../resources/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${ctx}/resources/css/public.css" media="all"/>
</head>
<body class="loginBody">
<form class="layui-form" id="loginFrm" method="post" action="${ctx }/auth/login_by_password.action">
    <div class="login_face"><img src="${ctx}/resources/images/face.jpg" class="userAvatar"></div>
    <div class="layui-form-item input-item">
        <label for="e_account">账户</label>
        <input type="text" placeholder="请输入账户名" autocomplete="off" id="e_account" name="e_account" class="layui-input"
               lay-verify="required">
    </div>
    <div class="layui-form-item input-item">
        <label for="e_password">密码</label>
        <input type="password" placeholder="请输入密码" autocomplete="off" id="e_password" name="e_password"
               class="layui-input" lay-verify="required">
    </div>
    <div class="layui-form-item input-item" id="imgCode">
        <label for="code">验证码</label>
        <input type="text" placeholder="请输入验证码" autocomplete="off" id="code" name="code" class="layui-input"
               lay-verify="required">
        <img src="${ctx}/auth/changeCode.action" id="imgCode_image">
    </div>
    <div class="layui-form-item">
        <button class="layui-btn layui-block" lay-filter="login" lay-submit>登录</button>
    </div>
    <div class="layui-form-item layui-row" style="text-align: center;color: red;">
        ${error }
        <!-- 	<a href="javascript:;" class="seraph icon-qq layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>
            <a href="javascript:;" class="seraph icon-wechat layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>
            <a href="javascript:;" class="seraph icon-sina layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a> -->
    </div>
</form>

<script type="text/javascript" src="${ctx}/resources/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/cache.js"></script>
<script type="text/javascript">
    layui.use(['form', 'layer', 'jquery'], function () {
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

        //登录按钮
        form.on("submit(login)", function (data) {
            $(this).text("登录中...").attr("disabled", "disabled").addClass("layui-disabled");



            // console.log("登录中")
            // var account = document.getElementById("e_account").value;
            // var password = document.getElementById("e_password").value;
            // var code = document.getElementById("code").value;
            // console.log("value:" + account + password + code);
            // $.ajax({
            //     url: "/auth/login_by_password.action",
            //     data: {
            //         'e_account': account,
            //         'e_password': password,
            //         'code': code
            //     },
            //     type: "post",
            //     dataType: "json",
            //     success: function (data) {
            //         console.log(data);
            //         window.location.href = 'index.jsp'
            //     },
            //     error: function (data) {
            //     }
            // });



            setTimeout(function () {
                $("#loginFrm").submit();
            }, 1000);

            return false;
        });

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
        })

        var image = document.getElementById("imgCode_image");
        image.onclick = function (ev) {
            image.setAttribute("src", "http://localhost:8080/auth/changeCode.action");
            image.update()
        };
    })

</script>
</body>
</html>