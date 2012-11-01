package com.claro.sccweb.controller.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccMotivoRejeicao;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPenalidadePorRejeicao;
import com.claro.cobillingweb.persistence.entity.SccPenalidadePorRejeicaoPK;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.CadastroConfigPenalidadeValidator;
import com.claro.sccweb.decorator.SccPenalidadePorRejeicaoDecorator;
import com.claro.sccweb.form.CadastroConfigPenalidadeForm;

@Controller
@RequestMapping(value="/user/admin/configrejeicao")
public class CadastroConfigPenalidadeController extends BaseCRUDAndMethodController<CadastroConfigPenalidadeForm> {

	private final CadastroConfigPenalidadeValidator validator = new CadastroConfigPenalidadeValidator();
	
	protected ModelAndView limpar(HttpServletRequest request, HttpServletResponse response, CadastroConfigPenalidadeForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	protected ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, CadastroConfigPenalidadeForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccPenalidadePorRejeicao> rows = getServiceManager().getAdminService().pesquisarPenalidadePorRejeicao(form.getCdOperadoraLD(), form.getCdMotivoRejeicao());
		List<SccPenalidadePorRejeicaoDecorator> decoratorList = new ArrayList<SccPenalidadePorRejeicaoDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccPenalidadePorRejeicaoDecorator decorator = new SccPenalidadePorRejeicaoDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		mav.addObject(FORM_NAME, form);
		cacheMyForm(getClass(), form);
		return mav;
	}
	
	protected ModelAndView salvar(HttpServletRequest request, HttpServletResponse response, CadastroConfigPenalidadeForm form,BindingResult bindingResult, Model model) throws Exception {
		SccPenalidadePorRejeicao entity = form.getEntity();
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		entity.setDtAtualizacao(Calendar.getInstance().getTime());
		getServiceManager().getAdminService().create(entity);		
		return limpar(request, response, form, bindingResult, model);
	}
	
	/**
	 * Salva por AJAX. 
	 */	  
	@RequestMapping(value="/json/salva_configrejeicao/{cdOperadoraLD}/{cdMotivoRejeicao}/{valorPenalidade}/{indCobraPenalidade}" , method=RequestMethod.GET)
	public void pesquisaPeriodos(@PathVariable("cdOperadoraLD") String cdOperadoraLD,@PathVariable("cdMotivoRejeicao") String cdMotivoRejeicao,@PathVariable("valorPenalidade") String valorPenalidade,@PathVariable("indCobraPenalidade") String indCobraPenalidade, HttpServletRequest request, HttpServletResponse response) throws Exception {			
		SccPenalidadePorRejeicao entity = new SccPenalidadePorRejeicao();
		SccPenalidadePorRejeicaoPK pk = new SccPenalidadePorRejeicaoPK();
		pk.setCdEotLd(cdOperadoraLD);
		pk.setCdMotivoRejeicao(cdMotivoRejeicao);
		entity.setId(pk);
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		entity.setDtAtualizacao(Calendar.getInstance().getTime());
		
		getServiceManager().getAdminService().update(entity);

		response.setContentType("application/json");
		//response.getWriter().print(jsonResponse.toString());
	}

	
	
	protected ModelAndView remover(HttpServletRequest request, HttpServletResponse response, CadastroConfigPenalidadeForm form,BindingResult bindingResult, Model model) throws Exception {		
		ModelAndView mav = new ModelAndView(getViewName());		
		SccPenalidadePorRejeicaoDecorator decorator = (SccPenalidadePorRejeicaoDecorator)form.getEntidadeSelecionada();
		getServiceManager().getAdminService().delete(decorator.getRow());		
		mav.addObject(FORM_NAME, new CadastroConfigPenalidadeForm());
		return mav;		
	}
	
	protected ModelAndView atualizar(HttpServletRequest request, HttpServletResponse response, CadastroConfigPenalidadeForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccPenalidadePorRejeicao entity = form.getEntity();
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		entity.setDtAtualizacao(Calendar.getInstance().getTime());
		getServiceManager().getAdminService().update(entity);
		mav.addObject(FORM_NAME, new CadastroConfigPenalidadeForm());
		return mav;
	}
	
	protected ModelAndView editar(HttpServletRequest request, HttpServletResponse response, CadastroConfigPenalidadeForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccPenalidadePorRejeicaoDecorator decorator = (SccPenalidadePorRejeicaoDecorator)form.getEntidadeSelecionada();
		form.setEntity(decorator.getRow());
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	protected String getViewName() {		
		return "config_rejeicao_filtro";
	}
	
	protected CadastroConfigPenalidadeForm getForm() {		
		return new CadastroConfigPenalidadeForm();
	}
	
	protected Validator getValidator() {		
		return this.validator;
	}
	
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccPenalidadePorRejeicao entity = ((SccPenalidadePorRejeicaoDecorator)entidadeSelecionada).getRow();
		return entity.getId();
	}
	
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		CadastroConfigPenalidadeForm cachedForm = (CadastroConfigPenalidadeForm)getMyFormFromCache(getClass());
		if (cachedForm != null) {
			List<SccPenalidadePorRejeicao> rows = getServiceManager().getAdminService().pesquisarPenalidadePorRejeicao(cachedForm.getCdOperadoraLD(), cachedForm.getCdMotivoRejeicao());
			List<SccPenalidadePorRejeicaoDecorator> decoratorList = new ArrayList<SccPenalidadePorRejeicaoDecorator>(rows.size());
			for (int i=0;i<rows.size();i++) {
				SccPenalidadePorRejeicaoDecorator decorator = new SccPenalidadePorRejeicaoDecorator(rows.get(i), i);
				decoratorList.add(decorator);
			}
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		}
	}

	@ModelAttribute("simNao")
	public List<BasicStringItem> populaTipos() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("S", "Sim"));
		comboList.add(new BasicStringItem("N", "Não"));
		return comboList;
	}
	
	@ModelAttribute("operadorasLDTodas")
	public List<SccOperadora> populaOperadorasLDTodas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	@ModelAttribute("operadorasLD")
	public List<SccOperadora> populaOperadorasLD() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	
	@ModelAttribute("tiposRejeicaoTodos")	
	public  List<SccMotivoRejeicao> populaTiposRejeicaoTodos() throws Exception
	{
		List<SccMotivoRejeicao> comboList = getServiceManager().getPesquisaDominiosService().getAllMotivosRejeicao();
		SccMotivoRejeicao allValues = new SccMotivoRejeicao();
		allValues.setCdMotivoRejeicao(BasicDAO.GET_ALL_STRING);
		allValues.setDsMotivoRejeicao("Todos");
		comboList.add(0,allValues);
		return comboList;
	}

	@ModelAttribute("tiposRejeicao")	
	public  List<SccMotivoRejeicao> populaTiposRejeicao() throws Exception
	{
		List<SccMotivoRejeicao> comboList = getServiceManager().getPesquisaDominiosService().getAllMotivosRejeicao();
		return comboList;
	}


}
