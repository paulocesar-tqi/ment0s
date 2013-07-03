package com.claro.sccweb.controller.arquivo;

import java.util.ArrayList;
import java.util.List;

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
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.RelCDRPrePagoView;
import com.claro.cobillingweb.persistence.view.SomatorioRelCDRPrePagoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.ControleRemessaCDRPreValidator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.ControleRemessaCDRPreForm;

@Controller
@RequestMapping(value="/user/arquivo/controle/cdr")
public class ControleRemessaCDRPreController extends BaseOperationController<ControleRemessaCDRPreForm>{

	private final ControleRemessaCDRPreValidator validator = new ControleRemessaCDRPreValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception
	{		
		cleanDisplayTag(request);
		ModelAndView mav = new ModelAndView(getViewName());
		ControleRemessaCDRPreForm form = (ControleRemessaCDRPreForm)_form;
		form.setQuantidadeCDRs(null);
		form.setMinutosReais(null);
		form.setMinutosTarifados(null);
		form.setValorBruto(null);
		List<RelCDRPrePagoView> view = getServiceManager().getControleRemessaService().pesquisaCDRsPrePago(form.getCdEOTClaro(), form.getCdEOTLD(), form.getTipoPeriodo(), form.getDataInicial(), form.getDataFinal(), form.getTipoOperadora().equals("H"));
		SomatorioRelCDRPrePagoView somatorio = getServiceManager().getControleRemessaService().geraSomatorioCDRs(form.getDataInicial(), form.getDataFinal(), form.getTipoPeriodo());
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, view, request);
		if (somatorio != null)
			{
			form.setQuantidadeCDRs(somatorio.getQuantidadeCDRs().toString());
			form.setMinutosReais(decimalFormat.format(somatorio.getMinutosReais()));
			form.setMinutosTarifados(decimalFormat.format(somatorio.getMinutosTarifados()));
			form.setValorBruto(decimalFormat.format(somatorio.getValorBruto()));
			}
		form.setMostrarSomatorio(true);
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception
	{
		return new ModelAndView(getExcelView());
	}
	
	 
	protected String getViewName() {		
		return "controle_remessa_cdr_pre";
	}
	
	private String getExcelView()
	{
		return "controle_remessa_cdr_pre_excel";
	}

	 
	protected ControleRemessaCDRPreForm getForm() {		
		return new ControleRemessaCDRPreForm();
	}

	 
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
	
	@ModelAttribute("tiposOperadora")
	public List<BasicStringItem> populaTiposOperadoras() throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("O", "Operadora"));
		comboList.add(new BasicStringItem("H", "Holding"));
		return comboList;
	}
	
	
	@ModelAttribute("tiposPeriodo")
	public List<BasicStringItem> populaTiposPeriodo() throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("C", "Data da Chamada"));
		comboList.add(new BasicStringItem("A", "Data da Apuração"));
		comboList.add(new BasicStringItem("F", "Data do Fechamento"));
		return comboList;
	}
	
}
