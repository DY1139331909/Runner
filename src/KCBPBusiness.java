import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

import java.nio.charset.StandardCharsets;
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
        return 0;
    }

    public int disConnect() {
        return 0;
    }
}
