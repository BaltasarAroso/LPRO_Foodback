package com.lpro.fbrest.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.Comment;

@RegisterMapper(CommentMapper.class)
public interface CommentDAO {
	
	@SqlUpdate("INSERT INTO comment "
			+ "VALUES (DEFAULT, :establishment_id, :commenter_id, DEFAULT, :rating, :comment)")
	@GetGeneratedKeys
	public long insertComment(@Bind("establishment_id") int establishment_id,
							@Bind("commenter_id") int commenter_id,
							@Bind("rating") int rating,
							@Bind("comment") String comment);
	
	@SqlUpdate("DELETE FROM comment "
			+ "WHERE id = :comment_id")
	public void deleteComment(@Bind("id") long comment_id);
	
	@SqlQuery("SELECT * "
			+ "FROM comment "
			+ "WHERE establishment_id = :establishment_id")
	public List<Comment> getEstablishmentComments(@Bind("establishment_id") long establishment_id);

}
