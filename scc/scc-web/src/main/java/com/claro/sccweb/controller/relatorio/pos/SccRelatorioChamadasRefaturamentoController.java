package com.claro.sccweb.controller.relatorio.pos;

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
import com.claro.cobillingweb.persistence.view.SccRelatorioChamadasRefaturamentoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SccRelatorioChamadasRefaturamentoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccRelatorioChamadasRefaturamentoViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccRelatorioChamadasRefaturamentoForm;
import com.claro.sccweb.service.SccRelatorioChamadasRefaturamentoService;
import com.claro.sccweb.service.ServiceException;

	
@Controller
@RequestMapping(value="/user/relatorio/chamadas/refaturamento")
public class SccRelatorioChamadasRefaturamentoController extends
			BaseOperationController<SccRelatorioChamadasRefaturamentoForm> {
		
		@Autowired
		private SccRelatorioChamadasRefaturamentoService sccRelatorioChamadasRefaturamentoService;
		
		private final SccRelatorioChamadasRefaturamentoValidator validator = new SccRelatorioChamadasRefaturamentoValidator();
		
		public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
			
			SccRelatorioChamadasRefaturamentoForm formRelatorioChamadasRefaturamento = (SccRelatorioChamadasRefaturamentoForm)form;
			List<SccRelatorioChamadasRefaturamentoView> rows = gerarRelatorioChamadasRefaturamento(formRelatorioChamadasRefaturamento);
			
			List<SccRelatorioChamadasRefaturamentoViewDecorator> decoratorList = new ArrayList<SccRelatorioChamadasRefaturamentoViewDecorator>(rows.size());
			
			for (int i = 0; i < rows.size(); i++) {
				
				SccRelatorioChamadasRefaturamentoViewDecorator decorator = new SccRelatorioChamadasRefaturamentoViewDecorator(rows.get(i), i);
				decoratorList.add(decorator);
			}
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
			ModelAndView mav = new ModelAndView(getViewName());
			return mav;

		}
		
		private List<SccRelatorioChamadasRefaturamentoView> gerarRelatorioChamadasRefaturamento(SccRelatorioChamadasRefaturamentoForm form) throws DAOException, ServiceException {
			return sccRelatorioChamadasRefaturamentoService.gerarRelatorioChamadasRefaturamento(form.getDtInicioPeriodo(), form.getDtFimPeriodo(), form.getCdEOTLD(), form.getCdEOTClaro(), form.getCdRefaturamento());
		}


		@Override
		protected String getViewName() {
			
			return "relatorio_chamadas_refaturamento";
		}

		@Override
		protected SccRelatorioChamadasRefaturamentoForm getForm() {
			
			return new SccRelatorioChamadasRefaturamentoForm();
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
		
		@ModelAttribute("cdRefaturamento")
		public List<BasicStringItem> populaTiposArquivos() throws Exception {
			List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
			comboList.add(new BasicStringItem("*", "Todos"));
			comboList.add(new BasicStringItem("RC", "RC"));
			comboList.add(new BasicStringItem("RR", "RR"));
			comboList.add(new BasicStringItem("RD", "RD"));
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
			return new ModelAndView("scc_relatorio_chamadas_refaturamento_excel");
		}

		public SccRelatorioChamadasRefaturamentoService getSccRelatorioChamadasRefaturamentoService() {
			return sccRelatorioChamadasRefaturamentoService;
		}

		public void setSccRelatorioChamadasRefaturamentoService(
				SccRelatorioChamadasRefaturamentoService sccRelatorioChamadasRefaturamentoService) {
			this.sccRelatorioChamadasRefaturamentoService = sccRelatorioChamadasRefaturamentoService;
		}
	

}
