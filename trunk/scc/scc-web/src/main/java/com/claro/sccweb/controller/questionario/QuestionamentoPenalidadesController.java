package com.claro.sccweb.controller.questionario;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoPenalidadeView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.QuestionamentoFinanceiroValidator;
import com.claro.sccweb.decorator.view.SccQuestionamentoPenalidadeViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.QuestionamentoFinanceiroForm;
import com.claro.sccweb.service.SccQuestionamentoService;

@Controller
@RequestMapping(value="/user/questionamento/penalidade")
public class QuestionamentoPenalidadesController extends BaseOperationController<QuestionamentoFinanceiroForm> {
	
	private static final String FWD_EXCEL_QUESTIONAMENTO_PENALIDADE = "relatorio_questionamento_penalidade_excel";
	
	@Autowired
	private SccQuestionamentoService sccQuestionamentoService;
	
	private final QuestionamentoFinanceiroValidator validator = new QuestionamentoFinanceiroValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm _form, BindingResult bindingResult, Model model) throws Exception {
		
		QuestionamentoFinanceiroForm form = (QuestionamentoFinanceiroForm)_form;
		
		List<SccQuestionamentoPenalidadeView> rows = (List<SccQuestionamentoPenalidadeView>) this.sccQuestionamentoService.gerarRelatorioQuestionamentoPenalidade(form.getFiltro());
		
		List<SccQuestionamentoPenalidadeViewDecorator> decoratorList = new ArrayList<SccQuestionamentoPenalidadeViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccQuestionamentoPenalidadeViewDecorator decorator = new SccQuestionamentoPenalidadeViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
		
	}
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME) BaseForm _form,BindingResult bindingResult, Model model) throws Exception{
		return new ModelAndView(FWD_EXCEL_QUESTIONAMENTO_PENALIDADE);
	}

	

	@Override
	protected String getViewName() {
		
		return "relatorio_questionamento_penalidade";
	}

	@Override
	protected QuestionamentoFinanceiroForm getForm() {
		
		return new QuestionamentoFinanceiroForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() {
		return _populaComboMeses();
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
	

	public void setSccQuestionamentoService(SccQuestionamentoService sccQuestionamentoService) {
		this.sccQuestionamentoService = sccQuestionamentoService;
	}
	

}
