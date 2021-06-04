package StackOperation;

import Internal.StackContext;
import StackOperation.StackOperation;

import java.util.List;

public class Local implements StackOperation {

    @Override
    public void execute(StackContext st, List params) {
        st.getLastMap().put((String)params.get(0), (int) st.peek());
    }
    public String toString(){
        return "Local";
    }
}
