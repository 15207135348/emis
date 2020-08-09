package abc.eims.service;

import abc.eims.entity.Attendance;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/9 10:02
 */
public interface IAttendanceService {

    List<Attendance> getMyAttendance(HttpSession httpSession);

    List<Attendance> getAllAttendance();

    int changeAttendanceRecord(Integer aId, Integer aType, String aTime);

}
