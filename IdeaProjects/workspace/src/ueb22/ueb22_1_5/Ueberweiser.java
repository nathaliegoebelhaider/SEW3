package ueb22.ueb22_1_5;

/**
 * Created by Nathalie on 24.04.2017.
 */
public class Ueberweiser implements Runnable{
    Konto ueberweiser = new Konto();
    Konto empfaenger = new Konto();

    int BETRAG = 10000000;
    int ANZAHL = 1000;
    Ueberweiser(Konto von, Konto nach){
        ueberweiser = von;
        empfaenger = nach;
    }

    public void run(){
        for(int i=0; i < ANZAHL; i++){
            ueberweiser.add(-BETRAG);
            empfaenger.add(BETRAG);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
