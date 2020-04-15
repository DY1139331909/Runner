import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;

/**
 * @author 程浩
 * @date 2020/4/13 18:27
 */


public class Kcbp {
    public interface CLibrary extends Library {
        CLibrary INSTANCE = Native.load((Platform.isWindows() ? "C:\\Users\\chenghao3\\IdeaProjects\\Runner\\dll\\kcbp\\KCBPCli.dll" : "c"), CLibrary.class);
        int KCBPCLI_Init(Pointer pointer);
    }

    public static void main(String[] args) {
        Pointer pointer;
//        CLibrary.INSTANCE.KCBPCLI_Init();
    }


}
