import java.util.Arrays;
import java.util.Stack;

/**
 * The expression string may contain decimal digit, open ( and closing parentheses ), the plus + or minus sign - or multiplication sign * or division sign /, non-negative integers and empty spaces .
 */
public class Calculator {
    public static void main(String[] args){
        System.out.println(infixToPostfix("1+2")); // 3.0
        System.out.println(infixToPostfix("1+2+3")); // 3.0
        System.out.println(infixToPostfix("1*2+3")); // 3.0
        //System.out.println(infixToPostfix("(1+2.1)+1")); // 3.0


        System.out.println(evalRPN(new String[]{"1", "2", "3", "+", "*"})); // 9.0
        System.out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"})); // 6.6


    }





    public static String infixToPostfix(String s) {
        int n = s.length();
        StringBuilder tokens = new StringBuilder();
        Stack<Character> leftStack_operator = new Stack<Character>(); // store operator
        int right = 0;
        while (right < n) {
            char c = s.charAt(right);
            if (isOper(c + "")) {
                while (!leftStack_operator.isEmpty() && isOper(leftStack_operator.peek() + "") && getPrecedence(leftStack_operator.peek()) >= getPrecedence(c)) {
                    tokens.append(" ");
                    tokens.append(leftStack_operator.pop());
                }
                tokens.append(" ");
                leftStack_operator.push(c);
            } else if (c != ' ') {
                tokens.append(c);
            }
            right++;
        }

        // empty stack
        if (!leftStack_operator.isEmpty()) {
            tokens.append(" ");
            tokens.append(leftStack_operator.pop());
        }

        String[] postfix = tokens.toString().split(" ");
        System.out.println("Postfix:" + Arrays.toString(postfix));
        return Double.toString(evalRPN(postfix));
    }


    /*
        ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
        ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6.6
    */
    public static Double evalRPN(String[] postfix) {

        int n = postfix.length;
        if (n == 0) {
            return -1.0;
        }
        Stack<Double> leftStack = new Stack<Double>();
        int right = 0;
        while (right < n) {
            String s = postfix[right];
            if (!isOper(s) && !s.equals("")) {
                leftStack.push(Double.valueOf(s));
            } else {
                // true if no matching left
                if (leftStack.isEmpty()) {
                    // do nothing
                } else {
                    double b = leftStack.pop();
                    double a = leftStack.isEmpty() ? 0.0 : leftStack.pop();
                    if (s.equals("+")) {
                        leftStack.push(a + b);
                    } else if (s.equals("-")) {
                        leftStack.push(a - b);
                    } else if (s.equals("*")) {
                        leftStack.push(a * b);
                    } else if (s.equals("/")) {
                        if (b == 0) {
                            leftStack.push(0.0);
                        } else {
                            leftStack.push(a / b);
                        }
                    }
                }
            }
            right++;
        }
        if (leftStack.isEmpty()) {
            return 0.0;
        } else {
            return leftStack.pop();
        }
    }

    private static boolean isOper(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    private static int getPrecedence(char c) {
        // c == '+' || c == '-' return 1
        // c == '*' || c == '/' return 2;
        return c == '+' || c == '-' ? 1 : 2;
    }


}