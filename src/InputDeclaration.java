import Internal.OperationsDeque;
import Internal.StackContext;
import Internal.VariableContext;

import java.util.Arrays;

class InputDeclaration extends CodeBlock {

    InputDeclaration(String... vars){
        operations = new OperationsDeque();
        executor = new StackFunction(new StackContext(new VariableContext()), operations, Arrays.asList(vars));

    }
}
