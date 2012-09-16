package com.claro.sccweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccItemRepasseDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoCobillingDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccRepasseDAO;
import com.claro.cobillingweb.persistence.entity.SccItemRepasse;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccRepasse;
import com.claro.cobillingweb.persistence.view.RelContabilView;
import com.claro.cobillingweb.persistence.view.mapper.LongEntity;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.RepassePosService;
import com.claro.sccweb.service.ServiceException;
import com.claro.sccweb.service.composite.RepassePosPagoComposite;
import com.claro.sccweb.service.helper.DemonstrativoPosPagoConstantes;

public class RepassePosServiceImpl extends AbstractService implements RepassePosService {

	private SccRepasseDAO repasseDAO;
	private SccItemRepasseDAO itemRepasseDAO;
	private SccOperadoraDAO operadoraDAO;
	private SccProdutoCobillingDAO produtoCobillingDAO;
	
	 
	public List<RepassePosPagoComposite> pesquisaRepassesPosPago(String cdEOTLD,String cdEOTClaro, Long cdProdutoCobilling, String cdStatus,Date dataInicial, Date dataFinal) throws DAOException,ServiceException {
		List<RepassePosPagoComposite> resultados = new ArrayList<RepassePosPagoComposite>();
		List<LongEntity> rows = getRepasseDAO().pesquisaRepassesPosPago(cdEOTLD, cdEOTClaro, cdProdutoCobilling, cdStatus, dataInicial, dataFinal);
		for (int i=0;i<rows.size();i++)
			{
			RepassePosPagoComposite composite = carregaRepassePosPago(rows.get(i).getValue());
			resultados.add(composite);
			}
		return resultados;
	}


	 
	public RepassePosPagoComposite carregaRepassePosPago(Long nuRepasse) throws DAOException, ServiceException {
		RepassePosPagoComposite composite = null;
		Map<Long, SccRepasse> indexRepasse = new HashMap<Long, SccRepasse>();
		String cdEOTClaro = null;
		String cdEOTLD = null;
		Long cdProdutoCobilling = null;
		Date dtInicialRepasse = null;
		Long cdPeriodicidade = null;
		try {
			List<SccRepasse> repasses = getRepasseDAO().carregaItensRepasse(nuRepasse);			
			 if ((repasses != null) && (!repasses.isEmpty()))
					 {
				 	 composite = new RepassePosPagoComposite();
					 for (int i=0;i<repasses.size();i++)
						{
						SccRepasse repasse = repasses.get(i);
						if (i == 0) /*Como os itens de repasse possuem sempre o mesmo status e tipo de contrato dentro do mesmo repasse , pego o primeiro valor.*/
							{
							composite.setStatusRepasse(repasses.get(i).getCdStatusRepasse());
							composite.setNuRepasse(repasses.get(i).getNuRepasse());							
							composite.setTipoContrato(repasses.get(i).getSccTipoContrato());
							cdEOTClaro = repasses.get(i).getCdEotClaro();
							cdEOTLD = repasses.get(i).getCdEotLd();
							cdProdutoCobilling = repasses.get(i).getProduto().getCdProdutoCobilling();
							dtInicialRepasse = repasses.get(i).getDtInicialRepasse();							
							}							
						SccItemRepasse itemRepasse = getItemRepasseDAO().getByPk(repasse.getCdItemRepasse(), SccItemRepasse.class);
						composite.getItems().put(itemRepasse, repasse);
						indexRepasse.put(itemRepasse.getCdItemRepasse(), repasse);
						}
					SccOperadora operadoraClaro = getOperadoraDAO().getByPk(cdEOTClaro, SccOperadora.class);
					SccOperadora operadoraLD = getOperadoraDAO().getByPk(cdEOTLD, SccOperadora.class);
					composite.setOperadoraClaro(operadoraClaro);
					composite.setOperadoraLD(operadoraLD);			
					SccProdutoCobilling produtoCobilling = getProdutoCobillingDAO().getByPk(cdProdutoCobilling, SccProdutoCobilling.class);
					composite.setProdutoCobilling(produtoCobilling);
					composite.setDtInicialRepasse(dtInicialRepasse);
					composite.setValorBrutoRepasse(calculaValorBrutoRepasse(indexRepasse));					
					 }
			return composite;
		} catch (DAOException daoException){
			error("DAOException", daoException);
			throw daoException;
		}catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
	}


