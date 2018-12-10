package anoh.talat.parcattraction.resources;

import anoh.talat.parcattraction.backend.Backend;
import anoh.talat.parcattraction.internals.Navette;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.ArrayList;
import java.util.Collection;

public class NavettesResource extends ServerResource {
    private Backend backend;

    public NavettesResource() {
        super();
        backend = (Backend) getApplication().getContext().getAttributes().get("backend");
    }

    @Get("json")
    public Representation getNavettes() throws JSONException {
        Collection<Navette> navettes = backend.getDatabase().getNavettes();
        Collection<JSONObject> jsonObjects = new ArrayList<>();
        for (Navette navette : navettes) {
            JSONObject current = new JSONObject();
            current.put("id", navette.numero);
            current.put("attraction_id", navette.attraction.numero);
            current.put("available_seat", navette.placeDisponible);
            current.put("state", navette.state.name());
            jsonObjects.add(current);
        }

        return new JsonRepresentation(new JSONArray(jsonObjects));
    }
}
