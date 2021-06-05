package main.com.sid.JavaStackInterpreter.StackOperation.IntrinsicOperations;

import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;
import main.com.sid.JavaStackInterpreter.Exceptions.InvalidTypeException;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;
import main.com.sid.JavaStackInterpreter.StackFunction;

public class StackFunctionOperation extends IntrinsicOperation{

    private StackFunction stackFunction;
    public StackFunctionOperation(StackFunction stackFunction){
        this.stackFunction = stackFunction;
    }

    @Override
    public void execute(StackContext st) {

        int size = stackFunction.getVariableLength();

        Integer[] parametersInStackFunction = new Integer[size];
        for (int i = 0; i < size; i ++){

            if (st.isEmpty()){
                throw new InvalidStackStateException("Invalid stack size");
            }
            Object x = st.pop();

            if (!(x instanceof Integer)){
                throw new InvalidTypeException("Can not convert " + x + "to int");
            }
            parametersInStackFunction[size - i - 1] = (int) x;
        }

        st.push(stackFunction.invoke(parametersInStackFunction));
    }
}
