layui.use(['form', 'layer', 'laydate'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    var laydate = layui.laydate;
    //执行一个laydate实例
    laydate.render({
        elem: '#birthday' //指定元素
    });


    form.on("submit(addUser)", function (data) {
        // 弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post("/employee/set_employee_info.action", {
            e_account: $(".userCode").val(),  //登录名
            e_name: $(".userName").val(),  //名字
            e_birthday: $(".birthday").val(),  //生日
            e_sex: $(".userSex").val(),  //性别
            e_phone: $(".userPhone").val(),  //电话
            e_email: $(".userEmail").val()
        }, function (res) {

        });
        setTimeout(function () {
            top.layer.close(index);
            top.layer.msg("用户添加成功！");
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        }, 2000);
        return false;
    });
});