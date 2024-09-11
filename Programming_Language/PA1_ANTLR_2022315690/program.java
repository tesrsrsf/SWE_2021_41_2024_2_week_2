import java.io.IOException;
import org.antlr.v4.runtime.*;
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
        HashMap<String, Double> content = new HashMap<>();
        BuildAstVisitor buildAstVisitor = new BuildAstVisitor();


        // Print AST
        // Print built AST with AstCall.java
        for (ExprParser.ExprContext exprContext : parserContext.expr()) {
            AstNode astRoot = buildAstVisitor.visit(exprContext);
            AstCall.Call(astRoot, 0);

        }

        // Print Result
        for (ExprParser.ExprContext exprContext : parserContext.expr()) {
            AstNode astRoot = buildAstVisitor.visit(exprContext);
            double result = astRoot.calcExpr(content);
            System.out.println(result);
        }


	}
}
