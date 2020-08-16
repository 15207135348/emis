package abc.eims.service;

import abc.eims.entity.Employee;

import java.util.List;

/**
 * @author wangzhe
 * @date 2020/8/9 10:01
 */
public interface IEmployeeService {

    /**
     * 通过账号和密码查找用户
     *
     * @param account  账号
     * @param password 密码
     * @return 对应的用户信息
     */
    Employee findEmployeeByIdAndPassword(String account, String password);

    /**
     * 通过用户Id查找用户
     *
     * @param id 用户Id
     * @return 对应的用户信息
     */
    Employee findEmployeeById(int id);

    /**
     * 查找所有用户信息
     *
     * @return 所有用户信息
     */
    List<Employee> getAllEmployeeInfo();

    /**
     * 通过账号删除用户信息
     *
     * @param accountList 账号
     */
    void delEmployeeInfoByAccount(List<String> accountList);

    /**
     * 管理员修改用户权限
     *
     * @param account 账号
     * @param roleId  权限
     */
    void changeEmployeeRole(String account, String roleId);

    /**
     * 插入一条记录
     *
     * @param employee 员工信息
     */
    void insert(Employee employee);

    /**
     * 插入或更新一条记录
     *
     * @param account  账号
     * @param name     姓名
     * @param birthday 生日
     * @param sex      性别
     * @param phone    电话
     * @param email    e-mail
     * @param roleId   权限
     * @return 是否操作成功
     */
    void update(String account, String name, String birthday,
                       Integer sex, String phone, String email, Integer roleId);

    /**
     * 通过账号查找员工信息
     *
     * @param eAccount 账号
     * @return 对应的员工信息
     */
    Employee findByAccount(String eAccount);

    /**
     * 模糊查询员工信息
     *
     * @param eAccount 账号
     * @return 员工信息
     */
    List<Employee> fuzzyFindByAccount(String eAccount);

}
