/**
 * Created by yzhao on 7/27/17.
 */
public class IntervalPair{
    int leftPosition;
    int rightPosition;

    public IntervalPair(int leftPosition, int rightPosition){
        this.leftPosition = leftPosition;
        this.rightPosition = rightPosition;
    }

    public int getLeftPosition() {
        return leftPosition;
    }

    public void setLeftPosition(int leftPosition) {
        this.leftPosition = leftPosition;
    }

    public int getRightPosition() {
        return rightPosition;
    }

    public void setRightPosition(int rightPosition) {
        this.rightPosition = rightPosition;
    }
}