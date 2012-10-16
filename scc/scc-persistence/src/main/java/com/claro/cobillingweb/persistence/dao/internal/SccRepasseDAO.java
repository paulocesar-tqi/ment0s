package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccRepasse;
import com.claro.cobillingweb.persistence.view.RelContabilView;
import com.claro.cobillingweb.persistence.view.RelPrestacaoServicoView;
import com.claro.cobillingweb.persistence.view.mapper.LongEntity;

public interface SccRepasseDAO extends BasicDAO<SccRepasse> {

	/**
	 * Carrega repasse(s) pós-pago de acordo com o filtro informado.
	 * @param cdEOT EOT CLARO (holding ou operadora)
	 * @param cdEOTLD CD EOT da operadora LD
	 * @param cdProduto Produto de Cobilling
	 * @param dtInicial Data inicial do repasse
	 * @param dtFinal Data Final do repasse
	 * @param cdStatusRepasse Status do Repasse
	 * @param holding Indica se a pesquisa deve ser feita considerando todas as operadoras da holding , ou uma operadora individual.	 
	 * @return
	 * @throws DAOException
	 */
	public List<SccRepasse> carregaRepassePosPago(String cdEOT,String cdEOTLD,Long cdProduto,Date dtInicial,Date dtFinal,String cdStatusRepasse,boolean holding) throws DAOException;
	
	/**
	 * Lista os items de repasse , agrupando (somando) por tipo (cd_item).
	 * @param nuRepasse
	 * @return
	 * @throws DAOException
	 */
	public List<SccRepasse> carregaItensRepasse(Long nuRepasse) throws DAOException;
	
	/**
	 * Remove os itens de um dado tipo no repasse.
	 * @param cdItemRepasse
	 * @param nuRepasse
	 * @throws DAOException
	 */
	public void removeItemRepasse(Long cdItemRepasse,Long nuRepasse) throws DAOException;
	
	/**
	 * Atualiza todos os ítens do repasse.
	 * @param sqRepasse
	 * @throws DAOException
	 */
	public void atualizaStatusRepasse(Long nuRepasse,String novoStatus) throws DAOException;
	
	/**
	 * Pesquisa lista de nu_repasse de acordo com o fitlro informado.
	 * @param cdEOTLD
	 * @param cdEOTClaro
	 * @param cdProdutoCobilling
	 * @param cdStatus
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws DAOException
	 */
	public List<LongEntity> pesquisaRepassesPosPago(String cdEOTLD,String cdEOTClaro,Long cdProdutoCobilling,String cdStatus,Date dataInicial,Date dataFinal)
	throws DAOException;
	
	public List<RelContabilView> geraRelatorioContabil(String cdEOTLD,String cdEOTClaro,String cdMotivoRejeicao,Date dataInicial,Date dataFinal,boolean holding) throws DAOException;
	
	List<RelPrestacaoServicoView> gerarRelatorioPrestacaoServicoPos(String cdEOTClaro, String cdEOTLd, Long cdProdutoCobilling, Date dataInicial, Date dataFinal)throws DAOException;
	
	List<RelPrestacaoServicoView> gerarRelatorioPrestacaoServicoPre(String cdEOTClaro, String cdEOTLd, Long cdProdutoPrepago, String dataFechamento) throws DAOException;

		
}
