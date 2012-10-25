package com.claro.sccweb.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivoCobillingDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccMemoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccPagamentoRepasseDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccParamProcessoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccPreFechamentoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioJurosMultaDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccRepasseDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccRepasseServicoAdicionalDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccPagamentoRepasse;
import com.claro.cobillingweb.persistence.entity.SccPagamentoRepassePK;
import com.claro.cobillingweb.persistence.entity.SccParamProcesso;
import com.claro.cobillingweb.persistence.entity.SccParamProcessoPK;
import com.claro.cobillingweb.persistence.entity.SccPreFechamento;
import com.claro.cobillingweb.persistence.entity.SccRepasse;
import com.claro.cobillingweb.persistence.entity.SccRepasseServicoAdicional;
import com.claro.cobillingweb.persistence.entity.SccStatusArquivo;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.PagamentoRepasseService;
import com.claro.sccweb.service.ServiceException;
import com.claro.sccweb.service.composite.RepassePosPagoComposite;
import com.claro.sccweb.service.composite.RepassePrePagoComposite;

public class PagamentoRepasseServiceImpl extends AbstractService implements PagamentoRepasseService {

	private SccRepasseDAO repasseDAO;
	private SccPagamentoRepasseDAO pagamentoRepasseDAO;
	private SccOperadoraDAO operadoraDAO;
	private SccMemoDAO memoDAO;
	private SccArquivoCobillingDAO arquivoCobillingDAO;
	private SccRepasseServicoAdicionalDAO repasseServicoAdicionalDAO;
	private SccRelatorioJurosMultaDAO relatorioJurosMultaDAO;
	private SccPreFechamentoDAO preFechamentoDAO;
	private SccParamProcessoDAO paramProcessoDAO;
	
	public SccRepasseDAO getRepasseDAO() {
		return repasseDAO;
	}
	public void setRepasseDAO(SccRepasseDAO repasseDAO) {
		this.repasseDAO = repasseDAO;
	}
	public SccPagamentoRepasseDAO getPagamentoRepasseDAO() {
		return pagamentoRepasseDAO;
	}
	public void setPagamentoRepasseDAO(SccPagamentoRepasseDAO pagamentoRepasseDAO) {
		this.pagamentoRepasseDAO = pagamentoRepasseDAO;
	}
	public SccOperadoraDAO getOperadoraDAO() {
		return operadoraDAO;
	}
	public void setOperadoraDAO(SccOperadoraDAO operadoraDAO) {
		this.operadoraDAO = operadoraDAO;
	}
	public SccMemoDAO getMemoDAO() {
		return memoDAO;
	}
	public void setMemoDAO(SccMemoDAO memoDAO) {
		this.memoDAO = memoDAO;
	}
	 
