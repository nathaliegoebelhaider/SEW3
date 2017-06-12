package ueb22.ueb22_1_1;

/**
 * Created by Nathalie on 24.04.2017.
 */
public class Bank {
    static long start = System.currentTimeMillis();
    public static void main(String[] args) {
        Konto a = new Konto();
        Konto b = new Konto();
        Konto c = new Konto();

        Ueberweiser ab = new Ueberweiser(a,b);
        Ueberweiser bc = new Ueberweiser(b,c);
        Ueberweiser ca = new Ueberweiser(c,a);

        ab.run();
        bc.run();
        ca.run();

        System.out.println(a.getKontostand());
        System.out.println(b.getKontostand());
        System.out.println(c.getKontostand());
        System.out.println(System.currentTimeMillis() - start );
    }
}
