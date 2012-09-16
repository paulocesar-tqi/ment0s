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

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccMapaStatus;
import com.claro.cobillingweb.persistence.entity.SccMapaStatusPK;
import com.claro.cobillingweb.persistence.entity.SccStatusCdr;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.CadastroMapaStatusValidator;
import com.claro.sccweb.decorator.rownum.entity.SccMapaStatusDecorator;
import com.claro.sccweb.form.CadastroMapaStatusForm;

@Controller
@RequestMapping(value="/user/admin/mapa")
public class CadastroMapaStatusController extends BaseCRUDAndMethodController<CadastroMapaStatusForm> {

	private final CadastroMapaStatusValidator validator = new CadastroMapaStatusValidator();
	
	 
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, CadastroMapaStatusForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}

	 
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, CadastroMapaStatusForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccMapaStatus> rows = getServiceManager().getAdminService().pesquisaMapaStatus(form.getFiltroDe(), form.getFiltroPara());
		List<SccMapaStatusDecorator> decoratorList = new ArrayList<SccMapaStatusDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{
			SccMapaStatusDecorator decorator = new SccMapaStatusDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), form);
		mav.addObject(FORM_NAME, form);
		return mav;
	}

	 
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, CadastroMapaStatusForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccMapaStatus entity = form.getEntity();
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		getServiceManager().getAdminService().create(entity);		
		return mav;
	}

	 
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, CadastroMapaStatusForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccMapaStatusDecorator decorator = (SccMapaStatusDecorator)form.getEntidadeSelecionada();
		getServiceManager().getAdminService().delete(decorator.getRow());
		return mav;
	}

	 
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, CadastroMapaStatusForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccMapaStatus entity = form.getEntity();
		entity.setDtAtualizacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		entity.setId((SccMapaStatusPK)getPkEntidade(getItem(request, form)));
		getServiceManager().getAdminService().update(entity);		
		return mav;
	}

	 
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, CadastroMapaStatusForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		int itemIndex = form.getItemSelecionado();
		SccMapaStatusDecorator decorator = ((List<SccMapaStatusDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		SccMapaStatus entity = decorator.getRow();
		form.setEntity(entity);
		mav.addObject("filtro",form);
		return mav;
	}

	 
	protected String getViewName() {
		return "mapa_status_filtro";
	}

	 
	protected CadastroMapaStatusForm getForm() {		
		return new CadastroMapaStatusForm();
	}

	 
	protected Validator getValidator() {
		return this.validator;
	}

	 
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccMapaStatusDecorator decorator = (SccMapaStatusDecorator)entidadeSelecionada;
		return decorator.getRow().getId();
	}

	 
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		CadastroMapaStatusForm form = (CadastroMapaStatusForm)getMyFormFromCache(getClass());
		if(form == null){
			cacheMyForm(getClass(), form);
			form = (CadastroMapaStatusForm)getMyFormFromCache(getClass());
		}
		
		List<SccMapaStatus> rows = getServiceManager().getAdminService().pesquisaMapaStatus(form.getFiltroDe(), form.getFiltroPara());
		List<SccMapaStatusDecorator> decoratorList = new ArrayList<SccMapaStatusDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{
			SccMapaStatusDecorator decorator = new SccMapaStatusDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		
	}

	@ModelAttribute("listaStatus")
	public List<SccStatusCdr> populaListaStatus() throws Exception
	{
		List<SccStatusCdr> comboList = new ArrayList<SccStatusCdr>();
		SccStatusCdr todos = new SccStatusCdr();
		todos.setCdStatusCdr(BasicDAO.GET_ALL);
		todos.setDsStatusCdr("Todos");
		comboList.add(0, todos);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().getAllStatus());
		return comboList;
	}
	
	@ModelAttribute("listaStatusEdicao")
	public List<SccStatusCdr> populaListaStatusEdicao() throws Exception
	{
		List<SccStatusCdr> comboList = new ArrayList<SccStatusCdr>();
		comboList.addAll(getServiceManager().getPesquisaDominiosService().getAllStatus());
		return comboList;
	}
	
}
