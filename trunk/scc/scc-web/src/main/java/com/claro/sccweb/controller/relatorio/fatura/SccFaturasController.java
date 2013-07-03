package com.claro.sccweb.controller.relatorio.fatura;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.filtro.SccFiltroFaturas;
import com.claro.cobillingweb.persistence.view.SccFaturaView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SccFaturasValidator;
import com.claro.sccweb.form.SccFaturasForm;
import com.claro.sccweb.service.SccFaturasService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/faturas/controle")
public class SccFaturasController extends BaseOperationController<SccFaturasForm> {
	
	public static final String FWD_VIEW_FATURAS = "relatorio_controle_faturas";
	public static final String FWD_EXCEL_FATURAS ="relatorio_controle_faturas_excel";
	
	@Autowired
	private SccFaturasService sccFaturasService;
	
	private final SccFaturasValidator validator = new SccFaturasValidator(); 
	
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, SccFaturasForm form) throws Exception {
		
		ModelAndView mav = null;
		form.setListFaturas(gerarRelatorioControleFaturas(form.getFiltro()));
		
		if(form.getOperacao().equals("excel")){
			if(form.getListFaturas() != null && form.getListFaturas().size() > 0){
				mav = new ModelAndView(FWD_EXCEL_FATURAS,"filtro", form);
			}
		}else{
			
			mav = new ModelAndView(FWD_VIEW_FATURAS,"filtro", form);
			
		}
		return mav;
		
	}
	
	private List<SccFaturaView> gerarRelatorioControleFaturas(SccFiltroFaturas filtro) throws DAOException, ServiceException {
		
		return sccFaturasService.gerarRelatorioFaturas(filtro);
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_controle_faturas";
	}

	@Override
	protected SccFaturasForm getForm() {
		
		return new SccFaturasForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}
	
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());
		return comboList;
	}

	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}

	
	@RequestMapping(value="/tab1" , method = RequestMethod.GET)
	public ModelAndView tab1(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView(getViewName());
			Object form = getMyFormFromCache(getClass());
			if (form != null)
				mav.addObject(FORM_NAME, form);
			else
				mav.addObject(FORM_NAME, getForm());
	    	return mav;  
	}
	
	@ModelAttribute("tipoRelatorio")
	public List<BasicStringItem> popularTipoRelatorio() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("S", "Sintético"));
		comboList.add(new BasicStringItem("F", "Por Fatura"));
		return comboList;
	}

	
	@ModelAttribute("tipoData")
	public List<BasicIntegerItem> popularTipoData() throws Exception {
		List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
        comboList.add(new BasicIntegerItem(1L, "Data de Emissão NF"));
        comboList.add(new BasicIntegerItem( 2L, "Data de Processamento"));
        comboList.add(new BasicIntegerItem(3L, "Data de Vencimento"));
		return comboList;
	}
	
	@ModelAttribute("statusFatura")
	public List<BasicStringItem> popularStatusFatura() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos"));
		comboList.add(new BasicStringItem("O", "Open"));
		comboList.add(new BasicStringItem("C", "Close"));
		return comboList;
	}

	
	public void setSccFaturasService(SccFaturasService sccFaturasService) {
		this.sccFaturasService = sccFaturasService;
	}
	
	

}
