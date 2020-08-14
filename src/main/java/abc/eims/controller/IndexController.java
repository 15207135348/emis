package abc.eims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转相关的Controller层
 *
 * @author wangzhe
 * @date 2020/8/8 12:37
 */
@Controller
@RequestMapping("index")
public class IndexController {

    /**
     * 跳转到登录页面
     *
     * @return login页面
     */
    @RequestMapping(value = "/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/toRegister")
    public String toRegister() {
        return "register";
    }

    /**
     * 跳转到首页
     *
     * @return index页面
     */
    @RequestMapping(value = "/toMain")
    public String toMain() {
        return "index";
    }

}
