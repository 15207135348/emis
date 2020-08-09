package abc.eims.dao;

import abc.eims.entity.Attendance;
import abc.eims.entity.Employee;
import abc.eims.entity.Items;
import org.apache.ibatis.annotations.Param;
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

    Employee findById(@Param("eId") Integer id);

    Employee findByAccount(@Param("account") String account);

    int insert(Employee recode);

    int update(Employee recode);

    void deleteById(@Param("eId") Integer id);

    int deleteByAccount(@Param("account") String account);

    Employee selectByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    int changeEmployeeInfo(@Param("account") String account,
                           @Param("name") String name,
                           @Param("birthday") Date birthday,
                           @Param("sex") Integer sex,
                           @Param("phone") String phone,
                           @Param("email") String email);

    int changeEmployeeRole(@Param("account") String account, @Param("roleId") String roleId);

}
