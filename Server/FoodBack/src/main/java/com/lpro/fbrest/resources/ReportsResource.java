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

import com.lpro.fbrest.api.Report;
import com.lpro.fbrest.auth.Client;
import com.lpro.fbrest.service.ReportService;

import io.dropwizard.auth.Auth;

/**
 * @author Daniel
 *
 */
@Path("/reports")
public class ReportsResource {
	
	/**
	 *  Service to access persistent storage
	 */
	private ReportService reportService;
	
	/**
	 * @param reportService Service to access persistent storage
	 */
	public ReportsResource(ReportService reportService) {
		super();
		this.reportService = reportService;
	}

	/**
	 * @param client Client that authenticated
	 * @param report Report to be stored
	 * @return Response to let client know if request was executed
	 */
	@POST
	@Path("/comments")
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newCommentReport(@Auth Client client, Report report) {
		if(client.getUsers_id() != report.getReporter_id()) throw new WebApplicationException(403);
		reportService.newCommentReport(report);
		return Response.ok().build();
	}
	
	/**
	 * @return List of reports
	 */
	@GET
	@RolesAllowed("ADMIN")
	@Path("/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Report> getAllCommentReports(){
		return reportService.getAllCommentReports();
	}
	
	/**
	 * @param client Client that authenticated
	 * @param report Report to be stored
	 * @return Response to let client know if request was executed
	 */
	@POST
	@Path("/establishments")
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newEstablishmentReport(@Auth Client client, Report report) {
		if(client.getUsers_id() != report.getReporter_id()) throw new WebApplicationException(403);
		reportService.newInfoReport(report);
		return Response.ok().build();
	}
	
	/**
	 * @return List of Reports
	 */
	@GET
	@RolesAllowed("ADMIN")
	@Path("/establishments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Report> getAllEstablishmentReports(){
		return reportService.getAllInfoReports();
	}
	
	/**
	 * @param id ID of Report to be deleted
	 * @return Response to let client know if request was executed
	 */
	@DELETE
	@RolesAllowed("ADMIN")
	@Path("/{id}")
	public Response deleteReport(@PathParam("id") long id) {
		reportService.deleteReportById(id);
		return Response.ok().build();
	}
}