	public SccParamProcessoDAO getParamProcessoDAO() {
		return paramProcessoDAO;
	}
	public void setParamProcessoDAO(SccParamProcessoDAO paramProcessoDAO) {
		this.paramProcessoDAO = paramProcessoDAO;
	}
	@Transactional
	public void realizaPagamentoRepasse(RepassePosPagoComposite composite) throws DAOException, ServiceException {
		Date now = new Date();		
		try {
			info("Iniciando pagamento do repasse "+composite.getNuRepasse()+" para LD "+composite.getOperadoraLD().getDsOperadora());
			/* 1. Atualiza os arquivos de cobilling associados a esse repasse*/
			SccStatusArquivo statusArquivoRepassado = new SccStatusArquivo();
			statusArquivoRepassado.setCdStatusArquivo("RE");			
			info("Pesquisando arquivos associados com repasse "+composite.getNuRepasse());
			List<SccArquivoCobilling> arquivosAfetados = getArquivoCobillingDAO().pesquisaArquivosRemessa(composite.getNuRepasse());
			if (arquivosAfetados != null)
				{
				info("Repasse "+composite.getNuRepasse()+" possui "+arquivosAfetados.size()+" arqivos associados");
				for (int i=0;i<arquivosAfetados.size();i++)
					{
					SccArquivoCobilling arquivoCobilling = arquivosAfetados.get(i);
					info("Atualizando status do arquivo "+arquivoCobilling.getNoArquivo()+" por pagamento de repasse "+composite.getNuRepasse());
					arquivoCobilling.setCdStatusArquivo(statusArquivoRepassado);
					arquivoCobilling.setDtStatusArquivo(now);
					getArquivoCobillingDAO().update(arquivoCobilling);
					}
				}
			info("Arquivos de repasse "+composite.getNuRepasse()+" atualizados");
			/* 2. Atualiza o status dos items do repasse para 'C'.*/
			
			info("Atualizando status dos items do repasse "+composite.getNuRepasse());
			List<SccRepasse>  itens = getRepasseDAO().carregaItensRepasse(composite.getNuRepasse());
			getRepasseDAO().atualizaStatusRepasse(composite.getNuRepasse(), "C");
			info("Itens  repasse "+composite.getNuRepasse()+" atualizado com status 'C'");
			/* 3. Atualiza o status dos items de assinatura.*/
			info("Pesquisando assinaturas associadas ao repasse "+composite.getNuRepasse());
			List<SccRepasseServicoAdicional> assinaturas = getRepasseServicoAdicionalDAO().pesquisaAssinaturas(composite.getOperadoraClaro().getCdEot(), composite.getOperadoraLD().getCdEot(), composite.getProdutoCobilling().getCdProdutoCobilling(), composite.getPeriodicidadeRepasse().getCdPeriodicidadeRepasse(), composite.getDtInicialRepasse(), false);
			if (assinaturas != null)
				{
				info("Repasse "+composite.getNuRepasse()+" possui "+assinaturas.size()+" assinaturas");
				for (int i=0;i<assinaturas.size();i++)
					{
					SccRepasseServicoAdicional assinatura = assinaturas.get(i);
					assinatura.setCdStatusRepasse("C");
					assinatura.setDtAtualizacao(now);	
					getRepasseServicoAdicionalDAO().update(assinatura);
					info("Assinatura "+assinatura.getSqRepasseServicoAdicional()+" do repasse "+composite.getNuRepasse()+" atualizada com status 'C'");
					}
				}
			/* 4. Cria um registro de pagamento.*/
			info("Criando pagamento do repasse "+composite.getNuRepasse());
			SccPagamentoRepassePK pagamentoPk = new SccPagamentoRepassePK();
			SccPagamentoRepasse pagamento = new SccPagamentoRepasse();
			pagamento.setVlRepasse(composite.getValorBrutoRepasse());
			pagamentoPk.setNuRepasse(composite.getNuRepasse());
			pagamento.setSgUf(composite.getOperadoraClaro().getSgUf());
			pagamentoPk.setCdEotHolding(composite.getOperadoraClaro().getCdEot());
			pagamentoPk.setCdEotLd(composite.getOperadoraLD().getCdEot());
			pagamentoPk.setDtReferencia(composite.getDtInicialRepasse());
			pagamentoPk.setCdTipoContrato(composite.getTipoContrato());
			pagamento.setNuNf(0L);
			pagamento.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
			pagamento.setVlBrutoNf(composite.getValorBrutoRepasse());
			Calendar dtPagamento = Calendar.getInstance();			
			dtPagamento.setTime(now);			 
			
			if (composite.getPeriodicidadeRepasse().getCdPeriodicidadeRepasse().equals(1L))
				{
				dtPagamento.set(Calendar.DAY_OF_MONTH, 30);
				if (dtPagamento.get(Calendar.DAY_OF_MONTH) >= 30)
					{
					dtPagamento.add(Calendar.MONTH, 1);
					}					
				}
				
			else if (composite.getPeriodicidadeRepasse().getCdPeriodicidadeRepasse().equals(2L))
				{
				dtPagamento.set(Calendar.DAY_OF_MONTH, 15);
				if (dtPagamento.get(Calendar.DAY_OF_MONTH) >= 15)
					{
					dtPagamento.add(Calendar.MONTH, 1);
					}
				}
				
			
			while (!isDiaUtil(dtPagamento.getTime()))
				{
				dtPagamento.add(Calendar.DAY_OF_MONTH, 1);
				}
			pagamento.setDtPagamentoRepasse(dtPagamento.getTime());						
			pagamento.setId(pagamentoPk);
			pagamento.setDtCriacao(now);
			pagamento.setDtAtualizacao(now);
			getPagamentoRepasseDAO().create(pagamento);
			info("Pagamento para repasse "+composite.getNuRepasse()+" realizado com sucesso.");
			/*5. Registra em SCC_MEMO o pagamento e o usuário que o confirmou.*/
		} catch (Exception e)
			{
			if (e instanceof DAOException)
				throw (DAOException)e;
			else
				throw new ServiceException(e.getMessage(), e);
			}
		
	}
	 
