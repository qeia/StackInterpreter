package StackOperation.IntrinsicOperations;

import Exceptions.InvalidStackStateException;
import Internal.StackContext;

import java.util.List;

public class Subtract implements IntrinsicOperation {

    @Override
    public void execute(StackContext st, List params) {
        if(st.size() < 2){
            throw new InvalidStackStateException("Stack size is less than 2");
        }
        Object pm1 = st.pop();
        Object pm2 = st.pop();
        if (!(pm2 instanceof Integer) || !(pm1 instanceof Integer)){
            throw new InvalidStackStateException("Can not convert stack value to Integer");
        }
        st.push(((int)pm2 - (int)pm1));
    }
}
