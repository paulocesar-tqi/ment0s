package com.claro.sccweb.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccContratoAcordadoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccItemRepasseDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccParamProcessoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccPeriodicidadeRepasseDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoCobillingDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoPrepagoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioJurosMultaDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccRepasseDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccRepasseServicoAdicionalDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccTipoContratoDAO;
import com.claro.cobillingweb.persistence.entity.SccContratoAcordado;
import com.claro.cobillingweb.persistence.entity.SccItemRepasse;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccParamProcesso;
import com.claro.cobillingweb.persistence.entity.SccParamProcessoPK;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccRelatorioJurosMulta;
import com.claro.cobillingweb.persistence.entity.SccRepasse;
import com.claro.cobillingweb.persistence.entity.SccRepasseServicoAdicional;
import com.claro.cobillingweb.persistence.view.RelPrestacaoServicoView;
import com.claro.sccweb.decorator.DemonstrativoRepassePosDecorator;
import com.claro.sccweb.decorator.RepasseServicoAdicionalDecorator;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.RelatorioRepasseService;
import com.claro.sccweb.service.ServiceException;
import com.claro.sccweb.service.composite.RepassePosPagoComposite;
import com.claro.sccweb.service.composite.SolicitacaoRepassePosComposite;
import com.claro.sccweb.service.helper.DemonstrativoPosPagoConstantes;
import com.claro.sccweb.service.to.ConsultaRepassePosTO;
import com.claro.sccweb.service.to.SolicitacaoRepassePosPagoTO;

public class RelatorioRepasseServiceImpl extends AbstractService implements RelatorioRepasseService
{

	private SccParamProcessoDAO paramProcessoDAO;
	private SccContratoAcordadoDAO contratoAcordadoDAO;
	private SccOperadoraDAO operadoraDAO;
	private SccPeriodicidadeRepasseDAO periodicidadeRepasseDAO;
	private SccProdutoCobillingDAO produtoCobillingDAO;
	private SccRepasseDAO repasseDAO;
	private SccRepasseServicoAdicionalDAO repasseServicoAdicionalDAO;
	private SccRelatorioJurosMultaDAO relatorioJurosMultaDAO;
	private SccItemRepasseDAO itemRepasseDAO;
	private SccTipoContratoDAO tipoContratoDAO;
	private SccProdutoPrepagoDAO produtoPrepagoDAO;
	
	
	
	
	 
	public List<SolicitacaoRepassePosComposite> pesquisaRepassesProcessados(final String idProcesso,final int max) throws DAOException, ServiceException {		
		List<SolicitacaoRepassePosComposite> resultado=null;
		if (idProcesso.equals(RelatorioRepasseService.CD_PROCESSO_REPASSE))
			{
			resultado =  pesquisaRepassesProcessadosPos(max);
			}			
		return resultado;
		}
	
	private List<SolicitacaoRepassePosComposite> pesquisaRepassesProcessadosPos(final int max) throws DAOException, ServiceException
	{
		final List<SolicitacaoRepassePosComposite> resultado = new ArrayList<SolicitacaoRepassePosComposite>();
		try {
			final List<SccParamProcesso> paramProcessoList = getParamProcessoDAO().pesquisaRepassesProcessados(RelatorioRepasseService.CD_PROCESSO_REPASSE,max);
			if (paramProcessoList != null)
				{
				SolicitacaoRepassePosComposite item = null;
				for (int i=0;i<paramProcessoList.size();i++)
					{					
					SccParamProcesso row = paramProcessoList.get(i);
					String valorTx = row.getTxValorParametro();  
					 item = new SolicitacaoRepassePosComposite();
					item.setPeriodoInicio(dateFormat.parse(valorTx.substring(0, 8)));
					item.setPeriodoFinal(dateFormat.parse(valorTx.substring(8, 16)));
					item.setNoRequisicao(row.getId().getNmParametro());
					SccOperadora operadoraLD = getOperadoraDAO().getByPk(valorTx.substring(16, 19),SccOperadora.class);
					SccOperadora operadoraClaro = getOperadoraDAO().getByPk(valorTx.substring(19, 22),SccOperadora.class);
					item.setOperadoraClaro(operadoraClaro);
					item.setOperadoraExterna(operadoraLD);
					SccProdutoCobilling produtoCobilling = getProdutoCobillingDAO().getByPk(Long.parseLong(valorTx.substring(22, 31)),SccProdutoCobilling.class);
					item.setProdutoCobilling(produtoCobilling);
					item.setDataCriacao(row.getDtCriacao());
					item.setDataEvento(row.getDtAtualizacao());
					item.setUsuario(row.getCdUsuarioManut());
					resultado.add(item);
					}
				}				
			return resultado;
		} catch (DAOException daoException)
			{
			error("DAO Exception :", daoException);
			throw daoException;
			}
		catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
	}

	 
	public List<SolicitacaoRepassePosComposite> pesquisaRepassesProcessando(String idProcesso,int max)	throws DAOException, ServiceException {
		List<SolicitacaoRepassePosComposite> resultado = null;
		if (idProcesso.equals(RelatorioRepasseService.CD_PROCESSO_REPASSE))
			{
			resultado =  pesquisaRepassesProcessandoPos(max);
			}
		return resultado;
	}
	
	
	
