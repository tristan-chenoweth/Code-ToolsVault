package Java.J2ASMTranslator;

// Built with help from AI

import java.util.*;
import java.io.*;

public class J2ASMTranslator {

    public static void main(String[] args) {
        String javaCode = """
                public class calculator {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(System.lineSeparator());
        int option;
        // Prompt the user for calculation option
        System.out.println("Choose from the following operations (Enter number of selection):");
        System.out.println("0. Quit Program \n1. Addition \n2. Subtraction \n3. Multiplication \n4. Division \n5. Modulus \n6. Exponential \n7. Root Function");
        System.out.print("Option: ");

        // Set input to calculator option
        option = scanner.nextInt();

        // Create switch to handle user input to start calculation
        switch (option) {
            case 1:
                // Code for handiling addition option

                // Open scanner for addition
                Scanner AddScanner = new Scanner(System.in);

                // Collet users first number
                System.out.print("Enter your first number: ");
                double addNum1 = AddScanner.nextDouble();
                
                // Collet users second number
                System.out.print("Enter your second number: ");
                double addNum2 = AddScanner.nextDouble();

                // Pass users numbers to addition class for calculation and print
                System.out.print("Addition of " + addNum1 + " and " + addNum2 + " is equal to: ");
                System.out.print(addition(addNum1,addNum2));
                System.out.println();

                // Close AddScanner to free up system resources
                AddScanner.close();
                break;

            case 2:
                // Code for handiling subtraction option

                // Open scanner for subtraction
                Scanner subScanner = new Scanner(System.in);

                // Collet users first number
                System.out.print("Enter your first number: ");
                double subNum1 = subScanner.nextDouble();

                // Collet users second number
                System.out.print("Enter your second number: ");
                double subNum2 = subScanner.nextDouble();
                
                // Pass users numbers to subtraction class for calculation and print
                System.out.print("Subtraction of " + subNum1 + " and " + subNum2 + " is equal to: ");
                System.out.print(subtraction(subNum1,subNum2));
                System.out.println();

                // Close SubScanner to free up system resources
                subScanner.close();
                break;

            case 3:
                // Code for handiling subtraction option

                // Open scanner for subtraction
                Scanner multiScanner = new Scanner(System.in);

                // Collet users first number
                System.out.print("Enter your first number: ");
                double mulNum1 = multiScanner.nextDouble();

                // Collet users second number
                System.out.print("Enter your second number: ");
                double mulNum2 = multiScanner.nextDouble();
                
                // Pass users numbers to subtraction class for calculation and print
                System.out.print("Multiplication of " + mulNum1 + " and " + mulNum2 + " is equal to: ");
                System.out.print(multiplication(mulNum1,mulNum2));
                System.out.println();

                // Close SubScanner to free up system resources
                multiScanner.close();
                break;

            case 4:
                // Code for handling Division option
                
                // Open scanner for division
                Scanner divScanner = new Scanner(System.in);

                // Collect users first number
                System.out.print("Enter your first number: ");
                double divNum1 = divScanner.nextDouble();

                // Coolect users second number
                System.out.print("Enter your second number: ");
                double divNum2 = divScanner.nextDouble();

                // Check for Divide by 0
                if (divNum2 != 0) {
                    // Pass numbers to division class for calculation and print
                    System.out.print("Division of " + divNum1 + " by " + divNum2 + " is equal to: ");
                    System.out.println(division(divNum1,divNum2));
                    System.out.println();

                }
                else {
                    // Inform user that dividing by zero is not defined. 
                    System.out.println("Can not Divide by 0!");
                    System.out.println("Restarting Program...");
                    main(args);
                    System.out.println();
                    
                    // Close scanner before program exit 
                    divScanner.close();
                    break;
                }

                // Close divScanner to free up system resources
                divScanner.close();
                break;
                
            case 5:
                // Code for handling Modulus option
                
                // Open scanner for modulus 
                Scanner modScanner = new Scanner(System.in);

                // Collet users first number
                System.out.print("Enter your first number: ");
                int modNum1 = modScanner.nextInt();

                // Collet users second number
                System.out.print("Enter your second number: ");
                int modNum2 = modScanner.nextInt();

                // Pass numbers to modulus class for calculation and print
                System.out.print("Modulus of " + modNum1 + " by " + modNum2 + " has remainder of: ");
                System.out.println(modulus(modNum1,modNum2));
                System.out.println();

                // Close modScanner to free up system resources
                modScanner.close();
                break;

            case 6:
                // Code for handling Exponential option
                
                // Open scanner for Exponential
                Scanner expScanner = new Scanner(System.in);

                // Collect users first number
                System.out.print("Enter your first number: ");
                double expNum1 = expScanner.nextDouble();

                // Collect users second number
                System.out.print("Enter you second number: ");
                double expNum2 = expScanner.nextDouble();

                // Pass numbers to exponential class for calculation and print
                System.out.print("Exponential of " + expNum1 + " raised to the " + expNum2 + " power is: ");
                System.out.println(exponential(expNum1,expNum2));

                //close expScanner to free up system resources
                expScanner.close();
                break;

            case 7:
                // Code for handling Sqaure Root option
                
                // Open scanner for sqaure root
                Scanner rootScanner = new Scanner(System.in);
                
                // Collect users first number
                System.out.print("Enter your first number: ");
                double rootNum1 = rootScanner.nextDouble();

                // Collect users second number
                System.out.print("Enter you second number: ");
                int rootNum2 = rootScanner.nextInt();

                // Pass numbers to exponential class for calculation and print

                // Check for the root by 0
                if (rootNum2 != 0) {
                    System.out.print("The root of " + rootNum1 + " rooted by " + rootNum2 + " is: ");
                    System.out.println(root(rootNum1,rootNum2));
                } else {
                    System.out.println("Can not root by 0!");
                    System.out.println("Restarting Program...");
                    main(args);
                }

                //close expScanner to free up system resources
                rootScanner.close();
                break;

            case 0:
                // Code to exit program.
                System.out.println("Ending Program...");
                
                // Pass exit code to JVM to terminate program
                System.exit(0);
                break;

            default:
                // Default handler to restart program on invalid entry
                System.out.println("Invalid option.");
                System.out.println("Restarting Program...");
                main(args);
                break;
        }
        scanner.close();
    }

    // Private addition class for handling addition option
    private static double addition(double addNum1, double addNum2) {

        // Create variable to hold calculated number
        double addSolve = addNum1 + addNum2;

        // Return calculated number to method call
        return addSolve;
    }

    // Private subtraction class for handling subtraction option
    private static double subtraction(double subNum1, double subNum2) {

        // Create variable to hold calculated number
        double subSolve = subNum1 - subNum2;

        // Return calculated number to method call
        return subSolve;
    }

    // Private multiplication class for handling multiplication option
    private static double multiplication(double mulNum1, double mulNum2) {

        // Create variable to hold calculated number
        double multSolve = mulNum1 * mulNum2;

        // Return calculated number to method call
        return multSolve;
    }

    // Private division class for handling division option
    private static double division(double divNum1, double divNum2) {

        // Create variable to hold calculated number
        double divSolve = divNum1 / divNum2;

        // Return calculated number to method call
        return divSolve;
    }

    // Private modulus class for handling modulus option
    private static int modulus(int modNum1, int modNum2) {

        // Create variable to hold calculated number
        int modSolve = modNum1 % modNum2;

        // Return calculated number to method call
        return modSolve;
    }
    
    // Private exponential class for handling exponential option
    private static double exponential(double expNum1, double expNum2) {

        // Create variable to hold calculated number
        double expSolve = Math.pow(expNum1,expNum2);

        // Return calculated number to method call
        return expSolve;
    }

    // Private root class for handling root option
    private static double root(double rootNum1, double rootNum2) {
        
        // Create variable to hold calculated number
        double rootSolve = Math.pow(rootNum1, (1/rootNum2));
        
        // Return calculated number to method call
        return rootSolve;
    }
}
                """;
        /*
        String javaCode = """
            public class Example {
                public static void main(String[] args) {
                    int x = 5;
                    int y = 10;
                    int z = x + y;
                }
            }
            """;
        */
    
        System.out.println("Input Java code:");
        System.out.println(javaCode);
        System.out.println("\nStarting translation...");
    
        J2ASMTranslator translator = new J2ASMTranslator();
        String assembly = translator.translate(javaCode);
        
        System.out.println("\nGenerated Assembly:");
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
            System.out.println("Parsing Java code...");
            System.out.println("\nTokenizing...");
            List<Token> tokens = parser.tokenize(javaCode);
            System.out.println("Tokens generated: " + tokens.size());
            
            AST ast = parser.parse(javaCode);
            
            System.out.println("Generating assembly...");
            String assembly = generator.generate(ast, symbolTable);
            
            System.out.println("Optimizing assembly...");
            String optimizedAssembly = optimizer.optimize(assembly);
            
            return optimizedAssembly;
        } catch (Exception e) {
            System.err.println("Translation error:");
            e.printStackTrace();
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

    // Make tokenize method public
    public List<Token> tokenize(String code) {
        List<Token> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(code, " \t\n\r\f(){};=+-*/", true);
        
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (!token.isEmpty()) {
                TokenType type = determineTokenType(token);
                tokens.add(new Token(type, token));
            }
        }
        
        return tokens;
    }

