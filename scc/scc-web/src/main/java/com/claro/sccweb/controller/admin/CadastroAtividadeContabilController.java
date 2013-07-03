package com.claro.sccweb.controller.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccAtividadeContabil;
import com.claro.cobillingweb.persistence.entity.SccContaContabil;
import com.claro.cobillingweb.persistence.entity.SccDominioContabil;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.CadastroAtividadeContabilValidator;
import com.claro.sccweb.decorator.rownum.entity.SccAtividadeContabilDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.CadastroAtividadeContabilForm;

@Controller
@RequestMapping(value="/user/admin/atividade")
public class CadastroAtividadeContabilController extends BaseCRUDAndMethodController<CadastroAtividadeContabilForm> {
	
	private final CadastroAtividadeContabilValidator validator = new CadastroAtividadeContabilValidator();
	
	public ModelAndView iniciar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.iniciar(request, response);
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		
		List<SccAtividadeContabil> rows = getServiceManager().getContabilService().getAllAtividadeContabil();
		
		List<SccAtividadeContabilDecorator> decoratorList = new ArrayList<SccAtividadeContabilDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccAtividadeContabilDecorator decorator = new SccAtividadeContabilDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		cacheMyForm(getClass(), getForm());
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		return mav;
	}
	
	protected ModelAndView limpar(HttpServletRequest request, HttpServletResponse response, CadastroAtividadeContabilForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	protected ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, CadastroAtividadeContabilForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
	}
	
	protected ModelAndView salvar(HttpServletRequest request, HttpServletResponse response, CadastroAtividadeContabilForm form, BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccAtividadeContabil entity = form.getEntity();
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuario(getSessionDataManager().getUserPrincipal());
		getServiceManager().getContabilService().create(entity);
		return mav;
	}
	
	protected ModelAndView remover(HttpServletRequest request, HttpServletResponse response, CadastroAtividadeContabilForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccAtividadeContabil entity = ((SccAtividadeContabilDecorator)form.getEntidadeSelecionada()).getRow();
		getServiceManager().getContabilService().delete(entity);
		return mav;
	}
	
	protected ModelAndView atualizar(HttpServletRequest request, HttpServletResponse response, CadastroAtividadeContabilForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccAtividadeContabil entity = form.getEntity();
		entity.setSqAtividade((Long)getPkEntidade(getItem(request, form)));
		entity.setDtAtualizacao(Calendar.getInstance().getTime());
		entity.setCdUsuario(getSessionDataManager().getUserPrincipal());
		getServiceManager().getContabilService().update(entity);
		return mav;
	}
	
	protected ModelAndView editar(HttpServletRequest request, HttpServletResponse response, CadastroAtividadeContabilForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		int itemIndex = form.getItemSelecionado();
		SccAtividadeContabilDecorator decorator = ((List<SccAtividadeContabilDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		SccAtividadeContabil entity = decorator.getRow();
		form.setEntity(entity);
		mav.addObject("filtro",form);
		return mav;
	}
	
	protected String getViewName() {
		return "atividade_contabil_filtro";
	}
	
	protected CadastroAtividadeContabilForm getForm() {
		return new CadastroAtividadeContabilForm();
	}
	
	protected Validator getValidator() {
		return this.validator;
	}
	
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccAtividadeContabilDecorator decorator = (SccAtividadeContabilDecorator)entidadeSelecionada;
		return decorator.getRow().getSqAtividade();
	}
	
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		List<SccAtividadeContabil> rows = getServiceManager().getContabilService().getAllAtividadeContabil();
		List<SccAtividadeContabilDecorator> decoratorList = new ArrayList<SccAtividadeContabilDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccAtividadeContabilDecorator decorator = new SccAtividadeContabilDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
	}
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME) BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
		return new ModelAndView("atividade_contabil_excel");
	}
	
	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		return super.populaOperadorasExternas(true);
	}
	
	@ModelAttribute("contas")
	public List<SccContaContabil> populaContas() throws Exception {
		return getServiceManager().getContabilService().getAllContaContabil();
	}
	
	@ModelAttribute("dominio")
	public List<SccDominioContabil> populaDominio() throws Exception {
		return getServiceManager().getContabilService().getAllDominioContabil();
	}
	
}
