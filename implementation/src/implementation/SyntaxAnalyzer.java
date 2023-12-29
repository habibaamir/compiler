package implementation;

import java.util.ArrayList;
import java.util.List;

public class  SyntaxAnalyzer {
    private List<LexicalAnalyzer.symbol> symbols;
    private int currentTokenIndex;

    public SyntaxAnalyzer(List<LexicalAnalyzer.symbol> symbols) {
        this.symbols = symbols;
        this.currentTokenIndex = 0;
    }

    public void parse() {
        program();
        match(LexicalAnalyzer.symbolType.Semic);
        System.out.println("syntax anlaysis completed successfully.");
    }

    /*private void program() {
        assignment();
        if (hasNextToken()) {
            program();
        }
    }*/
    
    private void program() {
        assign();
        if (hasNextToken() && peekNextToken().getType() == LexicalAnalyzer.symbolType.Semic) {
        match(LexicalAnalyzer.symbolType.Semic);
        if (hasNextToken()) {
            program();
        }
    } 
    }

    private void assign() {
        identifier();
        match(LexicalAnalyzer.symbolType.Assign);
        add_sub();
    }
    
     private void identifier() {
        match(LexicalAnalyzer.symbolType.Identifier);
    }

    private void add_sub() {
        mul_div();
        if (match(LexicalAnalyzer.symbolType.add) || match(LexicalAnalyzer.symbolType.sub)) {
            add_sub();
        }
    }

    private void mul_div() {
        factor();
        if (match(LexicalAnalyzer.symbolType.Muti) || match(LexicalAnalyzer.symbolType.Divide)) {
            mul_div();
        }
    }

    private void factor() {
        if (match(LexicalAnalyzer.symbolType.Integer)) {
            // Integer literal
        } else if (match(LexicalAnalyzer.symbolType.Identifier)) {
            // Identifier
        } else {
            // Handle error or throw an exception
            System.out.println("invalid symol");
        }
    }

   

    private boolean match(LexicalAnalyzer.symbolType expectedType) {
        if (hasNextToken() && peekNextToken().getType() == expectedType) {
            consumeNextToken();
            return true;
        }
         return false;
    }
   //checks if there are more tokens to be processed by comparing the `currentTokenIndex` with the size of the `tokens` list.
    private boolean hasNextToken() {
        return currentTokenIndex < symbols.size();
    }

    private LexicalAnalyzer.symbol peekNextToken() {
        return symbols.get(currentTokenIndex);
    }

    private void consumeNextToken() {
        currentTokenIndex++;
    }

    public static void main(String[] args) {
        LexicalAnalyzer lexer = new LexicalAnalyzer();
        List<LexicalAnalyzer.symbol> symbols = lexer.tokenize("x = 10 + 5;");
        SyntaxAnalyzer syntax = new SyntaxAnalyzer(symbols);
        syntax.parse();
    }
}