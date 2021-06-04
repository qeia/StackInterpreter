import Internal.OperationParameters;
import Internal.OperationsDeque;
import Internal.StackContext;
import StackOperation.StackOperation;

import java.util.*;

class Compiler {

  private StackContext stack;
  private OperationsDeque od;

  public Compiler (StackContext stack, OperationsDeque od){
      this.stack = stack;
      this.od = od;
      System.out.println("This is new Internal.StackContext  " + stack.toString() + " " + od.toString());
  }
  public void putValuesIntoMap(Map<String, Integer> initialVariables){

      stack.getLastMap().putAll(initialVariables);
  }

  Object compile(){
      System.out.println("doing"+ " "+ od.toString());
      while(!od.dq.isEmpty()){

          System.out.println(od);
          OperationParameters operationParameters = od.dq.pollFirst();
          StackOperation op = operationParameters.op;
          List params = operationParameters.params;
          List<Object> primitiveParams = new ArrayList<>();

          if (params != null) {
              for (Object param : params) {
                  if (param instanceof OperationsDeque) {
                      stack.variableContext.context.add(new HashMap<>());
                      primitiveParams.add(new Compiler(new StackContext(stack.variableContext), (OperationsDeque)param).compile());
                  } else  {
                      primitiveParams.add(param);
                  }
              }
          }
          op.execute(stack, primitiveParams);
          System.out.println(stack);

      }

      stack.removeLastMap();
      if(stack.isEmpty()){
        return null;
      }

      return stack.pop();
  }
}