	private List<SolicitacaoRepassePosComposite> pesquisaRepassesProcessandoPos(int max) throws DAOException, ServiceException
	{
		List<SolicitacaoRepassePosComposite> resultado = new ArrayList<SolicitacaoRepassePosComposite>();
		try {
			SolicitacaoRepassePosComposite item = null;
			List<SccParamProcesso> paramProcessoList = getParamProcessoDAO().pesquisaRepassesProcessando(RelatorioRepasseService.CD_PROCESSO_REPASSE,max);
			if (paramProcessoList != null)
				{
				for (int i=0;i<paramProcessoList.size();i++)
					{					
					SccParamProcesso row = paramProcessoList.get(i);
					String valorTx = row.getTxValorParametro();
					item = new SolicitacaoRepassePosComposite();
					item.setPeriodoInicio(dateFormat.parse(valorTx.substring(0, 8)));
					item.setPeriodoFinal(dateFormat.parse(valorTx.substring(8, 16)));
					item.setNoRequisicao(row.getId().getNmParametro());
					SccOperadora operadoraLD = getOperadoraDAO().getByPk(valorTx.substring(16, 19),SccOperadora.class);
					SccOperadora operadoraClaro = getOperadoraDAO().getByPk(valorTx.substring(19, 22),SccOperadora.class);
					item.setOperadoraClaro(operadoraClaro);
					item.setOperadoraExterna(operadoraLD);
					SccProdutoCobilling produtoCobilling = getProdutoCobillingDAO().getByPk(Long.parseLong(valorTx.substring(22, 31)),SccProdutoCobilling.class);
					item.setProdutoCobilling(produtoCobilling);
					item.setDataCriacao(row.getDtCriacao());
					item.setDataEvento(row.getDtAtualizacao());
					item.setUsuario(row.getCdUsuarioManut());
					resultado.add(item);
					}
				}				
			return resultado;
		} catch (DAOException daoException)
			{
			error("DAO Exception : ", daoException);
			throw daoException;
			}
		catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
	}
	
	public List<SolicitacaoRepassePosComposite> pesquisaRepassesProcessandoPos(String idProcesso,int max)	throws DAOException, ServiceException {
		List<SolicitacaoRepassePosComposite> resultado = new ArrayList<SolicitacaoRepassePosComposite>();
		try {
			List<SccParamProcesso> paramProcessoList = getParamProcessoDAO().pesquisaRepassesProcessando(idProcesso,max);
			SolicitacaoRepassePosComposite item = null;
			if (paramProcessoList != null)
				{
				for (int i=0;i<paramProcessoList.size();i++)
					{					
					SccParamProcesso row = paramProcessoList.get(i);
					String valorTx = row.getTxValorParametro();
					item = new SolicitacaoRepassePosComposite();
					item.setPeriodoInicio(dateFormat.parse(valorTx.substring(0, 8)));
					item.setPeriodoFinal(dateFormat.parse(valorTx.substring(8, 16)));
					item.setNoRequisicao(row.getId().getNmParametro());
					SccOperadora operadoraLD = getOperadoraDAO().getByPk(valorTx.substring(16, 19),SccOperadora.class);
					SccOperadora operadoraClaro = getOperadoraDAO().getByPk(valorTx.substring(19, 22),SccOperadora.class);
					item.setOperadoraClaro(operadoraClaro);
					item.setOperadoraExterna(operadoraLD);
					SccProdutoCobilling produtoCobilling = getProdutoCobillingDAO().getByPk(Long.parseLong(valorTx.substring(22, 31)),SccProdutoCobilling.class);
					item.setProdutoCobilling(produtoCobilling);
					item.setDataCriacao(row.getDtCriacao());
					item.setDataEvento(row.getDtAtualizacao());
					item.setUsuario(row.getCdUsuarioManut());
					resultado.add(item);
					}
				}				
			return resultado;
		} catch (DAOException daoException)
			{
			throw daoException;
			}
		catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
	}

	 
	public List<SolicitacaoRepassePosComposite> pesquisaRepassesAgendados(String idProcesso) throws DAOException, ServiceException {
		 if (idProcesso.equals(RelatorioRepasseService.CD_PROCESSO_REPASSE))
			 return pesquisaRepassesAgendadosPosPago();
		 return null;
	}

