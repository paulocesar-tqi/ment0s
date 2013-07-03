/**
 * 
 */
package com.claro.cobillingweb.persistence.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.FwjBaseEntidade;

/**
 * @author rodvagne
 *
 */
public interface FwjBaseServico<Entidade extends FwjBaseEntidade> {
	
/*	@SuppressWarnings("restriction")
	public Entidade findById(ID id, final boolean lock);
*/
	public Entidade findById(Serializable id) throws DAOException, ServiceException;

	public Collection<Entidade> findAll();

	public void flush();

	public void clear();

	public void evict(Entidade entity);

	public void delete(Serializable pk) throws DAOException, ServiceException;

	public Entidade merge(Entidade entity) throws DAOException, ServiceException;

	public Serializable save(Entidade entity) throws DAOException, ServiceException;

	public void saveOrUpdate(Entidade entity) throws DAOException, ServiceException;

	public void save(List<Entidade> list) throws DAOException, ServiceException;

	public void update(List<Entidade> list) throws DAOException, ServiceException;

	public void saveOrUpdate(List<Entidade> list) throws DAOException, ServiceException;

	public void update(Entidade entity) throws DAOException, ServiceException;

	public void lock(Entidade entity, boolean lock);

	public Long idAtual(String sql);

	public BigDecimal idAtualLong(String sql);

	public Long proximoId(String maxId);

	public Long getSequence(String idCampo);

	@SuppressWarnings("rawtypes")
	public Boolean cofereMapa(Map mapa, String vlr);

	@SuppressWarnings("rawtypes")
	public Boolean cofereMapa(Map mapa, Long vlr);

	@SuppressWarnings("rawtypes")
	public Integer verificaMapa(Map mapa, String vlr);

	@SuppressWarnings("rawtypes")
	public Integer verificaMapa(Map mapa, Long vlr);

	public void gridUpdate(List<Entidade> list) throws DAOException,
	ServiceException;

}
