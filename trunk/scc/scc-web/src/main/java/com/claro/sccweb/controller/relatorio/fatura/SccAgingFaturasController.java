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
import com.claro.cobillingweb.persistence.filtro.SccFiltroAgingFaturas;
import com.claro.cobillingweb.persistence.view.SccAgingFaturasView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.SccAgingFaturasValidator;
import com.claro.sccweb.form.SccAgingFaturasForm;
import com.claro.sccweb.service.SccFaturasService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/faturas/aging")
public class SccAgingFaturasController extends BaseOperationController<SccAgingFaturasForm> {
	
	public static final String FWD_EXCEL_AGING_FATURAS = "relatorio_aging_faturas_excel";
	public static final String FWD_VIEW_AGING_FATURAS  =  "relatorio_aging_faturas";
	
	@Autowired
	private SccFaturasService sccFaturasService;
	
	private final SccAgingFaturasValidator validator = new SccAgingFaturasValidator();
	
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, SccAgingFaturasForm form) throws Exception {
		
		ModelAndView mav = null;
		
		form.setListAgingFaturas(gerarRelatorioAgingFaturas(form.getFiltro()));
		
		if(form.getOperacao().equals("excel")){
			if(form.getListAgingFaturas() != null && form.getListAgingFaturas().size() > 0){
				mav = new ModelAndView(FWD_EXCEL_AGING_FATURAS,"filtro", form);
			}
		}else{
			
			mav = new ModelAndView(FWD_VIEW_AGING_FATURAS,"filtro", form);
			
		}
		return mav;
		
	}
	
	private List<SccAgingFaturasView> gerarRelatorioAgingFaturas(SccFiltroAgingFaturas filtro) throws DAOException, ServiceException {
		
		return getServiceManager().getSccFaturasService().gerarRelatorioAgingFaturas(filtro);
		
	}



	@Override
	protected String getViewName() {
		
		return "relatorio_aging_faturas";
	}

	@Override
	protected SccAgingFaturasForm getForm() {
		
		return new SccAgingFaturasForm();
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
	
	public void setSccFaturasService(SccFaturasService sccFaturasService) {
		this.sccFaturasService = sccFaturasService;
	}

	
	
	

}
