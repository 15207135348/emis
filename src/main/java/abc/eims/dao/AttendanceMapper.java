package abc.eims.dao;

import abc.eims.entity.Attendance;
import abc.eims.entity.Items;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/9 9:14
 */
@Repository
public interface AttendanceMapper {

    List<Attendance> findAll();

    List<Attendance> getMyAttendance(@Param("eId") Integer eId);

    Attendance findById(@Param("aId") Integer aId);

    int insert(Attendance recode);

    int update(Attendance recode);

    void deleteById(@Param("aId") Integer aId);

    int changeAttendanceRecord(@Param("aId") Integer aId, @Param("aType") Integer aType, @Param("aTime") String aTime);

}
