package abc.eims.service;

import abc.eims.entity.Employee;

import java.util.Date;

/**
 * @author wangzhe
 * @date 2020/8/9 15:12
 */
public interface IUserInfoService {

    void changePassword(String oldPwd, String newPwd);

    Employee changeInfo(String name,
                        Date birthday,
                        Integer sex,
                        String phone,
                        String email);

}
