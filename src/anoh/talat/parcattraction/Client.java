package anoh.talat.parcattraction;

import static anoh.talat.parcattraction.utils.Utils.randomInt;

public class Client extends Thread {
    private static final int GOTO_DELAY = 1000;
    private static final int TICKET_DELAY = 1000;
    private static final int ATTRACTION_DELAY = 1000;

    private Billeterie billeterie;
    private Attraction attraction1, attraction2, attractionCourant = null;

    public Client(Billeterie billeterie, Attraction attraction1, Attraction attraction2) {
        this.billeterie = billeterie;
        this.attraction1 = attraction1;
        this.attraction2 = attraction2;
    }

    private void acheterTicket() {
        try {
            Thread.sleep(TICKET_DELAY);
        } catch (InterruptedException ignored) {
        }
        billeterie.vendreBillet(randomInt(1, 5));
    }

    private void allerA(Attraction attraction) {
        try {
            Thread.sleep(GOTO_DELAY);
        } catch (InterruptedException ignored) {
        }
        attractionCourant = attraction;
    }

    private void faireAttraction() {
        attractionCourant.monter();
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