	private List<SolicitacaoRepassePosComposite> pesquisaRepassesAgendadosPosPago() throws DAOException,ServiceException
	{
		List<SolicitacaoRepassePosComposite> resultado = new ArrayList<SolicitacaoRepassePosComposite>();
		try {
			List<SccParamProcesso> paramProcessoList = getParamProcessoDAO().pesquisaRepassesAgendados(RelatorioRepasseService.CD_PROCESSO_REPASSE);
			if (paramProcessoList != null)
				{
				for (int i=0;i<paramProcessoList.size();i++)
					{					
					SccParamProcesso row = paramProcessoList.get(i);
					String valorTx = row.getTxValorParametro();
					SolicitacaoRepassePosComposite item = new SolicitacaoRepassePosComposite();
					item.setPeriodoInicio(dateFormat.parse(valorTx.substring(0, 8)));
					item.setPeriodoFinal(dateFormat.parse(valorTx.substring(8, 16)));
					item.setNoRequisicao(row.getId().getNmParametro());
					SccOperadora operadoraLD = getOperadoraDAO().getByPk(valorTx.substring(16, 19),SccOperadora.class);
					SccOperadora operadoraClaro = getOperadoraDAO().getByPk(valorTx.substring(19, 22),SccOperadora.class);
					item.setOperadoraClaro(operadoraClaro);
					item.setOperadoraExterna(operadoraLD);
					SccProdutoCobilling produtoCobilling = getProdutoCobillingDAO().getByPk(Long.parseLong(valorTx.substring(22, 31)),SccProdutoCobilling.class);
					item.setProdutoCobilling(produtoCobilling);
					item.setDataCriacao(row.getDtCriacao());
					item.setDataEvento(row.getDtAtualizacao());
					item.setUsuario(row.getCdUsuarioManut());
					resultado.add(item);
					}
				}
		}
					catch (DAOException daoException)
				{
					throw daoException;
					}
				catch (Exception e)
					{
					throw new ServiceException(e.getMessage(), e);
					}			
			return resultado;
	}
	
	
	
	@Transactional	
	public int insereSolicitacaoRepassePos(SolicitacaoRepassePosPagoTO argument) throws DAOException, ServiceException {		
		int resultado = 0;
		Date now = new Date();
		try {
			SccPeriodicidadeRepasse periodicidadeRepasse = getPeriodicidadeRepasseDAO().getByPk(argument.getCdPeriodicidadeRepasse(), SccPeriodicidadeRepasse.class);
			
			Calendar calInicio = Calendar.getInstance();
			calInicio.set(Calendar.YEAR, argument.getAnoRelatorio().intValue());
			calInicio.set(Calendar.MONTH, argument.getMesRelatorio().intValue()-1);			
			calInicio.set(Calendar.DAY_OF_MONTH, periodicidadeRepasse.getDdInicioPeriodoRepasse().intValue());
			
			Calendar calFinal = Calendar.getInstance();
			calFinal.set(Calendar.YEAR, argument.getAnoRelatorio().intValue());
			calFinal.set(Calendar.MONTH, argument.getMesRelatorio().intValue()-1);			
			calFinal.set(Calendar.DAY_OF_MONTH, periodicidadeRepasse.getDdFimPeriodoRepasse().intValue());
			
			List<SccContratoAcordado> acordados = getContratoAcordadoDAO().pesquisaContratosPorProduto(argument.getCdEOT(), argument.getCdProdutoCobilling());			
			for (int i=0;i<acordados.size();i++) {				
				SccParamProcessoPK pk = new SccParamProcessoPK();
				StringBuffer nmProcesso = new StringBuffer();
				nmProcesso.append(leftZeroFill(argument.getCdEOT(), 6));
				nmProcesso.append(argument.getMesRelatorio().toString());
				nmProcesso.append(argument.getAnoRelatorio().toString());
				nmProcesso.append(leftZeroFill(argument.getCdPeriodicidadeRepasse().toString(), 2));
				nmProcesso.append(leftZeroFill(acordados.get(i).getOperadoraClaro().getCdEot(), 6));
				nmProcesso.append(leftZeroFill(argument.getCdProdutoCobilling().toString(), 6));				
				pk.setNmParametro(nmProcesso.toString());
				pk.setCdProcesso(RelatorioRepasseService.CD_PROCESSO_REPASSE);
				SccParamProcesso paramTest = getParamProcessoDAO().getByPk(pk, SccParamProcesso.class);
				if (paramTest == null) {
					SccParamProcesso sccParamProcesso = new SccParamProcesso();
					sccParamProcesso.setId(pk);
					sccParamProcesso.setCdTipoParametro("TOLOAD");
					StringBuffer txValue = new StringBuffer();
					txValue.append(dateFormat.format(calInicio.getTime()));
					txValue.append(dateFormat.format(calFinal.getTime()));
					txValue.append(argument.getCdEOT());
					txValue.append(acordados.get(i).getOperadoraClaro().getCdEot());
					txValue.append(leftZeroFill(argument.getCdProdutoCobilling().toString(), 9));
					txValue.append(" ");
					txValue.append(RelatorioRepasseService.CD_PROCESSO_REPASSE);
					sccParamProcesso.setTxValorParametro(txValue.toString());
					sccParamProcesso.setCdUsuarioManut(argument.getUsuarioManutencao());
					sccParamProcesso.setDtCriacao(now);
					getParamProcessoDAO().create(sccParamProcesso);	
					resultado++;
				}
			}
			return resultado;
		} catch (DAOException daoException){
			throw daoException;
		} catch (Exception e){
			throw new ServiceException("Erro em service RepasseService.insereSolicitacaoRepassePos "+e.getMessage(), e);
		}
	}

