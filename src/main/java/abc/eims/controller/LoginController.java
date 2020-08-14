package abc.eims.controller;

import abc.eims.entity.Employee;
import abc.eims.exception.CustomException;
import abc.eims.service.Impl.EmployeeServiceImpl;
import abc.eims.utils.CaptchaUtil;
import abc.eims.utils.CookieUtil;
import abc.eims.utils.DateTimeUtil;
import abc.eims.utils.MD5Utils;
import abc.eims.vo.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 和员工信息相关的Controller
 *
 * @author wangzhe
 * @date 2020/8/9 11:34
 */
@Controller
@RequestMapping("auth")
public class LoginController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    /**
     * 点击生成验证码
     *
     * @param request  HttpRequest
     * @param response HttpResponse
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/changeCode")
    @ResponseBody
    public void getIdentifyingCode(HttpServletRequest request,
                                   HttpServletResponse response)
            throws ServletException, IOException {
        //验证码存储在session的identifyingCode属性中
        CaptchaUtil.outputCaptcha(request, response);
    }

    /**
     * 账号注册
     *
     * @return 是否注册成功
     */
    @RequestMapping("register")
    @ResponseBody
    public Response employeeRegister(HttpServletRequest request) {
        String account = request.getParameter("e_account");
        String password = request.getParameter("e_password");
        String name = request.getParameter("e_name");
        Integer sex = Integer.valueOf(request.getParameter("e_sex"));
        String phone = request.getParameter("e_phone");
        String email = request.getParameter("e_email");
        String birthday = request.getParameter("e_birthday");
        //查找要注册的用户是否存在
        Employee employee = employeeService.findByAccount(account);
        if (employee != null) {
            return new Response(Response.Code.UserHasExistError);
        }
        employee = new Employee();
        employee.setE_account(account);
        //将密码通过MD5加密后存入数据库中
        employee.setE_password(MD5Utils.encodeByMD5(password));
        employee.setE_sex(sex);
        employee.setE_name(name);
        employee.setE_phone(phone);
        employee.setE_email(email);
        employee.setE_birthday(DateTimeUtil.dateToStamp(birthday));
        employee.setE_role_id(3);
        employeeService.insert(employee);
        //将员工id放入cookie中，用于会话信息保存。
        CookieUtil.addCookie(String.valueOf(employee.getE_id()));
        return new Response(Response.Code.Success);
    }

    /**
     * 登录功能
     *
     * @param account         员工账户
     * @param password        登录密码
     * @param identifyingcode 验证码
     * @return 是否登录成功
     */
    @RequestMapping("login_by_password")
    @ResponseBody
    public Response employeeLogin(
            @RequestParam("e_account") String account,
            @RequestParam("e_password") String password,
            @RequestParam("code") String identifyingcode) {

        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession httpSession = request.getSession();

        //对输入的账号处理
        account = StringUtils.trim(account);
        //取出存放在session中的验证码
        String code = (String) httpSession.getAttribute("identifyingCode");
        if (StringUtils.equalsIgnoreCase(identifyingcode, code)) {
            Employee employee;
            try {
                employee = employeeService.findEmployeeByIdAndPassword(account, password);
            } catch (CustomException e) {
                return new Response(Response.Code.PasswordError);
            }
            CookieUtil.addCookie(String.valueOf(employee.getE_id()));
            return new Response(Response.Code.Success, employee.toJSON());
        } else {
            return new Response(Response.Code.CodeError);
        }
    }

    /**
     * 退出登录
     *
     * @return login页面
     */
    @RequestMapping("login_out")
    public String logout() {
        //清除Cookie中的信息
        CookieUtil.removeCookie();
        return "login";
    }

}