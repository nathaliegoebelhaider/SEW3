package Theorie;

import javax.swing.*;
import java.util.Scanner;

/**
 * Created by Nathalie on 19.05.2017.
 * <br> Diese KLasse demonstriert Benutzereingaben via Konsole
 * <br> Verwendet die Klasse {@link Scanner}
 */
public class JavaDoc {

    public static void main(String[] args) {

    }

    /**
     * Diese Methode ermoeglicht eine Benurteingabe in der Konsole.
     * <br> Verwendet wird die Methode {@link Scanner#nextInt()} der Klasse {@link Scanner}
     * <br> bla bla bla
     * @param info Text zur Eingabeaufforderung
     * @return liefert die eingebene Zahl
     * @see java.io.BufferedReader
     */
    public static int readData(String info){
        System.out.println(info);
        Scanner scanner = new Scanner(System.in);

        int erg = scanner.nextInt();

        return erg;
    }
}
