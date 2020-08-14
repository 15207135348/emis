package abc.eims.controller;

import abc.eims.service.Impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 与个人信息操作有关的Controller层
 *
 * @author wangzhe
 * @date 2020/8/9 15:02
 */
@Controller
@RequestMapping("personal_center")
public class UserInfoController {

    @Autowired
    private UserInfoServiceImpl userInfoService;

    /**
     * 修改密码
     *
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return 返回是否修改成功
     */
    @RequestMapping("set_my_password")
    @ResponseBody
    public Map<String, String> changePassword(
            @RequestParam("old_password") String oldPwd,
            @RequestParam("new_password") String newPwd) {
        Map<String, String> map = new HashMap<>();
        try {
            userInfoService.changePassword(oldPwd, newPwd);
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "密码修改失败");
            return map;
        }
        map.put("code", "0");
        map.put("msg", "密码修改成功");
        return map;
    }

    /**
     * 修改个人信息
     *
     * @param name     姓名
     * @param birthday 生日
     * @param sex      性别
     * @param phone    电话号码
     * @param email    e-mail
     * @return 返回是否修改成功
     */
    @RequestMapping("set_my_info")
    @ResponseBody
    public Map<String, String> changeInfo(
                        @RequestParam("e_name") String name,
                        @RequestParam("e_birthday") Date birthday,
                        @RequestParam("e_sex") Integer sex,
                        @RequestParam("e_phone") String phone,
                        @RequestParam("e_email") String email) {
        Map<String, String> map = new HashMap<>();
        try {
            userInfoService.changeInfo(name, birthday, sex, phone, email);
        } catch (Exception e) {
            map.put("code", "-1");
            map.put("msg", "更新信息失败");
            return map;
        }
        map.put("code", "0");
        map.put("msg", "更新成功");
        return map;
    }

}
