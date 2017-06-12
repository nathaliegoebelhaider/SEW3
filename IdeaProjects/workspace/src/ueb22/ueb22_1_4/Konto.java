package ueb22.ueb22_1_4;

/**
 * Created by Nathalie on 24.04.2017.
 */
public class Konto {
    int kontostand;

    Konto(){
        this.kontostand=0;
    }

    int getKontostand(){
        return kontostand;
    }

    void setKontostand(int betrag){
        kontostand = betrag;
    }
    public synchronized void add(int v){
        int wert = getKontostand();
        wert = wert + kontostand;
        setKontostand (wert);
    }
}
