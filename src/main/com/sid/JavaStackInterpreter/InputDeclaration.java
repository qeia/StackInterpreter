package main.com.sid.JavaStackInterpreter;


import java.util.Arrays;
import java.util.List;

//simply a codeblock but we can instantiate some variables initially
public class InputDeclaration extends CodeBlock<InputDeclaration> {

    //when InputDeclaration is used rather than codeblock
    private List<String> initialVariables;

    public InputDeclaration(String... vars){
        super();
        this.initialVariables = Arrays.asList(vars);
    }

    @Override
    public StackFunction compile(){
        return new StackFunction(executor, initialVariables);
    }
}
