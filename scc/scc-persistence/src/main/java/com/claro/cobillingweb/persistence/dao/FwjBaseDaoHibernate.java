/**
 * 
 */
package com.claro.cobillingweb.persistence.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import com.claro.cobillingweb.persistence.entity.FwjBaseEntidade;
import com.claro.cobillingweb.persistence.service.ServiceException;

/**
 * @author rodvagne
 *
 */
public interface FwjBaseDaoHibernate <Entidade extends FwjBaseEntidade, ID extends Serializable>{
	

public Entidade findById(ID id, final boolean lock);

public Entidade findById(Serializable id) throws DAOException, ServiceException;

public Collection<Entidade> findAll();

public void flush();

public void clear();

public void evict(Entidade entity);

public void delete(Object pk) throws DAOException, ServiceException;

public Entidade merge(Entidade entity) throws DAOException, ServiceException;

public Serializable save(Entidade entity) throws DAOException, ServiceException;

public void save(Collection<Entidade> list) throws DAOException, ServiceException;

public void saveOrUpdate(Entidade entity) throws DAOException, ServiceException;

public void saveOrUpdate(Collection<Entidade> list) throws DAOException, ServiceException;

public void update(Entidade entity) throws DAOException, ServiceException;
public void update(Collection<Entidade> list) throws DAOException, ServiceException;

public void lock(Entidade entity, boolean lock);
public Long idAtual(String sql);
public BigDecimal idAtualLong(String sql);
public Long proximoId(String maxId);
public Long getSequence(String idCampo);

public Serializable create(Entidade entity) throws DAOException, ServiceException;

}
