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
    public static List<Object> recursivelyExecuteOnParameters(List<Object> params, StackContext st) {
        List<Object> primitiveParams = new ArrayList<>();
        if (params != null) {
            for (Object param : params) {
                if (param instanceof OperationsList) {
                    //recursion in case we get a codeblock rather than a primitive
                    Object primitiveParam = new Executor(new StackContext(st),
                            (OperationsList)param).invoke();
                    primitiveParams.add(primitiveParam);
                } else  {
                    primitiveParams.add(param);
                }
            }
        }
        return primitiveParams;
    }
}