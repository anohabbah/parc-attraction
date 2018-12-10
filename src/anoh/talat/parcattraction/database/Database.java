package anoh.talat.parcattraction.database;

import anoh.talat.parcattraction.internals.Client;
import anoh.talat.parcattraction.internals.Navette;

import java.util.Collection;

public interface Database {
    /**
     * Retourne la liste des Clients
     * @return Liste des Clients
     */
    Collection<Client> getClients();

    /**
     * Retourne la liste des navettes
     * @return Liste des navettes
     */
    Collection<Navette> getNavettes();
}
