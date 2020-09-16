package tools;

import com.alibaba.fastjson.*;
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
    public static LinkedHashMap<String, LinkedHashMap<String, String[]>> caseData = new LinkedHashMap<>();
//    //    出参
//    public static LinkedHashMap<String, LinkedHashMap<String, String[]>> caseDataRes = new LinkedHashMap<>();

    //    当前组件
    public static String compName = "账户信息查询";

    //    设置当前执行组件名称
    public void setCompName(String name) {
        this.compName = name;
    }

    /**
     * 初始化测试用例入参
     *
     * @param caseDataStr
     * @param caseName
     */
    public void initCaseData(String caseDataStr, String caseName) {
        //将字符串转化成json对象
        JSONObject temp = JSONArray.parseObject(caseDataStr, JSONObject.class, Feature.OrderedField);
        JSONObject compJson = JSONObject.parseObject(temp.get(caseName).toString(), JSONObject.class, Feature.OrderedField);
        Iterator iterComp = compJson.entrySet().iterator();
        while (iterComp.hasNext()) {
            Map.Entry entry = (Map.Entry) iterComp.next();
            JSONObject paraJson = JSONObject.parseObject(entry.getValue().toString(), JSONObject.class, Feature.OrderedField);
            Iterator iterPara = paraJson.entrySet().iterator();
            LinkedHashMap<String, String[]> paraMap = new LinkedHashMap<>();
            while (iterPara.hasNext()) {
                Map.Entry entry2 = (Map.Entry) iterPara.next();
                JSONArray valueJson = JSONObject.parseArray(entry2.getValue().toString());
                String[] valueArr = valueJson.toArray(new String[0]);
                paraMap.put(entry2.getKey().toString(), valueArr);
            }
            this.caseData.put(entry.getKey().toString(), paraMap);
        }
//        caseDataRes = caseData;
    }

    /**
     * 返回指定参数值
     *
     * @param comp 组件名称
     * @param key  参数名称
     * @return
     */
    public static String getCaseData(String comp, String key) {
        String[] valueArr = caseData.get(comp).get(key);
        if (valueArr[0].equals("0")) {
            return valueArr[1];
        } else {
            String compRes = valueArr[0].split(".")[0];
            String paraRes = valueArr[0].split(".")[1];
            return caseData.get(compRes).get(paraRes)[0];
        }
    }

    public void putInto(String field, Object values) {
        String[] valueArr = caseData.get(this.compName).get(field);
        valueArr[1] = values.toString();
    }

    public String getFrom(String field) {
        return (String) CaseData.getCaseData(this.compName,field);
    }

