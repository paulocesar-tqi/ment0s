package com.claro.sccweb.controller.relatorio.pre;

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
import com.claro.cobillingweb.persistence.entity.SccPreDominio;
import com.claro.cobillingweb.persistence.view.SccPreRelatorioCriticasView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SccPreRelatorioCriticasValidator;
import com.claro.sccweb.decorator.rownum.entity.SccPreRelatorioCriticasViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccPreRelatorioCriticasForm;
import com.claro.sccweb.service.SccPreRelatorioCriticasService;
import com.claro.sccweb.service.ServiceException;

	@Controller
	@RequestMapping(value="/user/relatorio/criticas")
public class SccPreRelatorioCriticasController extends
			BaseOperationController<SccPreRelatorioCriticasForm> {
		
		@Autowired
		private SccPreRelatorioCriticasService sccPreRelatorioCriticasService;
		
		private final SccPreRelatorioCriticasValidator validator = new SccPreRelatorioCriticasValidator();
		
		public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
			
			SccPreRelatorioCriticasForm formRelatorioCriticas = (SccPreRelatorioCriticasForm)form;
			List<SccPreRelatorioCriticasView> rows = gerarPreRelatorioCriticas(formRelatorioCriticas);
			
			List<SccPreRelatorioCriticasViewDecorator> decoratorList = new ArrayList<SccPreRelatorioCriticasViewDecorator>(rows.size());
			
			for (int i = 0; i < rows.size(); i++) {
				
				SccPreRelatorioCriticasViewDecorator decorator = new SccPreRelatorioCriticasViewDecorator(rows.get(i), i);
				decoratorList.add(decorator);
			}
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
			ModelAndView mav = new ModelAndView(getViewName());
			return mav;

		}
		
		private List<SccPreRelatorioCriticasView> gerarPreRelatorioCriticas(SccPreRelatorioCriticasForm form) throws DAOException, ServiceException {
			return sccPreRelatorioCriticasService.gerarPreRelatorioCriticas(form.getDtInicioEvento(), form.getDtFimEvento(), form.getCdEOTLD(), form.getCdEOTClaro(),
					form.getCdStatus(), form.getCdDefeito(), form.getCdMotivoRejeicao());
		}


		@Override
		protected String getViewName() {
			
			return "relatorio_criticas";
		}

		@Override
		protected SccPreRelatorioCriticasForm getForm() {
			
			return new SccPreRelatorioCriticasForm();
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
		public List<BasicStringItem> populaStatus()	throws Exception
		{
			List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
			BasicStringItem allValues = new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos");
			comboList.add(allValues);
			List<SccPreDominio> rows = getServiceManager().getPesquisaDominiosService().pesquisaPorTipoDominioPre("STCHM");
			for (int i=0;i<rows.size();i++)
				{
				comboList.add(new BasicStringItem(rows.get(i).getId().getCdDominio(), rows.get(i).getDsDominio()));
				}
			return comboList;
		}
		
		@ModelAttribute("motivoRejeicao")
		public List<BasicStringItem> populaMotivoRejeicao()	throws Exception
		{
			List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
			BasicStringItem allValues = new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos");
			comboList.add(allValues);
			List<SccPreDominio> rows = getServiceManager().getPesquisaDominiosService().pesquisaPorTipoDominioPre("MTREJ");
			for (int i=0;i<rows.size();i++)
				{
				comboList.add(new BasicStringItem(rows.get(i).getId().getCdDominio(), rows.get(i).getDsDominio()));
				}
			return comboList;
		}
		
		@ModelAttribute("codigoDefeito")
		public List<BasicStringItem> populaCodigoDefeito()	throws Exception
		{
			List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
			BasicStringItem allValues = new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos");
			comboList.add(allValues);
			List<SccPreDominio> rows = getServiceManager().getPesquisaDominiosService().pesquisaPorTipoDominioPre("DEFCH");
			for (int i=0;i<rows.size();i++)
				{
				comboList.add(new BasicStringItem(rows.get(i).getId().getCdDominio(), rows.get(i).getDsDominio()));
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
			return new ModelAndView("relatorio_criticas_excel");
		}
		
		
		public SccPreRelatorioCriticasService getSccRelatorioCriticasService() {
			return sccPreRelatorioCriticasService;
		}

		public void setSccRelatorioCriticasService(
				SccPreRelatorioCriticasService sccPreRelatorioCriticasService) {
			this.sccPreRelatorioCriticasService = sccPreRelatorioCriticasService;
		}
		
}
