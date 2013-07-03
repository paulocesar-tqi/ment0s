package com.claro.sccweb.controller.contrato;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccContratoCobilling;
import com.claro.cobillingweb.persistence.entity.SccContratoCobillingPK;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.CadastroContratoPreValidator;
import com.claro.sccweb.form.CadastroContratoPreForm;
import com.claro.sccweb.service.ContratoPrePagoService;

@Controller
@RequestMapping(value="/user/contrato/pre")
public class CadastroContratoPreController extends BaseCRUDAndMethodController<CadastroContratoPreForm>{
	
	@Autowired
	private ContratoPrePagoService contratoPrePagoService;
	
	private final CadastroContratoPreValidator validator = new CadastroContratoPreValidator();
	
	protected ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPreForm form, BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = null;
		//List<SccContratoCobillingSearchView> tabela =  getServiceManager().getContratoPrePagoService().pesquisaContratosOperadoras(form.getCdEOTClaro(), form.getCdEOTLD());
		List<SccContratoCobillingSearchView> tabela =  getServiceManager().getContratoPrePagoService().pesquisaContratosOperadorasByFiltro(form.getCdEOTClaro(), form.getCdEOTLD(), form.getCdTipo());
		form.setListContratoSearchView(tabela);
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, tabela, request);
		mav = new ModelAndView(getViewName(), "filtro", form);
		return mav;
	}
	
	protected ModelAndView salvar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPreForm form, BindingResult bindingResult, Model model) throws Exception {
		form.getEntity().setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		form.getEntity().setDtAtualizacao(Calendar.getInstance().getTime());		
		form.getEntity().setDtCriacao(new Date());
		
		SccContratoCobillingPK pk = new SccContratoCobillingPK();
		pk.setCdEotClaro(form.getEntity().getId().getCdEotClaro());
		pk.setCdEotLd(form.getEntity().getId().getCdEotLd());
		pk.setCdTipoContrato(form.getEntity().getId().getCdTipoContrato());
		pk.setDtInicioContrato(form.getEntity().getId().getDtInicioContrato());
		
		form.getEntity().setId(pk);
		
		
		
		this.contratoPrePagoService.create(form.getEntity());
		
		
		return new ModelAndView(getViewName(), "filtro", form);
	}
	
	protected ModelAndView remover(HttpServletRequest request, HttpServletResponse response, CadastroContratoPreForm form, BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		SccContratoCobillingPK pk = (SccContratoCobillingPK)form.getPkEntidadeSelecionada();		
		SccContratoCobilling entity = getServiceManager().getContratoPrePagoService().getByPk(pk);
		form.setEntity(entity);
		
		this.contratoPrePagoService.delete(form.getEntity());
		//getServiceManager().getContratoPrePagoService().delete(form.getEntity());
		mav.addObject("filtro", form);
		//atualizarResultados(request, response, form, bindingResult, model);
		return mav;
	}
	
	protected ModelAndView atualizar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPreForm form, BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		form.getEntity().setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		form.getEntity().setDtAtualizacao(Calendar.getInstance().getTime());
		this.contratoPrePagoService.update(form.getEntity());
		//getServiceManager().getContratoPrePagoService().update(form.getEntity());
		//atualizarResultados(request, response, form, bindingResult, model);
		mav.addObject("filtro", form);
		return mav;
	}
	
	protected ModelAndView editar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPreForm form, BindingResult bindingResult, Model model) throws Exception {
		return null;
	}

	
	@RequestMapping(value = "/editarContratoPre", method=RequestMethod.GET)
	public @ResponseBody SccContratoCobilling editarEntity(@RequestParam("cdEotLd")String cdEotLd, @RequestParam("cdEotClaro") String cdEotClaro,
														   @RequestParam("dtInicioContrato") String dtInicioContrato, 
														   @RequestParam("cdTipoContrato") String cdTipoContrato, 
														   HttpServletRequest request,HttpServletResponse response, CadastroContratoPreForm form)throws Exception {
		
		SccContratoCobillingPK pk = new SccContratoCobillingPK();
		pk.setCdEotClaro(cdEotClaro);
		pk.setCdEotLd(cdEotLd);
		pk.setDtInicioContrato(StringToDaten( dtInicioContrato));
		pk.setCdTipoContrato(aliasTipoContrato(cdTipoContrato));
		
		SccContratoCobilling entity = getServiceManager().getContratoPrePagoService().findByFiltro(pk);
		form.setEntity(entity);
		
		return form.getEntity();
		
	}
	
	@RequestMapping(value="/salvarEntity", method=RequestMethod.POST)
	public ModelAndView salvar(HttpServletRequest request, HttpServletResponse response, CadastroContratoPreForm form) throws Exception {
		form.getEntity().setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		form.getEntity().setDtAtualizacao(Calendar.getInstance().getTime());		
		form.getEntity().setDtCriacao(Calendar.getInstance().getTime());
		
		SccContratoCobillingPK pk = new SccContratoCobillingPK();
		pk.setCdEotClaro(form.getEntity().getId().getCdEotClaro());
		pk.setCdEotLd(form.getEntity().getId().getCdEotLd());
		pk.setCdTipoContrato(form.getEntity().getId().getCdTipoContrato());
		pk.setDtInicioContrato(form.getEntity().getId().getDtInicioContrato());
		
		form.getEntity().setId(pk);
		getServiceManager().getContratoPrePagoService().create(form.getEntity());
		
		return new ModelAndView(getViewName());
	}
	
	
	private String aliasTipoContrato(String cdTipoContrato){
		String value = null;
		if(cdTipoContrato.equals("ARRECADADO")){
			value = "A";
		}else if(cdTipoContrato.equals("NOVO CONTRATO")){
			value = "F";
		}else{
			value = "P";
		}
		return value;
	}
	
	@RequestMapping(value = "/removerContratoPre", method=RequestMethod.DELETE)
	public ModelAndView removerContratoPre(@RequestParam("cdEotLd")String cdEotLd, @RequestParam("cdEotClaro") String cdEotClaro,
			   @RequestParam("dtInicioContrato") String dtInicioContrato, 
			   @RequestParam("cdTipoContrato") String cdTipoContrato, 
			   HttpServletRequest request,HttpServletResponse response, CadastroContratoPreForm form)throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		
		this.contratoPrePagoService.excluir(gerarPk(cdEotLd, cdEotClaro, dtInicioContrato, cdTipoContrato));
	
		mav.addObject("filtro", form);
		return mav;
	}
	
	private SccContratoCobillingPK gerarPk(String cdEotLd, String cdEotClaro, String dtInicioContrato, String cdTipoContrato){
		
		SccContratoCobillingPK pk = new SccContratoCobillingPK();
		pk.setCdEotLd(cdEotLd);
		pk.setCdEotClaro(cdEotClaro);
		pk.setDtInicioContrato(StringToDaten(dtInicioContrato));
		pk.setCdTipoContrato(cdTipoContrato);
		return pk;
	}

	
	public Date StringToDaten(String data){
	    Date DataNascFr = null;  
	    String DataNascDb = null; 
	    Date DataConvertida = null;
	    try{  
	    DataNascFr = new SimpleDateFormat("yyyy-MM-dd").parse(data);    
	    DataNascDb = new SimpleDateFormat("dd-MM-yyyy").format(DataNascFr);
	    DataConvertida = new SimpleDateFormat("dd-MM-yyyy").parse(DataNascDb);
	    }catch( java.text.ParseException e ){  
	    e.printStackTrace();  
	    }
		return DataConvertida;  


		
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
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());
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
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());
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

	public void setContratoPrePagoService(
			ContratoPrePagoService contratoPrePagoService) {
		this.contratoPrePagoService = contratoPrePagoService;
	}
	
	
	
}
