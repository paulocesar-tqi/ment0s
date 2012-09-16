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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccAliquotaImpostoPK;
import com.claro.cobillingweb.persistence.entity.SccMotivoRejeicao;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.CadastroMotivoRejeicaoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccMotivoRejeicaoDecorator;
import com.claro.sccweb.form.CadastroMotivoRejeicaoForm;

@Controller
@RequestMapping(value="/user/admin/rejeicao")
public class CadastroMotivoRejeicaoController extends BaseCRUDAndMethodController<CadastroMotivoRejeicaoForm> {

	private final CadastroMotivoRejeicaoValidator validator = new CadastroMotivoRejeicaoValidator();
	
	 
	public ModelAndView iniciar(HttpServletRequest request,HttpServletResponse response) throws Exception {
		super.iniciar(request, response);
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		List<SccMotivoRejeicao> rows = getServiceManager().getPesquisaDominiosService().getAllMotivosRejeicao();
		List<SccMotivoRejeicaoDecorator> decoratorList = new ArrayList<SccMotivoRejeicaoDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{
			SccMotivoRejeicaoDecorator decorator = new SccMotivoRejeicaoDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), getForm());
		return mav;
	}
	
	 
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, CadastroMotivoRejeicaoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}

	 
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, CadastroMotivoRejeicaoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
	}

	 
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, CadastroMotivoRejeicaoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccMotivoRejeicao entity = form.getEntity();
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		entity.setDtCriacao(Calendar.getInstance().getTime());
		getServiceManager().getAdminService().create(entity);		 
		return iniciar(request,response);
	}

	 
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, CadastroMotivoRejeicaoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());		
		SccMotivoRejeicaoDecorator decorator = (SccMotivoRejeicaoDecorator)form.getEntidadeSelecionada();
		getServiceManager().getAdminService().delete(decorator.getRow());
		return mav;
	}

	 
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, CadastroMotivoRejeicaoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccMotivoRejeicao entity = form.getEntity();
		entity.setDtAtualizacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		entity.setCdMotivoRejeicao((String)getPkEntidade(getItem(request, form)));
		getServiceManager().getAdminService().update(entity);
		return mav;
	}

	 
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, CadastroMotivoRejeicaoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		int itemIndex = form.getItemSelecionado();
		SccMotivoRejeicaoDecorator decorator = ((List<SccMotivoRejeicaoDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		SccMotivoRejeicao entity = decorator.getRow();
		form.setEntity(entity);
		mav.addObject("filtro",form);
		return mav;
	}

	 
	protected String getViewName() {	
		return "motivo_rejeicao_filtro";
	}

	 
	protected CadastroMotivoRejeicaoForm getForm() {
		return new CadastroMotivoRejeicaoForm();
	}

	 
	protected Validator getValidator() {
		return this.validator; 
	}

	 
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccMotivoRejeicaoDecorator decorator = (SccMotivoRejeicaoDecorator)entidadeSelecionada;
		return decorator.getRow().getCdMotivoRejeicao();
	}

	 
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		List<SccMotivoRejeicao> rows = getServiceManager().getPesquisaDominiosService().getAllMotivosRejeicao();
		List<SccMotivoRejeicaoDecorator> decoratorList = new ArrayList<SccMotivoRejeicaoDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{
			SccMotivoRejeicaoDecorator decorator = new SccMotivoRejeicaoDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
	}

	
	
}
