package anoh.talat.parcattraction.internals;

public class Billetterie {
    private static final int STOCK_INIT = 20;
    private boolean idle;

    private int stockDisponible;

    public Billetterie() {
        stockDisponible = STOCK_INIT;
        idle = true;
    }

    synchronized void vendreBillet(int nbreBillet) {
        while (stockDisponible < nbreBillet) {
            idle = false;
            notifyAll();
            try {
                System.out.printf("Un client est en attente d'achat de %d billets%n", nbreBillet);
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        stockDisponible -= nbreBillet;
        System.out.printf("La billetterie a vendu %d billets%n", nbreBillet);
    }

    synchronized void ajouterTicket(int nbreTicket) {
        while (idle) {
            try {
                System.out.println("Le responsable billetterie en attente");
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        idle = true;
        stockDisponible += nbreTicket;
        System.out.println("Billetterie alimenter de " + nbreTicket + " billets");
        notifyAll();
    }
}
