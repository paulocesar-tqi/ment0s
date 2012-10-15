package com.claro.sccweb.controller.relatorio.pos;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.SccContingenciaFiscalView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.RelatorioContingenciaFiscalValidator;
import com.claro.sccweb.decorator.rownum.entity.SccContingenciaFiscalViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioContingenciaFiscalForm;
import com.claro.sccweb.service.SccContingenciaFiscalService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/contingenciaFiscal")
public class RelatorioContingenciaFiscalController extends
		BaseOperationController<RelatorioContingenciaFiscalForm> {
	
	@Autowired
	private SccContingenciaFiscalService sccContingenciaFiscalService;
	
	private final RelatorioContingenciaFiscalValidator validator = new RelatorioContingenciaFiscalValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioContingenciaFiscalForm formContingenciaFiscal = (RelatorioContingenciaFiscalForm)form;
		List<SccContingenciaFiscalView> rows = gerarRelatorioContingenciaFiscal(formContingenciaFiscal);
		
		List<SccContingenciaFiscalViewDecorator> decoratorList = new ArrayList<SccContingenciaFiscalViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccContingenciaFiscalViewDecorator decorator = new SccContingenciaFiscalViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;

	}
	
	private List<SccContingenciaFiscalView> gerarRelatorioContingenciaFiscal(RelatorioContingenciaFiscalForm form) throws DAOException, ServiceException {
		return sccContingenciaFiscalService.gerarRelatorioContingenciaFiscal(form.getMesRelatorio(), form.getAnoRelatorio(), form.getCdCSP());
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

	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() 
	{
		return _populaComboMeses();
	}
	
	@Override
	protected String getViewName() {
		
		return "relatorio_contingencia_fiscal_filtro";
	}

	@Override
	protected RelatorioContingenciaFiscalForm getForm() {
		
		return new RelatorioContingenciaFiscalForm();
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
	
	

	public SccContingenciaFiscalService getSccContingenciaFiscalService() {
		return sccContingenciaFiscalService;
	}

	public void setSccContingenciaFiscalService(
			SccContingenciaFiscalService sccContingenciaFiscalService) {
		this.sccContingenciaFiscalService = sccContingenciaFiscalService;
	}

}
