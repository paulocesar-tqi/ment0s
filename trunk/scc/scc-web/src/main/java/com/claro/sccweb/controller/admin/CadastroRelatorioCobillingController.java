package com.claro.sccweb.controller.admin;

import java.io.Serializable;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccRelatorioCobilling;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.CadastroRelatorioCobilligValidator;
import com.claro.sccweb.decorator.rownum.entity.SccRelatorioCobillingDecorator;
import com.claro.sccweb.form.CadastroRelatorioCobillingForm;
import com.claro.sccweb.service.SccRelatorioCobillingService;

@Controller
@RequestMapping(value="/user/admin/relatorio")
public class CadastroRelatorioCobillingController extends BaseCRUDAndMethodController<CadastroRelatorioCobillingForm> {
	
	
	@Autowired
	private SccRelatorioCobillingService sccRelatorioCobillingService;
	
	private CadastroRelatorioCobilligValidator validator = new CadastroRelatorioCobilligValidator();
	
	
	@RequestMapping(value="/new") 
	public ModelAndView iniciar(HttpServletRequest request,HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView(getViewName());
		super.iniciar(request, response);
		
		List<SccRelatorioCobilling> rows = sccRelatorioCobillingService.findAll();
		List<SccRelatorioCobillingDecorator> decoratorList = new ArrayList<SccRelatorioCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccRelatorioCobillingDecorator decorator = new SccRelatorioCobillingDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), getForm());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}


	@Override
	protected ModelAndView limpar(HttpServletRequest request, HttpServletResponse response, CadastroRelatorioCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;

	}

	@Override
	protected ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, CadastroRelatorioCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;

	}

	@Override
	protected ModelAndView salvar(HttpServletRequest request,
			HttpServletResponse response, CadastroRelatorioCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		Date data = Calendar.getInstance().getTime();
		
		ModelAndView mav = new ModelAndView(getViewName());
		form.getEntity().setDtCriacao(data);
		form.getEntity().setDtInicioVigencia(data);
		form.getEntity().setCdUsuarioManutencao(getSessionDataManager().getUserPrincipal());
		sccRelatorioCobillingService.create(form.getEntity());
		return mav;

	}

	@Override
	protected ModelAndView remover(HttpServletRequest request, HttpServletResponse response, CadastroRelatorioCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		SccRelatorioCobillingDecorator decorator =  (SccRelatorioCobillingDecorator)form.getEntidadeSelecionada();
		sccRelatorioCobillingService.delete(decorator.getRow());
		return mav;

	}

	@Override
	protected ModelAndView atualizar(HttpServletRequest request,
			HttpServletResponse response, CadastroRelatorioCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		form.getEntity().setDtAtualizacao(Calendar.getInstance().getTime());
		sccRelatorioCobillingService.update(form.getEntity());
		return mav;

	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView editar(HttpServletRequest request,
			HttpServletResponse response, CadastroRelatorioCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccRelatorioCobillingDecorator> decoratorList = (List<SccRelatorioCobillingDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		SccRelatorioCobillingDecorator decorator = decoratorList.get(form.getItemSelecionado());
		form.setEntity(decorator.getRow());
		mav.addObject(FORM_NAME, form);
		return mav;

	}

	@Override
	protected String getViewName() {
		
		return "cadastro_relatorio_cobilling_filtro";
	}

	@Override
	protected CadastroRelatorioCobillingForm getForm() {
		return new CadastroRelatorioCobillingForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}

	@Override
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		
		SccRelatorioCobillingDecorator decorator = (SccRelatorioCobillingDecorator)entidadeSelecionada;
		return decorator.getRow().getCodigo();
	}

	@Override
	protected void atualizarDadosTabela(HttpServletRequest request)
			throws Exception {
		
		List<SccRelatorioCobilling> rows = sccRelatorioCobillingService.findAll();
		List<SccRelatorioCobillingDecorator> decoratorList = new ArrayList<SccRelatorioCobillingDecorator>(rows.size());
		for (int i = 0; i < rows.size(); i++) {
			SccRelatorioCobillingDecorator decorator = new SccRelatorioCobillingDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		
		
		
	}

	public SccRelatorioCobillingService getSccRelatorioCobillingService() {
		return sccRelatorioCobillingService;
	}

	public void setSccRelatorioCobillingService(SccRelatorioCobillingService sccRelatorioCobillingService) {
		this.sccRelatorioCobillingService = sccRelatorioCobillingService;
	}

	public void setValidator(CadastroRelatorioCobilligValidator validator) {
		this.validator = validator;
	}


}
