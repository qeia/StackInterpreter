package main.com.sid.JavaStackInterpreter.StackOperation.IntrinsicOperations;

import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;


public class Sum extends IntrinsicOperation {
  public void execute(StackContext st){
    if(st.size() < 2){
      throw new InvalidStackStateException("Stack size is less than 2");
    }
    Object pm1 = st.pop();
    Object pm2 = st.pop();
    if (!(pm2 instanceof Integer) || !(pm1 instanceof Integer)){
      throw new InvalidStackStateException("Can not convert stack value to Integer " + pm1 + " " + pm2);
    }
    st.push(((int)pm1 + (int)pm2));

  }
  public String toString(){
    return "Sum";
  }
}