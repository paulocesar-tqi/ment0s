package com.claro.sccweb.controller.processados.pos;

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
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.PesquisaProcessadosPosValidator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCobillingDecorator;
import com.claro.sccweb.decorator.rownum.entity.SccCdrCobillingDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.PesquisaProcessadosPosForm;
import com.claro.sccweb.form.SccPaginatedList;

@Controller
@RequestMapping(value="/user/pos/processados/pesquisa")
public class PesquisaProcessadosPosController extends BaseOperationController<PesquisaProcessadosPosForm>{

	private final PesquisaProcessadosPosValidator validator = new PesquisaProcessadosPosValidator();
	
	private final int TAMANHO_PAGINA = 50;
	
	@SuppressWarnings("unused")
	private static final String FWD_PESQUISA = "pesquisar";
	private static final String FWD_MOTIVO = "visualizarNOK";
	private static final String FWD_CICLO = "visualizarOK";
	private static final String FWD_SELECIONE = "selecionar";
	@SuppressWarnings("unused")
	private static final String FWD_SELECIONE_CDRS = "";
	private static final String FWD_EXCEL_CDRS = "excelCDRs";
	private static final String FWD_EXCEL = "excel";
	private static final String FWD_VOLTAR = "voltar";
	private static final String FWD_VOLTAR_ARQUIVO = "voltarArquivo";
	private static final String FWD_LISTAR = "listar";
	
	
	@RequestMapping(value="/execute" , method=RequestMethod.POST)
	public ModelAndView executa(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(FORM_NAME)  PesquisaProcessadosPosForm form,BindingResult bindingResult,Model model) throws Exception {
		
		ModelAndView mav = null;
		
		String operacao = form.getOperacao();
		if (operacao.equalsIgnoreCase(FWD_EXCEL_CDRS)) {
			mav = excelCDRs(request, response, form, bindingResult, model); 
		} else if(operacao.equalsIgnoreCase(FWD_EXCEL)){
			mav = excel(request, response, form, bindingResult, model);
		} else if(operacao.equalsIgnoreCase(FWD_MOTIVO)){
			mav = visualizarNOK(request, response, form, bindingResult, model);
		} else if(operacao.equals(FWD_SELECIONE)){
			mav = selecionar(request, response, form, bindingResult, model);
		} else if(operacao.equalsIgnoreCase(FWD_VOLTAR)){
			mav = voltar(request, response, form, bindingResult, model);
		} else if(operacao.equalsIgnoreCase(FWD_VOLTAR_ARQUIVO)){
			mav = voltarArquivo(request, response, form, bindingResult, model);
		} else if(operacao.equalsIgnoreCase(FWD_LISTAR)){
			mav = listar(request, response, form, bindingResult, model);
		} else if(operacao.equalsIgnoreCase(FWD_CICLO)){
			mav = visualizarOK(request, response, form, bindingResult, model);
		}else{
			mav = pesquisar(request, response, form, bindingResult, model);
		}
		return mav;		  
	}

	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		cleanDisplayTag(request);
		ModelAndView mav = new ModelAndView(getViewName());
		PesquisaProcessadosPosForm myForm = (PesquisaProcessadosPosForm)form;
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
	private ModelAndView selecionar(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {		 
		cleanSession(getClass(), request, DISPLAY_TAG_SPACE_2);
		ModelAndView mav = new ModelAndView(getSumarioCDRsView());
		PesquisaProcessadosPosForm myForm = (PesquisaProcessadosPosForm)form;
		SccArquivoCobillingDecorator decorator = (SccArquivoCobillingDecorator)getItemSelecionado(request, DISPLAY_TAG_SPACE_1, form);
		myForm.setArquivoSelecionado(decorator.getRow());
		info("Usuário selecionou arquivo processado "+myForm.getArquivoSelecionado().getNoArquivo()+" para sumário por status de CDRs.");
		myForm.setVisaoArquivo(getSumarioCDRsView());
		cacheMyForm(getClass(), myForm);
		List<SccCdrCobilling> rows = getServiceManager().getArquivosService().geraResumoCDRsArquivo(myForm.getArquivoSelecionado().getSqArquivo());
		List<SccCdrCobillingDecorator> decoratorList = new ArrayList<SccCdrCobillingDecorator>(rows.size());	
		for (int i=0;i<rows.size();i++) {
			SccCdrCobillingDecorator cdrDecorator = new SccCdrCobillingDecorator(rows.get(i), i);
			//debug("Arquivo "+myForm.getArquivoSelecionado().getNoArquivo()+" possui "+cdrDecorator.getQuantidade()+" no status "+cdrDecorator.getStatus());
			decoratorList.add(cdrDecorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_2, decoratorList, request);		
		return mav;	
	}
	
	@RequestMapping(value="excel", method = RequestMethod.GET)
	private ModelAndView excel(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		info("Iniciando geração de Excel para pesquisa de arquivos processados pós-pago");
		ModelAndView mav = new ModelAndView("pesquisa_arquivos_processados_excel");
		return mav;
	}
	
	private ModelAndView excelCDRs(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		info("Iniciando geração de Excel com CDRs para pesquisa de arquivos processados pós-pago");
		ModelAndView mav = new ModelAndView("pesquisa_arquivos_processados_cdrs_excel");
		PesquisaProcessadosPosForm myForm = (PesquisaProcessadosPosForm)getMyFormFromCache(getClass());
		List<SccCdrCobilling> rows = getServiceManager().getArquivosService().listaCDRsArquivo(myForm.getArquivoSelecionado().getSqArquivo(), myForm.getFiltroSelecionado(), -1, -1);
		List<SccCdrCobillingDecorator> decoratorList = new ArrayList<SccCdrCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccCdrCobillingDecorator cdrDecorator = new SccCdrCobillingDecorator(rows.get(i), i);
			decoratorList.add(cdrDecorator);			
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_4, decoratorList, request);
		return mav;
	}
	
	private ModelAndView voltar(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName(), "filtro", form);
		return mav;	
	}
	
