/**
 * @author 程浩
 * @date 2020/6/16 16:04
 */

import bsh.EvalError;
import bsh.Interpreter;

import java.io.IOException;


public class BshRunner {
    public static void main(String[] args) throws IOException, EvalError {
        System.out.println("start run");
        runBsh("src/test.bsh");
    }

    public static int runBsh(String source) throws IOException, EvalError {
        Interpreter i = new Interpreter();  // Construct an interpreter
        i.source(source);
        i.run();
        return 0;
    }
}

