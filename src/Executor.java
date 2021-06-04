import Internal.OperationParameters;
import Internal.OperationsDeque;
import Internal.StackContext;
import StackOperation.StackOperation;

import java.util.*;

class Executor {

  private StackContext stack;
  private OperationsDeque operationsDeque;
  List<String> initialVariables;

  Executor(StackContext stack, OperationsDeque operationsDeque, List<String> initialVariables){
      this.stack = stack;
      this.operationsDeque = operationsDeque;
      this.initialVariables = initialVariables;
  }
  void putValuesIntoMap(Map<String, Integer> initialVariables){

      stack.getLastMap().putAll(initialVariables);
  }

  Object execute(){
      System.out.println("Executing a new"+ " "+ operationsDeque.toString());
      while(!operationsDeque.isEmpty()){

          System.out.println("Remaining Operations: " + operationsDeque);

          OperationParameters operationParameters = operationsDeque.pollFirst();
          StackOperation op = operationParameters.operation;
          List params = operationParameters.params;
          List<Object> primitiveParams = new ArrayList<>();

          if (params != null) {
              for (Object param : params) {
                  if (param instanceof OperationsDeque) {
                      stack.addNewLevel();
                      primitiveParams.add(new Executor(new StackContext(stack.getVariableContext()), (OperationsDeque)param, null).execute());
                  } else  {
                      primitiveParams.add(param);
                  }
              }
          }
          op.execute(stack, primitiveParams);
          System.out.println("Stack currently: " + stack);

      }

      stack.removeLastMap();
      if(stack.isEmpty()){
        return null;
      }

      return stack.pop();
  }
}