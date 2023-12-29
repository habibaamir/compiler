package implementation;

import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {
    // Define symbol
    public enum symbolType {
        Integer,
        Identifier,
        add,
        sub,
        Muti,
        Divide,
        Assign,
        Semic,
    }

    public static class symbol {
        private symbolType type;
        private String lex;

        public symbol(symbolType type, String lex) {
            this.type = type;
            this.lex = lex;
        }
        public symbolType getType() {
        return type;
    }

    public String getLexeme() {
        return lex;
    }

    }

    public List<symbol> tokenize(String sourceCode) {
        List<symbol> symbols = new ArrayList<>();
        StringBuilder lexcal = new StringBuilder();
    
        for (int i = 0; i < sourceCode.length(); i++) {
            char currentChar = sourceCode.charAt(i);
    
            if (Character.isWhitespace(currentChar)) {
                continue;
            }
            lexcal.append(currentChar);
            String lexeme = lexcal.toString();
    
            if (lexeme.matches("\\d+")) {
                // Integer literal token
                symbols.add(new symbol(symbolType.Integer, lexeme));
                lexcal.setLength(0);  // Reset the lexeme builder
            } else if (lexeme.matches("[a-zA-Z][a-zA-Z0-9]*")) {
                // Identifier token
                symbols.add(new symbol(symbolType.Identifier, lexeme));
                lexcal.setLength(0);
            } else if (lexeme.equals("+")) {
                symbols.add(new symbol(symbolType.add, lexeme));
                lexcal.setLength(0);
            } else if (lexeme.equals("-")) {
                symbols.add(new symbol(symbolType.sub, lexeme));
                lexcal.setLength(0);
            } else if (lexeme.equals("*")) {
                symbols.add(new symbol(symbolType.Muti, lexeme));
                lexcal.setLength(0);
            } else if (lexeme.equals("/")) {
                symbols.add(new symbol(symbolType.Divide, lexeme));
                lexcal.setLength(0);
            } else if (lexeme.equals("=")) {
                symbols.add(new symbol(symbolType.Assign, lexeme));
                lexcal.setLength(0);
            } else if (lexeme.equals(";")) {
                symbols.add(new symbol(symbolType.Semic, lexeme));
                lexcal.setLength(0);
            } else {
                // Invalid token
                lexcal.setLength(0);
               System.out.println("invalid symol");
            }
        }
    
        return symbols;
    }
    public static void main(String[] args) {
        LexicalAnalyzer lexer = new LexicalAnalyzer();
        List<LexicalAnalyzer.symbol> symbols = lexer.tokenize(" : x = 10 + 5 * 4 - 8 / 6;");
        for (LexicalAnalyzer.symbol token : symbols) {
            System.out.println(token.getType() + ": " + token.getLexeme());
        }
    }
  }

