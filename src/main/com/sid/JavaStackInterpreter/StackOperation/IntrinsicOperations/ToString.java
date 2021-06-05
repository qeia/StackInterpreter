package main.com.sid.JavaStackInterpreter.StackOperation.IntrinsicOperations;

import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;

import java.util.List;

public class ToString implements IntrinsicOperation {
    @Override
    public void execute(StackContext st, List params) {
        if(st.size() < 1){
            throw new InvalidStackStateException("Stack size is 0");
        }
        st.push(st.pop().toString());
    }
}
