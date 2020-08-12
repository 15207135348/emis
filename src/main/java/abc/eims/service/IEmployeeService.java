package abc.eims.service;

import abc.eims.entity.Employee;
import abc.eims.exception.CustomException;

import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/9 10:01
 */
public interface IEmployeeService {

    Employee findEmployeeByIdAndPassword(String account, String password) throws CustomException;

    Employee findEmployeeById(int id);

    List<Employee> getAllEmployeeInfo();

    void delEmployeeInfoByAccount(List<String> accountList);

    void changeEmployeeInfo(Integer eId,
                            String account,
                            String password,
                            String name,
                            String birthday,
                            Integer sex,
                            String phone,
                            String email,
                            String roleId);

    void changeEmployeeRole(String account, String roleId);

    void insert(Employee employee);

    void updateOrInsert(String account, String name, String birthday, Integer sex, String phone, String email, Integer roleId);

    Employee findByAccount(String eAccount);

}
