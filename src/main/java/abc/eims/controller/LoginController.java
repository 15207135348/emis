package abc.eims.controller;

import abc.eims.entity.Employee;
import abc.eims.exception.CustomException;
import abc.eims.service.Impl.EmployeeServiceImpl;
import abc.eims.utils.CaptchaUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    /**
     * 点击切换验证码
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/changeCode.do")
    @ResponseBody
    public void getIdentifyingCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 验证码存储在session的identifyingCode属性中
        CaptchaUtil.outputCaptcha(request, response);
    }


    @RequestMapping("register")
    @ResponseBody
    public Map<String, String> employeeRegister(String account,
                                                String password,
                                                String name,
                                                String phone,
                                                String email,
                                                String sex,
                                                Date birthday) {
        return null;
    }

    /**
     * 登录
     *
     * @param httpSession
     * @param account
     * @param password
     * @param identifyingcode
     * @return
     */
    @RequestMapping("login_by_password")
    @ResponseBody
    public Map<String, String> employeeLogin(HttpSession httpSession,
                                             String account,
                                             String password,
                                             String identifyingcode) {

        account = StringUtils.trim(account);
        String code = (String) httpSession.getAttribute("identifyingCode");

        HashMap<String, String> map = new HashMap<String, String>();
        if (StringUtils.equalsIgnoreCase(identifyingcode, code)) {
            Employee employee;
            try {
                employee = employeeService.findEmployeeByIdAndPassword(account, password);
            } catch (CustomException e) {
                map.put("code", "-1");
                map.put("msg", e.getMessage());
                return map;
            }
            // 保存到session
            httpSession.setAttribute("employeeId", employee.getE_id());
            map.put("code", "0");
            map.put("msg", "成功");
            return map;
        } else {
            map.put("code", "-2");
            map.put("msg", "验证码错误");
            return map;
        }
    }

    /**
     * 退出登录
     *
     * @param httpSession
     * @return
     */
    @RequestMapping("login_out")
    public Map<String, String> logout(HttpSession httpSession) {
        Map<String, String> map = new HashMap<>();

        httpSession.removeAttribute("employeeId");
        // return "redirect:login_by_password";
        map.put("code", "0");
        map.put("msg", "操作成功");
        return map;

    }


}