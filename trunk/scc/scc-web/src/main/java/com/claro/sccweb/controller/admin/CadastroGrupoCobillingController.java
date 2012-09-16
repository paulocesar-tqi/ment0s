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

import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.CadastroGrupoCobillingValidator;
import com.claro.sccweb.decorator.rownum.entity.SccGrupoCobillingDecorator;
import com.claro.sccweb.form.CadastroGrupoCobillingForm;
import com.claro.sccweb.service.SccGrupoCobillingService;

@Controller
@RequestMapping(value="/user/admin/grupo")
public class CadastroGrupoCobillingController extends BaseCRUDAndMethodController<CadastroGrupoCobillingForm> {
	
	@Autowired
	private SccGrupoCobillingService sccGrupoCobillingService;
	
	private CadastroGrupoCobillingValidator validator = new CadastroGrupoCobillingValidator();
	
	
	@RequestMapping(value="/new") 
	public ModelAndView iniciar(HttpServletRequest request,HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView(getViewName());
		super.iniciar(request, response);
		
		List<SccGrupoCobilling> rows = sccGrupoCobillingService.findAll();
		List<SccGrupoCobillingDecorator> decoratorList = new ArrayList<SccGrupoCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccGrupoCobillingDecorator decorator = new SccGrupoCobillingDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), getForm());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}


	@Override
	protected ModelAndView limpar(HttpServletRequest request, HttpServletResponse response, CadastroGrupoCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;

	}

	@Override
	protected ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, CadastroGrupoCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;

	}

	@Override
	protected ModelAndView salvar(HttpServletRequest request, HttpServletResponse response, CadastroGrupoCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		Date data = Calendar.getInstance().getTime();
		
		ModelAndView mav = new ModelAndView(getViewName());
		form.getEntity().setDtCriacao(data);
		form.getEntity().setDtInicioVigencia(data);
		form.getEntity().setCdUsuarioManutencao(getSessionDataManager().getUserPrincipal());
		sccGrupoCobillingService.create(form.getEntity());
		return mav;

	}

	@Override
	protected ModelAndView remover(HttpServletRequest request, HttpServletResponse response, CadastroGrupoCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		SccGrupoCobillingDecorator decorator =  (SccGrupoCobillingDecorator)form.getEntidadeSelecionada();
		sccGrupoCobillingService.delete(decorator.getRow());
		return mav;

	}
	
	
	@Override
	protected ModelAndView atualizar(HttpServletRequest request, HttpServletResponse response, CadastroGrupoCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		form.getEntity().setDtAtualizacao(Calendar.getInstance().getTime());
		sccGrupoCobillingService.update(form.getEntity());
		return mav;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView editar(HttpServletRequest request, HttpServletResponse response, 
			CadastroGrupoCobillingForm form, BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccGrupoCobillingDecorator> decoratorList = (List<SccGrupoCobillingDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		SccGrupoCobillingDecorator decorator = decoratorList.get(form.getItemSelecionado());
		form.setEntity(decorator.getRow());
		mav.addObject(FORM_NAME, form);
		return mav;

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

	@Override
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccGrupoCobillingDecorator decorator = (SccGrupoCobillingDecorator)entidadeSelecionada;
		return decorator.getRow().getCodigo();
	}

	@Override
	protected void atualizarDadosTabela(HttpServletRequest request)	throws Exception {
		
		List<SccGrupoCobilling> rows = sccGrupoCobillingService.findAll();
		List<SccGrupoCobillingDecorator> decoratorList = new ArrayList<SccGrupoCobillingDecorator>(rows.size());
		for (int i = 0; i < rows.size(); i++) {
			SccGrupoCobillingDecorator decorator = new SccGrupoCobillingDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		
	}

	public SccGrupoCobillingService getSccGrupoCobillingService() {
		return sccGrupoCobillingService;
	}


	public void setSccGrupoCobillingService(
			SccGrupoCobillingService sccGrupoCobillingService) {
		this.sccGrupoCobillingService = sccGrupoCobillingService;
	}

}
