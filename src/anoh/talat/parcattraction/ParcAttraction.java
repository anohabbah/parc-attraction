package anoh.talat.parcattraction;

import static anoh.talat.parcattraction.utils.Utils.randomInt;

public class ParcAttraction {

    private static final int NBRE_CLIENTS = 20;
    private static final int NBRE_ATTRACTIONS = 5;
    private static final int NBRE_NAVETTES_PAR_ATTRACTION = 3;
    private static final int NBRE_NAVETTES = NBRE_NAVETTES_PAR_ATTRACTION * NBRE_ATTRACTIONS;

    private ParcAttraction() {
        // Billeterie
        Billeterie billeterie = new Billeterie();
        ResponsableBilleterie responsableBilleterie = new ResponsableBilleterie(billeterie);

        // instanciation des attractions
        Attraction[] attractions = new Attraction[NBRE_ATTRACTIONS];
        for (int i = 0; i < NBRE_ATTRACTIONS; ++i)
            attractions[i] = new Attraction(i + 1);

        // instanciation des clients
        Client[] clients = new Client[NBRE_CLIENTS];
        for (int i = 0; i < NBRE_CLIENTS; ++i)
            clients[i] = new Client(billeterie, attractions[randomInt(0, 4)], attractions[randomInt(0, 4)]);

        // instanciation des navettes
        Navette[] navettes = new Navette[NBRE_NAVETTES];
        int k = 0;
        for (int j = 0; j < NBRE_ATTRACTIONS; j++)
            for (int i = 0; i < NBRE_NAVETTES_PAR_ATTRACTION; ++i)
                navettes[k] = new Navette(++k, attractions[j]);

        // demarrage des processus
        responsableBilleterie.start();

        for (Client c : clients)
            c.start();

        for (Navette n : navettes)
            n.start();
    }

    public static void main(String[] args) {
        new ParcAttraction();
    }
}
