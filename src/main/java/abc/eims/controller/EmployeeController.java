package abc.eims.controller;

import abc.eims.entity.Employee;
import abc.eims.service.Impl.AttendanceServiceImpl;
import abc.eims.service.Impl.EmployeeServiceImpl;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhe
 * @date 2020/8/9 11:34
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private AttendanceServiceImpl attendanceService;

    @RequestMapping(value = "/get_employee_info")
    @ResponseBody
    public JSONObject getAllEmployeeInfo() {

        JSONObject object = new JSONObject();
        List<Employee> empList = null;
        try {
            empList = employeeService.getAllEmployeeInfo();
            System.out.println(empList.size());
        } catch (Exception e) {
            object.put("code", -1);
            object.put("msg", "查询失败");
        }
        if (empList != null) {
            JSONArray array = new JSONArray();
            for (Employee employee : empList) {
                array.add(employee.toJSON());
            }
            object.put("code", 0);
            object.put("msg", "");
            object.put("count", empList.size());
            object.put("data", array);
            System.out.println(array);
        }
        return object;

    }

    @RequestMapping("/set_employee_info")
    @ResponseBody
    public Map<String, String> changeEmployeeInfo(HttpServletRequest request) {
        String account = request.getParameter("e_account");
        String name = request.getParameter("e_name");
        String birthday = request.getParameter("e_birthday");
<<<<<<< HEAD
=======
//        System.out.println(request.getParameter("e_sex"));
>>>>>>> 9a45c3b5ffda51fbd3a8ecef74c37790afd0748b
        Integer sex = Integer.valueOf(request.getParameter("e_sex"));
        String phone = request.getParameter("e_phone");
        String email = request.getParameter("e_email");
        Integer roleId = Integer.valueOf(request.getParameter("e_role_id"));

        Map<String, String> map = new HashMap<>();

        try {
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
        }
        return map;

    }

    @RequestMapping("del_employee_info")
    @ResponseBody
    public Map<String, String> delEmployeeInfo(@RequestParam("accountList") String accountList) {
        Map<String, String> map = new HashMap<>();
        try {
            String[] arr = accountList.split(",");
            employeeService.delEmployeeInfoByAccount(Arrays.asList(arr));
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "删除失败");
        }
        map.put("code", "0");
        map.put("msg", "操作成功");
        return map;
    }

    //没有使用
    @RequestMapping("set_employee_role")
    @ResponseBody
    public Map<String, String> changeEmployeeRole(String account, String roleId) {

        Map<String, String> map = new HashMap<>();
        try {
            employeeService.changeEmployeeRole(account, roleId);
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "操作失败");
        }
        map.put("code", "0");
        map.put("msg", "操作成功");
        return map;
    }

}



