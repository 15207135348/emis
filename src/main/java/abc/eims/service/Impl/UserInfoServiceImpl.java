package abc.eims.service.Impl;

import abc.eims.dao.EmployeeMapper;
import abc.eims.dao.UserInfoMapper;
import abc.eims.entity.Employee;
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

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 修改密码
     *
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     */
    @Override
    public void changePassword(String oldPwd, String newPwd) {
        //从Cookie取出当前用户Id
        int eId = Integer.parseInt(Objects.requireNonNull(
                CookieUtil.getCookieValueFromRequest()));
        oldPwd = MD5Utils.encodeByMD5(oldPwd);
        newPwd = MD5Utils.encodeByMD5(newPwd);
        userInfoMapper.changePwd(eId, oldPwd, newPwd);
    }

    /**
     * 修改个人信息
     *
     * @param name     姓名
     * @param birthday 生日
     * @param sex      性别
     * @param phone    电话号码
     * @param email    e-mail
     */
    @Override
    public Employee changeInfo(String name, Date birthday,
                               Integer sex, String phone, String email) {
        //从Cookie取出当前用户Id
        int eId = Integer.parseInt(Objects.requireNonNull(
                CookieUtil.getCookieValueFromRequest()));
        userInfoMapper.changeInfo(eId, name, birthday, sex, phone, email);

        return employeeMapper.findById(eId);
    }

}
