package abc.eims.entity;

/**
 * 权限
 *
 * @author wangzhe
 * @date 2020/8/9 9:11
 */
public class Role {

    private Integer rId;

    private String rName;

    private String rDesc;

    public Role() {
    }

    public Role(Integer rId, String rName, String rDesc) {
        this.rId = rId;
        this.rName = rName;
        this.rDesc = rDesc;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrDesc() {
        return rDesc;
    }

    public void setrDesc(String rDesc) {
        this.rDesc = rDesc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "rId=" + rId +
                ", rName='" + rName + '\'' +
                ", rDesc='" + rDesc + '\'' +
                '}';
    }

}
