package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccAliquotaImpostoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccAssinanteCriticaDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccCentroDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccContaContabilDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccCtlParalizacaoPlatDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccFaixaPenalidadeDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccMapaStatusDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccMotivoRejeicaoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccPenalidadePorRejeicaoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccPreDominioDAO;
import com.claro.cobillingweb.persistence.entity.SccAliquotaImposto;
import com.claro.cobillingweb.persistence.entity.SccAssinanteCritica;
import com.claro.cobillingweb.persistence.entity.SccCentro;
import com.claro.cobillingweb.persistence.entity.SccContaContabil;
import com.claro.cobillingweb.persistence.entity.SccCtlParalizacaoPlat;
import com.claro.cobillingweb.persistence.entity.SccFaixaPenalidade;
import com.claro.cobillingweb.persistence.entity.SccMapaStatus;
import com.claro.cobillingweb.persistence.entity.SccMapaStatusPK;
import com.claro.cobillingweb.persistence.entity.SccMotivoRejeicao;
import com.claro.cobillingweb.persistence.entity.SccPenalidadePorRejeicao;
import com.claro.cobillingweb.persistence.entity.SccPenalidadePorRejeicaoPK;
import com.claro.cobillingweb.persistence.entity.SccPreDominio;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.AdminService;

public class AdminServiceImpl extends AbstractService implements AdminService {

	private SccFaixaPenalidadeDAO faixaPenalidadeDAO;
	private SccAssinanteCriticaDAO assinanteCriticaDAO;
	private SccMapaStatusDAO mapaStatusDAO;
	private SccMotivoRejeicaoDAO motivoRejeicaoDAO;
	private SccAliquotaImpostoDAO aliquotaImpostoDAO;
	private SccCtlParalizacaoPlatDAO ctlParalizacaoPlatDAO;
	private SccPreDominioDAO preDominioDAO;
	private SccContaContabilDAO contaContabilDAO;
	private SccCentroDAO centroDAO;
	private SccPenalidadePorRejeicaoDAO penalidadePorRejeicaoDAO;
	
	
	
	
	public SccPenalidadePorRejeicaoDAO getPenalidadePorRejeicaoDAO() {
		return penalidadePorRejeicaoDAO;
	}

	public void setPenalidadePorRejeicaoDAO(
			SccPenalidadePorRejeicaoDAO penalidadePorRejeicaoDAO) {
		this.penalidadePorRejeicaoDAO = penalidadePorRejeicaoDAO;
	}

	public List<SccFaixaPenalidade> pesquisarPenalidadePorTipo(String tipo) throws DAOException {
		return getFaixaPenalidadeDAO().pesquisarPorTipo(tipo);
	}
	
	@Transactional
	public void delete(SccFaixaPenalidade entity) throws DAOException {
		getFaixaPenalidadeDAO().delete(entity);
	}
	
	@Transactional
	public void update(SccFaixaPenalidade entity) throws DAOException {
		getFaixaPenalidadeDAO().update(entity);
	}
	
	@Transactional
	public void create(SccFaixaPenalidade entity) throws DAOException {
		getFaixaPenalidadeDAO().create(entity);
	}
	
	public SccFaixaPenalidadeDAO getFaixaPenalidadeDAO() {
		return faixaPenalidadeDAO;
	}
	
	public void setFaixaPenalidadeDAO(SccFaixaPenalidadeDAO faixaPenalidadeDAO) {
		this.faixaPenalidadeDAO = faixaPenalidadeDAO;
	}
	
	public SccAssinanteCriticaDAO getAssinanteCriticaDAO() {
		return assinanteCriticaDAO;
	}
	
	public void setAssinanteCriticaDAO(SccAssinanteCriticaDAO assinanteCriticaDAO) {
		this.assinanteCriticaDAO = assinanteCriticaDAO;
	}
	
	public List<SccAssinanteCritica> pesquisaCritica(String cdCritica) throws DAOException {
		return getAssinanteCriticaDAO().pesquisaCritica(cdCritica);
	}
	
	@Transactional
	
	public void delete(SccAssinanteCritica entity) throws DAOException {
		getAssinanteCriticaDAO().delete(entity);
	}
	
