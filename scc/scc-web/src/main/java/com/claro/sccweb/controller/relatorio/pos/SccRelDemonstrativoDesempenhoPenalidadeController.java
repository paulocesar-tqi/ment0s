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
import com.claro.cobillingweb.persistence.view.SccRelDemonstrativoDesempenhoPenalidadeView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.SccRelDemonstrativoDesempenhoPenalidadeValidator;
import com.claro.sccweb.decorator.rownum.entity.SccRelDemonstrativoDesempenhoPenalidadeViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccRelDemonstrativoDesempenhoPenalidadeForm;
import com.claro.sccweb.service.SccRelDemonstrativoDesempenhoPenalidadeService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/DemonstrativoDesempenhoPenalidade")
public class SccRelDemonstrativoDesempenhoPenalidadeController extends
		BaseOperationController<SccRelDemonstrativoDesempenhoPenalidadeForm> {
	
	private static final String FWD_VIEW_EXCEL ="relatorio_demonstrativo_desempenho_penalidade_excel";
	
	@Autowired
	private SccRelDemonstrativoDesempenhoPenalidadeService sccRelDemonstrativoDesempenhoPenalidadeService;
	
	private final SccRelDemonstrativoDesempenhoPenalidadeValidator validator = new SccRelDemonstrativoDesempenhoPenalidadeValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		SccRelDemonstrativoDesempenhoPenalidadeForm formRelDemonstrativoDesempenhoPenalidade = (SccRelDemonstrativoDesempenhoPenalidadeForm)form;
		List<SccRelDemonstrativoDesempenhoPenalidadeView> rows = gerarRelDemonstrativoDesempenhoPenalidade(formRelDemonstrativoDesempenhoPenalidade);
		
		List<SccRelDemonstrativoDesempenhoPenalidadeViewDecorator> decoratorList = new ArrayList<SccRelDemonstrativoDesempenhoPenalidadeViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccRelDemonstrativoDesempenhoPenalidadeViewDecorator decorator = new SccRelDemonstrativoDesempenhoPenalidadeViewDecorator(rows.get(i), i);
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
	
	
	private List<SccRelDemonstrativoDesempenhoPenalidadeView> gerarRelDemonstrativoDesempenhoPenalidade(SccRelDemonstrativoDesempenhoPenalidadeForm form) throws DAOException, ServiceException {
		return sccRelDemonstrativoDesempenhoPenalidadeService.gerarRelDemonstrativoDesempenhoPenalidade(form.getMesRelatorio(), form.getAnoRelatorio(), form.getCdCSP());
	}
	

	  @ModelAttribute("operadorasExternas")
		public List<SccOperadora> populaOperadorasExternas() throws Exception{
			List<SccOperadora> comboList = new ArrayList<SccOperadora>();			
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
		
		return "relatorio_demonstrativo_desempenho_penalidade";
	}

	@Override
	protected SccRelDemonstrativoDesempenhoPenalidadeForm getForm() {
		
		return new SccRelDemonstrativoDesempenhoPenalidadeForm();
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
	
	

	public SccRelDemonstrativoDesempenhoPenalidadeService getSccRelDemonstrativoDesempenhoPenalidadeService() {
		return sccRelDemonstrativoDesempenhoPenalidadeService;
	}

	public void setSccRelDemonstrativoDesempenhoPenalidadeService(
			SccRelDemonstrativoDesempenhoPenalidadeService sccRelDemonstrativoDesempenhoPenalidadeService) {
		this.sccRelDemonstrativoDesempenhoPenalidadeService = sccRelDemonstrativoDesempenhoPenalidadeService;
	}

}
