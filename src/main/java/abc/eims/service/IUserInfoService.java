package abc.eims.service;

import abc.eims.entity.Employee;

import java.util.Date;

/**
 * @author wangzhe
 * @date 2020/8/9 15:12
 */
public interface IUserInfoService {

    /**
     * 密码修改
     *
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     */
    void changePassword(String oldPwd, String newPwd);

    /**
     * 修改个人信息
     *
     * @param name     姓名
     * @param birthday 生日
     * @param sex      性别
     * @param phone    电话
     * @param email    e-mail
     * @return 修改后的员工信息
     */
    Employee changeInfo(String name,
                        Date birthday,
                        Integer sex,
                        String phone,
                        String email);

}
