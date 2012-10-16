package com.claro.sccweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.external.ControlConnectFileDAO;
import com.claro.cobillingweb.persistence.dao.external.ViewArquivoPrePagoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivoCobillingDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivoCreditoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccCdrCobillingDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccPreDominioDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccStatusArquivoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccStatusCdrDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccTipoArquivoDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccArquivoCredito;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.cobillingweb.persistence.entity.SccPreDominio;
import com.claro.cobillingweb.persistence.entity.SccStatusArquivo;
import com.claro.cobillingweb.persistence.entity.SccTipoArquivo;
import com.claro.cobillingweb.persistence.entity.external.ControlConnectFile;
import com.claro.cobillingweb.persistence.entity.external.ViewArquivoPrePago;
import com.claro.cobillingweb.persistence.filtro.SCCArquivoCobillingFiltro;
import com.claro.cobillingweb.persistence.filtro.SCCArquivoCobillingFiltroMulti;
import com.claro.cobillingweb.persistence.view.RelEventosArquivoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.ArquivosService;
import com.claro.sccweb.service.ServiceException;
import com.claro.sccweb.service.to.PesquisaArquivosConnectTO;

/**
 * Implementação do serviço ArquivosService.
 *
 */
public class ArquivosServiceImpl extends AbstractService implements ArquivosService {
	
	private ControlConnectFileDAO controlConnectFileDAO;
	
	private SccArquivoCobillingDAO sccArquivoCobillingDAO;
	
	private SccTipoArquivoDAO sccTipoArquivoDAO;
	
	private SccPreDominioDAO preDominioDAO;
	
	private SccStatusArquivoDAO statusArquivoDAO;
	
	private ViewArquivoPrePagoDAO viewArquivoPrePagoDAO;
	
	private SccCdrCobillingDAO cdrCobillingDAO;
	
	private SccStatusCdrDAO statusCdrDAO;
	
	private SccArquivoCreditoDAO arquivoCreditoDAO;
	
	public ControlConnectFileDAO getControlConnectFileDAO() {
		return controlConnectFileDAO;
	}
	
	public void setControlConnectFileDAO(ControlConnectFileDAO controlConnectFileDAO) {
		this.controlConnectFileDAO = controlConnectFileDAO;
	}
	
	public List<ControlConnectFile> pesquisaArquivosConnect(PesquisaArquivosConnectTO to) throws ServiceException,DAOException {
		return getControlConnectFileDAO().pesquisaPorFiltros(to.getStatusArquivo(), to.getTipoArquivo(), to.getNomeArquivo(), to.getDataInicio(), to.getDataFinal());		
	}
	
	public SccArquivoCobillingDAO getSccArquivoCobillingDAO() {
		return sccArquivoCobillingDAO;
	}
	
	public void setSccArquivoCobillingDAO (SccArquivoCobillingDAO sccArquivoCobillingDAO) {
		this.sccArquivoCobillingDAO = sccArquivoCobillingDAO;
	}
	
	public SccTipoArquivoDAO getSccTipoArquivoDAO() {
		return sccTipoArquivoDAO;
	}
	
	public void setSccTipoArquivoDAO(SccTipoArquivoDAO sccTipoArquivoDAO) {
		this.sccTipoArquivoDAO = sccTipoArquivoDAO;
	}
	
	public List<SccArquivoCobilling> pesquisaArquivos(SCCArquivoCobillingFiltro to) throws ServiceException, DAOException {
			return getSccArquivoCobillingDAO().pesquisaArquivos(to);
	}
	
	public List<Long> pesquisaTipoArquivoPorBatimento(String cdTipoBatimento) throws DAOException {		
		return getSccTipoArquivoDAO().pesquisaPorTipoBatimento(cdTipoBatimento);
	}
	
	public List<SccTipoArquivo> pesquisaEntidadePorTipoBatimento(String cdTipoBatimento) throws ServiceException, DAOException {		
		return getSccTipoArquivoDAO().pesquisaEntidadePorTipoBatimento(cdTipoBatimento);
	}
	
	public SccArquivoCobilling carregaArquivoByPK(Long seqArquivo)	throws ServiceException, DAOException {
		return getSccArquivoCobillingDAO().getByPk(seqArquivo, SccArquivoCobilling.class);		
	}
	
