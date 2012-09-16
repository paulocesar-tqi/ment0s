package com.claro.sccweb.controller.admin;

import java.io.Serializable;
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

import com.claro.cobillingweb.persistence.entity.SccPreDominio;
import com.claro.cobillingweb.persistence.entity.SccPreDominioPK;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.CadastroPreDominioValidator;
import com.claro.sccweb.decorator.rownum.entity.SccPreDominioDecorator;
import com.claro.sccweb.form.CadastroPreDominioForm;

@Controller
@RequestMapping(value="/user/admin/dominio")
public class CadastroPreDominioController extends BaseCRUDAndMethodController<CadastroPreDominioForm> {

	private final CadastroPreDominioValidator validator = new CadastroPreDominioValidator();
	
	 
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, CadastroPreDominioForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
		
	}

	 
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, CadastroPreDominioForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccPreDominio> rows = getServiceManager().getPesquisaDominiosService().pesquisaPorTipoDominioPre(form.getTpDominio());
		List<SccPreDominioDecorator> decoratorList = new ArrayList<SccPreDominioDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{
			SccPreDominioDecorator decorator = new SccPreDominioDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), form);
		return mav;
	}

	 
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, CadastroPreDominioForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccPreDominio entity = form.getEntity();
		getServiceManager().getAdminService().create(entity);
		return mav;
	}

	 
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, CadastroPreDominioForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccPreDominioDecorator decorator = (SccPreDominioDecorator)form.getEntidadeSelecionada();
		getServiceManager().getAdminService().delete(decorator.getRow());
		return mav;
	}

	 
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, CadastroPreDominioForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccPreDominio entity = form.getEntity();
		entity.setId((SccPreDominioPK)getPkEntidade(getItem(request, form)));
		getServiceManager().getAdminService().update(entity);
		return mav;
	}

	 
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, CadastroPreDominioForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		int itemIndex = form.getItemSelecionado();
		SccPreDominioDecorator decorator = ((List<SccPreDominioDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		SccPreDominio entity = decorator.getRow();
		form.setEntity(entity);
		mav.addObject("filtro",form);
		return mav;
	}

	 
	protected String getViewName() {
		return "pre_dominio_form";
	}

	 
	protected CadastroPreDominioForm getForm() {
		return new CadastroPreDominioForm();
	}

	 
	protected Validator getValidator() {
		return validator;
	}

	 
	protected Serializable getPkEntidade(Object entidadeSelecionada) {		
		SccPreDominioDecorator decorator = (SccPreDominioDecorator)entidadeSelecionada;
		return decorator.getRow().getId();		
	}

	 
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		CadastroPreDominioForm form = (CadastroPreDominioForm)getMyFormFromCache(getClass());
		List<SccPreDominio> rows = getServiceManager().getPesquisaDominiosService().pesquisaPorTipoDominioPre(form.getTpDominio());
		List<SccPreDominioDecorator> decoratorList = new ArrayList<SccPreDominioDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{
			SccPreDominioDecorator decorator = new SccPreDominioDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		
	}

	
	@ModelAttribute("tipos")
	public List<BasicStringItem> populaTipos() throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("MTREJ","MTREJ"));
		comboList.add(new BasicStringItem("TPARQ","TPARQ"));
		comboList.add(new BasicStringItem("TPCHM","TPCHM"));
		comboList.add(new BasicStringItem("STASS","STASS"));
		comboList.add(new BasicStringItem("PRCHM","PRCHM"));
		comboList.add(new BasicStringItem("STCRD","STCRD"));
		comboList.add(new BasicStringItem("STLZS","STLZS"));
		comboList.add(new BasicStringItem("CDORG","CDORG"));
		comboList.add(new BasicStringItem("STCAR","STCAR"));
		comboList.add(new BasicStringItem("STCHM","STCHM"));
		comboList.add(new BasicStringItem("DEFCH","DEFCH"));
		comboList.add(new BasicStringItem("STARQ","STARQ"));
		return comboList;
	}
	
}
