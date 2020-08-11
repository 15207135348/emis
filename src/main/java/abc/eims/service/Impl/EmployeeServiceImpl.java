package abc.eims.service.Impl;

import abc.eims.dao.EmployeeMapper;
import abc.eims.entity.Employee;
import abc.eims.exception.CustomException;
import abc.eims.service.IEmployeeService;
import abc.eims.utils.DateTimeUtil;
import abc.eims.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/9 10:35
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee findEmployeeByIdAndPassword(String account, String password) throws CustomException {

        String encode = MD5Utils.encodeByMD5(password);
//        Employee employee = new Employee();
//        employee.seteAccount(account);
//        employee.setePassword(encode);
        Employee employee1 = employeeMapper.selectByAccountAndPassword(account, encode);
        if (employee1 == null) {
            throw new CustomException("账号或密码错误");
        }
        return employee1;
    }

    @Override
    public Employee findEmployeeById(int eId) {
        return employeeMapper.findById(eId);
    }

    @Override
    public List<Employee> getAllEmployeeInfo() {
        return employeeMapper.findAll();
    }

    @Override
    public void delEmployeeInfoByAccount(List<String> list) {

        employeeMapper.deleteByAccount(list);
    }

    @Override
    public void changeEmployeeInfo(Integer eId, String account, String password, String name, String birthday, Integer sex, String phone, String email, String roleId) {

    }

//    @Override
//    public void changeEmployeeInfo(Integer eId,
//                                   String account,
//                                   String password,
//                                   String name,
//                                   String birthday,
//                                   Integer sex,
//                                   String phone,
//                                   String email,
//                                   String roleId) {
//        employeeMapper.changeEmployeeInfo(eId, account, password, name, birthday, sex, phone, email, roleId);
//    }

    @Override
    public void changeEmployeeRole(String account, String roleId) {
        employeeMapper.changeEmployeeRole(account, roleId);
    }

    @Override
    public void insert(Employee employee) {
        employeeMapper.insert(employee);
    }

    @Override
    public void updateOrInsert(Integer eId, String account, String password, String name, String birthday, Integer sex, String phone, String email, Integer roleId) {
        Employee employee = employeeMapper.findById(eId);
        if (employee != null) {
            employeeMapper.update(eId, account, password, name, birthday, sex, phone, email, roleId);
        }

        Date date = DateTimeUtil.dateToStamp(birthday);
        employee.setE_id(eId);
        employee.setE_account(account);
        employee.setE_password(password);
        employee.setE_name(name);
        employee.setE_sex(sex);
        employee.setE_birthday(date);
        employee.setE_phone(phone);
        employee.setE_role_id(roleId);

        employeeMapper.insert(employee);

    }

    @Override
    public Employee findByAccount(String eAccount) {
        return employeeMapper.findByAccount(eAccount);
    }

}
