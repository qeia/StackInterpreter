package main.com.sid.JavaStackInterpreter.StackOperation;

import main.com.sid.JavaStackInterpreter.Internal.StackContext;

import java.util.List;
public class Push implements StackOperation {
  public void execute(StackContext op, List parameters) {

    for (Object pm : parameters)
      op.push(pm);
  }


  public String toString(){
    return "Push";
  }
}