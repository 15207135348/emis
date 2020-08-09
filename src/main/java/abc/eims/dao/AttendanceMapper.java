package abc.eims.dao;

import abc.eims.entity.Attendance;
import abc.eims.entity.Items;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/9 9:14
 */
@Repository
public interface AttendanceMapper {

    List<Attendance> findAll();

    List<Attendance> getMyAttendance(String eId);

    Attendance findById(Integer id);

    int insert(Attendance recode);

    int update(Attendance recode);

    void deleteById(Integer id);


}
