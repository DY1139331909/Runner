import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Structure;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 程浩
 * @date 2020/4/22 21:56
 */
public class DllTest {

    public interface Dll extends Library {
        Dll INSTANCE = Native.load((Platform.isWindows() ? "DLL" : "c"), Dll.class);
        int KCBP_SERVERNAME_MAX = 32;
        int KCBP_DESCRIPTION_MAX = 32;

        public static class tagKCBPConnectOption extends Structure {
            public static class ByReference extends DllTest.Dll.tagKCBPConnectOption implements Structure.ByReference {
            }

            public static class ByValue extends DllTest.Dll.tagKCBPConnectOption implements Structure.ByValue {
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

        int test_sum(int a, int b);

        void KCBPCLI_SetConnectOption(DllTest.Dll.tagKCBPConnectOption.ByValue stKCBPConnection);

        void KCBPCLI_ConnectServer(String ServerName, String UserName, String Password);


        public static void main(String[] args) {
            DllTest.Dll.tagKCBPConnectOption.ByValue tagKCBPConnectOption = new DllTest.Dll.tagKCBPConnectOption.ByValue();
            tagKCBPConnectOption.write();
            tagKCBPConnectOption.szServerName = "KCBP1".getBytes(StandardCharsets.UTF_8);
            tagKCBPConnectOption.nProtocal = 0;
            tagKCBPConnectOption.szAddress = "10.1.160.167".getBytes(StandardCharsets.UTF_8);
            tagKCBPConnectOption.nPort = 21002;
            tagKCBPConnectOption.szSendQName = "req1".getBytes(StandardCharsets.UTF_8);
            tagKCBPConnectOption.szReceiveQName = "ans1".getBytes(StandardCharsets.UTF_8);
            tagKCBPConnectOption.szReserved = "".getBytes(StandardCharsets.UTF_8);
            DllTest.Dll.INSTANCE.KCBPCLI_SetConnectOption(tagKCBPConnectOption);
            DllTest.Dll.INSTANCE.test_sum(1,2);
            DllTest.Dll.INSTANCE.KCBPCLI_ConnectServer("KCBP1","KCXP00","888888");
        }
    }
}
