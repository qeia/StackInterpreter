package StackOperation;

import Internal.StackContext;

import java.util.List;
public class PushVar implements StackOperation {
    public void execute(StackContext op, List parameters){

        for (Object pm : parameters)
            op.push(op.findVariableValue((String)pm));

    }
    public String toString(){
        return "Push";
    }
}