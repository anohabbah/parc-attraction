package anoh.talat.parcattraction.application;

import anoh.talat.parcattraction.resources.ClientsResource;
import anoh.talat.parcattraction.resources.NavettesResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class ParcApplication extends Application {

    public ParcApplication(Context context) {
        super(context);
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("/clients", ClientsResource.class);
        router.attach("/navettes", NavettesResource.class);
        return router;
    }
}
