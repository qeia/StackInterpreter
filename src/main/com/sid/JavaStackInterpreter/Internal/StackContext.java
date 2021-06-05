package main.com.sid.JavaStackInterpreter.Internal;

import main.com.sid.JavaStackInterpreter.Exceptions.VariableNotFoundException;


import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

//contains the variable context and stack values
public class StackContext extends Stack<Object> {

    private VariableContext variableContext;

    public StackContext (VariableContext variableContext){
        this.variableContext = variableContext;
    }

    public StackContext (StackContext stackContext){
        this.variableContext = stackContext.variableContext;
    }

    public Integer findVariableValue (String variable) {
        if (variableContext.context.containsKey(variable)) {
            return variableContext.context.get(variable).peek();
        }
        throw new VariableNotFoundException(variable);
    }

    public void addNewLevel () {
        variableContext.variableStack.push(new HashSet<>());
    }


    public void addVariable (String var, Integer value) {

        variableContext.variableStack.peek().add(var);
        Stack<Integer> st = variableContext.context.get(var);
        //if adding var for the first time
        if (st == null){
            Stack<Integer> stack = new Stack<>();
            stack.push(value);
            variableContext.context.put(var, stack);
        } else {
            st.push(value);
        }
    }

    //remove all those variables from current level
    //pop the current level of variables also
    public void removeCurrentLevel () {
        for (String variable : variableContext.variableStack.peek()){
            deleteFromStack(variable, variableContext.context);
        }
        variableContext.variableStack.pop();
    }

    private void deleteFromStack (String variable, Map<String, Stack<Integer>> map) {
        Stack<Integer> currentStack = map.get(variable);
        currentStack.pop();
        //removing the key itself so that it can't be queried later
        if (currentStack.isEmpty()){
            map.remove(variable);
        }
    }


    public String toString() {
        return "[ SC " + super.toString() + " variableContext: " + variableContext.toString() + " ]";
    }


}
