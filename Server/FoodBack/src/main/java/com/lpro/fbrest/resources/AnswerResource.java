package com.lpro.fbrest.resources;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lpro.fbrest.api.Answer;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.service.AnswerService;

import io.dropwizard.auth.Auth;

/**
 * @author Daniel
 *
 */
@Path("/answers")
public class AnswerResource {

	/**
	 * Service to access Persistent Storage
	 */
	private AnswerService answerService;
	
	/**
	 * @param answerService Service to access persistent storage
	 */
	public AnswerResource(AnswerService answerService) {
		this.answerService = answerService;
	}
	
	/**
	 * @param client Client that authenticated
	 * @param answer Answer to be inserted in DB
	 * @return Response to tell client if request was well handled
	 */
	@POST
	@RolesAllowed("ESTABLISHMENT")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newAnswer(@Auth Client client, Answer answer) {
		if(!answerService.checkIfIsOwner(answer.getComment_id(), client.getEstablishment_id())) 
			throw new WebApplicationException(403);
		answerService.newAnswer(answer);
		return Response.ok().build();
	}
	
	/**
	 * @param comment_id ID of comment to get its answer
	 * @return Answer if found
	 */
	@GET
	@Path("/{comment_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Answer getAnswerByCommentId(@PathParam("comment_id") long comment_id) {
		return answerService.getAnswerByCommentId(comment_id);
	}
	
	/**
	 * @param establishment_id ID of establishment to get its answers
	 * @return List of Answers if any
	 */
	@GET
	@Path("/establishment/{establishment_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Answer> getAnswerByEstablishment(@PathParam("establishment_id") long establishment_id) {
		return answerService.getAllAnswersByEstablishmentId(establishment_id);
	}
}
