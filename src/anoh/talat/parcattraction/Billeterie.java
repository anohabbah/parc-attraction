package anoh.talat.parcattraction;

class Billeterie {
    private static final int STOCK_INIT = 20;
    private boolean idle;

    private int stockDisponible;

    Billeterie() {
        stockDisponible = STOCK_INIT;
        idle = true;
    }

    synchronized void vendreBillet(int nbreBillet) {
        while (stockDisponible < nbreBillet) {
            idle = false;
            notifyAll();
            try {
                wait();
                System.out.printf("Un client est en attente d'achat de %d billets%n", nbreBillet);
            } catch (InterruptedException ignored) {
            }
        }
        stockDisponible -= nbreBillet;
        System.out.printf("La billeterie a vendu %d billets%n", nbreBillet);
    }

    synchronized void ajouterTicket(int nbreTicket) {
        while (idle) {
            try {
                wait();
                System.out.println("Le responsable billeterie en attente");
            } catch (InterruptedException ignored) {
            }
        }
        idle = true;
        stockDisponible += nbreTicket;
        System.out.println("Billeterie alimenter de " + nbreTicket + " billets");
        notifyAll();
    }
}
