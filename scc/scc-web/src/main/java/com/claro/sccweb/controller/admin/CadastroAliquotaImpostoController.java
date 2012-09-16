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

import com.claro.cobillingweb.persistence.entity.SccAliquotaImposto;
import com.claro.cobillingweb.persistence.entity.SccAliquotaImpostoPK;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.CadastroAliquotaImpostoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccAliquotaImpostoDecorator;
import com.claro.sccweb.form.CadastroAliquotaImpostoForm;

@Controller
@RequestMapping(value="/user/admin/aliquotas")
public class CadastroAliquotaImpostoController extends BaseCRUDAndMethodController<CadastroAliquotaImpostoForm> {
	
	private final CadastroAliquotaImpostoValidator validator = new CadastroAliquotaImpostoValidator();
	
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, CadastroAliquotaImpostoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, CadastroAliquotaImpostoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccAliquotaImposto> rows = getServiceManager().getAdminService().pesquisaAliquotas(form.getNoImposto(), form.getInPlataformaTarifacao());
		
		List<SccAliquotaImpostoDecorator> decoratorList = new ArrayList<SccAliquotaImpostoDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccAliquotaImpostoDecorator decorator = new SccAliquotaImpostoDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), form);
		return mav;
	}
	
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, CadastroAliquotaImpostoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccAliquotaImposto entity = form.getEntity();
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		entity.setPcAliquotaImposto(Double.parseDouble(entity.getPcAliquotaImposto().toString().replace(',', '.')));
		getServiceManager().getAdminService().create(entity);
		return mav;
	}
	
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, CadastroAliquotaImpostoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccAliquotaImpostoDecorator decorator =  (SccAliquotaImpostoDecorator)form.getEntidadeSelecionada();
		getServiceManager().getAdminService().delete(decorator.getRow());
		return mav;
	}
	
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, CadastroAliquotaImpostoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccAliquotaImposto entity = form.getEntity();		
		entity.setDtAtualizacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		entity.setId((SccAliquotaImpostoPK)getPkEntidade(getItem(request, form)));
		getServiceManager().getAdminService().update(entity);
		return mav;
	}
	
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, CadastroAliquotaImpostoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccAliquotaImpostoDecorator> decoratorList = (List<SccAliquotaImpostoDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		SccAliquotaImpostoDecorator decorator = decoratorList.get(form.getItemSelecionado());
		form.setEntity(decorator.getRow());
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	protected String getViewName() {
		return "aliquota_imposto_filtro";
	}
	
	protected CadastroAliquotaImpostoForm getForm() {
		return new CadastroAliquotaImpostoForm();
	}
	
	protected Validator getValidator() {
		return this.validator;
	}
	
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccAliquotaImpostoDecorator decorator = (SccAliquotaImpostoDecorator)entidadeSelecionada;
		return decorator.getRow().getId();
	}
	
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		CadastroAliquotaImpostoForm form = (CadastroAliquotaImpostoForm)getMyFormFromCache(getClass());
		if(form == null){			
			cacheMyForm(getClass(), form);
			form = (CadastroAliquotaImpostoForm)getMyFormFromCache(getClass());
		}
		List<SccAliquotaImposto> rows = getServiceManager().getAdminService().pesquisaAliquotas(form.getNoImposto(), form.getInPlataformaTarifacao());
		List<SccAliquotaImpostoDecorator> decoratorList = new ArrayList<SccAliquotaImpostoDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccAliquotaImpostoDecorator decorator = new SccAliquotaImpostoDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
	}
	
	@ModelAttribute("impostos")
	public List<BasicStringItem> populaImpostos() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("I", "ICMS"));
		comboList.add(new BasicStringItem("P", "PIS"));
		comboList.add(new BasicStringItem("C", "COFINS"));
		comboList.add(new BasicStringItem("S", "ISS"));
		return comboList;
	}
	
	@ModelAttribute("tiposServico")
	public List<BasicStringItem> populaServicos() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("T", "Telecom/Chamadas"));
		comboList.add(new BasicStringItem("C", "Cobilling"));		
		return comboList;
	}
	
	@ModelAttribute("tiposContrato")
	public List<BasicStringItem> populaTiposContrato() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("POS", "Pós-Pago"));
		comboList.add(new BasicStringItem("PRE", "Pré-Pago"));		
		return comboList;
	}
	
	@ModelAttribute("listaUF")
	public List<BasicStringItem> populaUf() {
		return _populaUF();
	}
	
}
