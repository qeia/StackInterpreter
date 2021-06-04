package Internal;

import StackOperation.StackOperation;

import java.util.List;

public class OperationParameters {
    public StackOperation op;
    public List params;

    public OperationParameters (StackOperation op, List params){
        this.op = op;
        this.params = params;
    }
    @Override
    public String toString(){
        String paramString = params == null ?  "null" : params.toString();
        return "[OP " + op.toString() + " " + paramString + "]";
    }
}
