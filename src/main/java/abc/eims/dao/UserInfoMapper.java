package abc.eims.dao;

import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author wangzhe
 * @date 2020/8/9 15:15
 */
@Repository
public interface UserInfoMapper {

    int changePwd(String account, String oldPwd, String newPwd);

    int changeInfo(String eId, String name, Date birthday, Integer sex, String phone, String email);

}
