package main.com.sid.JavaStackInterpreter.StackOperation.IntrinsicOperations;

import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;
import main.com.sid.JavaStackInterpreter.Exceptions.InvalidTypeException;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;
import main.com.sid.JavaStackInterpreter.StackFunction;

import java.util.List;

public class StackFunctionOperation implements IntrinsicOperation{

    @Override
    public void execute(StackContext st, List params) {

        StackFunction stackFunction = (StackFunction) params.get(0);
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
