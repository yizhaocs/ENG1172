import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Arrays;
import java.util.Stack;

/**
 * The expression string may contain decimal digit, open ( and closing parentheses ), the plus + or minus sign - or multiplication sign * or division sign /, non-negative integers and empty spaces .
 */
public class Calculator {
    public static void main(String[] args) throws Exception{

        //System.out.println(result.toString());
        System.out.println(calculate("1.0+2.1")); // 3.0
        System.out.println(calculate("1+2+1+2*-6")); // 3.0
        System.out.println(calculate("1+2+1+2*-6")); // 3.0
    }





    public static double calculate(String expression) throws  Exception{
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = engine.eval(expression);

        return Double.valueOf(result.toString());

    }

    private static boolean isOperator(char c){
        return c == '+' || c == '-'|| c == '*'|| c == '/'|| c == '('|| c == ')'|| c == '['|| c == ']';
    }

    private static boolean isValidNumber(char c){
        return Character.isDigit(c) || c == '.';
    }


}