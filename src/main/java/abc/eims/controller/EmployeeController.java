package abc.eims.controller;

import abc.eims.entity.Employee;
import abc.eims.service.Impl.EmployeeServiceImpl;
import abc.eims.utils.CookieUtil;
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
    public Map<String, String> changeEmployeeInfo(
                @RequestParam("e_account") String account,
                @RequestParam("e_name") String name,
                @RequestParam("e_birthday") String birthday,
                @RequestParam("e_sex") Integer sex,
                @RequestParam("e_phone") String phone,
                @RequestParam("e_email") String email,
                @RequestParam("e_role_id") Integer roleId) {

        Map<String, String> map = new HashMap<>();
        /*从Cookie中取出员工Id*/
        int eId = Integer.parseInt(Objects.requireNonNull(
                CookieUtil.getCookieValueFromRequest()));
        /*判断员工是否有修改权限*/
        int targetRole = employeeService.findByAccount(account).getE_role_id();
        int myRole = employeeService.findEmployeeById(eId).getE_role_id();
        if (myRole >= targetRole) {
            map.put("code", "-1");
            map.put("msg", "权限不足，无法修改");
            return map;
        }
        try {
            /*添加员工信息记录，若记录已存在则修改。*/
            int res = employeeService.updateOrInsert(account, name,
                    birthday, sex, phone, email, roleId);
            if (res == 1) {
                map.put("code", "0");
                map.put("msg", "添加成功");
            } else {
                map.put("code", "0");
                map.put("msg", "修改成功");
            }
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "信息修改失败");
            return map;
        }
        return map;
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



