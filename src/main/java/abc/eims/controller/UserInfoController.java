package abc.eims.controller;

import abc.eims.entity.Employee;
import abc.eims.service.Impl.EmployeeServiceImpl;
import abc.eims.service.Impl.UserInfoServiceImpl;
import abc.eims.utils.CookieUtil;
import abc.eims.utils.DateTimeUtil;
import abc.eims.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 与个人信息操作有关的Controller层
 *
 * @author wangzhe
 * @date 2020/8/9 15:02
 */
@Controller
@RequestMapping("personal_center")
public class UserInfoController {

    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Autowired
    private EmployeeServiceImpl employeeService;
    /**
     * 修改密码
     *
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return 返回是否修改成功
     */
    @RequestMapping("set_my_password")
    @ResponseBody
    public Map<String, String> changePassword(
            @RequestParam("old_password") String oldPwd,
            @RequestParam("new_password") String newPwd) {
        Map<String, String> map = new HashMap<>();
        try {
            userInfoService.changePassword(oldPwd, newPwd);
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "密码修改失败");
            return map;
        }
        map.put("code", "0");
        map.put("msg", "密码修改成功");
        return map;
    }

    /**
     * 修改个人信息
     * @return 返回是否修改成功
     */
    @RequestMapping("set_my_info")
    @ResponseBody
    public Response changeInfo(HttpServletRequest request) {

        String name = request.getParameter("e_name");
        String birthday = request.getParameter("e_birthday");
        Integer sex = Integer.valueOf(request.getParameter("e_sex"));
        String phone = request.getParameter("e_phone");
        String email = request.getParameter("e_email");
        Employee employee;
        try {
            Date date = DateTimeUtil.strToDate(birthday, "yyyy年MM月dd日");
            employee = userInfoService.changeInfo(name, date, sex, phone, email);
        } catch (Exception e) {
            return new Response(Response.Code.SystemError);
        }
        return new Response(Response.Code.Success, employee.toJSON());
    }


    @RequestMapping("get_my_info")
    @ResponseBody
    public Response getMyInfo(){
        Map<String, String> map = new HashMap<>();
        try {
            int eId = Integer.parseInt(Objects.requireNonNull(
                    CookieUtil.getCookieValueFromRequest()));
            Employee employee = employeeService.findEmployeeById(eId);
            return new Response(Response.Code.Success, employee.toJSON());
        } catch (Exception e) {
            return new Response(Response.Code.SystemError);
        }
    }
}
