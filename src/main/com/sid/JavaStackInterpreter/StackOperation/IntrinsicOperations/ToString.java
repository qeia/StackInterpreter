package main.com.sid.JavaStackInterpreter.StackOperation.IntrinsicOperations;

import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;


public class ToString extends IntrinsicOperation {
    @Override
    public void execute(StackContext st) {
        if(st.size() < 1){
            throw new InvalidStackStateException("Stack size is 0");
        }
        st.push(st.pop().toString());
    }
}
