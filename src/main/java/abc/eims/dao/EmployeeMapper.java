package abc.eims.dao;

import abc.eims.entity.Attendance;
import abc.eims.entity.Employee;
import abc.eims.entity.Items;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/9 9:14
 */
@Repository
public interface EmployeeMapper {

    List<Employee> findAll();

    Employee findById(Integer id);

    Employee findByAccount(String account);

    int insert(Employee recode);

    int update(Employee recode);

    void deleteById(Integer id);

    int deleteByAccount(String account);

    Employee selectByAccountAndPassword(String eAccount, String ePassword);

    Integer findIdByAccountAndPassword(String eAccount, String ePassword);

    int changeEmployeeInfo(String account,
                           String name,
                           Date birthday,
                           Integer sex,
                           String phone,
                           String email);

    int changeEmployeeRole(String account, String roleId);

}
