package com.claro.sccweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccContratoAcordadoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccItemRepasseDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccParamProcessoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccPeriodicidadeRepasseDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccPreFechamentoAssinaturaDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccPreFechamentoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoCobillingDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoPrepagoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioJurosMultaDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccRepasseDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccRepasseServicoAdicionalDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccTipoContratoDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccParamProcesso;
import com.claro.cobillingweb.persistence.entity.SccParamProcessoPK;
import com.claro.cobillingweb.persistence.entity.SccPreFechamento;
import com.claro.cobillingweb.persistence.entity.SccPreFechamentoAssinatura;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.cobillingweb.persistence.view.RelApuracaoFechamentoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelSinteticoFechamentoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelSinteticoServicoPrePagoView;
import com.claro.sccweb.decorator.DemonstrativoRepassePreDecorator;
import com.claro.sccweb.decorator.DemonstrativoRepassePreItemDecorator;
import com.claro.sccweb.decorator.SccPreFechamentoAssinaturaDecorator;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.RepassePreService;
import com.claro.sccweb.service.ServiceException;
import com.claro.sccweb.service.calc.CalculoRepassePrePago;
import com.claro.sccweb.service.composite.RepassePrePagoComposite;
import com.claro.sccweb.service.composite.SolicitacaoRepassePreComposite;
import com.claro.sccweb.service.to.ConsultaRepassePreTO;
import com.claro.sccweb.service.to.DemonstrativoRepassePrePagoTO;
import com.claro.sccweb.service.to.SolicitacaoRepassePrePagoTO;

