package main.com.sid.JavaStackInterpreter.Internal;

import java.util.*;

//contains operations in format of [(op1, params1),(op2, params2)]
public class OperationsList extends ArrayList<OperationParameters> {
    public String toString() {
        return "{ Deque: " + super.toString() + " }";
    }
}