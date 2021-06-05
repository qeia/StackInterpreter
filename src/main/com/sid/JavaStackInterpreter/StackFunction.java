package main.com.sid.JavaStackInterpreter;
import main.com.sid.JavaStackInterpreter.Internal.OperationsList;

import java.util.List;

public class StackFunction extends Executor {

    private List<String> initialVariables;


    StackFunction(Executor executor, List<String> initialVariables) {
        super(executor);
        this.initialVariables = initialVariables;
    }

    public Object invoke(Integer... values){
        //we clear stack because it might have been full from previous
        this.stack.clear();
        //addNewLevel because we cleared stack
        this.stack.addNewLevel();

        for(int i = 0; i < values.length; i++){
            this.stack.addVariable(initialVariables.get(i), values[i]);
        }

        return this.invoke();
    }

    public int getVariableLength() { return initialVariables.size();}
}
