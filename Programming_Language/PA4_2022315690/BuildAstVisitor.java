/*

Build Ast using the method in MathBaseVisitor.java 
you should override the methods.

*/

import java.util.ArrayList;
import java.util.List;

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
        AstNode value = visit(ctx.expr(0));
        AstNode next = visit(ctx.expr(1));
        return new AsgnNode(id, value, next);
    }

    @Override
    public AstNode visitFuncallExpr(ExprParser.FuncallExprContext ctx) {
        String id = ctx.id().getText();
        ArrayList<AstNode> args = new ArrayList<>();

        if (!ctx.expr().isEmpty()){
            for (int i = 0; i < ctx.expr().size(); i++) {
                args.add(visit(ctx.expr(i)));
            }

        }

        return new funcallNode(id, args);
    }

    @Override
    public AstNode visitNormDecl(ExprParser.NormDeclContext ctx) {
        String id = ctx.id(0).getText();
        ArrayList<AstNode> param = new ArrayList<>();
        AstNode expr = visit(ctx.expr());

        for (int i = 1; i < ctx.id().size(); i++) {
            param.add(new IdtNode(ctx.id(i).getText()));
        }
        param.add(expr);    // The last element of param is function body

        return new declNode(id, param, expr);

    }

    @Override
    public AstNode visitNegateExpr(ExprParser.NegateExprContext ctx) {
        return new negateNode(visit(ctx.expr()));
    }
}

