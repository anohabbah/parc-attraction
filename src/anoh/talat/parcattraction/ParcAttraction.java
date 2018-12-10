package anoh.talat.parcattraction;

import anoh.talat.parcattraction.application.ParcApplication;
import anoh.talat.parcattraction.backend.Backend;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.data.Protocol;

public class ParcAttraction {

    ParcAttraction() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) throws Exception {
        Component component = new Component();
        Context context = component.getContext().createChildContext();
        component.getServers().add(Protocol.HTTP, 8000);

        Application application = new ParcApplication(context);

        Backend backend = new Backend();
        context.getAttributes().put("backend", backend);
        component.getDefaultHost().attach(application);

        component.start();
    }
}
