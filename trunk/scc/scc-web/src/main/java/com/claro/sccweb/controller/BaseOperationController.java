package com.claro.sccweb.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.sccweb.form.BaseForm;

public abstract class BaseOperationController<E extends BaseForm> extends BaseFormController {
	
	public static final String FORM_NAME = "filtro";
	
	@InitBinder
	
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sccDateFormat, true));
		binder.setValidator(getValidator());
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
		cleanMyFormCache(getClass());
		cleanSession(getClass(), request);
		mav.addObject(FORM_NAME,getForm());
		return mav;	 
	}
	
	@RequestMapping(value="/back") 
	public  ModelAndView  voltar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
	}
	
	@RequestMapping(value="/execute")
	public ModelAndView executar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  E form,BindingResult bindingResult,Model model) throws Exception {
		ModelAndView mav = null;
		if (bindingResult.hasErrors()) {
			info("Erros de validação ao executar "+form.getOperacao()+" em "+getClass().getName());
			printBindingErrors(bindingResult.getAllErrors());
			mav = new ModelAndView(getViewName());
		} else {
			info("Executando "+form.getOperacao()+" em "+getClass().getName());
			//cacheMyForm(getClass(), form);		 
			Method method = getClass().getMethod(form.getOperacao(), HttpServletRequest.class,HttpServletResponse.class,BaseForm.class,BindingResult.class,Model.class);
			mav = (ModelAndView)method.invoke(this, request,response,form,bindingResult,model);
		}
		return mav;
	}
	
	@RequestMapping(value="/tab1" , method = RequestMethod.GET)
	public ModelAndView tab1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());			
		mav.addObject(FORM_NAME, getMyFormFromCache(getClass()));
		return mav;  
	}
	
	protected ModelAndView cleanForm() {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	protected ModelAndView cleanAll(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		cleanDisplayTag(request);
		cleanMyFormCache(getClass());
		return mav;
	}
	
	protected abstract String getViewName();	 
	
	protected abstract E getForm(); 
	
	protected abstract Validator getValidator();
	
	protected List<SccProdutoCobilling> populaProdutos(boolean mandatorio) throws Exception {
		List<SccProdutoCobilling> comboList = getServiceManager().getContratoService().listaTodosProdutos();
		if (!mandatorio) {
			SccProdutoCobilling allvalues = new SccProdutoCobilling();
			allvalues.setCdProdutoCobilling(BasicDAO.GET_ALL);
			allvalues.setNoProdutoCobilling("Todos");
			comboList.add(allvalues);
		}			
		return comboList;
	}
	
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro(boolean mandatorio) throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		if (!mandatorio) {
			SccOperadora allValues = new SccOperadora();
			allValues.setCdEot(BasicDAO.GET_ALL_STRING);
			allValues.setDsOperadora("Todas");
			comboList.add(0,allValues);
		}
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaro());
		return comboList;
	}
	
	protected Object getItemSelecionado(HttpServletRequest request,String tableName,BaseForm form) {
		if (request.getSession().getAttribute(tableName) != null) {
			List tableData = (List)request.getSession().getAttribute(tableName);
			return tableData.get(form.getItemSelecionado());
		}
		return null;
	}
	
}
