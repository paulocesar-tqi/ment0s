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

import com.claro.cobillingweb.persistence.entity.SccTipoEvento;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.CadastroTipoEventoPreValidator;
import com.claro.sccweb.decorator.rownum.entity.SccTipoEventoDecorator;
import com.claro.sccweb.form.CadastroTipoEventoPreForm;

@Controller
@RequestMapping(value="/user/produto/pre/evento")
public class CadastroTipoEventoPreController extends BaseCRUDAndMethodController<CadastroTipoEventoPreForm> {

	private final CadastroTipoEventoPreValidator validator = new CadastroTipoEventoPreValidator();
	
	 
	public ModelAndView iniciar(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = super.iniciar(request, response);
		List<SccTipoEvento> rows = getServiceManager().getProdutoPrepagoService().getAllTipoEvento();
		List<SccTipoEventoDecorator> decoratorList = new ArrayList<SccTipoEventoDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{
			SccTipoEventoDecorator decorator = new SccTipoEventoDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		mav.addObject(FORM_NAME, getForm());		
		return mav;
	}
	
	 
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, CadastroTipoEventoPreForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}

	 
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, CadastroTipoEventoPreForm form,BindingResult bindingResult, Model model) throws Exception {
		return null;
	}

	 
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, CadastroTipoEventoPreForm form,BindingResult bindingResult, Model model) throws Exception {
		SccTipoEvento entity = form.getEntity();
		if(entity != null && entity.getFgAtribuirProdPadrao() == null){
			entity.setFgAtribuirProdPadrao("N");
		}
		entity.getFgAtribuirProdPadrao();
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		getServiceManager().getProdutoPrepagoService().create(entity);
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, CadastroTipoEventoPreForm form,BindingResult bindingResult, Model model) throws Exception {
		SccTipoEventoDecorator decorator = (SccTipoEventoDecorator)form.getEntidadeSelecionada();
		getServiceManager().getProdutoPrepagoService().delete(decorator.getRow());
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, CadastroTipoEventoPreForm form,BindingResult bindingResult, Model model) throws Exception 
	{		
		form.getEntity().setDtAtualizacao(Calendar.getInstance().getTime());
		form.getEntity().setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		if(form.getEntity() != null && form.getEntity().getFgAtribuirProdPadrao() == null){
			form.getEntity().setFgAtribuirProdPadrao("N");
		}

		getServiceManager().getProdutoPrepagoService().update(form.getEntity());
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, CadastroTipoEventoPreForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccTipoEventoDecorator decorator = (SccTipoEventoDecorator)form.getEntidadeSelecionada();
		form.setEntity(decorator.getRow());
		mav.addObject(FORM_NAME, form);
		return mav;
	}

	 
	protected String getViewName() {		
		return "produtos_pre_evento";
	}

	 
	protected CadastroTipoEventoPreForm getForm() {
		return new CadastroTipoEventoPreForm();
	}

	 
	protected Validator getValidator() {		
		return this.validator;
	}

	 
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccTipoEventoDecorator decorator = (SccTipoEventoDecorator)entidadeSelecionada;
		return decorator.getRow().getId();
	}

	 
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {	
		List<SccTipoEvento> rows = getServiceManager().getProdutoPrepagoService().getAllTipoEvento();
		List<SccTipoEventoDecorator> decoratorList = new ArrayList<SccTipoEventoDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{
			SccTipoEventoDecorator decorator = new SccTipoEventoDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);		
	}

	@ModelAttribute("categorias")
	public List<BasicStringItem> populaCategorias()
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("A", "Assinaturas"));
		comboList.add(new BasicStringItem("C", "Chamadas"));
		return comboList;
	}
	
	
}
