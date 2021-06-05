package main.com.sid.JavaStackInterpreter.StackOperation.IntrinsicOperations;

import main.com.sid.JavaStackInterpreter.Internal.StackContext;

import java.util.List;

public class PrintLn implements IntrinsicOperation {
    @Override
    public void execute(StackContext st, List params) {
        System.out.println(st.peek());
    }

    public String toString(){
        return "PrintLN";
    }
}
