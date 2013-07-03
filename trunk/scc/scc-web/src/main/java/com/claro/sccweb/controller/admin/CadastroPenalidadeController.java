package com.claro.sccweb.controller.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccFaixaPenalidade;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.CadastroPenalidadeValidator;
import com.claro.sccweb.decorator.SccFaixaPenalidadeDecorator;
import com.claro.sccweb.form.CadastroPenalidadeForm;
import com.claro.sccweb.service.AdminService;
import com.claro.sccweb.service.SessionDataManager;

@Controller
@RequestMapping(value="/user/admin/penalidade")
public class CadastroPenalidadeController extends BaseCRUDAndMethodController<CadastroPenalidadeForm> {

	private final CadastroPenalidadeValidator validator = new CadastroPenalidadeValidator();
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private SessionDataManager sessionDataManager;

	private static final String FWD_VIEW_LISTA_PENALIDADE = "gestao_penalidade_filtro";
	
	protected ModelAndView limpar(HttpServletRequest request, HttpServletResponse response, CadastroPenalidadeForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	@RequestMapping(value="listarPenalidades", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listarTodos(HttpServletRequest request, HttpServletResponse response, CadastroPenalidadeForm form) throws Exception {
		
		List<SccFaixaPenalidade> list = this.adminService.pesquisarPenalidadePorTipo(form.getCdTipo());
		
		form.setListFaixaPenalidade(list);
		form.setEntity(new SccFaixaPenalidade());
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_PENALIDADE, "filtro", form);
		return mav;
		
	}
	
	protected ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, CadastroPenalidadeForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccFaixaPenalidade> rows = getServiceManager().getAdminService().pesquisarPenalidadePorTipo(form.getCdTipo());
		List<SccFaixaPenalidadeDecorator> decoratorList = new ArrayList<SccFaixaPenalidadeDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccFaixaPenalidadeDecorator decorator = new SccFaixaPenalidadeDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		mav.addObject(FORM_NAME, form);
		cacheMyForm(getClass(), form);
		return mav;
	}
	
	protected ModelAndView salvar(HttpServletRequest request, HttpServletResponse response, CadastroPenalidadeForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccFaixaPenalidade entity = form.getEntity();
		getServiceManager().getAdminService().create(entity);		
		return limpar(request, response, form, bindingResult, model);
	}
	
	@RequestMapping(value="/salvarEntity", method=RequestMethod.POST)
	public @ResponseBody ModelAndView saveEntity(HttpServletRequest request, HttpServletResponse response, CadastroPenalidadeForm form) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_PENALIDADE);
		
		this.adminService.create(form.getEntity());

		return mav;
	}
	
	@RequestMapping(value = "/atualizarEntity", method=RequestMethod.POST)
	public @ResponseBody ModelAndView updateEntity(HttpServletRequest request, HttpServletResponse response, CadastroPenalidadeForm form) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_PENALIDADE);
		
		this.adminService.update(form.getEntity());
		
		return mav;
	}
	
	@RequestMapping(value = "/editarEntity", method=RequestMethod.GET)
	public @ResponseBody SccFaixaPenalidade editEntity(@RequestParam("cdFaixaPenalidade") Long cdFaixaPenalidade , HttpServletRequest request,HttpServletResponse response, CadastroPenalidadeForm form) throws Exception {
		
		SccFaixaPenalidade entity = (SccFaixaPenalidade) this.adminService.findById(cdFaixaPenalidade);
		form.setEntity(entity);
		
		return form.getEntity();
	}
	
	@RequestMapping(value = "/removerEntity", method=RequestMethod.DELETE)
	public @ResponseBody ModelAndView removerEntity(@RequestParam("cdFaixaPenalidade") Long cdFaixaPenalidade , HttpServletRequest request,HttpServletResponse response, CadastroPenalidadeForm form) throws Exception {
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_PENALIDADE);
		
		this.adminService.deleteEntity(cdFaixaPenalidade);
		
		mav.addObject("filtro", form);
		return mav;
	}
	

	

	
	protected ModelAndView remover(HttpServletRequest request, HttpServletResponse response, CadastroPenalidadeForm form,BindingResult bindingResult, Model model) throws Exception {		
		ModelAndView mav = new ModelAndView(getViewName());		
		SccFaixaPenalidadeDecorator decorator = (SccFaixaPenalidadeDecorator)form.getEntidadeSelecionada();
		getServiceManager().getAdminService().delete(decorator.getRow());		
		mav.addObject(FORM_NAME, new CadastroPenalidadeForm());
		return mav;		
	}
	
	protected ModelAndView atualizar(HttpServletRequest request, HttpServletResponse response, CadastroPenalidadeForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		getServiceManager().getAdminService().update(form.getEntity());
		mav.addObject(FORM_NAME, new CadastroPenalidadeForm());
		return mav;
	}
	
	protected ModelAndView editar(HttpServletRequest request, HttpServletResponse response, CadastroPenalidadeForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccFaixaPenalidadeDecorator decorator = (SccFaixaPenalidadeDecorator)form.getEntidadeSelecionada();
		form.setEntity(decorator.getRow());
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	protected String getViewName() {		
		return "gestao_penalidade_filtro";
	}
	
	protected CadastroPenalidadeForm getForm() {		
		return new CadastroPenalidadeForm();
	}
	
	protected Validator getValidator() {		
		return this.validator;
	}
	
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccFaixaPenalidade entity = ((SccFaixaPenalidadeDecorator)entidadeSelecionada).getRow();
		return entity.getCdFaixaPenalidade();
	}
	
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		CadastroPenalidadeForm cachedForm = (CadastroPenalidadeForm)getMyFormFromCache(getClass());
		if (cachedForm != null) {
			List<SccFaixaPenalidade> rows = getServiceManager().getAdminService().pesquisarPenalidadePorTipo(cachedForm.getCdTipo());
			List<SccFaixaPenalidadeDecorator> decoratorList = new ArrayList<SccFaixaPenalidadeDecorator>(rows.size());
			for (int i=0;i<rows.size();i++) {
				SccFaixaPenalidadeDecorator decorator = new SccFaixaPenalidadeDecorator(rows.get(i), i);
				decoratorList.add(decorator);
			}
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		}
	}

	@ModelAttribute("tipos")
	public List<BasicStringItem> populaTipos() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("C", "Chamadas Perdidas"));
		comboList.add(new BasicStringItem("Q", "Questionamento"));
		return comboList;
	}
	
	@ModelAttribute("descricao")
	public List<BasicStringItem> populaDescricao() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("chamadas", "Quantidade de Chamadas"));
		comboList.add(new BasicStringItem("minutos", "Quantidade de Minutos"));
		return comboList;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public void setSessionDataManager(SessionDataManager sessionDataManager) {
		this.sessionDataManager = sessionDataManager;
	}
	
	
	
}
