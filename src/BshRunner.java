/**
 * @author 程浩
 * @date 2020/6/16 16:04
 */

import bsh.EvalError;
import bsh.Interpreter;
import tools.CaseData;

import javax.xml.crypto.Data;
import java.io.IOException;


public class BshRunner {
    public CaseData data;
    private Interpreter i = new Interpreter(); // Construct an interpreter
    public BshRunner(CaseData data) {
        this.data = data;
    }

    public int runBsh(String source) throws IOException, EvalError {
        i.set("caseData", this.data);
        i.source(source);
        return 0;
    }
}

