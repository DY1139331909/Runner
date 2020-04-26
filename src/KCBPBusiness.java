import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import javax.lang.model.util.ElementScanner6;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 程浩 on 2020/4/26
 */
public class KCBPBusiness {
    int ret;
    PointerByReference KCBPCLIHANDLE = new PointerByReference(Pointer.NULL);
    static Pointer hHandle = null;

    public KCBPBusiness() {
    }

    public int connect(Map connectParameter) {
        ret = KCBPCli.KCBP.INSTANCE.KCBPCLI_Init(KCBPCLIHANDLE);
        hHandle = KCBPCLIHANDLE.getValue();
        if (ret == 0) {
//            初始化成功
            KCBPCli.KCBP.tagKCBPConnectOption.ByValue tagKCBPConnectOption = new KCBPCli.KCBP.tagKCBPConnectOption.ByValue();
            tagKCBPConnectOption.write();
            tagKCBPConnectOption.szServerName = connectParameter.get("szServerName").toString().getBytes(StandardCharsets.UTF_8);
            tagKCBPConnectOption.nProtocal = Integer.parseInt((String) connectParameter.get("nProtocal"));
            tagKCBPConnectOption.szAddress = connectParameter.get("szAddress").toString().getBytes(StandardCharsets.UTF_8);
            tagKCBPConnectOption.nPort = Integer.parseInt((String) connectParameter.get("nPort"));
            tagKCBPConnectOption.szSendQName = connectParameter.get("szSendQName").toString().getBytes(StandardCharsets.UTF_8);
            tagKCBPConnectOption.szReceiveQName = connectParameter.get("szReceiveQName").toString().getBytes(StandardCharsets.UTF_8);
//            tagKCBPConnectOption.szReserved = connectParameter.get("szReserved").toString().getBytes(StandardCharsets.UTF_8);
            ret = KCBPCli.KCBP.INSTANCE.KCBPCLI_SetConnectOption(hHandle, tagKCBPConnectOption);
            if (ret == 0) {
                ret = KCBPCli.KCBP.INSTANCE.KCBPCLI_ConnectServer(hHandle, connectParameter.get("ServerName").toString(), connectParameter.get("UserName").toString(), connectParameter.get("Password").toString());
            }
        }
        return ret;
    }

    public int business(Map businessParameter) {
        ret = KCBPCli.KCBP.INSTANCE.KCBPCLI_BeginWrite(hHandle);
        if (ret == 0) {
            Iterator<Map.Entry<String, String>> iterator = businessParameter.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                ret = KCBPCli.KCBP.INSTANCE.KCBPCLI_SetValue(hHandle, key, value);
                if (ret != 0) {
                    return ret;
                }
            }
            ret = KCBPCli.KCBP.INSTANCE.KCBPCLI_CallProgramAndCommit(hHandle, businessParameter.get("funcid").toString());
            if (ret == 0) {
//                调用成功
                KCBPCli.KCBP.INSTANCE.KCBPCLI_RsOpen(hHandle);
                if (ret == 0) {
//                    打开结果集成功
                    Pointer pszInfo = new Memory(1024);
                    ret = KCBPCli.KCBP.INSTANCE.KCBPCLI_RsGetColNames(hHandle, pszInfo, 1024);
                    String colNames = pszInfo.getString(0);
                    System.out.println(colNames);
                    String[] colNamesArr = colNames.split(",");
                    ret = KCBPCli.KCBP.INSTANCE.KCBPCLI_RsFetchRow(hHandle);
                    for (int i = 1; i <= colNamesArr.length; i++) {
                        Pointer vlu = new Memory(256);
                        ret = KCBPCli.KCBP.INSTANCE.KCBPCLI_RsGetCol(hHandle, i, vlu);
                        String vluStr = vlu.getString(0, "gbk");
                        System.out.println(vluStr);
                    }


                }
            }
        }

        return ret;
    }

    public int disConnect() {
        return 0;
    }
}