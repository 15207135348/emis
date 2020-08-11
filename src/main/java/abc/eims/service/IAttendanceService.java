package abc.eims.service;

import abc.eims.entity.Attendance;

import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/9 10:02
 */
public interface IAttendanceService {

    List<Attendance> getMyAttendance();

    List<Attendance> getAllAttendance();

    void changeAttendanceRecord(Integer aId, Integer aType, String aTime);

    void delAttendanceRecord(Integer aId);

}