	@Transactional
	
	public void update(SccAssinanteCritica entity) throws DAOException {
		getAssinanteCriticaDAO().update(entity);
	}
	
	@Transactional
	
	public void create(SccAssinanteCritica entity) throws DAOException {
		getAssinanteCriticaDAO().create(entity);
	}
	
	@Transactional
	
	public void delete(SccMapaStatus entity) throws DAOException {
		getMapaStatusDAO().delete(entity);
	}
	
	@Transactional
	
	public void update(SccMapaStatus entity) throws DAOException {
		getMapaStatusDAO().update(entity);
	}
	
	@Transactional
	
	public void create(SccMapaStatus entity) throws DAOException {
		getMapaStatusDAO().create(entity);
	}
	
	public List<SccMapaStatus> pesquisaMapaStatus(Long cdStatusInicial,Long cdStatusFinal) throws DAOException {		
		return getMapaStatusDAO().pesquisaMapaStatus(cdStatusInicial, cdStatusFinal);
	}
	
	public SccMapaStatusDAO getMapaStatusDAO() {
		return mapaStatusDAO;
	}
	
	public void setMapaStatusDAO(SccMapaStatusDAO mapaStatusDAO) {
		this.mapaStatusDAO = mapaStatusDAO;
	}
	
	public SccMapaStatus getMapaByPk(SccMapaStatusPK pk) throws DAOException {
		return getMapaStatusDAO().getByPk(pk, SccMapaStatus.class);		
	}
	
	@Transactional
	
	public void delete(SccMotivoRejeicao entity) throws DAOException {
		getMotivoRejeicaoDAO().delete(entity);
	}
	
	@Transactional
	
	public void update(SccMotivoRejeicao entity) throws DAOException {
		getMotivoRejeicaoDAO().update(entity);
	}
	
	@Transactional
	
	public void create(SccMotivoRejeicao entity) throws DAOException {
		getMotivoRejeicaoDAO().create(entity);
	}
	
	public SccMotivoRejeicaoDAO getMotivoRejeicaoDAO() {
		return motivoRejeicaoDAO;
	}
	
	public void setMotivoRejeicaoDAO(SccMotivoRejeicaoDAO motivoRejeicaoDAO) {
		this.motivoRejeicaoDAO = motivoRejeicaoDAO;
	}
	
	public SccMotivoRejeicao getMotivoRejeicaoByPk(String cdMotivoRejeicao) throws DAOException {
		return getMotivoRejeicaoDAO().getByPk(cdMotivoRejeicao, SccMotivoRejeicao.class);
	}
	
	public SccAliquotaImpostoDAO getAliquotaImpostoDAO() {
		return aliquotaImpostoDAO;
	}
	
	public void setAliquotaImpostoDAO(SccAliquotaImpostoDAO aliquotaImpostoDAO) {
		this.aliquotaImpostoDAO = aliquotaImpostoDAO;
	}
	
	public List<SccAliquotaImposto> pesquisaAliquotas(String noImposto,String inTipoServico) throws DAOException {
		return getAliquotaImpostoDAO().pesquisaAliquotas(noImposto, inTipoServico);
	}
	
	@Transactional
	
	public void update(SccAliquotaImposto entity) throws DAOException {
		getAliquotaImpostoDAO().update(entity);
	}
	
	@Transactional
	
	public void create(SccAliquotaImposto entity) throws DAOException {
		getAliquotaImpostoDAO().create(entity);
	}
	
	@Transactional
	
	public void delete(SccAliquotaImposto entity) throws DAOException {
		getAliquotaImpostoDAO().delete(entity);
	}
	
	public List<SccCtlParalizacaoPlat> pesquisaParalizacoes(SccCtlParalizacaoPlat filtro, Date dataInicial, Date dataFinal) throws DAOException {		
		return getCtlParalizacaoPlatDAO().pesquisaParalizacoes(filtro, dataInicial, dataFinal);
	}
	
	public SccCtlParalizacaoPlatDAO getCtlParalizacaoPlatDAO() {
		return ctlParalizacaoPlatDAO;
	}
	
