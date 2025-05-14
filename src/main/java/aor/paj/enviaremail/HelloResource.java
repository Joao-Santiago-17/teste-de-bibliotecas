package aor.paj.enviaremail;

import emailManager.EmailManager;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;

@Path("/mail")
public class HelloResource {

    @Inject
    EmailManager emailManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        emailManager.sendEmail("jpfs1812@gmail.com","Emails de teste","Hey Jorge!");
        return Response.ok("email enviado").build();
    }
}