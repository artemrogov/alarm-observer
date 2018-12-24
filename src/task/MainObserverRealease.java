package task;


import java.util.LinkedList;
import java.util.List;

interface Alarm {
    void tempChanged(int temp);
}
interface Observed {

    void addObserver(Alarm observer);
    void removeObserver(Alarm observer);
    void notifyObservers();

}

class Sensor implements Observed{

    private List<Alarm> alarm;
    private int temperature;

    public Sensor() {
        alarm = new LinkedList<>();
    }

    public void setTemperature(int temp){
        this.temperature = temp;
        notifyObservers();
    }

    public int getTemperature(){
        return this.temperature;
    }

    @Override
    public void addObserver(Alarm observer) {
        this.alarm.add(observer);
    }

    @Override
    public void removeObserver(Alarm observer) {
        this.alarm.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Alarm alm:alarm){
            alm.tempChanged(temperature);
        }
    }

}
class Green implements Alarm {

    @Override
    public void tempChanged(int temp) {
        if (temp>=100){
            System.out.print(" Green ");

        }

    }
}

class Yellow implements Alarm {

    @Override
    public void tempChanged(int temp) {
        if (temp>=300){
            System.out.print(" Yellow ");
        }

    }
}

class Red implements Alarm {



    @Override
    public void tempChanged(int temp) {
         if (temp>=600){
             System.out.print(" RED ");

         }
         System.out.println();


    }
}

public class MainObserverRealease {

    public static void main(String[] args) {

        Sensor sensor = new Sensor();

        Green greenSignal = new Green();
        Yellow yellowSignal = new Yellow();
        Red redSignal = new Red();

        sensor.addObserver(greenSignal);
        sensor.addObserver(yellowSignal);
        sensor.addObserver(redSignal);

        for (int i = 0; i<=600;i+=100){
            sensor.setTemperature(i);
            System.out.println("текущая температура: " + sensor.getTemperature());
        }


    }

}
