package main.com.sid.JavaStackInterpreter.Internal;

import main.com.sid.JavaStackInterpreter.Exceptions.VariableNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//a list of maps, each level in this list is the map of variables accessible only to this level
public class VariableContext {
    List<Map<String, Integer>> context;
    public VariableContext (){
        context = new ArrayList<>();
        context.add(new HashMap<>());
    }

    //find the first level from the back that contains our variable
    Integer findVariableValue(String var){

        for (int i = context.size() - 1; i >= 0; i--) {
            Integer value = context.get(i).get(var);
            if (value != null){
                return value;
            }
        }

        throw new VariableNotFoundException(var + " not found");
    }

    public String toString(){
        return context.toString();
    }

}
