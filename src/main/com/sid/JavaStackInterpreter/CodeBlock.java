package main.com.sid.JavaStackInterpreter;

import main.com.sid.JavaStackInterpreter.Exceptions.InvalidTypeException;
import main.com.sid.JavaStackInterpreter.Internal.OperationsList;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;
import main.com.sid.JavaStackInterpreter.Internal.StackBasedVariableTracker;
import main.com.sid.JavaStackInterpreter.StackOperation.IntrinsicOperations.*;
import main.com.sid.JavaStackInterpreter.StackOperation.*;
import main.com.sid.JavaStackInterpreter.StackOperation.StackOperation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//generic block of code
//fluent api
//operations is a deque that contains tuples of operations vs params
//executor is a class that executes all the operations on "operations" recursively
public class CodeBlock<T extends CodeBlock> {

  OperationsList operations;
  Executor executor;


  public CodeBlock(){
    operations = new OperationsList();
    executor = new Executor(new StackContext(new StackBasedVariableTracker()), operations);

  }

  public OperationsList getOperations(){
    return operations;
  }


  public static IntrinsicOperation EQUAL = new Equal();
  public static IntrinsicOperation MINUS = new Subtract();
  public static IntrinsicOperation PRINTLN = new PrintLn();
  public static IntrinsicOperation TIMES = new Times();
  public static IntrinsicOperation DIVIDE = new Divide();
  public static IntrinsicOperation LESS_THAN = new LessThan();
  public static IntrinsicOperation GREATER_THAN = new GreaterThan();
  public static IntrinsicOperation STR = new ToString();
  public static IntrinsicOperation PLUS = new Sum();
  public static StackOperation POP = new Pop();



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
    operations.add(new Push(getActualParameters(params)));
    return (T) this;
  }

  public T local (String var){
    operations.add(new Local(var));
    return (T) this;
  }

  public T pop (){
    operations.add(new Pop());
    return (T) this;
  }

  public T ifTrue (CodeBlock ip1, CodeBlock ip2){
    operations.add(new IfTrue(ip1.getOperations(), ip2.getOperations()));
    return (T)this;
  }

  public T op (IntrinsicOperation operation){
    operations.add(operation);
    return (T) this;
  }

  public T op (StackFunction stackFunction){
    operations.add(new StackFunctionOperation(stackFunction));
    return (T) this;
  }

  public T pushVar (String... vars){
    operations.add(new PushVar(Arrays.asList(vars)));
    return (T) this;
  }

  public Executor compile(){
      return executor;
  }


}