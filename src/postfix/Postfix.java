package postfix;

import java.util.*;

/**
 *
 * @author Selene
 */
public class Postfix {

    /*public static void main(String[] args) {

        System.out.println("Type a postfix expression (to quit, type q)");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String input; // line input by the user
        boolean quit = false;

        do {
            try {
                System.out.print(">"); // prompt
                input = in.readLine(); // each nos. and operators should be separated by a space
                if (input.equals("q")) {
                    quit = true;
                } else {
                    String postfix = input;
                    System.out.println(computePostfix(postfix));
                }
            } catch (IOException | ExpressionFormatException e) {
                System.out.println("Invalid expression");
            }
        } while (!quit);
    }
    */
    /**
     * Evaluates a postfix expression
     *
     * @param postfix the string that contains the postfix expression
     * @return the integer value of the expression
     * @throws ExpressionFormatException if the postfix expression is invalid
     */
    public  int computePostfix(String postfix) throws ExpressionFormatException {

        try {
            Stack<Integer> stack = new Stack<>();
            StringTokenizer st = new StringTokenizer(postfix);
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (token.equals("*")
                        || // an operator
                        token.equals("+") || token.equals("-")
                        || token.equals("%") || token.equals("/")
                        || token.equals("u+") || token.equals("u-"))
                 {
                    applyOperator(token, stack);
                }
                else if(token.equals("=")){
                    break;
                }
                else if (isChars(token)==true){
                    System.out.println(token);
                    break;
                }
                else { // an operand
                    stack.push(new Integer(token));
                }
            }
            int result = stack.pop();
            if (!stack.isEmpty()) { // the stack should be empty
                throw new ExpressionFormatException();
            }
            return result;
        } catch (EmptyStackException | NumberFormatException ese) {
            throw new ExpressionFormatException();
        }
    }

    /**
     * Applies the given operator to the top operand or the top two operands on
     * the given stack. Possible operators are unary + and - written as "u+" and
     * "u-", and binary "+", "-", "%", and "/"
     *
     * @param operator the operator to apply
     * @param s the stack of the operands
     * @throws EmptyStackException if the stack is empty
     * @throws IllegalArgumentException if the operator is not /,*,%,+,-,u-,u+
     *
     * post condition: the operator is applied to the top operand or to the top
     * two operands on the stack. The operand(s) is/are popped from the stack.
     * The result is pushed on the stack
     */
    public  void applyOperator(String operator, Stack<Integer> s) {
        int op1 = s.pop();
        switch (operator) {
            case "u-":
                s.push(-op1);
                break;
            case "u+":
                s.push(op1);
                break;
            default:
                // binary operator
                int op2 = s.pop();
                int result;
                switch (operator) {
                    case "+":
                        result = op2 + op1;
                        break;
                    case "-":
                        result = op2 - op1;
                        break;
                    case "/":
                        result = op2 / op1;
                        break;
                    case "%":
                        result = op2 % op1;
                        break;
                    case "*":
                        result = op2 * op1;
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                s.push(result);
        }
    }

    public  boolean isChars(String t){
        return Character.isDigit(t.charAt(0))==false;
    }

}
