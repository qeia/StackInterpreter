package main.com.sid.JavaStackInterpreter;

import main.com.sid.JavaStackInterpreter.Internal.OperationParameters;
import main.com.sid.JavaStackInterpreter.Internal.OperationsList;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;
import main.com.sid.JavaStackInterpreter.StackOperation.StackOperation;

import java.util.*;

//class to invoke all operations in operationsList
public class Executor {

    StackContext stack;
    OperationsList operationsList;



  Executor(StackContext stack, OperationsList operationsList){
      this.stack = stack;
      this.operationsList = operationsList;
      stack.addNewLevel();
  }

  Executor(Executor executor) {
      this.stack = executor.stack;
      this.operationsList = executor.operationsList;
  }

  public Object invoke(){
      int i = 0;
      while(i < operationsList.size()){

          OperationParameters operationParameters = operationsList.get(i++);
          StackOperation op = operationParameters.operation;
          List<Object> params = operationParameters.params;

          //actually invoke the operation
          op.executeOnStack(stack, params);
      }

      //need to remove last level of variables
      //so that next iteration these variables wont be used
      stack.removeCurrentLevel();
      if(stack.isEmpty()){
        return null;
      }

      return stack.pop();
  }

  //each param in params might be another codeblock
  //try to execute these codeblock and get the primitive
  public static List<Object> recursivelyExecuteOnParameters(List<Object> params, StackContext st) {
      List<Object> primitiveParams = new ArrayList<>();
      if (params != null) {
          for (Object param : params) {
              if (param instanceof OperationsList) {
                  //recursion in case we get a codeblock rather than a primitive
                  Object primitiveParam = new Executor(new StackContext(st),
                          (OperationsList)param).invoke();
                  primitiveParams.add(primitiveParam);
              } else  {
                  primitiveParams.add(param);
              }
          }
      }
      return primitiveParams;
  }
}