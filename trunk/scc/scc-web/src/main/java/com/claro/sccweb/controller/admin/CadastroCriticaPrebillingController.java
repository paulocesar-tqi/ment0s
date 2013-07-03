package com.claro.sccweb.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccCriticaPrebilling;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.CadastroCriticaPrebillingValidator;
import com.claro.sccweb.form.CadastroCriticaPrebillingForm;
import com.claro.sccweb.service.CadastroCriticaPrebillingService;
import com.claro.sccweb.service.SessionDataManager;

@Controller
@RequestMapping(value="/user/admin/critica/prebilling")
public class CadastroCriticaPrebillingController extends BaseOperationController<CadastroCriticaPrebillingForm> {

	private static final String FWD_VIEW_CRITICA = "cadastro_critica_prebilling";

	
	private final CadastroCriticaPrebillingValidator validator = new CadastroCriticaPrebillingValidator();
	
	@Autowired
	private SessionDataManager serviceDataManager;
	
	@Autowired
	private CadastroCriticaPrebillingService cadastroCriticaPrebillingService;

	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisarByFiltro(HttpServletRequest request, HttpServletResponse response, CadastroCriticaPrebillingForm form) throws Exception {
		
		ModelAndView mav = null;
		
		List<SccCriticaPrebilling> lst = this.cadastroCriticaPrebillingService.findAll();
		
		form.setLstCritica(lst);
		mav = new ModelAndView(FWD_VIEW_CRITICA, "filtro", form);
		return mav;
		
	}
	

	@RequestMapping(value = "/editarcritica", method=RequestMethod.GET)
	public @ResponseBody  SccCriticaPrebilling editarCritica(@RequestParam("id") String id , HttpServletRequest request,HttpServletResponse response, CadastroCriticaPrebillingForm form) throws Exception {
		
		
		SccCriticaPrebilling entidade = this.cadastroCriticaPrebillingService.findById(id);
		
		form.setEntidade(entidade);
		
		return form.getEntidade();
	}
	
	@RequestMapping(value="/salvarEntity", method=RequestMethod.POST)
	public @ResponseBody ModelAndView salvar(HttpServletRequest request, HttpServletResponse response, CadastroCriticaPrebillingForm form) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_CRITICA);
		
		Date data = new Date();
		form.getEntidade().setCdUsuarioManut(this.serviceDataManager.getUserPrincipal());
		form.getEntidade().setDtAtualizacao(data);
		if(form.getEntidade().getDtCriacao() == null || form.getEntidade().getDtCriacao().equals("")){
			form.getEntidade().setDtCriacao(data);
		}
		this.cadastroCriticaPrebillingService.saveOrUpdate(form.getEntidade());
		mav.addObject("filtro", form);	
		return mav;

	}
	
	@RequestMapping(value = "/removercritica", method=RequestMethod.DELETE)
	public ModelAndView removerCritica(@RequestParam("id") String id , HttpServletRequest request,HttpServletResponse response, CadastroCriticaPrebillingForm form) throws Exception {
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_CRITICA);
		
		this.cadastroCriticaPrebillingService.excluirCriticaPrebilling(id);
		
		mav.addObject("filtro", form);
		return mav;
	}
	
	
	
	@RequestMapping(value = "/novocritica", method=RequestMethod.POST)
	public ModelAndView novoCritica(HttpServletRequest request,HttpServletResponse response, CadastroCriticaPrebillingForm form) throws Exception {
		
		ModelAndView mav = null;
		form.setEntidade(new SccCriticaPrebilling());
		form.getEntidade().setDtCriacao(new Date());
		form.getEntidade().setDtAtualizacao(new Date());
		mav = new ModelAndView(FWD_VIEW_CRITICA, "filtro", form);
		return mav;
	}

	
	@Override
	protected String getViewName() {

		return FWD_VIEW_CRITICA;
	}

	@Override
	protected CadastroCriticaPrebillingForm getForm() {
		
		return new CadastroCriticaPrebillingForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}

	public void setCadastroCriticaPrebillingService(
			CadastroCriticaPrebillingService cadastroCriticaPrebillingService) {
		this.cadastroCriticaPrebillingService = cadastroCriticaPrebillingService;
	}


	public void setServiceDataManager(SessionDataManager serviceDataManager) {
		this.serviceDataManager = serviceDataManager;
	}


}
