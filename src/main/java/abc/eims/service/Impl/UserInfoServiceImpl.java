package abc.eims.service.Impl;

import abc.eims.dao.UserInfoMapper;
import abc.eims.service.IUserInfoService;
import abc.eims.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author wangzhe
 * @date 2020/8/9 15:13
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public int changePassword(String account, String oldPwd, String newPwd) {
        oldPwd = MD5Utils.encodeByMD5(oldPwd);
        return userInfoMapper.changePwd(account, oldPwd, newPwd);
    }

    @Override
    public int changeInfo(HttpSession httpSession, String name, Date birthday, Integer sex, String phone, String email) {
        String employeeId = (String) httpSession.getAttribute("employeeId");
        return userInfoMapper.changeInfo(employeeId, name, birthday, sex, phone, email);
    }


}
