package com.claro.sccweb.controller.relatorio;

import java.util.ArrayList;
import java.util.Collection;
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
import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;
import com.claro.cobillingweb.persistence.view.SccRelatorioQuestionamentoResultadoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SccRelatorioQuestionamentoResultadoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccRelatorioQuestionamentoResultadoViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccRelatorioQuestionamentoResultadoForm;
import com.claro.sccweb.service.SccRelatorioQuestionamentoResultadoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92038883
 *
 */

@Controller
@RequestMapping(value="/user/relatorio/questionamento/resultado")
public class SccRelatorioQuestionamentoResultadoController extends
			BaseOperationController<SccRelatorioQuestionamentoResultadoForm> {
		
		@Autowired
		private SccRelatorioQuestionamentoResultadoService sccRelatorioQuestionamentoResultadoService;
		
		private final SccRelatorioQuestionamentoResultadoValidator validator = new SccRelatorioQuestionamentoResultadoValidator();

		public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
			
			SccRelatorioQuestionamentoResultadoForm formRelatorioQuestionamentoResultado = (SccRelatorioQuestionamentoResultadoForm)form;
			List<SccRelatorioQuestionamentoResultadoView> rows = gerarRelatorioQuestionamentoResultado(formRelatorioQuestionamentoResultado);
			
			List<SccRelatorioQuestionamentoResultadoViewDecorator> decoratorList = new ArrayList<SccRelatorioQuestionamentoResultadoViewDecorator>(rows.size());
			
			for (int i = 0; i < rows.size(); i++) {
				
				SccRelatorioQuestionamentoResultadoViewDecorator decorator = new SccRelatorioQuestionamentoResultadoViewDecorator(rows.get(i), i);
				decoratorList.add(decorator);
			}
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
			ModelAndView mav = new ModelAndView(getViewName());
			return mav;

		}
		
		private List<SccRelatorioQuestionamentoResultadoView> gerarRelatorioQuestionamentoResultado(SccRelatorioQuestionamentoResultadoForm form) throws DAOException, ServiceException {
			return sccRelatorioQuestionamentoResultadoService.gerarRelatorioQuestionamentoResultado(form.getSqQuestionamento(), form.getCdEOTLD(), form.getTpStatus());
		}


		@Override
		protected String getViewName() {
			
			return "relatorio_questionamento_resultado";
		}

		@Override
		protected SccRelatorioQuestionamentoResultadoForm getForm() {
			
			return new SccRelatorioQuestionamentoResultadoForm();
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
			comboList.add(new BasicStringItem("Q_REGISTRO_LD","Registro LD"));
			comboList.add(new BasicStringItem("Q_PREPARACAO","Preparação"));
			comboList.add(new BasicStringItem("Q_ANALISE","Análise"));
			comboList.add(new BasicStringItem("Q_CORRECAO","Correção"));
			comboList.add(new BasicStringItem("Q_APURACAO","Apuração"));
			comboList.add(new BasicStringItem("Q_CONFIRMACAO","Confirmação"));
			comboList.add(new BasicStringItem("Q_REGISTRO_CLARO","Registro Claro"));
			comboList.add(new BasicStringItem("Q_INCLUSAO","Inclusão"));
			comboList.add(new BasicStringItem("Q_REPASSE","Repasse"));
			
			return comboList;
		}
		
		@ModelAttribute("sqQuestionamento")
		public Collection<SccQuestionamentoView> populaSqQuestionamento() throws Exception{
			
			List<SccQuestionamentoView> comboList = new ArrayList<SccQuestionamentoView>();

			SccQuestionamentoView todos = new SccQuestionamentoView();
			todos.setSqQuestionamento(BasicDAO.GET_ALL);
			todos.setDescricaoEotLD("Todos");
			comboList.add(todos);
			
			Collection<SccQuestionamentoView> colecao = this.sccRelatorioQuestionamentoResultadoService.gerarCombo();
			for (SccQuestionamentoView sccQuestionamentoView : colecao) {
				
				String value = "Nr " + sccQuestionamentoView.getSqQuestionamento().toString() + "-" + sccQuestionamentoView.getDescricaoEotLD() +"(" + sccQuestionamentoView.getCdEotLD() + ")";
				sccQuestionamentoView.setDescricaoEotLD(value);

				comboList.add(sccQuestionamentoView);
			}
			
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
			return new ModelAndView("relatorio_questionamento_resultado_excel");
		}

		public SccRelatorioQuestionamentoResultadoService getSccRelatorioQuestionamentoResultadoService() {
			return sccRelatorioQuestionamentoResultadoService;
		}

		public void setSccRelatorioQuestionamentoResultadoService(
				SccRelatorioQuestionamentoResultadoService sccRelatorioQuestionamentoResultadoService) {
			this.sccRelatorioQuestionamentoResultadoService = sccRelatorioQuestionamentoResultadoService;
		}

	

}