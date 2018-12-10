package anoh.talat.parcattraction.internals;

import static anoh.talat.parcattraction.utils.Utils.randomInt;

public class ResponsableBilletterie extends Thread {
    private Billetterie billetterie;

    public ResponsableBilletterie(Billetterie billetterie) {
        setDaemon(true);
        this.billetterie = billetterie;
    }

    @Override
    public void run() {
        while (true)
            alimenterBilleterie();
    }

    private void alimenterBilleterie() {
        billetterie.ajouterTicket(randomInt(15, 25));
    }
}
