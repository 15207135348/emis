package abc.eims.vo;

import com.alibaba.fastjson.JSONObject;

/**
 * 返回前端信息
 *
 * @author wangzhe
 * @date 2020/8/10 04:59
 */
public class Response {

    private int code;
    private String msg;
    private Object data;

    public Response(Code code) {
        this.code = code.code;
        this.msg = code.msg;
    }

    public Response(Code code, Object data) {
        this.code = code.code;
        this.msg = code.msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("data", data);
        return jsonObject.toString();
    }


    public enum Code {

        LoginSuccess(0, "登录成功"),
        Success(0, "操作成功"),
        ParameterError(-1, "参数错误"),
        UserNotExistError(-2, "用户不存在"),
        UserHasExistError(-3, "用户已存在"),
        PasswordError(-4, "账号或密码错误！"),
        UnLoginError(-5, "尚未登录"),
        CodeError(-6, "验证码错误！"),
        PhoneOrEmailHasUsedError(-7, "手机或者邮箱已被使用！"),
        SystemError(-1000, "服务器内部错误");
        private int code;
        private String msg;

        Code(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
