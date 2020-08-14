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

    /**
     * 查找所有员工信息
     *
     * @return 所有员工信息
     */
    List<Employee> findAll();

    /**
     * 通过员工编号查找员工信息
     *
     * @param eId 员工编号
     * @return 对应的员工信息
     */
    Employee findById(@Param("eId") Integer eId);

    /**
     * 通过账号查找员工信息
     *
     * @param account 员工账号
     * @return 对应的员工信息
     */
    Employee findByAccount(@Param("account") String account);

    /**
     * 插入一条员工信息
     *
     * @param recode 员工信息
     * @return 返回是否插入成功
     */
    int insert(Employee recode);

    /**
     * 更新员工信息
     *
     * @param eId      员工编号
     * @param account  账号
     * @param password 密码
     * @param name     姓名
     * @param birthday 生日
     * @param sex      性别
     * @param phone    电话
     * @param email    e-mail
     * @param roleId   权限
     * @return
     */
    int update(@Param("eId") Integer eId,
               @Param("account") String account,
               @Param("password") String password,
               @Param("name") String name,
               @Param("birthday") String birthday,
               @Param("sex") Integer sex,
               @Param("phone") String phone,
               @Param("email") String email,
               @Param("roleId") Integer roleId);

    /**
     * 通过员工Id删除员工信息
     *
     * @param eId 员工Id
     */
    void deleteById(@Param("eId") Integer eId);

    /**
     * 通过员工账号删除员工信息
     *
     * @param accountList 员工账号
     */
    void deleteEmployeeInfoByAccount(@Param("list") List<String> accountList);

    /**
     * 通过账号密码查找员工信息
     *
     * @param account  账号
     * @param password 密码
     * @return 返回员工信息
     */
    Employee selectByAccountAndPassword(@Param("account") String account,
                                        @Param("password") String password);

    /**
     * 修改权限
     *
     * @param account 账号
     * @param roleId  权限
     * @return 返回是否修改成功
     */
    int changeEmployeeRole(@Param("account") String account,
                           @Param("roleId") String roleId);

    List<Employee> fuzzyFindByAccount(@Param("account") String account);

}
