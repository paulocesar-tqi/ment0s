package com.claro.sccweb.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccParamProcessoDAO;
import com.claro.cobillingweb.persistence.entity.SccParamProcesso;
import com.claro.cobillingweb.persistence.entity.SccParamProcessoPK;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.ServiceException;
import com.claro.sccweb.service.VolumeSuspeitoService;

public class VolumeSuspeitoServiceImpl extends AbstractService implements VolumeSuspeitoService {

	private SccParamProcessoDAO paramProcessoDAO;

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
	
	
	
	public SccParamProcessoDAO getParamProcessoDAO() {
		return paramProcessoDAO;
	}

	public void setParamProcessoDAO(SccParamProcessoDAO paramProcessoDAO) {
		this.paramProcessoDAO = paramProcessoDAO;
	}

	 
	public void criaSolicitacaoArquivo(Long minutos, Double valorBruto,Date dataInicial, Date dataFinal) throws DAOException,ServiceException 
	{
		DecimalFormat nm = new DecimalFormat();		
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(dateFormat.format(dataInicial));
			sb.append(dateFormat.format(dataFinal));
			nm.applyPattern("0000000");
			sb.append(nm.format(minutos));
			nm.applyPattern("00000.00");
			sb.append(nm.format(valorBruto));
			SccParamProcessoPK pk = new SccParamProcessoPK();
			SccParamProcesso paramProcesso = new SccParamProcesso();			
			pk.setCdProcesso("MAVS_PRE");
			pk.setNmParametro(sb.toString());
			paramProcesso.setId(pk);
			paramProcesso.setCdTipoParametro("ALFA");
			paramProcesso.setTxValorParametro("TOLOAD");
			paramProcesso.setDtCriacao(Calendar.getInstance().getTime());
			paramProcesso.setDtAtualizacao(Calendar.getInstance().getTime());
			paramProcesso.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
			getParamProcessoDAO().create(paramProcesso);
		} catch (DAOException daoEx)
			{
			throw daoEx;
			}
		catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
		
	}
	
	
	
	
	
}
