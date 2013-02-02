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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccStatusCdr;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.ArquivoRetornoDrillDownPosValidator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCobillingDecorator;
import com.claro.sccweb.decorator.rownum.entity.SccCdrCobillingDecorator;
import com.claro.sccweb.form.ArquivoRetornoDrillDownPosForm;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccPaginatedList;

@Controller
@RequestMapping(value="/user/arquivo/retorno/drilldown")
public class ArquivoRetornoDrilldownController extends BaseOperationController<ArquivoRetornoDrillDownPosForm>{

	private final ArquivoRetornoDrillDownPosValidator validator = new ArquivoRetornoDrillDownPosValidator();
	
	private final int TAMANHO_PAGINA = 50;
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		cleanDisplayTag(request);
		ModelAndView mav = new ModelAndView(getViewName());
		ArquivoRetornoDrillDownPosForm myForm = (ArquivoRetornoDrillDownPosForm)form;
		info("Pesquisando arquivos processados pós-pago:  "+myForm.toString());
		List<SccArquivoCobilling> rows = null;
		if (myForm.getTipoArquivo().equals("REM")) {
			rows = getServiceManager().getArquivosService().pesquisaArquivosRemessa(myForm.getCdEOTClaro(), myForm.getCdEOTLD(), myForm.getPeriodo(), myForm.getDataInicial(), myForm.getDataFinal(), myForm.getStatusArquivo(), myForm.getTipoOperadora().equals("H"));
		} else {
			rows = getServiceManager().getArquivosService().pesquisaArquivosRemessaFranquia(myForm.getCdEOTClaro(), myForm.getCdEOTLD(), myForm.getPeriodo(), myForm.getDataInicial(), myForm.getDataFinal(), myForm.getStatusArquivo(), myForm.getTipoOperadora().equals("H"));
		}
		debug("Pesquisa de arquivos processados pós-pago retornou "+rows.size()+" linhas com filtro "+myForm.toString());
		List<SccArquivoCobillingDecorator> decoratorList = new ArrayList<SccArquivoCobillingDecorator>(rows.size());	
		for (int i=0;i<rows.size();i++) {
			SccArquivoCobilling arquivoProtocolo = getServiceManager().getArquivosService().pesquisaArquivoProtocolo(rows.get(i));
			SccArquivoCobillingDecorator decorator = new SccArquivoCobillingDecorator(rows.get(i), arquivoProtocolo, i);
			decoratorList.add(decorator); 
		}
		debug("Resultado de pequisa de arquivos processados pós-pago armazenada em DISPLAY_TAG_SPACE_1");
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		mav.addObject(FORM_NAME, form);
		cacheMyForm(getClass(), form);
		return mav;	
	}
	
	/**
	 * Monta a visão de CDRs do arquivo agrupados por status.
	 * @param request
	 * @param response
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ModelAndView selecionar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {		 
		cleanSession(getClass(), request, DISPLAY_TAG_SPACE_2);
		ModelAndView mav = new ModelAndView(getSumarioCDRsView());
		ArquivoRetornoDrillDownPosForm myForm = (ArquivoRetornoDrillDownPosForm)form;
		myForm.setVisaoArquivo(getSumarioCDRsView());
		cacheMyForm(getClass(), myForm);
		List<SccCdrCobilling> rows = getServiceManager().getArquivosService().geraResumoCDRs(myForm.getCdEOTClaro(), myForm.getCdEOTLD(), myForm.getDataInicial(), myForm.getDataFinal());
		List<SccCdrCobillingDecorator> decoratorList = new ArrayList<SccCdrCobillingDecorator>(rows.size());	
		for (int i=0;i<rows.size();i++) {
			SccCdrCobillingDecorator cdrDecorator = new SccCdrCobillingDecorator(rows.get(i), i);
			//debug("Arquivo "+myForm.getArquivoSelecionado().getNoArquivo()+" possui "+cdrDecorator.getQuantidade()+" no status "+cdrDecorator.getStatus());
			decoratorList.add(cdrDecorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_2, decoratorList, request);		
		return mav;	
	}
	
	public ModelAndView excel(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		info("Iniciando geração de Excel para pesquisa de arquivos processados pós-pago");
		ModelAndView mav = new ModelAndView("pesquisa_arquivos_processados_excel");
		return mav;
	}
	
	public ModelAndView excelCDRs(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		info("Iniciando geração de Excel com CDRs para pesquisa de arquivos processados pós-pago");
		ModelAndView mav = new ModelAndView("pesquisa_arquivos_processados_cdrs_excel");
		ArquivoRetornoDrillDownPosForm myForm = (ArquivoRetornoDrillDownPosForm)getMyFormFromCache(getClass());
		List<SccCdrCobilling> rows = getServiceManager().getArquivosService().listaCDRsStatus(myForm.getFiltroSelecionado().getStatusCdr().getCdStatusCdr(), myForm.getCdEOTClaro(), myForm.getCdEOTLD(), myForm.getDataInicial(), myForm.getDataFinal(), -1, -1);
		List<SccCdrCobillingDecorator> decoratorList = new ArrayList<SccCdrCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccCdrCobillingDecorator cdrDecorator = new SccCdrCobillingDecorator(rows.get(i), i);
			decoratorList.add(cdrDecorator);			
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_4, decoratorList, request);
		return mav;
	}
	
	public ModelAndView csvCDRs(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		info("Iniciando geração do CSV com CDRs para pesquisa de arquivos processados pós-pago");
		ModelAndView mav = new ModelAndView("pesquisa_arquivos_processados_cdrs_csv");
		ArquivoRetornoDrillDownPosForm myForm = (ArquivoRetornoDrillDownPosForm)getMyFormFromCache(getClass());
		List<SccCdrCobilling> rows = getServiceManager().getArquivosService().listaCDRsStatus(myForm.getFiltroSelecionado().getStatusCdr().getCdStatusCdr(), myForm.getCdEOTClaro(), myForm.getCdEOTLD(), myForm.getDataInicial(), myForm.getDataFinal(), -1, -1);
		List<SccCdrCobillingDecorator> decoratorList = new ArrayList<SccCdrCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccCdrCobillingDecorator cdrDecorator = new SccCdrCobillingDecorator(rows.get(i), i);
			decoratorList.add(cdrDecorator);			
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_4, decoratorList, request);
		return mav;
	}
	
	public ModelAndView txtCDRs(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		info("Iniciando geração do TXT com CDRs para pesquisa de arquivos processados pós-pago");
		ModelAndView mav = new ModelAndView("pesquisa_arquivos_processados_cdrs_txt");
		ArquivoRetornoDrillDownPosForm myForm = (ArquivoRetornoDrillDownPosForm)getMyFormFromCache(getClass());
		List<SccCdrCobilling> rows = getServiceManager().getArquivosService().listaCDRsStatus(myForm.getFiltroSelecionado().getStatusCdr().getCdStatusCdr(), myForm.getCdEOTClaro(), myForm.getCdEOTLD(), myForm.getDataInicial(), myForm.getDataFinal(), -1, -1);
		List<SccCdrCobillingDecorator> decoratorList = new ArrayList<SccCdrCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccCdrCobillingDecorator cdrDecorator = new SccCdrCobillingDecorator(rows.get(i), i);
			decoratorList.add(cdrDecorator);			
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_4, decoratorList, request);
		return mav;
	}
	
	public ModelAndView voltar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;	
	}
	
	public ModelAndView voltarArquivo(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception {
		ArquivoRetornoDrillDownPosForm form = (ArquivoRetornoDrillDownPosForm)getMyFormFromCache(getClass());
		return new ModelAndView(form.getVisaoArquivo());
	}
	
	public ModelAndView selecionarCDR(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		ArquivoRetornoDrillDownPosForm myForm = (ArquivoRetornoDrillDownPosForm)getMyFormFromCache(getClass());		
		ModelAndView mav = new ModelAndView(myForm.getVisaoArquivo());
		return mav;	
	}
	
	/**
	 * Lista os CDRs do arquivo.
	 * @param request
	 * @param response
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		cleanSession(getClass(), request, DISPLAY_TAG_SPACE_3);
		ModelAndView mav = new ModelAndView(getListaCDRsView());
		ArquivoRetornoDrillDownPosForm myCachedForm = (ArquivoRetornoDrillDownPosForm)getMyFormFromCache(getClass());
		SccCdrCobillingDecorator decorator = (SccCdrCobillingDecorator)getItemSelecionado(request, DISPLAY_TAG_SPACE_2, form);
		myCachedForm.setFiltroSelecionado(decorator.getRow());
		myCachedForm.setVisaoArquivo(myCachedForm.getVisaoArquivo());
		SccPaginatedList paginatedList = new SccPaginatedList();
		paginatedList.setObjectsPerPage(TAMANHO_PAGINA);
		paginatedList.setPageNumber(1);		
		List<SccCdrCobilling> rows = getServiceManager().getArquivosService().listaCDRsStatus(myCachedForm.getFiltroSelecionado().getStatusCdr().getCdStatusCdr(), myCachedForm.getCdEOTClaro(), myCachedForm.getCdEOTLD(), myCachedForm.getDataInicial(), myCachedForm.getDataFinal(), paginatedList.getPageNumber()-1, TAMANHO_PAGINA);
		List<SccCdrCobillingDecorator> decoratorList = new ArrayList<SccCdrCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccCdrCobillingDecorator cdrDecorator = new SccCdrCobillingDecorator(rows.get(i), i);
			decoratorList.add(cdrDecorator);			
		}
		paginatedList.setList(decoratorList);
		paginatedList.setFullListSize(myCachedForm.getFiltroSelecionado().getNuCdr().intValue());
		myCachedForm.setCdrList(paginatedList);
		storeInSession(getClass(), DISPLAY_TAG_SPACE_3, paginatedList, request);
		cacheMyForm(getClass(), myCachedForm);
		return mav;
	}
	
	@RequestMapping(value="/pagina" , method=RequestMethod.GET)
	public ModelAndView pagina(@RequestParam("page") Integer page,HttpServletRequest request, HttpServletResponse response ) throws Exception {
		ArquivoRetornoDrillDownPosForm myForm = (ArquivoRetornoDrillDownPosForm)getMyFormFromCache(getClass());
		ModelAndView mav = new ModelAndView(getListaCDRsView());
		mav.addObject("filtro", myForm);
		SccPaginatedList paginatedList = myForm.getCdrList();
		paginatedList.setObjectsPerPage(TAMANHO_PAGINA);
		paginatedList.setPageNumber(page);

		List<SccCdrCobilling> rows = getServiceManager().getArquivosService().listaCDRsStatus(myForm.getFiltroSelecionado().getStatusCdr().getCdStatusCdr(), myForm.getCdEOTClaro(), myForm.getCdEOTLD(), myForm.getDataInicial(), myForm.getDataFinal(), paginatedList.getPageNumber()-1, TAMANHO_PAGINA);
		List<SccCdrCobillingDecorator> decoratorList = new ArrayList<SccCdrCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccCdrCobillingDecorator cdrDecorator = new SccCdrCobillingDecorator(rows.get(i), i);
			decoratorList.add(cdrDecorator);
			paginatedList.setList(decoratorList);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_3, paginatedList, request);
		return mav;
	}
	
	@ModelAttribute("produtos")
	public List<SccProdutoCobilling> populaProdutos() throws Exception {
		return super.populaProdutos(false);
	}

	
	protected String getViewName() {
		return "pesquisa_arquivos_retorno_drilldown";
	}
	
	private String getSumarioCDRsView() {
		return "pesquisa_arquivos_retorno_drilldown_status";
	}
	
	private String getListaCDRsView() {
		return "pesquisa_arquivos_retorno_drilldown_cdrs";
	}
	
	protected ArquivoRetornoDrillDownPosForm getForm() {
		return new ArquivoRetornoDrillDownPosForm();
	}
	
	protected Validator getValidator() {		
		return this.validator;
	}
	
	@ModelAttribute("tiposOperadora")
	public List<BasicStringItem> populaTiposOperadoras() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("OP", "Operadora"));
		comboList.add(new BasicStringItem("HO", "Holding"));
		return comboList;
	}
	
	@ModelAttribute("statusArquivo")
	public List<BasicStringItem> populaStatusArquivo() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("", "Todos Arquivos"));
		comboList.add(new BasicStringItem("OK", "Arquivos OK"));
		comboList.add(new BasicStringItem("NOK", "Arquivos Não OK"));
		comboList.add(new BasicStringItem("NOPROTOCOL", "Arquivos sem Protocolo"));
		return comboList;
	}
	
	@ModelAttribute("tiposArquivo")
	public List<BasicStringItem> populaTiposArquivo() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("REM", "Remessa"));
		comboList.add(new BasicStringItem("FRANQ", "Remessa Franquia"));
		return comboList;
	}
	
	@ModelAttribute("tiposPeriodo")
	public List<BasicStringItem> populaTiposPeriodo() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("REF", "Data de Referência"));
		comboList.add(new BasicStringItem("PROC", "Data de Processamento Claro"));
		return comboList;
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

	@ModelAttribute("eventos")
	public List<SccStatusCdr> populaEventos() throws Exception {
		List<SccStatusCdr> comboList = new ArrayList<SccStatusCdr>();
		SccStatusCdr allValues = new SccStatusCdr();
		allValues.setCdStatusCdr(BasicDAO.GET_ALL);
		allValues.setDsStatusCdr("Todos");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().getAllStatus());
		return comboList;
	}
}
