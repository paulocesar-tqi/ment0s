package copyleft.by.pc.common.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import copyleft.by.pc.common.entities.Post;
import copyleft.by.pc.common.entities.Source;
import copyleft.by.pc.common.entities.User;

public class GenericDao {
	
	private static final Log log = LogFactory.getLog(GenericDao.class);
	
	@PersistenceContext
	@Autowired
	private EntityManager em;

	public List<String> getExternalIdsBySource(Integer sourceId) {
		
		String jpql = "SELECT p.externalId FROM Post p WHERE p.sourceId = :sourceId";
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		query.setParameter("sourceId", sourceId);
		
		return query.getResultList();
	}
	
	public List<String> getRegistrationIdsByPlatform(String platform) {
		
		String jpql = "SELECT u.regId FROM User u WHERE u.platform = :platform";
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		query.setParameter("platform", platform);
		
		return query.getResultList();
	}
	
	public void backupPostsBySources() {

		String jpqlActiveSources = "FROM Source s WHERE s.active = 1";
		String sqlCopy = "INSERT INTO post_bkp " +
				 "SELECT * FROM post " + 
				 "WHERE publication_date < (current_date - INTERVAL ( " +
				 "select days_to_purge from source where id = :sourceId) DAY) " +
				 "AND source_id = :sourceId ";
		String sqlDelete = "DELETE FROM post " +
		 		   "WHERE publication_date < (current_date - INTERVAL ( " +
		 		   "select days_to_purge from source where id = :sourceId) DAY) " +
		 		   "AND source_id = :sourceId ";

		TypedQuery<Source> tpQuery = em.createQuery(jpqlActiveSources, Source.class);
		List<Source> sourceIds = tpQuery.getResultList();
		
		for(Source source : sourceIds) {
							 
			Query query = em.createNativeQuery(sqlCopy);
			query.setParameter("sourceId", source.getId());
			int result1 = query.executeUpdate();
			
			query = em.createNativeQuery(sqlDelete);
			query.setParameter("sourceId", source.getId());
			int result2 = query.executeUpdate();
			
			log.info("Source: " + source.getId() + " - " + result1 + " posts copiados - " + result2 + " posts deletados.");
		}
	}
	
	@Transactional
	public void removeUser(String deviceId) {

		User user = em.find(User.class, deviceId);
		try {
			if(user != null) {
				em.remove(user);
				em.flush();
			}
		} catch (Exception e) {
			log.error("Erro ao remover o usuario: " + user.getRegId(), e);
		}
	}
	
	@Transactional
	public void createOrUpdateUser(String id, String platform) {
		User user = em.find(User.class, id);
		if(user == null)
			user = new User(id, platform);
		try {
			em.merge(user);
			em.flush();
		} catch (Exception e) {
			log.error("Erro ao persistir o usuario: " + user.getRegId(), e);
		}
	}	

	public List<String> getUserIdsByPlatform(String platformId) {
		
		String jpql = "SELECT u.regId FROM User u WHERE u.platformId = :platformId";
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		query.setParameter("platformId", platformId);
		
		return query.getResultList();
	}
	
	public List<Post> getPostsByFilter(int pageNumber, int pageSize) {
		
		String jpql = "SELECT new Post(p.id, p.title, p.publicationDate, p.sourceId, p.url) FROM Post p ORDER BY p.publicationDate DESC";
		TypedQuery<Post> query = em.createQuery(jpql, Post.class);
		query.setMaxResults(pageSize);
		query.setFirstResult(pageNumber * pageSize);
		return query.getResultList();
	}

	public Post getPostById(Long id) {
		return em.find(Post.class, id);
	}	


}
