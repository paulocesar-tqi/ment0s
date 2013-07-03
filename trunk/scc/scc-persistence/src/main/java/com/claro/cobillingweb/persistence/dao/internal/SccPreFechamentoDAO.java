package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccPreFechamento;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.cobillingweb.persistence.view.RelApuracaoFechamentoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelSinteticoFechamentoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelSinteticoServicoPrePagoView;

public interface SccPreFechamentoDAO extends BasicDAO<SccPreFechamento> {

	
	/**
	 * Pesquisa repasses de pré-pago de acordo com o status.
	 * @param cdEOTLD EOT da Operadora LD (externa).
	 * @param cdProdutoCobilling PK do produto de cobilling pré-pago segundo {@link SccProdutoPrepago}.
	 * @param statusRepasse Status do repasse 'C' = Confirmado , 'N' = Cancelado , BasicDAO.GET_ALL_STRING = Todos.
	 * @param dtInicial Data inicial do período de pesquisa.
	 * @param dtFinal Data final do período de pesquisa.
	 * @return Retorna repasses do pré-pago encontrados.
	 * @throws DAOException
	 */
	public List<SccPreFechamento> pesquisaRepassesPreOperadora(String cdEOTLD,String cdEOTClaro,String cdProdutoCobilling, String statusRepasse, Date dtInicial,Date dtFinal,boolean holding)
			throws DAOException;
	
	public List<SccPreFechamento> carregaDemonstrativoOperadoras(String cdEOTLD,String cdEOTClaro,String cdProdutoCobilling, String statusRepasse, Date dtInicial,Date dtFinal,boolean holding)
			throws DAOException;

	/**
	 * Pesquisa repasses de pré-pago de uma holding calculando somatório dos campos (GROUP BY).
	 * @param cdEOTLD
	 * @param cdEOTClaro
	 * @param cdProdutoCobilling
	 * @param statusRepasse
	 * @param dtInicial
	 * @param dtFinal
	 * @return
	 * @throws DAOException
	 */
	public List<SccPreFechamento> pesquisaRepassesPreHolding(String cdEOTLD,String cdEOTClaro,String cdProdutoCobilling,  Date dtInicial,Date dtFinal)
			throws DAOException;
	
	/**
	 * Gera relatório sintético de repasse pré-pago.
	 * @param cdEOTLD EOT da operadora externa (LD)
	 * @param cdEOTClaro EOT da operadora Claro.	  
	 * @param cdStatusFechamento Status do fechamento ('C' = Confirmado , 'N' = Cancelado , Vazio = Nulo).
	 * @param dataInicial Data inicial do período
	 * @param dataFinal Data final do período.
	 * @return
	 * @throws DAOException
	 */
	public List<RelSinteticoFechamentoPrePagoView> geraRelatorioSintetico(String cdProduto,String cdEOTLD,String cdEOTClaro,String cdStatusFechamento,Date dataInicial,Date dataFinal) throws DAOException;
	
	/**
	 * Gera segunda parte do relatório sintético de repasse pré-pago com dados da utilização da plataforma.
	 * @param cdEOTLD EOT da operadora externa (LD)
	 * @param cdEOTClaro EOT da operadora Claro.	  
	 * @param cdStatusFechamento Status do fechamento ('C' = Confirmado , 'N' = Cancelado , Vazio = Nulo).
	 * @param dataInicial Data inicial do período
	 * @param dataFinal Data final do período.
	 * @return
	 * @throws DAOException
	 */
	public List<RelSinteticoServicoPrePagoView> geraRelatorioSinteticoServico(String cdProduto,String cdEOTLD,String cdEOTClaro,String cdStatusFechamento,Date dataInicial,Date dataFinal) throws DAOException;
	
	/**
	 * Gera o resumo de apuração do repasse de pré-pago.
	 * @param cdProduto
	 * @param cdEOTLD
	 * @param cdEOTClaro
	 * @param cdStatusFechamento
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws DAOException
	 */
	public List<RelApuracaoFechamentoPrePagoView> geraRelatorioApuracao(String cdProduto,String cdEOTLD,String cdEOTClaro,String cdStatusFechamento,Date dataInicial,Date dataFinal) throws DAOException;

	void updateEntity(SccPreFechamento entity) throws DAOException;
	
	
}
