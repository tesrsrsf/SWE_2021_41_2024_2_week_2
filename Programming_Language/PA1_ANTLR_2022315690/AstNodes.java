/*

define Ast Nodes

*/
import java.util.Map;

abstract class AstNode {
    abstract public double calcExpr(Map<String, Double> content);
}

class NormCalcNode extends AstNode {
    AstNode left, right;
    String operator;


    public NormCalcNode(AstNode left, AstNode right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }


    public double calcExpr(Map<String, Double> content) {
        return Evaluate.evaluate(this, content);
    }
}

class NumNode extends AstNode {
    double value;

    public NumNode(double value) {
        this.value = value;

    }


    @Override
    public double calcExpr(Map<String, Double> content) {
        return Evaluate.evaluate(this, content);
    }
}


class IdtNode extends AstNode {
    String id;

    public IdtNode(String id) {
        this.id = id;
    }


    @Override
    public double calcExpr(Map<String, Double> content) {
        return Evaluate.evaluate(this, content);
    }
}


class AsgnNode extends AstNode {
    String id;
    AstNode value;

    public AsgnNode(String id, AstNode value) {
        this.id = id;
        this.value = value;
    }


    public double calcExpr(Map<String, Double> content) {
        return Evaluate.evaluate(this, content);
    }
}