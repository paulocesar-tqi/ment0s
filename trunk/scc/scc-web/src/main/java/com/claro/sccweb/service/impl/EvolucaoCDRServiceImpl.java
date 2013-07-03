package com.claro.sccweb.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.claro.cobillingweb.persistence.constants.StatusCDRConstants;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivoSumarizadoDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.EvolucaoCDRService;
import com.claro.sccweb.service.ServiceException;
import com.claro.sccweb.service.data.GrupoCDR;
import com.claro.sccweb.service.data.GrupoStatusConstants;
import com.claro.sccweb.service.data.PeriodoCDR;

public class EvolucaoCDRServiceImpl extends AbstractService implements EvolucaoCDRService {

	private SccArquivoSumarizadoDAO arquivoSumarizadoDAO;
	
	
	private PeriodoCDR gerarPeriodoCDR(SccArquivoSumarizado sccArquivoSumarizado) throws Exception{
		PeriodoCDR periodoCDR = new PeriodoCDR();
		periodoCDR.setCdrs(iniciaMapStatus());
		periodoCDR.setMesAno(sccArquivoSumarizado.getMesAno());		
		periodoCDR.setAno(sccArquivoSumarizado.getAaCiclo());
		periodoCDR.setMes(sccArquivoSumarizado.getMmCiclo());

		return periodoCDR;
		
	}
	public List<PeriodoCDR> geraEvolucaoCDRs(String grpCdr, String cdEOTClaro, String cdEOTLD, Long produto, Date dataInicial, Date dataFinal,boolean holding) throws DAOException, ServiceException {
		List<PeriodoCDR> resultados = new ArrayList<PeriodoCDR>();
		Map<String, PeriodoCDR> tempMap = new HashMap<String, PeriodoCDR>();
		try {
			
			List<SccArquivoSumarizado> sumarizado = getArquivoSumarizadoDAO().geraEvolucaoCDRs(grpCdr, cdEOTClaro, cdEOTLD, dataInicial, dataFinal, produto, holding);
			//PeriodoCDR periodoCDR = null;
			for (SccArquivoSumarizado sccArquivoSumarizado : sumarizado) {
				String key = sccArquivoSumarizado.getMesAno();
				sccArquivoSumarizado.setMmCiclo(new Long(sccArquivoSumarizado.getMesAno().substring(0,2)));
				sccArquivoSumarizado.setAaCiclo(new Long(sccArquivoSumarizado.getMesAno().substring(3,7)));

				if (!tempMap.containsKey(key)){
					tempMap.put(key, gerarPeriodoCDR(sccArquivoSumarizado));
				}
				tempMap.get(key).addSumario(sccArquivoSumarizado);
				
			}	
			resultados.addAll(tempMap.values());
			//gerarPercentual(resultados);
			Collections.sort(resultados);
			return resultados;
		} catch (DAOException daoEx){
			throw daoEx;
		}catch (Exception e){
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	private void gerarPercentual(List<PeriodoCDR> resultados){
		
		Long aceito = resultados.get(0).getAceitos();
		for (PeriodoCDR periodoCDR : resultados) {
			periodoCDR.setPercentual((periodoCDR.getTotalCDRs().doubleValue() / aceito.doubleValue()) * 100);
		}
	}

	
/*	public List<PeriodoCDR> geraEvolucaoCDRs(String cdEOTClaro, String cdEOTLD, Long produto, Date dataInicial, Date dataFinal,boolean holding) throws DAOException, ServiceException {
		List<PeriodoCDR> resultados = new ArrayList<PeriodoCDR>();
		Map<String, PeriodoCDR> tempMap = new HashMap<String, PeriodoCDR>();
		try {
			
			List<SccArquivoSumarizado> sumarizado = getArquivoSumarizadoDAO().geraEvolucaoCDRs(cdEOTClaro, cdEOTLD, dataInicial, dataFinal, produto, holding);
			PeriodoCDR periodoCDR = null;
			for (SccArquivoSumarizado sccArquivoSumarizado : sumarizado) {
				String key = sccArquivoSumarizado.getMesAno();//sccArquivoSumarizado.getMmCiclo()+"/"+sccArquivoSumarizado.getAaCiclo();
				if (!key.equals("0/0"))
					{
					if (!tempMap.containsKey(key))
						{
						periodoCDR = new PeriodoCDR();
						periodoCDR.setCdrs(iniciaMapStatus());
						periodoCDR.setMesAno(key);		
						periodoCDR.setAno(sccArquivoSumarizado.getAaCiclo());
						periodoCDR.setMes(sccArquivoSumarizado.getMmCiclo());
						tempMap.put(key, periodoCDR);
						}
					tempMap.get(key).addSumario(sccArquivoSumarizado);
					}
				}
			resultados.addAll(tempMap.values());
			Collections.sort(resultados);
			return resultados;
		} catch (DAOException daoEx)
			{
			throw daoEx;
			}
		catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
	}

*/	public SccArquivoSumarizadoDAO getArquivoSumarizadoDAO() {
		return arquivoSumarizadoDAO;
	}

	public void setArquivoSumarizadoDAO(SccArquivoSumarizadoDAO arquivoSumarizadoDAO) {
		this.arquivoSumarizadoDAO = arquivoSumarizadoDAO;
	}

	private List<GrupoCDR> iniciaMapStatus() throws Exception
	{
		List<GrupoCDR> list = new ArrayList<GrupoCDR>();
				list.add(new GrupoCDR("Encaminhado",GrupoStatusConstants.GRUPO_CDR_ENCAMINHADO,StatusCDRConstants.CDRSTATUS_ENCAMINHADO_ESB,StatusCDRConstants.CDRSTATUS_ENCAMINHADO_MOB));
				list.add(new GrupoCDR("Rejeitado",GrupoStatusConstants.GRUPO_CDR_REJEITADO,StatusCDRConstants.CDRSTATUS_REJEITADO_C1,StatusCDRConstants.CDRSTATUS_REJEITADO_C2_ESB,StatusCDRConstants.CDRSTATUS_REJEITADO_C2_MOB));
				list.add(new GrupoCDR("Excluído",GrupoStatusConstants.GRUPO_CDR_EXCLUIDO,StatusCDRConstants.CDRSTATUS_EXCLUIDO_X1,StatusCDRConstants.CDRSTATUS_EXCLUIDO_X2_ESB,StatusCDRConstants.CDRSTATUS_EXCLUIDO_X2_MOB));
				list.add(new GrupoCDR("Perdido",GrupoStatusConstants.GRUPO_CDR_PERDIDO,StatusCDRConstants.CDRSTATUS_PERDIDO_PPC,StatusCDRConstants.CDRSTATUS_PERDIDO_ESB,StatusCDRConstants.CDRSTATUS_PERDIDO_MOB));
				list.add(new GrupoCDR("Contestado",GrupoStatusConstants.GRUPO_CDR_CONTESTADO,StatusCDRConstants.CDRSTATUS_CONTESTADO_ESB,StatusCDRConstants.CDRSTATUS_CONTESTADO_MOB));
				list.add(new GrupoCDR("Retarifado",GrupoStatusConstants.GRUPO_RETARIFADO,StatusCDRConstants.CDRSTATUS_RETARIF));
				list.add(new GrupoCDR("Faturado",GrupoStatusConstants.GRUPO_CDR_FATURADO,StatusCDRConstants.CDRSTATUS_FATURADO_ESB,StatusCDRConstants.CDRSTATUS_FATURADO_MOB));
				list.add(new GrupoCDR("Alocado",GrupoStatusConstants.GRUPO_CDR_ALOCADO,StatusCDRConstants.CDRSTATUS_ALOCADO_ESB,StatusCDRConstants.CDRSTATUS_ALOCADO_MOB));
				list.add(new GrupoCDR("A Reciclar",GrupoStatusConstants.GRUPO_CDR_A_RECICLAR,StatusCDRConstants.CDRSTATUS_A_RECICLAR_ESB,StatusCDRConstants.CDRSTATUS_A_RECICLAR_MOB));
				list.add(new GrupoCDR("A Recuperar",GrupoStatusConstants.GRUPO_CDR_A_RECUPERAR,StatusCDRConstants.CDRSTATUS_A_RECUPERAR_ESB,StatusCDRConstants.CDRSTATUS_A_RECUPERAR_MOB));
				list.add(new GrupoCDR("Em Parcelamento",GrupoStatusConstants.GRUPO_EM_PARCELAMENTO,StatusCDRConstants.CDRSTATUS_EM_PARCELAMENTO));
				list.add(new GrupoCDR("Contestado Arrecadado",StatusCDRConstants.CDRSTATUS_CONTESTADO_MOB_ARRECADADA,StatusCDRConstants.CDRSTATUS_CONTESTADO_MOB_ARRECADADA));
				list.add(new GrupoCDR("Contestado Repassado",StatusCDRConstants.CDRSTATUS_CONTESTADO_MOB_REPASSADA,StatusCDRConstants.CDRSTATUS_CONTESTADO_MOB_REPASSADA));
				list.add(new GrupoCDR("Excluído Contestado",StatusCDRConstants.CDRSTATUS_EXCLUIDO_MOB_CONTESTADO,StatusCDRConstants.CDRSTATUS_EXCLUIDO_MOB_CONTESTADO));
				list.add(new GrupoCDR("Arrecadado",StatusCDRConstants.CDRSTATUS_ARRECADADA,StatusCDRConstants.CDRSTATUS_ARRECADADA));
				list.add(new GrupoCDR("Indadimplente",StatusCDRConstants.CDRSTATUS_INADIMPLENTE,StatusCDRConstants.CDRSTATUS_INADIMPLENTE));
				list.add(new GrupoCDR("Reversão de Pagamento",StatusCDRConstants.CDRSTATUS_REVERSAO_PGTO,StatusCDRConstants.CDRSTATUS_REVERSAO_PGTO));
				list.add(new GrupoCDR("Repassado",StatusCDRConstants.CDRSTATUS_REPASSADA,StatusCDRConstants.CDRSTATUS_REPASSADA));
				list.add(new GrupoCDR("Reversão",StatusCDRConstants.CDRSTATUS_REVERSAO,StatusCDRConstants.CDRSTATUS_REVERSAO));
				list.add(new GrupoCDR("Parcelado",StatusCDRConstants.CDRSTATUS_PARCELADA,StatusCDRConstants.CDRSTATUS_PARCELADA));
				list.add(new GrupoCDR("Alteração de Vencimento",StatusCDRConstants.CDRSTATUS_ALTERACAO_VCTO,StatusCDRConstants.CDRSTATUS_ALTERACAO_VCTO));				
				return list;
	}


}
