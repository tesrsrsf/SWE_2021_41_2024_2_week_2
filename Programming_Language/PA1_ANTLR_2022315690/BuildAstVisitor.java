/*

Build Ast using the method in MathBaseVisitor.java 
you should override the methods.

*/

public class BuildAstVisitor extends ExprBaseVisitor<AstNode> {
    @Override
    public AstNode visitParensExpr(ExprParser.ParensExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public AstNode visitInfixExpr(ExprParser.InfixExprContext ctx) {
        AstNode left = visit(ctx.expr(0));
        AstNode right = visit(ctx.expr(1));
        String operator = ctx.getChild(1).getText();
        return new NormCalcNode(left, right, operator);
    }

    @Override
    public AstNode visitNumberExpr(ExprParser.NumberExprContext ctx) {
        double num = Double.parseDouble(ctx.getText());
        return new NumNode(num);
    }

    @Override
    public AstNode visitIdentifierExpr(ExprParser.IdentifierExprContext ctx) {
        String id = ctx.id().getText();
        return new IdtNode(id);
    }

    @Override
    public AstNode visitAssignExpr(ExprParser.AssignExprContext ctx) {
        String id = ctx.id().getText();
        AstNode value = visit(ctx.expr());
        return new AsgnNode(id, value);
    }


}

