package main.com.sid.JavaStackInterpreter.Internal;


import java.util.Stack;

//contains the variable context and stack values
public class StackContext extends Stack<Object> {

    private VariableTracker variableTracker;

    public StackContext (VariableTracker variableTracker){
        this.variableTracker = variableTracker;
    }

    public StackContext (StackContext stackContext){
        this.variableTracker = stackContext.variableTracker;
    }

    public Integer findVariableValue (String variable) {
        return variableTracker.findVariableValue(variable);
    }

    public void addNewLevel () {
        variableTracker.addNewLevel();
    }

    public void addVariable (String var, Integer value) {
        variableTracker.addVariable(var, value);
    }

    public void removeCurrentLevel () {
        variableTracker.removeCurrentLevel();
    }

    public void clear(){
        super.clear(); variableTracker.clear();
        //addNewLevel because we cleared stack
        this.addNewLevel();
    }

    public String toString() {
        return "[ SC " + super.toString() + " variableTracker: " + variableTracker.toString() + " ]";
    }


}
