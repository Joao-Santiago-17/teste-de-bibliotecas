package aor.paj.enviaremail;

import aor.paj.CsvManager.CsvManager;
import aor.paj.PdfTest.PdfManager;
import emailManager.EmailManager;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.File;

@Path("/test")
public class HelloResource {

    @Inject
    EmailManager emailManager;

    

    @GET
    @Path("/mail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        emailManager.sendEmail("jpfs1812@gmail.com","Emails de teste","Hey Jorge!");
        return Response.ok("email enviado").build();
    }

    @GET
    @Path("/csv")
    public Response csv(){

        File file=  CsvManager.convertToCsv();


        return Response.ok(file).build();
    }

    @GET
    @Path("/pdf")
    public Response pdf(){
        File file = PdfManager.convertPdf();
        return Response.ok(file)
                .header("Content-Disposition", "attachment; filename=pessoa.pdf")
                .build();
    }
}