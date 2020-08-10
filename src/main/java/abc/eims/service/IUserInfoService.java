package abc.eims.service;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author wangzhe
 * @date 2020/8/9 15:12
 */
public interface IUserInfoService {

    int changePassword(String account, String oldPwd, String newPwd);

    int changeInfo(String name, Date birthday, Integer sex, String phone, String email);

}
