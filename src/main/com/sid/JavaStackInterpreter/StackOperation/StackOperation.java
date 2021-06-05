package main.com.sid.JavaStackInterpreter.StackOperation;

import main.com.sid.JavaStackInterpreter.Executor;
import main.com.sid.JavaStackInterpreter.Internal.OperationsList;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;

import java.util.ArrayList;
import java.util.List;

public abstract class StackOperation {


  public abstract void execute(StackContext st);

    //each param in params might be another codeblock
    //try to execute these codeblock and get the primitive
    List<Object> recursivelyExecuteOnParameters(List<Object> params, StackContext st) {
        List<Object> primitiveParams = new ArrayList<>();
        if (params != null) {
            for (Object param : params) {
                primitiveParams.add(invokeOnce(st, param));
            }
        }
        return primitiveParams;
    }

    Object invokeOnce(StackContext st, Object param){
        if (param instanceof OperationsList){
            return new Executor(new StackContext(st), (OperationsList)param).invoke();
        }
        return param;
    }
}