package com.claro.sccweb.controller.relatorio;

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
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.SccRelatorioQuestionamentoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SccRelatorioQuestionamentoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccRelatorioQuestionamentoViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccRelatorioQuestionamentoForm;
import com.claro.sccweb.service.SccRelatorioQuestionamentoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92038883
 *
 */

@Controller
@RequestMapping(value="/user/relatorio/questionamento")
public class SccRelatorioQuestionamentoController extends
			BaseOperationController<SccRelatorioQuestionamentoForm> {
		
		@Autowired
		private SccRelatorioQuestionamentoService sccRelatorioQuestionamentoService;
		
		private final SccRelatorioQuestionamentoValidator validator = new SccRelatorioQuestionamentoValidator();
		
		public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
			
			SccRelatorioQuestionamentoForm formRelatorioQuestionamento = (SccRelatorioQuestionamentoForm)form;
			List<SccRelatorioQuestionamentoView> rows = gerarRelatorioQuestionamento(formRelatorioQuestionamento);
			
			List<SccRelatorioQuestionamentoViewDecorator> decoratorList = new ArrayList<SccRelatorioQuestionamentoViewDecorator>(rows.size());
			
			for (int i = 0; i < rows.size(); i++) {
				
				SccRelatorioQuestionamentoViewDecorator decorator = new SccRelatorioQuestionamentoViewDecorator(rows.get(i), i);
				decoratorList.add(decorator);
			}
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
			ModelAndView mav = new ModelAndView(getViewName());
			return mav;

		}
		
		private List<SccRelatorioQuestionamentoView> gerarRelatorioQuestionamento(SccRelatorioQuestionamentoForm form) throws DAOException, ServiceException {
			return sccRelatorioQuestionamentoService.gerarRelatorioQuestionamento(form.getCdEOTLD(), form.getTpStatus());
		}


		@Override
		protected String getViewName() {
			
			return "relatorio_questionamento";
		}

		@Override
		protected SccRelatorioQuestionamentoForm getForm() {
			
			return new SccRelatorioQuestionamentoForm();
		}

		@Override
		protected Validator getValidator() {
			
			return this.validator;
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
		
	
		@ModelAttribute("tiposStatus")
		public List<BasicStringItem> populaStatus() {
			List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
			comboList.add(new BasicStringItem("*","Todos"));
			comboList.add(new BasicStringItem("RGS","Registro LD"));
			comboList.add(new BasicStringItem("PRP","Preparação"));
			comboList.add(new BasicStringItem("ANL","Análise"));
			comboList.add(new BasicStringItem("CRR","Correção"));
			comboList.add(new BasicStringItem("APR","Apuração"));
			comboList.add(new BasicStringItem("CNF","Confirmação"));
			comboList.add(new BasicStringItem("RGC","Registro Claro"));
			comboList.add(new BasicStringItem("INC","Inclusão"));
			comboList.add(new BasicStringItem("RPS","Repasse"));
			
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
		
		public ModelAndView excel(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME) BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
			return new ModelAndView("relatorio_questionamento_excel");
		}

		public SccRelatorioQuestionamentoService getSccRelatorioQuestionamentoService() {
			return sccRelatorioQuestionamentoService;
		}

		public void setSccRelatorioQuestionamentoService(
				SccRelatorioQuestionamentoService sccRelatorioQuestionamentoService) {
			this.sccRelatorioQuestionamentoService = sccRelatorioQuestionamentoService;
		}

	
	

}