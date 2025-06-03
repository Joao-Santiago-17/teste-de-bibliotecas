package aor.paj.enviaremail;

import aor.paj.PdfTest.PdfManager;
import emailManager.EmailManager;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

@Path("/test")
public class HelloResource {

    private static final String UPLOAD_DIR = "C:\\Users\\LENOVO\\OneDrive\\Ambiente de Trabalho\\enviarEmail\\Avatars";


    @GET
    @Path("/mail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        EmailManager.sendEmail("jpfs1812@gmail.com", "Emails de teste", "Hey!");
        return Response.ok("email enviado").build();
    }

    @GET
    @Path("/csv")
    public Response csv() {

        //File file= CsvManager.convertToCsv();


        return Response.ok(/*file*/).build();
    }

    @GET
    @Path("/pdf")
    public Response pdf() {
        File file = PdfManager.convertPdf();
        return Response.ok(file)
                .header("Content-Disposition", "attachment; filename=pessoa.pdf")
                .build();
    }


    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@MultipartForm FileUploadForm form) {
        if (form.file != null && form.filename != null && !form.filename.isBlank()) {
            try {
                System.out.println(form.filename);
                System.out.println(form.file);

                File uploadFolder = new File(UPLOAD_DIR);
                if (!uploadFolder.exists()) {
                    uploadFolder.mkdirs();
                }

                File outputFile = new File(UPLOAD_DIR, form.filename);

                try (InputStream input = form.file; FileOutputStream output = new FileOutputStream(outputFile)) {
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Erro a criar o ficehiro").build();
            }

            return Response.status(Response.Status.OK)
                    .entity("ok").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Ficheiro ou nome não fornecido").build();
        }
    }


}