    public AST parse(String javaCode) throws Exception {
        tokens = tokenize(javaCode);
        currentPosition = 0;

        // Debug print
        System.out.println("Tokens:");
        for (Token t : tokens) {
            System.out.println(t.type + ": " + t.value);
        }

        ASTNode root = new BasicASTNode("PROGRAM", "root");
        
        while (currentPosition < tokens.size()) {
            Token token = tokens.get(currentPosition);
            
            // Handle variable declarations
            if (token.type == TokenType.KEYWORD && 
                (token.value.equals("int") || token.value.equals("double"))) {
                ASTNode node = parseVariableDeclaration();
                if (node != null) {
                    root.addChild(node);
                }
            } else {
                currentPosition++;
            }
        }

        return new AST(root);
    }

    private ASTNode parseVariableDeclaration() throws Exception {
        String dataType = tokens.get(currentPosition++).value;
        
        if (currentPosition >= tokens.size()) {
            throw new Exception("Unexpected end of input after type");
        }
        
        String identifier = tokens.get(currentPosition++).value;
        String initialValue = null;
        
        // Check for initialization
        if (currentPosition < tokens.size() && tokens.get(currentPosition).type == TokenType.EQUALS) {
            currentPosition++; // Skip equals
            if (currentPosition >= tokens.size()) {
                throw new Exception("Unexpected end of input after equals sign");
            }
            
            // Handle expression
            StringBuilder valueBuilder = new StringBuilder();
            while (currentPosition < tokens.size() && 
                   tokens.get(currentPosition).type != TokenType.SEMICOLON) {
                valueBuilder.append(tokens.get(currentPosition++).value);
            }
            initialValue = valueBuilder.toString();
        }
        
        // Skip semicolon
        if (currentPosition < tokens.size() && tokens.get(currentPosition).type == TokenType.SEMICOLON) {
            currentPosition++;
        }
        
        return new VariableDeclarationNode(dataType, identifier, initialValue);
    }

