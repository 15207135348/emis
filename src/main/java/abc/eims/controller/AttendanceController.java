package abc.eims.controller;

import abc.eims.entity.Attendance;
import abc.eims.entity.Employee;
import abc.eims.service.Impl.AttendanceServiceImpl;
import abc.eims.service.Impl.EmployeeServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
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

    @RequestMapping("get_my_attendance_record")
    @ResponseBody
    public JSONObject getMyAttendanceRecord() {

        JSONObject object = new JSONObject();
        List<Attendance> attendList = null;
        try {
            attendList = attendanceService.getMyAttendance();
        } catch (Exception e) {
            object.put("code", "-1");
            object.put("msg", "查询失败");
        }
        if (attendList != null) {
            JSONArray array = new JSONArray();
            for (Attendance attendance : attendList) {
                Employee e = employeeService.findEmployeeById(attendance.getE_id());
                JSONObject o = new JSONObject(array.add(JSON.toJSON(attendance)));
                o.put("account", e.getE_account());
                o.put("name", e.getE_name());
            }
            object.put("code", "0");
            object.put("msg", "操作成功");
            object.put("count", attendList.size());
            object.put("data", array);
        }
        return object;
    }

    @RequestMapping("get_employee_attendance_record")
    @ResponseBody
    public JSONObject getAllAttendanceRecord() {

        JSONObject object = new JSONObject();
        List<Attendance> attendListAll = null;
        try {
            attendListAll = attendanceService.getAllAttendance();
        } catch (Exception e) {
            object.put("code", "-1");
            object.put("msg", "查询失败");
        }
        if (attendListAll != null) {
            JSONArray array = new JSONArray();
            for (Attendance attendance : attendListAll) {
                Employee e = employeeService.findEmployeeById(attendance.getE_id());
                JSONObject o = new JSONObject(array.add(JSON.toJSON(attendance)));
                o.put("account", e.getE_account());
                o.put("name", e.getE_name());
            }
            object.put("code", "0");
            object.put("msg", "操作成功");
            object.put("count", attendListAll.size());
            object.put("data", array);
        }
        return object;
    }

    @RequestMapping("set_employee_attendance_record")
    @ResponseBody
    public Map<String, String> changeAttendanceRecord(Integer aId, Integer aType, String aTime) {
        Map<String, String> map = new HashMap<>();

        try {
            attendanceService.changeAttendanceRecord(aId, aType, aTime);
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "操作失败");
        }
        map.put("code", "0");
        map.put("msg", "操作成功");
        return map;
    }

    @RequestMapping("del_attendance_info")
    @ResponseBody
    public Map<String, String> delAttendanceRecord(Integer aId) {
        Map<String, String> map = new HashMap<>();

        try {
            attendanceService.delAttendanceRecord(aId);
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "操作失败");
        }
        map.put("code", "0");
        map.put("msg", "操作成功");
        return map;
    }

}
