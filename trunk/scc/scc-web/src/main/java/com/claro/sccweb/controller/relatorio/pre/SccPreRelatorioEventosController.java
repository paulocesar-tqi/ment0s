package com.claro.sccweb.controller.relatorio.pre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.claro.cobillingweb.persistence.entity.SccPreDominio;
import com.claro.cobillingweb.persistence.view.SccPreRelatorioEventosView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SccPreRelatorioEventosValidator;
import com.claro.sccweb.decorator.rownum.entity.SccPreRelatorioEventosViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccPreRelatorioEventosForm;
import com.claro.sccweb.service.SccPreRelatorioEventosService;
import com.claro.sccweb.service.ServiceException;

	@Controller
	@RequestMapping(value="/user/relatorio/eventos")
public class SccPreRelatorioEventosController extends
			BaseOperationController<SccPreRelatorioEventosForm> {
		
		
		private static final String FWD_VIEW_EXCEL ="relatorio_eventos_excel";
		
		@Autowired
		private SccPreRelatorioEventosService sccPreRelatorioEventosService;
		
		private final SccPreRelatorioEventosValidator validator = new SccPreRelatorioEventosValidator();
		
		public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
			
			SccPreRelatorioEventosForm formRelatorioEventos = (SccPreRelatorioEventosForm)form;
			List<SccPreRelatorioEventosView> rows = gerarPreRelatorioEventos(formRelatorioEventos);
			
			List<SccPreRelatorioEventosViewDecorator> decoratorList = new ArrayList<SccPreRelatorioEventosViewDecorator>(rows.size());
			Map<String,Object> totais = new HashMap<String, Object>();
			Double totalQuantidade = 0.0;
			Double totalDuracaoReal = 0.0;
			Double totalDuracaoTarifada = 0.0;
			Double totalValorBruto = 0.0;
			
			for (int i = 0; i < rows.size(); i++) {
				SccPreRelatorioEventosViewDecorator decorator = new SccPreRelatorioEventosViewDecorator(rows.get(i), i);
				decoratorList.add(decorator);
				totalQuantidade += rows.get(i).getQuantidadeCdrs() != null ? rows.get(i).getQuantidadeCdrs() : 0;
				totalDuracaoReal += rows.get(i).getDuracaoReal() != null ? rows.get(i).getDuracaoReal() : 0;
				totalDuracaoTarifada += rows.get(i).getDuracaoTarifada() != null ? rows.get(i).getDuracaoTarifada() : 0;
				totalValorBruto += rows.get(i).getValorBruto() != null ? rows.get(i).getValorBruto() : 0;
			}
			totais.put("totalQuantidade",  formataInteiro(totalQuantidade));
			totais.put("totalDuracaoReal", formataValorMonetario(totalDuracaoReal));
			totais.put("totalDuracaoTarifada", formataValorMonetario(totalDuracaoTarifada));
			totais.put("totalValorBruto", formataValorMonetario(totalValorBruto));

			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
			storeInSession(getClass(), DISPLAY_TAG_SPACE_2, totais, request);
			ModelAndView mav = new ModelAndView(getViewName());
			return mav;

		}
		
		public ModelAndView excel(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
			ModelAndView mav = new ModelAndView(FWD_VIEW_EXCEL);
			return mav;
		}
		
		
		private List<SccPreRelatorioEventosView> gerarPreRelatorioEventos(SccPreRelatorioEventosForm form) throws DAOException, ServiceException {
			return sccPreRelatorioEventosService.gerarPreRelatorioEventos(form.getDtInicioEvento(), form.getDtFimEvento(), form.getCdEOTLD(), form.getCdEOTClaro(), form.getTpStatus());
		}


		@Override
		protected String getViewName() {
			
			return "relatorio_eventos";
		}

		@Override
		protected SccPreRelatorioEventosForm getForm() {
			
			return new SccPreRelatorioEventosForm();
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

		public SccPreRelatorioEventosService getSccRelatorioEventosService() {
			return sccPreRelatorioEventosService;
		}

		public void setSccRelatorioEventosService(
				SccPreRelatorioEventosService sccPreRelatorioEventosService) {
			this.sccPreRelatorioEventosService = sccPreRelatorioEventosService;
		}
		
}
