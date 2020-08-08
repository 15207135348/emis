package abc.eims.entity;

import java.util.Date;

/**
 * @author wangzhe
 * @date 2020/8/7 15:39
 */
public class Employee {

    private Integer eId;

    private String eAccount;

    private String ePassword;

    private String eName;

    private Date eBirthday;

    private String eSex;

    private String ePhone;

    private String eEmail;

    private Integer eRoleId;

    public Employee() {
    }

    public Employee(Integer eId, String eAccount, String ePassword, String eName, Date eBirthday, String eSex, String ePhone, String eEmail, Integer eRoleId) {
        this.eId = eId;
        this.eAccount = eAccount;
        this.ePassword = ePassword;
        this.eName = eName;
        this.eBirthday = eBirthday;
        this.eSex = eSex;
        this.ePhone = ePhone;
        this.eEmail = eEmail;
        this.eRoleId = eRoleId;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String geteAccount() {
        return eAccount;
    }

    public void seteAccount(String eAccount) {
        this.eAccount = eAccount;
    }

    public String getePassword() {
        return ePassword;
    }

    public void setePassword(String ePassword) {
        this.ePassword = ePassword;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public Date geteBirthday() {
        return eBirthday;
    }

    public void seteBirthday(Date eBirthday) {
        this.eBirthday = eBirthday;
    }

    public String geteSex() {
        return eSex;
    }

    public void seteSex(String eSex) {
        this.eSex = eSex;
    }

    public String getePhone() {
        return ePhone;
    }

    public void setePhone(String ePhone) {
        this.ePhone = ePhone;
    }

    public String geteEmail() {
        return eEmail;
    }

    public void seteEmail(String eEmail) {
        this.eEmail = eEmail;
    }

    public Integer geteRoleId() {
        return eRoleId;
    }

    public void seteRoleId(Integer eRoleId) {
        this.eRoleId = eRoleId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eId=" + eId +
                ", eAccount='" + eAccount + '\'' +
                ", ePassword='" + ePassword + '\'' +
                ", eName='" + eName + '\'' +
                ", eBirthday=" + eBirthday +
                ", eSex='" + eSex + '\'' +
                ", ePhone='" + ePhone + '\'' +
                ", eEmail='" + eEmail + '\'' +
                ", eRoleId=" + eRoleId +
                '}';
    }

}
