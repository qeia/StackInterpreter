package main.com.sid.JavaStackInterpreter;
import java.util.List;

public class StackFunction extends Executor {

    private List<String> initialVariables;

    StackFunction(Executor executor, List<String> initialVariables) {
        super(executor);
        this.initialVariables = initialVariables;
    }

    public Object invoke(Integer... values){

        for(int i = 0; i < values.length; i++){
            this.stack.addVariable(initialVariables.get(i), values[i]);
        }

        return this.invoke();
    }
}
