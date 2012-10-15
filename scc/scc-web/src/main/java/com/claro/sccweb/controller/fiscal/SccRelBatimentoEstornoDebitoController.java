package com.claro.sccweb.controller.fiscal;

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
import com.claro.cobillingweb.persistence.entity.SccStatusArquivo;
import com.claro.cobillingweb.persistence.view.SccRelBatimentoEstornoDebitoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.SccRelBatimentoEstornoDebitoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccRelBatimentoEstornoDebitoViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccRelBatimentoEstornoDebitoForm;
import com.claro.sccweb.service.SccRelBatimentoEstornoDebitoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92038883
 *
 */

@Controller
@RequestMapping(value="/user/fiscal/relatorio/batimento/estorno/debito")
public class SccRelBatimentoEstornoDebitoController extends
				BaseOperationController<SccRelBatimentoEstornoDebitoForm> {
			
			@Autowired
			private SccRelBatimentoEstornoDebitoService sccRelBatimentoEstornoDebitoService;
			
			private final SccRelBatimentoEstornoDebitoValidator validator = new SccRelBatimentoEstornoDebitoValidator();
			
			public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
				
				SccRelBatimentoEstornoDebitoForm formBatimentoEstornoDebito = (SccRelBatimentoEstornoDebitoForm)form;
				List<SccRelBatimentoEstornoDebitoView> rows = gerarRelBatimentoEstornoDebito(formBatimentoEstornoDebito);
				
				List<SccRelBatimentoEstornoDebitoViewDecorator> decoratorList = new ArrayList<SccRelBatimentoEstornoDebitoViewDecorator>(rows.size());
				
				for (int i = 0; i < rows.size(); i++) {
					
					SccRelBatimentoEstornoDebitoViewDecorator decorator = new SccRelBatimentoEstornoDebitoViewDecorator(rows.get(i), i);
					decoratorList.add(decorator);
				}
				storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
				ModelAndView mav = new ModelAndView(getViewName());
				return mav;

			}
			
			private List<SccRelBatimentoEstornoDebitoView> gerarRelBatimentoEstornoDebito(SccRelBatimentoEstornoDebitoForm form) throws DAOException, ServiceException {
				return sccRelBatimentoEstornoDebitoService.gerarRelBatimentoEstornoDebito(form.getCdEOTLD(), form.getCdEOTClaro(), form.getCdStatusArquivo(), form.getMmCiclo(), form.getAaCiclo()); 
			}


			@Override
			protected String getViewName() {
				
				return "relatorio_batimento_estorno_debito";
			}

			@Override
			protected SccRelBatimentoEstornoDebitoForm getForm() {
				
				return new SccRelBatimentoEstornoDebitoForm();
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
			public List<SccStatusArquivo> populaStatus() throws Exception {
				List<SccStatusArquivo> comboList = new ArrayList<SccStatusArquivo>();
				SccStatusArquivo allValues = new SccStatusArquivo();
				allValues.setCdStatusArquivo(BasicDAO.GET_ALL_STRING);
				allValues.setDsStatusArquivo("Todos");
				comboList.add(0,allValues);
				comboList.addAll(getServiceManager().getPesquisaDominiosService().findByStatus());
				return comboList;
			}
			
			@ModelAttribute("meses")
			public List<BasicIntegerItem> populaMeses() {
				return _populaComboMeses();
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

			public SccRelBatimentoEstornoDebitoService getSccRelBatimentoEstornoDebitoService() {
				return sccRelBatimentoEstornoDebitoService;
			}

			public void setSccRelBatimentoEstornoDebitoService(SccRelBatimentoEstornoDebitoService sccRelBatimentoEstornoDebitoService) {
				this.sccRelBatimentoEstornoDebitoService = sccRelBatimentoEstornoDebitoService;
			}

			
	

}
