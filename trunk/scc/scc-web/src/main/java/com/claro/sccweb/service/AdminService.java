package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
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
import com.claro.cobillingweb.persistence.entity.SccPreDominio;

/**
 * Serviço para unir cadastro básicos pertinentes à administração do sistema.
 *
 */
public interface AdminService {

	public List<SccFaixaPenalidade> pesquisarPenalidadePorTipo(String tipo) throws DAOException;
	
	public void delete(SccFaixaPenalidade entity) throws DAOException;
	
	public void update(SccFaixaPenalidade entity) throws DAOException;
	
	public void create(SccFaixaPenalidade entity) throws DAOException;
	
	public List<SccAssinanteCritica> pesquisaCritica(String cdCritica) throws DAOException;
	
	public void delete(SccAssinanteCritica entity) throws DAOException;
	
	public void update(SccAssinanteCritica entity) throws DAOException;
	
	public void create(SccAssinanteCritica entity) throws DAOException;
	
    public void delete(SccMapaStatus entity) throws DAOException;
	
	public void update(SccMapaStatus entity) throws DAOException;
	
	public void create(SccMapaStatus entity) throws DAOException;
	
	public List<SccMapaStatus> pesquisaMapaStatus(Long cdStatusInicial,Long cdStatusFinal) throws DAOException;
	
	public SccMapaStatus getMapaByPk(SccMapaStatusPK pk) throws DAOException;
	
	public void update(SccMotivoRejeicao entity) throws DAOException;
	
	public void create(SccMotivoRejeicao entity) throws DAOException;
	
    public void delete(SccMotivoRejeicao entity) throws DAOException;
    
    public SccMotivoRejeicao getMotivoRejeicaoByPk(String cdMotivoRejeicao) throws DAOException;
    
    public List<SccAliquotaImposto> pesquisaAliquotas(String noImposto,String inTipoServico) throws DAOException;
    
    public void update(SccAliquotaImposto entity) throws DAOException;
	
	public void create(SccAliquotaImposto entity) throws DAOException;
	
    public void delete(SccAliquotaImposto entity) throws DAOException;
    
    public List<SccCtlParalizacaoPlat> pesquisaParalizacoes(SccCtlParalizacaoPlat filtro,Date dataInicial,Date dataFinal) throws DAOException;
    
    public void update(SccCtlParalizacaoPlat entity) throws DAOException;
	
   	public void create(SccCtlParalizacaoPlat entity) throws DAOException;
   	
    public void delete(SccCtlParalizacaoPlat entity) throws DAOException;
    
    public void update(SccPreDominio entity) throws DAOException;
	
   	public void create(SccPreDominio entity) throws DAOException;
   	
    public void delete(SccPreDominio entity) throws DAOException;
    
    public List<SccPreDominio> getAllPreDominio() throws DAOException;
    
    public List<SccPreDominio> getTipos() throws DAOException;
    
    public List<SccContaContabil> getAllContaContabil() throws DAOException;
    
    public void update(SccContaContabil entity) throws DAOException;
	
   	public void create(SccContaContabil entity) throws DAOException;
   	
    public void delete(SccContaContabil entity) throws DAOException;
    
    public List<SccCentro> getAllCentro() throws DAOException;
    
    public void update(SccCentro entity) throws DAOException;
	
   	public void create(SccCentro entity) throws DAOException;
   	
    public void delete(SccCentro entity) throws DAOException;
    
    public List<SccPenalidadePorRejeicao> pesquisarPenalidadePorRejeicao(String cdEOTLd, String cdMotivoRejeicao) throws DAOException;
	
	public void delete(SccPenalidadePorRejeicao entity) throws DAOException;
	
	public void update(SccPenalidadePorRejeicao entity) throws DAOException;
	
	public void create(SccPenalidadePorRejeicao entity) throws DAOException;

}
