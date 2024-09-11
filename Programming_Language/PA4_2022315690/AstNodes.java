/*

define Ast Nodes

*/
import java.util.ArrayList;
import java.util.Map;

abstract class AstNode {
    abstract public double calcExpr(Map<String, Double> valMem, Map<String, ArrayList<AstNode>> funMem);
}

class NormCalcNode extends AstNode {
    AstNode left, right;
    String operator;


    public NormCalcNode(AstNode left, AstNode right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }


    public double calcExpr(Map<String, Double> valMem, Map<String, ArrayList<AstNode>> funMem) {
        return Evaluate.evaluate(this, valMem, funMem);
    }
}

class NumNode extends AstNode {
    double value;

    public NumNode(double value) {
        this.value = value;

    }


    @Override
    public double calcExpr(Map<String, Double> valMem, Map<String, ArrayList<AstNode>> funMem) {
        return Evaluate.evaluate(this, valMem, funMem);
    }
}


class IdtNode extends AstNode {
    String id;

    public IdtNode(String id) {
        this.id = id;
    }


    @Override
    public double calcExpr(Map<String, Double> valMem, Map<String, ArrayList<AstNode>> funMem) {
        return Evaluate.evaluate(this, valMem, funMem);
    }
}


class AsgnNode extends AstNode {
    String id;
    AstNode value;
    AstNode next;

    public AsgnNode(String id, AstNode value, AstNode next) {
        this.id = id;
        this.value = value;
        this.next = next;
    }


    public double calcExpr(Map<String, Double> valMem, Map<String, ArrayList<AstNode>> funMem) {
        return Evaluate.evaluate(this, valMem, funMem);
    }
}


/*
class funcall_NGNode extends AstNode {
    String id;

    public funcall_NGNode(String id){
        this.id = id;
    }


    public double calcExpr(Map<String, Double> valMem, Map<String, String[]> funMem) {
        return Evaluate.evaluate(this, valMem, funMem);
    }
}
*/


class funcallNode extends AstNode {
    String id;
    ArrayList<AstNode> args;

    public funcallNode(String id, ArrayList<AstNode> args){
        // IDK. Finish this later (2024.6.2)
        // Done (2024.6.4)
        this.id = id;
        this.args = args;
    }


    public double calcExpr(Map<String, Double> valMem, Map<String, ArrayList<AstNode>> funMem) {
        return Evaluate.evaluate(this, valMem, funMem);
    }
}


class negateNode extends AstNode{
    AstNode value;

    public negateNode(AstNode value){
        this.value = value;
    }

    @Override
    public double calcExpr(Map<String, Double> valMem, Map<String, ArrayList<AstNode>> funMem) {
        return Evaluate.evaluate(this, valMem, funMem);
    }
}

class declNode extends AstNode{
    String id;
    ArrayList<AstNode> params;
    AstNode expr;
    public declNode(String id, ArrayList<AstNode> params, AstNode expr){
        this.id = id;
        this.params = params;
        this.expr = expr;
    }

    @Override
    public double calcExpr(Map<String, Double> valMem, Map<String, ArrayList<AstNode>> funMem) {
        return Evaluate.evaluate(this, valMem, funMem);
    }
}




