package main.com.sid.JavaStackInterpreter;

import main.com.sid.JavaStackInterpreter.Internal.OperationsDeque;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StackFunction extends Executor {

    StackFunction(StackContext stack, OperationsDeque operationsDeque, List<String> initialVariables) {
        super(stack, operationsDeque, initialVariables);
    }

    public Object invoke(Integer... values){

        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < values.length; i++){
            map.put(initialVariables.get(i), values[i]);
        }
        this.putValuesIntoMap(map);
        return this.execute();
    }
}