	public List<SccArquivoCobilling> pesquisaArquivosPrePago(SCCArquivoCobillingFiltro to) throws ServiceException, DAOException {
		SCCArquivoCobillingFiltroMulti filtroMulti = new SCCArquivoCobillingFiltroMulti();
		filtroMulti.setTiposArquivo(new ArrayList<Long>());
		filtroMulti.setStatus(new ArrayList<String>());
		 
		try {
		if (to.getTipoArquivo().longValue() == BasicDAO.GET_ALL)
			{
			
			 List<SccPreDominio> dominioPreTipos = getPreDominioDAO().pesquisaPorTipoDominio("TPARQ");
			 for (int i=0;i<dominioPreTipos.size();i++)
				 filtroMulti.getTiposArquivo().add(Long.parseLong(dominioPreTipos.get(i).getId().getCdDominio()));			 
			}
		else
			{
			filtroMulti.getTiposArquivo().add(to.getTipoArquivo());
			}
		
		if (to.getStatusArquivo().equals(BasicDAO.GET_ALL_STRING))
			{			
			List<SccPreDominio> dominioPreStatus = getPreDominioDAO().pesquisaPorTipoDominio("STARQ");
			for (int i=0;i<dominioPreStatus.size();i++)
				 filtroMulti.getStatus().add(dominioPreStatus.get(i).getId().getCdDominio());	
			}
		else
			{
			filtroMulti.getStatus().add(to.getStatusArquivo());
			}
		} catch (Exception e)
			{
			throw new ServiceException("Erro em serviço ArquivoService.pesquisaArquivosPrePago : "+e.getMessage());
			}
		filtroMulti.setTipoPeriodo("REF");
		filtroMulti.setDataFinalPeriodo(to.getDataFinalPeriodo());
		filtroMulti.setDataInicialPeriodo(to.getDataInicialPeriodo());
		return getSccArquivoCobillingDAO().pesquisaArquivos(filtroMulti);
	}
	
	public SccPreDominioDAO getPreDominioDAO() {
		return preDominioDAO;
	}
	
	public void setPreDominioDAO(SccPreDominioDAO preDominioDAO) {
		this.preDominioDAO = preDominioDAO;
	}
	
	public List<SccTipoArquivo> pesquisaTiposArquivoPrePago() throws ServiceException, DAOException {
		List<SccTipoArquivo> lista = new ArrayList<SccTipoArquivo>();
		List<SccPreDominio> dominioPreTipos = getPreDominioDAO().pesquisaPorTipoDominio("TPARQ");
		if ((dominioPreTipos == null) || (dominioPreTipos.size() == 0))
			{
			throw new ServiceException("Dominio TPARQ não cadastrado no sistema!");
			}
		for (int i=0;i<dominioPreTipos.size();i++)
			lista.add(getSccTipoArquivoDAO().getByPk(Long.parseLong(dominioPreTipos.get(i).getId().getCdDominio()), SccTipoArquivo.class));
		return lista;
	}
	
	public List<SccStatusArquivo> pesquisaStatusArquivoPrePago() throws ServiceException, DAOException {
		List<SccStatusArquivo> lista = new ArrayList<SccStatusArquivo>();
		List<SccPreDominio> dominioPreTipos = getPreDominioDAO().pesquisaPorTipoDominio("STARQ");
		if ((dominioPreTipos == null) || (dominioPreTipos.size() == 0))
			{
			throw new ServiceException("Dominio STARQ não cadastrado no sistema!");
			}
		for (int i=0;i<dominioPreTipos.size();i++)
			lista.add(getStatusArquivoDAO().getByPk(dominioPreTipos.get(i).getId().getCdDominio(), SccStatusArquivo.class));
		return lista;
	}
	
	public SccStatusArquivoDAO getStatusArquivoDAO() {
		return statusArquivoDAO;
	}
	
	public void setStatusArquivoDAO(SccStatusArquivoDAO statusArquivoDAO) {
		this.statusArquivoDAO = statusArquivoDAO;
	}
	
	public ViewArquivoPrePagoDAO getViewArquivoPrePagoDAO() {
		return viewArquivoPrePagoDAO;
	}
	
	public void setViewArquivoPrePagoDAO(ViewArquivoPrePagoDAO viewArquivoPrePagoDAO) {
		this.viewArquivoPrePagoDAO = viewArquivoPrePagoDAO;
	}
	
	public List<ViewArquivoPrePago> carregaDetalhesArquivoPrePago(Long noSeqArquivo) throws ServiceException, DAOException {		
		return getViewArquivoPrePagoDAO().carregaDetalhesArquivoPrePago(noSeqArquivo);
	}
	
	public SccArquivoCobilling pesquisaArquivoProtocolo(SccArquivoCobilling arquivoCobilling) throws DAOException {		
		return getSccArquivoCobillingDAO().pesquisaArquivoProtocolo(arquivoCobilling);
	}
	
	public List<SccArquivoCobilling> pesquisaArquivosRemessa(String cdEOTClaro,String cdEOTLD, String tipoPeriodo, Date dtInicial, Date dtFinal,String statusArquivo, boolean holding) throws DAOException {
		return getSccArquivoCobillingDAO().pesquisaArquivosRemessa(cdEOTClaro, cdEOTLD, tipoPeriodo, dtInicial, dtFinal, statusArquivo, holding);
	}
	
