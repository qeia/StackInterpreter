package main.com.sid.JavaStackInterpreter.StackOperation;

import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;

import java.util.List;

public class Local extends StackOperation {

    private String var;
    public Local (String var){
        this.var = var;
    }
    @Override
    public void execute(StackContext st) {
        st.addVariable(var, (int) st.peek());
    }
    public String toString(){
        return "Local";
    }
}
