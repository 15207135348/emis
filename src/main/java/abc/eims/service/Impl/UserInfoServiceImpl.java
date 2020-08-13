package abc.eims.service.Impl;

import abc.eims.dao.UserInfoMapper;
import abc.eims.service.IUserInfoService;
import abc.eims.utils.CookieUtil;
import abc.eims.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author wangzhe
 * @date 2020/8/9 15:13
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void changePassword(String oldPwd, String newPwd) {
        int eId = Integer.parseInt(Objects.requireNonNull(
                            CookieUtil.getCookieValueFromRequest()));
        oldPwd = MD5Utils.encodeByMD5(oldPwd);
        newPwd = MD5Utils.encodeByMD5(newPwd);
        userInfoMapper.changePwd(eId, oldPwd, newPwd);
    }

    @Override
    public void changeInfo(String name, Date birthday,
                           Integer sex, String phone, String email) {
        int eId = Integer.parseInt(Objects.requireNonNull(
                            CookieUtil.getCookieValueFromRequest()));
        userInfoMapper.changeInfo(eId, name, birthday, sex, phone, email);
    }

}
