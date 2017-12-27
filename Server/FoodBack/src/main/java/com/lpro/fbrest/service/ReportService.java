package com.lpro.fbrest.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.lpro.fbrest.api.Report;
import com.lpro.fbrest.db.ReportDAO;

/**
 * @author Daniel
 *
 */
public abstract class ReportService {
	
	@CreateSqlObject
	abstract ReportDAO reportDao();
	
	/**
	 * @param report Report to be stored
	 */
	public void newCommentReport(Report report) {
		try {
			reportDao().insertCommentReport(report.getReport(), 
				report.getComment_id(), 
				report.getReporter_id());
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	
	/**
	 * @param report Report to be stored
	 */
	public void newInfoReport(Report report) {
		try {
			reportDao().insertInfoReport(report.getReport(), 
				report.getEstablishment_id(), 
				report.getReporter_id());
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}
	
	/**
	 * @return List of reports
	 */
	public List<Report> getAllCommentReports(){
		List<Report> reports;
		try {
			reports = reportDao().getAllCommentReports();
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		if(reports == null) throw new WebApplicationException(404);
		if(reports.isEmpty()) throw new WebApplicationException(404);
		return reports;
	}
	
	/**
	 * @return List of reports
	 */
	public List<Report> getAllInfoReports(){
		List<Report> reports;
		try {
			reports = reportDao().getAllInfoReports();
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
		if(reports == null) throw new WebApplicationException(404);
		if(reports.isEmpty()) throw new WebApplicationException(404);
		return reports;
	}
	
	/**
	 * @param id ID of report to be deleted
	 */
	public void deleteReportById(long id) {
		try {
			reportDao().deleteReportById(id);
		} catch(Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(500);
		}
	}

}
