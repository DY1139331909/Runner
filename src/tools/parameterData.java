package tools;

import java.util.HashMap;
import java.util.Map;
/**
 * 存储校验数据
 */
public class parameterData {
    public parameterData() {
    }

    public parameterData(Map parameterData) {
        this.parameterData = parameterData;
    }

    public Map getParameterData() {
        return parameterData;
    }

    public void setParameterData(Map parameterData) {
        this.parameterData = parameterData;
    }

    Map parameterData = new HashMap();

    public void putInto(String field, Object values) {
        parameterData.put(field, values.toString());
    }

    public String getFrom(String field) {
        return (String) parameterData.get(field);
    }
}