	public void setCtlParalizacaoPlatDAO(SccCtlParalizacaoPlatDAO ctlParalizacaoPlatDAO) {
		this.ctlParalizacaoPlatDAO = ctlParalizacaoPlatDAO;
	}
	
	@Transactional
	
	public void update(SccCtlParalizacaoPlat entity) throws DAOException {
		getCtlParalizacaoPlatDAO().update(entity);
	}
	
	@Transactional
	
	public void create(SccCtlParalizacaoPlat entity) throws DAOException {
		getCtlParalizacaoPlatDAO().create(entity);
	}
	
	@Transactional
	
	public void delete(SccCtlParalizacaoPlat entity) throws DAOException {
		getCtlParalizacaoPlatDAO().delete(entity);
	}
	
	@Transactional
	
	public void update(SccPreDominio entity) throws DAOException {
		getPreDominioDAO().update(entity);
	}
	
	@Transactional
	
	public void create(SccPreDominio entity) throws DAOException {
		getPreDominioDAO().create(entity);
	}
	
	@Transactional
	
	public void delete(SccPreDominio entity) throws DAOException {
		getPreDominioDAO().delete(entity);
	}
	
	public SccPreDominioDAO getPreDominioDAO() {
		return preDominioDAO;
	}
	
	public void setPreDominioDAO(SccPreDominioDAO preDominioDAO) {
		this.preDominioDAO = preDominioDAO;
	}
	
	public List<SccPreDominio> getAllPreDominio() throws DAOException {	
		return getPreDominioDAO().getAll();
	}
	
	public List<SccPreDominio> getTipos() throws DAOException {
		return getPreDominioDAO().getTipos();
	}
	
	public SccContaContabilDAO getContaContabilDAO() {
		return contaContabilDAO;
	}
	
	public void setContaContabilDAO(SccContaContabilDAO contaContabilDAO) {
		this.contaContabilDAO = contaContabilDAO;
	}
	
	public List<SccContaContabil> getAllContaContabil() throws DAOException {
		return getContaContabilDAO().getAll();
	}
	
	@Transactional
	public void update(SccContaContabil entity) throws DAOException {
		getContaContabilDAO().update(entity);
	}
	
	@Transactional
	public void create(SccContaContabil entity) throws DAOException {
		getContaContabilDAO().create(entity);
	}
	
	@Transactional
	public void delete(SccContaContabil entity) throws DAOException {
		getContaContabilDAO().delete(entity);
	}
	
	public SccCentroDAO getCentroDAO() {
		return centroDAO;
	}
	
	public void setCentroDAO(SccCentroDAO centroDAO) {
		this.centroDAO = centroDAO;
	}
	
	public List<SccCentro> getAllCentro() throws DAOException {
		return centroDAO.getAll();
	}
	
	@Transactional
	public void update(SccCentro entity) throws DAOException {
		getCentroDAO().update(entity);
	}
	
	@Transactional
	public void create(SccCentro entity) throws DAOException {
		getCentroDAO().create(entity);
	}
	
	@Transactional
	public void delete(SccCentro entity) throws DAOException {
		getCentroDAO().delete(entity);
	}
	
	public List<SccPenalidadePorRejeicao> pesquisarPenalidadePorRejeicao(String cdEOTLd, String cdMotivoRejeicao) throws DAOException {
		return penalidadePorRejeicaoDAO.pesquisar(cdEOTLd, cdMotivoRejeicao);
	}

	public SccPenalidadePorRejeicao getSccPenalidadePorRejeicaoByPk(SccPenalidadePorRejeicaoPK pk) throws DAOException {
		return getPenalidadePorRejeicaoDAO().getByPk(pk, SccPenalidadePorRejeicao.class);		
	}

	
	@Transactional
	public void update(SccPenalidadePorRejeicao entity) throws DAOException {
		getPenalidadePorRejeicaoDAO().update(entity);
	}
	
	@Transactional
	public void create(SccPenalidadePorRejeicao entity) throws DAOException {
		getPenalidadePorRejeicaoDAO().create(entity);
	}
	
	@Transactional
	public void delete(SccPenalidadePorRejeicao entity) throws DAOException {
		getPenalidadePorRejeicaoDAO().delete(entity);
	}
	
}
