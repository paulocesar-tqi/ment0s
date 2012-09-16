package com.claro.sccweb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccCdrCobillingDAO;
import com.claro.cobillingweb.persistence.dao.internal.SumarioArquivoDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.sccweb.form.ItemSumarioArquivoProcessado;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.ServiceException;
import com.claro.sccweb.service.SumarioService;

public class SumarioServiceImpl extends AbstractService implements SumarioService {

	
	private SumarioArquivoDAO sumarioArquivoDAO;
	private SccCdrCobillingDAO cdrCobillingDAO;
	
	 
	public List<ItemSumarioArquivoProcessado> geraSumarioProcessadoPosXStatus(SccArquivoCobilling sccArquivoCobilling) throws ServiceException,DAOException {		
		List<Object[]>rows = getSumarioArquivoDAO().carregaSumarioXStatusCdr(sccArquivoCobilling.getSqArquivo());
		List<ItemSumarioArquivoProcessado>  sumario = new ArrayList<ItemSumarioArquivoProcessado>();
		try {			
			if (rows != null)
				{
				for (int i=0;i<rows.size();i++)
					{
					ItemSumarioArquivoProcessado item = new ItemSumarioArquivoProcessado();
					item.setKey(((BigDecimal)rows.get(i)[1]).longValue());
					item.setDescricaoItem((String)rows.get(i)[0]);
					item.setTotal(((BigDecimal)rows.get(i)[2]).longValue());
					sumario.add(item);
					}
				}
		} catch (Exception e)
			{
			throw new ServiceException("Erro ao executar service SumarioService.geraSumarioRemessaPosXStatus",e);
			}
		return sumario;
	}

	

	public SumarioArquivoDAO getSumarioArquivoDAO() {
		return sumarioArquivoDAO;
	}

	public void setSumarioArquivoDAO(SumarioArquivoDAO sumarioArquivoDAO) {
		this.sumarioArquivoDAO = sumarioArquivoDAO;
	}



	public SccCdrCobillingDAO getCdrCobillingDAO() {
		return cdrCobillingDAO;
	}



	public void setCdrCobillingDAO(SccCdrCobillingDAO cdrCobillingDAO) {
		this.cdrCobillingDAO = cdrCobillingDAO;
	}



	 
	public List<ItemSumarioArquivoProcessado> geraSumarioProcessadoPosXDataXStatus(SccArquivoCobilling sccArquivoCobilling, Long cdStatus) throws ServiceException, DAOException {
		List<Object[]> rows = getSumarioArquivoDAO().geraSumarioRemessaPosXDataXStatus(sccArquivoCobilling, cdStatus);
		List<ItemSumarioArquivoProcessado>  sumario = new ArrayList<ItemSumarioArquivoProcessado>();
		try {
			if (rows != null){
				for (int i=0;i<rows.size();i++){
					ItemSumarioArquivoProcessado item = new ItemSumarioArquivoProcessado();
					item.setKey(new Long[] {((BigDecimal)rows.get(i)[0]).longValue(),((BigDecimal)rows.get(i)[1]).longValue(),((BigDecimal)rows.get(i)[2]).longValue()});
					item.setDescricaoItem(rows.get(i)[0]+" "+rows.get(i)[1]+" / "+rows.get(i)[2]);
					item.setTotal(((BigDecimal)rows.get(i)[3]).longValue());
					sumario.add(item);
				}
			}
		} catch (Exception e)
			{
			throw new ServiceException("Erro ao executar service SumarioService.geraSumarioRemessaPosXDataXStatus",e);
			}
		return sumario;
	}



	

	 
	public List<SccCdrCobilling> gerarListaCDRsSumario(Long cdSeqArquivo,Long cdStatus, Long cdCiclo, Long mmCiclo, Long aaCiclo, int page,int pageSize) throws ServiceException, DAOException {
			return getSumarioArquivoDAO().gerarListaCDRsSumario(cdSeqArquivo, cdStatus, cdCiclo, mmCiclo, aaCiclo, page, pageSize);
	}



	 
	public int getCountListaCDRsSumario(Long cdSeqArquivo, Long cdStatus,Long cdCiclo, Long mmCiclo, Long aaCiclo) throws ServiceException,DAOException {
		return getSumarioArquivoDAO().getCountListaCDRsSumario(cdSeqArquivo, cdStatus, cdCiclo, mmCiclo, aaCiclo);		
	}

	
	
	
}

