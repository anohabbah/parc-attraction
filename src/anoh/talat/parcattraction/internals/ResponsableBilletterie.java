package anoh.talat.parcattraction.internals;

import static anoh.talat.parcattraction.utils.Utils.randomInt;

public class ResponsableBilletterie extends Thread {
    private Billetterie billetterie;

    public ResponsableBilletterie(Billetterie billetterie) {
        this.billetterie = billetterie;
        setDaemon(true);
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
