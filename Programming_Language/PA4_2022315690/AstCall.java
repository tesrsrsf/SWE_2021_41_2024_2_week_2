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
            System.out.println("LETIN");
            Call(new IdtNode(((AsgnNode)node).id), indentNum + indentDepth);
            Call(((AsgnNode)node).value, indentNum + indentDepth);
            Call(((AsgnNode) node).next, indentNum + indentDepth);

        } else if (node instanceof IdtNode) {
            System.out.println(((IdtNode)node).id);

        } else if (node instanceof funcallNode) {
            System.out.println("CALL");
            Call(new IdtNode(((funcallNode) node).id), indentNum + indentDepth);
            for (AstNode para: ((funcallNode) node).args) {
                Call(para, indentNum + indentDepth);
            }
        } else if (node instanceof declNode) {
            System.out.println("DECL");
            Call(new IdtNode(((declNode) node).id), indentNum + indentDepth);
            for (int i = 0; i < ((declNode) node).params.size() - 1; i++) {
                Call(((declNode) node).params.get(i), indentNum + indentDepth);
            }
            Call(((declNode) node).expr, indentNum + indentDepth);

        } else if (node instanceof negateNode) {
            System.out.println("NEG");
            Call(((negateNode) node).value, indentNum + indentDepth);
        }


    }
}


