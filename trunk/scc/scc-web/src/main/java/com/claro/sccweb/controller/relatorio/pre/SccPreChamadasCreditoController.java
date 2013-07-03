package com.claro.sccweb.controller.relatorio.pre;

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
import com.claro.cobillingweb.persistence.view.SccPreChamadasCreditoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SccPreChamadasCreditoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccPreChamadasCreditoViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccPreChamadasCreditoForm;
import com.claro.sccweb.service.SccPreChamadasCreditoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92038883
 *
 */

@Controller
@RequestMapping(value="/user/relatorio/chamadas/credito")
public class SccPreChamadasCreditoController extends
			BaseOperationController<SccPreChamadasCreditoForm> {
	
		private static final String FWD_VIEW_EXCEL ="relatorio_chamadas_credito_excel";
		
		@Autowired
		private SccPreChamadasCreditoService sccPreChamadasCreditoService;
		
		private final SccPreChamadasCreditoValidator validator = new SccPreChamadasCreditoValidator();
		
		public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
			
			SccPreChamadasCreditoForm formChamadasCredito = (SccPreChamadasCreditoForm)form;
			List<SccPreChamadasCreditoView> rows = gerarPreChamadasCredito(formChamadasCredito);
			
			List<SccPreChamadasCreditoViewDecorator> decoratorList = new ArrayList<SccPreChamadasCreditoViewDecorator>(rows.size());
			
			for (int i = 0; i < rows.size(); i++) {
				
				SccPreChamadasCreditoViewDecorator decorator = new SccPreChamadasCreditoViewDecorator(rows.get(i), i);
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

		
		private List<SccPreChamadasCreditoView> gerarPreChamadasCredito(SccPreChamadasCreditoForm form) throws DAOException, ServiceException {
			return sccPreChamadasCreditoService.gerarPreChamadasCredito(form.getDtInicioCredito(), form.getDtFimCredito(), form.getCdEOTLD(), form.getCdEOTClaro(), form.getTpStatusCredito());
		}


		@Override
		protected String getViewName() {
			
			return "relatorio_chamadas_credito";
		}

		@Override
		protected SccPreChamadasCreditoForm getForm() {
			
			return new SccPreChamadasCreditoForm();
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
		
	
		@ModelAttribute("tiposStatus")
		public List<BasicStringItem> populaStatus() {
			List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
			comboList.add(new BasicStringItem("*","Todos"));
			comboList.add(new BasicStringItem("P","Pendente"));
			comboList.add(new BasicStringItem("C","Confirmado"));
			comboList.add(new BasicStringItem("E","Erro"));
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

		public SccPreChamadasCreditoService getSccPreChamadasCreditoService() {
			return sccPreChamadasCreditoService;
		}

		public void setSccPreChamadasCreditoService(
				SccPreChamadasCreditoService sccPreChamadasCreditoService) {
			this.sccPreChamadasCreditoService = sccPreChamadasCreditoService;
		}

	
	

}
