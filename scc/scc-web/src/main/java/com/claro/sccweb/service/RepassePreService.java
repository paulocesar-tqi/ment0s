package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccParamProcessoPK;
import com.claro.cobillingweb.persistence.entity.SccPreFechamento;
import com.claro.cobillingweb.persistence.view.ConsolidadoProdutoPreView;
import com.claro.cobillingweb.persistence.view.RelApuracaoFechamentoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelSinteticoFechamentoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelSinteticoServicoPrePagoView;
import com.claro.sccweb.decorator.DemonstrativoRepassePreDecorator;
import com.claro.sccweb.decorator.DemonstrativoRepassePreItemDecorator;
import com.claro.sccweb.decorator.SccPreFechamentoAssinaturaDecorator;
import com.claro.sccweb.service.composite.RepassePrePagoComposite;
import com.claro.sccweb.service.composite.SolicitacaoRepassePreComposite;
import com.claro.sccweb.service.to.ConsultaRepassePreTO;
import com.claro.sccweb.service.to.DemonstrativoRepassePrePagoTO;
import com.claro.sccweb.service.to.SolicitacaoRepassePrePagoTO;

public interface RepassePreService {

	/**
	 * Código do processo de repasse do pré-pago. Valor de cdProcesso em {@link SccParamProcessoPK}.
	 */
	public static final String CD_PROCESSO_PREPAGO = "FECHAMENTO_PRE";
	
	/**
	 * Status de requisições de repasse pré-pago que ainda aguardam para ser processadas.
	 */
	public static final String VALOR_TO_LOAD_PRE = "TOLOAD";
	
	/**
	 * Status da requisição de repasse pré-pago que está sendo executadas. 
	 */
	public static final String VALOR_LOADING_PRE = "LOADING";
	
	/**
	 * Status da requisição de repasse pré-pago que já foi processada.
	 */
	public static final String VALOR_LOADED_PRE = "LOADED";
	
	/**
	 * Cria uma solicitação de relatório de repasse pré-pago
	 * @param to
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public int insereSolicitacaoRepassePre(SolicitacaoRepassePrePagoTO to) throws DAOException,ServiceException;
	
	/**
	 * Remove uma solicitação de relatório de repasse pré-pago que esteja agendada (a carregar).
	 * @param noRequisicao
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public void deleteSolicitacaoRepassePre(String noRequisicao) throws DAOException,ServiceException;
	
	/**
	 * Pesquisa solicitações de relatório de repasse pré-pago.
	 * @param status TO_LOAD , TO_LOAD ou LOADING.
	 * @param max Número máximo de registros que podem ser retornados. Esse parâmetro só é considerado quando o status não for TO_LOAD.
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public List<SolicitacaoRepassePreComposite> pesquisaSolicitacoesRepassePre(String status,int max) throws DAOException,ServiceException;
	
	/**
	 * Consulta repasses pré-pago.
	 * @param to Filtro de pesquisa.
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public List<RepassePrePagoComposite> consultaRepassesPrePago(ConsultaRepassePreTO to) throws DAOException,ServiceException;
	
	/**
	 * Gera demonstrativo(s) de repasse para pré-pago de acordo com o filtro informado.
	 * 
	 * @param to Filtro de pesquisa.
	 * @param full Indica que a operadora Claro informada é uma holding e o usuário deseja demonstrativos para 
	 * todas as operadoras dessa holding , além do somatório da Holding.
	 * @return 
	 * @throws DAOException
	 * @throws ServiceException
	 */
	@Deprecated
	public List<DemonstrativoRepassePreDecorator> carregaDemonstrativoOperadora(ConsultaRepassePreTO to,boolean full) throws DAOException,ServiceException;
	
	/**
	 * Carrega o demonstrativo de repasse pré-pago da holding (somando valores das operadoras).
	 * @param to
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public DemonstrativoRepassePreDecorator carregaDemonstrativoHolding(DemonstrativoRepassePrePagoTO to) throws DAOException,ServiceException;
	
	/**
	 * Carrega o demonstrativo de repasse pré-pago da operadora.
	 * @param to
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public DemonstrativoRepassePreDecorator carregaDemonstrativoOperadoras(DemonstrativoRepassePrePagoTO to) throws DAOException,ServiceException;
	
	/**
	 * Baseado em um demonstrativo de repasse , gera o demonstrativo de assinaturas.
	 * @param to
	 * @return
	 */
	public List<SccPreFechamentoAssinaturaDecorator> carregaAssinaturas(DemonstrativoRepassePrePagoTO to) throws DAOException,ServiceException;
	
	public List<SccPreFechamentoAssinaturaDecorator> carregaAssinaturasHolding(DemonstrativoRepassePrePagoTO to) throws DAOException,ServiceException;
	
	public List<RelSinteticoFechamentoPrePagoView> geraRelatorioSintetico(String cdProduto,String cdEOTLD, String cdEOTClaro, String cdStatusFechamento,Date dataInicial, Date dataFinal) throws DAOException;
	
	public List<RelSinteticoServicoPrePagoView> geraRelatorioSinteticoService(String cdProduto,String cdEOTLD, String cdEOTClaro, String cdStatusFechamento,Date dataInicial, Date dataFinal) throws DAOException; 
	
	public List<RelApuracaoFechamentoPrePagoView> geraRelatorioApuracao(String cdProduto, String cdEOTLD, String cdEOTClaro,String cdStatusFechamento, Date dataInicial, Date dataFinal) throws DAOException;
	
	public List<DemonstrativoRepassePreItemDecorator> geraLinhasDemonstrativo(SccPreFechamento preFechamento) throws ServiceException;
	
	public List<ConsolidadoProdutoPreView> gerarRelatorioConsolidadoProdutoPre(String cdEOTLD, String cdEOTClaro, String cdProduto, Date dataInicial, Date dataFinal) throws DAOException;
	
}
