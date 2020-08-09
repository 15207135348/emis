package abc.eims.entity;

/**
 * @author wangzhe
 * @date 2020/8/8 23:28
 */
public class Attendance {

    private Integer aId;

    private Integer eId;

    private Integer aType;

    private String aTime;

    public Attendance() {
    }

    public Attendance(Integer aId, Integer eId, Integer aType, String aTime) {
        this.aId = aId;
        this.eId = eId;
        this.aType = aType;
        this.aTime = aTime;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Integer getaType() {
        return aType;
    }

    public void setaType(Integer aType) {
        this.aType = aType;
    }

    public String getaTime() {
        return aTime;
    }

    public void setaTime(String aTime) {
        this.aTime = aTime;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "aId=" + aId +
                ", eId=" + eId +
                ", aType=" + aType +
                ", aTime='" + aTime + '\'' +
                '}';
    }

}