	public SccParamProcessoDAO getParamProcessoDAO() {
		return paramProcessoDAO;
	}

	public void setParamProcessoDAO(SccParamProcessoDAO paramProcessoDAO) {
		this.paramProcessoDAO = paramProcessoDAO;
	}

	public SccContratoAcordadoDAO getContratoAcordadoDAO() {
		return contratoAcordadoDAO;
	}

	public void setContratoAcordadoDAO(SccContratoAcordadoDAO contratoAcordadoDAO) {
		this.contratoAcordadoDAO = contratoAcordadoDAO;
	}

	public SccOperadoraDAO getOperadoraDAO() {
		return operadoraDAO;
	}

	public void setOperadoraDAO(SccOperadoraDAO operadoraDAO) {
		this.operadoraDAO = operadoraDAO;
	}

	public SccPeriodicidadeRepasseDAO getPeriodicidadeRepasseDAO() {
		return periodicidadeRepasseDAO;
	}

	public void setPeriodicidadeRepasseDAO(
			SccPeriodicidadeRepasseDAO periodicidadeRepasseDAO) {
		this.periodicidadeRepasseDAO = periodicidadeRepasseDAO;
	}

	public SccProdutoCobillingDAO getProdutoCobillingDAO() {
		return produtoCobillingDAO;
	}

	public void setProdutoCobillingDAO(SccProdutoCobillingDAO produtoCobillingDAO) {
		this.produtoCobillingDAO = produtoCobillingDAO;
	}
	
	

	public SccRepasseDAO getRepasseDAO() {
		return repasseDAO;
	}

	public void setRepasseDAO(SccRepasseDAO repasseDAO) {
		this.repasseDAO = repasseDAO;
	}

	@Transactional
	public void deleteSolicitacaoRepasse(String noRequisicao) throws DAOException, ServiceException {
		getParamProcessoDAO().deleteSolicitacaoRepasse(noRequisicao);
		
	}

	 
	public List<DemonstrativoRepassePosDecorator> carregaDemonstrativoRepasse(String cdEOTClaro, String cdEOTLD, Long cdProdutoCobilling,Date dtInicial, Date dtFinal, boolean holding) 
			throws DAOException,ServiceException {
		Map<Long, DemonstrativoRepassePosDecorator> tempMap = new HashMap<Long, DemonstrativoRepassePosDecorator>();		
		try {			
			List<SccRepasse> repasses = getRepasseDAO().carregaRepassePosPago(cdEOTClaro, cdEOTLD, cdProdutoCobilling, dtInicial, dtFinal,null,holding);
			if (repasses != null)
				{
				for (int i=0;i<repasses.size();i++)
					{
					tempMap.put(repasses.get(i).getCdItemRepasse(), new DemonstrativoRepassePosDecorator(repasses.get(i)));
					}
				}
			return converteMap2Demonstrativo(tempMap);	
		} catch (DAOException daoException)
			{
			throw daoException;
			}
		catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
	}
	
