/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccIndicadorDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccIndicadoresDAO;
import com.claro.cobillingweb.persistence.entity.SccAgingIndicador;
import com.claro.cobillingweb.persistence.entity.SccAgingIndicadorPK;
import com.claro.cobillingweb.persistence.entity.SccIndicador;
import com.claro.cobillingweb.persistence.filtro.SccFiltroIndicador;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccIndicadorService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author vagner.souza
 *
 */
@Service
public class SccIndicadorServiceImpl extends AbstractService implements SccIndicadorService {
	
	@Autowired
	private SccIndicadorDAO sccIndicadorDAO;
	
	@Autowired
	private SccIndicadoresDAO sccIndicadoresDAO;

	@Override
	public Collection<SccAgingIndicador> pesquisaByFiltro(SccFiltroIndicador filtro) throws ServiceException, DAOException {
		
		return this.sccIndicadorDAO.pesquisaByFiltro(filtro);
	}

	@Override
	public SccAgingIndicador getSccAgingIndicadorByPk(SccAgingIndicadorPK id)throws ServiceException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Collection<SccIndicador> gerarCombo(SccFiltroIndicador filtro)	throws ServiceException, DAOException {

		return this.sccIndicadorDAO.gerarCombo(filtro);
	}
	
	@Override
	public Collection<SccIndicador> montarComboBox(String dsPeriodicidade, String tipo) throws ServiceException, DAOException{
		return this.sccIndicadorDAO.montarComboBox(dsPeriodicidade, tipo);
		
	}
	
	@Override
	public SccAgingIndicador getSccAgingIndicador(String idIndicador, Long sqAgingIndicador) throws ServiceException, DAOException {
		
		return this.sccIndicadorDAO.getSccAgingIndicador(idIndicador, sqAgingIndicador);
	}


	@Transactional
	public void update(SccAgingIndicador entity) throws DAOException {
		this.sccIndicadorDAO.update(entity);		
	}

	@Transactional
	public void delete(SccAgingIndicador entity) throws DAOException {
		this.sccIndicadorDAO.delete(entity);
		
	}
	
	@Transactional
	public void excluirByParam(String idIndicador, Long sqAgingIndicador) throws DAOException{
		this.sccIndicadorDAO.excluirByParam(idIndicador, sqAgingIndicador);
	}

	@Transactional
	public void saveOrUpdate(SccAgingIndicador entity) throws DAOException {
		
		Date data = new Date();
		if(entity.getDtCriacao() != null){
			entity.setDtAtualizacao(new Date());
			this.sccIndicadorDAO.merge(entity);
		}else{
			entity.setDtAtualizacao(data);
			entity.setDtCriacao(data);
			this.sccIndicadorDAO.create(entity);
		}
		
	}

	@Transactional
	public void excluirAgingIndicador(String value) throws ServiceException, DAOException {
		
		this.sccIndicadorDAO.excluirAgingIndicador(value);
		
	}


	@Override
	public Collection<SccIndicador> pesquisaByFiltroIndicador(SccFiltroIndicador filtro) throws ServiceException, DAOException {
		
		return this.sccIndicadoresDAO.pesquisaByFiltro(filtro);
	}
	
	@Override
	public SccIndicador findById(String cdIndicador) throws DAOException{
		return this.sccIndicadorDAO.findById(cdIndicador);
	}

	@Transactional
	public void update(SccIndicador entity) throws DAOException {
		
		this.sccIndicadoresDAO.update(entity);
		
	}

	@Transactional
	public void delete(SccIndicador entity) throws DAOException {
		
		this.sccIndicadoresDAO.delete(entity);
		
	}

	@Transactional
	public void saveOrUpdate(SccIndicador entity) throws DAOException {
		
		Date dt = new Date();
		if(entity.getDtCriacao() != null){
			this.sccIndicadoresDAO.merge(entity);
		}else{
			entity.setDtCriacao(dt);
			entity.setDtAtualizacao(dt);
			this.sccIndicadoresDAO.create(entity);
		}
	}

	@Override
	public SccIndicador getSccIndicador(String value) throws ServiceException,	DAOException {
		
		return this.sccIndicadoresDAO.getSccIndicador(value);
	}

	@Override
	public void excluirIndicador(String value) throws ServiceException, DAOException {
		
	}
	
	@Override
	public SccIndicador getPkIndicador(String pk) throws ServiceException, DAOException {
		
		return this.sccIndicadoresDAO.getByPk(pk, SccIndicador.class);
	}
	
	public void excluirByPk(String value) throws ServiceException, DAOException{
		this.sccIndicadoresDAO.excluirByPk(value);
	}

	public void setSccIndicadorDAO(SccIndicadorDAO sccIndicadorDAO) {
		this.sccIndicadorDAO = sccIndicadorDAO;
	}

	
	
	public void setSccIndicadoresDAO(SccIndicadoresDAO sccIndicadoresDAO) {
		this.sccIndicadoresDAO = sccIndicadoresDAO;
	}

	
	
	
	
	

	
	
	
	

}
