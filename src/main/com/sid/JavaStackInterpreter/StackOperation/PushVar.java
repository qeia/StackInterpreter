package main.com.sid.JavaStackInterpreter.StackOperation;

import main.com.sid.JavaStackInterpreter.Internal.StackContext;

import java.util.List;
public class PushVar extends StackOperation {
    private List<String> variables;

    public PushVar(List<String> variables){
        this.variables = variables;
    }

    public void execute(StackContext st){
        for (String pm : variables)
            st.push(st.findVariableValue(pm));

    }
    public String toString(){
        return "Push";
    }
}