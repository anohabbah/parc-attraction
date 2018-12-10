package anoh.talat.parcattraction;

public class Navette extends Thread {
    private static final int STOP_DELAY = 3 * 1000;
    private static final int TRIP_DELAY = 5 * 1000;

    private static final int PLACE_INIT = 20;

    int placeDisponible;
    private Attraction attraction;
    int numero;

    Navette(int numero, Attraction attraction) {
        this.numero = numero;
        setDaemon(true);
        this.placeDisponible = PLACE_INIT;
        this.attraction = attraction;
    }

    @Override
    public void run() {
        while (true) {
            arrivee();
            depart();
        }

    }

    private void arrivee() {
        attraction.quai(this);
        try {
            Thread.sleep(STOP_DELAY);
        } catch (InterruptedException ignored) {
        }
    }

    private void depart() {
        attraction.voyage();
        try {
            Thread.sleep(TRIP_DELAY);
        } catch (InterruptedException ignored) {
        }
        placeDisponible = PLACE_INIT;
    }
}
