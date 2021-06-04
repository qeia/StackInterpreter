package main.com.sid.JavaStackInterpreter.Internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//contains the variable context and stack values
public class StackContext extends Stack<Object> {
    private VariableContext variableContext;

    public StackContext (VariableContext variableContext){
        this.variableContext = variableContext;

    }
    public Integer findVariableValue (String val){
        return variableContext.findVariableValue(val);
    }
    public Map<String, Integer> getLastMap(){
        return variableContext.context.get(variableContext.context.size()-1);
    }

    public void putVariableInMap (String var, Integer value){
        getLastMap().put(var, value);
    }
    public void removeLastMap(){
        variableContext.context.remove(variableContext.context.size()-1);
    }

    public String toString() {
        return "[ SC " + super.toString() + " " + variableContext.toString() + " ]";
    }

    public VariableContext getVariableContext() {
        return variableContext;
    }

    public void addNewLevel() {
        variableContext.context.add(new HashMap<>());
    }
}
