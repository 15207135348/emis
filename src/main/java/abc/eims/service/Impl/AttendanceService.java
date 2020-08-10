package abc.eims.service.Impl;

import abc.eims.dao.AttendanceMapper;
import abc.eims.entity.Attendance;
import abc.eims.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/9 10:40
 */
@Service
public class AttendanceService implements IAttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

//    @Autowired
//    private EmployeeMapper employeeMapper;

    @Override
    public List<Attendance> getMyAttendance(HttpSession httpSession) {
        String eId = (String) httpSession.getAttribute("employeeId");
        return attendanceMapper.getMyAttendance(eId);
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceMapper.findAll();
    }


    @Override
    public int changeAttendanceRecord(Integer aId, Integer aType, String aTime) {
        return attendanceMapper.changeAttendanceRecord(aId, aType, aTime);
    }

}
