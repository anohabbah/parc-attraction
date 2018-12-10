package anoh.talat.parcattraction;

class Attraction {

    private Navette navetteCourant = null;
    private final int numero;
    private final String tag;

    Attraction(int numero) {
        this.numero = numero;
        tag = "Attraction " + numero;
    }

    synchronized void quai(Navette navette) {
        while (navetteCourant != null) {
            try {
                System.out.printf("%s: La Navette %d attend pour rentrer au quai%n", tag, navette.numero);
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        System.out.printf("%s: La Navette %d est au quai%n", tag, navette.numero);
        navetteCourant = navette;
        notifyAll();
    }

    synchronized void monter() {
        while (navetteCourant == null || navetteCourant.placeDisponible == 0) {
            try {
                System.out.printf("%s: Un client en attente de navette%n", tag);
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        System.out.printf("%s: Un client monte dans la navette %d%n", tag, navetteCourant.numero);
        navetteCourant.placeDisponible--;
    }

    synchronized void voyage() {
        System.out.printf(
                "%s: La navette %d commence le voyage avec %d places de disponible%n",
                tag, navetteCourant.numero, navetteCourant.placeDisponible
        );
        navetteCourant = null;
        notifyAll();
    }
}
