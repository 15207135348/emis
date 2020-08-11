package abc.eims.dao;

import abc.eims.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

    int update(@Param("eId") Integer eId,
               @Param("account") String account,
               @Param("password") String password,
               @Param("name") String name,
               @Param("birthday") String birthday,
               @Param("sex") Integer sex,
               @Param("phone") String phone,
               @Param("email") String email,
               @Param("roleId") Integer roleId);

    void deleteById(@Param("eId") Integer id);

    int deleteByAccount(@Param("list") List<String> accountList);

    Employee selectByAccountAndPassword(@Param("account") String account, @Param("password") String password);

//    int changeEmployeeInfo(@Param("eId") Integer eId,
//                           @Param("account") String account,
//                           @Param("password") String password,
//                           @Param("name") String name,
//                           @Param("birthday") String birthday,
//                           @Param("sex") Integer sex,
//                           @Param("phone") String phone,
//                           @Param("email") String email,
//                           @Param("roleId") String roleId);

    int changeEmployeeRole(@Param("account") String account, @Param("roleId") String roleId);

}
