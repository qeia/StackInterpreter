package main.com.sid.JavaStackInterpreter.StackOperation;

import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;

import java.util.List;

public class Local implements StackOperation {

    @Override
    public void execute(StackContext st, List params) {
        if (params.size()>1 || (!(params.get(0) instanceof String))){
            throw new InvalidStackStateException("Invalid use of local");
        }
        st.addVariable((String)params.get(0), (int) st.peek());
    }
    public String toString(){
        return "Local";
    }
}
