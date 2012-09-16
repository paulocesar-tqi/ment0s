package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccConfigRepasseCobrancaDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccContratoAcordadoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccContratoCoblDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccPeriodProdContrDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccPeriodicidadeRepasseDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoCobillingDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoContratadoDAO;
import com.claro.cobillingweb.persistence.entity.SccConfigRepasseCobranca;
import com.claro.cobillingweb.persistence.entity.SccContratoAcordado;
import com.claro.cobillingweb.persistence.entity.SccContratoCobl;
import com.claro.cobillingweb.persistence.entity.SccPeriodProdContr;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratado;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPK;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.ContratoService;
import com.claro.sccweb.service.ServiceException;

public class ContratoServiceImpl extends AbstractService implements ContratoService {

	private SccProdutoCobillingDAO 			produtoCobillingDAO;
	private SccPeriodicidadeRepasseDAO 		periodicidadeRepasseDAO;
	private SccContratoAcordadoDAO 			contratoAcordadoDAO;
	private SccContratoCoblDAO 				contratoCoblDAO;
	private SccProdutoContratadoDAO 		produtoContratadoDAO;
	private SccConfigRepasseCobrancaDAO 	configRepasseCobrancaDAO;
	private SccPeriodProdContrDAO 			periodProdContrDAO;
	
	public List<SccProdutoCobilling> pesquisaProdutosOperadoraLD(String cdEOT) throws DAOException,ServiceException {
		return getProdutoCobillingDAO().pesquisaProdutosOperadoraLD(cdEOT);
	}
	
	public SccProdutoCobillingDAO getProdutoCobillingDAO() {
		return produtoCobillingDAO;
	}
	
	public void setProdutoCobillingDAO(SccProdutoCobillingDAO produtoCobillingDAO) {
		this.produtoCobillingDAO = produtoCobillingDAO;
	}
	
	public SccContratoAcordadoDAO getContratoAcordadoDAO() {
		return contratoAcordadoDAO;
	}
	
	public void setContratoAcordadoDAO(SccContratoAcordadoDAO contratoAcordadoDAO) {
		this.contratoAcordadoDAO = contratoAcordadoDAO;
	}
	
	public SccPeriodicidadeRepasseDAO getPeriodicidadeRepasseDAO() {
		return periodicidadeRepasseDAO;
	}
	
	public void setPeriodicidadeRepasseDAO(SccPeriodicidadeRepasseDAO periodicidadeRepasseDAO) {
		this.periodicidadeRepasseDAO = periodicidadeRepasseDAO;
	}
	
	public List<SccPeriodicidadeRepasse> pesquisaPeriodicidadeRepasse(String cdEOT, Long cdProduto) throws DAOException,ServiceException {
		return getPeriodicidadeRepasseDAO().pesquisaPeriodicidadeRepasse(cdEOT, cdProduto);		
	}
	
	public List<SccContratoAcordado> pesquisaContratosPorProduto(String cdEOTClaro, String cdEOTLD, Long cdProduto, Date dtInicio,Date dtFinal, boolean holding) throws DAOException,ServiceException {
		return getContratoAcordadoDAO().pesquisaContratosPorProduto(cdEOTClaro, cdEOTLD, cdProduto, dtInicio, dtFinal, holding);
	}
	
	public SccProdutoCobilling pesquisaProdutoByPk(Long cdProdutoCobilling) throws DAOException {
		return getProdutoCobillingDAO().getByPk(cdProdutoCobilling, SccProdutoCobilling.class);
	}
	
	public SccContratoCoblDAO getContratoCoblDAO() {
		return contratoCoblDAO;
	}
	
	public void setContratoCoblDAO(SccContratoCoblDAO contratoCoblDAO) {
		this.contratoCoblDAO = contratoCoblDAO;
	}
	
	public List<SccContratoCobl> pesquisaPorNome(String dsOperadora) throws DAOException {		
		return getContratoCoblDAO().pesquisaPorNome(dsOperadora);
	}
	
	@Transactional
	public void create(SccContratoCobl entity) throws DAOException {
		getContratoCoblDAO().create(entity);
	}
	
	@Transactional
	public void update(SccContratoCobl entity) throws DAOException {
		getContratoCoblDAO().update(entity);
	}
	
	@Transactional
	public void delete(SccContratoCobl entity) throws DAOException {
		getContratoCoblDAO().delete(entity);
	}
	
	public SccContratoCobl getByPk(Long pk) throws DAOException {
		return getContratoCoblDAO().getByPk(pk, SccContratoCobl.class);
	}
	
