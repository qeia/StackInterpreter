package StackOperation.IntrinsicOperations;

import Internal.StackContext;

import java.util.List;

public class Subtract implements IntrinsicOperation {

    @Override
    public void execute(StackContext st, List params) {
        Object pm1 = st.pop();
        Object pm2 = st.pop();
        st.push(((int)pm2 - (int)pm1));
    }
}
