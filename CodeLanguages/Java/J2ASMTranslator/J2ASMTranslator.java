package Java.J2ASMTranslator;

import java.util.*;
import java.io.*;

public class J2ASMTranslator {
    public static void main(String[] args) {
        String javaCode = """
            public class Example {
                public static void main(String[] args) {
                    int x = 5;
                    int y = 10;
                    int z = x + y;
                }
            }
            """;
    
        J2ASMTranslator translator = new J2ASMTranslator();
        String assembly = translator.translate(javaCode);
        System.out.println("Generated Assembly:");
        System.out.println(assembly);
    }
    
    private JavaParser parser;
    private AssemblyGenerator generator;
    private Optimizer optimizer;
    private SymbolTable symbolTable;

    public J2ASMTranslator() {
        this.parser = new JavaParser();
        this.generator = new AssemblyGenerator();
        this.optimizer = new Optimizer();
        this.symbolTable = new SymbolTable();
    }

    public String translate(String javaCode) {
        try {
            // 1. Parse Java code into AST
            AST ast = parser.parse(javaCode);
            
            // 2. Generate initial assembly
            String assembly = generator.generate(ast, symbolTable);
            
            // 3. Optimize assembly
            String optimizedAssembly = optimizer.optimize(assembly);
            
            return optimizedAssembly;
        } catch (Exception e) {
            return "Error during translation: " + e.getMessage();
        }
    }
}

class AST {
    private ASTNode root;

    public AST(ASTNode root) {
        this.root = root;
    }

    public ASTNode getRoot() {
        return root;
    }
}

abstract class ASTNode {
    protected List<ASTNode> children;
    protected String type;
    protected String value;

