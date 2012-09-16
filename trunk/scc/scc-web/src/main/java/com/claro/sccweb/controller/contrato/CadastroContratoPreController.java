package com.claro.sccweb.controller.contrato;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccContratoCobilling;
import com.claro.cobillingweb.persistence.entity.SccContratoCobillingPK;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.CadastroContratoPreValidator;
import com.claro.sccweb.form.CadastroContratoPreForm;

@Controller
@RequestMapping(value="/user/contrato/pre")
public class CadastroContratoPreController extends BaseCRUDAndMethodController<CadastroContratoPreForm>{
	
	private final CadastroContratoPreValidator validator = new CadastroContratoPreValidator();
	
	protected ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPreForm form, BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccContratoCobillingSearchView> tabela =  getServiceManager().getContratoPrePagoService().pesquisaContratosOperadoras(form.getCdEOTClaro(), form.getCdEOTLD());
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, tabela, request);
		return mav;
	}
	
	protected ModelAndView salvar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPreForm form, BindingResult bindingResult, Model model) throws Exception {
		form.getEntity().setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		form.getEntity().setDtAtualizacao(Calendar.getInstance().getTime());		
		form.getEntity().setDtCriacao(Calendar.getInstance().getTime());
		

		
		
		SccContratoCobillingPK pk = new SccContratoCobillingPK();
		pk.setCdEotClaro(form.getEntity().getId().getCdEotClaro());
		pk.setCdEotLd(form.getEntity().getId().getCdEotLd());
		pk.setCdTipoContrato(form.getEntity().getId().getCdTipoContrato());
		pk.setDtInicioContrato(form.getEntity().getId().getDtInicioContrato());
		
		SccContratoCobilling entityV = null;
		
		try {
			entityV = getServiceManager().getContratoPrePagoService().getByPk(pk);
		} catch (Exception e) {
			getServiceManager().getContratoPrePagoService().create(form.getEntity());
		}
		
		return new ModelAndView(getViewName());
	}
	
	protected ModelAndView remover(HttpServletRequest request, HttpServletResponse response, CadastroContratoPreForm form, BindingResult bindingResult, Model model) throws Exception {
		SccContratoCobillingPK pk = (SccContratoCobillingPK)form.getPkEntidadeSelecionada();		
		SccContratoCobilling entity = getServiceManager().getContratoPrePagoService().getByPk(pk);
		form.setEntity(entity);
		getServiceManager().getContratoPrePagoService().delete(form.getEntity());
		atualizarResultados(request, response, form, bindingResult, model);
		return new ModelAndView(getViewName());
	}
	
	protected ModelAndView atualizar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPreForm form, BindingResult bindingResult, Model model) throws Exception {	
		form.getEntity().setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		form.getEntity().setDtAtualizacao(Calendar.getInstance().getTime());
		getServiceManager().getContratoPrePagoService().update(form.getEntity());
		atualizarResultados(request, response, form, bindingResult, model);
		return new ModelAndView(getViewName());
	}
	
	protected ModelAndView editar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPreForm form, BindingResult bindingResult, Model model) throws Exception {		
		SccContratoCobillingPK pk = (SccContratoCobillingPK)form.getPkEntidadeSelecionada();		
		SccContratoCobilling entity = getServiceManager().getContratoPrePagoService().getByPk(pk);
		form.setEntity(entity);		
		return new ModelAndView(getViewName());
	}
	
	protected String getViewName() {		
		return "contrato_pre_cadastro";
	}
	
	protected CadastroContratoPreForm getForm() {
		return new CadastroContratoPreForm();
	}
	
	protected Validator getValidator() {		
		return validator;
	}
	
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccContratoCobillingSearchView view = (SccContratoCobillingSearchView)entidadeSelecionada;
		SccContratoCobillingPK pk = new SccContratoCobillingPK();
		pk.setCdEotClaro(view.getCdEOTClaro());
		pk.setCdEotLd(view.getCdEOTLD());
		pk.setCdTipoContrato(view.getCdTipoContrato());
		pk.setDtInicioContrato(view.getDtInicioContrato());
		return pk;
	}
	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaro());
		return comboList;
	}
	
	@ModelAttribute("operadorasExternasnotNull")
	public List<SccOperadora> populaOperadorasExternasNotNull() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();		
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	@ModelAttribute("operadorasClaroNotNull")
	public List<SccOperadora> populaOperadorasClaroNotNull() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();		
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaro());
		return comboList;
	}
	
	@ModelAttribute("periodos")
	public List<BasicStringItem> populaPeriodos() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("MENSAL", "MENSAL"));		
		return comboList;
	}
	
	@ModelAttribute("criterios")
	public List<BasicStringItem> populaCriterios() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("POR CHAMADA", "POR CHAMADA"));		
		comboList.add(new BasicStringItem("FATURA", "FATURA"));
		comboList.add(new BasicStringItem("PERCENTUAL", "PERCENTUAL"));
		comboList.add(new BasicStringItem("POR MINUTO", "POR MINUTO"));
		return comboList;
	}
	
	@ModelAttribute("tipos")
	public List<BasicStringItem> populaTipos() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("*", "Todos"));
		comboList.add(new BasicStringItem("F", "Novo Contrato"));		
		comboList.add(new BasicStringItem("A", "Arrecadado"));
		comboList.add(new BasicStringItem("P", "Pre Pago"));
		return comboList;
	}
	
	protected ModelAndView limpar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPreForm form,BindingResult bindingResult, Model model) throws Exception {		
		ModelAndView mav = new ModelAndView(getViewName());
		form = getForm();
		form.setEntity(new SccContratoCobilling());
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	@RequestMapping(value="/tab1" , method = RequestMethod.GET)
	public ModelAndView tab1(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView(getViewName());
			Object form = getMyFormFromCache(getClass());
			if (form != null)
				mav.addObject(FORM_NAME, form);
			else
				mav.addObject(FORM_NAME, getForm());
	    	return mav;  
	}
	
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
	}
	
}
