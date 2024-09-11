/*

Calculate The Input String
And Return the result

*/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Evaluate {
    public static double evaluate(AstNode node, Map<String, Double> valMem, Map<String, ArrayList<AstNode>> funMem){
        if (node instanceof NormCalcNode) {
            switch (((NormCalcNode)node).operator) {
                case "+":
                    return ((NormCalcNode)node).left.calcExpr(valMem, funMem) + ((NormCalcNode)node).right.calcExpr(valMem, funMem);
                case "-":
                    return ((NormCalcNode)node).left.calcExpr(valMem, funMem) - ((NormCalcNode)node).right.calcExpr(valMem, funMem);
                case "*":
                    return ((NormCalcNode)node).left.calcExpr(valMem, funMem) * ((NormCalcNode)node).right.calcExpr(valMem, funMem);
                case "/":
                    return ((NormCalcNode)node).left.calcExpr(valMem, funMem) / ((NormCalcNode)node).right.calcExpr(valMem, funMem);

            }
        } else if (node instanceof NumNode) {
            return ((NumNode)node).value;

        } else if (node instanceof AsgnNode) {
            // Calculate the value of the variable
            double value = ((AsgnNode) node).value.calcExpr(valMem, funMem);
            // Store the value and id to the value-map
            valMem.put(((AsgnNode) node).id, value);
            // Evaluate and return the result of the next expression (after in)
            return ((AsgnNode) node).next.calcExpr(valMem, funMem);

        } else if (node instanceof IdtNode) {
            if (valMem.get(((IdtNode)node).id) == null){    // If the identifier doesn't exist in the memory
                System.out.println("Error: Free identifier " + ((IdtNode) node).id + " detected.");
                System.exit(0);
            } else {
                return valMem.get(((IdtNode)node).id);
            }
        } else if (node instanceof negateNode) {
            return -(((negateNode) node).value.calcExpr(valMem, funMem));
        } else if (node instanceof declNode) {
            funMem.put(((declNode) node).id, ((declNode) node).params);
            return 0;
        } else if (node instanceof funcallNode) {
            String funcId = ((funcallNode) node).id;
            ArrayList<AstNode> args = ((funcallNode) node).args;
            ArrayList<AstNode> params = funMem.get(funcId);     // The last element in params is function body

            if (params == null) {       // if the parameter list is null, then this function is undefined (there's always an element in the list that stores function body)
                System.out.println("Error: Undefined function " + funcId + " detected.");
                System.exit(0);
            }

            if (args.size() != params.size() - 1) {     // If the length of 2 lists doesn't match, then exit the program with an error message
                System.out.println("Error: The number of arguments of " + funcId + " mismatched, Required: " + (params.size() - 1) + ", Actual: " + (args.size()));
                System.exit(0);
            }

            Map<String, Double> localValMem = new HashMap<>();  // Create local variable memory
            for (int i = 0; i < params.size() - 1; i++) {
                IdtNode param = (IdtNode) params.get(i);
                double argValue = args.get(i).calcExpr(valMem, funMem);
                localValMem.put(param.id, argValue);
            }

            // The last parameter in the parameter list is the function body
            AstNode funExpr = params.get(params.size() - 1);
            return funExpr.calcExpr(localValMem, funMem);

        }


        return -1;



    }
}
