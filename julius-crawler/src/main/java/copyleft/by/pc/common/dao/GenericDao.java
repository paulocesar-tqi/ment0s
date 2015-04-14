package copyleft.by.pc.common.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

public class GenericDao {
	
	@PersistenceContext
	@Autowired
	private EntityManager em;
	
	public List<String> getExternalIdsBySource(Integer sourceId) {
		
		String jpql = "SELECT p.externalId FROM Post p WHERE p.sourceId = :sourceId";
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		query.setParameter("sourceId", sourceId);
		
		return query.getResultList();
	}
}
