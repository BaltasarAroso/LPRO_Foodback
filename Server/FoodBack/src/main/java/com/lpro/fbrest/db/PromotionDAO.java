package com.lpro.fbrest.db;

import java.time.LocalDate;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.lpro.fbrest.api.Promotion;

/**
 * DAO for promotion storage access
 */
@RegisterMapper(PromotiontMapper.class)
public interface PromotionDAO {
	
	/**
	 * @param title Title of promotion
	 * @param description Description of promotion
	 * @param code Code of promotion 
	 * @param good_until Date the promotion ends
	 * @return ID of promotion
	 */
	@SqlUpdate("INSERT INTO promotion "
			+ "VALUES (DEFAULT, :title, :description, :code, :good_until)")
	@GetGeneratedKeys
	public long insertPromotion(@Bind("title") String title, 
			@Bind("description") String description,
			@Bind("code") String code, 
			@Bind("good_until") LocalDate good_until);
	
	/**
	 * @return List of active promotions
	 */
	@SqlQuery("SELECT * "
			+ "FROM promotion "
			+ "WHERE now() <= good_until")
	public List<Promotion> getActivePromotions();
	
	/**
	 * @param id ID of promotion to be deleted
	 */
	@SqlUpdate("DELETE FROM promotion "
			+ "WHERE id = :id")
	public void deletePromotion(@Bind("id") long id);

	/**
	 * @param users_id ID of user
	 * @return If user is premium or not
	 */
	@SqlQuery("SELECT premium "
			+ "FROM users "
			+ "WHERE id = :id")
	public boolean userIsPremium(@Bind("id") long users_id);

}
