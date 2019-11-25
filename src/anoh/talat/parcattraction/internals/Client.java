package anoh.talat.parcattraction.internals;

import static anoh.talat.parcattraction.utils.Utils.randomInt;

public class Client extends Thread {
    private static final int GOTO_DELAY = 1000;
    private static final int TICKET_DELAY = 1000;
    private static final int ATTRACTION_DELAY = 1000;

    public enum ClientState {INIT, ENTERED, RIDE1, TRANSIT, RIDE2}

    public int numero;
    private Billetterie billetterie;
    private Attraction attraction1, attraction2, attractionCourant;
    public ClientState state;

    public Client(int numero, Billetterie billetterie, Attraction attraction1, Attraction attraction2) {
        this.numero = numero;
        this.billetterie = billetterie;
        this.attraction1 = attraction1;
        this.attraction2 = attraction2;
    }

    private void acheterTicket() {
        this.state = ClientState.INIT;
        try {
            Thread.sleep(TICKET_DELAY);
        } catch (InterruptedException ignored) {
        }
        billetterie.vendreBillet(randomInt(1, 5));
    }

    private void allerA(Attraction attraction) {
        this.state = (attraction == attraction1) ? ClientState.ENTERED : ClientState.TRANSIT;
        try {
            Thread.sleep(GOTO_DELAY);
        } catch (InterruptedException ignored) {
        }
        attractionCourant = attraction;
    }

    private void faireAttraction() {
        attractionCourant.monter();
        this.state = attractionCourant == attraction1 ? ClientState.RIDE1 : ClientState.RIDE2;
        try {
            Thread.sleep(ATTRACTION_DELAY);
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public void run() {
        acheterTicket();
        allerA(attraction1);
        faireAttraction();
        allerA(attraction2);
        faireAttraction();
    }
}
