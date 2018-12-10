package anoh.talat.parcattraction.database;

import anoh.talat.parcattraction.internals.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static anoh.talat.parcattraction.utils.Utils.randomInt;

public class ParcDatabase implements Database {
    private Map<Integer, Client> clients;
    private Map<Integer, Navette> navettes;

    public ParcDatabase() {
        // Billetterie
        Billetterie billetterie = new Billetterie();
        ResponsableBilletterie responsableBilletterie = new ResponsableBilletterie(billetterie);

        // instanciation des attractions
        Attraction[] attractions = new Attraction[5];
        for (int i = 0; i < 5; ++i) {
            attractions[i] = new Attraction(i + 1);
        }

        // instanciation des clients
        clients = new HashMap<>();
        for (int i = 0; i < 20; ++i) {
            clients.put(i, new Client(i, billetterie, attractions[randomInt(0, 4)], attractions[randomInt(0, 4)]));
        }

        navettes = new HashMap<>();
        int k = 0;
        for (int i = 0; i < 5; ++i) {
            for (int j = 1; j <= 3; ++j) {
                navettes.put(k, new Navette(k + 1, attractions[i]));
                ++k;
            }
        }

        // Demarrage des processus
        responsableBilletterie.start();
        for (Client c : clients.values())
            c.start();

        for (Navette n : navettes.values())
            n.start();
    }

    /**
     * Retourne la liste des Clients
     *
     * @return Liste des Clients
     */
    @Override
    public Collection<Client> getClients() {
        return clients.values();
    }

    /**
     * Retourne la liste des navettes
     *
     * @return Liste des navettes
     */
    @Override
    public Collection<Navette> getNavettes() {
        return navettes.values();
    }
}
