package com.claro.sccweb.controller.produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.CadastroProdutoPreValidator;

import com.claro.sccweb.decorator.rownum.entity.SccProdutoPrepagoDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.CadastroProdutoPreForm;

@Controller
@RequestMapping(value="/user/produto/pre/cadastro")
public class CadastroProdutoPreController extends BaseCRUDAndMethodController<CadastroProdutoPreForm>{

	private CadastroProdutoPreValidator validator = new CadastroProdutoPreValidator();
	
	 
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, CadastroProdutoPreForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		form = getForm();
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	 
	public ModelAndView iniciar(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccProdutoPrepago> rows = getServiceManager().getProdutoPrepagoService().getAll();
		List<SccProdutoPrepagoDecorator> decoratorList = new ArrayList<SccProdutoPrepagoDecorator>();
		for (int i=0;i<rows.size();i++)
			{
			SccProdutoPrepagoDecorator decorator = new SccProdutoPrepagoDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		CadastroProdutoPreForm form = getForm();
		mav.addObject(FORM_NAME, form);
		return mav;
	}

	 
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, CadastroProdutoPreForm form,BindingResult bindingResult, Model model) throws Exception {
		return null;
	}

	 
		protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, CadastroProdutoPreForm form,BindingResult bindingResult, Model model) throws Exception {
		SccProdutoPrepago entity = form.getEntity();
		if(entity != null && entity.getFgCobrarPrestacaoServico() == null)
        {
               entity.setFgCobrarPrestacaoServico("N");
        }
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		getServiceManager().getProdutoPrepagoService().create(entity);
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, CadastroProdutoPreForm form,BindingResult bindingResult, Model model) throws Exception {
		SccProdutoPrepagoDecorator decorator = (SccProdutoPrepagoDecorator)form.getEntidadeSelecionada();
		getServiceManager().getProdutoPrepagoService().delete(decorator.getRow());
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, CadastroProdutoPreForm form,BindingResult bindingResult, Model model) throws Exception {
		if(form.getEntity().getFgCobrarPrestacaoServico() == null)
        {
			form.getEntity().setFgCobrarPrestacaoServico("N");
        }
		SccProdutoPrepago entity = form.getEntity();
		entity.setDtAtualizacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		getServiceManager().getProdutoPrepagoService().update(entity);
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, CadastroProdutoPreForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccProdutoPrepagoDecorator decorator = (SccProdutoPrepagoDecorator)form.getEntidadeSelecionada();
		form.setEntity(decorator.getRow());
		mav.addObject(FORM_NAME, form);
		return mav;
	}

	 
	protected String getViewName() {		
		return "produtos_contrato_pre_cadastro";
	}

	 
	protected CadastroProdutoPreForm getForm() {
		return new CadastroProdutoPreForm();
	}

	 
	protected Validator getValidator() {
		return this.validator;
	}

	 
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccProdutoPrepagoDecorator decorator = (SccProdutoPrepagoDecorator)entidadeSelecionada;
		return decorator.getRow().getCdProdutoPrepago();
	}

	 
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {		
		List<SccProdutoPrepago> rows = getServiceManager().getProdutoPrepagoService().getAll();
		List<SccProdutoPrepagoDecorator> decoratorList = new ArrayList<SccProdutoPrepagoDecorator>();
		for (int i=0;i<rows.size();i++)
			{
			SccProdutoPrepagoDecorator decorator = new SccProdutoPrepagoDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		
	}

	public ModelAndView novo(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView(getViewName());
		CadastroProdutoPreForm form = new CadastroProdutoPreForm();
		form.setOperacao("novo");
		form.setEntity(new SccProdutoPrepago());
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
}
