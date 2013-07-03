package com.claro.sccweb.controller.relatorio;

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
import com.claro.cobillingweb.persistence.view.SccBatimentoArquivosView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.RelatorioBatimentoArquivosValidator;
import com.claro.sccweb.decorator.rownum.entity.SccBatimentoArquivosViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioBatimentoArquivosForm;
import com.claro.sccweb.service.SccBatimentoArquivosService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/batimento/arquivos")
public class SccRelatorioBatimentoArquivosController extends
		BaseOperationController<RelatorioBatimentoArquivosForm> {
	
	@Autowired
	private SccBatimentoArquivosService sccBatimentoArquivosService;
	
	private final RelatorioBatimentoArquivosValidator validator = new RelatorioBatimentoArquivosValidator();
	
	private static final String FWD_EXCEL_BATIMENTO_ARQUIVOS = "relatorio_batimento_arquivos_filtro_excel";
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm form, BindingResult bindingResult, Model model) throws Exception {
		
		RelatorioBatimentoArquivosForm formBatimentoArquivos = (RelatorioBatimentoArquivosForm)form;
		List<SccBatimentoArquivosView> rows = gerarRelatorioBatimentoArquivos(formBatimentoArquivos);
		
		List<SccBatimentoArquivosViewDecorator> decoratorList = new ArrayList<SccBatimentoArquivosViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccBatimentoArquivosViewDecorator decorator = new SccBatimentoArquivosViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;

	}
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME) BaseForm _form,BindingResult bindingResult, Model model) throws Exception{
		return new ModelAndView(FWD_EXCEL_BATIMENTO_ARQUIVOS);
	}

	
	private List<SccBatimentoArquivosView> gerarRelatorioBatimentoArquivos(RelatorioBatimentoArquivosForm form) throws DAOException, ServiceException {
		return sccBatimentoArquivosService.gerarRelatorioBatimento(form.getDtInicioBatimento(), form.getDtFimBatimento(), form.getCdEOTLD(), form.getCdEOTClaro(), form.getTpArquivo());
	}


	@Override
	protected String getViewName() {
		
		return "relatorio_batimento_arquivos_filtro";
	}

	@Override
	protected RelatorioBatimentoArquivosForm getForm() {
		
		return new RelatorioBatimentoArquivosForm();
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
	
	@ModelAttribute("tiposArquivo")
	public List<BasicStringItem> populaTiposArquivos() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("REM", "Remessa"));
		comboList.add(new BasicStringItem("RET", "Retorno"));
		return comboList;
	}
	
/*
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
*/

	public SccBatimentoArquivosService getSccBatimentoArquivosService() {
		return sccBatimentoArquivosService;
	}

	public void setSccBatimentoArquivosService(
			SccBatimentoArquivosService sccBatimentoArquivosService) {
		this.sccBatimentoArquivosService = sccBatimentoArquivosService;
	}
	


}
