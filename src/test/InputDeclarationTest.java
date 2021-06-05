package test;

import main.com.sid.JavaStackInterpreter.CodeBlock;
import main.com.sid.JavaStackInterpreter.Exceptions.InvalidStackStateException;
import main.com.sid.JavaStackInterpreter.Exceptions.InvalidTypeException;
import main.com.sid.JavaStackInterpreter.Exceptions.VariableNotFoundException;
import main.com.sid.JavaStackInterpreter.InputDeclaration;
import main.com.sid.JavaStackInterpreter.StackFunction;
import org.testng.annotations.Test;

import static main.com.sid.JavaStackInterpreter.CodeBlock.*;


public class InputDeclarationTest {

    private StackFunction stackFunctionToTest;
    private StackFunction twoOperations;
    private StackFunction oneOperation;


    public InputDeclarationTest () {
        stackFunctionToTest = new InputDeclaration("a", "b", "c") // example input: 1, 2, 4
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
        twoOperations = new InputDeclaration("a", "b").pushVar("a").pushVar("b").push(100).op(TIMES).op(MINUS).compile();
        oneOperation = new InputDeclaration("a").pushVar("a").push(10).op(GREATER_THAN).compile();
    }

    @Test
    public void Test1(){
        StackFunction foo = new InputDeclaration("a","b","c").push(1).push(15).push(
                new CodeBlock()
                        .push(1)
                        .local("v1")
                        .pushVar("v1","v1","v1")
                        .push(2).
                        op(PLUS).op(PLUS)
        ).op(PRINTLN).local("v2").pushVar("v2").op(TIMES).op(GREATER_THAN).ifTrue(
                new CodeBlock().push("True").push(1,2,3,4).op(TIMES),
                new CodeBlock().push("False").push(10,10).op(TIMES)
        ).compile();
        assert(12 == (int)foo.invoke(1,2,3));
    }

    @Test
    public void TestIdempotency(){
        assert((int)stackFunctionToTest.invoke(1, 2, 4) == 24);
        assert((int)stackFunctionToTest.invoke(1, 2, 4) == 24);
        assert((boolean)oneOperation.invoke(5));
        assert(!(boolean)oneOperation.invoke(11));
        assert((int)twoOperations.invoke(5,6) == 595);
        assert((int)twoOperations.invoke(5,6) == 595);
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

    @Test
    public void TestThreeLevels(){
        int x = (int)new CodeBlock().push(1).local("v1").push(2).local("v2").push(
                new CodeBlock().push(5).local("v2").push(
                        new CodeBlock().push(3).push(2).op(TIMES)
                ).pushVar("v2").op(PLUS).op(TIMES)
        ).op(TIMES).op(MINUS).pushVar("v2").op(PLUS).compile().invoke();
        assert(x==111);
    }

    @Test
    public void TestShadowing(){
        int x = (int) new CodeBlock().push(1).local("v1").push(
                new CodeBlock().push(5).local("v1").push(
                        new CodeBlock().pushVar("v1").push(15).op(PLUS)
                ).op(TIMES)
        ).op(PLUS).compile().invoke();
        assert(x==101);
    }

    @Test
    public void TestStackFunctionOnOP(){
        int x = (int) new CodeBlock().push(1).local("v1").push(2).push(4).op(
                stackFunctionToTest
        ).compile().invoke();
        System.out.println(x);
        assert(x==24);
    }

    @Test(expectedExceptions = InvalidTypeException.class)
    public void TestInvalidTypeOnUsingStackFunctionOP(){
        int x = (int) new CodeBlock().push(1).local("v1").push(2).push("5").op(
                stackFunctionToTest
        ).compile().invoke();
        System.out.println(x);
        assert(x==24);
    }
}