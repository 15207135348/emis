package abc.eims.dao;

import abc.eims.entity.Attendance;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/9 9:14
 */
@Repository
public interface AttendanceMapper {

    /**
     * 获取所有考勤记录
     *
     * @return 考勤记录
     */
    List<Attendance> findAll();

    /**
     * 获取本人的考勤记录
     *
     * @param eId 员工Id
     * @return 对应的考勤记录
     */
    List<Attendance> getMyAttendance(@Param("eId") Integer eId);

    /**
     * 通过考勤记录Id获取考勤记录
     *
     * @param aId 考勤记录Id
     * @return 对应的考勤记录
     */
    Attendance findById(@Param("aId") Integer aId);

    /**
     * 通过员工Id获取考勤记录
     *
     * @param eId 员工Id
     * @return 对应的考勤记录
     */
    Attendance findByEid(@Param("eId") Integer eId);

    /**
     * 插入一条记录
     *
     * @param recode 考勤记录
     * @return 是否插入成功
     */
    int insert(Attendance recode);

    /**
     * 更新一条记录
     *
     * @param recode 考勤记录
     * @return 是否更新成功
     */
    int update(Attendance recode);

    /**
     * 删除记录-批量删除
     *
     * @param aIdList 记录Id列表
     */
    void deleteByaId(@Param("aIdList") List<String> aIdList);

    /**
     * 通过员工编号删除考勤记录
     *
     * @param eId 员工编号
     */
    void deleteByeId(@Param("eId") Integer eId);

    /**
     * 修改考勤记录
     *
     * @param aId   考勤记录Id
     * @param aType 考勤记录类型
     * @param aTime 打卡时间
     * @return 返回是否修改成功
     */
    int changeAttendanceRecord(@Param("aId") Integer aId,
                               @Param("aType") Integer aType,
                               @Param("aTime") String aTime);

}
