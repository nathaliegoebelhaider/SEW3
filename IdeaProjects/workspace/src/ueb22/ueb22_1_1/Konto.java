package ueb22.ueb22_1_1;

/**
 * Created by Nathalie on 24.04.2017.
 */
class Konto {

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
    public void add(int v){
        int wert = getKontostand();
        wert = wert + kontostand;
        setKontostand (wert);
    }
}
