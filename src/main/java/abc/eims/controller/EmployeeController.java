package abc.eims.controller;

import abc.eims.entity.Employee;
import abc.eims.service.Impl.EmployeeServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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


    @RequestMapping(value = "/get_employee_info")
    @ResponseBody
    public JSONObject getAllEmployeeInfo() {

        JSONObject object = new JSONObject();
        List<Employee> empList = null;
        try {
            empList = employeeService.getAllEmployeeInfo();
        } catch (Exception e) {
            object.put("code", -1);
            object.put("msg", "查询失败");
        }
        if (empList != null) {
            JSONArray array = new JSONArray();
            for (Employee employee : empList) {
                array.add(JSON.toJSON(employee));
            }
            object.put("code", 0);
            object.put("msg", "");
            object.put("count", empList.size());
            object.put("data", array);
        }
        return object;
    }
//
//    @RequestMapping("set_employee_info")
//    @ResponseBody
//    public Map<String, String> changeEmployeeInfo(@RequestBody Employee employee) {
//        Map<String, String> map = new HashMap<>();
//        try {
//            Employee employee1 = employeeService.findByAccount(employee.getE_account());
//            if (employee1 == null) {
//                employeeService.insert(employee);
//            } else {
//                employeeService.changeEmployeeInfo(account, name, birthday, sex, phone, email);
//            }
//        } catch (Exception e) {
//            map.put("code", "-1");
//            map.put("msg", "信息修改失败");
//        }
//        map.put("code", "0");
//        map.put("msg", "修改成功");
//        return map;
//    }


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

}



