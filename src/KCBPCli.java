import com.sun.jna.*;
import com.sun.jna.ptr.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


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

    public interface KCBP extends Library {

        KCBP INSTANCE = Native.load((Platform.isWindows() ? ".\\dll\\kcbp\\KCBPCli" : "c"), KCBP.class);
        int KCBP_SERVERNAME_MAX = 32;
        int KCBP_DESCRIPTION_MAX = 32;

        @Structure.FieldOrder(value = {"szServerName", "nProtocal", "szAddress", "nPort", "szSendQName", "szReceiveQName", "szReserved"})
        public static class tagKCBPConnectOption extends Structure {
            public tagKCBPConnectOption() {

                setAlignType(Structure.ALIGN_NONE);

            }

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
        }

        int KCBPCLI_Init(PointerByReference KCBPCLIHANDLE);

        int KCBPCLI_GetVersion(Pointer hHandle, IntByReference pnVersion);

        int KCBPCLI_SetConnectOption(Pointer hHandle, tagKCBPConnectOption.ByValue stKCBPConnection);

        int KCBPCLI_SetOption(Pointer hHandle, int nIndex, Pointer pValue);

        int KCBPCLI_GetConnectOption(Pointer hHandle, tagKCBPConnectOption stKCBPConnection);

        int KCBPCLI_ConnectServer(Pointer hHandle, String ServerName, String UserName, String Password);

        int KCBPCLI_SQLConnect(Pointer hHandle, String szServerName, String UserName, String Password);

        int KCBPCLI_DisConnect(Pointer hHandle);

        int KCBPCLI_BeginWrite(Pointer hHandle);


    }
}
