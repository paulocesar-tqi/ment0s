package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.filtro.SCCArquivoCobillingFiltro;
import com.claro.cobillingweb.persistence.filtro.SCCArquivoCobillingFiltroMulti;

public interface SccArquivoCobillingDAO extends BasicDAO<SccArquivoCobilling> {
	 
	
	public List<SccArquivoCobilling> pesquisaArquivos(SCCArquivoCobillingFiltro to) throws DAOException;	
	
	
	/*Usado para pré-pago.*/
	public List<SccArquivoCobilling> pesquisaArquivos(SCCArquivoCobillingFiltroMulti to) throws DAOException;

	/**
	 * Pesquisa os arquivos de cobilling associados a uma remessa.
	 * @param nuRemessa Identificador da remessa;
	 * @return
	 * @throws DAOException
	 */
	public List<SccArquivoCobilling> pesquisaArquivosRemessa(Long nuRemessa) throws DAOException;
	
	/**
	 * Pesquisa o arquivo de protocolo associado a um arquivo de cobilling.
	 * Arquivos de protocolo são do tipo 60 ou 65 e possuem como arquivo de origem o arquivo usado como filtro.
	 * @param arquivoCobilling
	 * @return
	 * @throws DAOException
	 */
	public  SccArquivoCobilling pesquisaArquivoProtocolo(SccArquivoCobilling arquivoCobilling) throws DAOException;
	
	public List<SccArquivoCobilling> pesquisaArquivosRemessa(String cdEOTClaro,String cdEOTLD,String tipoPeriodo,Date dtInicial,Date dtFinal,String statusArquivo,boolean holding) throws DAOException;
	
	public List<SccArquivoCobilling> pesquisaArquivosRemessaFranquia(String cdEOTClaro,String cdEOTLD,String tipoPeriodo,Date dtInicial,Date dtFinal,String statusArquivo,boolean holding) throws DAOException;
	
	public List<SccArquivoCobilling> pesquisaArquivosRetorno(String cdEOTClaro,String cdEOTLD,Long cdTipoArquivo,String statusArquivo,String sistema,String tipoPeriodo,Date dtInicial,Date dtFinal,boolean holding) throws DAOException;
	
	public List<SccArquivoCobilling> pesquisaArquivosDataProcessamento(String cdEOTClaro,String cdEOTLD,Date dataInicial,Date dataFinal) throws DAOException;
	
	public List<SccArquivoCobilling> pesquisaRelatoriosTransicao(Long tiposArquivo,Date dataInicial,Date dataFinal) throws DAOException;
}
