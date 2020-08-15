package abc.eims.controller;

import abc.eims.entity.Attendance;
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
 * 考勤记录相关操作的Controller层
 *
 * @author wangzhe
 * @date 2020/8/9 19:12
 */
@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceServiceImpl attendanceService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    /**
     * 获取当前用户的考勤记录
     *
     * @return 返回当前用户的考勤记录-JSON
     */
    @RequestMapping("get_my_attendance_record")
    @ResponseBody
    public JSONObject getMyAttendanceRecord(HttpServletRequest request) {
        JSONObject object = new JSONObject();
        List<Attendance> attendList = null;
        try {
            attendList = attendanceService.getMyAttendance();
        } catch (Exception e) {
            object.put("code", "-1");
            object.put("msg", "查询失败");
            return object;
        }
        /**若记录不为空，则讲对应的账户和密码一起返回到前端*/
        if (attendList != null) {
            JSONArray array = new JSONArray();
            String key = request.getParameter("key");
            if (key == null) {
                for (Attendance attendance : attendList) {
                    Employee e = employeeService.findEmployeeById(attendance.getE_id());
                    JSONObject o = attendance.toJSON();
                    o.put("e_account", e.getE_account());
                    o.put("e_name", e.getE_name());
                    array.add(o);
                }
            } else {
                for (Attendance attendance : attendList) {
                    Employee e = employeeService.findEmployeeById(attendance.getE_id());
                    String aId = attendance.getA_id().toString();
                    String time = attendance.getA_time();
                    String type = attendance.getA_type() == 1 ? "上班打卡" : "下班打卡";
                    if (aId.contains(key) || time.contains(key) || type.contains(key)) {
                        JSONObject o = attendance.toJSON();
                        o.put("e_account", e.getE_account());
                        o.put("e_name", e.getE_name());
                        array.add(o);
                    }
                }
            }
            object.put("code", "0");
            object.put("msg", "操作成功");
            object.put("count", attendList.size());
            object.put("data", array);
        }
        return object;
    }

    /**
     * 获取所有用户的考勤记录
     *
     * @return 返回所有用户的考勤记录-JSON
     */
    @RequestMapping("get_employee_attendance_record")
    @ResponseBody
    public JSONObject getAllAttendanceRecord(HttpServletRequest request) {
        JSONObject object = new JSONObject();
        List<Attendance> attendListAll;
        try {
            attendListAll = attendanceService.getAllAttendance();
        } catch (Exception e) {
            object.put("code", "-1");
            object.put("msg", "查询失败");
            return object;
        }
        /**若记录不为空，则讲对应的账户和密码一起返回到前端*/
        if (attendListAll != null) {
            JSONArray array = new JSONArray();
            String key = request.getParameter("key");
            if (key == null) {
                for (Attendance attendance : attendListAll) {
                    Employee e = employeeService.findEmployeeById(attendance.getE_id());
                    JSONObject o = attendance.toJSON();
                    o.put("e_account", e.getE_account());
                    o.put("e_name", e.getE_name());
                    array.add(o);
                }
            } else {
                for (Attendance attendance : attendListAll) {
                    Employee e = employeeService.findEmployeeById(attendance.getE_id());
                    String aId = attendance.getA_id().toString();
                    String eAccount = e.getE_account();
                    String eName = e.getE_name();
                    String time = attendance.getA_time();
                    String type = attendance.getA_type() == 1 ? "上班打卡" : "下班打卡";
                    if (aId.contains(key) || eAccount.contains(key) || eName.contains(key) || time.contains(key) || type.contains(key)) {
                        JSONObject o = attendance.toJSON();
                        o.put("e_account", e.getE_account());
                        o.put("e_name", e.getE_name());
                        array.add(o);
                    }
                }
            }
            object.put("code", "0");
            object.put("msg", "操作成功");
            object.put("count", attendListAll.size());
            object.put("data", array);
        }
        return object;
    }

    /**
     * 管理员修改考勤记录
     *
     * @param request HttpRequest
     * @return 返回是否操作成功
     */
    @RequestMapping("set_employee_attendance_record")
    @ResponseBody
    public Map<String, String> changeAttendanceRecord(HttpServletRequest request) {

        Integer aId = Integer.valueOf(request.getParameter("a_id"));

        Integer aType = Integer.valueOf(request.getParameter("a_type"));

        String aTime = request.getParameter("a_time");

        Map<String, String> map = new HashMap<>();

        try {
            attendanceService.changeAttendanceRecord(aId, aType, aTime);
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "操作失败");
            return map;
        }
        map.put("code", "0");
        map.put("msg", "操作成功");
        return map;
    }

    /**
     * 管理员删除考勤记录
     *
     * @param aIdList 考勤记录的Id
     * @return 返回是否操作成功
     */
    @RequestMapping("/del_attendance_info")
    @ResponseBody
    public Map<String, String> delAttendanceRecord(
            @RequestParam("idList") String aIdList) {
        Map<String, String> map = new HashMap<>();
        try {
            String[] arr = aIdList.split(",");
            attendanceService.delAttendanceRecord(Arrays.asList(arr));
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
