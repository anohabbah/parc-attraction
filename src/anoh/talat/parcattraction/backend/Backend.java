package anoh.talat.parcattraction.backend;

import anoh.talat.parcattraction.database.Database;
import anoh.talat.parcattraction.database.ParcDatabase;

public class Backend {

    Database database;

    public Backend() {
        database = new ParcDatabase();
    }

    public Database getDatabase() {
        return database;
    }
}
