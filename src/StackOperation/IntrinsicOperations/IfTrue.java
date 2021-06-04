package StackOperation.IntrinsicOperations;

import Internal.StackContext;
import StackOperation.StackOperation;

import java.util.List;

public class IfTrue implements StackOperation {
    @Override
    public void execute(StackContext st, List params) {
        if ((boolean)st.pop()){
            st.push(params.get(0));
            return;
        }
        st.push(params.get(1));
    }
}
