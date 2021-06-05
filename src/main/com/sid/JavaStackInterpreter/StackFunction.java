package main.com.sid.JavaStackInterpreter;
import main.com.sid.JavaStackInterpreter.Internal.OperationsDeque;

import java.util.List;

public class StackFunction extends Executor {

    private List<String> initialVariables;

    //we want invoke(..) to be called multiple times, so we can't lose operationsDeque
    //hence we clone it
    private OperationsDeque odBackup;

    StackFunction(Executor executor, List<String> initialVariables) {
        super(executor);
        this.initialVariables = initialVariables;
        odBackup = operationsDeque.clone();
    }

    public Object invoke(Integer... values){
        //we clear stack because it might have been full from previous
        this.stack.clear();
        //addNewLevel because we cleared stack
        this.stack.addNewLevel();

        if (operationsDeque.isEmpty()){
            operationsDeque = odBackup.clone();
        }
        for(int i = 0; i < values.length; i++){
            this.stack.addVariable(initialVariables.get(i), values[i]);
        }

        return this.invoke();
    }

    public int getVariableLength() { return initialVariables.size();}
}
