package com.claro.sccweb.controller.arquivo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.cobillingweb.persistence.entity.SccMotivoRejeicao;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.AmostragemCriticaValidator;
import com.claro.sccweb.decorator.rownum.entity.SccCdrCobillingDecorator;
import com.claro.sccweb.form.AmostragemCriticaForm;
import com.claro.sccweb.form.BaseForm;

@Controller
@RequestMapping(value="/user/arquivo/retorno/critica")
public class AmostragemCriticaController extends BaseOperationController<AmostragemCriticaForm> {

	private final AmostragemCriticaValidator validator = new AmostragemCriticaValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME) BaseForm _form,BindingResult bindingResult, Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView(getViewName());
		AmostragemCriticaForm form = (AmostragemCriticaForm)_form;
		List<SccCdrCobilling> rows = getServiceManager().getArquivosService().pesquisaCDRsRejeitados(form.getCdMotivoRejeicao(), form.getCdEOTClaro(), form.getCdEOTLD(), form.getDataInicial(), form.getDataFinal());
	    List<SccCdrCobillingDecorator> decoratorList = new ArrayList<SccCdrCobillingDecorator>(rows.size());
	    for (int i=0;i<rows.size();i++)
	    	{
	    	SccCdrCobillingDecorator decorator = new SccCdrCobillingDecorator(rows.get(i), rows.get(i).getArquivoRetorno(),i);
	    	decoratorList.add(decorator);
	    	}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), form);
		return mav;
	}
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME) BaseForm _form,BindingResult bindingResult, Model model) throws Exception
	{
		return new ModelAndView("arquivo_retorno_critica_excel");
	}
	
	 
	protected String getViewName() {
		return "arquivo_retorno_critica";
	}

	 
	protected AmostragemCriticaForm getForm() {
		return new AmostragemCriticaForm();
	}

	 
	protected Validator getValidator() {
		return validator;
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
	/*
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadoras() throws Exception
	{
		return super.populaOperadorasClaro(false);
	}
	*/
	
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
	/*
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception
	{
		return super.populaOperadorasExternas(false);
	}
	*/
	
	@ModelAttribute("criticas")
	public List<SccMotivoRejeicao> populaCriticas() throws Exception
	{
		return getServiceManager().getPesquisaDominiosService().getAllMotivosRejeicao();
	}
	
}
