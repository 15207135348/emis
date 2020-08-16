package abc.eims.entity;

import abc.eims.utils.DateTimeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * 员工信息
 *
 * @author wangzhe
 * @date 2020/8/7 15:39
 */
public class Employee {

    private Integer e_id;

    private String e_account;

    private String e_password;

    private String e_name;

    private Date e_birthday;

    private Integer e_sex;

    private String e_phone;

    private String e_email;

    private Integer e_role_id;


    public Employee() {
    }

    public Employee(Integer e_id, String e_account, String e_password,
                    String e_name, Date e_birthday, Integer e_sex,
                    String e_phone, String e_email, Integer e_role_id) {
        this.e_id = e_id;
        this.e_account = e_account;
        this.e_password = e_password;
        this.e_name = e_name;
        this.e_birthday = e_birthday;
        this.e_sex = e_sex;
        this.e_phone = e_phone;
        this.e_email = e_email;
        this.e_role_id = e_role_id;
    }

    public Integer getE_id() {
        return e_id;
    }

    public void setE_id(Integer e_id) {
        this.e_id = e_id;
    }

    public String getE_account() {
        return e_account;
    }

    public void setE_account(String e_account) {
        this.e_account = e_account;
    }

    public String getE_password() {
        return e_password;
    }

    public void setE_password(String e_password) {
        this.e_password = e_password;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public Date getE_birthday() {
        return e_birthday;
    }

    public void setE_birthday(Date e_birthday) {
        this.e_birthday = e_birthday;
    }

    public Integer getE_sex() {
        return e_sex;
    }

    public void setE_sex(Integer e_sex) {
        this.e_sex = e_sex;
    }

    public String getE_phone() {
        return e_phone;
    }

    public void setE_phone(String e_phone) {
        this.e_phone = e_phone;
    }

    public String getE_email() {
        return e_email;
    }

    public void setE_email(String e_email) {
        this.e_email = e_email;
    }

    public Integer getE_role_id() {
        return e_role_id;
    }

    public void setE_role_id(Integer e_role_id) {
        this.e_role_id = e_role_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "e_id=" + e_id +
                ", e_account='" + e_account + '\'' +
                ", e_password='" + e_password + '\'' +
                ", e_name='" + e_name + '\'' +
                ", e_birthday=" + e_birthday +
                ", e_sex=" + e_sex +
                ", e_phone='" + e_phone + '\'' +
                ", e_email='" + e_email + '\'' +
                ", e_role_id=" + e_role_id +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject object = (JSONObject) JSON.toJSON(this);
        object.put("e_birthday", DateTimeUtil.dateToStr(e_birthday, "yyyy-MM-dd"));
        object.put("e_sex", e_sex == 1 ? "男" : "女");
        object.put("e_role_id", e_role_id == 1 ? "超级管理员" : e_role_id == 2 ? "管理员" : "普通员工");
        return object;
    }

}
