package observer2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
interface Observed {
 void addObserver(Observer o); // добавить объект
 void removeObserver(Observer o); // удалить объект
 void notifyObservers();//увидомить всех
}

/**
 * Наблюдаемый интерфейс
 */
interface Observer {
    void handleEvent(int temp, int presser);
}

/**
 * Издатель
 */
class MetioStation implements Observed {

    //состояние(данные)
    private int temperature;
    private int presser;

    private List<Observer> observer;

    public MetioStation() {
        this.observer = new LinkedList<>();
    }


    public void setMesseges(int temperature, int presser){
        this.temperature = temperature;
        this.presser = presser;
        notifyObservers();
    }


    @Override
    public void addObserver(Observer o) {
        this.observer.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
         this.observer.remove(o);
    }

    @Override
    public void notifyObservers() {

        for (Observer observer : this.observer){
            observer.handleEvent(this.temperature,this.presser);
        }
    }
}

/**
 * Наблюдатели:
 */

/**
 * Наблюдатель 1:
 */

class A implements Observer {
    @Override
    public void handleEvent(int temp, int presser) {
        System.out.println("Температура изменилась: " + temp + " Давление воздуха: " + presser);
    }
}

/**
 * Наблюдатель 2:
 */

class B implements Observer{
    @Override
    public void handleEvent(int temp, int presser) {

    }
}

/**
 * Новый наблюдатель: который записывает погодные изменения в файл
 */
class FileObserver implements Observer {
    @Override
    public void handleEvent(int temp, int presser) {

        File file;

        try {
            file = new File("result.txt");
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print("Погода изменилась. Температура - " + temp + " Давление - " + presser);
            printWriter.println();
            printWriter.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
class FileTempDir2 implements Observer {
    @Override
    public void handleEvent(int temp, int presser) {
        File file;

        try {

            file = File.createTempFile("temperature","_txt");

            PrintWriter pr = new PrintWriter(file);
            pr.println("Температура воздуха составляет: " + temp + " Давление воздуха: " + presser);
            pr.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
public class Main2 {
    public static void main(String[] args) {

        MetioStation metioStation1 = new MetioStation();//создание издателя
        metioStation1.addObserver(new A()); // добавление подписчика

        FileTempDir2 subsciber01 = new FileTempDir2();

        //metioStation1.addObserver(new FileObserver());// добавление подписчика, который температурные изменения записывает в файл

        metioStation1.addObserver(subsciber01);


        metioStation1.addObserver(new FileTempDir2());
        metioStation1.setMesseges(78,789); // установка температурного режима
        metioStation1.setMesseges(90,456); // изменения температурного режима

        metioStation1.setMesseges(90,88);

        metioStation1.setMesseges(100,1026);

        metioStation1.setMesseges(66,45);


        metioStation1.setMesseges(-12,765);



    }
}
