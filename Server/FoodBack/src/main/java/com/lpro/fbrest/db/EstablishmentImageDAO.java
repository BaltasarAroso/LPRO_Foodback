package com.lpro.fbrest.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface EstablishmentImageDAO {
	
	@SqlUpdate("INSERT INTO establishment_image "
			+ "VALUES (DEFAULT, :establishment_id, :extension, :profile)")
	@GetGeneratedKeys
	public long insertImage(@Bind("establishment_id") long establishment_id,
			@Bind("extension") String extension,
			@Bind("profile") boolean profile);
	
	@SqlQuery("SELECT extension "
			+ "FROM establishment_image "
			+ "WHERE id = :id")
	public String getImageExtension(@Bind("id") long id);
	
	@SqlQuery("SELECT id "
			+ "FROM establishment_image "
			+ "WHERE establishment_id = :establishment_id AND profile = true")
	public long getProfileId(@Bind("establishment_id") long establishment_id);
	
	@SqlQuery("SELECT id "
			+ "FROM establishment_image "
			+ "WHERE establishment_id = :establishment_id AND profile = false")
	public List<Long> getAllImagesId(@Bind("establishment_id") long establishment_id);
	
	@SqlUpdate("DELETE FROM establishment_image "
			+ "WHERE id = :id")
	public void deleteImage(@Bind("id") long id);

}
