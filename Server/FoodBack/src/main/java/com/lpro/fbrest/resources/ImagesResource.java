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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.lpro.fbrest.auth.Client;

import io.dropwizard.auth.Auth;

@Path("/images")
public class ImagesResource {

	
	@POST
	@Path("/users")
	@RolesAllowed("USER")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadUserImage(@Auth Client client, @FormDataParam("file") InputStream fileInputStream,
									@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {
		
		String fileName = contentDispositionHeader.getFileName();
		if(!fileName.endsWith(".jpg") &&
				!fileName.endsWith(".jpeg") &&
				!fileName.endsWith(".png") &&
				!fileName.endsWith(".gif"))
			throw new WebApplicationException(400);
		
		saveFile(fileInputStream, "uploads/" + Integer.toString(client.getUsers_id()) 
							+ fileName.substring(fileName.lastIndexOf('.')));
		
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
