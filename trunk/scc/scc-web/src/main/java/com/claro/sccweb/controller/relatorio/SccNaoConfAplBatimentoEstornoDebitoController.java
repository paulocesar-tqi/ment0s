package com.claro.sccweb.controller.relatorio;

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
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.BatimentoEstornoDebitoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.RelatorioNaoConfBatimentoEstornoDebitoValidator;
import com.claro.sccweb.decorator.view.BatimentoEstornoDebitoViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioNaoConfBatimentoEstornoDebitoForm;
import com.claro.sccweb.service.SccNaoConfAplBatimentoEstornoDebitoService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/naoconf/batimento/estorno")
public class SccNaoConfAplBatimentoEstornoDebitoController extends
		BaseOperationController<RelatorioNaoConfBatimentoEstornoDebitoForm> {
	
	private static final String FWD_VIEW_EXCEL ="relatorio_nao_conformidade_batimento_estorno_debito_excel";
	
	@Autowired
	private SccNaoConfAplBatimentoEstornoDebitoService sccNaoConfAplBatimentoEstornoDebitoService;
	
	private RelatorioNaoConfBatimentoEstornoDebitoValidator validator = new RelatorioNaoConfBatimentoEstornoDebitoValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm _form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioNaoConfBatimentoEstornoDebitoForm form = (RelatorioNaoConfBatimentoEstornoDebitoForm)_form;
		SccFiltro filtro = getFiltro(form);
		
		List<BatimentoEstornoDebitoView> rows = gerarRelatorio(filtro);
		
		List<BatimentoEstornoDebitoViewDecorator> decoratorList = new ArrayList<BatimentoEstornoDebitoViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			BatimentoEstornoDebitoViewDecorator decorator = new BatimentoEstornoDebitoViewDecorator(rows.get(i), i);
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
	
	
	private SccFiltro getFiltro(RelatorioNaoConfBatimentoEstornoDebitoForm form) {
		
		SccFiltro filtro = new SccFiltro();
		filtro.setOperadoraClaro(form.getCdEOTClaro());
		filtro.setOperadoraExterna(form.getEntity().getOperadoraLD());
		filtro.setMmCiclo(form.getMesReferencia());
		filtro.setAaCiclo(form.getAnoReferencia());
		
		return filtro;
	}
	
	private List<BatimentoEstornoDebitoView> gerarRelatorio(SccFiltro filtro) throws ServiceException, DAOException{
		
		return (List<BatimentoEstornoDebitoView>) sccNaoConfAplBatimentoEstornoDebitoService.gerarRelatorioBatimentoEstornoDebito(filtro);
		
	}




	@Override
	protected String getViewName() {
		
		return "relatorio_nao_conformidade_batimento_estorno_debito";
	}

	@Override
	protected RelatorioNaoConfBatimentoEstornoDebitoForm getForm() {
		
		return new RelatorioNaoConfBatimentoEstornoDebitoForm();
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
	


	public void setValidator(
			RelatorioNaoConfBatimentoEstornoDebitoValidator validator) {
		this.validator = validator;
	}

	public void setSccNaoConfAplBatimentoEstornoDebitoService(
			SccNaoConfAplBatimentoEstornoDebitoService sccNaoConfAplBatimentoEstornoDebitoService) {
		this.sccNaoConfAplBatimentoEstornoDebitoService = sccNaoConfAplBatimentoEstornoDebitoService;
	}
	
	
	
	

}
