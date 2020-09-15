package kcbp;

import com.sun.jna.*;
import com.sun.jna.ptr.*;


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

        KCBP INSTANCE = Native.load((Platform.isWindows() ? ".\\dll\\kcbp\\KCBPCli.dll" : "c"), KCBP.class);
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

        int KCBPCLI_SetValue(Pointer hHandle, String KeyName, String Vlu);

        int KCBPCLI_CallProgramAndCommit(Pointer hHandle, String ProgramName);

        int KCBPCLI_RsOpen(Pointer hHandle);

        int KCBPCLI_RsGetColNames(Pointer hHandle, Pointer pszInfo, int nLen);

        int KCBPCLI_RsGetCol(Pointer hHandle, int col, Pointer Vlu);

        int KCBPCLI_RsFetchRow(Pointer hHandle);

        int KCBPCLI_RsGetColByName(Pointer hHandle, String KeyName, Pointer Vlu);

        int KCBPCLI_RsMore(Pointer hHandle);


    }
}
