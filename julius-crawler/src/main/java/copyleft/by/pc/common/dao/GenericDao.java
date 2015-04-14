package copyleft.by.pc.common.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import copyleft.by.pc.common.entities.Source;

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
}
