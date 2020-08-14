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
 * 权限功能相关的Controller层
 *
 * @author wangzhe
 * @date 2020/8/9 11:34
 */
@Controller
@RequestMapping("menu")
public class RoleController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    /**
     * 通过不同的权限id，返回不同的页面。
     *
     * @return 返回主页面-JSON
     */
    @RequestMapping(value = "get_menu", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getMenu() {
        int eId = Integer.parseInt(Objects.requireNonNull(
                        CookieUtil.getCookieValueFromRequest()));
        Employee employee = employeeService.findEmployeeById(eId);
        if (employee == null) {
            return getMenuById(0);
        }
        int roleId = employee.getE_role_id();
        return getMenuById(roleId);
    }

    /**
     * 返回roleId对应的JSON
     *
     * @param roleId 权限id
     * @return 对应的JSON
     */
    private String getMenuById(int roleId) {
        if (roleId == 1) {
            return Const.SYSTEM_ADMINISTRATOR;
        } else if (roleId == 2) {
            return Const.ADMINISTRATOR;
        }
        return Const.CUSTOMER;
    }

}