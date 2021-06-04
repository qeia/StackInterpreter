package Internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VariableContext {
    public List<Map<String, Integer>> context;
    public VariableContext (){
        context = new ArrayList<>();
        context.add(new HashMap<>());
    }

    public Integer findVariableValue (String var){
        for (int i = context.size() - 1; i >= 0; i--) {
            Integer value = context.get(i).get(var);
            if (value != null){
                return value;
            }
        }
        throw new RuntimeException();
    }

    public String toString(){
        return context.toString();
    }

}
