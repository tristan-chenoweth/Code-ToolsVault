package Java.JavaToAssembly;

// J2ASM Translator

import java.util.HashMap;
import java.util.Map;

public class J2ASMTranslator {
    private static class JavaToAssemblyConverter {
        public String convertVariable(String javaCode) {
            // Convert Java variable declarations to assembly
            if (javaCode.startsWith("int")) {
                return convertIntDeclaration(javaCode);
            }
            return "";
        }

        private String convertIntDeclaration(String javaCode) {
            // Example: "int x = 5;" to assembly
            String[] parts = javaCode.split("=");
            String varName = parts[0].split(" ")[1].trim();
            String value = parts[1].replace(";", "").trim();
            
            return String.format("""
                section .data
                    %s dd %s
                """, varName, value);
        }

        public String convertArithmetic(String javaCode) {
            // Convert basic arithmetic operations
            if (javaCode.contains("+")) {
                return convertAddition(javaCode);
            }
            return "";
        }

        private String convertAddition(String javaCode) {
            // Example: "x = x + 1;" to assembly
            String[] parts = javaCode.split("=");
            String target = parts[0].trim();
            String[] operands = parts[1].replace(";", "").split("\\+");
            
            return String.format("""
                mov eax, [%s]
                add eax, %s
                mov [%s], eax
                """, operands[0].trim(), operands[1].trim(), target);
        }
    }

    public static void main(String[] args) {
        JavaToAssemblyConverter converter = new JavaToAssemblyConverter();
        
        // Example usage
        String javaCode = "int x = 5;";
        String assembly = converter.convertVariable(javaCode);
        System.out.println("Java code:\n" + javaCode);
        System.out.println("Assembly code:\n" + assembly);

        javaCode = "x = x + 1;";
        assembly = converter.convertArithmetic(javaCode);
        System.out.println("\nJava code:\n" + javaCode);
        System.out.println("Assembly code:\n" + assembly);
    }
}