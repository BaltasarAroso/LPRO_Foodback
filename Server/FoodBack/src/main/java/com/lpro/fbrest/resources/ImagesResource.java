package com.lpro.fbrest.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.db.EstablishmentImageDAO;

import io.dropwizard.auth.Auth;

@Path("/images")
public class ImagesResource {

	private EstablishmentImageDAO establishmentImageDao;
	
	public ImagesResource(EstablishmentImageDAO establishmentImageDao) {
		this.establishmentImageDao = establishmentImageDao;
	}
	
	@POST
	@Path("/establishment")
	@RolesAllowed("ESTABLISHMENT")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadEstablishmentImage(@Auth Client client, @FormDataParam("file") InputStream fileInputStream,
									@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {
		
		String fileName = contentDispositionHeader.getFileName();
		if(!fileName.endsWith(".jpg") &&
				!fileName.endsWith(".jpeg") &&
				!fileName.endsWith(".png") &&
				!fileName.endsWith(".gif"))
			throw new WebApplicationException(400);
		
		String extension = fileName.substring(fileName.lastIndexOf('.'));
		
		long imageId;
		try {
			imageId = establishmentImageDao.insertImage(client.getEstablishment_id(), extension, false);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		
		String destinationPath = "uploads/" 
				+ "establishment"
				+ "_"
				+ Long.toString(client.getEstablishment_id()) 
				+ "_"
				+ Long.toString(imageId)
				+ extension;
		
		saveFile(fileInputStream, destinationPath);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("/establishment/profile")
	@RolesAllowed("ESTABLISHMENT")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadEstablishmentProfileImage(@Auth Client client, @FormDataParam("file") InputStream fileInputStream,
									@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {
		
		long profileId = establishmentImageDao.getProfileId(client.getEstablishment_id());
		if(profileId != 0) {
			String oldImagePath = "uploads/" 
					+ "establishment"
					+ "_"
					+ Long.toString(client.getEstablishment_id()) 
					+ "_"
					+ Long.toString(profileId)
					+ establishmentImageDao.getImageExtension(profileId);
			try {
				File file = new File(oldImagePath);
				if(!file.delete()) throw new WebApplicationException(500);
				establishmentImageDao.deleteImage(profileId);
			} catch(Exception e) {
				e.printStackTrace();
				throw new WebApplicationException(500);
			}
		}
		
		String fileName = contentDispositionHeader.getFileName();
		if(!fileName.endsWith(".jpg") &&
				!fileName.endsWith(".jpeg") &&
				!fileName.endsWith(".png") &&
				!fileName.endsWith(".gif"))
			throw new WebApplicationException(400);
		
		String extension = fileName.substring(fileName.lastIndexOf('.'));
		
		long imageId;
		try {
			imageId = establishmentImageDao.insertImage(client.getEstablishment_id(), extension, true);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		
		String destinationPath = "uploads/" 
				+ "establishment"
				+ "_"
				+ Long.toString(client.getEstablishment_id()) 
				+ "_"
				+ Long.toString(imageId)
				+ extension;
		
		saveFile(fileInputStream, destinationPath);
		
		return Response.ok().build();
	}
	
	@GET
	@Path("/{imageName}")
	@Produces("image/*")
	public Response getImage(@PathParam("imageName") String imageName) {
	  File f = new File("uploads/" + imageName);
	 
	  if (!f.exists()) {
	    throw new WebApplicationException(404);
	  }
	 
	  String mt = new MimetypesFileTypeMap().getContentType(f);
	  return Response.ok(f, mt).build();
	}

	@GET
	@Path("/establishment/profile/{establishment_id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getEstablishmentProfileImageName(@PathParam("establishment_id") long establishment_id) {
		long profileId = establishmentImageDao.getProfileId(establishment_id);
		if(profileId == 0) throw new WebApplicationException(404);
		return "establishment"
				+ "_"
				+ Long.toString(establishment_id)
				+ "_"
				+ Long.toString(profileId)
				+ establishmentImageDao.getImageExtension(profileId);
	}
	
	@GET
	@Path("/establishment/{establishment_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getEstablishmentImageNames(@PathParam("establishment_id") long establishment_id) {
		List<Long> imageIds = establishmentImageDao.getAllImagesId(establishment_id);
		if(imageIds.isEmpty()) throw new WebApplicationException(404);
		
		List<String> imageNames = new ArrayList<String>();
		for(int i = 0; i<imageIds.size() ; i++) {
			imageNames.add("establishment"
				+ "_"
				+ Long.toString(establishment_id)
				+ "_"
				+ Long.toString(imageIds.get(i))
				+ establishmentImageDao.getImageExtension(imageIds.get(i)));
		}
		return imageNames;
	}
	
	@DELETE
	@Path("/{imageName}")
	@RolesAllowed("ESTABLISHMENT")
	public Response deleteImage(@Auth Client client, @PathParam("imageName") String imageName) {
		long establishment_id = Long.parseLong(imageName.substring(imageName.indexOf('_')+1).split("_")[0]);
		if(establishment_id != client.getEstablishment_id()) throw new WebApplicationException(405);
		try {
			File file = new File("uploads/" + imageName);
			if(!file.delete()) throw new WebApplicationException(500);
			establishmentImageDao.deleteImage(Long.parseLong(imageName.substring(imageName.lastIndexOf('_')+1).split("[.]")[0]));
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
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
            throw new WebApplicationException(500);
        }

	}
	
}
