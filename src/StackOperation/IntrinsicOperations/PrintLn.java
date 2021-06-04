package StackOperation.IntrinsicOperations;

import Internal.StackContext;

import java.util.List;

public class PrintLn implements IntrinsicOperation {
    @Override
    public void execute(StackContext st, List params) {
        System.out.println(st.peek());
    }
}
