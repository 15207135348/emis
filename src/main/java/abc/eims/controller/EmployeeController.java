package abc.eims.controller;

import abc.eims.entity.Employee;
import abc.eims.service.Impl.EmployeeServiceImpl;
import abc.eims.utils.CookieUtil;
import abc.eims.utils.DateTimeUtil;
import abc.eims.utils.MD5Utils;
import abc.eims.vo.Response;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 员工信息相关的Controller层
 *
 * @author wangzhe
 * @date 2020/8/9 11:34
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    /**
     * 获取所有员工的考勤记录
     *
     * @return 所有员工的考勤记录
     */
    @RequestMapping(value = "/get_employee_info")
    @ResponseBody
    public JSONObject getEmployeeInfo(HttpServletRequest request) {

        JSONObject object = new JSONObject();
        List<Employee> empList;
        try {
            String eAccount = request.getParameter("e_account");
            /*搜索框内容为空则返回所有用户信息*/
            if (eAccount == null) {
                empList = employeeService.getAllEmployeeInfo();
            } else {
                /*若搜索内容不为空，根据搜索框内容进行模糊查询*/
                List<Employee> employeeList =
                        employeeService.fuzzyFindByAccount(eAccount);
                empList = new ArrayList<>(employeeList);
            }

        } catch (Exception e) {
            object.put("code", -1);
            object.put("msg", "查询失败");
            return object;
        }
        /*若记录不为空，则讲员工信息转换为JSON返回到前端*/
        if (empList != null) {
            JSONArray array = new JSONArray();
            for (Employee employee : empList) {
                array.add(employee.toJSON());
            }
            object.put("code", 0);
            object.put("msg", "");
            object.put("count", empList.size());
            object.put("data", array);
        }
        return object;

    }

    /**
     * 管理员修改员工信息
     *
     * @param account  账号
     * @param name     姓名
     * @param birthday 生日
     * @param sex      性别
     * @param phone    电话
     * @param email    e-mail
     * @param roleId   权限
     * @return 是否修改成功
     */
    @RequestMapping("/set_employee_info")
    @ResponseBody
    public Response changeEmployeeInfo(
                @RequestParam("e_account") String account,
                @RequestParam("e_name") String name,
                @RequestParam("e_birthday") String birthday,
                @RequestParam("e_sex") Integer sex,
                @RequestParam("e_phone") String phone,
                @RequestParam("e_email") String email,
                @RequestParam("e_role_id") Integer roleId) {

        /*从Cookie中取出员工Id*/
        int eId = Integer.parseInt(Objects.requireNonNull(
                CookieUtil.getCookieValueFromRequest()));
        Employee employee = employeeService.findByAccount(account);
        if (employee == null){
            return new Response(Response.Code.UserNotExistError);
        }
        /*判断员工是否有修改权限*/
        int targetRole = employeeService.findByAccount(account).getE_role_id();
        int myRole = employeeService.findEmployeeById(eId).getE_role_id();
        if (myRole >= targetRole) {
            return new Response(Response.Code.InsufficientPermissions);
        }
        try {
            /*添加员工信息记录，若记录已存在则修改。*/
            employeeService.update(account, name,
                    birthday, sex, phone, email, roleId);
            return new Response(Response.Code.EditSuccess);
        } catch (Exception e) {
            return new Response(Response.Code.SystemError);
        }
    }


    /**
     * 账号注册
     *
     * @return 是否注册成功
     */
    @RequestMapping("add_employee_info")
    @ResponseBody
    public Response employeeRegister(
            @RequestParam("e_account") String account,
            @RequestParam("e_name") String name,
            @RequestParam("e_sex") Integer sex,
            @RequestParam("e_phone") String phone,
            @RequestParam("e_email") String email,
            @RequestParam("e_birthday") String birthday,
            @RequestParam("e_role_id") Integer roleId) {

        /**查找要注册的用户是否存在，不存在则新增。*/
        Employee employee = employeeService.findByAccount(account);
        if (employee != null) {
            return new Response(Response.Code.UserHasExistError);
        }
        employee = new Employee();
        employee.setE_account(account);
        //将密码通过MD5加密后存入数据库中
        employee.setE_password(MD5Utils.encodeByMD5("123456"));
        employee.setE_sex(sex);
        employee.setE_name(name);
        employee.setE_phone(phone);
        employee.setE_email(email);
        employee.setE_birthday(DateTimeUtil.dateToStamp(birthday));
        employee.setE_role_id(roleId);
        employeeService.insert(employee);

        Employee employee1 = employeeService.findByAccount(account);
        if (employee1 == null) {
            return new Response(Response.Code.PhoneOrEmailHasUsedError);
        } else {
            return new Response(Response.Code.AddSuccess, employee1.toJSON());
        }
    }

    /**
     * 管理员删除员工信息
     *
     * @param accountList 员工账号
     * @return 是否删除成功
     */
    @RequestMapping("del_employee_info")
    @ResponseBody
    public Map<String, String> delEmployeeInfo(
            @RequestParam("accountList") String accountList) {
        Map<String, String> map = new HashMap<>();
        try {
            String[] arr = accountList.split(",");
            employeeService.delEmployeeInfoByAccount(Arrays.asList(arr));
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "删除失败");
            return map;
        }
        map.put("code", "0");
        map.put("msg", "操作成功");
        return map;
    }

    /**
     * 系统管理员修改员工权限
     *
     * @param account 员工账号
     * @param roleId  旧的员工权限
     * @return 是否修改成功
     */
    @RequestMapping("set_employee_role")
    @ResponseBody
    public Map<String, String> changeEmployeeRole(
                @RequestParam("e_account") String account,
                @RequestParam("e_role_id") String roleId) {

        Map<String, String> map = new HashMap<>();
        try {
            employeeService.changeEmployeeRole(account, roleId);
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "操作失败");
            return map;
        }
        map.put("code", "0");
        map.put("msg", "操作成功");
        return map;
    }

}



