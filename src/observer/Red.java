package observer;

public class Red implements AlarmInterface{

    private int prevTemp;
    @Override
    public void tempChanged(int temp) {

        if(temp<=600 && prevTemp<600){
            System.out.println("RED");

            prevTemp = temp;
        }
    }
}
