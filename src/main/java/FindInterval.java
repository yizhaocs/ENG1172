/**
 * Created by yzhao on 7/27/17.
 */
public class FindInterval {
    public static IntervalPair findNextInterval(char[] str, char leftTarget, char rightTarget, int startPosition){
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
