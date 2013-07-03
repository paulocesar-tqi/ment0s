/**
 * 
 */
package com.claro.cobillingweb.persistence.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernate;
import com.claro.cobillingweb.persistence.entity.FwjBaseEntidade;


/**
 * @author rodvagne
 *
 */
@SuppressWarnings("rawtypes")
public abstract class FwjBaseServicoImpl<Entidade extends FwjBaseEntidade, DAO extends FwjBaseDaoHibernate>
		implements FwjBaseServico<Entidade> {

	private DAO dao;

	@Override
	public void clear() {

		this.dao.clear();
	}

	@Override
	public void delete(Serializable pk) throws DAOException, ServiceException {

		this.dao.delete(pk);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void evict(FwjBaseEntidade entity) {

		this.dao.evict(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Entidade> findAll() {

		List list = (List) this.dao.findAll();
		if (list != null && !list.isEmpty()
				&& list.get(0) instanceof Comparable) {
			Collections.sort(list);
		}
		return list;
	}

/*	@SuppressWarnings({ "unchecked", "restriction" })
	@Override
	public Entidade findById(ID id, boolean lock) {

		return (Entidade) this.dao.findById(id, lock);
	}
*/
	@SuppressWarnings("unchecked")
	@Override
	public Entidade findById(Serializable id) throws DAOException, ServiceException {

		return (Entidade) this.dao.findById(id);
	}

	@Override
	public void flush() {

		this.dao.flush();
	}

	@Override
	public void lock(FwjBaseEntidade entity, boolean lock) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public Entidade merge(FwjBaseEntidade entity) throws DAOException, ServiceException {

		return (Entidade) this.dao.merge(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(List<Entidade> list) throws DAOException, ServiceException {
		this.dao.save(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveOrUpdate(List<Entidade> list) throws DAOException, ServiceException {
		this.dao.saveOrUpdate(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveOrUpdate(Entidade entity) throws DAOException, ServiceException {
		this.dao.saveOrUpdate(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(List<Entidade> list) throws DAOException, ServiceException {
		this.dao.update(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Serializable save(FwjBaseEntidade entity) throws DAOException, ServiceException {
		Serializable id = null;
		try {
			id = this.dao.save(entity);
		} catch (DataIntegrityViolationException e) {
			if (e.toString().indexOf("not-null") != -1) {
				throw new DAOException(
						"Registro não pode ser nulo ou vazio");
			} else {
				throw new DAOException(
						"Registro não pode ser inserido, um ou mais campos tentaram quebrar a integridade. Por favor, verifique campos chaves duplicados ou campos nulos");
			}
		} catch (ConstraintViolationException e) {
			throw new DAOException(e.getMessage());
		} catch (HibernateException e) {
			throw new DAOException(
					"Erro desconhecido. Entre em contato com o Administrador do sistema");
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(FwjBaseEntidade entity) throws DAOException, ServiceException {

		this.dao.update(entity);
	}

	public Long idAtual(String sql) {

		return this.dao.idAtual(sql);
	}

	@Override
	public BigDecimal idAtualLong(String sql) {

		return this.dao.idAtualLong(sql);
	}

	@Override
	public Long proximoId(String maxId) {

		return this.dao.proximoId(maxId);
	}

	@Override
	public Long getSequence(String idCampo) {

		return this.dao.getSequence(idCampo);
	}

	@Override
	public Boolean cofereMapa(Map mapa, String vlr) {

		Boolean result = false;
		if (vlr == null) {
			return result;
		}
		if (mapa.containsKey(vlr))
			return true;
		return result;
	}

	@Override
	public Boolean cofereMapa(Map mapa, Long vlr) {

		Boolean result = false;
		if (vlr == null) {
			return result;
		}
		if (mapa.containsKey(vlr))
			return true;
		return result;
	}

	@Override
	public Integer verificaMapa(Map mapa, Long vlr) {

		Integer result = 1;
		if (vlr == null) {
			return result;
		}
		if (mapa.containsKey(vlr))
			return 0;
		return 2;
	}

	
	@Override
	public Integer verificaMapa(Map mapa, String vlr) {

		Integer result = 1;
		if (vlr == null) {
			return result;
		}
		if (mapa.containsKey(vlr))
			return 0;
		return 2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void gridUpdate(List<Entidade> list) throws DAOException, ServiceException {
		final int CRIAR = 1;
		final int REMOVER = 2;
		final int ATUALIZAR = 3;
		try {
			for (Entidade entidade : list) {
				switch (entidade.getMetodo()) {
				case CRIAR:
					entidade.setMetodo(0);
					this.dao.save(entidade);
					break;
				case REMOVER:
					entidade.setMetodo(0);
					this.dao.delete(entidade);
					break;
				case ATUALIZAR:
					entidade.setMetodo(0);
					this.dao.update(entidade);
					break;
				default:
					break;
				}
			}
		} catch (DataIntegrityViolationException e) {
			if (e.toString().indexOf("not-null") != -1) {
				throw new DAOException(
						"Registro não pode ser nulo ou vazio");
			} else if (e.toString().indexOf(
					"Could not execute JDBC batch update") != -1) {
				throw new DAOException(
						"Registro não pode ser atualizado ou removido, ele está sendo associado a outro item dentro do Sistema.");
			} else {
				throw new DAOException(
						"Registro não pode ser inserido, um ou mais campo tentou quebrar a integridade. Por favor, verifique campos chaves duplicados ou campos nulos");
			}
		} catch (ConstraintViolationException e) {
			throw new DAOException(e.getMessage());
		} catch (HibernateException e) {
			throw new DAOException(
					"Erro desconhecido. Entre em contato com o Administrador do sistema");
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/******************* GETTERS e SETTERS ****************************/
	/**
	 * @return the dao
	 */
	public DAO getDao() {

		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO dao) {

		this.dao = dao;
	}

}
