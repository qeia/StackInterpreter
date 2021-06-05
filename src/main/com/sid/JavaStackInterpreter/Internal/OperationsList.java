package main.com.sid.JavaStackInterpreter.Internal;

import main.com.sid.JavaStackInterpreter.StackOperation.StackOperation;

import java.util.*;

//contains operations in format of [(op1, params1),(op2, params2)]
public class OperationsList extends ArrayList<StackOperation> {
    public String toString() {
        return "{ ops: " + super.toString() + " }";
    }
}