package abc.eims.controller;

import abc.eims.entity.Employee;
import abc.eims.exception.CustomException;
import abc.eims.service.Impl.EmployeeServiceImpl;
import abc.eims.utils.CaptchaUtil;
import abc.eims.utils.CookieUtil;
import abc.eims.utils.MD5Utils;
import abc.eims.vo.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
 * @author wangzhe
 * @date 2020/8/9 11:34
 */
@Controller
@RequestMapping("auth")
public class LoginController {


    @Autowired
    private EmployeeServiceImpl employeeService;


    /**
     * 跳转到登陆页面
     */
    @RequestMapping("toLogin")
    public String toLogin() {
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
    public Response employeeRegister(@RequestParam("account") String account,
                                     @RequestParam("password") String password,
                                     @RequestParam("name") String name,
                                     @RequestParam("phone") String phone,
                                     @RequestParam("email") String email,
                                     @RequestParam("sex") int sex,
                                     @RequestParam("birthday") long birthday,
                                     @RequestParam("role_id") int roleId) {

        try {
            employeeService.findEmployeeByIdAndPassword(account, password);
        } catch (CustomException e) {
            return new Response(Response.Code.UserHasExistError);
        }
        Employee employee = new Employee(account, MD5Utils.encodeByMD5(password),
                name, new Date(birthday), sex, phone, email, roleId);
        employeeService.insert(employee);
        //添加cookie
        CookieUtil.addCookie(account);
        //返回消息
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
    @PostMapping("login_by_password")
    public String employeeLogin(@RequestParam("e_account") String account,
                                @RequestParam("e_password") String password,
                                @RequestParam("code") String identifyingcode) {

        account = StringUtils.trim(account);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession httpSession = request.getSession();
        String code = (String) httpSession.getAttribute("identifyingCode");

        if (StringUtils.equalsIgnoreCase(identifyingcode, code)) {
            try {
                int id = employeeService.findIdByAccountAndPassword(account, password);
                // 保存到session
                httpSession.setAttribute("employeeId", id);
            } catch (CustomException e) {
                return "login";
            }
            //添加cookie
            CookieUtil.addCookie(account);
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
        return "redirect:login";
    }

}