	/**
	 * O demonstrativo de repasse pós-pago deve ser exibido em uma ordem específica e com alguns somatórios.
	 * Esse formatação é feita por esse método auxiliar.
	 * @param map
	 * @return
	 */
	private List<DemonstrativoRepassePosDecorator> converteMap2Demonstrativo(Map<Long, DemonstrativoRepassePosDecorator> map)
	{
		List<DemonstrativoRepassePosDecorator> list = new ArrayList<DemonstrativoRepassePosDecorator>();
		DemonstrativoRepassePosDecorator decorator;		
		
		for (int i=0;i<DemonstrativoPosPagoConstantes.CONFIGURACAO_DEMONSTRATIVO_REPASSE_POS.length;i++)
			{
			if (DemonstrativoPosPagoConstantes.CONFIGURACAO_DEMONSTRATIVO_REPASSE_POS[i] == DemonstrativoPosPagoConstantes.TITULO)
				{
				decorator = new DemonstrativoRepassePosDecorator(DemonstrativoPosPagoConstantes.TITULOS_DEMONSTRATIVO_REPASSE_POS[i]);	
				}			
			else if (map.containsKey(DemonstrativoPosPagoConstantes.CONFIGURACAO_DEMONSTRATIVO_REPASSE_POS[i]))
				{
				decorator = map.get(DemonstrativoPosPagoConstantes.CONFIGURACAO_DEMONSTRATIVO_REPASSE_POS[i]);
				}				
			else
				{
				decorator = new DemonstrativoRepassePosDecorator();
				}				
			decorator.setDescricao(DemonstrativoPosPagoConstantes.TITULOS_DEMONSTRATIVO_REPASSE_POS[i]);		
			list.add(decorator);
			}		
		
		DemonstrativoRepassePosDecorator baseCalculo = geraBaseCalculoRepasse(map);
		if (baseCalculo != null)
			list.add(baseCalculo);
		
		list.add(geraCPMFDescontar(map));
		
		DemonstrativoRepassePosDecorator total = geraTotalValorRepassar(map);
		if (total != null)
			list.add(total);
	    return list;
	}


	/**
	 * Soma itens de repasse para calcular a base de cálculo do repasse.
	 * @param map Map com os itens de repasse indexados por código do item de repasse.
	 * @return
	 */
	private DemonstrativoRepassePosDecorator geraBaseCalculoRepasse(Map<Long, DemonstrativoRepassePosDecorator> map)
	{
		if (map == null)
			{
			return null;
			}
		DemonstrativoRepassePosDecorator decorator = new DemonstrativoRepassePosDecorator("5 - BASE DE CÁLCULO DO REPASSE (1 - 2 + 3 + 4 )");
		SccRepasse valor1,valor2;
		if (map.containsKey(DemonstrativoPosPagoConstantes.TOTAL_DO_VALOR_ARRECADADO))				
			{
			valor1 = map.get(DemonstrativoPosPagoConstantes.TOTAL_DO_VALOR_ARRECADADO).getRepasse();
			}			
		else
			{
			valor1 = new SccRepasse();
			}			
		
		if (map.containsKey(DemonstrativoPosPagoConstantes.TOTAL_DAS_DEDUCOES))
			{
			valor2	= map.get(DemonstrativoPosPagoConstantes.TOTAL_DAS_DEDUCOES).getRepasse();
			}					
		else
			{
			valor2 = new SccRepasse();
			}			

		decorator.setRepasse(new SccRepasse());
		
		if  (valor1.getVlBrutoItemRepasse() == null)
			{
			valor1.setVlBrutoItemRepasse(0.0);
			}			
		
		if  (valor2.getVlBrutoItemRepasse() == null)
			{
			valor2.setVlBrutoItemRepasse(0.0);
			}		
		
		Double valorFinal = valor1.getVlBrutoItemRepasse()-valor2.getVlBrutoItemRepasse()+calcularTotalAcertos(map)+calcularPenalidades(map);
		decorator.getRepasse().setVlBrutoRepasse(valorFinal);
		decorator.setBruto(valorFinal);		
		return decorator;
	}
	
	
	/**
	 * Calcula o valor bruto do repasse.
	 */
	public Double calculaValorBrutoRepasse(Map<Long, SccRepasse> map) throws ServiceException
	{	
		try {
		Double valor1,valor2;
		if (map.containsKey(DemonstrativoPosPagoConstantes.TOTAL_DO_VALOR_ARRECADADO))		
			{
			valor1 = map.get(DemonstrativoPosPagoConstantes.TOTAL_DO_VALOR_ARRECADADO).getVlBrutoItemRepasse();
			}			
		else
			{
			valor1 = 0.0;
			}			
		
		if (map.containsKey(DemonstrativoPosPagoConstantes.TOTAL_DAS_DEDUCOES))
			{
			valor2	= map.get(DemonstrativoPosPagoConstantes.TOTAL_DAS_DEDUCOES).getVlBrutoItemRepasse();
			}					
		else
			{
			valor2 = 0.0;
			}			
		
		return valor1-valor2+calcularTotalAcertosRepasse(map)+calcularPenalidadesRepasse(map);
		} catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
	}
	
	
	/**
	 * Soma os valores brutos dos itens de repasse relativos a acertos.
	 * @param map Map com os itens de repasse indexados por código do item de repasse.
	 * @return
	 */
	private Double calcularTotalAcertos(Map<Long, DemonstrativoRepassePosDecorator> map)
	{
		return somaValores(map, DemonstrativoPosPagoConstantes.ACERTO_CONCILIACAO_CONTRA_CLARO,DemonstrativoPosPagoConstantes.ACERTO_CONCILIACAO_CONTRA_OPERADORA_LD);		
	}
	
