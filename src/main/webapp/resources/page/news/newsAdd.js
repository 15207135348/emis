layui.use(['form', 'layer', 'laydate'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    var laydate = layui.laydate;
    //执行一个laydate实例
    laydate.render({
        elem: '#a_time' //指定元素
        ,type: 'datetime'
    });

    form.on("submit(addNews)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.get("/attendance/set_employee_attendance_record.action", {
            a_id: $(".a_id").val(),
            a_type: $(".a_type input:checked").val(),
            a_time: $(".a_time").val()
        }, function (res) {
            top.layer.close(index);
            top.layer.msg("修改成功！");
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        });
        return false;
    })
})