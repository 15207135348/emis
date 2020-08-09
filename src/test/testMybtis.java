import abc.eims.dao.EmployeeMapper;
import abc.eims.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class testMybtis {


    @Autowired
    EmployeeMapper employeeMapper;



    @Test
    public void test(){
        int id = employeeMapper.findIdByAccountAndPassword("admin", "379B75FE452F76792BCCA3A64289599F");
        System.out.println(id);
    }
}
