/*

Calculate The Input String
And Return the result

*/


import java.util.Map;

public class Evaluate {
    public static double evaluate(AstNode node, Map<String, Double> content){
        if (node instanceof NormCalcNode) {
            switch (((NormCalcNode)node).operator) {
                case "+":
                    return ((NormCalcNode)node).left.calcExpr(content) + ((NormCalcNode)node).right.calcExpr(content);
                case "-":
                    return ((NormCalcNode)node).left.calcExpr(content) - ((NormCalcNode)node).right.calcExpr(content);
                case "*":
                    return ((NormCalcNode)node).left.calcExpr(content) * ((NormCalcNode)node).right.calcExpr(content);
                case "/":
                    return ((NormCalcNode)node).left.calcExpr(content) / ((NormCalcNode)node).right.calcExpr(content);
            }
        } else if (node instanceof NumNode) {
            return ((NumNode)node).value;

        } else if (node instanceof AsgnNode) {
            double result = ((AsgnNode)node).value.calcExpr(content);

            content.put(((AsgnNode)node).id, result);
            return 0;

        } else if (node instanceof IdtNode) {
            return content.get(((IdtNode)node).id);
        }


        return -1;



    }
}
