package observer;

public class Green implements AlarmInterface{

    private int prevTemp;

    @Override
    public void tempChanged(int temp) {

        if (temp>=100 && prevTemp<100){

            System.out.println("GREEN");

            prevTemp = temp;

        }

    }
}
