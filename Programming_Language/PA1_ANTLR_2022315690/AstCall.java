/*

print the Ast that we build in BuildAstVisitor.java

*/


public class AstCall {
    static int indentDepth = 1;
    public static void Call(AstNode node, int indentNum) {
        for (int i = 0; i < indentNum; i++) {
            System.out.print("\t");
        }

        if (node instanceof NormCalcNode) {
            switch(((NormCalcNode) node).operator){
                case "+":
                    System.out.println("ADD");
                    break;

                case "-":
                    System.out.println("SUB");
                    break;

                case "*":
                    System.out.println("MUL");
                    break;

                case "/":
                    System.out.println("DIV");
                    break;

            }

            Call(((NormCalcNode) node).left, indentNum + indentDepth);
            Call(((NormCalcNode) node).right, indentNum + indentDepth);


        } else if (node instanceof NumNode) {
            System.out.println(((NumNode)node).value);

        } else if (node instanceof AsgnNode) {
            System.out.println("ASSIGN");
            Call(new IdtNode(((AsgnNode)node).id), indentNum + indentDepth);
            Call(((AsgnNode)node).value, indentNum + indentDepth);

        } else if (node instanceof IdtNode) {
            System.out.println(((IdtNode)node).id);
        }




    }
}


