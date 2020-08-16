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

    /**
     * 通过eId查找考勤记录
     *
     * @param eId 员工id
     * @return 考勤记录
     */
    @Override
    public Attendance findByEid(Integer eId) {
        return attendanceMapper.findByEid(eId);
    }

    /**
     * 获取本人考勤记录
     *
     * @return 相应的考勤记录
     */
    @Override
    public List<Attendance> getMyAttendance() {
        //将当前用户Id从Cookie中取出
        int eId = Integer.parseInt(Objects.requireNonNull(
                CookieUtil.getCookieValueFromRequest()));
        return attendanceMapper.getMyAttendance(eId);
    }

    /**
     * 获取所有考勤记录
     *
     * @return 所有考勤记录
     */
    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceMapper.findAll();
    }

    /**
     * 修改考勤信息
     *
     * @param aId   考勤记录Id
     * @param aType 考勤类型
     * @param aTime 打卡时间
     */
    @Override
    public void changeAttendanceRecord(Integer aId, Integer aType, String aTime) {
        attendanceMapper.changeAttendanceRecord(aId, aType, aTime);
    }

    /**
     * 删除考勤记录
     *
     * @param aIdList 考勤记录Id
     */
    @Override
    public void delAttendanceRecord(List<String> aIdList) {
        attendanceMapper.deleteByaId(aIdList);
    }

}
