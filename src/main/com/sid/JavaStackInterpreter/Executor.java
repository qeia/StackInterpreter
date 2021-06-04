package main.com.sid.JavaStackInterpreter;

import main.com.sid.JavaStackInterpreter.Internal.OperationParameters;
import main.com.sid.JavaStackInterpreter.Internal.OperationsDeque;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;
import main.com.sid.JavaStackInterpreter.StackOperation.StackOperation;

import java.util.*;

//class to execute all operations in operationsDeque
class Executor {

  private StackContext stack;
  private OperationsDeque operationsDeque;

  //when main.com.sid.JavaStackInterpreter.InputDeclaration is used
  List<String> initialVariables;

  //initialVariables is only used in context of main.com.sid.JavaStackInterpreter.InputDeclaration
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
                      //adding next level of variable maps
                      stack.addNewLevel();
                      //recursion in case we get a codeblock rather than a primitive
                      Object primitiveParam = new Executor(new StackContext(stack.getVariableContext()),
                              (OperationsDeque)param, null).execute();
                      primitiveParams.add(primitiveParam);
                  } else  {
                      primitiveParams.add(param);
                  }
              }
          }
          //actually execute the operation
          op.execute(stack, primitiveParams);
          System.out.println("Stack currently: " + stack);

      }
      //need to remove last level of variables
      //so that next iteration these variables wont be used
      stack.removeLastMap();
      if(stack.isEmpty()){
        return null;
      }

      return stack.pop();
  }
}