package main.com.sid.JavaStackInterpreter.Internal;

import main.com.sid.JavaStackInterpreter.StackOperation.StackOperation;

import java.util.List;

public class OperationParameters {
    public StackOperation operation;
    public List params;

    public OperationParameters (StackOperation operation, List params){
        this.operation = operation;
        this.params = params;
    }
    @Override
    public String toString(){
        String paramString = params == null ?  "null" : params.toString();
        return "[OP " + operation.toString() + " " + paramString + "]";
    }
}
