package abc.eims.controller;

import abc.eims.entity.Employee;
import abc.eims.exception.CustomException;
import abc.eims.service.Impl.EmployeeServiceImpl;
import abc.eims.utils.CaptchaUtil;
import abc.eims.utils.CookieUtil;
import abc.eims.vo.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhe
 * @date 2020/8/9 11:34
 */
@Controller
@RequestMapping("auth")
public class LoginController {


    @Autowired
    private EmployeeServiceImpl employeeService;


    @RequestMapping(value = "/toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 点击切换验证码
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/changeCode")
    @ResponseBody
    public void getIdentifyingCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 验证码存储在session的identifyingCode属性中
        CaptchaUtil.outputCaptcha(request, response);
    }


    @RequestMapping("register")
    @ResponseBody
    public Response employeeRegister(@RequestBody Employee employee) {
        Employee employee1 = employeeService.findByAccount(employee.getE_account());
        if (employee1 != null){
            return new Response(Response.Code.UserHasExistError);
        }
        employeeService.insert(employee);
        CookieUtil.addCookie(String.valueOf(employee.getE_id()));
        return new Response(Response.Code.Success);
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @param identifyingcode
     * @return
     */
    @RequestMapping("login_by_password")
    public String employeeLogin(
            @RequestParam("e_account") String account,
            @RequestParam("e_password") String password,
            @RequestParam("code") String identifyingcode) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession httpSession = request.getSession();
        account = StringUtils.trim(account);
        String code = (String) httpSession.getAttribute("identifyingCode");
        if (StringUtils.equalsIgnoreCase(identifyingcode, code)) {
            Employee employee;
            try {
                employee = employeeService.findEmployeeByIdAndPassword(account, password);
            } catch (CustomException e) {
                return "login";
            }
            // 保存到session
            httpSession.setAttribute("employeeId", employee.getE_id());
            CookieUtil.addCookie(String.valueOf(employee.getE_id()));
            return "index";
        } else {
            return "login";
        }
    }

    /**
     * 退出登录
     *
     * @param httpSession
     * @return
     */
    @RequestMapping("login_out")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("employeeId");
        CookieUtil.removeCookie();
        return "login";

    }


}