package Theorie;

import java.util.Date;

/**
 * Created by Nathalie on 21.04.2017.
 */
public class Threads {
    public static void main(String[] args) {
        DateThread dt1 = new DateThread("dateThread");
        dt1.start();

       // new DateThread().start();   //Anonymer Start


        Thread ct1 = new Thread(new CounterThread(), "counterThread");
        ct1.start();

        try {
            dt1.join();
            ct1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("alles erledigt");
    }
   synchronized public  void iwas(){
        //beide Threads greifen auf diese Methode zu
        //synchronized: System verhindert das mehere Threads auf diese Methode zugreifen können
    }

    public void iwasandere(){
       Date i = new Date();
       synchronized (i){

       }
    }
}

class DateThread extends Thread {

    public DateThread  (String name){
        super(name);
    }
    public void run(){
        for (int i = 0; i < 20 ; i++) {
            System.out.println(getName() + ": " + new Date());
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class CounterThread implements Runnable {

    @Override
    public void run() {
       String name = Thread.currentThread().getName();
        for (int i = 0; i < 20 ; i++) {
            System.out.println(name + ": " +i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}