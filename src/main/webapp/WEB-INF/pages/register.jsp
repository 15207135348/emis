<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
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
<body>

<form class="layui-form layui-row">

    <div class="layui-col-md6 layui-col-xs12">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" placeholder="请输入账户名" lay-verify="required|userCode" id="userCode" class="layui-input userCode">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="text" placeholder="请输入密码" lay-verify="required|password" class="layui-input password">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="text" placeholder="请输入确认密码" lay-verify="required|confirmPwd" class="layui-input confirmPwd">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">真实姓名</label>
            <div class="layui-input-block">
                <input type="text" value="" placeholder="请输入真实姓名" lay-verify="required" class="layui-input userName">
            </div>
        </div>
        <div class="layui-form-item" pane="">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block userSex">
                <input type="radio" name="sex" value="1" title="男" checked>
                <input type="radio" name="sex" value="0" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电话号码</label>
            <div class="layui-input-block">
                <input type="tel" value="" placeholder="请输入手机号码" lay-verify="phone" class="layui-input userPhone">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="email" value="" placeholder="请输入邮箱地址" lay-verify="email" class="layui-input userEmail">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">生日</label>
            <div class="layui-input-block">
                <input type="text" value="" id="birthday" placeholder="请输入出生年月日" lay-verify="required" readonly class="layui-input userBirthday">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="register">注册</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>

</body>
<script type="text/javascript" src="${ctx}/resources/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/cache.js"></script>
<script type="text/javascript">
    layui.use(['form', 'layer', 'jquery', 'laydate'], function () {
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#birthday' //指定元素
        });

        //提交个人资料
        form.on("submit(register)", function (data) {

            var password = $(".password").val();
            var confirmPwd = $(".confirmPwd").val();
            if(!new RegExp(password).test(confirmPwd)){
                layer.msg("两次输入密码不一致，请重新输入！");
                return;
            }
            //将填写的用户信息存到session以便下次调取
            $.get("/auth/register.action", {
                'e_account': $(".userCode").val(),
                'e_password': $(".password").val(),
                'e_name': $(".userName").val(),
                'e_sex' : $(".userSex input:checked").val(),
                'e_phone': $(".userPhone").val(),
                'e_email': $(".userEmail").val(),
                'e_birthday': $(".userBirthday").val()
            }, function (res) {
                if(res['code'] === 0){
                    window.location.href = "/index/toLogin.action";
                }
                else {
                    layer.msg(res['msg']);
                    location.reload();
                }
            });
        });
    })
</script>
</html>