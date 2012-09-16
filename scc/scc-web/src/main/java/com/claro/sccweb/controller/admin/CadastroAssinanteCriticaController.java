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
import com.claro.cobillingweb.persistence.entity.SccAssinanteCritica;
import com.claro.cobillingweb.persistence.entity.SccAssinanteCriticaPK;
import com.claro.cobillingweb.persistence.entity.SccCriticaPrebilling;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.CadastroAssinanteCriticaValidator;
import com.claro.sccweb.decorator.rownum.entity.SccAssinanteCriticaDecorator;
import com.claro.sccweb.form.CadastroAssinanteCriticaForm;

@Controller
@RequestMapping(value="/user/admin/critica")
public class CadastroAssinanteCriticaController extends BaseCRUDAndMethodController<CadastroAssinanteCriticaForm> {

	private final CadastroAssinanteCriticaValidator validator = new CadastroAssinanteCriticaValidator(); 
	
	 
	protected String getViewName() {
		return "cadastro_assinante_critica_filtro";
	}

	 
	protected CadastroAssinanteCriticaForm getForm() {	
		return new CadastroAssinanteCriticaForm();
	}

	 
	protected Validator getValidator() {	
		return this.validator;
	}

	
	@ModelAttribute("csp")
	public List<SccOperadora> populaCSP() throws Exception
	{
		return getServiceManager().getOperadoraService().getAllCSP();
	}
	
	
	@ModelAttribute("codigos")
	public List<SccCriticaPrebilling> populaCodigos() throws Exception
	{
		List<SccCriticaPrebilling>  comboList = new ArrayList<SccCriticaPrebilling>();
		SccCriticaPrebilling todas = new SccCriticaPrebilling();
		todas.setCdCritica(BasicDAO.GET_ALL_STRING);
		todas.setDsCritica("Todas");
		comboList.add(0,todas);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().getAllCriticaPreBilling());
		return comboList;
	}
	
	
	@ModelAttribute("codigosEdicao")
	public List<SccCriticaPrebilling> populaCodigosEdica() throws Exception
	{
		List<SccCriticaPrebilling>  comboList = new ArrayList<SccCriticaPrebilling>();		
		comboList.addAll(getServiceManager().getPesquisaDominiosService().getAllCriticaPreBilling());
		return comboList;
	}

	 
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, CadastroAssinanteCriticaForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName()); 
		form.setEntity(new SccAssinanteCritica());
		mav.addObject(FORM_NAME, form);
		return mav;
	}

	 
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, CadastroAssinanteCriticaForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccAssinanteCritica> rows = getServiceManager().getAdminService().pesquisaCritica(form.getCdCritica());
		List<SccAssinanteCriticaDecorator> decoratorList = new ArrayList<SccAssinanteCriticaDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{
			SccAssinanteCriticaDecorator decorator = new SccAssinanteCriticaDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		form.setEntity(new SccAssinanteCritica());
		mav.addObject(FORM_NAME, form);
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), form);
		return mav;
	}

	 
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, CadastroAssinanteCriticaForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccAssinanteCritica entity = form.getEntity();
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		getServiceManager().getAdminService().create(entity);
		form.setOperacao(OPERACAO_CREATE);
		form.setEntity(new SccAssinanteCritica());
		return mav;
	}

	 
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, CadastroAssinanteCriticaForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccAssinanteCriticaDecorator decorator = (SccAssinanteCriticaDecorator)form.getEntidadeSelecionada();
		SccAssinanteCritica entity = decorator.getRow();
		getServiceManager().getAdminService().delete(entity);
		form.setEntity(new SccAssinanteCritica());
		mav.addObject(FORM_NAME, form);
		return mav;
	}

	 
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, CadastroAssinanteCriticaForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccAssinanteCritica entity = form.getEntity();		
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		entity.setId((SccAssinanteCriticaPK)getPkEntidade(getItem(request, form)));
		getServiceManager().getAdminService().update(entity);
		form.setOperacao(OPERACAO_UPDATE);
		form.setEntity(new SccAssinanteCritica());
		return mav;
	}

	 
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, CadastroAssinanteCriticaForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		int itemIndex = form.getItemSelecionado();
		SccAssinanteCriticaDecorator decorator = ((List<SccAssinanteCriticaDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		SccAssinanteCritica entity = decorator.getRow();
		form.setEntity(entity);
		mav.addObject("filtro",form);
		return mav;
	}

	 
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccAssinanteCriticaDecorator decorator = (SccAssinanteCriticaDecorator)entidadeSelecionada;
		return decorator.getRow().getId();
	}

	 
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {		
		CadastroAssinanteCriticaForm form = (CadastroAssinanteCriticaForm)getMyFormFromCache(getClass());
		if(form == null){
			cacheMyForm(getClass(), form);
			form = (CadastroAssinanteCriticaForm)getMyFormFromCache(getClass());
		}		
		List<SccAssinanteCritica> rows = getServiceManager().getAdminService().pesquisaCritica(form.getCdCritica());
		List<SccAssinanteCriticaDecorator> decoratorList = new ArrayList<SccAssinanteCriticaDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{
			SccAssinanteCriticaDecorator decorator = new SccAssinanteCriticaDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		form.setEntity(new SccAssinanteCritica());		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);		
	}	
}
