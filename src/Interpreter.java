
import Internal.OperationParameters;
import Internal.OperationsDeque;
import Internal.StackContext;
import Internal.VariableContext;
import StackOperation.IntrinsicOperations.*;
import StackOperation.*;
import StackOperation.StackOperation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Interpreter {

  private OperationsDeque op;
  private Compiler compiler;
  private List<String> initialVariables;
  public Interpreter (String... vars){
    op = new OperationsDeque();
    compiler = new Compiler(new StackContext(new VariableContext()), op);
    this.initialVariables = Arrays.asList(vars);
  }

  public OperationsDeque getOp (){
    return op;
  }

  public static StackOperation PUSH = new Push();
  public static IntrinsicOperation PLUS = new Sum();
  public static StackOperation LOCAL = new Local();
  public static StackOperation IFTRUE = new IfTrue();
  public static StackOperation PUSHVAR = new PushVar();
  public static IntrinsicOperation EQUAL = new Equal();
  public static IntrinsicOperation MINUS = new Subtract();
  public static IntrinsicOperation PRINTLN = new PrintLn();
  public static IntrinsicOperation TIMES = new Times();
  public static StackOperation POP = new Pop();

  private Object getActualParameter(Object object) {

    if (object instanceof Interpreter){
      return ((Interpreter)object).getOp();
    } else if (object instanceof Integer || object instanceof String){
      return object;
    }
    throw new RuntimeException();

  }

  private List<Object> getActualParameters (Object... objects){
    return Arrays.stream(objects).map(this::getActualParameter).collect(Collectors.toList());
  }

  public Interpreter push (Object... params){
    op.dq.addLast(new OperationParameters(PUSH, getActualParameters(params)));
    return this;
  }



  public Interpreter local (String var){
    op.dq.addLast(new OperationParameters(LOCAL, getActualParameters(var)));
    return this;
  }

  public Interpreter pop (){
    op.dq.addLast(new OperationParameters(POP, null));
    return this;
  }

  public Interpreter ifTrue (Interpreter ip1, Interpreter ip2){
    op.dq.addLast(new OperationParameters(IFTRUE, Arrays.asList(ip1.getOp(), ip2.getOp())));
    return this;
  }

  public Interpreter op (IntrinsicOperation operation){
    op.dq.addLast(new OperationParameters(operation, null));
    return this;
  }

  public Interpreter pushVar (String... vars){
    op.dq.addLast(new OperationParameters(PUSHVAR, Arrays.asList(vars)));
    return this;
  }

  public Interpreter compile(){
      return this;
  }

  public Object invoke(Integer... values){
    Map<String, Integer> map = new HashMap<>();
    for(int i = 0; i < values.length; i++){
      map.put(initialVariables.get(i), values[i]);
    }
    compiler.putValuesIntoMap(map);
//    System.out.println("Starting" + " " + op.toString());
    return compiler.compile();
  }
}