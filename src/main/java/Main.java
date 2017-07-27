import java.util.HashMap;
import java.util.Map;

/**
 * Created by yzhao on 7/27/17.
 */
public class Main {
    public static void main(String[] args){
        preprocess("1+[2]");

    }

    public static String preprocess(String input){
        int n = input.length();
        char[] str = input.toCharArray();

        StringBuilder sb = new StringBuilder();
        Map<String, String> originalMap = new HashMap<String, String>();
        originalMap.put("1001", "2");


        int p_1 = 0;
        int p_2 = 0;
        while(p_1 < n && p_2 < n){
            p_1 = findNext(str, '[', p_2 + 1);
            p_2 = findNext(str, ']', p_1 + 1);
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
