package main.com.sid.JavaStackInterpreter.StackOperation;


import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;
import main.com.sid.JavaStackInterpreter.Internal.OperationsList;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;



public class IfTrue extends StackOperation {

    private OperationsList whenTrue;
    private OperationsList whenFalse;

    public IfTrue(OperationsList whenTrue, OperationsList whenFalse){
        this.whenFalse = whenFalse;
        this.whenTrue = whenTrue;
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
            st.push(invokeOnce(st, whenTrue));

        } else {
            st.push(invokeOnce(st, whenFalse));
        }
    }
}
