package com.claro.sccweb.controller.relatorio.pos;

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
import com.claro.cobillingweb.persistence.view.SccArquivosFiscaisView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.RelatorioArquivosFiscaisValidator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivosFiscaisViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioArquivosFiscaisForm;
import com.claro.sccweb.service.SccArquivosFiscaisService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/arquivosFiscais")
public class RelatorioArquivosFiscaisController extends
		BaseOperationController<RelatorioArquivosFiscaisForm> {
	
	private static final String FWD_VIEW_EXCEL ="relatorio_arquivos_fiscais_filtro_excel";
	
	@Autowired
	private SccArquivosFiscaisService sccArquivosFiscaisService;
	
	private final RelatorioArquivosFiscaisValidator validator = new RelatorioArquivosFiscaisValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioArquivosFiscaisForm formArquivosFiscais = (RelatorioArquivosFiscaisForm)form;
		List<SccArquivosFiscaisView> rows = gerarRelatorioArquivosFiscais(formArquivosFiscais);
		
		List<SccArquivosFiscaisViewDecorator> decoratorList = new ArrayList<SccArquivosFiscaisViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccArquivosFiscaisViewDecorator decorator = new SccArquivosFiscaisViewDecorator(rows.get(i), i);
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
	
	
	private List<SccArquivosFiscaisView> gerarRelatorioArquivosFiscais(RelatorioArquivosFiscaisForm form) throws DAOException, ServiceException {
		return sccArquivosFiscaisService.gerarRelatorioArquivosFiscais(form.getSgUF(), form.getCdStatusArquivo(), form.getCdCSP(), form.getCdCiclo(), form.getMesCiclo(), form.getAnoCiclo());
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_arquivos_fiscais_filtro";
	}

	@Override
	protected RelatorioArquivosFiscaisForm getForm() {
		
		return new RelatorioArquivosFiscaisForm();
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
	
	@ModelAttribute("listaUF")
	public List<BasicStringItem> populaUf() {
		return _populaUF();
	}

	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses(){
		return _populaComboMeses();
		}
		
	@ModelAttribute("statusArquivo")
	public List<SccStatusArquivo> populaListaStatusArquivo() throws Exception {
		List<SccStatusArquivo> comboList = new ArrayList<SccStatusArquivo>();
		SccStatusArquivo allValues = new SccStatusArquivo();
		allValues.setDsStatusArquivo("Todos");
		comboList.add(0, allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().getStatusArquivo());
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

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}
	
	

	public SccArquivosFiscaisService getSccArquivosFiscaisService() {
		return sccArquivosFiscaisService;
	}

	public void setSccArquivosFiscaisService(
			SccArquivosFiscaisService sccArquivosFiscaisService) {
		this.sccArquivosFiscaisService = sccArquivosFiscaisService;
	}

}