/*    public static void main(String[] args) {
        String a = "{\"市价买入-深-正例-主板-0c-数量1000-00001\": {\"账户信息查询\": {\"acct_match\": [\"0\", \"jzjy_zl_001\"], \"case_acct\": [\"0\", \"\"], \"custid\": [\"0\", \"\"], \"custorgid\": [\"0\", \"\"], \"trdpwd\": [\"0\", \"\"], \"netaddr\": [\"0\", \"\"], \"orgid\": [\"0\", \"\"], \"operway\": [\"0\", \"\"], \"ext\": [\"0\", \"\"], \"fundid\": [\"0\", \"\"], \"sza_secuid\": [\"0\", \"\"], \"sha_secuid\": [\"0\", \"\"], \"zxjt_orderflag1\": [\"0\", \"before\"], \"zxjt_orderflag2\": [\"0\", \"after\"], \"zxjt_orderflag3\": [\"0\", \"cancel\"], \"zxjt_bsflag\": [\"0\", \"\"], \"market\": [\"0\", \"\"], \"stkcode\": [\"0\", \"\"], \"secuid\": [\"0\", \"\"], \"remark\": [\"0\", \"\"], \"remark1\": [\"0\", \"\"], \"remark2\": [\"0\", \"\"], \"remark3\": [\"0\", \"\"], \"remark4\": [\"0\", \"\"], \"remark5\": [\"0\", \"\"], \"locked\": [\"0\", \"\"], \"starttime\": [\"0\", \"\"], \"tc_allowerror\": [\"0\", \"\"], \"tc_result\": [\"0\", \"\"]}, \"410203-证券信息\": {\"custid\": [\"账户信息查询.custid\", \"110600000412\"], \"custorgid\": [\"账户信息查询.custorgid\", \"1109\"], \"trdpwd\": [\"账户信息查询.trdpwd\", \"123321\"], \"netaddr\": [\"账户信息查询.netaddr\", \"127.0.0.1      5C260A54DEE6 CPU(000206a7) HD(WD-WXD1A21T7130) Computer(DNSH-SUNWEI-NB) GUID(12733333-408C-4BC8-9F1C-7D05F934F941)\"], \"orgid\": [\"账户信息查询.orgid\", \"1109\"], \"operway\": [\"账户信息查询.operway\", \"7\"], \"ext\": [\"账户信息查询.ext\", \"0\"], \"funcid\": [\"0\", \"410203\"], \"market\": [\"0\", \"0\"], \"stklevel\": [\"0\", \"\"], \"stkcode\": [\"0\", \"000001\"], \"poststr\": [\"0\", \"\"], \"rowcount\": [\"0\", \"\"], \"stktype\": [\"0\", \"\"], \"zxjt_yq_qty\": [\"0\", \"1000\"], \"zxjt_price_type\": [\"0\", \"rise\"], \"zxjt_trade_type\": [\"0\", \"sz_a_B\"], \"stkcodeid\": [\"0\", \"\"], \"zxjt_fundid\": [\"0\", \"\"], \"zxjt_bsflag\": [\"0\", \"\"], \"zxjt_dayflag\": [\"0\", \"\"], \"zxjt_commissionrate\": [\"0\", \"\"], \"zxjt_commissionmodel\": [\"0\", \"\"], \"zxjt_trdid\": [\"0\", \"0\"], \"remark\": [\"0\", \"\"], \"remark1\": [\"0\", \"\"], \"poststr_out\": [\"0\", \"\"], \"market_out\": [\"0\", \"\"], \"moneytype\": [\"0\", \"\"], \"stkname\": [\"0\", \"\"], \"stkcode_out\": [\"0\", \"\"], \"stktype_out\": [\"0\", \"\"], \"priceunit\": [\"0\", \"\"], \"maxrisevalue\": [\"0\", \"\"], \"maxdownvalue\": [\"0\", \"\"], \"stopflag\": [\"0\", \"\"], \"mtkcalflag\": [\"0\", \"\"], \"bondintr\": [\"0\", \"\"], \"maxqty\": [\"0\", \"\"], \"minqty\": [\"0\", \"\"], \"buyunit\": [\"0\", \"\"], \"saleunit\": [\"0\", \"\"], \"stkstatus\": [\"0\", \"\"], \"stklevel_out\": [\"0\", \"\"], \"trdid\": [\"0\", \"\"], \"quitdate\": [\"0\", \"\"], \"fixprice\": [\"0\", \"\"], \"priceflag\": [\"0\", \"\"], \"memotext\": [\"0\", \"\"], \"zxjt_yq_price\": [\"0\", \"\"], \"zxjt_yq_cjje\": [\"0\", \"\"], \"tc_allowerror\": [\"0\", \"1\"], \"tc_result\": [\"0\", \"\"]}, \"410502委托前-资金查询\": {\"custid\": [\"410203-证券信息.custid\", \"\"], \"custorgid\": [\"410203-证券信息.custorgid\", \"\"], \"trdpwd\": [\"410203-证券信息.trdpwd\", \"\"], \"netaddr\": [\"410203-证券信息.netaddr\", \"\"], \"orgid\": [\"410203-证券信息.orgid\", \"\"], \"operway\": [\"410203-证券信息.operway\", \"\"], \"ext\": [\"410203-证券信息.ext\", \"\"], \"funcid\": [\"0\", \"410502\"], \"zxjt_bsflag\": [\"0\", \"\"], \"fundid\": [\"账户信息查询.fundid\", \"\"], \"moneytype\": [\"0\", \"0\"], \"remark\": [\"0\", \"\"], \"custid_out\": [\"0\", \"\"], \"fundid_out\": [\"0\", \"\"], \"orgid_out\": [\"0\", \"\"], \"moneytype_out\": [\"0\", \"\"], \"fundbal\": [\"0\", \"\"], \"fundavl\": [\"0\", \"\"], \"marketvalue\": [\"0\", \"\"], \"fund\": [\"0\", \"\"], \"stkvalue\": [\"0\", \"\"], \"fundseq\": [\"0\", \"\"], \"fundloan\": [\"0\", \"\"], \"fundbuy\": [\"0\", \"\"], \"fundsale\": [\"0\", \"\"], \"fundfrz\": [\"0\", \"\"], \"fundlastbal\": [\"0\", \"\"], \"tc_allowerror\": [\"0\", \"\"], \"tc_result\": [\"0\", \"\"]}, \"410410-取最大交易数量\": {\"custid\": [\"410502委托前-资金查询.custid\", \"\"], \"custorgid\": [\"410502委托前-资金查询.custorgid\", \"\"], \"trdpwd\": [\"410502委托前-资金查询.trdpwd\", \"\"], \"netaddr\": [\"410502委托前-资金查询.netaddr\", \"\"], \"orgid\": [\"410502委托前-资金查询.orgid\", \"\"], \"operway\": [\"410502委托前-资金查询.operway\", \"\"], \"ext\": [\"410502委托前-资金查询.ext\", \"\"], \"funcid\": [\"0\", \"410410\"], \"market\": [\"410203-证券信息.market\", \"\"], \"secuid\": [\"账户信息查询.sza_secuid\", \"\"], \"fundid\": [\"账户信息查询.fundid\", \"\"], \"stkcode\": [\"410203-证券信息.stkcode\", \"\"], \"bsflag\": [\"0\", \"0c\"], \"price\": [\"410203-证券信息.fixprice\", \"\"], \"bankcode\": [\"0\", \"\"], \"hiqtyflag\": [\"0\", \"\"], \"creditid\": [\"0\", \"\"], \"creditflag\": [\"0\", \"\"], \"linkmarket\": [\"0\", \"\"], \"linksecuid\": [\"0\", \"\"], \"sorttype\": [\"0\", \"\"], \"prodcode\": [\"0\", \"\"], \"dzsaletype\": [\"0\", \"0\"], \"zxjt_yq_qty\": [\"410203-证券信息.zxjt_yq_qty\", \"\"], \"stkcodeid\": [\"0\", \"\"], \"maxstkqty\": [\"0\", \"\"], \"maxstkqty_check\": [\"0\", \"\"], \"orderqty_check\": [\"0\", \"\"], \"tc_allowerror\": [\"0\", \"\"], \"tc_result\": [\"0\", \"\"]}, \"410411-买卖委托\": {\"custid\": [\"410410-取最大交易数量.custid\", \"\"], \"custorgid\": [\"410410-取最大交易数量.custorgid\", \"\"], \"trdpwd\": [\"410410-取最大交易数量.trdpwd\", \"\"], \"netaddr\": [\"410410-取最大交易数量.netaddr\", \"\"], \"orgid\": [\"410410-取最大交易数量.orgid\", \"\"], \"operway\": [\"410410-取最大交易数量.operway\", \"\"], \"ext\": [\"410410-取最大交易数量.ext\", \"\"], \"funcid\": [\"0\", \"410411\"], \"market\": [\"410203-证券信息.market\", \"\"], \"secuid\": [\"账户信息查询.sza_secuid\", \"\"], \"fundid\": [\"账户信息查询.fundid\", \"\"], \"stkcode\": [\"410203-证券信息.stkcode\", \"\"], \"bsflag\": [\"410410-取最大交易数量.bsflag\", \"\"], \"price\": [\"410203-证券信息.fixprice\", \"\"], \"qty\": [\"410203-证券信息.zxjt_yq_qty\", \"\"], \"ordergroup\": [\"0\", \"\"], \"bankcode\": [\"0\", \"\"], \"creditid\": [\"0\", \"\"], \"creditflag\": [\"0\", \"\"], \"remark\": [\"0\", \"\"], \"targetseat\": [\"0\", \"\"], \"promiseno\": [\"0\", \"\"], \"risksno\": [\"0\", \"\"], \"autoflag\": [\"0\", \"\"], \"enddate\": [\"0\", \"\"], \"linkman\": [\"0\", \"\"], \"linkway\": [\"0\", \"\"], \"linkmarket\": [\"0\", \"\"], \"linksecuid\": [\"0\", \"\"], \"sorttype\": [\"0\", \"\"], \"mergematchcode\": [\"0\", \"\"], \"mergematchdate\": [\"0\", \"\"], \"oldorderid\": [\"0\", \"\"], \"prodcode\": [\"0\", \"\"], \"pricetype\": [\"0\", \"\"], \"blackflag\": [\"0\", \"\"], \"dzsaletype\": [\"0\", \"\"], \"ordersno\": [\"0\", \"\"], \"orderid\": [\"0\", \"\"], \"ordergroup_out\": [\"0\", \"\"], \"tc_flag\": [\"0\", \"\"], \"tc_checkStr\": [\"0\", \"\"], \"tc_allowerror\": [\"0\", \"\"], \"zxjt_ar_sleep\": [\"0\", \"\"], \"tc_result\": [\"0\", \"\"]}, \"410510-当日委托明细查询\": {\"custid\": [\"410411-买卖委托.custid\", \"\"], \"custorgid\": [\"410411-买卖委托.custorgid\", \"\"], \"trdpwd\": [\"410411-买卖委托.trdpwd\", \"\"], \"netaddr\": [\"410411-买卖委托.netaddr\", \"\"], \"orgid\": [\"410411-买卖委托.orgid\", \"\"], \"operway\": [\"410411-买卖委托.operway\", \"\"], \"ext\": [\"410411-买卖委托.ext\", \"\"], \"funcid\": [\"0\", \"410510\"], \"market\": [\"410203-证券信息.market\", \"\"], \"fundid\": [\"账户信息查询.fundid\", \"\"], \"secuid\": [\"账户信息查询.sza_secuid\", \"\"], \"stkcode\": [\"410203-证券信息.stkcode\", \"\"], \"ordersno\": [\"410411-买卖委托.ordersno\", \"\"], \"ordergroup\": [\"0\", \"200\"], \"bankcode\": [\"0\", \"\"], \"qryflag\": [\"0\", \"1\"], \"count\": [\"0\", \"\"], \"poststr\": [\"0\", \"\"], \"extsno\": [\"0\", \"\"], \"qryoperway\": [\"0\", \"\"], \"zxjt_yq_orderfrzamt\": [\"410203-证券信息.zxjt_yq_cjje\", \"\"], \"zxjt_yq_orderstatus\": [\"0\", \"A\"], \"zxjt_poststr\": [\"0\", \"\"], \"poststr_out\": [\"0\", \"\"], \"orderdate\": [\"0\", \"\"], \"ordersno_out\": [\"0\", \"\"], \"ordergroup_out\": [\"0\", \"\"], \"custid_out\": [\"0\", \"\"], \"custname\": [\"0\", \"\"], \"fundid_out\": [\"0\", \"\"], \"moneytype\": [\"0\", \"\"], \"orgid_out\": [\"0\", \"\"], \"secuid_out\": [\"0\", \"\"], \"bsflag\": [\"0\", \"\"], \"orderid\": [\"0\", \"\"], \"reporttime\": [\"0\", \"\"], \"opertime\": [\"0\", \"\"], \"market_out\": [\"0\", \"\"], \"stkcode_out\": [\"0\", \"\"], \"stkname\": [\"0\", \"\"], \"prodcode\": [\"0\", \"\"], \"prodname\": [\"0\", \"\"], \"orderprice\": [\"0\", \"\"], \"orderqty\": [\"0\", \"\"], \"orderfrzamt\": [\"0\", \"\"], \"matchqty\": [\"0\", \"\"], \"matchamt\": [\"0\", \"\"], \"cancelqty\": [\"0\", \"\"], \"orderstatus\": [\"0\", \"\"], \"seat\": [\"0\", \"\"], \"cancelflag\": [\"0\", \"\"], \"operdate\": [\"0\", \"\"], \"bondintr\": [\"0\", \"\"], \"operway_out\": [\"0\", \"\"], \"remark\": [\"0\", \"\"], \"serverid\": [\"0\", \"\"], \"orderfrzamt_check\": [\"0\", \"\"], \"orderstatus_check\": [\"0\", \"\"], \"poststr_check\": [\"0\", \"\"], \"tc_allowerror\": [\"0\", \"\"], \"tc_result\": [\"0\", \"\"]}, \"410502委托后-资金查询\": {\"custid\": [\"410510-当日委托明细查询.custid\", \"\"], \"custorgid\": [\"410510-当日委托明细查询.custorgid\", \"\"], \"trdpwd\": [\"410510-当日委托明细查询.trdpwd\", \"\"], \"netaddr\": [\"410510-当日委托明细查询.netaddr\", \"\"], \"orgid\": [\"410510-当日委托明细查询.orgid\", \"\"], \"operway\": [\"410510-当日委托明细查询.operway\", \"\"], \"ext\": [\"410510-当日委托明细查询.ext\", \"\"], \"funcid\": [\"0\", \"410502\"], \"fundid\": [\"账户信息查询.fundid\", \"\"], \"moneytype\": [\"0\", \"0\"], \"remark\": [\"0\", \"\"], \"zxjt_trade_type\": [\"0\", \"\"], \"zxjt_pre_fundbal\": [\"410502委托前-资金查询.fundbal\", \"\"], \"zxjt_pre_fundavl\": [\"410502委托前-资金查询.fundavl\", \"\"], \"zxjt_pre_fund\": [\"0\", \"\"], \"zxjt_yq_cjje\": [\"410203-证券信息.zxjt_yq_cjje\", \"\"], \"zxjt_cj_flag\": [\"0\", \"\"], \"custid_out\": [\"0\", \"\"], \"fundid_out\": [\"0\", \"\"], \"orgid_out\": [\"0\", \"\"], \"moneytype_out\": [\"0\", \"\"], \"fundbal\": [\"0\", \"\"], \"fundavl\": [\"0\", \"\"], \"marketvalue\": [\"0\", \"\"], \"fund\": [\"0\", \"\"], \"stkvalue\": [\"0\", \"\"], \"fundseq\": [\"0\", \"\"], \"fundloan\": [\"0\", \"\"], \"fundbuy\": [\"0\", \"\"], \"fundsale\": [\"0\", \"\"], \"fundfrz\": [\"0\", \"\"], \"fundlastbal\": [\"0\", \"\"], \"fundbal_check\": [\"0\", \"\"], \"fundavl_check\": [\"0\", \"\"], \"fund_check\": [\"0\", \"\"], \"tc_allowerror\": [\"0\", \"\"], \"tc_result\": [\"0\", \"\"]}, \"账户解锁\": {\"acct_match\": [\"账户信息查询.case_acct\", \"\"], \"starttime\": [\"账户信息查询.starttime\", \"\"], \"runtime\": [\"0\", \"\"], \"tc_allowerror\": [\"0\", \"\"], \"tc_result\": [\"0\", \"\"]}}}";
        CaseData caseData = new CaseData();
        caseData.initCaseData(a, "市价买入-深-正例-主板-0c-数量1000-00001");
        System.out.println(caseData.getCaseData("账户信息查询", "acct_match"));
    }*/

}
















