package ueb22.ueb22_1_2;

/**
 * Created by Nathalie on 24.04.2017.
 */
public class Bank {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Konto a = new Konto();
        Konto b = new Konto();
        Konto c = new Konto();

        Ueberweiser ab = new Ueberweiser(a,b);
        Ueberweiser bc = new Ueberweiser(b,c);
        Ueberweiser ca = new Ueberweiser(c,a);

        ab.start();
        bc.start();
        ca.start();

        try {
            ca.join();
            ab.join();
            bc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getKontostand());
        System.out.println(b.getKontostand());
        System.out.println(c.getKontostand());
        System.out.println(System.currentTimeMillis() - start);
    }
}
