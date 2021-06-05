package main.com.sid.JavaStackInterpreter.Internal;

import java.util.*;

//contains operations in format of [(op1, params1),(op2, params2)]
public class OperationsDeque extends LinkedList<OperationParameters> {
    public String toString(){
        return "{ Deque: " + super.toString() + " }";
    }

    public OperationsDeque clone (){
        super.clone();
        return helperDFS(this);
    }

    private OperationsDeque helperDFS(OperationsDeque original){

        OperationsDeque start = new OperationsDeque();
        for (OperationParameters cur : original){
            List<Object> paramsNew  = new ArrayList<>();

            if (cur.params == null) {
                start.addLast(new OperationParameters(cur.operation, null));
            }

            else {
                for (Object param : cur.params) {
                    if (!(param instanceof OperationsDeque)) {
                        paramsNew.add(param);
                    } else {
                        paramsNew.add(helperDFS((OperationsDeque) param));
                    }
                }
                start.addLast(new OperationParameters(cur.operation, paramsNew));
            }
        }
        return start;
    }
}