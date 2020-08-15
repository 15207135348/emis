layui.use(['form', 'layer', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url: '/employee/get_employee_info.action',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 20,
        id: "userList",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'e_account', title: '账号', minWidth: 100, align: "center"},
            {field: 'e_name', title: '姓名', minWidth: 100, align: "center"},
            {field: 'e_sex', title: '性别', align: 'center'},
            {field: 'e_birthday', title: '生日', align: 'center'},
            {field: 'e_email', title: '邮箱', minWidth: 200, align: 'center'},
            {field: 'e_phone', title: '手机', minWidth: 150, align: "center"}
            ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {
        if ($(".searchVal").val() != '') {
            tableIns.reload({
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    e_account: $(".searchVal").val()
                }
            });
        } else {
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addUser(edit) {
        var index = layui.layer.open({
            title: "-",
            type: 2,
            content: "userAdd_2.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    var sex_code = edit.e_sex === '男' ? "1" : "0";
                    body.find(".userCode").val(edit.e_account);  //登录名
                    body.find(".userName").val(edit.e_name);  //登录名
                    body.find(".birthday").val(edit.e_birthday);  //登录名
                    body.find(".userSex input[value="+sex_code+"]").prop("checked","checked");  //性别
                    body.find(".userPhone").val(edit.e_phone);  //电话
                    body.find(".userEmail").val(edit.e_email);  //邮箱
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index", index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    $(".addNews_btn").click(function () {
        addUser();
    })

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('userList'),
            data = checkStatus.data,
            accounts = [];
        if (data.length > 0) {
            for (var k in data) {
                accounts.push(data[k].e_account);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/employee/del_employee_info.action",{
                    accountList : accounts.join(',')
                },function(data){
                    tableIns.reload();
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function (obj) {
        var layEvent = obj.event, data = obj.data;
        if (layEvent === 'edit') { //编辑
            addUser(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此用户？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/employee/del_employee_info.action",{
                    accountList : data.e_account
                },function(data){
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });

})
