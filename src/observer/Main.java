package observer;

public class Main {

    public static void main(String[] args) {

      Sensor sensor1 = new Sensor();
      sensor1.addAlarm(new Green());
      sensor1.addAlarm(new Yellow());
      sensor1.addAlarm(new Red());


      for (int i = 0; i<1000; i+=100){
          sensor1.changeTemp(i);
      }
      sensor1.changeTemp(100);
      sensor1.changeTemp(500);

      

    }
}
