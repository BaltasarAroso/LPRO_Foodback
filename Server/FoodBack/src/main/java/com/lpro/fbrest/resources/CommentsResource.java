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
import com.lpro.fbrest.api.User;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.service.CommentService;

import io.dropwizard.auth.Auth;

/**
 * Resource to manage comments
 */
@Path("/comments")
public class CommentsResource {

	/**
	 * Service to access comments
	 */
	private CommentService commentService;
	
	/**
	 * @param commentService to access comment
	 */
	public CommentsResource(CommentService commentService) {
		this.commentService = commentService;
	}
	
	/**
	 * @param client Client that is making the comment
	 * @param comment Comment to be created
	 * @return Response.ok() if comment was successfully created
	 */
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
	
	/**
	 * @param establishment_id Id of the establishment
	 * @return List of comments made to the establishment specified
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{establishment_id}")
	public List<Comment> getEstablishmentComments(@PathParam("establishment_id") long establishment_id){
		return commentService.getEstablishmentComments(establishment_id);
	}
	
	/**
	 * @param client Client that wants to delete his own comment
	 * @param comment_id ID of Comment to be deleted
	 * @return Response.ok() if comment was successfully deleted
	 */
	@DELETE
	@Path("/{comment_id}")
	@RolesAllowed({"USER","ADMIN"})
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteComment(@Auth Client client, @PathParam("comment_id") long comment_id) {
		commentService.deleteComment(client.getUsers_id() ,comment_id);
		return Response.ok().build();
	}
	
	/**
	 * @param comment_id ID of comment
	 * @return User who made comment
	 */
	@GET
	@Path("/{comment_id}/commenter")	
	@Produces(MediaType.APPLICATION_JSON)
	public User getCommeterUser(@PathParam("comment_id") long comment_id) {
		return commentService.getCommenterUser(comment_id);
	}
	
	/**
	 * @param comment_id ID of comment
	 * @return Comment with specified ID
	 */
	@GET
	@Path("/id/{comment_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Comment getCommentById(@PathParam("comment_id") long comment_id) {
		return commentService.getCommentById(comment_id);
	}
	
}
