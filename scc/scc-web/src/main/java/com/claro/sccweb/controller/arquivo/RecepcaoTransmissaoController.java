package com.claro.sccweb.controller.arquivo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccStatusArquivo;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.RecepcaoTransmissaoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCobillingDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RecepcaoTransmissaoForm;

/**
 * Funcionalidade : Arquivos/Recepção e Transmissão
 * 
 * Páginas : /WEB-INF/jsp/recepcao_transmissao/
 * 
 * Descrição : Gerar o relatório de Recepção e Transmissão, permitindo controle de recebimento 
 * dos arquivos endereçados pelas LDs e envio dos arquivos gerados pelo SCC, endereçados às LDs. 
 *
 */
@Controller
@RequestMapping("/user/recepcao_transmissao")
public class RecepcaoTransmissaoController extends BaseOperationController<RecepcaoTransmissaoForm>{
	
	private final RecepcaoTransmissaoValidator validator = new RecepcaoTransmissaoValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute("filtro")  BaseForm _form,BindingResult bindingResult,Model model) throws Exception {
		debug("Pesquisando arquivos recebidos/transmitidos");
		RecepcaoTransmissaoForm form = (RecepcaoTransmissaoForm)_form;
		ModelAndView mav = new ModelAndView();
		
		
		Calendar df = Calendar.getInstance();
		df.setTime(form.getPesquisa().getDataFinalPeriodo());
		df.add(Calendar.DAY_OF_MONTH, 1);
		
		form.getPesquisa().setDataFinalPeriodo(df.getTime());		
		
		List<SccArquivoCobilling> resultadoPesquisa =  getServiceManager().getArquivosService().pesquisaArquivos(form.getPesquisa());
		
		df.setTime(form.getPesquisa().getDataFinalPeriodo());
		df.add(Calendar.DAY_OF_MONTH, -1);
		
		form.getPesquisa().setDataFinalPeriodo(df.getTime());
		
		
		form.getPesquisa().setDataFinalPeriodo(df.getTime());
		
		List<SccArquivoCobillingDecorator> decoratorList = new ArrayList<SccArquivoCobillingDecorator>(resultadoPesquisa.size());
		for (int i=0;i<resultadoPesquisa.size();i++) {
			SccArquivoCobilling arquivoProtocolo = getServiceManager().getArquivosService().pesquisaArquivoProtocolo(resultadoPesquisa.get(i));
			SccArquivoCobillingDecorator decorator = new SccArquivoCobillingDecorator(resultadoPesquisa.get(i), arquivoProtocolo, i);
			decoratorList.add(decorator);
		}
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		
		cacheMyForm(getClass(), form);
		mav.setViewName(getViewName());			
		return mav;	
	}
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute("filtro")  BaseForm _form,BindingResult bindingResult,Model model) {
		return new ModelAndView("transmissao_recepcao_excel");
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
	
	@ModelAttribute("holdingClaro")
	public List<SccOperadora> populaHoldingClaro() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaHoldingClaro());
		return comboList;
	}
	
	@ModelAttribute("errosProtocolo")
	public List<BasicStringItem> populaErrosProtocolo() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos"));
		comboList.add(new BasicStringItem("OK","Arquivos OK"));
		comboList.add(new BasicStringItem("NOK","Arquivos não OK"));
		comboList.add(new BasicStringItem("SP","Arquivos sem protocolo"));
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
	public List<BasicStringItem> populaTiposArquivo() throws Exception {
		return BaseFormController.getTiposBatimentoArquivo();
	}
	
	@ModelAttribute("statusArquivo")
	public List<SccStatusArquivo> populaListaStatusArquivo() throws Exception {
		List<SccStatusArquivo> comboList = new ArrayList<SccStatusArquivo>();
		SccStatusArquivo allValues = new SccStatusArquivo();
		allValues.setCdStatusArquivo(BasicDAO.GET_ALL_STRING);
		allValues.setDsStatusArquivo("Todos");
		comboList.add(0, allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().getStatusArquivo());
		return comboList;
	}
	
	@ModelAttribute("tiposPeriodo")
	public List<BasicStringItem> populaTipoPeriodo() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();		
		comboList.add(new BasicStringItem("REF", "Data de Referência"));
		comboList.add(new BasicStringItem("PROC", "Data de Processamento Claro"));
		return comboList;
	}
	
	
	@ModelAttribute("tiposOperadora")
	public List<BasicStringItem> populaTiposOperadora() throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();		
		comboList.add(new BasicStringItem("OP", "Operadora"));
		comboList.add(new BasicStringItem("HO", "Holding"));
		return comboList;
	}
	
	@RequestMapping(value = "/operadoras/json" , method= RequestMethod.GET )
	public void populaOperadorasJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SccOperadora> list = populaOperadorasClaro();
		JSONObject jsonResponse = new JSONObject();		 
		for (int i=0;i<list.size();i++) {			
			jsonResponse.put(list.get(i).getCdEot(),list.get(i).getDsOperadoraForCombos());			
		}
		response.setContentType("application/json");
	    response.getWriter().print(jsonResponse.toString());
	}
	
	@RequestMapping(value = "/holding/json" , method= RequestMethod.GET )
	public void populaHoldingJSON(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SccOperadora> list = populaHoldingClaro();
		JSONObject jsonResponse = new JSONObject();		 
		for (int i=0;i<list.size();i++) {
			jsonResponse.put(list.get(i).getCdEot(),list.get(i).getDsOperadoraForCombos());			
		}
		response.setContentType("application/json");
	    response.getWriter().print(jsonResponse.toString());
	}
	
	public String getViewName() {
		return "transmissao_recepcao_pesquisa";
	}
	
	protected RecepcaoTransmissaoForm getForm() {
		return new RecepcaoTransmissaoForm();
	}
	
	protected Validator getValidator() {
		return this.validator;
	}
	
}
