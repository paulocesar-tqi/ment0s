package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccAssociacaoRelatorioGrupoDAO;
import com.claro.cobillingweb.persistence.entity.SccAssociacaoRelatorioGrupo;
import com.claro.cobillingweb.persistence.service.ServiceException;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccAssociacaoRelatorioGrupoService;

@Service
public class SccAssociacaoRelatorioGrupoServiceImpl extends AbstractService implements	SccAssociacaoRelatorioGrupoService {
	
	
	@Autowired
	private SccAssociacaoRelatorioGrupoDAO sccAssociacaoRelatorioGrupoDAO;

	@Override
	public List<SccAssociacaoRelatorioGrupo> buscarGrupoAssociado(Long id) throws ServiceException, DAOException{
		
		return this.sccAssociacaoRelatorioGrupoDAO.buscarGrupoAssociado(id);
		
	}
	
	@Override
	public SccAssociacaoRelatorioGrupo editEntity(Long sqGrupo, Long sqRelatorio, Date dtInicioVigencia) throws ServiceException, DAOException{
		
		return this.sccAssociacaoRelatorioGrupoDAO.editEntity(sqGrupo, sqRelatorio, dtInicioVigencia);
	}
	
	@Override
	@Transactional
	public void salvarEntity(SccAssociacaoRelatorioGrupo entity) throws ServiceException, DAOException{
		
		this.sccAssociacaoRelatorioGrupoDAO.create(entity);
	}
	
	@Override
	@Transactional
	public void deleteEntity(Long sqGrupo, Long sqRelatorio, Date dtInicioVigencia) throws ServiceException, DAOException{
		
		this.sccAssociacaoRelatorioGrupoDAO.deleteEntity(sqGrupo, sqRelatorio, dtInicioVigencia);
	}
	

	public void setSccAssociacaoRelatorioGrupoDAO(SccAssociacaoRelatorioGrupoDAO sccAssociacaoRelatorioGrupoDAO) {
		this.sccAssociacaoRelatorioGrupoDAO = sccAssociacaoRelatorioGrupoDAO;
	}
	
	
	

}
