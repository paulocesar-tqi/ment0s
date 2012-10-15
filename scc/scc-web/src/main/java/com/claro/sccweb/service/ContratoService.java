package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccConfigRepasseCobranca;
import com.claro.cobillingweb.persistence.entity.SccContratoAcordado;
import com.claro.cobillingweb.persistence.entity.SccContratoCobl;
import com.claro.cobillingweb.persistence.entity.SccPeriodProdContr;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratado;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPK;

/**
 * Serviço com métodos referentes a contratos , acordos e produtos de billing com operadoras externas.
 *
 */
public interface ContratoService {

	public List<SccProdutoCobilling> pesquisaProdutosOperadoraLD(String cdEOT) throws DAOException,ServiceException;
	public List<SccProdutoCobilling> pesquisaProdutosOperadoraLDTodas() throws DAOException,ServiceException;
	public List<SccPeriodicidadeRepasse> pesquisaPeriodicidadeRepasse(String cdEOT,Long cdProduto) throws DAOException,ServiceException;
	public List<SccContratoAcordado> pesquisaContratosPorProduto(String cdEOTClaro, String cdEOTLD, Long cdProduto, Date dtInicio,Date dtFinal,boolean holding) throws DAOException,ServiceException;
	public SccProdutoCobilling pesquisaProdutoByPk(Long cdProdutoCobilling) throws DAOException;
	public List<SccContratoCobl> pesquisaPorNome(String dsOperadora) throws DAOException;
	
	public void create(SccContratoCobl entity) throws DAOException;
	public void update(SccContratoCobl entity) throws DAOException;
	public void delete(SccContratoCobl entity) throws DAOException;
	public SccContratoCobl getByPk(Long pk) throws DAOException;
	public List<SccContratoCobl> getAll() throws DAOException;
	public List<SccContratoAcordado> pesquisaAcordosContrato(Long cdContratoCobilling) throws DAOException;
	
	public void create(SccContratoAcordado entity) throws DAOException;
	public void update(SccContratoAcordado entity) throws DAOException;
	public void atualizarAcordosContrato(SccContratoAcordado entity) throws DAOException;
	public void delete(SccContratoAcordado entity) throws DAOException;
	
	public List<SccProdutoContratado> pesquisaProdutosContratados(Long cdContratoCobilling) throws DAOException;
	
	public void create(SccProdutoContratado entity) throws DAOException;
	public void update(SccProdutoContratado entity) throws DAOException;
	public void delete(SccProdutoContratado entity) throws DAOException;
	
	public List<SccProdutoCobilling> listaTodosProdutos() throws DAOException;
	
	public List<SccConfigRepasseCobranca> carregaConfiguracaoRepasse(SccProdutoContratadoPK pk) throws DAOException;
	
	
	public void create(SccConfigRepasseCobranca entity) throws DAOException;
	public void update(SccConfigRepasseCobranca entity) throws DAOException;
	public void delete(SccConfigRepasseCobranca entity) throws DAOException;
	
	public SccProdutoContratado getProdutoContratatoByPk(SccProdutoContratadoPK pk) throws DAOException;
	
	public List<SccPeriodProdContr> carregaPeriodicidadesContrato(SccProdutoContratadoPK pk) throws DAOException;
	
	public void create(SccPeriodProdContr entity) throws DAOException;
	public void update(SccPeriodProdContr entity) throws DAOException;
	public void delete(SccPeriodProdContr entity) throws DAOException;
	
}
