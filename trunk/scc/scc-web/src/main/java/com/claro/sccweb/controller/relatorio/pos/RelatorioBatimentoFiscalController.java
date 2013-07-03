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
import com.claro.cobillingweb.persistence.view.SccBatimentoFiscalView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.RelatorioBatimentoFiscalValidator;
import com.claro.sccweb.decorator.rownum.entity.SccBatimentoFiscalViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioBatimentoFiscalForm;
import com.claro.sccweb.service.SccBatimentoFiscalService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/batimentoFiscal")
public class RelatorioBatimentoFiscalController extends
		BaseOperationController<RelatorioBatimentoFiscalForm> {
	
	public static final String FWD_VIEW_EXCEL ="relatorio_batimento_fiscal_filtro_excel";
	
	@Autowired
	private SccBatimentoFiscalService sccBatimentoFiscalService;
	
	private final RelatorioBatimentoFiscalValidator validator = new RelatorioBatimentoFiscalValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioBatimentoFiscalForm formBatimentoFiscal = (RelatorioBatimentoFiscalForm)form;
		List<SccBatimentoFiscalView> rows = gerarRelatorioBatimentoFiscal(formBatimentoFiscal);
		
		List<SccBatimentoFiscalViewDecorator> decoratorList = new ArrayList<SccBatimentoFiscalViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccBatimentoFiscalViewDecorator decorator = new SccBatimentoFiscalViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;

	}
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_EXCEL);
		return mav;
	}
	
	
	private List<SccBatimentoFiscalView> gerarRelatorioBatimentoFiscal(RelatorioBatimentoFiscalForm form) throws DAOException, ServiceException {
		return sccBatimentoFiscalService.gerarRelatorioBatimentoFiscal(form.getMesCiclo(), form.getAnoCiclo(), form.getCdCiclo(), form.getCdCSP());
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
		
		return "relatorio_batimento_fiscal_filtro";
	}

	@Override
	protected RelatorioBatimentoFiscalForm getForm() {
		
		return new RelatorioBatimentoFiscalForm();
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
	
	

	public SccBatimentoFiscalService getSccBatimentoFiscalService() {
		return sccBatimentoFiscalService;
	}

	public void setSccBatimentoFiscalService(
			SccBatimentoFiscalService sccBatimentoFiscalService) {
		this.sccBatimentoFiscalService = sccBatimentoFiscalService;
	}

}
