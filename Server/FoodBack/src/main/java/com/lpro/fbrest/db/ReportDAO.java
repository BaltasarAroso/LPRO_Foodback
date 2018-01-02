package com.lpro.fbrest.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.Report;

/**
 * @author Daniel
 *
 */
@RegisterMapper(ReportMapper.class)
public interface ReportDAO {
	
	/**
	 * @param report Report text
	 * @param comment_id ID of reported comment
	 * @param reporter_id ID of user who is reporting
	 * @return ID of inserted report
	 */
	@SqlUpdate("INSERT INTO report "
			+ "VALUES (DEFAULT, 'bad_comment', :report, :comment_id, null, :reporter_id)")
	@GetGeneratedKeys
	public long insertCommentReport(@Bind("report") String report, 
									@Bind("comment_id") long comment_id, 
									@Bind("reporter_id") long reporter_id);
	
	/**
	 * @param report Report text
	 * @param establishment_id ID of reported establishment
	 * @param reporter_id ID of user who is reporting
	 * @return ID of inserted report
	 */
	@SqlUpdate("INSERT INTO report "
			+ "VALUES (DEFAULT, 'bad_info', :report, null, :establishment_id, :reporter_id)")
	@GetGeneratedKeys
	public long insertInfoReport(@Bind("report") String report, 
								@Bind("establishment_id") long establishment_id, 
								@Bind("reporter_id") long reporter_id);
	
	/**
	 * @return List of reports 
	 */
	@SqlQuery("SELECT * "
			+ "FROM report "
			+ "WHERE type = 'bad_comment'")
	public List<Report> getAllCommentReports();
	
	/**
	 * @return List of reports 
	 */
	@SqlQuery("SELECT * "
			+ "FROM report "
			+ "WHERE type = 'bad_info'")
	public List<Report> getAllInfoReports();
	
	/**
	 * @param id ID of report to be searched
	 * @return Report if found
	 */
	@SqlQuery("SELECT * "
			+ "FROM report "
			+ "WHERE id = :id")
	public Report getReportById(@Bind("id") long id);
	
	/**
	 * @param id ID of report to be deleted
	 */
	@SqlUpdate("DELETE FROM report "
			+ "WHERE id = :id")
	public void deleteReportById(@Bind("id") long id);
}