	/**
	 * Soma os valores brutos dos itens de repasse relativos a penalidades.
	 * @param map Map com os itens de repasse indexados por código do item de repasse.
	 * @return
	 */
	private Double calcularPenalidades(Map<Long, DemonstrativoRepassePosDecorator> map)
	{		
		return somaValores(map, DemonstrativoPosPagoConstantes.PENALIDADE_REJEICOES_INDEVIDAS_CONTRA_CLARO,DemonstrativoPosPagoConstantes.PENALIDADE_REJEICOES_DEVIDAS_CONTRA_LD,
				DemonstrativoPosPagoConstantes.PENALIDADE_CHAMADA_PERDIDA_CONTRA_CLARO,DemonstrativoPosPagoConstantes.PENALIDADE_SLA_CONTRA_CLARO,
				DemonstrativoPosPagoConstantes.MULTAS_JUROS_ATRASO_REPASSE_CONTRA_CLARO,DemonstrativoPosPagoConstantes.MULTAS_JUROS_ATRASO_PAGAMENTO_CONTRA_LD);		
	}
	
	
	private Double calcularPenalidadesRepasse(Map<Long, SccRepasse> map)
	{
		return somaValoresRepasse(map, DemonstrativoPosPagoConstantes.PENALIDADE_REJEICOES_INDEVIDAS_CONTRA_CLARO,DemonstrativoPosPagoConstantes.PENALIDADE_REJEICOES_DEVIDAS_CONTRA_LD,
				DemonstrativoPosPagoConstantes.PENALIDADE_CHAMADA_PERDIDA_CONTRA_CLARO,DemonstrativoPosPagoConstantes.PENALIDADE_SLA_CONTRA_CLARO,
				DemonstrativoPosPagoConstantes.MULTAS_JUROS_ATRASO_REPASSE_CONTRA_CLARO,DemonstrativoPosPagoConstantes.MULTAS_JUROS_ATRASO_PAGAMENTO_CONTRA_LD);		
	}
	
	/**
	 * Método utilitário para somar valores brutos de itens de repasse.
	 * @param map Map com os itens de repasse indexados por código do item de repasse.
	 * @param codigos
	 * @return
	 */
	private Double somaValores(Map<Long, DemonstrativoRepassePosDecorator> map,Long...codigos)
	{
		Double valorFinal = 0.0;
		for (Long codigo : codigos) {
			if ((map.containsKey(codigo)) && (map.get(codigo).getRepasse() != null))
				{
				valorFinal = zeroIfNull(valorFinal)+zeroIfNull(map.get(codigo).getRepasse().getVlBrutoItemRepasse());
				}							
		}
		return valorFinal;
	}
	
	private Double somaValoresRepasse(Map<Long, SccRepasse> map,Long...codigos)
	{
		Double valorFinal = new Double(0.0);
		for (Long codigo : codigos) {
			if (map.containsKey(codigo))
				{
				valorFinal = valorFinal+map.get(codigo).getVlBrutoRepasse();
				}							
		}
		return valorFinal;
	}
	
	private Double calcularTotalAcertosRepasse(Map<Long, SccRepasse> map)
	{
		return somaValoresRepasse(map, DemonstrativoPosPagoConstantes.ACERTO_CONCILIACAO_CONTRA_CLARO,DemonstrativoPosPagoConstantes.ACERTO_CONCILIACAO_CONTRA_OPERADORA_LD);		
	}
	
	
	/**
	 * Calcula CPMF a descontar do repasse.
	 * @param map Map com os itens de repasse indexados por código do item de repasse.
	 * @return
	 */
	/*Verificar se é necessário o cálculo de CPMF.*/
	private DemonstrativoRepassePosDecorator geraCPMFDescontar(Map<Long, DemonstrativoRepassePosDecorator> map)
	{
		DemonstrativoRepassePosDecorator decorator = new DemonstrativoRepassePosDecorator("6 - CPMF A DESCONTAR DO REPASSE");
		SccRepasse repasse = new SccRepasse();
		decorator.setRepasse(repasse);
		return decorator;
	}
	
