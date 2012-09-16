package com.claro.sccweb.controller;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.form.BaseForm;

/**
 * Template de controller para funcionalidades de CRUD.
 * Caso a operação não seja prevista no CRUD , o controller irá utilizar reflection para
 * encontrar um métode para handler.
 *
 */
public abstract class BaseCRUDAndMethodController<E extends BaseForm> extends BaseFormController {

	public static final String OPERACAO_CREATE = "CRUD.salvar";
	public static final String OPERACAO_DELETE = "CRUD.remover";
	public static final String OPERACAO_DELETE_FROM_TABLE = "CRUD.remover_tabela";
	public static final String OPERACAO_UPDATE = "CRUD.atualizar";
	public static final String OPERACAO_RETRIEVE = "CRUD.carregar";
	public static final String OPERACAO_EDIT = "CRUD.editar";
	public static final String OPERACAO_SEARCH = "CRUD.pesquisar";
	public static final String OPERACAO_CLEAN = "CRUD.limpar"; 	
	public static final String OPERACAO_NOVO = "CRUD.novo"; 
	public static final String OPERACAO_ERRO = "CRUD.erro";
	
	public static final String FORM_NAME = "filtro";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(getValidator());
		super.initBinder(binder);
	}
	
	/**
	 * Mapear esse método para a entrada da página. @RequestMapping(value="/new")
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/new") 
	public  ModelAndView  iniciar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ModelAndView mav = new ModelAndView(getViewName());
		 cleanDisplayTag(request);
		 mav.addObject(FORM_NAME,getForm());
		 return mav;	 
	 }	 
	
	@RequestMapping(value="/execute")
	 public ModelAndView executar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  E form,BindingResult bindingResult,Model model) throws Exception
	 {
		ModelAndView mav = null;
		 if (bindingResult.hasErrors())
		 {
			 if(form.getOperacao().equals(OPERACAO_SEARCH)){
				 form.setActiveTab("0");
			 }else{
				 form.setActiveTab("1");	 
			 }
			 mav = new ModelAndView(getViewName());
		 } else if (form.getOperacao().equals(OPERACAO_CREATE))
		 {
			 mav = preSalvar(request, response, form, bindingResult, model);
			 atualizarDadosTabela(request);
		 } else if (form.getOperacao().equals(OPERACAO_DELETE))
		 {
			 mav = remover(request, response, form, bindingResult, model);
		 } else if (form.getOperacao().equals(OPERACAO_SEARCH))
		 {
			cacheMyForm(getClass(), form);
			mav = pesquisar(request, response, form, bindingResult, model); 
		 	}
		 else if (form.getOperacao().equals(OPERACAO_UPDATE))
		 	{
			mav = atualizar(request, response, form, bindingResult, model); 
			atualizarDadosTabela(request);
		 	}
		 else if (form.getOperacao().equals(OPERACAO_EDIT))
		 	{
			mav = preEditar(request, response, form, bindingResult, model); 
		 	}
		 else if (form.getOperacao().equals(OPERACAO_DELETE_FROM_TABLE))
		 	{
			mav = preRemoverTabela(request, response, form, bindingResult, model); 
			atualizarDadosTabela(request);
		 	}
		 else if (form.getOperacao().equals(OPERACAO_CLEAN))
		 	{
			 mav = limpar(request, response, form, bindingResult, model);
		 	}
		 else if (form.getOperacao().equals(OPERACAO_NOVO))
		 {
			 mav = novo(request, response, form, bindingResult, model);
		 }
		 else
		 	{
			 Method method = getClass().getMethod(form.getOperacao(), HttpServletRequest.class,HttpServletResponse.class,BaseForm.class,BindingResult.class,Model.class);
			 mav = (ModelAndView)method.invoke(this, request,response,form,bindingResult,model);			 
		 	}
		 return mav;
	 }
	
	@RequestMapping(value="/tab1" , method = RequestMethod.GET)
	public ModelAndView tab1(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView(getViewName());
			Object form = getMyFormFromCache(getClass());
			if (form != null)
				mav.addObject(FORM_NAME, form);
			else
				mav.addObject(FORM_NAME, getForm());
	    	return mav;  
	}
	
	public ModelAndView novo(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView(getViewName());
		form = getForm();
		form.setOperacao(OPERACAO_NOVO);		
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	 private ModelAndView preEditar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  E form,BindingResult bindingResult,Model model) throws Exception
	 {
		 Object item = getItem(request, form);
		 form.setEntidadeSelecionada(item);
		 form.setPkEntidadeSelecionada(getPkEntidade(item));
		 return editar(request, response, form, bindingResult, model);
	 }
	 
	 private ModelAndView preSalvar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  E form,BindingResult bindingResult,Model model) throws Exception
	 {		 
		 if (alreadyExists(form))
		 	{
			 return new ModelAndView(getViewName());
		 	}
		 else
		 	{
			 return salvar(request, response, form, bindingResult, model);
		 	}	 
	 }
	 
	 
	 private ModelAndView preRemoverTabela(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  E form,BindingResult bindingResult,Model model) throws Exception
	 {
		 Object item = getItem(request, form);
		 form.setEntidadeSelecionada(item);
		 form.setPkEntidadeSelecionada(getPkEntidade(item));
		 return remover(request, response, form, bindingResult, model);
	 }
	
	 protected abstract ModelAndView limpar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  E form,BindingResult bindingResult,Model model) throws Exception;
	 
	 protected abstract ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  E form,BindingResult bindingResult,Model model) throws Exception;
	 
	 protected abstract ModelAndView salvar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  E form,BindingResult bindingResult,Model model) throws Exception;
	 
	 protected abstract ModelAndView remover(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  E form,BindingResult bindingResult,Model model) throws Exception;
	 
	 protected abstract ModelAndView atualizar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  E form,BindingResult bindingResult,Model model) throws Exception;
	 
	 protected abstract ModelAndView editar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  E form,BindingResult bindingResult,Model model) throws Exception;
	 
	 protected abstract String getViewName();	 
	 
	 protected abstract E getForm(); 
	 
	 protected abstract Validator getValidator();
	 
	 protected abstract Serializable getPkEntidade(Object entidadeSelecionada);
	 
	 /**
	  * Sobreescrever em caso de PK não ser suficiente para identificar duplicidade.
	  * @param form
	  * @return
	  */
	 protected  boolean alreadyExists(E form)
	 {
		 return false;
	 }
	 
	 protected ModelAndView atualizarResultados(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  E form,BindingResult bindingResult,Model model) throws Exception
	 {
		 E cachedForm = (E)getMyFormFromCache(getClass());
		 return pesquisar(request, response, cachedForm, bindingResult, model);		 
	 }
	 
	 
	 protected Object getItem(HttpServletRequest request,E form)
	 {
		 Integer index = form.getItemSelecionado();		 
		 List tabela =  (List)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		 if ((tabela != null) && (tabela.size() >= index))
		 	return tabela.get(index);
		 else
			 return null;
	 }
	 
	 
	 protected abstract void atualizarDadosTabela(HttpServletRequest request) throws Exception;
	 
	 public ModelAndView cancelar(HttpServletRequest request,HttpServletResponse response, BaseForm form,BindingResult bindingResult, Model model) throws Exception {
			return iniciar(request, response);
		
		}
	 
	
		protected List<SccOperadora> _populaOperadorasClaro(boolean mandatorio) throws Exception
		{
			List<SccOperadora> comboList = new ArrayList<SccOperadora>();
			if (!mandatorio)
				{
				SccOperadora allValues = new SccOperadora();
				allValues.setCdEot(BasicDAO.GET_ALL_STRING);
				allValues.setDsOperadora("Todas");
				comboList.add(0,allValues);
				}
			comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaro());
			return comboList;
		}
}
