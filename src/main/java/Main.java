import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yzhao on 7/27/17.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Map<String, String> originalMap = new HashMap<String, String>();
        originalMap.put("1001", "2");
        originalMap.put("1002", "4");

        System.out.println(preprocess("1", originalMap)); // 1
        System.out.println(preprocess("1+2", originalMap)); // 1+2
        System.out.println(preprocess("1+[1001]", originalMap)); // 1+2
        System.out.println(preprocess("1+[1001]+3", originalMap)); // 1+2+3
        System.out.println(preprocess("1+[1001]+3*[1002]+5", originalMap)); // 1+2+3*4+5


        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = engine.eval(preprocess("1+[1001]+3*[1002]+5", originalMap));
        System.out.println(result.toString());
    }

    public static String preprocess(String expression, Map<String, String> originalMap){
        int n = expression.length();
        char[] str = expression.toCharArray();

        StringBuilder sb = new StringBuilder();

        int startPosition = 0;
        while(startPosition < n){
            IntervalPair mIntervalPair = FindInterval.findNextInterval(str, '[', ']', startPosition);
            int left = mIntervalPair.getLeftPosition();
            int right = mIntervalPair.getRightPosition();

            // append the substring that is not part
            if(right >= n){
                sb.append(expression.substring(startPosition, n));
                break;
            }

            // append the substring that is part
            sb.append(expression.substring(startPosition, left));
            String key = expression.substring(left + 1, right);
            if(key != null && originalMap.containsKey(key)){
                sb.append(originalMap.get(key));
            }
            startPosition = right + 1;
        }

        return sb.toString();
    }
}


