package com.claro.sccweb.controller.arquivo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoCredito;
import com.claro.cobillingweb.persistence.entity.SccPreDominio;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.ArquivosCreditoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCreditoDecorator;
import com.claro.sccweb.form.ArquivosCreditoForm;
import com.claro.sccweb.form.BaseForm;


@Controller
@RequestMapping(value="/user/arquivos/credito")
public class ArquivosCreditoController extends BaseOperationController<ArquivosCreditoForm> {

	private final ArquivosCreditoValidator validator = new ArquivosCreditoValidator();

	private final  Map<String, String> statusMap = new HashMap<String, String>();
	private final  Map<String, String> origemMap = new HashMap<String, String>();
	
	
	
	public ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView(getViewName());
		if (statusMap.size() == 0)
			{
			info("Iniciando cache de domínios.");
			List<SccPreDominio> dominios = getServiceManager().getPesquisaDominiosService().pesquisaPorTipoDominioPre("CDORG");
			for (SccPreDominio sccPreDominio : dominios) {
				origemMap.put(sccPreDominio.getId().getCdDominio(), sccPreDominio.getDsDominio());
			}
			dominios = getServiceManager().getPesquisaDominiosService().pesquisaPorTipoDominioPre("STCAR");
			for (SccPreDominio sccPreDominio : dominios) {
				statusMap.put(sccPreDominio.getId().getCdDominio(), sccPreDominio.getDsDominio());			
			}
			}
		
		ArquivosCreditoForm form = (ArquivosCreditoForm)_form;
		List<SccArquivoCredito> rows = getServiceManager().getArquivosService().pesquisaArquivosCredito(form.getCdOrigem(), form.getCdStatus(), form.getDataInicial(), form.getDataFinal());
		List<SccArquivoCreditoDecorator> decoratorList = new ArrayList<SccArquivoCreditoDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{						
			String status = statusMap.get(rows.get(i).getCdStatus());
			String origem = origemMap.get(rows.get(i).getCdOrigem());
			SccArquivoCreditoDecorator decorator = new SccArquivoCreditoDecorator(rows.get(i), status, origem, i);			
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		return mav;
	}
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception
	{
		return new ModelAndView("arquivos_credito_excel");
	}
	
	 
	protected String getViewName() {
		return "arquivos_credito_filtro";
	}

	 
	protected ArquivosCreditoForm getForm() {
		return new ArquivosCreditoForm();
	}

	 
	protected Validator getValidator() {
		return this.validator;
	}
	
	@ModelAttribute("origem")
	public List<BasicStringItem> populaOrigem()	throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		BasicStringItem allValues = new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos");
		comboList.add(allValues);
		List<SccPreDominio> rows = getServiceManager().getPesquisaDominiosService().pesquisaPorTipoDominioPre("CDORG");
		for (int i=0;i<rows.size();i++)
			{
			comboList.add(new BasicStringItem(rows.get(i).getId().getCdDominio(), rows.get(i).getDsDominio()));
			}
		return comboList;
	}
	
	@ModelAttribute("status")
	public List<BasicStringItem> populaStatus()	throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		BasicStringItem allValues = new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos");
		comboList.add(allValues);
		List<SccPreDominio> rows = getServiceManager().getPesquisaDominiosService().pesquisaPorTipoDominioPre("STCAR");
		for (int i=0;i<rows.size();i++)
			{
			comboList.add(new BasicStringItem(rows.get(i).getId().getCdDominio(), rows.get(i).getDsDominio()));
			}
		return comboList;
	}
	
}
