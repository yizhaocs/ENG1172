import java.util.HashMap;
import java.util.Map;

/**
 * Created by yzhao on 7/27/17.
 */
public class Main {
    public static void main(String[] args){
        Map<String, String> originalMap = new HashMap<String, String>();
        originalMap.put("1001", "2");

        System.out.println(preprocess("1+[1001]+3", originalMap));

    }

    public static String preprocess(String input, Map<String, String> originalMap){
        int n = input.length();
        char[] str = input.toCharArray();

        StringBuilder sb = new StringBuilder();

        int p_1 = 0;
        int p_2 = 0;
        while(p_1 != -1 && p_2 != -1){
            p_1 = findNext(str, '[', p_2 + 1);
            if(p_1 == -1){
                break;
            }
            sb.append(input.substring(p_2, p_1));
            p_2 = findNext(str, ']', p_1 + 1);
            String key = input.substring(p_1 + 1, p_2);
            if(key != null && originalMap.containsKey(key)){
                sb.append(originalMap.get(key));
            }

            System.out.println("p_1:" + p_1 + "  ,p_2:" + p_2);
            //sb.append(input.substring())
            //originalMap.containsKey()
        }


        return sb.toString();


    }

    private static int findNext(char[] str, char target, int startPosition){
        for(int i = startPosition; i < str.length; i++){
            if(str[i] == target){
                return i;
            }
        }
        return -1;
    }
}