	public void cancelaPagamentoRepasse(RepassePosPagoComposite composite,String usuario) throws DAOException, ServiceException {
		Date now = new Date();
		try {
			info("Iniciando cancelamento do repasse "+composite.getNuRepasse()+" para LD "+composite.getOperadoraLD().getDsOperadora());
			/* 1. Atualiza os arquivos de cobilling associados a esse repasse*/
			SccStatusArquivo statusArquivoRepassado = new SccStatusArquivo();
			statusArquivoRepassado.setCdStatusArquivo("RE");			
			info("Pesquisando arquivos associados com repasse "+composite.getNuRepasse());
			List<SccArquivoCobilling> arquivosAfetados = getArquivoCobillingDAO().pesquisaArquivosRemessa(composite.getNuRepasse());
			if (arquivosAfetados != null)
				{
				info("Repasse "+composite.getNuRepasse()+" possui "+arquivosAfetados.size()+" arqivos associados");
				for (int i=0;i<arquivosAfetados.size();i++)
					{
					SccArquivoCobilling arquivoCobilling = arquivosAfetados.get(i);
					info("Atualizando status do arquivo "+arquivoCobilling.getNoArquivo()+" por cancelamento de repasse "+composite.getNuRepasse());
					arquivoCobilling.setCdStatusArquivo(statusArquivoRepassado);
					arquivoCobilling.setDtStatusArquivo(now);
					getArquivoCobillingDAO().update(arquivoCobilling);
					}
				}
			info("Arquivos de repasse "+composite.getNuRepasse()+" atualizados");
			/* 2. Atualiza o status dos items do repasse para 'N'.*/
			
			info("Atualizando status dos items do repasse "+composite.getNuRepasse());
			List<SccRepasse>  itens = getRepasseDAO().carregaItensRepasse(composite.getNuRepasse());
			for (int i=0;i<itens.size();i++)
				{
				SccRepasse repasse = itens.get(i);
				repasse.setDtAtualizacao(now);
				repasse.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
				repasse.setCdStatusRepasse("N");				
				getRepasseDAO().update(repasse);
				info("Item "+repasse.getCdItemRepasse()+"do repasse "+composite.getNuRepasse()+" atualizado com status 'N'");
				}
			
			/* 3. Atualiza o status dos items de assinatura.*/
			info("Pesquisando assinaturas associadas ao repasse "+composite.getNuRepasse());
			List<SccRepasseServicoAdicional> assinaturas = getRepasseServicoAdicionalDAO().pesquisaAssinaturas(composite.getOperadoraClaro().getCdEot(), composite.getOperadoraLD().getCdEot(), composite.getProdutoCobilling().getCdProdutoCobilling(), composite.getPeriodicidadeRepasse().getCdPeriodicidadeRepasse(), composite.getDtInicialRepasse(), false);
			if (assinaturas != null)
				{
				info("Repasse "+composite.getNuRepasse()+" possui "+assinaturas.size()+" assinaturas");
				for (int i=0;i<assinaturas.size();i++)
					{
					SccRepasseServicoAdicional assinatura = assinaturas.get(i);
					assinatura.setCdStatusRepasse("N");
					assinatura.setDtAtualizacao(now);	
					getRepasseServicoAdicionalDAO().update(assinatura);
					info("Assinatura "+assinatura.getSqRepasseServicoAdicional()+" do repasse "+composite.getNuRepasse()+" atualizada com status 'C'");
					}
				}
			getRelatorioJurosMultaDAO().apagaJurosMultasRepasse(composite.getOperadoraClaro().getCdEot(), composite.getOperadoraLD().getCdEot(), composite.getPeriodicidadeRepasse().getCdPeriodicidadeRepasse(), composite.getProdutoCobilling().getCdProdutoCobilling(), composite.getDtInicialRepasse());
		}  catch (Exception e)
		{
		if (e instanceof DAOException)
			throw (DAOException)e;
		else
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	
	public SccArquivoCobillingDAO getArquivoCobillingDAO() {
		return arquivoCobillingDAO;
	}
	public void setArquivoCobillingDAO(SccArquivoCobillingDAO arquivoCobillingDAO) {
		this.arquivoCobillingDAO = arquivoCobillingDAO;
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
	
	
	@Transactional	 
	public void realizaPagamentoRepasse(RepassePrePagoComposite composite, String usuario) throws DAOException, ServiceException {
		try {
			SccPreFechamento fechamento = getPreFechamentoDAO().getByPk(composite.getSqPreFechamento(),
					SccPreFechamento.class);
			fechamento.setCdStatusFechamento("C");
			getPreFechamentoDAO().update(fechamento);
			SccPagamentoRepassePK pagamentoRepassePK = new SccPagamentoRepassePK();
			pagamentoRepassePK.setCdEotHolding(composite.getOperadoraClaro().getCdEot());
			pagamentoRepassePK.setCdEotLd(composite.getOperadoraLD().getCdEot());
			pagamentoRepassePK.setCdTipoContrato("P");
			pagamentoRepassePK.setDtReferencia(composite.getPreFechamento().getDtInicialFechamento());
			pagamentoRepassePK.setNuRepasse(composite.getPreFechamento().getSqPreFechamento());
			SccPagamentoRepasse pagamentoRepasse = new SccPagamentoRepasse();
			pagamentoRepasse.setId(pagamentoRepassePK);
			pagamentoRepasse.setCdFormaPagamento("1");
			pagamentoRepasse.setVlBrutoNf(composite.getPreFechamento().getVlFinalRepassar());
			pagamentoRepasse.setVlRepasse(composite.getPreFechamento().getVlFinalRepassar());
			Calendar cal = Calendar.getInstance();
			if (cal.get(Calendar.DAY_OF_MONTH) >= 10) {
				cal.add(Calendar.MONTH, 1);
			}
			cal.set(Calendar.DAY_OF_MONTH, 10);
			pagamentoRepasse.setDtEmissaoNf(cal.getTime());
			cal.set(Calendar.DAY_OF_MONTH, 20);
			pagamentoRepasse.setDtPagamentoRepasse(cal.getTime());
			pagamentoRepasse.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
			pagamentoRepasse.setQtDiasAtraso(0L);
			Date now = new Date();
			pagamentoRepasse.setDtCriacao(now);
			pagamentoRepasse.setDtAtualizacao(now);
			getPagamentoRepasseDAO().create(pagamentoRepasse);
			
			gerarParamProcessoMab(fechamento, usuario);
			
		} catch (DAOException daoEx) {
			throw daoEx;
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage(), ex);
		}
		
	}
	 
	public void cancelaPagamentoRepasse(RepassePrePagoComposite repasse) throws DAOException, ServiceException {
		// TODO Auto-generated method stub
		
	}
	public SccPreFechamentoDAO getPreFechamentoDAO() {
		return preFechamentoDAO;
	}
	public void setPreFechamentoDAO(SccPreFechamentoDAO preFechamentoDAO) {
		this.preFechamentoDAO = preFechamentoDAO;
	}
	 
	public SccPagamentoRepasse getPagamentoByRepasse(Long nuRepasse,String cdEOTClaro, String cdEOTLD) throws DAOException {		
		return getPagamentoRepasseDAO().getPagamentoByRepasse(nuRepasse, cdEOTClaro, cdEOTLD);
	}
	
	
	 
	@Transactional
	public void confirmaDadosRepasse(SccPagamentoRepasse pagamentoRepasse) throws DAOException {
		pagamentoRepasse.setDtAtualizacao(Calendar.getInstance().getTime());
		pagamentoRepasse.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		getPagamentoRepasseDAO().update(pagamentoRepasse);
		
	}
	
	private void gerarParamProcessoMab(SccPreFechamento fechamento, String username) throws DAOException {
		final SccParamProcessoPK pk = new SccParamProcessoPK();
		pk.setCdProcesso(CobillingConstants.PARAMETER_PREPAGO_MAB);
		StringBuffer nmParametro = new StringBuffer();

		// EOT LD (3) 
		// EOT CLARO (3)
		// DT FECHAMENTO (8)
		// DT INI FECHAMENTO (8)
		// DT FIM FECHAMENTO (8)
		// PRODUTO (4)
		nmParametro.append(fechamento.getCdEotLd());
		nmParametro.append(fechamento.getCdEotClaro());
		nmParametro.append(dateFormat.format(fechamento.getDtFechamento()));
		nmParametro.append(dateFormat.format(fechamento.getDtInicialFechamento()));
		nmParametro.append(dateFormat.format(fechamento.getDtFimFechamento()));
		nmParametro.append(leftZeroFill(fechamento.getCdProdutoPrepago(), 4));
		pk.setNmParametro(nmParametro.toString());

		SccParamProcesso toTest = getParamProcessoDAO().getByPk(pk, SccParamProcesso.class);
		final Date now = new Date();
		if (toTest == null) {
			SccParamProcesso paramProcesso = new SccParamProcesso();
			paramProcesso.setCdTipoParametro(CobillingConstants.ALFA);
			paramProcesso.setTxValorParametro(CobillingConstants.TO_LOAD);
			paramProcesso.setDtCriacao(now);
			paramProcesso.setCdUsuarioManut(username);
			paramProcesso.setId(pk);
			getParamProcessoDAO().create(paramProcesso);
		}
	}
	
}
