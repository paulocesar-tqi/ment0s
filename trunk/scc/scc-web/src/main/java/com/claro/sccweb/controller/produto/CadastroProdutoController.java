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

import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.CadastroProdutoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccProdutoCobillingDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.CadastroProdutoForm;

@Controller
@RequestMapping(value="/user/produto/cadastro")
public class CadastroProdutoController extends BaseCRUDAndMethodController<CadastroProdutoForm>{

	private final CadastroProdutoValidator validator = new CadastroProdutoValidator();
	
	 
	public ModelAndView iniciar(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav =  super.iniciar(request, response);
		List<SccProdutoCobilling> rows = getServiceManager().getProdutoService().getAll();
		List<SccProdutoCobillingDecorator> decoratorList = new ArrayList<SccProdutoCobillingDecorator>();
		for (int i=0;i<rows.size();i++)
			{
			SccProdutoCobillingDecorator decorator = new SccProdutoCobillingDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		return mav;
	}
	 
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, CadastroProdutoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		form = getForm();
		form.setEntity(new SccProdutoCobilling());
		mav.addObject(FORM_NAME, form);
		return mav;
	}

	 
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, CadastroProdutoForm form,BindingResult bindingResult, Model model) throws Exception {		
		return null;
	}

	 
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, CadastroProdutoForm form,BindingResult bindingResult, Model model) throws Exception {		
		form.getEntity().setDtCriacao(Calendar.getInstance().getTime());
		form.getEntity().setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		getServiceManager().getProdutoService().create(form.getEntity());				
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, CadastroProdutoForm form,BindingResult bindingResult, Model model) throws Exception {
		SccProdutoCobillingDecorator decorator = (SccProdutoCobillingDecorator)form.getEntidadeSelecionada();
		getServiceManager().getProdutoService().delete(decorator.getRow());				
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, CadastroProdutoForm form,BindingResult bindingResult, Model model) throws Exception {
		form.getEntity().setDtAtualizacao(Calendar.getInstance().getTime());
		form.getEntity().setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		getServiceManager().getProdutoService().update(form.getEntity());				
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, CadastroProdutoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccProdutoCobillingDecorator decorator = (SccProdutoCobillingDecorator)form.getEntidadeSelecionada();
		form.setEntity(decorator.getRow());
		mav.addObject(FORM_NAME, form);
		return mav;
	}

	 
	protected String getViewName() {
		return "produto_pos_cadastro";
	}

	 
	protected CadastroProdutoForm getForm() {		
		return new CadastroProdutoForm();
	}

	 
	protected Validator getValidator() {	
		return this.validator;
	}

	 
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccProdutoCobillingDecorator decorator = (SccProdutoCobillingDecorator)entidadeSelecionada;
		return decorator.getRow().getCdProdutoCobilling();		
	}

	 
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		List<SccProdutoCobilling> rows = getServiceManager().getProdutoService().getAll();
		List<SccProdutoCobillingDecorator> decoratorList = new ArrayList<SccProdutoCobillingDecorator>();
		for (int i=0;i<rows.size();i++)
			{
			SccProdutoCobillingDecorator decorator = new SccProdutoCobillingDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
	}

	public ModelAndView novo(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView(getViewName());
		CadastroProdutoForm form = new CadastroProdutoForm();
		form.setOperacao("novo");
		form.setEntity(new SccProdutoCobilling());
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	
	
}
