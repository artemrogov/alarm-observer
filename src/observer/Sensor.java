package observer;

import java.util.ArrayList;
import java.util.List;

public class Sensor{

    private List<AlarmInterface> alarms = new ArrayList<>();

    public void addAlarm(AlarmInterface alarm){
        alarms.add(alarm);
    }

    public void notifyAll(int temp){
        for (AlarmInterface alarm :alarms){
            alarm.tempChanged(temp);
        }
    }


    public void changeTemp(int temp) {
        System.out.println("Current temperature: " + temp);
        notifyAll(temp);
    }


}
