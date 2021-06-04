import static javax.management.Query.TIMES;

class Main {
  public static void main(String[] args) {
//    new Interpreter().push(1, 2).local("v3")
//    .sum().push(15).local("v1").sum().push(new Interpreter().
//            push(15,12,"v1").sum().local("v4").push("v4").sum()).sum().push("v3").sum().push("v4").compile();

    new Interpreter("a", "b", "c") // example input: 1, 2, 4
            .pushVar("a", "b") // 1, 2
            .op(Interpreter.PLUS) // 3
            .local("v1") // 3
            .pushVar("c", "c") // 3, 4, 4
            .pop() // 3, 4
            .push(2) // 3, 4, 2
            .op(Interpreter.TIMES) // 3, 8
            .local("v2") // 3, 8
            .op(Interpreter.EQUAL) // false
            .ifTrue(
                    new Interpreter()
                            .push("true!!")
                            .op(Interpreter.PRINTLN)
                            .pushVar("v1", "v2")
                            .op(Interpreter.MINUS),
                    new Interpreter()
                            .push("false!!") // "false!!"
                            .op(Interpreter.PRINTLN) //
                            .pushVar("v1", "v2") // 3, 8
                            .op(Interpreter.TIMES) // 24
            ) .compile().invoke(1,2,4);
    
  }
}

