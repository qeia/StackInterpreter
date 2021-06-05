package main.com.sid.JavaStackInterpreter;

import main.com.sid.JavaStackInterpreter.Exceptions.InvalidTypeException;
import main.com.sid.JavaStackInterpreter.Internal.OperationParameters;
import main.com.sid.JavaStackInterpreter.Internal.OperationsDeque;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;
import main.com.sid.JavaStackInterpreter.Internal.StackBasedVariableTracker;
import main.com.sid.JavaStackInterpreter.StackOperation.IntrinsicOperations.*;
import main.com.sid.JavaStackInterpreter.StackOperation.*;
import main.com.sid.JavaStackInterpreter.StackOperation.StackOperation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//generic block of code
//fluent api
//operations is a deque that contains tuples of operations vs params
//executor is a class that executes all the operations on "operations" recursively
public class CodeBlock<T extends CodeBlock> {

  OperationsDeque operations;
  Executor executor;


  public CodeBlock(){
    operations = new OperationsDeque();
    executor = new Executor(new StackContext(new StackBasedVariableTracker()), operations);

  }

  public OperationsDeque getOperations(){
    return operations;
  }

  public static StackOperation PUSH = new Push();
  public static IntrinsicOperation PLUS = new Sum();
  public static StackOperation LOCAL = new Local();
  public static StackOperation IF_TRUE = new IfTrue();
  public static StackOperation PUSH_VAR = new PushVar();
  public static IntrinsicOperation EQUAL = new Equal();
  public static IntrinsicOperation MINUS = new Subtract();
  public static IntrinsicOperation PRINTLN = new PrintLn();
  public static IntrinsicOperation TIMES = new Times();
  public static IntrinsicOperation DIVIDE = new Divide();
  public static IntrinsicOperation LESS_THAN = new LessThan();
  public static IntrinsicOperation GREATER_THAN = new GreaterThan();
  public static IntrinsicOperation STR = new ToString();
  public static StackOperation POP = new Pop();
  private static StackFunctionOperation STACK_FUNCTION_OPERATION = new StackFunctionOperation();


  private Object getActualParameter(Object object) {

    if (object instanceof CodeBlock){
      return ((CodeBlock)object).getOperations();
    } else if (object instanceof Integer || object instanceof String) {
      return object;
    }
    throw new InvalidTypeException("Can not parse " + object);

  }

  private List<Object> getActualParameters (Object... objects){
    return Arrays.stream(objects).map(this::getActualParameter).collect(Collectors.toList());
  }

  public T push (Object... params){
    operations.addLast(new OperationParameters(PUSH, getActualParameters(params)));
    return (T) this;
  }

  public T local (String var){
    operations.addLast(new OperationParameters(LOCAL, Collections.singletonList(var)));
    return (T) this;
  }

  public T pop (){
    operations.addLast(new OperationParameters(POP, null));
    return (T) this;
  }

  public T ifTrue (CodeBlock ip1, CodeBlock ip2){
    operations.addLast(new OperationParameters(IF_TRUE, Arrays.asList(ip1.getOperations(), ip2.getOperations())));
    return (T)this;
  }

  public T op (IntrinsicOperation operation){
    operations.addLast(new OperationParameters(operation, null));
    return (T) this;
  }

  public T op (StackFunction stackFunction){
    operations.addLast(new OperationParameters(STACK_FUNCTION_OPERATION, Arrays.asList(stackFunction)));
    return (T) this;
  }

  public T pushVar (String... vars){
    operations.addLast(new OperationParameters(PUSH_VAR, Arrays.asList(vars)));
    return (T) this;
  }

  public Executor compile(){
      return executor;
  }


}