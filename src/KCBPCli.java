import com.sun.jna.*;
import com.sun.jna.ptr.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 程浩
 * @date 2020/4/13 18:27
 */

/*
* Native Type	Java Type
  void **	    PointerByReference
  void*	        Pointer
  char**	    PointerByReference
  char&	        PointerByReference
  char*	        Pointer
  int&	        IntByReference
  int*	        IntByReference
* */
public class KCBPCli {
    public interface CLibrary extends Library {

        CLibrary INSTANCE = Native.load((Platform.isWindows() ? ".\\dll\\kcbp\\KCBPCli" : "c"), CLibrary.class);
        int KCBP_SERVERNAME_MAX = 32;
        int KCBP_DESCRIPTION_MAX = 32;

        public static class tagKCBPConnectOption extends Structure {
            public static class ByReference extends tagKCBPConnectOption implements Structure.ByReference {
            }

            public static class ByValue extends tagKCBPConnectOption implements Structure.ByValue {
            }

            public byte[] szServerName = new byte[KCBP_SERVERNAME_MAX + 1];
            public int nProtocal;
            public byte[] szAddress = new byte[KCBP_DESCRIPTION_MAX + 1];
            public int nPort;
            public byte[] szSendQName = new byte[KCBP_DESCRIPTION_MAX + 1];
            public byte[] szReceiveQName = new byte[KCBP_DESCRIPTION_MAX + 1];
            public byte[] szReserved = new byte[KCBP_DESCRIPTION_MAX + 1];

            @Override
            protected List<String> getFieldOrder() {
                List<String> Field = new ArrayList<String>();
                Field.add("szServerName");
                Field.add("nProtocal");
                Field.add("szAddress");
                Field.add("nPort");
                Field.add("szSendQName");
                Field.add("szReceiveQName");
                Field.add("szReserved");
                return Field;
            }
        }

        int KCBPCLI_Init(PointerByReference KCBPCLIHANDLE);

        int KCBPCLI_GetVersion(Pointer hHandle, IntByReference pnVersion);

        int KCBPCLI_SetConnectOption(Pointer hHandle, tagKCBPConnectOption stKCBPConnection);

        int KCBPCLI_GetConnectOption(Pointer hHandle, tagKCBPConnectOption stKCBPConnection);

        int KCBPCLI_ConnectServer(Pointer hHandle, String ServerName, String UserName, String Password);

        int KCBPCLI_SQLConnect(Pointer hHandle, String ServerName, String UserName, String Password);

        int KCBPCLI_DisConnect(Pointer hHandle);

    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        PointerByReference KCBPCLIHANDLE = new PointerByReference(Pointer.NULL);
        int ret;
        ret = CLibrary.INSTANCE.KCBPCLI_Init(KCBPCLIHANDLE);
        System.out.println("KCBPCLI_Init: " + ret);
        Pointer hHandle = KCBPCLIHANDLE.getValue();
//        IntByReference pnVersion = new IntByReference();
//        ret = CLibrary.INSTANCE.KCBPCLI_GetVersion(hHandle, pnVersion);
//        System.out.println(ret);
//        System.out.println(pnVersion.getValue());
        CLibrary.tagKCBPConnectOption tagKCBPConnectOption = new CLibrary.tagKCBPConnectOption.ByValue();
        tagKCBPConnectOption.write();
        tagKCBPConnectOption.szServerName = "KCBP1".getBytes(StandardCharsets.UTF_8);
        tagKCBPConnectOption.nProtocal = 0;
        tagKCBPConnectOption.szAddress = "7.72.174.32".getBytes(StandardCharsets.UTF_8);
        tagKCBPConnectOption.nPort = 21000;
        tagKCBPConnectOption.szSendQName = "req1".getBytes(StandardCharsets.UTF_8);
        tagKCBPConnectOption.szReceiveQName = "ans1".getBytes(StandardCharsets.UTF_8);
        tagKCBPConnectOption.szReserved = "".getBytes(StandardCharsets.UTF_8);
        ret = CLibrary.INSTANCE.KCBPCLI_SetConnectOption(hHandle, tagKCBPConnectOption);
        CLibrary.tagKCBPConnectOption tagKCBPConnectOptionByReference = new CLibrary.tagKCBPConnectOption();
        ret = CLibrary.INSTANCE.KCBPCLI_GetConnectOption(hHandle, tagKCBPConnectOptionByReference);
//        tagKCBPConnectOptionByReference.read();
        System.out.println("szServerName： " + new String(tagKCBPConnectOptionByReference.szServerName));
        System.out.println("nProtocal： " + tagKCBPConnectOptionByReference.nProtocal);
        System.out.println("szAddress： " + new String(tagKCBPConnectOptionByReference.szAddress));
        System.out.println("nPort： " + tagKCBPConnectOptionByReference.nPort);
        System.out.println("szSendQName： " + new String(tagKCBPConnectOptionByReference.szSendQName));
        System.out.println("szReceiveQName： " + new String(tagKCBPConnectOptionByReference.szReceiveQName));
        System.out.println(ret);
//        开始连接
        String ServerName = "KCBP1";
        String UserName = "KCXP00";
        String Password = "888888";

        ret = CLibrary.INSTANCE.KCBPCLI_ConnectServer(hHandle, ServerName, UserName, Password);
        System.out.println("KCBPCLI_ConnectServer: " + ret);
        ret = CLibrary.INSTANCE.KCBPCLI_DisConnect(hHandle);
        System.out.println("KCBPCLI_DisConnect: " + ret);

    }
}
