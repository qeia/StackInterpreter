package main.com.sid.JavaStackInterpreter.StackOperation;

import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;
import main.com.sid.JavaStackInterpreter.Executor;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;

import java.util.List;

public class IfTrue implements StackOperation {

    @Override
    public void executeOnStack(StackContext st, List<Object> params){
        if(st.size() < 1){
            throw new InvalidStackStateException("Stack size is 0");
        }
        if(!(st.peek() instanceof Boolean)){
            throw new InvalidStackStateException("Last value in stack is not a boolean");
        }
        if ((boolean)st.pop()){
            st.push(getLastElement(Executor.recursivelyExecuteOnParameters(params.subList(0,1), st)));
            return;
        }
        st.push(getLastElement(Executor.recursivelyExecuteOnParameters(params.subList(1,2), st)));

    }

    private Object getLastElement(List<Object> list){
        return list.get(list.size()-1);
    }
    @Override
    public void execute(StackContext st, List params) {
    }
}
