package main.com.sid.JavaStackInterpreter;

import main.com.sid.JavaStackInterpreter.Internal.OperationsList;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;

//class to invoke all operations in operationsList
public class Executor {

    StackContext stack;
    private OperationsList operationsList;

    public Executor(StackContext stack, OperationsList operationsList){
      this.stack = stack;
      this.operationsList = operationsList;
      stack.addNewLevel();
    }

  Executor(Executor executor) {
      this.stack = executor.stack;
      this.operationsList = executor.operationsList;
  }

  public Object invoke(){

      operationsList.forEach(e -> e.execute(stack));

      //need to remove last level of variables
      //so that next iteration these variables wont be used
      stack.removeCurrentLevel();

      if(stack.isEmpty()){
        return null;
      }
      return stack.pop();
  }

}