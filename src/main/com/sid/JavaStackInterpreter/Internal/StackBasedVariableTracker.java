package main.com.sid.JavaStackInterpreter.Internal;

import main.com.sid.JavaStackInterpreter.Exceptions.VariableNotFoundException;

import java.util.*;

//map that keeps track of latest value of a variable
//stack of sets that keep track of all variables in a current level
public class StackBasedVariableTracker implements VariableTracker {
    Map<String, Stack<Integer>> context;
    Stack<Set<String>> variableStack;
    public StackBasedVariableTracker(){
        context = new HashMap<>();
        variableStack = new Stack<>();
    }

    public Integer findVariableValue (String variable) {
        if (context.containsKey(variable)) {
            return context.get(variable).peek();
        }
        throw new VariableNotFoundException(variable);
    }

    public void addNewLevel () {
        variableStack.push(new HashSet<>());
    }

    public void addVariable (String var, Integer value) {

        variableStack.peek().add(var);
        Stack<Integer> st = context.get(var);
        //if adding var for the first time
        if (st == null){
            Stack<Integer> stack = new Stack<>();
            stack.push(value);
            context.put(var, stack);
        } else {
            st.push(value);
        }
    }

    //remove all those variables from current level
    //pop the current level of variables also
    public void removeCurrentLevel () {
        for (String variable : variableStack.peek()){
            deleteFromStack(variable, context);
        }
        variableStack.pop();
    }

    @Override
    public void clear() {
        this.context.clear();
        this.variableStack.clear();
    }

    private void deleteFromStack (String variable, Map<String, Stack<Integer>> map) {
        Stack<Integer> currentStack = map.get(variable);
        currentStack.pop();
        //removing the key itself so that it can't be queried later
        if (currentStack.isEmpty()){
            map.remove(variable);
        }
    }


    public String toString(){
        return context.toString();
    }

}
