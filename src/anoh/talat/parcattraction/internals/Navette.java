package anoh.talat.parcattraction.internals;

public class Navette extends Thread {
    private static final int STOP_DELAY = 3000;
    private static final int TRIP_DELAY = 5000;
    private static final int PLACE_INIT = 20;

    public enum NavetteState {STOPPED, TRAVELLING}

    public int placeDisponible;
    public Attraction attraction;
    public int numero;
    public NavetteState state;

    public Navette(int numero, Attraction attraction) {
        this.numero = numero;
        placeDisponible = PLACE_INIT;
        this.attraction = attraction;
        setDaemon(true);
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
        this.state = NavetteState.STOPPED;
        try {
            Thread.sleep(STOP_DELAY);
        } catch (InterruptedException ignored) {
        }
    }

    private void depart() {
        attraction.voyage();
        this.state = NavetteState.TRAVELLING;
        try {
            Thread.sleep(TRIP_DELAY);
        } catch (InterruptedException ignored) {
        }
        this.placeDisponible = PLACE_INIT;
    }
}
