package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.sccweb.form.ItemSumarioArquivoProcessado;


/**
 * Métodos de serviços para geração de sumários de arquivos de CDRs.
 *
 */
public interface SumarioService {

	
	/**
	 * Gera o sumário de CDRs de pós-pago  agupados por status.
	 * @param sccArquivoCobilling
	 * @return
	 * @throws ServiceException
	 * @throws DAOException
	 */
	public List<ItemSumarioArquivoProcessado> geraSumarioProcessadoPosXStatus(SccArquivoCobilling sccArquivoCobilling) throws ServiceException,DAOException;
	 
	public List<ItemSumarioArquivoProcessado> geraSumarioProcessadoPosXDataXStatus(SccArquivoCobilling sccArquivoCobilling,Long cdStatus) throws ServiceException,DAOException;
	
	
	public List<SccCdrCobilling> gerarListaCDRsSumario(Long cdSeqArquivo,Long cdStatus, Long cdCiclo, Long mmCiclo, Long aaCiclo,int page,int pageSize) throws ServiceException,DAOException;
	
	public int getCountListaCDRsSumario(Long cdSeqArquivo, Long cdStatus,Long cdCiclo, Long mmCiclo, Long aaCiclo) throws ServiceException,DAOException;
}
