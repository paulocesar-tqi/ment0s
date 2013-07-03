/**
 * 
 */
package com.claro.cobillingweb.persistence.dao;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.claro.cobillingweb.persistence.entity.FwjBaseEntidade;
import com.claro.cobillingweb.persistence.service.ServiceException;

import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * @author rodvagne
 *
 */
public abstract class FwjBaseDaoHibernateImpl <Entidade extends FwjBaseEntidade, ID extends Serializable> extends HibernateDaoSupport implements FwjBaseDaoHibernate<Entidade,ID> {
	private final Class<Entidade> persistentClass;

	@Autowired
	protected void initSessionFactory(
			@Qualifier("sessionFactory") SessionFactory factory) {

		setSessionFactory(factory);
	}

	@SuppressWarnings("unchecked")
	public FwjBaseDaoHibernateImpl() {
		this.persistentClass = (Class<Entidade>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<Entidade> getPersistentClass() {

		return persistentClass;
	}

	protected LockMode getLock(boolean lock) {

		return lock ? LockMode.UPGRADE_NOWAIT : LockMode.NONE;
	}

	@Override
	public void clear() {

		getHibernateTemplate().clear();
	}

	@Override
	public void delete(Object pk) throws DAOException, ServiceException {

		getHibernateTemplate().delete(pk);
		getHibernateTemplate().flush();
	}

	@Override
	public void evict(Entidade entity) {

		getHibernateTemplate().evict(entity);
	}

	@Override
	public Collection<Entidade> findAll() {

		List<Entidade> ret = (List<Entidade>) getHibernateTemplate().loadAll(this.getPersistentClass());
		return ret;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public Entidade findById(ID id, boolean lock) {

		Entidade entity;
		if (lock)
			entity = (Entidade) getHibernateTemplate().get(
					getPersistentClass(), id, LockMode.UPGRADE);
		else
			entity = (Entidade) getHibernateTemplate().get(
					getPersistentClass(), id);
		Object obj = getHibernateTemplate().get(getPersistentClass(), id);
		if (obj != null) {
			entity = (Entidade) obj;
		}
		getHibernateTemplate().flush();
		return entity;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Entidade findById(Serializable id) throws DAOException, ServiceException{
		
		Entidade entity = null;
		Object obj = getHibernateTemplate().get(getPersistentClass(), id);
		if (obj != null) {
			entity = (Entidade) obj;
			getHibernateTemplate().flush();
		}
		return entity;
	}
	

	@Override
	public void flush() {

		getHibernateTemplate().flush();
	}

	@Override
	public void lock(Entidade entity, boolean lock) {

		this.getHibernateTemplate().lock(entity, getLock(lock));
	}

	@Override
	public Entidade merge(Entidade entity) throws DAOException, ServiceException {

		Entidade e = (Entidade) getHibernateTemplate().merge(entity);
		getHibernateTemplate().flush();
		return e;
	}

	@Override
	public Serializable save(Entidade entity) throws DAOException, ServiceException {

		Serializable id = getHibernateTemplate().save(entity);
		getHibernateTemplate().flush();
		return id;
	}
	
	@Override
	public Serializable create(Entidade entity) throws DAOException, ServiceException{
		
		return save(entity);
	}

	@Override
	public void saveOrUpdate(Entidade entity) throws DAOException, ServiceException {

		getHibernateTemplate().saveOrUpdate(entity);
		getHibernateTemplate().flush();
	}

	@Override
	public void update(Entidade entity) throws DAOException, ServiceException {

		getHibernateTemplate().update(entity);
		getHibernateTemplate().flush();
	}

	@Override
	public void update(Collection<Entidade> list) throws DAOException, ServiceException {

		int index = 0;
		for (Entidade entidade : list) {
			++index;
			getHibernateTemplate().update(entidade);
			if (index % 50 == 0) {
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
	}

	public Long idAtual(String sql) {

		Query query = this.getSession().createSQLQuery(sql);
		Long idAtual = (Long) query.uniqueResult();
		return idAtual;
	}

	@Override
	public BigDecimal idAtualLong(String sql) {

		Query query = this.getSession().createSQLQuery(sql);
		return (BigDecimal) query.uniqueResult();
	}

	public Long proximoId(String maxId) {

		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.setProjection(Projections.max(maxId));
		Long id = null;
		id = (Long) criteria.uniqueResult();
		if (id == null) {
			return 1L;
		}
		return id;
	}

	@Override
	public Long getSequence(String idCampo) {

		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.setProjection(Projections.max(idCampo));
		return (Long) criteria.uniqueResult();
	}

	@Override
	public void save(Collection<Entidade> list) throws DAOException, ServiceException {

		int index = 0;
		for (Entidade entidade : list) {
			++index;
			getHibernateTemplate().save(entidade);
			if (index % 50 == 0) {
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
	}

	@Override
	public void saveOrUpdate(Collection<Entidade> list)	throws DAOException, ServiceException {

		int index = 0;
		for (Entidade entidade : list) {
			++index;
			getHibernateTemplate().saveOrUpdate(entidade);
			if (index % 50 == 0) {
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
	}

}