	public SccRepasseDAO getRepasseDAO() {
		return repasseDAO;
	}

	public void setRepasseDAO(SccRepasseDAO repasseDAO) {
		this.repasseDAO = repasseDAO;
	}







	public SccItemRepasseDAO getItemRepasseDAO() {
		return itemRepasseDAO;
	}

	public void setItemRepasseDAO(SccItemRepasseDAO itemRepasseDAO) {
		this.itemRepasseDAO = itemRepasseDAO;
	}





	public SccOperadoraDAO getOperadoraDAO() {
		return operadoraDAO;
	}



	public void setOperadoraDAO(SccOperadoraDAO operadoraDAO) {
		this.operadoraDAO = operadoraDAO;
	}





	public SccProdutoCobillingDAO getProdutoCobillingDAO() {
		return produtoCobillingDAO;
	}





	public void setProdutoCobillingDAO(SccProdutoCobillingDAO produtoCobillingDAO) {
		this.produtoCobillingDAO = produtoCobillingDAO;
	}

	/**
	 * Calcula o valor bruto do repasse.
	 */
	public Double calculaValorBrutoRepasse(Map<Long, SccRepasse> map) throws ServiceException
	{	
		try {
		Double valor1,valor2;
		if (map.containsKey(DemonstrativoPosPagoConstantes.TOTAL_DO_VALOR_ARRECADADO))		
			{
			valor1 = map.get(DemonstrativoPosPagoConstantes.TOTAL_DO_VALOR_ARRECADADO).getVlBrutoItemRepasse();
			}			
		else
			{
			valor1 = 0.0;
			}			
		
		if (map.containsKey(DemonstrativoPosPagoConstantes.TOTAL_DAS_DEDUCOES))
			{
			valor2	= map.get(DemonstrativoPosPagoConstantes.TOTAL_DAS_DEDUCOES).getVlBrutoItemRepasse();
			}					
		else
			{
			valor2 = 0.0;
			}			
		
		return valor1-valor2+calcularTotalAcertosRepasse(map)+calcularPenalidadesRepasse(map);
		} catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
	}
	
	
	private Double calcularTotalAcertosRepasse(Map<Long, SccRepasse> map)
	{
		return somaValoresRepasse(map, DemonstrativoPosPagoConstantes.ACERTO_CONCILIACAO_CONTRA_CLARO,DemonstrativoPosPagoConstantes.ACERTO_CONCILIACAO_CONTRA_OPERADORA_LD);		
	}
	
	private Double calcularPenalidadesRepasse(Map<Long, SccRepasse> map)
	{
		return somaValoresRepasse(map, DemonstrativoPosPagoConstantes.PENALIDADE_REJEICOES_INDEVIDAS_CONTRA_CLARO,DemonstrativoPosPagoConstantes.PENALIDADE_REJEICOES_DEVIDAS_CONTRA_LD,
				DemonstrativoPosPagoConstantes.PENALIDADE_CHAMADA_PERDIDA_CONTRA_CLARO,DemonstrativoPosPagoConstantes.PENALIDADE_SLA_CONTRA_CLARO,
				DemonstrativoPosPagoConstantes.MULTAS_JUROS_ATRASO_REPASSE_CONTRA_CLARO,DemonstrativoPosPagoConstantes.MULTAS_JUROS_ATRASO_PAGAMENTO_CONTRA_LD);		
	}
	
	private Double somaValoresRepasse(Map<Long, SccRepasse> map,Long...codigos)
	{
		Double valorFinal = new Double(0.0);
		for (Long codigo : codigos) {
			if (map.containsKey(codigo))
				{
				valorFinal = valorFinal+map.get(codigo).getVlBrutoRepasse();
				}							
		}
		return valorFinal;
	}


	 
	public List<RelContabilView> geraRelatorioContabil(String cdEOTLD,String cdEOTClaro, String cdMotivoRejeicao, Date dataInicial,Date dataFinal, boolean holding) throws DAOException {		
		return getRepasseDAO().geraRelatorioContabil(cdEOTLD, cdEOTClaro, cdMotivoRejeicao, dataInicial, dataFinal, holding);
	}
}
