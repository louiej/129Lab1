package postfix;

import java.util.*;

/**
 *
 * @author Selene
 */
public class Postfix {

    HashMap sTable = new HashMap();

    /**
     * Evaluates a postfix expression
     *
     * @param postfix the string that contains the postfix expression
     * @return the integer value of the expression
     * @throws ExpressionFormatException if the postfix expression is invalid
     */
    public int computePostfix(String postfix) {

        Stack<String> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(postfix);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            //System.out.println("token " +token + "\n");
            if (token.equals("*")
                    || // an operator
                    token.equals("+") || token.equals("-")
                    || token.equals("%") || token.equals("/")
                    || token.equals("u+") || token.equals("u-")
                    || token.equals("=")) {
                applyOperator(token, stack);
            } /*
             else if (isChars(token)==true){
             break;
             }
             */ else { // an operand
                stack.push(token);
            }
        }

        String result = stack.pop();

        //System.out.println("peek" + result);
        if (result.contains("[a-zA-Z]+")) {
            if (sTable.containsKey(result)) {
                return (Integer) sTable.get(result);
            } else {
            }
        } else {
            //System.out.println("value" + result);
            return Integer.parseInt(result);
        }
        return 0;

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
    public void applyOperator(String operator, Stack<String> s) {
        int val1 = 0, val2 = 0;
        String op1 = s.pop();
        switch (operator) {
            case "u-":
                s.push("-" + op1);
                break;
            case "u+":
                s.push(op1);
                break;
            default:
                // binary operator
                String op2 = s.pop();
                if (op1.matches("[a-zA-Z]+")) {
                    if (sTable.containsKey(op1)) {
                        val1 = (Integer) sTable.get(op1);
                    } else {

                    }
                } else {
                    //System.out.println("value1" + op1);
                    val1 = Integer.parseInt(op1);
                }
                if (op2.matches("[a-zA-Z]+")) {
                    if (sTable.containsKey(op2)) {
                        //System.out.println("sTable");
                        val2 = (Integer) sTable.get(op2);
                    } else {
                        sTable.remove(op2);
                        sTable.put(op2, val1);
                    }
                } else {
                    //System.out.println("value2" + op2);
                    val2 = Integer.parseInt(op2);
                }
                int result = 0;
                //System.out.println("val1: " + val2 + "val2: " + val1 + "\n");

                switch (operator) {
                    case "=":
                        sTable.put(val2, val1);
                        System.out.println(val2 + "=" + val1);
                        result = val1;
                        break;
                    case "+":
                        System.out.println(val2 + "+" + val1);
                        result = val2 + val1;
                        break;
                    case "-":
                        System.out.println(val2 + "-" + val1);
                        result = val2 - val1;
                        break;
                    case "/":
                        System.out.println(val2 + "/" + val1);
                        result = val2 / val1;
                        break;
                    case "%":
                        System.out.println(val2 + "%" + val1);
                        result = val2 % val1;
                        break;
                    case "*":
                        System.out.println(val2 + "*" + val1);
                        result = val2 * val1;
                        break;
                    default:
                        System.out.println(result + "\n");
                        throw new IllegalArgumentException();
                }
                System.out.println("\n");
                s.push(result + "");
        }
    }

    public boolean isChars(String t) {
        return Character.isDigit(t.charAt(0)) == false;
    }

    public String getKeyValues() {
        /*
         String keyValues = "";
         for (Object key : sTable.keySet()) {
         System.out.println("Key = " + key);
         }

         //iterating over values only
         for (Object value : sTable.values()) {
         System.out.println("Value = " + value);
         }
         */
        Iterator<Map.Entry> entries = sTable.entrySet().iterator();
        String hashData;
        hashData = "\n\nVariables: \n";
        while (entries.hasNext()) {
            Map.Entry entry = entries.next();
            if (entry.getKey().toString().matches("[a-zA-Z]+")) {
                hashData += entry.getKey() + " = " + entry.getValue() + "\n";
            }
        }

        return hashData;
    }

}
