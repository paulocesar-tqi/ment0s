package com.claro.sccweb.controller.relatorio.pos;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccAlocadasMobileView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.RelatorioAlocadasMobileValidator;
import com.claro.sccweb.form.RelatorioAlocadasMobileForm;
import com.claro.sccweb.service.SccAlocadasMobileService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/alocadasMobile")
public class RelatorioAlocadasMobileController extends
		BaseOperationController<RelatorioAlocadasMobileForm> {
	
	@Autowired
	private SccAlocadasMobileService sccAlocadasMobileService;
	
	public static final String FWD_EXCEL_ALOCADAS_MOBILE = "relatorio_alocadas_mobile_filtro_excel";
	private static final String FWD_VIEW_ALOCADAS_MOBILE = "relatorio_alocadas_mobile_filtro";
	private static final String OPERACAO_EXCEL =	"excel";
	
	private final RelatorioAlocadasMobileValidator validator = new RelatorioAlocadasMobileValidator();
	
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisarByFiltro(HttpServletRequest request, HttpServletResponse response, RelatorioAlocadasMobileForm form) throws Exception {
		
		ModelAndView mav = null;
		
		form.setLstAlocadasMobile(gerarRelatorioAlocadasMobile(form));
		
		if(form.getOperacao().equals(OPERACAO_EXCEL)){
			if(form.getLstAlocadasMobile() != null && form.getLstAlocadasMobile().size() > 0){
				mav = new ModelAndView(FWD_EXCEL_ALOCADAS_MOBILE, "filtro", form);
			}
			
		}else{
			mav = new ModelAndView(FWD_VIEW_ALOCADAS_MOBILE, "filtro", form);
		}
		
		return mav;
		
	}

	private List<SccAlocadasMobileView> gerarRelatorioAlocadasMobile(RelatorioAlocadasMobileForm form) throws DAOException, ServiceException {
		return sccAlocadasMobileService.gerarRelatorioAlocadasMobile(form.getDtInicial(), form.getDtFinal(), form.getNoArquivoGerado());
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_alocadas_mobile_filtro";
	}

	@Override
	protected RelatorioAlocadasMobileForm getForm() {
		
		return new RelatorioAlocadasMobileForm();
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

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}
	
	public SccAlocadasMobileService getSccAlocadasMobileService() {
		return sccAlocadasMobileService;
	}

	public void setSccAlocadasMobileService(SccAlocadasMobileService sccAlocadasMobileService) {
		this.sccAlocadasMobileService = sccAlocadasMobileService;
	}
}
