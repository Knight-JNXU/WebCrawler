package code.utils;

import code.model.BaseModel;
import com.google.gson.Gson;

/**
 * Created by Knight_JXNU on 2016/12/28.
 */
public class JSONUtils {
    public <T extends BaseModel> T str2Obj(String str, Class clazz){
        return (T) new Gson().fromJson(str, clazz);
    }

    public <T extends BaseModel> String obj2Str(T obj){
        return new Gson().toJson(obj);
    }
}
