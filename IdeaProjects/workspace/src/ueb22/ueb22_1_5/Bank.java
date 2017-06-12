package ueb22.ueb22_1_5;

/**
 * Created by Nathalie on 24.04.2017.
 */
public class Bank {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Konto a = new Konto();
        Konto b = new Konto();
        Konto c = new Konto();


        Thread tab = new Thread(new Ueberweiser(a, b));
        Thread tbc = new Thread(new Ueberweiser(b, c));
        Thread tca = new Thread(new Ueberweiser(c, a));

        tab.start();
        tbc.start();
        tca.start();

        System.out.println(a.getKontostand());
        System.out.println(b.getKontostand());
        System.out.println(c.getKontostand());
        System.out.println(System.currentTimeMillis() - start);
    }
}
