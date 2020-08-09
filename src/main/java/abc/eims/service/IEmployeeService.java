package abc.eims.service;

import abc.eims.entity.Employee;
import abc.eims.exception.CustomException;

import java.util.Date;
import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/9 10:01
 */
public interface IEmployeeService {

    Employee findEmployeeByIdAndPassword(String account, String password) throws CustomException;

    int findIdByAccountAndPassword(String account, String password) throws CustomException;


    Employee findEmployeeById(int id);

    List<Employee> getAllEmployeeInfo();

    void delEmployeeInfoByAccount(String account);

    void changeEmployeeInfo(String account,
                            String name,
                            Date birthday,
                            Integer sex,
                            String phone,
                            String email);

    void changeEmployeeRole(String account, String roleId);

    void insert(Employee employee);

    Employee findByAccount(String eAccount);

}
