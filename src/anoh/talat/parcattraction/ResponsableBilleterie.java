package anoh.talat.parcattraction;

import static anoh.talat.parcattraction.utils.Utils.randomInt;

public class ResponsableBilleterie extends Thread {
    private Billeterie billeterie;

    ResponsableBilleterie(Billeterie billeterie) {
        setDaemon(true);
        this.billeterie = billeterie;
    }

    @Override
    public void run() {
        while (true)
            alimenterBilleterie();
    }

    private void alimenterBilleterie() {
        billeterie.ajouterTicket(randomInt(15, 25));
    }
}
