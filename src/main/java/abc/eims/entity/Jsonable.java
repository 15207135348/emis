package abc.eims.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public interface Jsonable {

    default JSONObject toJSON(){
        return (JSONObject) JSON.toJSON(this);
    }
}
