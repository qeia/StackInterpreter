package StackOperation.IntrinsicOperations;

import Internal.StackContext;


import java.util.List;

public class Times implements IntrinsicOperation {
    @Override
    public void execute(StackContext st, List params) {
        Object ob1 = st.pop();
        Object ob2 = st.pop();
        st.push((int)ob1 * (int)ob2);
    }
}
