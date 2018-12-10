package anoh.talat.parcattraction.internals;

public class Attraction {

    private Navette navetteCourant;
    public final int numero;
    private final String tag;

    public Attraction(int numero) {
        this.numero = numero;
        tag = "Attraction " + numero;
    }

    /**
     * Entree au quai.
     *
     * @param navette La navette qui souhaite rentrer au quai
     */
    synchronized void quai(Navette navette) {
        while (navetteCourant != null) {
            try {
                System.out.printf("%s: La navette %d attend de rentrer au quai%n", tag, navette.numero);
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        System.out.printf("%s: La navette %d est au quai%n", tag, navette.numero);
        navetteCourant = navette;
    }

    /**
     * Montee d'un client dans la navette
     */
    synchronized void monter() {
        while (navetteCourant == null || navetteCourant.placeDisponible == 0) {
            try {
                System.out.println(tag + ": Un client en attente de navette");
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        System.out.printf("%s: Un client monte dans la navette %d%n", tag, navetteCourant.numero);
        navetteCourant.placeDisponible--;
    }

    /**
     * Depart d'une navette du quai
     */
    synchronized void voyage() {
        System.out.printf(
                "%s: La navette %d commence le voyage avec %d client(s) abord%n",
                tag, navetteCourant.numero, 20 - navetteCourant.placeDisponible
        );
        navetteCourant = null;
        notifyAll();
    }
}
