layui.use(['form', 'layer'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addNews)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post("/attendance/set_employee_attendance_record", {
            e_account: $(".userCode").val(),
            e_name: $(".userName").val(),
            e_type: $(".e_type").val(),
            e_time: $(".e_time").val()
        }, function (res) {

        })
        setTimeout(function () {
            top.layer.close(index);
            top.layer.msg("修改成功！");
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        }, 2000);
        return false;
    })
})