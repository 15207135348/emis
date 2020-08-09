package abc.eims.service.Impl;

import abc.eims.dao.AttendanceMapper;
import abc.eims.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangzhe
 * @date 2020/8/9 10:40
 */
@Service
public class AttendanceService implements IAttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public void getMyAttendance(String eId) {

    }

}
