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
        var index = top.layer.msg('数据提交中，请稍候...', {icon: 16, time: false, shade: 0.8});
        var exec = false;
        var e_account = $(".userCode").val(),
            e_name = $(".userName").val(),
            e_birthday = $(".birthday").val(),
            e_sex = $(".userSex input:checked").val(),
            e_phone = $(".userPhone").val(),
            e_email = $(".userEmail").val(),
            e_role_id = $(".userGrade input:checked").val();


        // 实际使用时的提交信息
        $.get("/employee/add_employee_info.action", {
            e_account: e_account,  //登录名
            e_name: e_name,  //名字
            e_birthday: e_birthday,  //生日
            e_sex: e_sex,  //性别
            e_phone: e_phone,  //电话
            e_email: e_email,  //邮箱
            e_role_id: e_role_id
        }, function (res) {
            if (!exec) {
                if(res['code'] === 0){
                    top.layer.close(index);
                    top.layer.msg(res["msg"]);
                    layer.closeAll("iframe");
                    parent.location.reload();
                }else {
                    layer.confirm(res['msg'], {icon: 3, title: '提示信息'}, function (index) {
                        layer.close(index);
                    })
                }
                exec = true;
            }
        });
        setTimeout(function () {
            if (!exec) {
                top.layer.close(index);
                top.layer.msg("操作成功");
                layer.closeAll("iframe");
                parent.location.reload();
                exec = true;
            }
        }, 2000);
        return false;
    });
});