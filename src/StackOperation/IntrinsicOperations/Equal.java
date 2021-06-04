package StackOperation.IntrinsicOperations;

import Internal.StackContext;


import java.util.List;
import java.util.Objects;

public class Equal implements IntrinsicOperation {

    @Override
    public void execute(StackContext st, List params) {
        Object pm1 = st.pop();
        Object pm2 = st.pop();
        st.push(Objects.equals(pm1,pm2));
    }
}
