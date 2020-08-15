layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //新闻列表
    var tableIns = table.render({
        elem: '#newsList',
        url: '/attendance/get_my_attendance_record.action',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limit: 20,
        limits: [10, 15, 20, 25],
        id: "newsListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'a_id', title: 'ID', width: 60, align: "center"},
            {field: 'e_account', title: '员工账号', align: 'center'},
            {field: 'e_name', title: '员工姓名', align: 'center'},
            {field: 'a_type', title: '打卡类型', align: 'center'},
            {field: 'a_time', title: '打卡时间', align: 'center', minWidth: 150},
            {title: '操作', width: 170, templet: '#newsListBar', fixed: "right", align: "center"}
        ]]
    });

    //是否置顶
    form.on('switch(newsTop)', function (data) {
        var index = layer.msg('修改中，请稍候...', {icon: 16, time: false, shade: 0.8});
        setTimeout(function () {
            layer.close(index);
            if (data.elem.checked) {
                layer.msg("置顶成功！");
            } else {
                layer.msg("取消置顶成功！");
            }
        }, 500);
    })

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
        if ($(".searchVal").val() != '') {
            table.reload("newsListTable", {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            })
        } else {
            layer.msg("请输入要搜索的内容");
        }
    });

    //添加文章
    function addNews(edit) {
        var index = layui.layer.open({
            title: "添加考勤记录",
            type: 2,
            content: "newsAdd.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    var type_code = edit.a_type === '上班打卡' ? "1" : "2";
                    body.find(".a_id").val(edit.a_id);
                    body.find(".userCode").val(edit.e_account);
                    body.find(".userName").val(edit.e_name);
                    body.find(".a_type input[value=" + type_code + "]").prop("checked", "checked");  //性别
                    body.find(".a_time").val(edit.a_time);
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回考勤记录列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(index);
        })
    }

    $(".addNews_btn").click(function () {
        addNews();
    })

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('newsListTable'),
            data = checkStatus.data,
            aids = [];
        if (data.length > 0) {
            for (var i in data) {
                aids.push(data[i].a_id);
            }
            layer.confirm('确定删除选中的考勤记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/attendance/del_attendance_info.action", {
                    idList: aids.join(',')
                }, function (data) {
                    tableIns.reload();
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要删除的文章");
        }
    });

    //列表操作
    table.on('tool(newsList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            addNews(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此考勤记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/attendance/del_attendance_info.action", {
                    idList: data.a_id
                }, function (data) {
                    tableIns.reload();
                    layer.close(index);
                })
            })
        } else if (layEvent === 'look') { //预览
            layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
        }
    });
});