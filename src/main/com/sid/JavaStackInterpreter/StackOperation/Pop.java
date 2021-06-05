package main.com.sid.JavaStackInterpreter.StackOperation;

import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;


public class Pop extends StackOperation {

    @Override
    public void execute(StackContext st) {
        if(st.size() < 1){
            throw new InvalidStackStateException("Stack size is 0");
        }
        st.pop();
    }
    public String toString(){
        return "Pop";
    }
}
