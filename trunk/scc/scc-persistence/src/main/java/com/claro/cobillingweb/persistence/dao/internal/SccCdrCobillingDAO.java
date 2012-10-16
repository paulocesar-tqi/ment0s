package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.cobillingweb.persistence.filtro.SccCdrCobillingFiltro;

public interface SccCdrCobillingDAO extends BasicDAO<SccCdrCobilling> {

	public List<SccCdrCobilling> pesquisaCDRs(SccCdrCobillingFiltro filtro) throws DAOException;
	
	public List<SccCdrCobilling> pesquisaArquivoPorStatus(Long seqArquivo,Long cdStatus) throws DAOException;
	
	public List<Object[]> pesquisaMatrizListaCDRsPorStatus(Long seqArquivo,Long cdStatus) throws DAOException;
	
	public List<SccCdrCobilling> geraResumoCDRsSemErroArquivo(Long seqArquivo) throws DAOException;
	
	public List<SccCdrCobilling> geraResumoCDRsComErroArquivo(Long seqArquivo) throws DAOException;
	
	public List<SccCdrCobilling> geraResumoCDRsArquivo(Long seqArquivo) throws DAOException;

	public List<SccCdrCobilling> geraResumoCDRs(String cdEOTClaro , String cdEOTLD,Date dataInicial,Date dataFinal) throws DAOException;
	
	public List<SccCdrCobilling> listaCDRsArquivo(Long seqArquivo,SccCdrCobilling filtro,int pagina,int quantidadeRegistros) throws DAOException;
	
	public Long contaCDRsArquivo(Long seqArquivo,SccCdrCobilling filtro) throws DAOException;
	
	public List<SccCdrCobilling> pesquisaCDRsRejeitados(String cdMotivoRejeicao,String cdEOTClaro , String cdEOTLD,Date dataInicial,Date dataFinal ) throws DAOException;
}
