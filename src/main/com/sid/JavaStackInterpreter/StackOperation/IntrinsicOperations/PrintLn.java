package main.com.sid.JavaStackInterpreter.StackOperation.IntrinsicOperations;

import main.com.sid.JavaStackInterpreter.Internal.StackContext;


public class PrintLn extends IntrinsicOperation {
    @Override
    public void execute(StackContext st) {
        System.out.println(st.peek());
    }

    public String toString(){
        return "PrintLN";
    }
}
