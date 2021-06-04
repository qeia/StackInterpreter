package Internal;

import java.util.Map;
import java.util.Stack;

public class StackContext extends Stack<Object> {
    public VariableContext variableContext;

    public StackContext (VariableContext variableContext){
        this.variableContext = variableContext;

    }
    public Integer findVariableValue (String val){
        return variableContext.findVariableValue(val);
    }
    public Map<String, Integer> getLastMap(){
        return variableContext.context.get(variableContext.context.size()-1);
    }

    public void removeLastMap(){
        variableContext.context.remove(variableContext.context.size()-1);
    }
    public String toString() {
        return "[ SC " + super.toString() + " " + variableContext.toString() + " ]";
    }
}
