package com.lpro.fbrest.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/images")
public class ImagesResource {

	
	@POST
	@Path("/users")
	@RolesAllowed("USER")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadUserImage(@FormDataParam("file") InputStream fileInputStream,
									@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {
		
		String filePath = contentDispositionHeader.getFileName();
		saveFile(fileInputStream, filePath);
		
		return Response.ok().build();
	}

	private void saveFile(InputStream fileInputStream, String filePath) {
		try {
            OutputStream outputStream = new FileOutputStream(new File(filePath));
            int read = 0;
            byte[] bytes = new byte[1024];
            outputStream = new FileOutputStream(new File(filePath));
            while ((read = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
}
