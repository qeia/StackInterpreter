package main.com.sid.JavaStackInterpreter.StackOperation;

import main.com.sid.JavaStackInterpreter.Executor;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;

import java.util.List;

public interface StackOperation {

  //by default we want to execute the codeblocks in the parameters first
  //there are some cases where we might conditionally execute
  default void executeOnStack(StackContext st, List<Object> params){
      List<Object> primitiveParams = Executor.recursivelyExecuteOnParameters(params, st);
      execute(st, primitiveParams);

  }
  void execute(StackContext st, List params);
}