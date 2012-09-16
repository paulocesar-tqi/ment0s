package com.claro.sccweb.service.impl;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccCriticaPrebillingDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccMotivoRejeicaoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccPreDominioDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccStatusArquivoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccStatusCdrDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccTipoArquivoDAO;
import com.claro.cobillingweb.persistence.entity.SccCriticaPrebilling;
import com.claro.cobillingweb.persistence.entity.SccMotivoRejeicao;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPreDominio;
import com.claro.cobillingweb.persistence.entity.SccStatusArquivo;
import com.claro.cobillingweb.persistence.entity.SccStatusCdr;
import com.claro.cobillingweb.persistence.entity.SccTipoArquivo;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.PesquisaDominiosService;
import com.claro.sccweb.service.ServiceException;


public class PesquisaDominiosServiceImpl extends AbstractService implements PesquisaDominiosService {

	private SccTipoArquivoDAO tipoArquivoDAO;
	
	private SccStatusArquivoDAO statusArquivoDAO;
	
	private SccOperadoraDAO sccOperadoraDAO;
	
	private SccPreDominioDAO preDominioDAO;
	
	private SccStatusCdrDAO statusCdrDAO;
	
	private SccCriticaPrebillingDAO criticaPrebillingDAO;
	
	private SccMotivoRejeicaoDAO motivoRejeicaoDAO;
	
	 
	public List<SccTipoArquivo> getTiposArquivo() throws ServiceException,DAOException {		
		return getTipoArquivoDAO().getAll();
	}

	public SccTipoArquivoDAO getTipoArquivoDAO() {
		return tipoArquivoDAO;
	}

	public void setTipoArquivoDAO(SccTipoArquivoDAO tipoArquivoDAO) {
		this.tipoArquivoDAO = tipoArquivoDAO;
	}

	public SccStatusArquivoDAO getStatusArquivoDAO() {
		return statusArquivoDAO;
	}

	public void setStatusArquivoDAO(SccStatusArquivoDAO statusArquivoDAO) {
		this.statusArquivoDAO = statusArquivoDAO;
	}
	
	

	public SccOperadoraDAO getSccOperadoraDAO() {
		return sccOperadoraDAO;
	}

	public void setSccOperadoraDAO(SccOperadoraDAO sccOperadoraDAO) {
		this.sccOperadoraDAO = sccOperadoraDAO;
	}

	 
	public List<SccStatusArquivo> getStatusArquivo() throws ServiceException,DAOException {
		return getStatusArquivoDAO().getAll();
	}

	 
	public List<SccOperadora> pesquisaOperadorasExternas() throws ServiceException, DAOException {
		return getSccOperadoraDAO().pesquisaOperadorasExternas();
	}

	 
	public List<SccOperadora> pesquisaHoldingClaro() throws ServiceException,DAOException {
		return getSccOperadoraDAO().pesquisaHoldingClaro();
	}

	 
	public List<SccOperadora> pequisaOperadorasClaro() throws ServiceException,DAOException {
		return getSccOperadoraDAO().pequisaOperadorasClaro();
	}
	
	public List<SccOperadora> pequisaOperadorasClaroComM() throws ServiceException,DAOException {
		return getSccOperadoraDAO().pequisaOperadorasClaroComM();
	}
	 
	public List<SccPreDominio> pesquisaPorTipoDominioPre(String tipo) throws DAOException {
		return getPreDominioDAO().pesquisaPorTipoDominio(tipo);
	}

	public SccPreDominioDAO getPreDominioDAO() {
		return preDominioDAO;
	}

	public void setPreDominioDAO(SccPreDominioDAO preDominioDAO) {
		this.preDominioDAO = preDominioDAO;
	}

	public SccStatusCdrDAO getStatusCdrDAO() {
		return statusCdrDAO;
	}

	public void setStatusCdrDAO(SccStatusCdrDAO statusCdrDAO) {
		this.statusCdrDAO = statusCdrDAO;
	}

	 
	public List<SccStatusCdr> getAllStatus() throws DAOException {
		return getStatusCdrDAO().getAll();
	}

	 
	public List<SccMotivoRejeicao> getAllMotivosRejeicao() throws DAOException {
		return getMotivoRejeicaoDAO().getAll();
	}

	public SccMotivoRejeicaoDAO getMotivoRejeicaoDAO() {
		return motivoRejeicaoDAO;
	}

	public void setMotivoRejeicaoDAO(SccMotivoRejeicaoDAO motivoRejeicaoDAO) {
		this.motivoRejeicaoDAO = motivoRejeicaoDAO;
	}

	public SccCriticaPrebillingDAO getCriticaPrebillingDAO() {
		return criticaPrebillingDAO;
	}

	public void setCriticaPrebillingDAO(SccCriticaPrebillingDAO criticaPrebillingDAO) {
		this.criticaPrebillingDAO = criticaPrebillingDAO;
	}

	 
	public List<SccCriticaPrebilling> getAllCriticaPreBilling() throws DAOException {
		return getCriticaPrebillingDAO().getAll();
	}

	
	
	
}
