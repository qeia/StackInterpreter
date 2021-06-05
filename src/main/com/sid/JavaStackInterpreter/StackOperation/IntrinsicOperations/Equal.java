package main.com.sid.JavaStackInterpreter.StackOperation.IntrinsicOperations;

import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;

import java.util.Objects;

public class Equal extends IntrinsicOperation {

    @Override
    public void execute(StackContext st) {
        if(st.size() < 2){
            throw new InvalidStackStateException("Stack size is less than 2");
        }
        Object pm1 = st.pop();
        Object pm2 = st.pop();
        st.push(Objects.equals(pm1,pm2));
    }
    public String toString(){
        return "Equal";
    }
}
