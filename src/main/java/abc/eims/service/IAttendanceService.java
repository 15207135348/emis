package abc.eims.service;

import abc.eims.entity.Attendance;

import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/9 10:02
 */
public interface IAttendanceService {

    /**
     * 通过员工Id查找对应的考勤记录
     *
     * @param eId 员工Id
     * @return 对应的考勤记录
     */
    Attendance findByEid(Integer eId);

    /**
     * 查找当前用户所有的考勤记录
     *
     * @return 对应的考勤记录
     */
    List<Attendance> getMyAttendance();

    /**
     * 获取所有员工的考勤记录
     *
     * @return 所有的考勤记录
     */
    List<Attendance> getAllAttendance();

    /**
     * 修改考勤记录信息
     *
     * @param aId   考勤记录Id
     * @param aType 打卡类型
     * @param aTime 打卡时间
     */
    void changeAttendanceRecord(Integer aId, Integer aType, String aTime);

    /**
     * 删除考勤记录
     *
     * @param aIdList 考勤记录Id
     */
    void delAttendanceRecord(List<String> aIdList);

}
