package abc.eims.service.Impl;

import abc.eims.dao.AttendanceMapper;
import abc.eims.dao.EmployeeMapper;
import abc.eims.entity.Employee;
import abc.eims.service.IEmployeeService;
import abc.eims.utils.DateTimeUtil;
import abc.eims.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private AttendanceMapper attendanceMapper;

    /**
     * 通过账号密码查找用户
     *
     * @param account  账号
     * @param password 密码
     * @return 员工信息
     */
    @Override
    public Employee findEmployeeByIdAndPassword(String account, String password){
        //对密码进行MD5加密然后进行查询
        String encode = MD5Utils.encodeByMD5(password);
        return employeeMapper.selectByAccountAndPassword(account, encode);
    }

    /**
     * 通过员工Id查找员工信息
     *
     * @param eId 员工Id
     * @return 对应员工信息
     */
    @Override
    public Employee findEmployeeById(int eId) {
        return employeeMapper.findById(eId);
    }

    /**
     * 获取说有员工信息
     *
     * @return 所有员工信息
     */
    @Override
    public List<Employee> getAllEmployeeInfo() {
        return employeeMapper.findAll();
    }

    /**
     * 删除员工信息
     *
     * @param list 通过员工账号删除员工信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delEmployeeInfoByAccount(List<String> list) {
        for (String account : list) {
            /*删除员工信息前，将改员工对应的考勤记录删除*/
            Employee employee = employeeMapper.findByAccount(account);
            Integer eId = employee.getE_id();
            attendanceMapper.deleteByeId(eId);
        }
        employeeMapper.deleteEmployeeInfoByAccount(list);
    }

    /**
     * 管理员修改员工权限
     *
     * @param account 账号
     * @param roleId  权限
     */
    @Override
    public void changeEmployeeRole(String account, String roleId) {
        employeeMapper.changeEmployeeRole(account, roleId);
    }

    /**
     * 新增员工
     *
     * @param employee 员工信息
     */
    @Override
    public void insert(Employee employee) {
        employeeMapper.insert(employee);
    }

    /**
     * 新增或修改员工信息
     *
     * @param account  账号
     * @param name     姓名
     * @param birthday 生日
     * @param sex      性别
     * @param phone    电话
     * @param email    email
     * @param roleId   权限
     * @return 是否操作成功
     */
    @Override
    public void update(String account, String name,
                              String birthday, Integer sex,
                              String phone, String email,
                              Integer roleId) {
        /*查找员工是否存在，不存在则插入一条员工信息，存在则更新。*/
        Employee employee = employeeMapper.findByAccount(account);
        if (employee != null) {
            employeeMapper.update(employee.getE_id(), account,
                    employee.getE_password(), name, birthday, sex, phone, email, roleId);

        }
    }

    /**
     * 通过账号查找员工信息
     *
     * @param eAccount 账号
     * @return 对应的员工信息
     */
    @Override
    public Employee findByAccount(String eAccount) {
        return employeeMapper.findByAccount(eAccount);
    }

    @Override
    public Employee findByPhone(String phone) {
        return employeeMapper.findByPhone(phone);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeMapper.findByEmail(email);
    }


    /**
     * 通过账号查找员工信息
     *
     * @param eAccount 账号
     * @return 对应的员工信息
     */
    @Override
    public List<Employee> fuzzyFindByAccount(String eAccount) {
        return employeeMapper.fuzzyFindByAccount(eAccount);
    }


}
