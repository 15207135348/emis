package abc.eims.controller;

import abc.eims.entity.Employee;
import abc.eims.exception.CustomException;
import abc.eims.service.Impl.EmployeeServiceImpl;
import abc.eims.utils.CaptchaUtil;
import abc.eims.utils.CookieUtil;
import abc.eims.utils.MD5Utils;
import abc.eims.vo.Response;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/**
 * @author wangzhe
 * @date 2020/8/9 11:34
 */
@Controller
@RequestMapping("menu")
public class RoleController {


    @Autowired
    private EmployeeServiceImpl employeeService;


    /**
     * 跳转到登陆页面
     */
    @RequestMapping(value = "get_menu", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getMenu() {
        int eId = Integer.parseInt(Objects.requireNonNull(CookieUtil.getCookieValueFromRequest()));
        Employee employee = employeeService.findEmployeeById(eId);
        if (employee == null){
            return getMenuById(0);
        }
        int roleId = employee.getE_role_id();
        return getMenuById(roleId);

    }


    private String getMenuById(int roleId){

        String one = "[\n" +
                "\t{\n" +
                "\t\t\"title\": \"员工管理\",\n" +
                "\t\t\"icon\": \"&#xe655;\",\n" +
                "\t\t\"href\": \"../resources/page/news/newsList.html\",\n" +
                "\t\t\"spread\": true,\n" +
                "\t\t\"children\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"title\": \"员工信息\",\n" +
                "\t\t\t\t\"icon\": \"&#xe66f;\",\n" +
                "\t\t\t\t\"href\": \"../resources/page/user/userList_0.html\",\n" +
                "\t\t\t\t\"spread\": false\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"title\": \"添加员工\",\n" +
                "\t\t\t\t\"icon\": \"&#xe665;\",\n" +
                "\t\t\t\t\"href\": \"../resources/page/user/userAdd_0.html\",\n" +
                "\t\t\t\t\"spread\": false\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"title\": \"考勤管理\",\n" +
                "\t\t\"icon\": \"&#xe634;\",\n" +
                "\t\t\"href\": \"../resources/page/img/images.html\",\n" +
                "\t\t\"spread\": true,\n" +
                "\t\t\"children\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"title\": \"考勤信息\",\n" +
                "\t\t\t\t\"icon\": \"&#xe61c;\",\n" +
                "\t\t\t\t\"href\": \"../resources/page/news/newsList.html\",\n" +
                "\t\t\t\t\"spread\": false\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"title\": \"个人中心\",\n" +
                "\t\t\"icon\": \"&#xe716;\",\n" +
                "\t\t\"href\": \"\",\n" +
                "\t\t\"spread\": false,\n" +
                "\t\t\"children\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"title\": \"个人资料\",\n" +
                "\t\t\t\t\"icon\": \"&#xe61c;\",\n" +
                "\t\t\t\t\"href\": \"../resources/page/user/userInfo.html\",\n" +
                "\t\t\t\t\"spread\": false\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"title\": \"修改密码\",\n" +
                "\t\t\t\t\"icon\": \"&#xe609;\",\n" +
                "\t\t\t\t\"href\": \"../resources/page/user/changePwd.html\",\n" +
                "\t\t\t\t\"spread\": false\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}\n" +
                "]";

        String two = "[\n" +
                "\t{\n" +
                "\t\t\"title\": \"员工管理\",\n" +
                "\t\t\"icon\": \"&#xe655;\",\n" +
                "\t\t\"href\": \"../resources/page/news/newsList.html\",\n" +
                "\t\t\"spread\": true,\n" +
                "\t\t\"children\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"title\": \"员工信息\",\n" +
                "\t\t\t\t\"icon\": \"&#xe66f;\",\n" +
                "\t\t\t\t\"href\": \"../resources/page/user/userList_1.html\",\n" +
                "\t\t\t\t\"spread\": false\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"title\": \"添加员工\",\n" +
                "\t\t\t\t\"icon\": \"&#xe665;\",\n" +
                "\t\t\t\t\"href\": \"../resources/page/user/userAdd_1.html\",\n" +
                "\t\t\t\t\"spread\": false\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"title\": \"考勤管理\",\n" +
                "\t\t\"icon\": \"&#xe634;\",\n" +
                "\t\t\"href\": \"../resources/page/img/images.html\",\n" +
                "\t\t\"spread\": true,\n" +
                "\t\t\"children\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"title\": \"考勤信息\",\n" +
                "\t\t\t\t\"icon\": \"&#xe61c;\",\n" +
                "\t\t\t\t\"href\": \"../resources/page/news/newsList.html\",\n" +
                "\t\t\t\t\"spread\": false\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"title\": \"个人中心\",\n" +
                "\t\t\"icon\": \"&#xe716;\",\n" +
                "\t\t\"href\": \"\",\n" +
                "\t\t\"spread\": false,\n" +
                "\t\t\"children\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"title\": \"个人资料\",\n" +
                "\t\t\t\t\"icon\": \"&#xe61c;\",\n" +
                "\t\t\t\t\"href\": \"../resources/page/user/userInfo.html\",\n" +
                "\t\t\t\t\"spread\": false\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"title\": \"修改密码\",\n" +
                "\t\t\t\t\"icon\": \"&#xe609;\",\n" +
                "\t\t\t\t\"href\": \"../resources/page/user/changePwd.html\",\n" +
                "\t\t\t\t\"spread\": false\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}\n" +
                "]";

        String three = "[\n" +
                "\t{\n" +
                "\t\t\"title\": \"员工管理\",\n" +
                "\t\t\"icon\": \"&#xe655;\",\n" +
                "\t\t\"href\": \"../resources/page/user/userList_2.html\",\n" +
                "\t\t\"spread\": true,\n" +
                "\t\t\"children\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"title\": \"员工信息\",\n" +
                "\t\t\t\t\"icon\": \"&#xe66f;\",\n" +
                "\t\t\t\t\"href\": \"../resources/page/user/userList_2.html\",\n" +
                "\t\t\t\t\"spread\": false\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"title\": \"考勤管理\",\n" +
                "\t\t\"icon\": \"&#xe634;\",\n" +
                "\t\t\"href\": \"../resources/page/img/images.html\",\n" +
                "\t\t\"spread\": true,\n" +
                "\t\t\"children\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"title\": \"考勤信息\",\n" +
                "\t\t\t\t\"icon\": \"&#xe61c;\",\n" +
                "\t\t\t\t\"href\": \"../resources/page/news/newsList_2.html\",\n" +
                "\t\t\t\t\"spread\": false\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"title\": \"个人中心\",\n" +
                "\t\t\"icon\": \"&#xe716;\",\n" +
                "\t\t\"href\": \"\",\n" +
                "\t\t\"spread\": false,\n" +
                "\t\t\"children\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"title\": \"个人资料\",\n" +
                "\t\t\t\t\"icon\": \"&#xe61c;\",\n" +
                "\t\t\t\t\"href\": \"../resources/page/user/userInfo.html\",\n" +
                "\t\t\t\t\"spread\": false\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"title\": \"修改密码\",\n" +
                "\t\t\t\t\"icon\": \"&#xe609;\",\n" +
                "\t\t\t\t\"href\": \"../resources/page/user/changePwd.html\",\n" +
                "\t\t\t\t\"spread\": false\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}\n" +
                "]";

        if (roleId == 1){
            return one;
        }
        if (roleId == 2){
            return two;
        }
        return three;
    }

}