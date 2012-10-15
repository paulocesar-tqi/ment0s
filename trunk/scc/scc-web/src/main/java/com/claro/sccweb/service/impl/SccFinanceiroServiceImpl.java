package com.claro.sccweb.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccFinanceiroDAO;
import com.claro.cobillingweb.persistence.filtro.SccFinanceiroFiltro;
import com.claro.cobillingweb.persistence.view.SccFinanceiroView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccFinanceiroService;
import com.claro.sccweb.service.ServiceException;
import com.claro.sccweb.vo.RelatorioFinanceiroVO;

@Service
public class SccFinanceiroServiceImpl extends AbstractService implements
		SccFinanceiroService {

	@Autowired
	private SccFinanceiroDAO sccFinanceiroDAO;
	
	public static final int SIZE = 13;
	

	@Override
	public Collection<SccFinanceiroView> findByFiltro(SccFinanceiroFiltro filtro) throws ServiceException, DAOException {
		
		Collection<SccFinanceiroView> list = new ArrayList<SccFinanceiroView>();
		
		Calendar calendar  = Calendar.getInstance();
		String strData = montarArrayString(calendar, filtro);
		filtro.setData(strData);
		
		list = this.sccFinanceiroDAO.findByFiltro(filtro);
		
//		Collections.reverse((List<SccFinanceiroView>) list);
		
		return list;
	}
	
	public Collection<Collection<SccFinanceiroView>> findByFiltroPorData(SccFinanceiroFiltro filtro) throws ServiceException, DAOException {
		Collection<Collection<SccFinanceiroView>> listAll = new ArrayList<Collection<SccFinanceiroView>>();
		
		Calendar calendar = Calendar.getInstance();
		
		for (int i = 0; i < SIZE; i++) {
			filtro.setData(alterarData(calendar, filtro));
			Collection<SccFinanceiroView> list = this.sccFinanceiroDAO.findByFiltro(filtro);
			listAll.add(list);
			
		}
		
		
		return listAll;
		
	}
	
	@SuppressWarnings("unused")
	private Collection<RelatorioFinanceiroVO> montarRelatorio(Collection<SccFinanceiroView> list, int indice){
	
		Collection<RelatorioFinanceiroVO> itemsRel = null;
		if(list != null && list.size() > 0){
			for (SccFinanceiroView sccFinanceiroView : list) {
				RelatorioFinanceiroVO relatorio = new RelatorioFinanceiroVO();
				relatorio.setDescricao(sccFinanceiroView.getDescItem());
				sccFinanceiroView.getDescItem();
				
				
			}
		}
			
		
		return null;
	}
	
	//TODO APOS CRIAREM A NOVA COLUNA QUE FOI SOLICITADA VOLTAR PARA ESTE CASO DE USO PARA FINALIZAR
	
	@SuppressWarnings({ "unused", "rawtypes" })
	private void testeReflection() throws ClassNotFoundException, SecurityException, NoSuchFieldException{
		Class cls = Class.forName( SccFinanceiroView.class.getName().toString() );
		Field field = cls.getField("");   
		field.getType();
		

	}
	
	private String montarArrayString(Calendar calendar, SccFinanceiroFiltro filtro){
		String strData = "";
		for (int i = 0; i < SIZE; i++) {
			String str = new String(alterarData(calendar, filtro)) ;
			
			if(StringUtils.isEmpty(strData)){
				strData = "'"+ str +"'" ;
			}else{
				strData = strData +"," + "'"+str+"'";
			}
		}
		
		return strData;
	}
	
	private String converterData(SccFinanceiroFiltro filtro){
		
		if(filtro.getMes().toString().length() == 1){
			return  filtro.getAno().toString() + "0"+ filtro.getMes().toString();
		}
		
		return filtro.getAno().toString() + "0"+ filtro.getMes().toString();
		
	}
	private String alterarData(Calendar calendar, SccFinanceiroFiltro filtro){
		
		filtro.setMes(calendar.get(Calendar.MONTH) + 1);
		filtro.setAno(calendar.get(Calendar.YEAR));
		calendar.add(Calendar.MONTH, - 1);
		
		return converterData(filtro);
		
	}
	
	public void setSccFinanceiroDAO(SccFinanceiroDAO sccFinanceiroDAO) {
		this.sccFinanceiroDAO = sccFinanceiroDAO;
	}
	
	
	public static void main (String[] args){
		Calendar lCalendar  = Calendar.getInstance();
		
		Integer mes = 8;
		Integer ano = 2012;
		ArrayList<String> teste = new ArrayList<String>();
		String[] array = new String[13];
		String str = null;
		for(int i=0;i < 13; i++){
	   		mes = lCalendar.get(Calendar.MONTH) + 1;
    		ano =lCalendar.get(Calendar.YEAR);
    		lCalendar.add(Calendar.MONTH, - 1);
    		if(mes.toString().length() > 1){
    			teste.add(ano.toString()+mes.toString());
    		}else{
    			teste.add(ano.toString()+ "0"+ mes.toString());
    		}
    		//Collections.reverse(teste);
    		//System.out.println("mes = "+ mes + " ano = "+ano);
 			str = str + ano.toString()+mes.toString();
 			//System.out.println(str);
		}
		String valor = "";
		for (int i = 0; i < teste.size(); i++) {
			if(StringUtils.isEmpty(valor)){
				valor = "'"+teste.get(i)+"'" ;
				System.out.println(valor +"indice" + i);
				array[i] = valor;
			}else{
				valor = valor.trim() +"," + "'"+teste.get(i)+"'";
				System.out.println(valor +"indice" + i);
				array[i] = valor;
			}
			
			
			
		}
		
		//System.out.println(array.toString());
		//System.out.println(valor);
		
		
		
	}

}
