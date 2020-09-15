import kcbp.KCBPBusiness;

import java.util.HashMap;



/**
 * Created by 程浩 on 2020/4/26
 */
public class KCBPBusinessTest {


    public static void main(String[] args) {
        HashMap<String, String> para = new HashMap<String, String>();
        para.put("szServerName", "KCBP1");
        para.put("nProtocal", "0");
        para.put("szAddress", "10.1.160.167");
        para.put("nPort", "21002");
        para.put("szSendQName", "req1");
        para.put("szReceiveQName", "ans1");
//        para.put("szReserved","");
        para.put("ServerName", "KCBP1");
        para.put("UserName", "KCXP00");
        para.put("Password", "888888");
        KCBPBusiness kcbpBusiness = new KCBPBusiness();
//        int ret = kcbpBusiness.connect(para);
//        System.out.println(ret);
//        HashMap<String, String> para = new HashMap<>();
        para.put("custid", "110600000412");
        para.put("custorgid", "1109");
        para.put("trdpwd", "123321");
        para.put("netaddr", "127.0.0.1");
        para.put("orgid", "1109");
        para.put("operway", "7");
        para.put("ext", "0");
        para.put("funcid", "410203");
        para.put("market", "0");
        para.put("stklevel", "N");
        para.put("stkcode", "000001");
        para.put("poststr", "");
        para.put("rowcount", "");
        para.put("stktype", "0");
//        ret = kcbpBusiness.business(para);
//        System.out.println("kcbpBusiness.business: " + ret);
        kcbpBusiness.trade(para);
        
        
    }
}
