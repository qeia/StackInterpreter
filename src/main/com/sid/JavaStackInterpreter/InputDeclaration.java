package main.com.sid.JavaStackInterpreter;

import main.com.sid.JavaStackInterpreter.Internal.OperationsDeque;
import main.com.sid.JavaStackInterpreter.Internal.StackContext;
import main.com.sid.JavaStackInterpreter.Internal.VariableContext;

import java.util.Arrays;

//simply a codeblock but we can instantiate some variables
public class InputDeclaration extends CodeBlock {

    public InputDeclaration(String... vars){
        operations = new OperationsDeque();
        executor = new StackFunction(new StackContext(new VariableContext()), operations, Arrays.asList(vars));

    }
}
