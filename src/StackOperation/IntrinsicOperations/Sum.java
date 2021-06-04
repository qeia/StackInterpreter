package StackOperation.IntrinsicOperations;

import Internal.StackContext;

import java.util.List;
public class Sum implements IntrinsicOperation {
  public void execute(StackContext op, List parameters){
    Object pm1 = op.pop();
    Object pm2 = op.pop();
    op.push(((int)pm1 + (int)pm2));

  }
  public String toString(){
    return "Sum";
  }
}