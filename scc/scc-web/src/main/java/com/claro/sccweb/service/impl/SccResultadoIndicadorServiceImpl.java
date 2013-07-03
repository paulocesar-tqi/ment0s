package com.claro.sccweb.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccResultadoIndicadoresDAO;
import com.claro.cobillingweb.persistence.entity.SccAgingIndicador;
import com.claro.cobillingweb.persistence.service.ServiceException;
import com.claro.cobillingweb.persistence.view.SccResultadoIndicadorView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccResultadoIndicadorService;

@Service
public class SccResultadoIndicadorServiceImpl extends AbstractService implements
		SccResultadoIndicadorService {
	
	@Autowired
	private SccResultadoIndicadoresDAO sccResultadoIndicadorDAO;
	
	
	
	
	@Override
	public List<SccAgingIndicador> gerarListAging(String cdIndicador) throws ServiceException, DAOException {
		
		return this.sccResultadoIndicadorDAO.gerarListaAging(cdIndicador);
	}
	
	@Override
	public List<SccResultadoIndicadorView> gerarList(String cdEotLd, String cdEotClaro, Date dataInicial, Date dataFinal, Long vlMinimo, Long vlMaximo, String cdIndicador) throws DAOException{
		
		return this.sccResultadoIndicadorDAO.gerarList(cdEotLd, cdEotClaro, dataInicial, dataFinal, vlMinimo, vlMaximo, cdIndicador);
	}
	
	@Override
	public List<SccResultadoIndicadorView> gerarListaFinal(String cdEotLd, String cdEotClaro, Date dataInicial, Date dataFinal) throws DAOException{
	
		return this.sccResultadoIndicadorDAO.gerarListaFinal(cdEotLd, cdEotClaro, dataInicial, dataFinal);
		
	}
	
	@Override
	public List<SccResultadoIndicadorView> gerarRelatorioDiario(String cdEotLd, String cdEotClaro, String cdIndicador, boolean tipo, Date dataInicial, Date dataFinal) throws DAOException{
		
		List<SccResultadoIndicadorView> listResultadoIndicador = new ArrayList<SccResultadoIndicadorView>();
		List<SccAgingIndicador> listAging = this.sccResultadoIndicadorDAO.gerarListaAging(cdIndicador);
		
		for (SccAgingIndicador entity : listAging) {
			SccResultadoIndicadorView view = this.sccResultadoIndicadorDAO.gerarResultadoIndicador(cdEotLd, cdEotClaro, cdIndicador, entity.getVlMinimoAging().longValue(), entity.getVlMaximoAging().longValue(), dataInicial, dataFinal);
			view.setAgingDias(entity.getVlMinimoAging().toString() + "   -   " + entity.getVlMaximoAging().toString());
			view.setDtIniFiltro(DateToStr(dataInicial));
			view.setDtFimFiltro(DateToStr(dataFinal));
			view.setCdEotLd(cdEotLd);
			view.setCdEotClaro(cdEotClaro);
			listResultadoIndicador.add(view);
		}
		
		return listResultadoIndicador;
	}
	
	private String DateToStr(Date data){
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		return fmt.format(data);
	}
	
	@SuppressWarnings("unused")
	private void gerarLinha(SccAgingIndicador entity){
		
		SccResultadoIndicadorView view = new SccResultadoIndicadorView();
		view.setAgingDias(entity.getVlMinimoAging().toString() + "     "+ entity.getVlMinimoAging());
		
	}
	
	@Override
	public List<SccResultadoIndicadorView> pesquisaByFiltro(String cdEotLd, String cdEotClaro, String cdIndicador, boolean tipo, Date dataInicial, Date dataFinal) throws DAOException{
		
		return this.sccResultadoIndicadorDAO.pesquisaByFiltro(cdEotLd, cdEotClaro, cdIndicador, tipo, dataInicial, dataFinal);
	}
	
	public List<SccResultadoIndicadorView> montarListaParaView(String cdEotLd, String cdEotClaro, Date dataInicial, Date dataFinal, Long vlMinimo, Long vlMaximo, String cdIndicador) throws ServiceException, DAOException{
		
		List<SccResultadoIndicadorView> lstResultado = new ArrayList<SccResultadoIndicadorView>();
		
		for (SccAgingIndicador sccAgingIndicador : gerarListAging(cdIndicador)) {
			SccResultadoIndicadorView entity = (SccResultadoIndicadorView) gerarList(cdEotLd, cdEotClaro, dataInicial, dataFinal, sccAgingIndicador.getVlMinimoAging().longValue(), sccAgingIndicador.getVlMaximoAging().longValue(), sccAgingIndicador.getId().getCdIndicador()).get(0);
			lstResultado.add(entity);
		}
		return lstResultado;
	}

	public void setSccResultadoIndicadorDAO(
			SccResultadoIndicadoresDAO sccResultadoIndicadorDAO) {
		this.sccResultadoIndicadorDAO = sccResultadoIndicadorDAO;
	}
	
	
	

}