	/**
	 * Calcula valor total a repassar.
	 * @param map Map com os itens de repasse indexados por código do item de repasse.
	 * @return
	 */
	private DemonstrativoRepassePosDecorator geraTotalValorRepassar(Map<Long, DemonstrativoRepassePosDecorator> map)
	{
		if (map == null)
			{
			return null;
			}
		DemonstrativoRepassePosDecorator decorator = new DemonstrativoRepassePosDecorator("7 - TOTAL DO VALOR A REPASSAR (5 -6)");
		decorator.setBruto(geraBaseCalculoRepasse(map).getRepasse().getVlBrutoRepasse() - geraCPMFDescontar(map).getRepasse().getVlBrutoRepasse());
		decorator.setValorBrutoRepasse(true);
		return decorator;		
	}

	public SccRepasseServicoAdicionalDAO getRepasseServicoAdicionalDAO() {
		return repasseServicoAdicionalDAO;
	}

	public void setRepasseServicoAdicionalDAO(
			SccRepasseServicoAdicionalDAO repasseServicoAdicionalDAO) {
		this.repasseServicoAdicionalDAO = repasseServicoAdicionalDAO;
	}

	 
	public List<RepasseServicoAdicionalDecorator> carregaAssinaturasRepasse(String cdEOTClaro,String cdEOTLD,Long cdProdutoCobilling, Long cdPeriodicidade, Date dtInicioRepasse,boolean holding) 
	throws DAOException,ServiceException
	{
		List<RepasseServicoAdicionalDecorator> resultado = new ArrayList<RepasseServicoAdicionalDecorator>();
		List<SccRepasseServicoAdicional> items = getRepasseServicoAdicionalDAO().pesquisaAssinaturas(cdEOTClaro,cdEOTLD, cdProdutoCobilling, cdPeriodicidade, dtInicioRepasse,holding);
		if (items != null)
			{
			for (int i=0;i<items.size();i++)
				{
				SccRepasseServicoAdicional adicional = items.get(i);
				adicional.getOperadoraClaro();
				RepasseServicoAdicionalDecorator decorator = new RepasseServicoAdicionalDecorator(adicional);
				resultado.add(decorator);
				}
			}
		return resultado;
	}

	public SccRelatorioJurosMultaDAO getRelatorioJurosMultaDAO() {
		return relatorioJurosMultaDAO;
	}

	public void setRelatorioJurosMultaDAO(
			SccRelatorioJurosMultaDAO relatorioJurosMultaDAO) {
		this.relatorioJurosMultaDAO = relatorioJurosMultaDAO;
	}

	 
	public List<SccRelatorioJurosMulta> carregaJurosMultasRepasse(String cdEOTClaro, String cdEOT, Long cdProdutoCobilling,Date dtInicialRepasse) 
			throws DAOException, ServiceException {
		return getRelatorioJurosMultaDAO().pesquisaDemonstrativoJurosMultas(cdEOTClaro, cdEOT, cdProdutoCobilling, dtInicialRepasse);		
	}

	 
	public RepassePosPagoComposite carregaDadosRepasse(ConsultaRepassePosTO argument) 
			throws DAOException, ServiceException {		
		RepassePosPagoComposite composite = null;
		Map<Long, SccRepasse> indexRepasse = new HashMap<Long, SccRepasse>();
		try {
			List<SccRepasse> repasses = getRepasseDAO().carregaRepassePosPago(argument.getCdEOTClaro(), argument.getCdEOTLD(), argument.getCdProdutoCobilling(), argument.getDtInicialRepasse(),argument.getDtFinalRepasse() , argument.getCdStatusRepasse(),argument.isHolding());			
			 if ((repasses != null) && (!repasses.isEmpty()))
					 {
				 	 composite = new RepassePosPagoComposite();
					 for (int i=0;i<repasses.size();i++)
						{
						SccRepasse repasse = repasses.get(i);
						if (i == 0) /*Como os itens de repasse possuem sempre o mesmo status e tipo de contrato dentro do mesmo repasse , pego o primeiro valor.*/
							{
							composite.setStatusRepasse(repasses.get(i).getCdStatusRepasse());
							composite.setNuRepasse(repasses.get(i).getNuRepasse());							
							composite.setTipoContrato(repasses.get(i).getSccTipoContrato());
							}
							
						SccItemRepasse itemRepasse = getItemRepasseDAO().getByPk(repasse.getCdItemRepasse(), SccItemRepasse.class);
						composite.getItems().put(itemRepasse, repasse);
						indexRepasse.put(itemRepasse.getCdItemRepasse(), repasse);
						}
					SccOperadora operadoraClaro = getOperadoraDAO().getByPk(argument.getCdEOTClaro(), SccOperadora.class);
					SccOperadora operadoraLD = getOperadoraDAO().getByPk(argument.getCdEOTLD(), SccOperadora.class);
					composite.setOperadoraClaro(operadoraClaro);
					composite.setOperadoraLD(operadoraLD);			
					SccProdutoCobilling produtoCobilling = getProdutoCobillingDAO().getByPk(argument.getCdProdutoCobilling(), SccProdutoCobilling.class);
					composite.setProdutoCobilling(produtoCobilling);
					composite.setDtInicialRepasse(argument.getDtInicialRepasse());
					composite.setValorBrutoRepasse(calculaValorBrutoRepasse(indexRepasse));
					SccPeriodicidadeRepasse periodicidadeRepasse = getPeriodicidadeRepasseDAO().getByPk(argument.getCdPeriodicidade(), SccPeriodicidadeRepasse.class);
					composite.setPeriodicidadeRepasse(periodicidadeRepasse);
					 }
			return composite;
		} catch (DAOException daoException){
			error("DAOException", daoException);
			throw daoException;
		}catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
	}

