import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.*;

/**
 * @author 程浩
 * @date 2020/4/29 13:30
 */


/*
 *测试用例数据存储
 * LinkedHashMap{
 *  "comp":LinkedHashMap{
 *      key:value,
 *      key:value
 * }
 *  "comp":LinkedHashMap{
 *      key:value,
 *      key:value
 * }
 * }
 */

public class CaseData {
    //    入参
    public static LinkedHashMap<String, LinkedHashMap<String, String>> caseDataInput = new LinkedHashMap<>();
    //    出参
    public static LinkedHashMap<String, LinkedHashMap<String, String>> caseDataRes = new LinkedHashMap<>();

    /**
     * 初始化测试用例入参
     * @param caseDataInputStr
     * @param caseName
     */
    public void initCaseDataInput(String caseDataInputStr, String caseName) {
        //将字符串转化成json对象
        JSONObject temp = JSONArray.parseObject(caseDataInputStr, JSONObject.class, Feature.OrderedField);
        JSONObject caseDataInputJson = JSONObject.parseObject(temp.get(caseName).toString(), JSONObject.class, Feature.OrderedField);
        LinkedHashMap<String, String> caseDataInputMap = JSON.parseObject(caseDataInputJson.toString(), new TypeReference<LinkedHashMap<String, String>>() {
        }, Feature.OrderedField);
        for (Map.Entry<String, String> entry : caseDataInputMap.entrySet()) {
            caseDataInput.put(entry.getKey(),JSON.parseObject(entry.getValue(), new TypeReference<LinkedHashMap<String, String>>() {
            }, Feature.OrderedField));
        }
        caseDataRes = caseDataInput;
    }

    /**
     * 返回指定参数值
     * @param comp 组件名称
     * @param key 参数名称
     * @return
     */
    public String getCaseDataInput(String comp,String key){
        return caseDataInput.get(comp).get(key);
    }

}
















