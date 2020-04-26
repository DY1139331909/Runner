import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;

/**
 * Created by 程浩 on 2020/4/26
 */
public class KCBPBusinessTest {


    public static void main(String[] args) {
        HashMap<String,String> connectPar = new HashMap<String, String>();
        connectPar.put("szServerName","KCBP1");
        connectPar.put("nProtocal","0");
        connectPar.put("szAddress","7.72.174.32");
        connectPar.put("nPort","21000");
        connectPar.put("szSendQName","req1");
        connectPar.put("szReceiveQName","ans1");
//        connectPar.put("szReserved","");
        connectPar.put("ServerName","KCBP1");
        connectPar.put("UserName","KCXP00");
        connectPar.put("Password","888888");
        KCBPBusiness kcbpBusiness = new KCBPBusiness();
        int ret = kcbpBusiness.connect(connectPar);
        System.out.println(ret);
    }
}
