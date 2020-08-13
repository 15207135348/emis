package abc.eims.controller;

import abc.eims.common.Const;
import abc.eims.entity.Employee;
import abc.eims.service.Impl.EmployeeServiceImpl;
import abc.eims.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "get_menu", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getMenu() {
        int eId = Integer.parseInt(Objects.requireNonNull(CookieUtil.getCookieValueFromRequest()));
        Employee employee = employeeService.findEmployeeById(eId);
        if (employee == null) {
            return getMenuById(0);
        }
        int roleId = employee.getE_role_id();
        return getMenuById(roleId);
    }


    private String getMenuById(int roleId) {
        if (roleId == 1) {
            return Const.one;
        } else if (roleId == 2) {
            return Const.two;
        }
        return Const.three;
    }

}