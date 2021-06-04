package Internal;

import java.util.Deque;
import java.util.*;

public class OperationsDeque {
  public Deque<OperationParameters> dq;
  public OperationsDeque (){
    dq = new LinkedList<>();
  }
  public String toString(){
    return dq.toString();
  }
}