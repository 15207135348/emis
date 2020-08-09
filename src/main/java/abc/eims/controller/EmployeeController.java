package abc.eims.controller;

import abc.eims.entity.Employee;
import abc.eims.service.Impl.EmployeeServiceImpl;
import abc.eims.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhe
 * @date 2020/8/9 11:34
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;


    @RequestMapping("get_employ_info")
    @ResponseBody
    public Map<String, String> getAllEmployeeInfo() {

        Map<String, String> map = new HashMap<>();
        List<Employee> empList = null;
        try {
            empList = employeeService.getAllEmployeeInfo();
        }catch (Exception e){
            map.put("code", "-1");
            map.put("msg", "查询失败");
        }
//        JSONPObject json = new JSONPObject(empList);
        String jsonEmployee = JsonUtil.obj2String(empList);
        map.put("code", "0");
        map.put("msg", "操作成功");
        map.put("data", jsonEmployee);
        return map;

    }

    @RequestMapping("set_employee_info")
    @ResponseBody
    public Map<String, String> changeEmployeeInfo(String account,
                                                  String name,
                                                  Date birthday,
                                                  Integer sex,
                                                  String phone,
                                                  String email) {
        Map<String, String> map = new HashMap<>();

        try {
            employeeService.changeEmployeeInfo(account, name, birthday, sex, phone, email);
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "信息修改失败");
        }
        map.put("code", "0");
        map.put("msg", "修改成功");
        return map;

    }


    @RequestMapping("del_employee_info")
    @ResponseBody
    public Map<String, String> delEmployeeInfo(String account) {
        Map<String, String> map = new HashMap<>();
        try {
            employeeService.delEmployeeInfoByAccount(account);
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "删除失败");
        }
        map.put("code", "0");
        map.put("msg", "操作成功");
        return map;
    }

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


