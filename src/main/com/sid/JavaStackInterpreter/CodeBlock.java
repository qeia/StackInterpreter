package main.com.sid.JavaStackInterpreter;

import main.com.sid.JavaStackInterpreter.Internal.OperationParameters;
import main.com.sid.JavaStackInterpreter.Internal.OperationsDeque;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;
import main.com.sid.JavaStackInterpreter.Internal.VariableContext;
import main.com.sid.JavaStackInterpreter.StackOperation.IntrinsicOperations.*;
import main.com.sid.JavaStackInterpreter.StackOperation.*;
import main.com.sid.JavaStackInterpreter.StackOperation.StackOperation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//generic code of a blog
public class CodeBlock {

  OperationsDeque operations;
  StackFunction executor;


  public CodeBlock(){
    operations = new OperationsDeque();
    executor = new StackFunction(new StackContext(new VariableContext()), operations, null);

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
  public static StackOperation POP = new Pop();


  private Object getActualParameter(Object object) {

    if (object instanceof CodeBlock){
      return ((CodeBlock)object).getOperations();
    } else if (object instanceof Integer || object instanceof String){
      return object;
    }
    throw new RuntimeException();

  }

  private List<Object> getActualParameters (Object... objects){
    return Arrays.stream(objects).map(this::getActualParameter).collect(Collectors.toList());
  }

  public CodeBlock push (Object... params){
    operations.addLast(new OperationParameters(PUSH, getActualParameters(params)));
    return this;
  }

  public CodeBlock local (String var){
    operations.addLast(new OperationParameters(LOCAL, getActualParameters(var)));
    return this;
  }

  public CodeBlock pop (){
    operations.addLast(new OperationParameters(POP, null));
    return this;
  }

  public CodeBlock ifTrue (CodeBlock ip1, CodeBlock ip2){
    operations.addLast(new OperationParameters(IF_TRUE, Arrays.asList(ip1.getOperations(), ip2.getOperations())));
    return this;
  }

  public CodeBlock op (IntrinsicOperation operation){
    operations.addLast(new OperationParameters(operation, null));
    return this;
  }

  public CodeBlock pushVar (String... vars){
    operations.addLast(new OperationParameters(PUSH_VAR, Arrays.asList(vars)));
    return this;
  }

  public StackFunction compile(){
      return executor;
  }


}