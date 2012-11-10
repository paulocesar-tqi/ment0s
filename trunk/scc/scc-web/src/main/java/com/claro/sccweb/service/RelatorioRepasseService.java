package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccItemRepasse;
import com.claro.cobillingweb.persistence.entity.SccRelatorioJurosMulta;
import com.claro.cobillingweb.persistence.entity.SccRepasse;
import com.claro.cobillingweb.persistence.view.PenalidadeRejeicaoView;
import com.claro.cobillingweb.persistence.view.RelPrestacaoServicoView;
import com.claro.cobillingweb.persistence.view.SccRetornoRepasseView;
import com.claro.sccweb.decorator.DemonstrativoRepassePosDecorator;
import com.claro.sccweb.decorator.RepasseServicoAdicionalDecorator;
import com.claro.sccweb.service.composite.RepassePosPagoComposite;
import com.claro.sccweb.service.composite.SolicitacaoRepassePosComposite;
import com.claro.sccweb.service.to.ConsultaRepassePosTO;
import com.claro.sccweb.service.to.SolicitacaoRepassePosPagoTO;

/**
 * Interface para métodos de pesquisa e gestão de relatórios de repasse pós e pré-pago.
 *
 */
public interface RelatorioRepasseService {
	
	public static final String CD_PROCESSO_REPASSE = "REPASSE";
	
	/**
	 * Pesquisa os últimos relatórios de repasse pós pago processados.
	 * @param max Quantidade máxima de registros processados.
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public List<SolicitacaoRepassePosComposite> pesquisaRepassesProcessados(String idProcesso,int max) throws DAOException,ServiceException;
	
	/**
	 * Pesquisa os últimos relatórios de repasse pós pago sendo processados.
	 * @param max Quantidade máxima de registros processados.
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public List<SolicitacaoRepassePosComposite> pesquisaRepassesProcessando(String idProcesso,int max) throws DAOException,ServiceException;
	
	/**
	 * Pesquisa os últimos relatórios de repasse pós pago agendados (a carregar).
	 * @param max Quantidade máxima de registros processados.
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public List<SolicitacaoRepassePosComposite> pesquisaRepassesAgendados(String idProcesso) throws DAOException,ServiceException;
	
	/**
	 * Cria uma solicitação de relatório de repasse.
	 * @param to
	 * @throws DAOException
	 * @throws ServiceException
	 * @return Retorna a quantidade de solicitações que foram criadas na base de dados. Se uma solicitação que já está criada for enviada , ela não 
	 * será criada na base de dados.
	 */
	public int insereSolicitacaoRepassePos(SolicitacaoRepassePosPagoTO to) throws DAOException,ServiceException;
	
	
	
	/**
	 * Remove uma solicitação de relatório de repasse pós-pago que esteja agendada (a carregar).
	 * @param noRequisicao
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public void deleteSolicitacaoRepasse(String noRequisicao) throws DAOException,ServiceException;
	
	
	
	/**
	 * Carrega o demonstrativo de repasse de uma operadora/holding Claro de acordo com o filtro informado.
	 * Apesar de não ser típico um serviço gerar decorator , nesse caso a justificativa é que a formatação e descrição
	 * dos itens de repasse seguem regras de negócio. O decorator não atua somente como formatador.
	 * @param cdEOTClaro EOT da Operadora Claro
	 * @param cdEOTLD EOT da Operadora LD
	 * @param cdProdutoCobillin PK do produto de cobilling.
	 * @param dtInicial Data Inicial do período de pesquisa.
	 * @param dtFinal Data final do período de pesquisa.
	 * @param holding TRUE caso a operadora Claro seja uma holding.
	 * @return Map com item de repasse totalizados (somatório) agupados por Descrição.
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public List<DemonstrativoRepassePosDecorator> carregaDemonstrativoRepasse(String cdEOTClaro,String cdEOTLD,Long cdProdutoCobilling,Date dtInicial,Date dtFinal,boolean holding) throws DAOException,ServiceException;
	
	
	
	/**
	 * Pesquisa o detalhamento de assinaturas do repasse.
	 * @param cdEOTLD EOT da operadora LD.
	 * @param cdProdutoCobilling PK do produto de cobilling.
	 * @param cdPeriodicidade PK da periodicidade.
	 * @param dtInicioRepasse Data início do repasse.
	 * @return
	 * @throws DAOException
	 */
	public List<RepasseServicoAdicionalDecorator> carregaAssinaturasRepasse(String cdEOTClaro,String cdEOTLD,Long cdProdutoCobilling, Long cdPeriodicidade, Date dtInicioRepasse,boolean holding)
	throws DAOException,ServiceException;
	
	/**
	 * Carrega os repasses de juros e multas para um dado produto ,par de operadora e data de repasse.
	 * @param cdEOTClaro
	 * @param cdEOT
	 * @param cdProdutoCobilling
	 * @param dtInicialRepasse
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public List<SccRelatorioJurosMulta> carregaJurosMultasRepasse(String cdEOTClaro, String cdEOT, Long cdProdutoCobilling,Date dtInicialRepasse)
	throws DAOException,ServiceException;
	
	/**
	 * Pesquisa repasses 
	 * @param cdEOTClaro
	 * @param cdEOT
	 * @param cdProdutoCobilling
	 * @param dtInicialRepasse
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public RepassePosPagoComposite carregaDadosRepasse(ConsultaRepassePosTO to)
	throws DAOException,ServiceException;

	/**
	 * Calcula o valor bruto do repasse.
	 * @param map Items de repasse {@link SccRepasse} indexados pelo tipo de item {@link SccItemRepasse}
	 * @return Valor bruto do repasse.
	 * @throws ServiceException
	 */
	public Double calculaValorBrutoRepasse(Map<Long, SccRepasse> map) throws ServiceException;
	
	/**
	 * Carrega dos itens de um repasse.
	 * @param nuRepasse
	 * @return
	 * @throws DAOException
	 */
	public List<SccRepasse> carregaItensRepasse(Long nuRepasse) throws DAOException;
	
	/**
	 * Pesquisa detalhe de um item de repasse.
	 * @param cdItemRepasse
	 * @return
	 * @throws DAOException
	 */
	public SccItemRepasse getItemRepasse(Long cdItemRepasse) throws DAOException;
	
	/**
	 * Remove um item do repasse.
	 * @param repasse
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public void removeItemRepasse(Long cdItemRepasse,Long nuRepasse) throws DAOException,ServiceException;
	
	/**
	 * Insere/Atualiza um item de repasse.
	 * @param novoValor
	 * @param repasse
	 * @throws DAOException
	 */
	public void ajustaValorBrutoItem(Double novoValor,SccRepasse repasse) throws DAOException,ServiceException;
	
	List<RelPrestacaoServicoView> gerarRelatorioPrestacaoServicoPos(ConsultaRepassePosTO consultaRepassePosTO)throws DAOException;
	
	List<RelPrestacaoServicoView> gerarRelatorioPrestacaoServicoPre(ConsultaRepassePosTO consultaRepassePosTO)
			throws DAOException;

	/**
	 * Pesquisa os retornos de repasse.
	 * @param TO com os parametros.
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public List<SccRetornoRepasseView> pesquisaRetornoRepasse(ConsultaRepassePosTO to) throws DAOException,ServiceException;


	/**
	 * Pesquisa as penalidades por Rejeição.
	 * @param TO com os parametros.
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public List<PenalidadeRejeicaoView> pesquisaPenalidadeRejeicao(ConsultaRepassePosTO to) throws DAOException,ServiceException;


}