	public List<SccContratoCobl> getAll() throws DAOException {
		return getContratoCoblDAO().getAll();
	}
	
	public List<SccContratoAcordado> pesquisaAcordosContrato(Long cdContratoCobilling) throws DAOException {		
		return getContratoAcordadoDAO().pesquisaAcordosContrato(cdContratoCobilling);
	}
	
	@Transactional
	public void create(SccContratoAcordado entity) throws DAOException {
		getContratoAcordadoDAO().create(entity);
	}
	
	@Transactional
	public void update(SccContratoAcordado entity) throws DAOException {
		getContratoAcordadoDAO().update(entity);
	}
	
	@Transactional
	public void atualizarAcordosContrato(SccContratoAcordado entity) throws DAOException {
		getContratoAcordadoDAO().atualizarAcordosContrato(entity);
	}
	
	@Transactional
	public void delete(SccContratoAcordado entity) throws DAOException {
		getContratoAcordadoDAO().delete(entity);
	}
	
	public List<SccProdutoContratado> pesquisaProdutosContratados(Long cdContratoCobilling) throws DAOException {
		return getContratoCoblDAO().pesquisaProdutosContratados(cdContratoCobilling);
	}
	
	@Transactional
	public void create(SccProdutoContratado entity) throws DAOException {
		getProdutoContratadoDAO().create(entity);
	}
	
	@Transactional
	public void update(SccProdutoContratado entity) throws DAOException {
		getProdutoContratadoDAO().update(entity);
	}
	
	@Transactional
	public void delete(SccProdutoContratado entity) throws DAOException {
		getProdutoContratadoDAO().delete(entity);
	}
	
	public SccProdutoContratadoDAO getProdutoContratadoDAO() {
		return produtoContratadoDAO;
	}
	
	public void setProdutoContratadoDAO(SccProdutoContratadoDAO produtoContratadoDAO) {
		this.produtoContratadoDAO = produtoContratadoDAO;
	}
	
	public List<SccProdutoCobilling> listaTodosProdutos() throws DAOException {
		return getProdutoCobillingDAO().getAll();
	}
	
	public SccConfigRepasseCobrancaDAO getConfigRepasseCobrancaDAO() {
		return configRepasseCobrancaDAO;
	}
	
	public void setConfigRepasseCobrancaDAO(SccConfigRepasseCobrancaDAO configRepasseCobrancaDAO) {
		this.configRepasseCobrancaDAO = configRepasseCobrancaDAO;
	}
	
	public List<SccConfigRepasseCobranca> carregaConfiguracaoRepasse(SccProdutoContratadoPK pk) throws DAOException {
		return getConfigRepasseCobrancaDAO().carregaConfiguracaoRepasse(pk);		
	}
	
	@Transactional
	public void create(SccConfigRepasseCobranca entity) throws DAOException {		
		getConfigRepasseCobrancaDAO().create(entity);
	}
	
	@Transactional
	public void update(SccConfigRepasseCobranca entity) throws DAOException {
		getConfigRepasseCobrancaDAO().update(entity);
	}
	
	@Transactional
	public void delete(SccConfigRepasseCobranca entity) throws DAOException {
		getConfigRepasseCobrancaDAO().delete(entity);
	}
	
	public SccProdutoContratado getProdutoContratatoByPk(SccProdutoContratadoPK pk) throws DAOException {		
		return getProdutoContratadoDAO().getByPk(pk, SccProdutoContratado.class);
	}
	
	public List<SccPeriodProdContr> carregaPeriodicidadesContrato(SccProdutoContratadoPK pk) throws DAOException {
		return getPeriodProdContrDAO().carregaPeriodicidadesContrato(pk);
	}
	
	public SccPeriodProdContrDAO getPeriodProdContrDAO() {
		return periodProdContrDAO;
	}
	
	public void setPeriodProdContrDAO(SccPeriodProdContrDAO periodProdContrDAO) {
		this.periodProdContrDAO = periodProdContrDAO;
	}
	
	@Transactional
	public void create(SccPeriodProdContr entity) throws DAOException {
		getPeriodProdContrDAO().create(entity);
	}
	
	@Transactional
	public void update(SccPeriodProdContr entity) throws DAOException {
		getPeriodProdContrDAO().update(entity);
	}
	
	@Transactional
	public void delete(SccPeriodProdContr entity) throws DAOException {
		getPeriodProdContrDAO().delete(entity);
	}
	
}
