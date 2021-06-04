package test;

import main.com.sid.JavaStackInterpreter.CodeBlock;
import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;
import main.com.sid.JavaStackInterpreter.Exceptions.VariableNotFoundException;
import main.com.sid.JavaStackInterpreter.InputDeclaration;
import main.com.sid.JavaStackInterpreter.StackFunction;
import org.testng.annotations.Test;

import static main.com.sid.JavaStackInterpreter.CodeBlock.*;


public class InputDeclarationTest {

    @Test
    public void Test1(){

        StackFunction foo = new InputDeclaration("a","b","c").push(1).push(15).push(
                new CodeBlock().push(1).local("v1").pushVar("v1","v1","v1").push(2).
                        op(PLUS).op(PLUS)).local("v2").pushVar("v2").op(TIMES).op(EQUAL).compile();
        foo.invoke(1,2,3);

    }

    @Test
    public void givenTest(){
        StackFunction foo =
                new InputDeclaration("a", "b", "c") // example input: 1, 2, 4
                        .pushVar("a", "b") // 1, 2
                        .op(PLUS) // 3
                        .local("v1") // 3
                        .pushVar("c", "c") // 3, 4, 4
                        .pop() // 3, 4
                        .push(2) // 3, 4, 2
                        .op(TIMES) // 3, 8
                        .local("v2") // 3, 8
                        .op(EQUAL) // false
                        .ifTrue(
                                new CodeBlock()
                                        .push("true!!")
                                        .op(PRINTLN)
                                        .pushVar("v1", "v2")
                                        .op(MINUS),
                                new CodeBlock()
                                        .push("false!!") // "false!!"
                                        .op(PRINTLN) //
                                        .pushVar("v1", "v2") // 3, 8
                                        .op(TIMES) // 24
                        ) .compile();
        foo.invoke(1, 2, 4);
    }

    @Test(expectedExceptions = VariableNotFoundException.class)
    public void TestVariableNotFound(){
        new CodeBlock().push(1).local("a").push(
                new CodeBlock().push(2).local("b").push(
                        new CodeBlock().push(3).local("c")
                ).pushVar("c")
        ).compile().invoke();
    }

    @Test(expectedExceptions = InvalidStackStateException.class)
    public void TestInvalidStackStateException(){
        new CodeBlock().push(1).local("a").push(2).op(EQUAL).op(GREATER_THAN).compile().invoke();

    }

}