	public ModelAndView voltarArquivo(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception {
		PesquisaProcessadosPosForm form = (PesquisaProcessadosPosForm)getMyFormFromCache(getClass());
		return new ModelAndView(form.getVisaoArquivo());
	}
	
	@SuppressWarnings("unused")
	public ModelAndView selecionarCDR(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		PesquisaProcessadosPosForm myForm = (PesquisaProcessadosPosForm)getMyFormFromCache(getClass());		
		Integer itemSelecionado = ((PesquisaProcessadosPosForm)form).getItemSelecionado();
		ModelAndView mav = new ModelAndView(myForm.getVisaoArquivo());
		return mav;	
	}
	
	/**
	 * Visualizar os CDRs agrupados por Ciclo quando o usuário clicar no link "Ciclo" na lista de resultados de arquivos.
	 * @param request
	 * @param response
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	private ModelAndView visualizarOK(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		cleanSession(getClass(), request, DISPLAY_TAG_SPACE_2);
		ModelAndView mav = new ModelAndView(getSumarioProcessadosView());
		PesquisaProcessadosPosForm myForm = (PesquisaProcessadosPosForm)form;
		SccArquivoCobillingDecorator decorator = (SccArquivoCobillingDecorator)getItemSelecionado(request, DISPLAY_TAG_SPACE_1, form);
		myForm.setArquivoSelecionado(decorator.getRow());
		myForm.setVisaoArquivo(getSumarioProcessadosView());
		cacheMyForm(getClass(), myForm);
		List<SccCdrCobilling> rows = getServiceManager().getArquivosService().geraResumoCDRsSemErroArquivo(myForm.getArquivoSelecionado().getSqArquivo());
		List<SccCdrCobillingDecorator> decoratorList = new ArrayList<SccCdrCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccCdrCobillingDecorator cdrDecorator = new SccCdrCobillingDecorator(rows.get(i), i);
			decoratorList.add(cdrDecorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_2, decoratorList, request);
		return mav;	
	}
	
	/**
	 * Chamado quando o usuário clicar no link "Motivo" na lista de arquivos retornados.
	 * Serão apresentados os CDRs com erro agrupados por motivo.
	 * @param request
	 * @param response
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	private ModelAndView visualizarNOK(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		cleanSession(getClass(), request, DISPLAY_TAG_SPACE_2);
		ModelAndView mav = new ModelAndView(getSumarioErrosView());
		PesquisaProcessadosPosForm myForm = (PesquisaProcessadosPosForm)form;
		SccArquivoCobillingDecorator decorator = (SccArquivoCobillingDecorator)getItemSelecionado(request, DISPLAY_TAG_SPACE_1, form);
		myForm.setVisaoArquivo(getSumarioErrosView());
		myForm.setArquivoSelecionado(decorator.getRow());
		cacheMyForm(getClass(), myForm);
		List<SccCdrCobilling> rows = getServiceManager().getArquivosService().geraResumoCDRsComErroArquivo(myForm.getArquivoSelecionado().getSqArquivo());
		List<SccCdrCobillingDecorator> decoratorList = new ArrayList<SccCdrCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccCdrCobillingDecorator cdrDecorator = new SccCdrCobillingDecorator(rows.get(i), i);
			decoratorList.add(cdrDecorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_2, decoratorList, request);
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
	private ModelAndView listar(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(FORM_NAME) BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		cleanSession(getClass(), request, DISPLAY_TAG_SPACE_3);
		ModelAndView mav = new ModelAndView(getListaCDRsView(), "filtro", form);
		PesquisaProcessadosPosForm myForm = (PesquisaProcessadosPosForm)form;
		PesquisaProcessadosPosForm myCachedForm = (PesquisaProcessadosPosForm)getMyFormFromCache(getClass());
		SccCdrCobillingDecorator decorator = (SccCdrCobillingDecorator)getItemSelecionado(request, DISPLAY_TAG_SPACE_2, form);
		myForm.setFiltroSelecionado(decorator.getRow());
		myForm.setArquivoSelecionado(myCachedForm.getArquivoSelecionado());		
		myForm.setVisaoArquivo(myCachedForm.getVisaoArquivo());
		SccPaginatedList paginatedList = new SccPaginatedList();
		paginatedList.setObjectsPerPage(TAMANHO_PAGINA);
		paginatedList.setPageNumber(1);		
		List<SccCdrCobilling> rows = getServiceManager().getArquivosService().listaCDRsArquivo(myForm.getArquivoSelecionado().getSqArquivo(), myForm.getFiltroSelecionado(), paginatedList.getPageNumber()-1, TAMANHO_PAGINA);
		List<SccCdrCobillingDecorator> decoratorList = new ArrayList<SccCdrCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccCdrCobillingDecorator cdrDecorator = new SccCdrCobillingDecorator(rows.get(i), i);
			decoratorList.add(cdrDecorator);			
		}
		paginatedList.setList(decoratorList);
		paginatedList.setFullListSize(getServiceManager().getArquivosService().contaCDRsArquivo(myForm.getArquivoSelecionado().getSqArquivo(), myForm.getFiltroSelecionado()).intValue());
		myForm.setCdrList(paginatedList);
		storeInSession(getClass(), DISPLAY_TAG_SPACE_3, paginatedList, request);
		cacheMyForm(getClass(), myForm);
		return mav;
	}
	
	@RequestMapping(value="/pagina" , method=RequestMethod.GET)
	public ModelAndView pagina(@RequestParam("page") Integer page,HttpServletRequest request, HttpServletResponse response ) throws Exception {
		PesquisaProcessadosPosForm myForm = (PesquisaProcessadosPosForm)getMyFormFromCache(getClass());
		ModelAndView mav = new ModelAndView(getListaCDRsView());		
		SccPaginatedList paginatedList = myForm.getCdrList();
		paginatedList.setObjectsPerPage(TAMANHO_PAGINA);
		paginatedList.setPageNumber(page);
		List<SccCdrCobilling> rows = getServiceManager().getArquivosService().listaCDRsArquivo(myForm.getArquivoSelecionado().getSqArquivo(), myForm.getFiltroSelecionado(), paginatedList.getPageNumber()-1, TAMANHO_PAGINA);
		List<SccCdrCobillingDecorator> decoratorList = new ArrayList<SccCdrCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccCdrCobillingDecorator cdrDecorator = new SccCdrCobillingDecorator(rows.get(i), i);
			decoratorList.add(cdrDecorator);
			paginatedList.setList(decoratorList);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_3, paginatedList, request);
		return mav;
	}
	
	protected String getViewName() {
		return "pesquisa_arquivos_processados_pos";
	}
	
	private String getSumarioCDRsView() {
		return "pesquisa_arquivos_processados_pos_status";
	}
	
	@SuppressWarnings("unused")
	private String getDetalheCDRView() {
		return "pesquisa_arquivos_processados_pos_cdr_detail";
	}
	
	private String getSumarioErrosView() {
		return "pesquisa_arquivos_processados_pos_erro";
	}
	
	private String getSumarioProcessadosView() {
		return "pesquisa_arquivos_processados_pos_processados";
	}
	
	private String getListaCDRsView() {
		return "pesquisa_arquivos_processados_pos_cdrs";
	}
	
	protected PesquisaProcessadosPosForm getForm() {
		return new PesquisaProcessadosPosForm();
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
	/*
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadoras() throws Exception {
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

}
