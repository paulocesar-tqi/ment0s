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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccComposicaoProduto;
import com.claro.cobillingweb.persistence.entity.SccItemCobilling;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.ComposicaoProdutoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccComposicaoProdutoDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.ComposicaoProdutoForm;


/**
 * Cadastro de item de produto.
 * 
 *
 */
@Controller
@RequestMapping(value="/user/produto/item")
public class ComposicaoProdutoController extends BaseCRUDAndMethodController<ComposicaoProdutoForm> {
	
	private final ComposicaoProdutoValidator validator = new ComposicaoProdutoValidator();
	
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, ComposicaoProdutoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, ComposicaoProdutoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccComposicaoProduto> rows = getServiceManager().getProdutoService().carregaComposicaoProduto(form.getCdProdutoCobilling());
		List<SccComposicaoProdutoDecorator> decoratorList = new ArrayList<SccComposicaoProdutoDecorator>();
		if (rows != null) {
			for (int i=0;i<rows.size();i++) {
				SccComposicaoProdutoDecorator decorator = new SccComposicaoProdutoDecorator(rows.get(i), i);
				decoratorList.add(decorator);
			}
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		}
		SccProdutoCobilling produtoCobilling = getServiceManager().getProdutoService().getByPk(form.getCdProdutoCobilling());
		form.setProdutoCobilling(produtoCobilling);
		cacheMyForm(getClass(), form);
		return mav;
	}
	
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, ComposicaoProdutoForm form,BindingResult bindingResult, Model model) throws Exception {
		ComposicaoProdutoForm cachedForm = (ComposicaoProdutoForm)getMyFormFromCache(getClass());
		SccComposicaoProduto entity = form.getEntity();
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		getServiceManager().getProdutoService().create(entity);
		return limpar(request, response, cachedForm, bindingResult, model);
	}
	
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, ComposicaoProdutoForm form,BindingResult bindingResult, Model model) throws Exception {
		SccComposicaoProduto entity = ((SccComposicaoProdutoDecorator)form.getEntidadeSelecionada()).getRow();
		getServiceManager().getProdutoService().delete(entity);
		return limpar(request, response, form, bindingResult, model);
	}
	
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, ComposicaoProdutoForm form,BindingResult bindingResult, Model model) throws Exception {
		SccComposicaoProduto entity = form.getEntity();
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		entity.setDtAtualizacao(Calendar.getInstance().getTime());
		entity.setSccProdutoCobilling(new SccProdutoCobilling());
		entity.getSccProdutoCobilling().setCdProdutoCobilling(form.getCdProdutoCobilling());
		getServiceManager().getProdutoService().update(entity);		
		return limpar(request, response, form, bindingResult, model);
	}
	
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, ComposicaoProdutoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccComposicaoProdutoDecorator decorator = (SccComposicaoProdutoDecorator)form.getEntidadeSelecionada();
		form.setEntity(decorator.getRow());
		form.setCdProdutoCobilling(decorator.getRow().getSccProdutoCobilling().getCdProdutoCobilling());
		return mav;
	}
	
	public ModelAndView novo(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		ComposicaoProdutoForm form = (ComposicaoProdutoForm)_form;
		form.setOperacao("novo");
		form.setEntity(new SccComposicaoProduto());
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	protected String getViewName() {
		return "produto_pos_item_cadastro";
	}
	
	protected ComposicaoProdutoForm getForm() {
		return new ComposicaoProdutoForm();
	}
	
	protected Validator getValidator() {
		return this.validator;
	}
	
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccComposicaoProdutoDecorator decorator = (SccComposicaoProdutoDecorator)entidadeSelecionada;
		return decorator.getRow().getCdComponenteProduto();
	}
	
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		ComposicaoProdutoForm form = (ComposicaoProdutoForm)getMyFormFromCache(getClass());
		if (form != null) /*Se o cached form é nulo , significa que não houve pesquisa e por isso não há o que atualizar.*/ {
			List<SccComposicaoProduto> rows = getServiceManager().getProdutoService().carregaComposicaoProduto(form.getCdProdutoCobilling());
			List<SccComposicaoProdutoDecorator> decoratorList = new ArrayList<SccComposicaoProdutoDecorator>();
			if (rows != null) {
				for (int i=0;i<rows.size();i++) {
					SccComposicaoProdutoDecorator decorator = new SccComposicaoProdutoDecorator(rows.get(i), i);
					decoratorList.add(decorator);
				}
				storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
			}
		}
	}
	
	@ModelAttribute("itemsCobilling")
	public List<SccItemCobilling> populaItemsCobilling() throws Exception {
		return getServiceManager().getProdutoService().getAllItem();
	}
	
	@ModelAttribute("produtos")
	public List<SccProdutoCobilling> populaProdutosCobilling() throws Exception {
		return getServiceManager().getProdutoService().getAll();
	}
	
}
