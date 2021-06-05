package main.com.sid.JavaStackInterpreter.Internal;

import main.com.sid.JavaStackInterpreter.Exceptions.VariableNotFoundException;

import java.util.*;

//map that keeps track of latest value of a variable
//stack of sets that keep track of all variables in a current level
public class VariableContext {
    Map<String, Stack<Integer>> context;
    Stack<Set<String>> variableStack;
    public VariableContext (){
        context = new HashMap<>();
        variableStack = new Stack<>();
    }



    public String toString(){
        return context.toString();
    }

}
