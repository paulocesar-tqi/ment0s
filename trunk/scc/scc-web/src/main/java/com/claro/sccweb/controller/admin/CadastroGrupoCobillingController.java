package com.claro.sccweb.controller.admin;

import java.util.Calendar;
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

import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.CadastroGrupoCobillingValidator;
import com.claro.sccweb.form.CadastroGrupoCobillingForm;
import com.claro.sccweb.service.SccGrupoCobillingService;
import com.claro.sccweb.service.SessionDataManager;

@Controller
@RequestMapping(value="/user/admin/grupo")
public class CadastroGrupoCobillingController extends BaseOperationController<CadastroGrupoCobillingForm> {
	
	private static final String FWD_VIEW_LISTA_GRUPO = "cadastro_grupo_cobilling_filtro";
	
	@Autowired
	private SccGrupoCobillingService sccGrupoCobillingService;
	
	private CadastroGrupoCobillingValidator validator = new CadastroGrupoCobillingValidator();
	
	
	@Autowired
	private SessionDataManager sessionDataManager;
	
	
	@RequestMapping(value="grupo", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listarTodos(HttpServletRequest request, HttpServletResponse response, CadastroGrupoCobillingForm form) throws Exception {
		
		List<SccGrupoCobilling> lstSccGrupo = (List<SccGrupoCobilling>) sccGrupoCobillingService.findAll();
		
		form.setListGrupo(lstSccGrupo);
		form.setEntity(new SccGrupoCobilling());
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_GRUPO, "filtro", form);
		
		return mav;
		
	}
	
	@RequestMapping(value = "/atualizarGrupo", method=RequestMethod.POST)
	public @ResponseBody ModelAndView updateEntity(HttpServletRequest request, HttpServletResponse response, CadastroGrupoCobillingForm form) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_GRUPO);
		
		String usuario = this.sessionDataManager.getUserPrincipal();
		form.getEntity().setCdUsuarioManut(usuario);
		form.getEntity().setDtAtualizacao(new Date());
		setarDataVigencia(form.getEntity());
		this.sccGrupoCobillingService.update(form.getEntity());

		
		return mav;
	}
	
	private void setarDataVigencia(SccGrupoCobilling entity){
		if(entity.getDtInicioVigencia() == null){
			entity.setDtInicioVigencia(new Date());
		}
		if(entity.getDtFimVigencia() == null){
			entity.setDtFimVigencia(new Date());
		}
	}
	
	@RequestMapping(value="/salvarGrupo", method=RequestMethod.POST)
	public @ResponseBody ModelAndView saveEntity(HttpServletRequest request, HttpServletResponse response, CadastroGrupoCobillingForm form) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_GRUPO);
		String usuario = this.sessionDataManager.getUserPrincipal();
		form.getEntity().setCdUsuarioManut(usuario);
		form.getEntity().setDtAtualizacao(Calendar.getInstance().getTime());
		form.getEntity().setDtCriacao(Calendar.getInstance().getTime());
		setarDataVigencia(form.getEntity());
		this.sccGrupoCobillingService.create(form.getEntity());

		return mav;
	}
	
	
	
	@RequestMapping(value = "/editarGrupo", method=RequestMethod.GET)
	public @ResponseBody SccGrupoCobilling editEntity(@RequestParam("sqGrupo") Long sqGrupo , HttpServletRequest request,HttpServletResponse response, CadastroGrupoCobillingForm form) throws Exception {
		
		SccGrupoCobilling entity = this.sccGrupoCobillingService.findById(sqGrupo);
		form.setEntity(entity);
		
		return form.getEntity();
	}
	
	@RequestMapping(value = "/removerGrupo", method=RequestMethod.DELETE)
	public @ResponseBody ModelAndView removerGrupo(@RequestParam("sqGrupo") Long sqGrupo , HttpServletRequest request,HttpServletResponse response, CadastroGrupoCobillingForm form) throws Exception {
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_GRUPO);
		
		this.sccGrupoCobillingService.deleteGrupo(sqGrupo);
		
		mav.addObject("filtro", form);
		return mav;
	}
	
	
	public Date setarData(Date data){
		Date dtAtual = null;
		if(data == null){
			dtAtual = new Date();
		}
		return dtAtual;
	}
	

	@Override
	protected String getViewName() {
		return "cadastro_grupo_cobilling_filtro";
	}

	@Override
	protected CadastroGrupoCobillingForm getForm() {
		
		return new CadastroGrupoCobillingForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}


	public SccGrupoCobillingService getSccGrupoCobillingService() {
		return sccGrupoCobillingService;
	}


	public void setSccGrupoCobillingService(
			SccGrupoCobillingService sccGrupoCobillingService) {
		this.sccGrupoCobillingService = sccGrupoCobillingService;
	}

	public void setSessionDataManager(SessionDataManager sessionDataManager) {
		this.sessionDataManager = sessionDataManager;
	}
	
	

}
