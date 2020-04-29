import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author 程浩
 * @date 2020/4/29 13:30
 */


/*
*测试用例数据存储
*/

public class CaseData {
//    入参
    public static List<HashMap<String,String>> caseDataInputArr = new ArrayList<>();
//    出参
    public static List<HashMap<String,String>> caseDataResArr = new ArrayList<>();

    public void initCaseDataResArr(String caseDataInputStr){
        JSONObject jsonObject = JSONArray.parseObject(caseDataInputStr, JSONObject.class, Feature.OrderedField);//将字符串转化成json对象
        JSONObject jsonObject2 = JSONObject.parseObject(jsonObject.get("").toString(), JSONObject.class, Feature.OrderedField);
        LinkedHashMap<String, String> jsonMap = JSON.parseObject(jsonObject2.toString(), new TypeReference<LinkedHashMap<String, String>>() {
        }, Feature.OrderedField);

    }



}
