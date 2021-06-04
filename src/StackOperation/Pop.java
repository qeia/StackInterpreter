package StackOperation;

import Internal.StackContext;

import java.util.List;

public class Pop implements StackOperation {
    @Override
    public void execute(StackContext st, List params) {
        st.pop();
    }

}
