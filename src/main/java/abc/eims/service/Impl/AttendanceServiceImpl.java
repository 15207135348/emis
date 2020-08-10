package abc.eims.service.Impl;

import abc.eims.dao.AttendanceMapper;
import abc.eims.entity.Attendance;
import abc.eims.service.IAttendanceService;
import abc.eims.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author wangzhe
 * @date 2020/8/9 10:40
 */
@Service
public class AttendanceServiceImpl implements IAttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public List<Attendance> getMyAttendance() {
        //String eId = (String) httpSession.getAttribute("employeeId");
        int eId = Integer.parseInt(Objects.requireNonNull(CookieUtil.getCookieValueFromRequest()));
        return attendanceMapper.getMyAttendance(eId);
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceMapper.findAll();
    }


    @Override
    public void changeAttendanceRecord(Integer aId, Integer aType, String aTime) {
        attendanceMapper.changeAttendanceRecord(aId, aType, aTime);
    }

}
