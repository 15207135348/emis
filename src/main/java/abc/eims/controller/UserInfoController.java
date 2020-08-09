package abc.eims.controller;

import abc.eims.exception.CustomException;
import abc.eims.service.Impl.UserInfoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhe
 * @date 2020/8/9 15:02
 */
@Controller
@RequestMapping("pernal_center")
public class UserInfoController {

    private UserInfoServiceImpl userInfoService;

    @RequestMapping("set_my_password")
    @ResponseBody
    public Map<String, String> changePassword(String account, String oldPwd, String newPwd) {

        Map<String, String> map = new HashMap<>();
//        int flag = 0;
        try {
            userInfoService.changePassword(account, oldPwd, newPwd);
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "密码修改失败");
        }
        map.put("code", "0");
        map.put("msg", "密码修改成功");
        return map;
    }

    @RequestMapping("set_my_info")
    @ResponseBody
    public Map<String, String> changeInfo(HttpSession httpSession,
                                          String name,
                                          Date birthday,
                                          Integer sex,
                                          String phone,
                                          String email) {

        Map<String, String> map = new HashMap<>();
        try {
            userInfoService.changeInfo(httpSession, name, birthday, sex, phone, email);
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "更新信息失败");
        }
        map.put("code", "0");
        map.put("msg", "更新成功");
        return map;
    }

}