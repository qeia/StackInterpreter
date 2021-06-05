package main.com.sid.JavaStackInterpreter;

import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;

import java.util.List;

public class StackFunction extends Executor {

    private List<String> initialVariables;

    StackFunction(Executor executor, List<String> initialVariables) {
        super(executor);
        this.initialVariables = initialVariables;
    }

    public Object invoke(Integer... values){
        if (values.length != initialVariables.size()) {
            throw new InvalidStackStateException("Invalid number of arguments given");
        }
        //we clear stack because it might have been full from previous
        this.stack.clear();
        for(int i = 0; i < values.length; i++){
            this.stack.addVariable(initialVariables.get(i), values[i]);
        }

        return this.invoke();
    }

    public int getVariableLength() { return initialVariables.size();}
}
