package main.com.sid.JavaStackInterpreter.StackOperation;


import main.com.sid.JavaStackInterpreter.Internal.StackContext;

import java.util.List;

public class Push extends StackOperation {

  private List<Object> params;

  public Push (List<Object> params){
    this.params = params;
  }

  @Override
  public void execute (StackContext st){
    List<Object> primitiveParams = recursivelyExecuteOnParameters(params, st);
    for (Object param: primitiveParams) {
      st.push(param);
    }
  }


}
