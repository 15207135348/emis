import abc.eims.dao.AttendanceMapper;
import abc.eims.entity.Attendance;
import abc.eims.service.Impl.AttendanceServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/12 9:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testAttendance {

    @Autowired
    private AttendanceServiceImpl attendanceService;
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Test
    public void test() {
        List<Attendance> allAttendance = attendanceService.getAllAttendance();
        for (Attendance a : allAttendance) {
            System.out.println(a);
        }
    }

    @Test
    public void test1() {
        List<Attendance> allAttendance = attendanceMapper.getMyAttendance(2);
        for (Attendance a : allAttendance) {
            System.out.println(a);
        }
    }

    @Test
    public void test2() {
        attendanceService.changeAttendanceRecord(1,3,null);
    }



}
