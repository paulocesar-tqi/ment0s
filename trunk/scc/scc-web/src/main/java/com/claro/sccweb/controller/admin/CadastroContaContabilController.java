package com.claro.sccweb.controller.admin;

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

import com.claro.cobillingweb.persistence.entity.SccContaContabil;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.CadastroContaContabilValidator;
import com.claro.sccweb.decorator.rownum.entity.SccContaContabilDecorator;
import com.claro.sccweb.form.CadastroContaContabilForm;

@Controller
@RequestMapping(value="/user/admin/contas")
public class CadastroContaContabilController extends BaseCRUDAndMethodController<CadastroContaContabilForm> {
	
	private final CadastroContaContabilValidator validator = new CadastroContaContabilValidator();
	
	public ModelAndView iniciar(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		super.iniciar(request, response);
		List<SccContaContabil> rows = getServiceManager().getAdminService().getAllContaContabil();
		List<SccContaContabilDecorator> decoratorList = new ArrayList<SccContaContabilDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccContaContabilDecorator decorator = new SccContaContabilDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), getForm());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, CadastroContaContabilForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, CadastroContaContabilForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
	}
	
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, CadastroContaContabilForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		form.getEntity().setDtCriacao(Calendar.getInstance().getTime());
		form.getEntity().setCdUsuario(getSessionDataManager().getUserPrincipal());
		getServiceManager().getAdminService().create(form.getEntity());
		return mav;
	}
	
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, CadastroContaContabilForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccContaContabilDecorator decorator = (SccContaContabilDecorator)form.getEntidadeSelecionada();
		getServiceManager().getAdminService().delete(decorator.getRow());
		return mav;
	}
	
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, CadastroContaContabilForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccContaContabil entity = form.getEntity();		
		entity.setDtAtualizacao(Calendar.getInstance().getTime());
		entity.setCdUsuario(getSessionDataManager().getUserPrincipal());
		entity.setCdConta((Long)getPkEntidade(getItem(request, form)));
		getServiceManager().getAdminService().update(entity);
		return mav;
	}
	
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, CadastroContaContabilForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		int itemIndex = form.getItemSelecionado();
		SccContaContabilDecorator decorator = ((List<SccContaContabilDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		SccContaContabil entity = decorator.getRow();
		form.setEntity(entity);
		mav.addObject("filtro",form);
		return mav;
	}
	
	protected String getViewName() {	
		return "conta_contabil_filtro";
	}
	
	protected CadastroContaContabilForm getForm() {		
		return new CadastroContaContabilForm();
	}
	
	protected Validator getValidator() {		
		return this.validator;
	}
	
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccContaContabilDecorator decorator = (SccContaContabilDecorator)entidadeSelecionada;
		return decorator.getRow().getCdConta();
	}
	
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		List<SccContaContabil> rows = getServiceManager().getAdminService().getAllContaContabil();
		List<SccContaContabilDecorator> decoratorList = new ArrayList<SccContaContabilDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{
			SccContaContabilDecorator decorator = new SccContaContabilDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
	}
	
	@ModelAttribute("status")
	public List<BasicStringItem> populaStatus()
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("A", "Ativa"));
		comboList.add(new BasicStringItem("C", "Cancelada"));
		return comboList;
				
	}
	
	@ModelAttribute("centro")
	public List<BasicStringItem> populaCentro()
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("NE", "Nenhum"));
		comboList.add(new BasicStringItem("CC", "Centro de Custo"));
		comboList.add(new BasicStringItem("CL", "Centro de Lucro"));
		return comboList;
				
	}
	
}