public class RepassePreServiceImpl extends AbstractService implements RepassePreService{

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
	private SccPreFechamentoDAO preFechamentoDAO;
	private SccPreFechamentoAssinaturaDAO preFechamentoAssinaturaDAO;
	
	
	
	 
	@Transactional
	public int insereSolicitacaoRepassePre(final SolicitacaoRepassePrePagoTO argument) throws DAOException, ServiceException {
		int resultado = 0;
		final Date now = new Date();
		try{
			List<SccOperadora> operadorasClaro = null;
			if (argument.getCdEODClaro().equals(BasicDAO.GET_ALL_STRING))
				{
				operadorasClaro = getOperadoraDAO().pequisaOperadorasClaro();
				}				
			else
				{
				operadorasClaro = new ArrayList<SccOperadora>();
				operadorasClaro.add(getOperadoraDAO().getByPk(argument.getCdEODClaro(), SccOperadora.class));
				}
			for (int i=0;i<operadorasClaro.size();i++)
				{
				final SccParamProcessoPK pk = new SccParamProcessoPK();
				pk.setCdProcesso(CD_PROCESSO_PREPAGO);
				StringBuffer nmParametro = new StringBuffer();
				if (argument.getMesRelatorio() < 10)
					{
					nmParametro.append("0"+argument.getMesRelatorio());
					}					
				else
					{
					nmParametro.append(argument.getMesRelatorio());
					}
					
				nmParametro.append("/");
				nmParametro.append(argument.getAnoRelatorio()+" ");
				nmParametro.append(argument.getCdEOTLD());
				nmParametro.append(operadorasClaro.get(i).getCdEot());
				nmParametro.append(argument.getCdCriterioCusto());
				nmParametro.append(leftZeroFill(argument.getCdProdutoPrepago(),4));
				pk.setNmParametro(nmParametro.toString());
				SccParamProcesso toTest = getParamProcessoDAO().getByPk(pk, SccParamProcesso.class);
				if (toTest == null)
					{
					SccParamProcesso paramProcesso = new SccParamProcesso();
					paramProcesso.setCdTipoParametro(VALOR_TO_LOAD_PRE);
					paramProcesso.setTxValorParametro(VALOR_TO_LOAD_PRE);
					paramProcesso.setDtCriacao(now);
					paramProcesso.setCdUsuarioManut(argument.getUserName());
					paramProcesso.setId(pk);
					getParamProcessoDAO().create(paramProcesso);
					if (argument.isReturnPks())
						{
						argument.getPksSolicitacao().add(pk);
						}						
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
	
	 
	@Transactional
	public void deleteSolicitacaoRepassePre(final String noRequisicao) throws DAOException, ServiceException {
		getParamProcessoDAO().deleteSolicitacaoRepassePre(noRequisicao);
		
	}
	
	public List<SolicitacaoRepassePreComposite> pesquisaSolicitacoesRepassePre(String status,int max) throws DAOException,ServiceException
	{
		List<SolicitacaoRepassePreComposite> resultados = new ArrayList<SolicitacaoRepassePreComposite>();
		List<SccParamProcesso> paramProcessoList = null;
		if (status.equals(VALOR_TO_LOAD_PRE))
			paramProcessoList = getParamProcessoDAO().pesquisaRepassesAgendados(CD_PROCESSO_PREPAGO);
		else if (status.equals(VALOR_LOADING_PRE))
			{
			paramProcessoList = getParamProcessoDAO().pesquisaRepassesProcessando(CD_PROCESSO_PREPAGO,max);
			}			
		else if (status.equals(VALOR_LOADED_PRE))
			{
			paramProcessoList = getParamProcessoDAO().pesquisaRepassesProcessados(CD_PROCESSO_PREPAGO,max);
			}		
		
		if (paramProcessoList != null)
			{
			String nmParametro = null;
			for (int i=0;i<paramProcessoList.size();i++)
				{
				try {
				final SccParamProcesso paramProcesso = paramProcessoList.get(i);
				nmParametro = paramProcesso.getId().getNmParametro();
				SolicitacaoRepassePreComposite composite = new SolicitacaoRepassePreComposite();
				SccOperadora operadoraClaro = getOperadoraDAO().getByPk(nmParametro.substring(11,14),SccOperadora.class);
				SccOperadora operadoraLD = getOperadoraDAO().getByPk(nmParametro.substring(8,11),SccOperadora.class);
				SccProdutoPrepago produtoPrepago = getProdutoPrepagoDAO().getByPk(String.valueOf(Long.parseLong(nmParametro.substring(16))), SccProdutoPrepago.class);
				String criterio = nmParametro.substring(14,16);
				composite.setCriterio(criterio);
				composite.setPeriodo(nmParametro.substring(0,7));
				composite.setDtCriacao(paramProcesso.getDtCriacao());
				composite.setDtEvento(paramProcesso.getDtAtualizacao());
				composite.setOperadoraClaro(operadoraClaro);
				composite.setOperadoraLD(operadoraLD);
				composite.setProdutoPrepago(produtoPrepago);
				composite.setNmParametro(nmParametro);
				composite.setUsuario(paramProcesso.getCdUsuarioManut());
				resultados.add(composite);
				} catch (Exception e)
					{
					error("Erro ao realizar parse em "+nmParametro, e);
					}
				}
			}
		return resultados;
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

	public SccRepasseServicoAdicionalDAO getRepasseServicoAdicionalDAO() {
		return repasseServicoAdicionalDAO;
	}

	public void setRepasseServicoAdicionalDAO(
			SccRepasseServicoAdicionalDAO repasseServicoAdicionalDAO) {
		this.repasseServicoAdicionalDAO = repasseServicoAdicionalDAO;
	}

	public SccRelatorioJurosMultaDAO getRelatorioJurosMultaDAO() {
		return relatorioJurosMultaDAO;
	}

	public void setRelatorioJurosMultaDAO(
			SccRelatorioJurosMultaDAO relatorioJurosMultaDAO) {
		this.relatorioJurosMultaDAO = relatorioJurosMultaDAO;
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

	public SccProdutoPrepagoDAO getProdutoPrepagoDAO() {
		return produtoPrepagoDAO;
	}

	public void setProdutoPrepagoDAO(SccProdutoPrepagoDAO produtoPrepagoDAO) {
		this.produtoPrepagoDAO = produtoPrepagoDAO;
	}

	 
	public List<RepassePrePagoComposite> consultaRepassesPrePago(ConsultaRepassePreTO to) throws DAOException, ServiceException {
		try {
			SccOperadora operadoraLD = getOperadoraDAO().getByPk(to.getCdEOTLD(), SccOperadora.class);
			List<RepassePrePagoComposite> resultado = new ArrayList<RepassePrePagoComposite>();
			List<SccPreFechamento> rows = getPreFechamentoDAO().pesquisaRepassesPreOperadora(to.getCdEOTLD(), to.getCdEOTClaro(), to.getCdProdutoPrepago(), to.getCdStatusRepasse(), calculaDataInicialPeriodoPrePago(to.getMes(), to.getAno()), calculaDataFinalPeriodoPrePago(to.getMes(), to.getAno()),to.isHolding());
			for (int i=0;i<rows.size();i++) {
				RepassePrePagoComposite composite = new RepassePrePagoComposite();				
				composite.setOperadoraClaro(getOperadoraDAO().getByPk(rows.get(i).getCdEotClaro(), SccOperadora.class));
				composite.setOperadoraLD(operadoraLD);
				if (to.getCdProdutoPrepago() != null)
					composite.setProdutoPrepago(getProdutoPrepagoDAO().getByPk(to.getCdProdutoPrepago(), SccProdutoPrepago.class));
				composite.setValorRepasse(rows.get(i).getVlFinalRepassar());
				composite.setSqPreFechamento(rows.get(i).getSqPreFechamento());
				composite.setPeriodo(to.getMes()+"/"+to.getAno());
				composite.setPreFechamento(rows.get(i));
				composite.setStatus(rows.get(i).getCdStatusFechamento());
				resultado.add(composite);
			}
			return resultado;
		} catch (DAOException daoEx) { throw daoEx;
		} catch (Exception e) { throw new ServiceException(e.getMessage(), e); }
	}
	
	public SccPreFechamentoDAO getPreFechamentoDAO() {
		return preFechamentoDAO;
	}
	
	public void setPreFechamentoDAO(SccPreFechamentoDAO preFechamentoDAO) {
		this.preFechamentoDAO = preFechamentoDAO;
	}
	
	public List<DemonstrativoRepassePreDecorator> carregaDemonstrativoOperadora(ConsultaRepassePreTO to,boolean full) throws DAOException, ServiceException {
		try {
			List<DemonstrativoRepassePreDecorator> resultado = new ArrayList<DemonstrativoRepassePreDecorator>();
			if (full) {
				List<SccPreFechamento> fechamentoHolding = getPreFechamentoDAO().pesquisaRepassesPreHolding(to.getCdEOTLD(), to.getCdEOTClaro(), to.getCdProdutoPrepago(),  calculaDataInicialPeriodoPrePago(to.getMes(), to.getAno()), calculaDataFinalPeriodoPrePago(to.getMes(), to.getAno()));
				if ((fechamentoHolding != null) && (fechamentoHolding.size() > 0)) {
					DemonstrativoRepassePreDecorator decorator = new DemonstrativoRepassePreDecorator();
					decorator.setHolding(true);
					decorator.setItens(geraLinhasDemonstrativo(fechamentoHolding.get(0)));
					resultado.add(decorator);
				}
				List<SccPreFechamento> fechamentoOperadoras = getPreFechamentoDAO().pesquisaRepassesPreOperadora(to.getCdEOTLD(), to.getCdEOTClaro(), to.getCdProdutoPrepago(), to.getCdStatusRepasse(), calculaDataInicialPeriodoPrePago(to.getMes(), to.getAno()), calculaDataFinalPeriodoPrePago(to.getMes(), to.getAno()),full);
				for (int o=0;o<fechamentoOperadoras.size();o++) {
					DemonstrativoRepassePreDecorator decorator = new DemonstrativoRepassePreDecorator();
					decorator.setHolding(false);
					decorator.setItens(geraLinhasDemonstrativo(fechamentoOperadoras.get(o)));
					resultado.add(decorator);
				}
			}
			return resultado;
		} catch (DAOException daoException) { throw daoException;
		} catch (Exception ex) { throw new ServiceException(ex.getMessage(), ex); }	
	}
	
	public List<DemonstrativoRepassePreItemDecorator> geraLinhasDemonstrativo(SccPreFechamento preFechamento) throws ServiceException {
		try {
			info("Executando cálculos para demonstrativo pré-pago com ID "+preFechamento.getSqPreFechamento());
			CalculoRepassePrePago calculoRepassePrePago = new CalculoRepassePrePago(preFechamento);
			List<DemonstrativoRepassePreItemDecorator> itens = new ArrayList<DemonstrativoRepassePreItemDecorator>();
			itens.add(calculoRepassePrePago.getTotalValorApurado());
			itens.add(calculoRepassePrePago.getValorChamadasApuradasMesAnterior());
			itens.add(calculoRepassePrePago.getValorChamadasApuradasOutrosMeses());
			itens.add(calculoRepassePrePago.getTotalDeducoes());
			itens.add(calculoRepassePrePago.getUtilizacaoPlataforma());
			itens.add(calculoRepassePrePago.getCreditosAutorizadosLD());
			itens.add(calculoRepassePrePago.getANATEL226());
			itens.add(calculoRepassePrePago.getTotalPenalidades());
			itens.add(calculoRepassePrePago.getPenalidadesMinutosContraClaro());
			itens.add(calculoRepassePrePago.getMultasAtrasoContraClaro());
			itens.add(calculoRepassePrePago.getMultasAtrasoContraLD());
			itens.add(calculoRepassePrePago.getTotalAcertos());
			itens.add(calculoRepassePrePago.getConciliacaoContraClaro());
			itens.add(calculoRepassePrePago.getConciliacaoContraLD());
			itens.add(calculoRepassePrePago.getCPMF());
			itens.add(calculoRepassePrePago.getICMSDescontar());
			itens.add(calculoRepassePrePago.getICMSRepassar());
			itens.add(calculoRepassePrePago.getValorFinal());				
			return itens;
		} catch (Exception e) { throw new ServiceException(e.getMessage(), e); }
	}
	
	public DemonstrativoRepassePreDecorator carregaDemonstrativoHolding(DemonstrativoRepassePrePagoTO to) throws DAOException,ServiceException {
		try {
			List<SccPreFechamento> fechamentoHolding = getPreFechamentoDAO().pesquisaRepassesPreHolding(to.getCdEOTLD(), to.getCdEOTClaro(), to.getCdProdutoPrepago(),  to.getDtInicial(), to.getDtFinal());
			DemonstrativoRepassePreDecorator decorator = new DemonstrativoRepassePreDecorator();
			if ((fechamentoHolding != null) && (fechamentoHolding.size() > 0)) {				
				decorator.setHolding(true);
				decorator.setItens(geraLinhasDemonstrativo(fechamentoHolding.get(0)));				
			}
			return decorator;
		} catch (DAOException daoEx) { throw daoEx;
		} catch (Exception e) { throw new ServiceException(e.getMessage(), e); }
	}
	
	public DemonstrativoRepassePreDecorator carregaDemonstrativoOperadoras(DemonstrativoRepassePrePagoTO to) throws DAOException,ServiceException {
		try {
			List<SccPreFechamento> fechamentoOperadoras = getPreFechamentoDAO().pesquisaRepassesPreOperadora(to.getCdEOTLD(), to.getCdEOTClaro(), to.getCdProdutoPrepago(),BasicDAO.GET_ALL_STRING, to.getDtInicial(), to.getDtFinal(),false);
			DemonstrativoRepassePreDecorator decorator = new DemonstrativoRepassePreDecorator();
			for (int o=0;o<fechamentoOperadoras.size();o++) {				
				decorator.setHolding(false);
				decorator.setItens(geraLinhasDemonstrativo(fechamentoOperadoras.get(o)));				
			}
			return decorator;
		} catch (DAOException daoEx) { throw daoEx;
		} catch (Exception e) { throw new ServiceException(e.getMessage(), e); }
	}
	
	public List<SccPreFechamentoAssinaturaDecorator> carregaAssinaturas(DemonstrativoRepassePrePagoTO to) throws DAOException,ServiceException {
		List<SccPreFechamentoAssinaturaDecorator> resultados = new ArrayList<SccPreFechamentoAssinaturaDecorator>();
		SccOperadora operadoraClaro;
		SccOperadora operadoraLD;
		try {			
			SccPreFechamentoAssinatura filtro = new SccPreFechamentoAssinatura();
			filtro.setCdEOTClaro(to.getCdEOTClaro());
			filtro.setCdEOTLD(to.getCdEOTLD());
			filtro.setCdProdutoPrepago(to.getCdProdutoPrepago());
			filtro.setDtInicialFechamento(to.getDtInicial());
			filtro.setDtFimFechamento(to.getDtFinal());
			List<SccPreFechamentoAssinatura> assinaturas = getPreFechamentoAssinaturaDAO().pesquisaAssinaturas(filtro);
			for (int i=0;i<assinaturas.size();i++) {
				operadoraLD = getOperadoraDAO().getByPk(to.getCdEOTLD(), SccOperadora.class);
				operadoraClaro = getOperadoraDAO().getByPk(to.getCdEOTClaro(), SccOperadora.class);
				SccPreFechamentoAssinaturaDecorator decorator = new SccPreFechamentoAssinaturaDecorator(assinaturas.get(i), operadoraClaro, operadoraLD);
				resultados.add(decorator);
			}
			return resultados;
		} catch (DAOException daoEx) { throw daoEx;
		} catch (Exception e) { throw new ServiceException(e.getMessage(), e); }
	}
	
	public SccPreFechamentoAssinaturaDAO getPreFechamentoAssinaturaDAO() {
		return preFechamentoAssinaturaDAO;
	}
	
	public void setPreFechamentoAssinaturaDAO (SccPreFechamentoAssinaturaDAO preFechamentoAssinaturaDAO) {
		this.preFechamentoAssinaturaDAO = preFechamentoAssinaturaDAO;
	}
	
	public List<RelSinteticoFechamentoPrePagoView> geraRelatorioSintetico(String cdProduto,String cdEOTLD, String cdEOTClaro, String cdStatusFechamento,Date dataInicial, Date dataFinal) throws DAOException {		
		return getPreFechamentoDAO().geraRelatorioSintetico(cdProduto,cdEOTLD, cdEOTClaro, cdStatusFechamento, dataInicial, dataFinal);
	}
	
	public List<RelSinteticoServicoPrePagoView> geraRelatorioSinteticoService(String cdProduto,String cdEOTLD, String cdEOTClaro, String cdStatusFechamento,Date dataInicial, Date dataFinal) throws DAOException {
		return getPreFechamentoDAO().geraRelatorioSinteticoServico(cdProduto,cdEOTLD, cdEOTClaro, cdStatusFechamento, dataInicial, dataFinal);
	}
	
	public List<RelApuracaoFechamentoPrePagoView> geraRelatorioApuracao(String cdProduto, String cdEOTLD, String cdEOTClaro,String cdStatusFechamento, Date dataInicial, Date dataFinal) throws DAOException {
		return getPreFechamentoDAO().geraRelatorioApuracao(cdProduto, cdEOTLD, cdEOTClaro, cdStatusFechamento, dataInicial, dataFinal);		
	}
	
}
