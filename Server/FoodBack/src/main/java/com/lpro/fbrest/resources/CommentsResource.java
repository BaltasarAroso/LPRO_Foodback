package com.lpro.fbrest.resources;

import java.util.List;

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

import com.lpro.fbrest.api.Comment;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.service.CommentService;

import io.dropwizard.auth.Auth;

@Path("/comments")
public class CommentsResource {

	private CommentService commentService;
	
	public CommentsResource(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@POST
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newComment(@Auth Client client, Comment comment) {
		if(client.getUsers_id() != comment.getCommenter_id()) {
			throw new WebApplicationException(401); //not authorized
		}
		commentService.newComment(comment);
		return Response.ok().build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{establishment_id}")
	public List<Comment> getEstablishmentComments(@PathParam("establishment_id") long establishment_id){
		return commentService.getEstablishmentComments(establishment_id);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteComment(@Auth Client client, Comment comment) {
		if(client.getUsers_id() != comment.getCommenter_id()) {
			throw new WebApplicationException(401); //not authorized
		}
		commentService.deleteComment(comment);
		return Response.ok().build();
	}
	
}
