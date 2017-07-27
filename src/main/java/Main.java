import java.util.HashMap;
import java.util.Map;

/**
 * Created by yzhao on 7/27/17.
 */
public class Main {
    public static void main(String[] args){
        Map<String, String> originalMap = new HashMap<String, String>();
        originalMap.put("1001", "2");
        originalMap.put("1002", "4");

        System.out.println(preprocess("1", originalMap)); // 1
        System.out.println(preprocess("1+[1001]", originalMap)); // 1+2
        System.out.println(preprocess("1+[1001]+3", originalMap)); // 1+2+3
        System.out.println(preprocess("1+[1001]+3*[1002]+5", originalMap)); // 1+2+3*4+5

    }

    public static String preprocess(String input, Map<String, String> originalMap){
        int n = input.length();
        char[] str = input.toCharArray();

        StringBuilder sb = new StringBuilder();

        int startPosition = 0;
        int previousStopPosition = 0;
        while(startPosition < n){
            IntervalPair mIntervalPair = findNextInterval(str, '[', ']', startPosition);
            int left = mIntervalPair.getLeftPosition();
            int right = mIntervalPair.getRightPosition();

           // System.out.println("left:" + left + "  ,right:" + right);
            if(right >= n){
                sb.append(input.substring(startPosition + 1, n));
                break;
            }
            sb.append(input.substring(startPosition, left));
            String key = input.substring(left + 1, right);
           // System.out.println("key:" + key);
            if(key != null && originalMap.containsKey(key)){
                sb.append(originalMap.get(key));
            }
            startPosition = right;
        }


        return sb.toString();


    }

    private static IntervalPair findNextInterval(char[] str, char leftTarget, char rightTarget, int startPosition){
        int leftPosition = startPosition;
        while(leftPosition < str.length){
            if(str[leftPosition] == leftTarget){
                break;
            }
            leftPosition++;
        }

        int rightPosition = startPosition + 1;
        while(rightPosition < str.length){
            if(str[rightPosition] == rightTarget){
                break;
            }
            rightPosition++;
        }
        return new IntervalPair(leftPosition, rightPosition);
    }
}


