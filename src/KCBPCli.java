import com.sun.jna.*;
import com.sun.jna.ptr.*;

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
        CLibrary INSTANCE = Native.load((Platform.isWindows() ? ".\\dll\\kcbp\\KCBPCli.dll" : "c"), CLibrary.class);

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

        int KCBPCLI_SetConnectOption(Pointer hHandle, tagKCBPConnectOption.ByValue stKCBPConnection);

        int KCBPCLI_GetConnectOption(Pointer hHandle, tagKCBPConnectOption.ByReference stKCBPConnection);

        int KCBPCLI_ConnectServer(Pointer hHandle, Pointer ServerName, Pointer UserName, Pointer Password);

    }

    public static void main(String[] args) {
        PointerByReference KCBPCLIHANDLE = new PointerByReference(Pointer.NULL);
        int ret = CLibrary.INSTANCE.KCBPCLI_Init(KCBPCLIHANDLE);
        System.out.println(ret);
        IntByReference pnVersion = new IntByReference();
        pnVersion.setValue(0);
        ret = CLibrary.INSTANCE.KCBPCLI_GetVersion(KCBPCLIHANDLE.getValue(), pnVersion);
        System.out.println(ret);
        System.out.println(pnVersion.getValue());
        CLibrary.tagKCBPConnectOption.ByValue tagKCBPConnectOption = new CLibrary.tagKCBPConnectOption.ByValue();
        tagKCBPConnectOption.szServerName = "KCBP1".getBytes();
        tagKCBPConnectOption.nProtocal = 0;
        tagKCBPConnectOption.szAddress = "7.72.174.32".getBytes();
        tagKCBPConnectOption.nPort = 21000;
        tagKCBPConnectOption.szSendQName = "req1".getBytes();
        tagKCBPConnectOption.szReceiveQName = "ans1".getBytes();
//        tagKCBPConnectOption.szReserved = "".getBytes();
        ret = CLibrary.INSTANCE.KCBPCLI_SetConnectOption(KCBPCLIHANDLE.getValue(), tagKCBPConnectOption);
        CLibrary.tagKCBPConnectOption.ByReference tagKCBPConnectOptionByReference = new CLibrary.tagKCBPConnectOption.ByReference();
        CLibrary.INSTANCE.KCBPCLI_GetConnectOption(KCBPCLIHANDLE.getValue(), tagKCBPConnectOptionByReference);
        System.out.println(ret);
        System.out.println(tagKCBPConnectOptionByReference.nPort);
        System.out.println(tagKCBPConnectOptionByReference.szServerName);
        System.out.println(tagKCBPConnectOptionByReference.szAddress);
//        开始连接
        Pointer ServerName = new Memory(32);
        ServerName.clear(32);
        ServerName.setString(0, "KCBP1");
        Pointer UserName = new Memory(32);
        ServerName.clear(32);
        ServerName.setString(0, "KCXP00");
        Pointer Password = new Memory(32);
        ServerName.clear(32);
        ServerName.setString(0, "888888");
        ret = CLibrary.INSTANCE.KCBPCLI_ConnectServer(KCBPCLIHANDLE.getValue(), ServerName, UserName, Password);
        System.out.println(ret);


    }
}
