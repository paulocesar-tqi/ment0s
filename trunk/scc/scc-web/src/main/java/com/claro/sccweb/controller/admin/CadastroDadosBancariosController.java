package com.claro.sccweb.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccDadosBancario;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.CadastroDadosBancariosValidator;
import com.claro.sccweb.form.CadastroDadosBancariosForm;
import com.claro.sccweb.service.CadastroDadosBancariosService;
import com.claro.sccweb.service.SessionDataManager;

@Controller
@RequestMapping(value="/user/admin/dados/bancarios")
public class CadastroDadosBancariosController extends BaseOperationController<CadastroDadosBancariosForm> {

	private static final String FWD_VIEW_BANCARIO = "cadastro_dados_bancarios";

	
	private final CadastroDadosBancariosValidator validator = new CadastroDadosBancariosValidator();
	
	@Autowired
	private SessionDataManager serviceDataManager;
	
	@Autowired
	private CadastroDadosBancariosService cadastroDadosBancariosService;

	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisarByFiltro(HttpServletRequest request, HttpServletResponse response, CadastroDadosBancariosForm form) throws Exception {
		
		ModelAndView mav = null;
		
		List<SccDadosBancario> lst = this.cadastroDadosBancariosService.findAll();
		
		form.setLstBancario(lst);
		mav = new ModelAndView(FWD_VIEW_BANCARIO, "filtro", form);
		return mav;
		
	}
	

	@RequestMapping(value = "/editarbancario", method=RequestMethod.GET)
	public @ResponseBody  SccDadosBancario editarBancario(@RequestParam("cdEotLd") String cdEotLd, @RequestParam("nuBanco") String nuBanco, @RequestParam("nuAgencia") String nuAgencia  , HttpServletRequest request,HttpServletResponse response, CadastroDadosBancariosForm form) throws Exception {
		
		
		SccDadosBancario entidade = this.cadastroDadosBancariosService.findById(cdEotLd, nuBanco, nuAgencia);
		
		form.setEntidade(entidade);
		
		return form.getEntidade();
	}
	
	@RequestMapping(value="/salvarEntity", method=RequestMethod.POST)
	public @ResponseBody ModelAndView salvar(HttpServletRequest request, HttpServletResponse response, CadastroDadosBancariosForm form) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_BANCARIO);
		
		Date data = new Date();
		form.getEntidade().setCdUsuarioManut(this.serviceDataManager.getUserPrincipal());
		form.getEntidade().setDtAtualizacao(data);
		if(form.getEntidade().getDtCriacao() == null || form.getEntidade().getDtCriacao().equals("")){
			form.getEntidade().setDtCriacao(data);
		}
		this.cadastroDadosBancariosService.saveOrUpdate(form.getEntidade());
		mav.addObject("filtro", form);	
		return mav;

	}
	
	@RequestMapping(value = "/removerbancario", method=RequestMethod.DELETE)
	public ModelAndView removerBancario(@RequestParam("cdEotLd") String cdEotLd, @RequestParam("nuBanco") String nuBanco, @RequestParam("nuAgencia") String nuAgencia , HttpServletRequest request,HttpServletResponse response, CadastroDadosBancariosForm form) throws Exception {
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_BANCARIO);
		
		this.cadastroDadosBancariosService.excluirDadosBancario(cdEotLd, nuBanco, nuAgencia);
		
		mav.addObject("filtro", form);
		return mav;
	}
	
	
	
	@RequestMapping(value = "/novobancario", method=RequestMethod.POST)
	public ModelAndView novoBancario(HttpServletRequest request,HttpServletResponse response, CadastroDadosBancariosForm form) throws Exception {
		
		ModelAndView mav = null;
		form.setEntidade(new SccDadosBancario());
		form.getEntidade().setDtCriacao(new Date());
		form.getEntidade().setDtAtualizacao(new Date());
		mav = new ModelAndView(FWD_VIEW_BANCARIO, "filtro", form);
		return mav;
	}

	
	@ModelAttribute("operadorasLD")
	public List<SccOperadora> populaOperadorasLD() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	
	@Override
	protected String getViewName() {

		return FWD_VIEW_BANCARIO;
	}

	@Override
	protected CadastroDadosBancariosForm getForm() {
		
		return new CadastroDadosBancariosForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}

	public void setCadastroDadosBancariosService(
			CadastroDadosBancariosService cadastroDadosBancariosService) {
		this.cadastroDadosBancariosService = cadastroDadosBancariosService;
	}


	public void setServiceDataManager(SessionDataManager serviceDataManager) {
		this.serviceDataManager = serviceDataManager;
	}


}
