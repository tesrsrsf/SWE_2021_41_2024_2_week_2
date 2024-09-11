import java.io.IOException;
import org.antlr.v4.runtime.*;

import java.util.ArrayList;
import java.util.HashMap;

public class program {

    public static void main(String[] args) throws IOException {
                
        // Get Lexer
        ExprLexer lexer = new ExprLexer(CharStreams.fromStream(System.in));

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass tokens to parser
        ExprParser parser = new ExprParser(tokens);
        ExprParser.ProgContext parserContext = parser.prog();

        // Build AST
        HashMap<String, Double> valMem = new HashMap<>();
        HashMap<String, ArrayList<AstNode>> funMem = new HashMap<>();
        BuildAstVisitor buildAstVisitor = new BuildAstVisitor();

        ArrayList<Double> declResults = new ArrayList<>();
        ArrayList<Double> exprResults = new ArrayList<>();


        // Calculate Results (also detecting exceptions before printing AST)
        for (ExprParser.DeclContext exprContext : parserContext.decl()) {
            AstNode astRoot = buildAstVisitor.visit(exprContext);
            double result = astRoot.calcExpr(valMem, funMem);
            declResults.add(result);
        }
        for (ExprParser.ExprContext exprContext : parserContext.expr()) {
            AstNode astRoot = buildAstVisitor.visit(exprContext);
            double result = astRoot.calcExpr(valMem, funMem);
            exprResults.add(result);
        }


        // Print AST
        // Print built AST of decl part with AstCall.java
        for (ExprParser.DeclContext exprContext : parserContext.decl()){
            AstNode astRoot = buildAstVisitor.visit(exprContext);
            AstCall.Call(astRoot, 0);

        }

        // Print built AST of expr part with AstCall.java
        for (ExprParser.ExprContext exprContext : parserContext.expr()){
            AstNode astRoot = buildAstVisitor.visit(exprContext);
            AstCall.Call(astRoot, 0);

        }


        // Print results
        for (double x :
                declResults) {
            System.out.println(x);
        }

        for (double x :
                exprResults) {
            System.out.println(x);
        }




	}
}
