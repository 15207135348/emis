import abc.eims.dao.EmployeeMapper;
import abc.eims.entity.Employee;
import abc.eims.service.Impl.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testEmployeeMapper {


    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmployeeServiceImpl employeeService;

    @Test
    public void test() {
//        System.out.println(MD5Utils.encodeByMD5("admin"));
//        String birthday = "2020-07-21 09:25:23";
//        Date date = DateTimeUtil.dateToStamp(birthday);
//        System.out.println(date);
//        employeeMapper.update(2, "123", "12345678",
//                "张三", "1994-08-21", 1, "15062225597", "sdd@abc.com", 1);

//        employeeMapper.update(2,null ,null ,
//                "李四", null, null, null, null, null);
//
//        Employee employee = employeeService.findEmployeeById(1);
//        System.out.println(employee);

//        List<String> list = new ArrayList<>();
//        list.add("123");
//        list.add("1996249");
//        employeeService.delEmployeeInfoByAccount(list);

//        employeeService.updateOrInsert("11111", "王二麻子", "2020-12-12",
//                1, "15207135348", "1365733349@qq.com", 2);
        List<Employee> e = employeeService.getAllEmployeeInfo();
        for (Employee e1 : e) {
            System.out.println(e1);
        }

    }

}
