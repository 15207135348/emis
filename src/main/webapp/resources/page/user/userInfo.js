var form, $, areaData;
layui.config({
    base: "../../js/"
}).extend({
    "address": "address"
})
layui.use(['form', 'layer', 'upload', 'laydate', "address"], function () {
    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        laydate = layui.laydate,
        address = layui.address;

    //上传头像
    upload.render({
        elem: '.userFaceBtn',
        url: '../../json/userface.json',
        method: "get",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
        done: function (res, index, upload) {
            var num = parseInt(4 * Math.random());  //生成0-4的随机数，随机显示一个头像信息
            $('#userFace').attr('src', res.data[num].src);
            window.sessionStorage.setItem('userFace', res.data[num].src);
        }
    });

    //添加验证规则
    form.verify({
        userBirthday: function (value) {
            if (!/^(\d{4})[\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|1[0-2])([\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/.test(value)) {
                return "出生日期格式不正确！";
            }
        }
    });

    //选择出生日期
    laydate.render({
        elem: '.userBirthday',
        format: 'yyyy年MM月dd日',
        trigger: 'click',
        max: 0,
        mark: {"0-12-15": "生日"},
        done: function (value, date) {
            if (date.month === 8 && date.date === 21) { //点击每年12月15日，弹出提示语
                layer.msg('今天也是我的生日，快来送上祝福吧！');
            }
        }
    });

    var edit = JSON.parse(window.localStorage.getItem("userInfo"));
    if (edit) {
        var sex_code = edit.e_sex === '男' ? "1" : "0";
        $(".userCode").val(edit.e_account);  //登录名
        $(".userName").val(edit.e_name);  //登录名
        $(".userBirthday").val(edit.e_birthday);  //登录名
        $(".userSex input[value=" + sex_code + "]").prop("checked", "checked");  //性别
        $(".userPhone").val(edit.e_phone);  //电话
        $(".userEmail").val(edit.e_email);  //邮箱
        $(".userGrade").val(edit.e_role_id);  //邮箱
    }

    //提交个人资料
    form.on("submit(changeUser)", function (data) {
        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //将填写的用户信息存到session以便下次调取
        $.get("/personal_center/set_my_info.action", {
            'e_name': $(".userName").val(),
            'e_sex' : $(".userSex input:checked").val(),
            'e_phone': $(".userPhone").val(),
            'e_email': $(".userEmail").val(),
            'e_birthday': $(".userBirthday").val()
        }, function (res) {
            console.log(res);
            window.localStorage.setItem("userInfo", JSON.stringify(res['data']));
            layer.close(index);
            layer.msg("提交成功！");
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
});