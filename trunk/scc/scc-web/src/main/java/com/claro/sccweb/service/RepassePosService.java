package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.RelContabilView;
import com.claro.sccweb.service.composite.RepassePosPagoComposite;

/**
 * Esse serviço irá substituir {@link RelatorioRepasseService} após refactoring previsto para 11/05/2012. 
 *
 */
public interface RepassePosService {

	/**
	 * Pesquisa repasses (já sumarizados) de acordo com o filtro informado.
	 * @param cdEOTLD
	 * @param cdEOTClaro
	 * @param cdProdutoCobilling
	 * @param cdStatus
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws DAOException
	 */
	public List<RepassePosPagoComposite>pesquisaRepassesPosPago(String cdEOTLD,String cdEOTClaro,Long cdProdutoCobilling,String cdStatus,Date dataInicial,Date dataFinal) throws DAOException,ServiceException;
	
	/**
	 * Gera o sumário do repasse baseado em nu_repasse.
	 * @param nuRepasse
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public RepassePosPagoComposite carregaRepassePosPago(Long nuRepasse) throws DAOException,ServiceException;
	
	
	public List<RelContabilView> geraRelatorioContabil(String cdEOTLD,String cdEOTClaro,String cdMotivoRejeicao,Date dataInicial,Date dataFinal,boolean holding) throws DAOException;
	
	
}