    public ASTNode(String type, String value) {
        this.type = type;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(ASTNode child) {
        children.add(child);
    }

    public List<ASTNode> getChildren() {
        return children;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}

class BasicASTNode extends ASTNode {
    public BasicASTNode(String type, String value) {
        super(type, value);
    }
}

class VariableDeclarationNode extends ASTNode {
    private String dataType;
    private String identifier;
    private String initialValue;

    public VariableDeclarationNode(String dataType, String identifier, String initialValue) {
        super("VARIABLE_DECLARATION", identifier);
        this.dataType = dataType;
        this.identifier = identifier;
        this.initialValue = initialValue;
    }

    // Add these getter methods
    public String getDataType() {
        return dataType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getInitialValue() {
        return initialValue;
    }

    // Optional: Add setter methods if needed
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setInitialValue(String initialValue) {
        this.initialValue = initialValue;
    }
}

class MethodNode extends ASTNode {
    private String returnType;
    private String methodName;
    private List<String> parameters;

    public MethodNode(String returnType, String methodName) {
        super("METHOD", methodName);
        this.returnType = returnType;
        this.methodName = methodName;
        this.parameters = new ArrayList<>();
    }
}

enum TokenType {
    KEYWORD,
    IDENTIFIER,
    NUMBER,
    OPERATOR,
    SEPARATOR,
    EQUALS,
    SEMICOLON,
    OTHER
}

class Token {
    TokenType type;
    String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }
}

class JavaParser {
    private int currentPosition;
    private List<Token> tokens;

    public AST parse(String javaCode) throws Exception {
        tokens = tokenize(javaCode);
        currentPosition = 0;

        ASTNode root = new BasicASTNode("PROGRAM", "root");
        
        // Skip class declaration
        while (currentPosition < tokens.size() && 
               !tokens.get(currentPosition).value.equals("class")) {
            currentPosition++;
        }
        currentPosition++; // Skip "class" keyword
        
        // Skip class name
        if (currentPosition < tokens.size()) {
            currentPosition++; // Skip class name
        }
        
        // Skip opening brace
        while (currentPosition < tokens.size() && 
               !tokens.get(currentPosition).value.equals("{")) {
            currentPosition++;
        }
        currentPosition++; // Skip "{"

        // Skip method declaration
        while (currentPosition < tokens.size() && 
               !tokens.get(currentPosition).value.equals("main")) {
            currentPosition++;
        }
        
        // Skip until method body
        while (currentPosition < tokens.size() && 
               !tokens.get(currentPosition).value.equals("{")) {
            currentPosition++;
        }
        currentPosition++; // Skip "{"

        // Parse the actual statements
        while (currentPosition < tokens.size() && 
               !tokens.get(currentPosition).value.equals("}")) {
            ASTNode node = parseNextStatement();
            if (node != null) {
                root.addChild(node);
            }
        }

        return new AST(root);
    }

    private List<Token> tokenize(String code) {
        List<Token> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(code, " \t\n\r\f(){};=+-*/", true);
        
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (!token.isEmpty()) {
                tokens.add(new Token(determineTokenType(token), token));
            }
        }
        
        return tokens;
    }

    private TokenType determineTokenType(String token) {
        if (token.matches("\\d+")) return TokenType.NUMBER;
        if (isKeyword(token)) return TokenType.KEYWORD;
        if (token.matches("[a-zA-Z][a-zA-Z0-9]*")) return TokenType.IDENTIFIER;
        if (token.equals("=")) return TokenType.EQUALS;
        if (token.equals(";")) return TokenType.SEMICOLON;
        if (token.equals("+") || token.equals("-") || 
            token.equals("*") || token.equals("/")) return TokenType.OPERATOR;
        return TokenType.OTHER;
    }

    private boolean isKeyword(String token) {
        String[] keywords = {"public", "class", "static", "void", "main", "int", "double", "return"};
        return Arrays.asList(keywords).contains(token);
    }

    private ASTNode parseNextStatement() throws Exception {
        if (currentPosition >= tokens.size()) {
            return null;
        }

        Token token = tokens.get(currentPosition);
        
        switch (token.type) {
            case KEYWORD:
                if (token.value.equals("int") || token.value.equals("double")) {
                    return parseVariableDeclaration();
                }
                currentPosition++;
                return null;
            case IDENTIFIER:
                return parseAssignment();
            default:
                currentPosition++;
                return null;
        }
    }

    private ASTNode parseVariableDeclaration() throws Exception {
        String dataType = tokens.get(currentPosition++).value;
        
        if (currentPosition >= tokens.size()) {
            throw new Exception("Unexpected end of input after type");
        }
        
        String identifier = tokens.get(currentPosition++).value;
        
        if (currentPosition < tokens.size() && tokens.get(currentPosition).type == TokenType.EQUALS) {
            currentPosition++; // Skip equals sign
            
            if (currentPosition >= tokens.size()) {
                throw new Exception("Unexpected end of input after equals sign");
            }
            
            String initialValue = tokens.get(currentPosition++).value;
            
            // Skip semicolon if present
            if (currentPosition < tokens.size() && tokens.get(currentPosition).type == TokenType.SEMICOLON) {
                currentPosition++;
            }
            
            return new VariableDeclarationNode(dataType, identifier, initialValue);
        }
        
        // Skip semicolon if present
        if (currentPosition < tokens.size() && tokens.get(currentPosition).type == TokenType.SEMICOLON) {
            currentPosition++;
        }
        
        return new VariableDeclarationNode(dataType, identifier, null);
    }

    private ASTNode parseAssignment() throws Exception {
        String identifier = tokens.get(currentPosition++).value;
        
        if (currentPosition >= tokens.size() || tokens.get(currentPosition).type != TokenType.EQUALS) {
            throw new Exception("Expected '=' after identifier in assignment");
        }
        
        currentPosition++; // Skip equals sign
        
        if (currentPosition >= tokens.size()) {
            throw new Exception("Unexpected end of input after equals sign");
        }
        
        StringBuilder expression = new StringBuilder();
        
        // Parse the right-hand side of the assignment
        while (currentPosition < tokens.size() && 
               tokens.get(currentPosition).type != TokenType.SEMICOLON) {
            expression.append(tokens.get(currentPosition++).value);
        }
        
        // Skip semicolon if present
        if (currentPosition < tokens.size() && tokens.get(currentPosition).type == TokenType.SEMICOLON) {
            currentPosition++;
        }
        
        return new AssignmentNode(identifier, expression.toString());
    }

    // AssignmentNode inner class
    public static class AssignmentNode extends ASTNode {
        private String identifier;
        private String expression;

        public AssignmentNode(String identifier, String expression) {
            super("ASSIGNMENT", identifier);
            this.identifier = identifier;
            this.expression = expression;
        }

        public String getExpression() {
            return expression;
        }
    }
}

class AssemblyGenerator {
    private StringBuilder assembly;
    private SymbolTable symbolTable;

