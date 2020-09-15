import java.util.HashMap;

/**
 * Created by 程浩 on 2020/4/26
 */
public class KCBPBusinessTest {


    public static void main(String[] args) {
        HashMap<String, String> connectPar = new HashMap<String, String>();
        connectPar.put("szServerName", "KCBP1");
        connectPar.put("nProtocal", "0");
        connectPar.put("szAddress", "10.1.160.167");
        connectPar.put("nPort", "21002");
        connectPar.put("szSendQName", "req1");
        connectPar.put("szReceiveQName", "ans1");
//        connectPar.put("szReserved","");
        connectPar.put("ServerName", "KCBP1");
        connectPar.put("UserName", "KCXP00");
        connectPar.put("Password", "888888");
        KCBPBusiness kcbpBusiness = new KCBPBusiness();
        int ret = kcbpBusiness.connect(connectPar);
        System.out.println(ret);
        HashMap<String, String> businessParameter = new HashMap<>();
        businessParameter.put("custid", "110600000412");
        businessParameter.put("custorgid", "1109");
        businessParameter.put("trdpwd", "123321");
        businessParameter.put("netaddr", "127.0.0.1");
        businessParameter.put("orgid", "1109");
        businessParameter.put("operway", "7");
        businessParameter.put("ext", "0");
        businessParameter.put("funcid", "410203");
        businessParameter.put("market", "0");
        businessParameter.put("stklevel", "N");
        businessParameter.put("stkcode", "000001");
        businessParameter.put("poststr", "");
        businessParameter.put("rowcount", "");
        businessParameter.put("stktype", "0");
        ret = kcbpBusiness.business(businessParameter);
        System.out.println("kcbpBusiness.business: " + ret);
    }
}