    private TokenType determineTokenType(String token) {
        if (token.matches("\\d+")) return TokenType.NUMBER;
        if (isKeyword(token)) return TokenType.KEYWORD;
        if (token.matches("[a-zA-Z][a-zA-Z0-9]*")) return TokenType.IDENTIFIER;
        if (token.equals("=")) return TokenType.EQUALS;
        if (token.equals(";")) return TokenType.SEMICOLON;
        if (token.equals("+") || token.equals("-") || 
            token.equals("*") || token.equals("/")) return TokenType.OPERATOR;
        if (token.equals("{") || token.equals("}")) return TokenType.SEPARATOR;
        return TokenType.OTHER;
    }

    private boolean isKeyword(String token) {
        String[] keywords = {"public", "class", "static", "void", "main", "int", "double", "return"};
        return Arrays.asList(keywords).contains(token);
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

        generateHeader();
        generateCode(ast.getRoot());
        generateFooter();
        
        return assembly.toString();
    }

    // Add the missing generateCode method
    private void generateCode(ASTNode node) {
        if (node == null) return;

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
            default:
                // Handle unknown node types
                System.out.println("Unknown node type: " + node.getType());
        }

        // Process all child nodes
        for (ASTNode child : node.getChildren()) {
            generateCode(child);
        }
    }

    private void generateHeader() {
        assembly.append("section .data\n");
    }

    private void generateFooter() {
        assembly.append("\nsection .text\n");
        assembly.append("global _start\n");
        assembly.append("_start:\n");
        assembly.append("    mov eax, 1\n");
        assembly.append("    mov ebx, 0\n");
        assembly.append("    int 0x80\n");
    }

    private void generateVariableDeclaration(VariableDeclarationNode node) {
        String value = node.getInitialValue() != null ? node.getInitialValue() : "0";
        assembly.append(String.format("    %s: dd %s\n", node.getIdentifier(), value));
    }

    private void generateMethod(MethodNode node) {
        assembly.append(String.format("%s:\n", node.getValue()));
        assembly.append("    push ebp\n");
        assembly.append("    mov ebp, esp\n");
        assembly.append("    mov esp, ebp\n");
        assembly.append("    pop ebp\n");
        assembly.append("    ret\n");
    }

    private void generateAssignment(JavaParser.AssignmentNode node) {
        assembly.append(String.format("    mov eax, %s\n", node.getExpression()));
        assembly.append(String.format("    mov [%s], eax\n", node.getValue()));
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
