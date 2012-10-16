package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccArquivoCredito;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.cobillingweb.persistence.entity.SccStatusArquivo;
import com.claro.cobillingweb.persistence.entity.SccTipoArquivo;
import com.claro.cobillingweb.persistence.entity.external.ControlConnectFile;
import com.claro.cobillingweb.persistence.entity.external.ViewArquivoPrePago;
import com.claro.cobillingweb.persistence.filtro.SCCArquivoCobillingFiltro;
import com.claro.cobillingweb.persistence.view.RelEventosArquivoView;
import com.claro.sccweb.service.to.PesquisaArquivosConnectTO;

/**
 * Serviço que oferece métodos de negócio envolvendo objetos de arquivos.
 *
 */
public interface ArquivosService {
	
	public static final String TIPO_OPERADORA_CLARO = "OP";
	public static final String TIPO_OPERADORA_HOLDING = "HO";

	public List<ControlConnectFile> pesquisaArquivosConnect(PesquisaArquivosConnectTO to) throws ServiceException,DAOException;
	
	public List<SccArquivoCobilling> pesquisaArquivos(SCCArquivoCobillingFiltro to) throws ServiceException,DAOException;
	
	public List<SccArquivoCobilling> pesquisaArquivosPrePago(SCCArquivoCobillingFiltro to) throws ServiceException,DAOException;
	 
	public List<Long> pesquisaTipoArquivoPorBatimento(String cdTipoBatimento) throws ServiceException,DAOException;	
	
	public List<SccTipoArquivo> pesquisaEntidadePorTipoBatimento(String cdTipoBatimento) throws ServiceException,DAOException;
	
	public List<SccTipoArquivo> pesquisaTiposArquivoPrePago() throws ServiceException,DAOException;
	
	public List<SccStatusArquivo> pesquisaStatusArquivoPrePago() throws ServiceException,DAOException;
	
	public SccArquivoCobilling carregaArquivoByPK(Long seqArquivo) throws ServiceException,DAOException;
	
	public List<ViewArquivoPrePago> carregaDetalhesArquivoPrePago(Long noSeqArquivo) throws ServiceException,DAOException;
	
	public SccArquivoCobilling pesquisaArquivoProtocolo(SccArquivoCobilling arquivoCobilling) throws DAOException;
	
	public List<SccArquivoCobilling> pesquisaArquivosRemessa(String cdEOTClaro,String cdEOTLD,String tipoPeriodo,Date dtInicial,Date dtFinal,String statusArquivo,boolean holding) throws DAOException;
	
	public List<SccArquivoCobilling> pesquisaArquivosRemessaFranquia(String cdEOTClaro,String cdEOTLD,String tipoPeriodo,Date dtInicial,Date dtFinal,String statusArquivo,boolean holding) throws DAOException;
	
	public List<SccCdrCobilling> geraResumoCDRsArquivo(Long seqArquivo) throws DAOException;
	
	public List<SccCdrCobilling> geraResumoCDRsSemErroArquivo(Long seqArquivo) throws DAOException;
	
	public List<SccCdrCobilling> geraResumoCDRsComErroArquivo(Long seqArquivo) throws DAOException;
	
	public List<SccCdrCobilling> listaCDRsArquivo(Long seqArquivo,SccCdrCobilling filtro,int pagina,int quantidadeRegistros) throws DAOException;
	
	public Long contaCDRsArquivo(Long seqArquivo, SccCdrCobilling filtro) throws DAOException ;
	
	public List<RelEventosArquivoView> geraRelatorioEventos(String cdEOTLD,String cdEOTClaro, Long cdCdrStatus, Date dataInicial,Date dataFinal) throws DAOException;
	
	public List<SccArquivoCobilling> pesquisaArquivosRetorno(String cdEOTClaro,String cdEOTLD, Long cdTipoArquivo, String statusArquivo,String sistema, String tipoPeriodo, Date dtInicial, Date dtFinal,boolean holding) throws DAOException;
	
	public List<SccArquivoCobilling> pesquisaArquivosDataProcessamento(String cdEOTClaro,String cdEOTLD,Date dataInicial,Date dataFinal) throws DAOException;
	
	public List<SccCdrCobilling> pesquisaCDRsRejeitados(String cdMotivoRejeicao,String cdEOTClaro , String cdEOTLD,Date dataInicial,Date dataFinal ) throws DAOException;
	
	public List<SccArquivoCredito> pesquisaArquivosCredito(String origem,String status, Date dataInicial, Date dataFinal) throws DAOException;
	
	public List<SccArquivoCobilling> pesquisaRelatoriosTransicao(Long tiposArquivo, Date dataInicial, Date dataFinal) throws DAOException;

	public List<SccCdrCobilling> geraResumoCDRs(String cdEOTClaro , String cdEOTLD,Date dataInicial,Date dataFinal ) throws DAOException;

}
