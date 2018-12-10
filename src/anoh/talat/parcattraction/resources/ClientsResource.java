package anoh.talat.parcattraction.resources;

import anoh.talat.parcattraction.backend.Backend;
import anoh.talat.parcattraction.internals.Client;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.Collection;

public class ClientsResource extends ServerResource {
    private Backend backend;

    public ClientsResource() {
        super();
        backend = (Backend) getApplication().getContext().getAttributes().get("backend");
    }

    @Get("json")
    public Representation getClients() throws JSONException {
        Collection<Client> clients = backend.getDatabase().getClients();
        Collection<JSONObject> jsonObjects = new ArrayList<>();
        for (Client client : clients) {
            JSONObject current = new JSONObject();
            current.put("id", client.numero);
            current.put("state", client.state.name());
            jsonObjects.add(current);
        }
        return new JsonRepresentation(new JSONArray(jsonObjects));
    }
}
