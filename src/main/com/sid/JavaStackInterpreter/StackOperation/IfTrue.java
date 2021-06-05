package main.com.sid.JavaStackInterpreter.StackOperation;


import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;
import main.com.sid.JavaStackInterpreter.Internal.OperationsList;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;

import java.util.Collections;
import java.util.List;

public class IfTrue extends StackOperation {

    private OperationsList whenTrue;
    private OperationsList whenFalse;

    public IfTrue(OperationsList whenTrue, OperationsList whenFalse){
        this.whenFalse = whenFalse;
        this.whenTrue = whenTrue;
    }



    private Object getLastElement(List<Object> list){
        return list.get(list.size()-1);
    }

    @Override
    public void execute(StackContext st) {
        if(st.size() < 1){
            throw new InvalidStackStateException("Stack size is 0");
        }
        if(!(st.peek() instanceof Boolean)){
            throw new InvalidStackStateException("Last value in stack is not a boolean");
        }
        if ((boolean)st.pop()){
            st.push(getLastElement(recursivelyExecuteOnParameters(Collections.singletonList(whenTrue), st)));
            return;
        }
        st.push(getLastElement(recursivelyExecuteOnParameters(Collections.singletonList(whenFalse), st)));
    }
}
