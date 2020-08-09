package abc.eims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author wangzhe
 * @date 2020/8/9 19:12
 */
@Controller
public class AttendanceController {

    @RequestMapping("get_my_attendance_record")
    @ResponseBody
    public Map<String,String> getMyAttendanceRecord(HttpSession httpSession){
        return null;

    }


}
