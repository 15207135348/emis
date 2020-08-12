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

    //没问题
    @Override
    public Employee findEmployeeByIdAndPassword(String account, String password) throws CustomException {

        String encode = MD5Utils.encodeByMD5(password);
        Employee employee1 = employeeMapper.selectByAccountAndPassword(account, encode);
        if (employee1 == null) {
            throw new CustomException("账号或密码错误");
        }
        return employee1;
    }

    //没问题
    @Override
    public Employee findEmployeeById(int eId) {
        return employeeMapper.findById(eId);
    }

    //没问题
    @Override
    public List<Employee> getAllEmployeeInfo() {
        return employeeMapper.findAll();
    }

    //没问题
    @Override
    public void delEmployeeInfoByAccount(List<String> list) {
        employeeMapper.deleteEmployeeInfoByAccount(list);
    }

    @Override
    public void changeEmployeeInfo(Integer eId, String account, String password, String name, String birthday, Integer sex, String phone, String email, String roleId) {

    }


    @Override
    public void changeEmployeeRole(String account, String roleId) {
        employeeMapper.changeEmployeeRole(account, roleId);
    }

    @Override
    public void insert(Employee employee) {
        employeeMapper.insert(employee);
    }


    @Override
    public int updateOrInsert(String account, String name,
                               String birthday, Integer sex, String phone, String email, Integer roleId) {
        Employee employee = employeeMapper.findByAccount(account);
        if (employee != null) {
            employeeMapper.update(employee.getE_id(), account, employee.getE_password(),
                    name, birthday, sex, phone, email, roleId);
            return 0;
        } else {
            Date date = DateTimeUtil.dateToStamp(birthday);
            Employee e = new Employee();
            e.setE_account(account);
            e.setE_password(MD5Utils.encodeByMD5("123456"));
            e.setE_name(name);
            e.setE_sex(sex);
            e.setE_birthday(date);
            e.setE_phone(phone);
            e.setE_email(email);
            e.setE_role_id(roleId);
            employeeMapper.insert(e);
            return 1;
        }
    }

    @Override
    public Employee findByAccount(String eAccount) {
        return employeeMapper.findByAccount(eAccount);
    }

}
