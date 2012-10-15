package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.cobillingweb.persistence.filtro.SccFiltroRelPerdaFaturamento;
import com.claro.cobillingweb.persistence.view.DemonstrativoSaldosLotesView;

public interface SccArquivoSumarizadoDAO extends BasicDAO<SccArquivoSumarizado> {

	public List<SccArquivoSumarizado> pesquisaSumarioPorCiclo(Long aaCiclo,Long mmCiclo,Long cdCiclo,String cdEOTLD,String cdEOTClaro,Long cdProdutoCobilling,Long cdTipoArquivo,boolean holding)
	throws DAOException;
	
	/*
	 * Usado para distribuição de CDRs.
	 */
	public List<SccArquivoSumarizado> geraSumarizadoPeriodo(String cdEOTClaro,String cdEOTLD,Date dataInicial,Date dataFinal,boolean holding) throws DAOException;
	
	public List<SccArquivoSumarizado> geraSumarizadoPeriodoCiclo(String cdEOTClaro,String cdEOTLD, Date dataInicial, Date dataFinal, boolean holding) throws DAOException;
	
	List<SccArquivoSumarizado> pesquisarPorEvento(String cdEOTClaro, String cdEOTLD, Date dataInicial, Date dataFinal, Long cdProdutoCobilling, Long[] statusCdr, boolean holding, boolean tipoPeriodo) throws DAOException;
	
	public List<DemonstrativoSaldosLotesView> geraDemonstrativoSaldosLotes(String cdEOTClaro,String cdEOTLD,Long cdProdutoCobilling,Long cdTipoArquivo,Date dataInicial,Date dataFinal) throws DAOException;
	
	Collection<SccArquivoSumarizado> gerarRelatorioPerdaFaturamento(SccFiltroRelPerdaFaturamento filtro) throws DAOException;
	
}
