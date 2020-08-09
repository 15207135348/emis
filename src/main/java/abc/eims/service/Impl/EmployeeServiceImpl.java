package abc.eims.service.Impl;

import abc.eims.dao.EmployeeMapper;
import abc.eims.entity.Employee;
import abc.eims.exception.CustomException;
import abc.eims.service.IEmployeeService;
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
        Employee employee = employeeMapper.selectByAccountAndPassword(account, encode);
        if (employee == null) {
            throw new CustomException("账号或密码错误");
        }
        return employee;
    }

    @Override
    public int findIdByAccountAndPassword(String account, String password) throws CustomException {

        String encode = MD5Utils.encodeByMD5(password);
        Integer id =  employeeMapper.findIdByAccountAndPassword(account, encode);
        if (id == null){
            throw new CustomException("账号或密码错误");
        }
        return id;
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
    public void delEmployeeInfoByAccount(String account) {
        employeeMapper.deleteByAccount(account);
    }

    @Override
    public void changeEmployeeInfo(String account, String name, Date birthday, Integer sex, String phone, String email) {
        employeeMapper.changeEmployeeInfo(account, name, birthday, sex, phone, email);
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
    public Employee findByAccount(String eAccount) {
        return employeeMapper.findByAccount(eAccount);
    }

}
