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
import com.claro.cobillingweb.persistence.view.FaturamentoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.RelatorioNaoConfFaturamentoValidator;
import com.claro.sccweb.decorator.view.FaturamentoViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioNaoConfFaturamentoForm;
import com.claro.sccweb.service.SccNaoConfAplFaturamentoService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/naoconf/faturamento")
public class SccNaoConfAplFaturamentoController extends
		BaseOperationController<RelatorioNaoConfFaturamentoForm> {
	
	private static final String FWD_VIEW_EXCEL ="relatorio_nao_conformidade_faturamento_excel";
	
	@Autowired
	private SccNaoConfAplFaturamentoService sccNaoConfAplFaturamentoService;
	
	private RelatorioNaoConfFaturamentoValidator validator = new RelatorioNaoConfFaturamentoValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm _form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioNaoConfFaturamentoForm form = (RelatorioNaoConfFaturamentoForm)_form;
		SccFiltro filtro = getFiltro(form);
		
		List<FaturamentoView> rows = gerarRelatorio(filtro);
		
		List<FaturamentoViewDecorator> decoratorList = new ArrayList<FaturamentoViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			FaturamentoViewDecorator decorator = new FaturamentoViewDecorator(rows.get(i), i);
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
	

	
	private SccFiltro getFiltro(RelatorioNaoConfFaturamentoForm form) {
		
		SccFiltro filtro = new SccFiltro();
		filtro.setCdCsp(form.getCdCsp());
		filtro.setCdCiclo(form.getCdCiclo());
		filtro.setMmCiclo(form.getMmCiclo());
		filtro.setAaCiclo(form.getAaCiclo());
		
		return filtro;
	}

	private List<FaturamentoView> gerarRelatorio(SccFiltro filtro) throws ServiceException, DAOException{
		
		return (List<FaturamentoView>) sccNaoConfAplFaturamentoService.gerarRelatorioNaoConfFaturamento(filtro);
		
	}

	@Override
	protected String getViewName() {
		
		return "relatorio_nao_conformidade_faturamento";
	}

	@Override
	protected RelatorioNaoConfFaturamentoForm getForm() {
		
		return new RelatorioNaoConfFaturamentoForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}
	
	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdCsp(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() {
		List<BasicIntegerItem> combo = new ArrayList<BasicIntegerItem>();
		combo.add(new BasicIntegerItem(0l, ""));
		combo.addAll(_populaComboMeses());
		return combo;
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


	public void setValidator(RelatorioNaoConfFaturamentoValidator validator) {
		this.validator = validator;
	}

	public void setSccNaoConfAplFaturamentoService(
			SccNaoConfAplFaturamentoService sccNaoConfAplFaturamentoService) {
		this.sccNaoConfAplFaturamentoService = sccNaoConfAplFaturamentoService;
	}


	
	

}
