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

import com.claro.cobillingweb.persistence.entity.SccEmailCobilling;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.CadastroEmailCobillingValidator;
import com.claro.sccweb.decorator.rownum.entity.SccEmailCobillingDecorator;
import com.claro.sccweb.form.CadastroEmailCobillingForm;
import com.claro.sccweb.service.SccEmailCobillingService;

@Controller
@RequestMapping(value="/user/admin/email")
public class CadastroEmailCobillingController extends
		BaseCRUDAndMethodController<CadastroEmailCobillingForm> {
	
	@Autowired
	private SccEmailCobillingService sccEmailCobillingService;
	
	private CadastroEmailCobillingValidator validator = new CadastroEmailCobillingValidator();
	
	@RequestMapping(value="/new") 
	public ModelAndView iniciar(HttpServletRequest request,HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView(getViewName());
		super.iniciar(request, response);
		
		List<SccEmailCobilling> rows = sccEmailCobillingService.findAll();
		List<SccEmailCobillingDecorator> decoratorList = new ArrayList<SccEmailCobillingDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			SccEmailCobillingDecorator decorator = new SccEmailCobillingDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), getForm());
		mav.addObject(FORM_NAME, getForm());
		return mav;
		
	}


	@Override
	protected ModelAndView limpar(HttpServletRequest request, HttpServletResponse response, CadastroEmailCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;

	}

	@Override
	protected ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, CadastroEmailCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;

	}

	@Override
	protected ModelAndView salvar(HttpServletRequest request, HttpServletResponse response, CadastroEmailCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		Date data = Calendar.getInstance().getTime();
		
		ModelAndView mav = new ModelAndView(getViewName());
		form.getEntity().setDtCriacao(data);
		form.getEntity().setCdUsuarioManutencao(getSessionDataManager().getUserPrincipal());
		sccEmailCobillingService.create(form.getEntity());
		return mav;

	}

	@Override
	protected ModelAndView remover(HttpServletRequest request, HttpServletResponse response, CadastroEmailCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		SccEmailCobillingDecorator decorator =  (SccEmailCobillingDecorator)form.getEntidadeSelecionada();
		sccEmailCobillingService.delete(decorator.getRow());
		return mav;

	}

	@Override
	protected ModelAndView atualizar(HttpServletRequest request, HttpServletResponse response, CadastroEmailCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		form.getEntity().setDtAtualizacao(Calendar.getInstance().getTime());
		sccEmailCobillingService.update(form.getEntity());
		return mav;

	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView editar(HttpServletRequest request, HttpServletResponse response, CadastroEmailCobillingForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccEmailCobillingDecorator> decoratorList = (List<SccEmailCobillingDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1);
		SccEmailCobillingDecorator decorator = decoratorList.get(form.getItemSelecionado());
		form.setEntity(decorator.getRow());
		mav.addObject(FORM_NAME, form);
		return mav;

	}

	@Override
	protected String getViewName() {
		
		return "cadastro_email_cobilling_filtro";
	}

	@Override
	protected CadastroEmailCobillingForm getForm() {
		
		return new CadastroEmailCobillingForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}

	@Override
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		
		SccEmailCobillingDecorator decorator = (SccEmailCobillingDecorator)entidadeSelecionada;
		return decorator.getRow().getCodigo();

	}

	@Override
	protected void atualizarDadosTabela(HttpServletRequest request)
			throws Exception {
		
		List<SccEmailCobilling> rows = sccEmailCobillingService.findAll();
		List<SccEmailCobillingDecorator> decoratorList = new ArrayList<SccEmailCobillingDecorator>(rows.size());
		for (int i = 0; i < rows.size(); i++) {
			SccEmailCobillingDecorator decorator = new SccEmailCobillingDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);

		
	}

	public SccEmailCobillingService getSccEmailCobillingService() {
		return sccEmailCobillingService;
	}

	public void setSccEmailCobillingService(SccEmailCobillingService sccEmailCobillingService) {
		this.sccEmailCobillingService = sccEmailCobillingService;
	}

	public void setValidator(CadastroEmailCobillingValidator validator) {
		this.validator = validator;
	}
	
	

}