    public String generate(AST ast, SymbolTable symbolTable) {
        this.assembly = new StringBuilder();
        this.symbolTable = symbolTable;

        // Add initial assembly segments
        generateHeader();
        
        // Generate assembly for each node in the AST
        generateCode(ast.getRoot());
        
        // Add footer
        generateFooter();
        
        return assembly.toString();
    }

    private void generateHeader() {
        assembly.append("section .data\n");
        assembly.append("section .text\n");
        assembly.append("global _start\n\n");
        assembly.append("_start:\n");
    }

    private void generateCode(ASTNode node) {
        switch (node.getType()) {
            case "VARIABLE_DECLARATION":
                generateVariableDeclaration((VariableDeclarationNode)node);
                break;
            case "METHOD":
                generateMethod((MethodNode)node);
                break;
            case "ASSIGNMENT":
                generateAssignment((JavaParser.AssignmentNode)node);
                break;
        }

        for (ASTNode child : node.getChildren()) {
            generateCode(child);
        }
    }

    private void generateAssignment(JavaParser.AssignmentNode node) {
        assembly.append(String.format("\tmov eax, %s\n", node.getExpression()));
        assembly.append(String.format("\tmov [%s], eax\n", node.getValue()));
    }

    private void generateVariableDeclaration(VariableDeclarationNode node) {
        // Generate assembly for variable declaration
        assembly.append(String.format("\t%s dd %s\n", 
            node.getValue(), 
            node.getInitialValue() != null ? node.getInitialValue() : "0"));
    }

    private void generateMethod(MethodNode node) {
        // Generate assembly for method
        assembly.append(String.format("%s:\n", node.getValue()));
        assembly.append("\tpush ebp\n");
        assembly.append("\tmov ebp, esp\n");
        // Generate method body
        assembly.append("\tmov esp, ebp\n");
        assembly.append("\tpop ebp\n");
        assembly.append("\tret\n");
    }

    private void generateFooter() {
        // Exit program
        assembly.append("\tmov eax, 1\n");
        assembly.append("\tmov ebx, 0\n");
        assembly.append("\tint 0x80\n");
    }
}

class Optimizer {
    public String optimize(String assembly) {
        String optimized = assembly;
        
        optimized = removeRedundantMoves(optimized);
        optimized = optimizeArithmetic(optimized);
        optimized = peepholeOptimization(optimized);
        
        return optimized;
    }

    private String removeRedundantMoves(String assembly) {
        // Remove unnecessary move instructions
        String[] lines = assembly.split("\n");
        StringBuilder optimized = new StringBuilder();
        
        for (int i = 0; i < lines.length; i++) {
            if (i < lines.length - 1 && 
                lines[i].contains("mov") && 
                lines[i+1].contains("mov")) {
                // Check for redundant moves
                if (!areMovesRedundant(lines[i], lines[i+1])) {
                    optimized.append(lines[i]).append("\n");
                }
            } else {
                optimized.append(lines[i]).append("\n");
            }
        }
        
        return optimized.toString();
    }

    private boolean areMovesRedundant(String move1, String move2) {
        // Implementation to check if two move instructions are redundant
        return false;
    }

    private String optimizeArithmetic(String assembly) {
        // Optimize arithmetic operations
        return assembly;
    }

    private String peepholeOptimization(String assembly) {
        // Perform peephole optimization
        return assembly;
    }
}

class SymbolTable {
    private Map<String, SymbolInfo> symbols;
    private int currentScope;

    public SymbolTable() {
        this.symbols = new HashMap<>();
        this.currentScope = 0;
    }

    public void addSymbol(String name, String type, int scope) {
        symbols.put(name + "@" + scope, new SymbolInfo(type, scope));
    }

    public SymbolInfo lookupSymbol(String name) {
        for (int i = currentScope; i >= 0; i--) {
            SymbolInfo info = symbols.get(name + "@" + i);
            if (info != null) {
                return info;
            }
        }
        return null;
    }
}

class SymbolInfo {
    String type;
    int scope;

    public SymbolInfo(String type, int scope) {
        this.type = type;
        this.scope = scope;
    }
}