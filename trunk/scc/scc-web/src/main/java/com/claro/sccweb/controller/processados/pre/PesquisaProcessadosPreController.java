package com.claro.sccweb.controller.processados.pre;

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
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccStatusArquivo;
import com.claro.cobillingweb.persistence.entity.SccTipoArquivo;
import com.claro.cobillingweb.persistence.entity.external.ViewArquivoPrePago;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.SearchResultList;
import com.claro.sccweb.controller.validator.PesquisarProcessadosPreValidator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCobillingDecorator;
import com.claro.sccweb.decorator.rownum.view.ViewArquivoPrePagoDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.PesquisaProcessadosPreForm;


/**
 * Funcionalidade : Arquivo -> Processados
 * Módulo : Pré-Pago 
 *
 * Realiza pesquisa de arquivos pré-pago processados.
 */
@Controller
@RequestMapping(value="/user/pre/processados/pesquisa")
public class PesquisaProcessadosPreController extends BaseOperationController<PesquisaProcessadosPreForm> {

	private PesquisarProcessadosPreValidator validator = new PesquisarProcessadosPreValidator();

	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception
	{
		PesquisaProcessadosPreForm form = (PesquisaProcessadosPreForm)_form;
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccArquivoCobilling> arquivos = getServiceManager().getArquivosService().pesquisaArquivosPrePago(form.getPesquisa());	
		List<SccArquivoCobillingDecorator> decoratorList = new ArrayList<SccArquivoCobillingDecorator>(arquivos.size());
		for (int i=0;i<arquivos.size();i++)
			{
			SccArquivoCobillingDecorator decorator = new SccArquivoCobillingDecorator(arquivos.get(i), i);
			decoratorList.add(decorator);
			}
		cacheMyForm(this.getClass(), form);		
		SearchResultList searchResultList = new SearchResultList();
		searchResultList.setResult(arquivos);
		searchResultList.setResultClassType(SccArquivoCobilling.class);
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);			
		mav.setViewName(getViewName());
		return mav;
	}

	public ModelAndView voltar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception
	{
		cleanSession(getClass(), request, DISPLAY_TAG_SPACE_2);
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getMyFormFromCache(getClass()));
		return mav;		
	}
	
	public ModelAndView selecionar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView("pesquisa_processados_pre_detalhes");
		PesquisaProcessadosPreForm form = (PesquisaProcessadosPreForm)_form;
		List<SccArquivoCobillingDecorator> tabela = (List<SccArquivoCobillingDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);		
		SccArquivoCobillingDecorator decorator = tabela.get(form.getItemSelecionado());
		SccArquivoCobilling arquivoSelecionado = decorator.getRow();
		List<ViewArquivoPrePago> detalhes =  getServiceManager().getArquivosService().carregaDetalhesArquivoPrePago(arquivoSelecionado.getSqArquivo());
		List<ViewArquivoPrePagoDecorator> decoratorList = new ArrayList<ViewArquivoPrePagoDecorator>(detalhes.size());
		for (int i=0;i<detalhes.size();i++)
			{
			ViewArquivoPrePagoDecorator decoratorItem = new ViewArquivoPrePagoDecorator(detalhes.get(i), i);
			decoratorList.add(decoratorItem);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_2, decoratorList, request);				
		mav.setViewName("pesquisa_processados_pre_detalhes");
		return mav;
	}
	
	 
	protected String getViewName() {		
		return "pesquisa_processados_pre_filtro";
	}

	 
	protected PesquisaProcessadosPreForm getForm() {		
		return new PesquisaProcessadosPreForm();
	}

	 
	protected Validator getValidator() {		
		return this.validator;
	}
	
	
	
	@ModelAttribute("tiposArquivo")
	public List<SccTipoArquivo> populaTiposArquivo() throws Exception
	{			
		List<SccTipoArquivo> comboList = new ArrayList<SccTipoArquivo>();
		SccTipoArquivo sccTipoArquivo = new SccTipoArquivo();
		sccTipoArquivo.setCdTipoArquivo(BasicDAO.GET_ALL);
		sccTipoArquivo.setDsTipoArquivo("Todos");
		comboList.add(sccTipoArquivo);
		comboList.addAll(getServiceManager().getArquivosService().pesquisaTiposArquivoPrePago());
		return comboList;		
	}
	
	@ModelAttribute("statusArquivo")
	public List<SccStatusArquivo> populaStatusArquivo() throws Exception
	{
		List<SccStatusArquivo> comboList = new ArrayList<SccStatusArquivo>();
		SccStatusArquivo sccStatusArquivo = new SccStatusArquivo();
		sccStatusArquivo.setCdStatusArquivo(BasicDAO.GET_ALL_STRING);
		sccStatusArquivo.setDsStatusArquivo("Todos");
		comboList.add(sccStatusArquivo);
		comboList.addAll(getServiceManager().getArquivosService().pesquisaStatusArquivoPrePago());
		return comboList;
	}
	
	
}
