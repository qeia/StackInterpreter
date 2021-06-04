package StackOperation.IntrinsicOperations;

import Exceptions.InvalidStackStateException;
import Internal.StackContext;
import StackOperation.StackOperation;

import java.util.List;

public class IfTrue implements StackOperation {

    @Override
    public void execute(StackContext st, List params) {
        if(st.size() < 1){
            throw new InvalidStackStateException("Stack size is 0");
        }
        if(!(st.peek() instanceof Boolean)){
            throw new InvalidStackStateException("Last value in stack is not a boolean");
        }
        if ((boolean)st.pop()){
            st.push(params.get(0));
            return;
        }
        st.push(params.get(1));
    }
}
