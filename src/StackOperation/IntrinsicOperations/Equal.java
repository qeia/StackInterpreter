package StackOperation.IntrinsicOperations;

import Exceptions.InvalidStackStateException;
import Internal.StackContext;


import java.util.List;
import java.util.Objects;

public class Equal implements IntrinsicOperation {

    @Override
    public void execute(StackContext st, List params) {
        if(st.size() < 2){
            throw new InvalidStackStateException("Stack size is less than 2");
        }
        Object pm1 = st.pop();
        Object pm2 = st.pop();
        st.push(Objects.equals(pm1,pm2));
    }
}
