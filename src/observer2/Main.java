package observer2;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
interface MyObserver {
    void update(float temperature, float humidity, int pressure);
}
interface Observable{
    void registerObserver(MyObserver o);
    void removeObserver(MyObserver o);
    void notifyObservers(); // уидомить Обсерверы
}
class WeatherData implements  Observable {

    private List<MyObserver> observers;
    private float temperature;
    private float humidity;
    private int pressure;

    public WeatherData() {
        this.observers = new LinkedList<>();
    }

    @Override
    public void registerObserver(MyObserver o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(MyObserver o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (MyObserver observer :this.observers){
            observer.update(temperature,humidity,pressure);
        }
    }

    public void setMeasurements(float temperature, float humidity, int pressure){
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        notifyObservers();
    }



}

class CurrentConditionsDisplay  implements MyObserver {

    private float temperature;
    private float humidity;
    private int pressure;

    private WeatherData weatherData;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.weatherData.notifyObservers();
    }

    @Override
    public void update(float temperature, float humidity, int pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display(){
        System.out.printf("Сейчас значения:%.1f градусов цельсия и %.1f %% влажности. Давление %d мм рт. ст.\n", temperature, humidity, pressure);
    }

}

public class Main {
    public static void main(String[] args) {

        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(29f,65f,745);
        weatherData.setMeasurements(39f,55f,890);
        weatherData.setMeasurements(23f,21f,789);
       // currentConditionsDisplay.update(12f,45f,300);

        weatherData.setMeasurements(78f,7f,900);

        currentConditionsDisplay.display();

    }
}
