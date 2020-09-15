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
    public Interpreter i = new Interpreter(); // Construct an interpreter
    public void setData() throws EvalError {
        this.i.set("caseData", this.data);
    }
    public BshRunner(CaseData data) throws EvalError {
        this.data = data;
        setData();
    }

    public int runBsh(String source) throws IOException, EvalError {
        i.source(source);
        return 0;
    }
}

