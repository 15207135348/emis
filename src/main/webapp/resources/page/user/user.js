layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //添加验证规则
    form.verify({
        newPwd : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
        confirmPwd : function(value, item){
            if(!new RegExp($("#newPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }else {
                var oldPwd = $("#oldPwd").val();
                var newPwd = $("#newPwd").val();
                // 实际使用时的提交信息
                $.get("/personal_center/set_my_password.action", {
                    old_password: oldPwd,
                    new_password: newPwd
                }, function (res) {
                    layer.msg(res["msg"]);
                });
            }
        }
    });


});