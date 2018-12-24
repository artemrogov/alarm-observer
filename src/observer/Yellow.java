package observer;


public class Yellow implements AlarmInterface {

    private int prevTemp;

    @Override
    public void tempChanged(int temp) {
        if (temp<=300 && prevTemp<300){
            System.out.print(" Yellow ");

            prevTemp = temp;
        }

    }
}
