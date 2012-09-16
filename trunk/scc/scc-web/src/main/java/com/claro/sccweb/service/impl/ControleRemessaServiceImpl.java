package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivoSumarizadoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccCdrCoblDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.cobillingweb.persistence.view.RelCDRPrePagoView;
import com.claro.cobillingweb.persistence.view.SomatorioRelCDRPrePagoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.ControleRemessaService;

public class ControleRemessaServiceImpl extends AbstractService implements ControleRemessaService {

	private SccArquivoSumarizadoDAO arquivoSumarizadoDAO;
	private SccCdrCoblDAO cdrCoblDAO;
	
	 
	public List<SccArquivoSumarizado> pesquisaSumarioPorCiclo(Long aaCiclo,Long mmCiclo, Long cdCiclo, String cdEOTLD, String cdEOTClaro,Long cdProdutoCobilling,Long cdTipoArquivo,boolean holding) throws DAOException {
		return getArquivoSumarizadoDAO().pesquisaSumarioPorCiclo(aaCiclo, mmCiclo, cdCiclo, cdEOTLD, cdEOTClaro, cdProdutoCobilling,cdTipoArquivo,holding);
	}

	public SccArquivoSumarizadoDAO getArquivoSumarizadoDAO() {
		return arquivoSumarizadoDAO;
	}

	public void setArquivoSumarizadoDAO(SccArquivoSumarizadoDAO arquivoSumarizadoDAO) {
		this.arquivoSumarizadoDAO = arquivoSumarizadoDAO;
	}

	 
	public List<RelCDRPrePagoView> pesquisaCDRsPrePago(String cdEOTClaro,String cdEOTLD, String tipoPeriodo, Date dataInicial,Date dataFinal, boolean holding) throws DAOException {		
		return getCdrCoblDAO().pesquisaCDRsPrePago(cdEOTClaro, cdEOTLD, tipoPeriodo, dataInicial, dataFinal, holding);
	}

	public SccCdrCoblDAO getCdrCoblDAO() {
		return cdrCoblDAO;
	}

	public void setCdrCoblDAO(SccCdrCoblDAO cdrCoblDAO) {
		this.cdrCoblDAO = cdrCoblDAO;
	}

	 
	public SomatorioRelCDRPrePagoView geraSomatorioCDRs(Date dataInicial,Date dataFinal,String tipoPeriodo) throws DAOException {
		return getCdrCoblDAO().geraSomatorioCDRs(dataInicial, dataFinal, tipoPeriodo);		
	}

	 
	public List<SccArquivoSumarizado>  geraSumarizadoPeriodo(String cdEOTClaro,String cdEOTLD, Date dataInicial, Date dataFinal, boolean holding) throws DAOException {		
		return getArquivoSumarizadoDAO().geraSumarizadoPeriodo(cdEOTClaro, cdEOTLD, dataInicial, dataFinal, holding);
	}

	@Override
	public List<SccArquivoSumarizado> pesquisaSumarioPorEvento(String cdEOTClaro, String cdEOTLD, Date dataInicial, Date dataFinal, Long cdProdutoCobilling, Long statusCdr, boolean holding, boolean tipoPeriodo) throws DAOException {
		return getArquivoSumarizadoDAO().pesquisarPorEvento(cdEOTClaro, cdEOTLD, dataInicial, dataFinal, cdProdutoCobilling, statusCdr, holding, tipoPeriodo);
	}
	
	
}