	public SccItemRepasseDAO getItemRepasseDAO() {
		return itemRepasseDAO;
	}

	public void setItemRepasseDAO(SccItemRepasseDAO itemRepasseDAO) {
		this.itemRepasseDAO = itemRepasseDAO;
	}

	public SccTipoContratoDAO getTipoContratoDAO() {
		return tipoContratoDAO;
	}

	public void setTipoContratoDAO(SccTipoContratoDAO tipoContratoDAO) {
		this.tipoContratoDAO = tipoContratoDAO;
	}

	 
	public List<SccRepasse> carregaItensRepasse(Long nuRepasse) throws DAOException {
		return getRepasseDAO().carregaItensRepasse(nuRepasse);
	}

	 
	public SccItemRepasse getItemRepasse(Long cdItemRepasse) throws DAOException {
		return getItemRepasseDAO().getByPk(cdItemRepasse, SccItemRepasse.class);
	}

	 
	@Transactional
	public void removeItemRepasse(Long cdItemRepasse,Long nuRepasse) throws DAOException,ServiceException{
		try {
			getRepasseDAO().removeItemRepasse(cdItemRepasse, nuRepasse);
		} catch (Exception e)
			{
			if (e instanceof DAOException)
				throw (DAOException)e;
			else
				throw new ServiceException(e.getMessage(), e);
			}
		
	}

	 
	@Transactional
	public void ajustaValorBrutoItem(Double novoValor, SccRepasse repasse) throws DAOException,ServiceException {		
		try {
			if (repasse.getSqRepasse() != null)
				{
				repasse.setVlBrutoItemRepasse(novoValor);
				getRepasseDAO().update(repasse);	
				}
			else
				{
				getRepasseDAO().create(repasse);
				}			
		}catch (Exception e)
		{
		if (e instanceof DAOException)			
			throw (DAOException)e;
		else
			throw new ServiceException(e.getMessage(), e);
		}
	}

	

	public SccProdutoPrepagoDAO getProdutoPrepagoDAO() {
		return produtoPrepagoDAO;
	}

	public void setProdutoPrepagoDAO(SccProdutoPrepagoDAO produtoPrepagoDAO) {
		this.produtoPrepagoDAO = produtoPrepagoDAO;
	}

	public List<RelPrestacaoServicoView> gerarRelatorioPrestacaoServicoPos(ConsultaRepassePosTO consultaRepassePosTO)
			throws DAOException {
		
		List<RelPrestacaoServicoView> listRelPrestacaoServico = null;
		if(consultaRepassePosTO != null){
			
			listRelPrestacaoServico = getRepasseDAO().gerarRelatorioPrestacaoServicoPos(consultaRepassePosTO.getCdEOTClaro(), 
					consultaRepassePosTO.getCdEOTLD(), consultaRepassePosTO.getCdProdutoCobilling(), 
					consultaRepassePosTO.getDtInicialRepasse(), consultaRepassePosTO.getDtFinalRepasse());
			
		}
		
		return listRelPrestacaoServico;
	}

	@Override
	public List<RelPrestacaoServicoView> gerarRelatorioPrestacaoServicoPre(
			ConsultaRepassePosTO consultaRepassePosTO) throws DAOException {
		
		List<RelPrestacaoServicoView> listRelPrestacaoServico = null;
		if(consultaRepassePosTO != null){
			
			listRelPrestacaoServico = getRepasseDAO().gerarRelatorioPrestacaoServicoPre(consultaRepassePosTO.getCdEOTClaro(), consultaRepassePosTO.getCdEOTLD(), consultaRepassePosTO.getCdProdutoCobilling(), consultaRepassePosTO.getMesAno());
			
		}
		
		return listRelPrestacaoServico;
	}
	
	

	
	
	

	
	
}