	public List<SccArquivoCobilling> pesquisaArquivosRemessaFranquia(String cdEOTClaro, String cdEOTLD, String tipoPeriodo,Date dtInicial, Date dtFinal, String statusArquivo, boolean holding) throws DAOException {		
		return getSccArquivoCobillingDAO().pesquisaArquivosRemessaFranquia(cdEOTClaro, cdEOTLD, tipoPeriodo, dtInicial, dtFinal, statusArquivo, holding);
	}
	
	public List<SccCdrCobilling> geraResumoCDRsArquivo(Long seqArquivo) throws DAOException {
		return getCdrCobillingDAO().geraResumoCDRsArquivo(seqArquivo);
	}
	
	public SccCdrCobillingDAO getCdrCobillingDAO() {
		return cdrCobillingDAO;
	}
	
	public void setCdrCobillingDAO(SccCdrCobillingDAO cdrCobillingDAO) {
		this.cdrCobillingDAO = cdrCobillingDAO;
	}
	
	public List<SccCdrCobilling> geraResumoCDRsSemErroArquivo(Long seqArquivo) throws DAOException {		 
		return getCdrCobillingDAO().geraResumoCDRsSemErroArquivo(seqArquivo);
	}
	
	public List<SccCdrCobilling> geraResumoCDRsComErroArquivo(Long seqArquivo) throws DAOException {
		return getCdrCobillingDAO().geraResumoCDRsComErroArquivo(seqArquivo);
	}
	
	public List<SccCdrCobilling> listaCDRsArquivo(Long seqArquivo,SccCdrCobilling filtro, int pagina, int quantidadeRegistros) throws DAOException {		
		return getCdrCobillingDAO().listaCDRsArquivo(seqArquivo, filtro, pagina, quantidadeRegistros);
	}
	
	public Long contaCDRsArquivo(Long seqArquivo, SccCdrCobilling filtro) throws DAOException {
		return getCdrCobillingDAO().contaCDRsArquivo(seqArquivo, filtro);
	}
	
	public List<RelEventosArquivoView> geraRelatorioEventos(String cdEOTLD,String cdEOTClaro, Long cdCdrStatus, Date dataInicial,Date dataFinal) throws DAOException {
		return getStatusCdrDAO().geraRelatorioEventos(cdEOTLD, cdEOTClaro, cdCdrStatus, dataInicial, dataFinal);
	}
	
	public SccStatusCdrDAO getStatusCdrDAO() {
		return statusCdrDAO;
	}
	
	public void setStatusCdrDAO(SccStatusCdrDAO statusCdrDAO) {
		this.statusCdrDAO = statusCdrDAO;
	}
	
	public List<SccArquivoCobilling> pesquisaArquivosRetorno(String cdEOTClaro,String cdEOTLD, Long cdTipoArquivo, String statusArquivo,String sistema, String tipoPeriodo, Date dtInicial, Date dtFinal,boolean holding) throws DAOException {
		return getSccArquivoCobillingDAO().pesquisaArquivosRetorno(cdEOTClaro, cdEOTLD, cdTipoArquivo, statusArquivo, sistema, tipoPeriodo, dtInicial, dtFinal, holding);
	}
	
	public List<SccArquivoCobilling> pesquisaArquivosDataProcessamento(String cdEOTClaro, String cdEOTLD, Date dataInicial, Date dataFinal) throws DAOException {
		return getSccArquivoCobillingDAO().pesquisaArquivosDataProcessamento(cdEOTClaro, cdEOTLD, dataInicial, dataFinal);
	}
	
	public List<SccCdrCobilling> pesquisaCDRsRejeitados(String cdMotivoRejeicao, String cdEOTClaro, String cdEOTLD,Date dataInicial, Date dataFinal) throws DAOException {
		return getCdrCobillingDAO().pesquisaCDRsRejeitados(cdMotivoRejeicao, cdEOTClaro, cdEOTLD, dataInicial, dataFinal);
	}
	
	public SccArquivoCreditoDAO getArquivoCreditoDAO() {
		return arquivoCreditoDAO;
	}
	
	public void setArquivoCreditoDAO(SccArquivoCreditoDAO arquivoCreditoDAO) {
		this.arquivoCreditoDAO = arquivoCreditoDAO;
	}
	
	public List<SccArquivoCredito> pesquisaArquivosCredito(String origem,String status, Date dataInicial, Date dataFinal) throws DAOException {
		return getArquivoCreditoDAO().pesquisaArquivosCredito(origem, status, dataInicial, dataFinal);
	}
	
	public List<SccArquivoCobilling> pesquisaRelatoriosTransicao(Long tiposArquivo, Date dataInicial, Date dataFinal) throws DAOException {
		return getSccArquivoCobillingDAO().pesquisaRelatoriosTransicao(tiposArquivo, dataInicial, dataFinal);
	}

	@Override
	public List<SccCdrCobilling> geraResumoCDRs(String cdEOTClaro, String cdEOTLD, Date dataInicial, Date dataFinal) throws DAOException {
		return getCdrCobillingDAO().geraResumoCDRs(cdEOTClaro, cdEOTLD, dataInicial, dataFinal);
	}
	
}
