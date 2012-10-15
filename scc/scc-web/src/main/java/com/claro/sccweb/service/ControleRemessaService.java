package com.claro.sccweb.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.cobillingweb.persistence.filtro.SccFiltroRelPerdaFaturamento;
import com.claro.cobillingweb.persistence.view.RelCDRPrePagoView;
import com.claro.cobillingweb.persistence.view.SomatorioRelCDRPrePagoView;
import com.claro.sccweb.vo.PerdaFaturamentoVO;


public interface ControleRemessaService {
	
	public List<SccArquivoSumarizado>  pesquisaSumarioPorCiclo(Long aaCiclo,Long mmCiclo,Long cdCiclo,String cdEOTLD,String cdEOTClaro,Long cdProdutoCobilling,Long cdTipoArquivo,boolean holding) throws DAOException;
	
	public List<RelCDRPrePagoView> pesquisaCDRsPrePago(String cdEOTClaro,String cdEOTLD,String tipoPeriodo,Date dataInicial,Date dataFinal,boolean holding) throws DAOException;
	
	public SomatorioRelCDRPrePagoView geraSomatorioCDRs(Date dataInicial,Date dataFinal,String tipoPeriodo) throws DAOException;
	
	public List<SccArquivoSumarizado> geraSumarizadoPeriodo(String cdEOTClaro,String cdEOTLD,Date dataInicial,Date dataFinal,boolean holding) throws DAOException;
	
	List<SccArquivoSumarizado> pesquisaSumarioPorEvento(String cdEOTClaro, String cdEOTLD, Date dataInicial, Date dataFinal, Long cdProdutoCobilling, Long[] statusCdr, boolean holding, boolean tipoPeriodo) throws DAOException;
	
	Collection<PerdaFaturamentoVO> gerarRelatorioPerdaFaturamento(SccFiltroRelPerdaFaturamento filtro) throws DAOException;
	
}
