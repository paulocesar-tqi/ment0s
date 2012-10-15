package com.claro.sccweb.controller.relatorio.fatura;

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
import com.claro.cobillingweb.persistence.utils.DateUtils;
import com.claro.cobillingweb.persistence.view.SccFaturaView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.SccFaturasValidator;
import com.claro.sccweb.decorator.view.SccFaturasViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccFaturasForm;
import com.claro.sccweb.service.SccFaturasService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/juros/multas")
public class SccJurosMultasController extends BaseOperationController<SccFaturasForm> {
	
	@Autowired
	private SccFaturasService sccFaturasService;
	
	private final SccFaturasValidator validator = new SccFaturasValidator(); 
	
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm _form, BindingResult bindingResult, Model model) throws Exception {
		
		SccFaturasForm form = (SccFaturasForm)_form;
		SccFiltro filtro = getFiltro(form);
		
		List<SccFaturaView> rows = gerarRelatorioJurosMultas(filtro);
		
		List<SccFaturasViewDecorator> decoratorList = new ArrayList<SccFaturasViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccFaturasViewDecorator decorator = new SccFaturasViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
		
	}
	
	private List<SccFaturaView> gerarRelatorioJurosMultas(SccFiltro filtro) throws DAOException, ServiceException {
		
		return sccFaturasService.gerarRelatorioJurosMultas(filtro);
	}

	private SccFiltro getFiltro(SccFaturasForm form){
		
		SccFiltro filtro = new SccFiltro();
		filtro.setOperadoraClaro(form.getEntity().getEotClaro());
		filtro.setCsp(form.getEntity().getCsp());
		filtro.setDataInicialPeriodo(DateUtils.lowDateTime(form.getDataInicialPeriodo()));
		filtro.setDataFinalPeriodo(DateUtils.highDateTime(form.getDataFinalPeriodo()));
		return filtro;
		
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_juros_multas";
	}

	@Override
	protected SccFaturasForm getForm() {
		
		return new SccFaturasForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
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

	public void setSccFaturasService(SccFaturasService sccFaturasService) {
		this.sccFaturasService = sccFaturasService;
	}
	
	
	
	

}
