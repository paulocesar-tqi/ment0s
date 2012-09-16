package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.cobillingweb.persistence.view.SumarioCDRView;

public interface SumarioArquivoDAO extends BasicDAO<List<Object[]>>{

	public List<Object[]> carregaSumarioXStatusCdr(Long seqArquivo) throws DAOException;
	
	public List<Object[]> geraSumarioRemessaPosXDataXStatus(SccArquivoCobilling sccArquivoCobilling,Long cdStatus) throws DAOException;
	
	
	public List<SccCdrCobilling> gerarListaCDRsSumario(Long cdSeqArquivo,Long cdStatus, Long cdCiclo, Long mmCiclo, Long aaCiclo,int page,int pageSize) throws DAOException;
	
	public int getCountListaCDRsSumario(Long cdSeqArquivo,Long cdStatus, Long cdCiclo, Long mmCiclo, Long aaCiclo) throws DAOException;
}
