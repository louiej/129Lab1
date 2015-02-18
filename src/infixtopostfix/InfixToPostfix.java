/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infixtopostfix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author xiandalisay
 */
public class InfixToPostfix {

    /*
     * Function isOperator
     * This function determines if the character is an arithmetic operator
     * Parameter: char ch
     * Return: Boolean
     */
    private boolean isOperator(char ch) {

        return ch == '+'
                || ch == '-'
                || ch == '*'
                || ch == '/'
                || ch == '%'
                || ch == '^'
                || ch == '('
                || ch == ')'
                || ch == '=';

    }

    /*
     * Function isWhiteSpace
     * This function determines if the character is a whitespace
     * Parameter: char c
     * Return: Boolean
     */
    private boolean isWhiteSpace(char ch) {

        return (ch == ' ');

    }

    /*
     * Function operatorPrecedence
     * This function determines if op1 has a lower precedence than op2.
     * op1 is an operator on the leftside and op2 is an operator on the right side.
     * Both should be an arithmetic operator characters (+,-,*,/,%,^).
     * Parameter: char op1, char op2
     * Return: Boolean
     */
    private boolean operatorPrecedence(char op1, char op2) {

        switch (op1) {

            case '+':
            case '-':
                return !(op2 == '+' || op2 == '-');

            case '*':
            case '/':
            case '%':
                return op2 == '^' || op2 == '(';

            case '^':
                return op2 == '(';

            case '=':
            case '(':
                return true;

            // no precedence must arise 
            default:
                return false;
        }

    }

    /*
     * Function convertToPostFix
     * This function converts the string from infix notation to postfix notation
     * parameters: String infix
     * Returns: String postfix
     */
    @SuppressWarnings("empty-statement")
    public String convertToPostfix(String infix) {

        //The stack of operators to be used
        Stack operatorStack = new Stack();
        //Initialization of the first character of a token
        char ch;
        //StringTokenizer for the input string
        StringTokenizer parser;
        parser = new StringTokenizer(infix, "+-*/%^() ", true);
        //StringBuilder for the output string
        StringBuilder postfix;
        postfix = new StringBuilder(infix.length());

        // Process the tokens for conversion
        while (parser.hasMoreTokens()) {
            //gets the next token and let ch be the first character of this token
            String token = parser.nextToken();
            ch = token.charAt(0);

            // if token is an arithmetic operator
            if ((token.length() == 1) && isOperator(ch)) {
                // Operator on the stack does not have lower precedence
                while (!operatorStack.empty()
                        && !operatorPrecedence(((String) operatorStack.peek()).charAt(0), ch)) {
                    postfix.append(" ").append((String) operatorStack.pop());
                }

                if (ch == ')') {
                    // Output the remaining operators in the parenthesized part.
                    String operator = (String) operatorStack.pop();
                    while (operator.charAt(0) != '(') {
                        postfix.append(" ").append(operator);
                        operator = (String) operatorStack.pop();
                    }
                } else {
                    // Push this operator onto the stack.
                    operatorStack.push(token);
                }
            } else if ((token.length() != 1) || !isWhiteSpace(ch)) {
                // outputs the operand
                postfix.append(" ").append(token);
            } else {
                // character is a whitespace
                ;
            } // character is an operand

        }

        // Output the remaining operators on the stack.
        while (!operatorStack.empty()) {
            postfix.append(" ").append((String) operatorStack.pop());
        }

        // Returns the result which is the postfix result.
        return postfix.toString();

    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String[] testString
                = {
                    "b = 1 + 3",
                    "2 + 3 * 4 / 5",
                    "a + b * asd / operator",
                    "2*3 - 4 + 5 / 6",
                    " 35 - 42* 17 /2 + 10",
                    " 33.2 - 17.5 * 2.0 ^ 3.2",
                    "3 * (4 + 5)",
                    "asd = 3 * ( 4 - (5+2))/(2+3)"
                };

        InfixToPostfix converter = new InfixToPostfix();

        /*
         FileWriter fwriter;
         fwriter = new FileWriter("F:\\new.out");
         BufferedWriter bwrite;
         bwrite = new BufferedWriter(fwriter);
        
         */
        System.out.println("\nTest for convertToPostfix:\n");

        for (String testString1 : testString) {
            System.out.println("infix: " + testString1);

            String line;
            line = converter.convertToPostfix(testString1);
            System.out.println("postfix: " + line);

            /*
             bwrite.write(line);
             bwrite.flush();
             bwrite.newLine();
             */
            System.out.println();
        }
    }

}
