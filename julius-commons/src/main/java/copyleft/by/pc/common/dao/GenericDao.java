package copyleft.by.pc.common.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import copyleft.by.pc.common.entities.NotificationPlan;
import copyleft.by.pc.common.entities.Post;
import copyleft.by.pc.common.entities.Source;
import copyleft.by.pc.common.entities.User;
import copyleft.by.pc.common.entities.Word;

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

	public User getUserById(String id) {
		return em.find(User.class, id);
	}	

	@Transactional
	public void updateUser(User user) {
		em.merge(user);
		em.flush();
	}	

	public List<String> getUserIdsByPlatform(String platformId) {
		
		String jpql = "SELECT u.regId FROM User u WHERE u.platformId = :platformId";
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		query.setParameter("platformId", platformId);
		
		return query.getResultList();
	}
	
	public List<Post> getPostsByFilter(int pageNumber, int pageSize) {
		
		String jpql = "SELECT new Post(p.id, p.html, p.publicationDate, p.sourceId, p.url) FROM Post p ORDER BY p.publicationDate DESC";
		TypedQuery<Post> query = em.createQuery(jpql, Post.class);
		query.setMaxResults(pageSize);
		query.setFirstResult(pageNumber * pageSize);
		return query.getResultList();
	}

	public Post getPostById(Long id) {
		return em.find(Post.class, id);
	}
	
	public NotificationPlan getWordsAndIds() {
		String sqlIdsNoFilter = " select platform, GROUP_CONCAT(reg_id SEPARATOR ' ') from user where active_notifications = 1 and active_filter = 0 " +
								" group by platform";
		
		String sqlWords = "select word, GROUP_CONCAT(id_android SEPARATOR ' ') id_android, GROUP_CONCAT(id_ios SEPARATOR ' ') id_ios, GROUP_CONCAT(id_wp SEPARATOR ' ') id_wp from ( " +
							" select a.word1 word, IF(a.platform = 'android', a.reg_id, null) id_android, IF(a.platform = 'ios', a.reg_id, null) id_ios, IF(a.platform = 'wp', a.reg_id, null) id_wp from user a where a.word1 is not null and a.active_notifications = 1 and a.active_filter = 1 " +
							" union all " +
							" select a.word2 word, IF(a.platform = 'android', a.reg_id, null) id_android, IF(a.platform = 'ios', a.reg_id, null) id_ios, IF(a.platform = 'wp', a.reg_id, null) id_wp from user a where a.word2 is not null and a.active_notifications = 1 and a.active_filter = 1 " + 
							" union all " + 
							" select a.word3 word, IF(a.platform = 'android', a.reg_id, null) id_android, IF(a.platform = 'ios', a.reg_id, null) id_ios, IF(a.platform = 'wp', a.reg_id, null) id_wp from user a where a.word3 is not null and a.active_notifications = 1 and a.active_filter = 1 " +
						 ") words " +
						 " GROUP BY word";
		
		NotificationPlan result = new NotificationPlan();
		
		Query query = em.createNativeQuery(sqlIdsNoFilter);
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		if(list != null) {
			for(Object[] obj : list) {
				if("android".equals(obj[0])) {
					result.setIdsAndroidNoFilter(new ArrayList<String>(Arrays.asList(((String)obj[1]).split(" "))));
				} else if("ios".equals(obj[0])) {
					result.setIdsIosNoFilter(new ArrayList<String>(Arrays.asList(((String)obj[1]).split(" "))));
				} else if("wp".equals(obj[0])) {
					result.setIdsWpNoFilter(new ArrayList<String>(Arrays.asList(((String)obj[1]).split(" "))));
				}
			}
		}
		
		query = em.createNativeQuery(sqlWords);
		@SuppressWarnings("unchecked")
		List<Object[]> list2 = query.getResultList();
		if(list2 != null) {
			ArrayList<Word> wordList = new ArrayList<Word>();
			for(Object[] obj : list2) {
				ArrayList<String> listAndroid = new ArrayList<String>();
				ArrayList<String> listIos = new ArrayList<String>();
				ArrayList<String> listWp = new ArrayList<String>();
				if(obj[1] != null) 
					listAndroid = new ArrayList<String>(Arrays.asList(((String)obj[1]).split(" ")));
				if(obj[2] != null) 
					listIos = new ArrayList<String>(Arrays.asList(((String)obj[2]).split(" ")));
				if(obj[3] != null) 
					listWp = new ArrayList<String>(Arrays.asList(((String)obj[3]).split(" ")));
				
				wordList.add(new Word((String)obj[0], listAndroid, listIos, listWp));
			}
			result.setWords(wordList);
		}
		return result;
	}


}
