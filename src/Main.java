

class Main {
  public static void main(String[] args) {


    StackFunction foo = new InputDeclaration("a", "b", "c") // example input: 1, 2, 4
            .pushVar("a", "b") // 1, 2
            .op(CodeBlock.PLUS) // 3
            .local("v1") // 3
            .pushVar("c", "c") // 3, 4, 4
            .pop() // 3, 4
            .push(2) // 3, 4, 2
            .op(CodeBlock.TIMES) // 3, 8
            .local("v2") // 3, 8
            .op(CodeBlock.EQUAL) // false
            .ifTrue(
                    new CodeBlock()
                            .push("true!!")
                            .op(CodeBlock.PRINTLN)
                            .pushVar("v1", "v2")
                            .op(CodeBlock.MINUS),
                    new CodeBlock()
                            .push("false!!") // "false!!"
                            .op(CodeBlock.PRINTLN) //
                            .pushVar("v1", "v2") // 3, 8
                            .op(CodeBlock.TIMES) // 24
            ) .compile();
        foo.invoke(1,2,4);
    
  }
}

