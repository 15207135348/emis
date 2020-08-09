package abc.eims.controller;

import abc.eims.entity.Attendance;
import abc.eims.service.Impl.AttendanceService;
import abc.eims.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhe
 * @date 2020/8/9 19:12
 */
@Controller
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping("get_my_attendance_record")
    @ResponseBody
    public Map<String, String> getMyAttendanceRecord(HttpSession httpSession) {

        Map<String, String> map = new HashMap<>();

        List<Attendance> attendList = null;
        try {
            attendList = attendanceService.getMyAttendance(httpSession);
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "查询失败");
        }
        String jsonAttend = JsonUtil.obj2String(attendList);
        map.put("code", "0");
        map.put("msg", "操作成功");
        map.put("data", jsonAttend);
        return map;
    }

    @RequestMapping("get_employee_attendance_record")
    @ResponseBody
    public Map<String, String> getAllAttendanceRecord() {
        Map<String, String> map = new HashMap<>();

        List<Attendance> attendListAll = null;
        try {
            attendListAll = attendanceService.getAllAttendance();
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "查询失败");
        }
        String jsonAttendAll = JsonUtil.obj2String(attendListAll);
        map.put("code", "0");
        map.put("msg", "操作成功");
        map.put("data", jsonAttendAll);
        return map;
    }

    @RequestMapping("set_employee_attendance_record")
    @ResponseBody
    public Map<String, String> changeAttendanceRecord(Integer aId, Integer aType, String aTime) {
        Map<String, String> map = new HashMap<>();

        try {
            attendanceService.changeAttendanceRecord(aId, aType, aTime);
        }catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "操作失败");
        }
        map.put("code", "0");
        map.put("msg", "操作成功");
        return map;
    }

}
