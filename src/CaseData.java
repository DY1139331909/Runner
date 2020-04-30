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
 */

public class CaseData {
    //    入参
    public static LinkedHashMap<String, String> caseDataInput = new LinkedHashMap<>();
    //    出参
    public static LinkedHashMap<String, String> caseDataRes = new LinkedHashMap<>();

    public void initCaseDataResArr(String caseDataInputStr, String caseName) {
        //将字符串转化成json对象
        JSONObject temp = JSONArray.parseObject(caseDataInputStr, JSONObject.class, Feature.OrderedField);
        JSONObject caseDataInputJson = JSONObject.parseObject(temp.get(caseName).toString(), JSONObject.class, Feature.OrderedField);
        LinkedHashMap<String, String> caseDataInputMap = JSON.parseObject(caseDataInputJson.toString(), new TypeReference<LinkedHashMap<String, String>>() {
        }, Feature.OrderedField);
        caseDataInput = caseDataInputMap;
        caseDataRes = caseDataInputMap;
    }


}
