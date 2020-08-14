package abc.eims.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 员工考勤记录
 *
 * @author wangzhe
 * @date 2020/8/8 23:28
 */
public class Attendance {

    private Integer a_id;

    private Integer e_id;

    private Integer a_type;

    private String a_time;

    public Attendance() {
    }

    public Attendance(Integer a_id, Integer e_id, Integer a_type, String a_time) {
        this.a_id = a_id;
        this.e_id = e_id;
        this.a_type = a_type;
        this.a_time = a_time;
    }

    public Integer getA_id() {
        return a_id;
    }

    public void setA_id(Integer a_id) {
        this.a_id = a_id;
    }

    public Integer getE_id() {
        return e_id;
    }

    public void setE_id(Integer e_id) {
        this.e_id = e_id;
    }

    public Integer getA_type() {
        return a_type;
    }

    public void setA_type(Integer a_type) {
        this.a_type = a_type;
    }

    public String getA_time() {
        return a_time;
    }

    public void setA_time(String a_time) {
        this.a_time = a_time;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "a_id=" + a_id +
                ", e_id=" + e_id +
                ", a_type=" + a_type +
                ", a_time='" + a_time + '\'' +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject object = (JSONObject) JSON.toJSON(this);
        object.put("a_type", a_type == 1 ? "上班打卡" : "下班打卡");
        return object;
    }

}
