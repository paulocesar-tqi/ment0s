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
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccEmailCobilling;
import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.SccEmailGrupoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccEmailDecorator;
import com.claro.sccweb.decorator.rownum.entity.SccGrupoDecorator;
import com.claro.sccweb.form.CadastroEmailGrupoForm;
import com.claro.sccweb.service.SccComposicaoGrupoEmailService;
import com.claro.sccweb.service.SccGrupoCobillingService;


@Controller
@RequestMapping(value="/user/admin/email/grupo")
public class CadastroEmailGrupoController extends BaseCRUDAndMethodController<CadastroEmailGrupoForm> {
	
	private final SccEmailGrupoValidator validator = new SccEmailGrupoValidator();
	
	private static final String FWD_EMAIL_GRUPO = "cadastro_email_grupo";
	
	private static final String PESQUISAR = "CRUD.pesquisar";
	
	private static final String EDITAR = "CRUD.editar";
	
	private static final String EDITAR_EMAIL = "CRUD.editar.email";
	
	private static final String ATUALIZAR ="CRUD.atualizar";
	
	private static final String SALVAR = "CRUD.salvar"; 

	
	@Autowired
	private SccGrupoCobillingService sccGrupoCobillingService;
	
	@SuppressWarnings("unused")
	@Autowired
	private SccComposicaoGrupoEmailService sccComposicaoGrupoEmailService;


	@RequestMapping(value="/execute" , method=RequestMethod.POST)
	public ModelAndView executa(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(FORM_NAME)  CadastroEmailGrupoForm form,BindingResult bindingResult,Model model) throws Exception {
		
		ModelAndView mav = null;
		
		String operacao = form.getOperacao();
		if (operacao.equalsIgnoreCase(PESQUISAR)) {
			mav = pesquisar(request, response, form, bindingResult, model);
			 
		} else if(operacao.equalsIgnoreCase(EDITAR)){
			mav = editar(request, response, form, bindingResult, model);
		} else if(operacao.equalsIgnoreCase(EDITAR_EMAIL)){
			mav = editarEmailAssociado(request, response, form, bindingResult, model);
		} else if(operacao.equalsIgnoreCase(ATUALIZAR)){
			mav = atualizar(request, response, form, bindingResult, model);
		}
		return mav;		  
	}

	
	
	@Override
	protected ModelAndView limpar(HttpServletRequest request,
			HttpServletResponse response, CadastroEmailGrupoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ModelAndView pesquisar(HttpServletRequest request,
			HttpServletResponse response, CadastroEmailGrupoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		
		List<SccGrupoCobilling> list = (List<SccGrupoCobilling>) this.sccGrupoCobillingService.pesquisarBySeqGrupo(form.getSeqGrupo());
		List<SccGrupoDecorator> decoratorList = new ArrayList<SccGrupoDecorator>(list.size());
		form.setListGrupoCobilling(list);
		for (int i = 0; i < list.size(); i++) {
			SccGrupoDecorator decorator = new SccGrupoDecorator(list.get(i), i);
			decoratorList.add(decorator);
			
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName(), "filtro", form);
		cacheMyForm(getClass(), form);
		
		
		return mav;

	}

	@Override
	protected ModelAndView salvar(HttpServletRequest request,
			HttpServletResponse response, CadastroEmailGrupoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ModelAndView remover(HttpServletRequest request,
			HttpServletResponse response, CadastroEmailGrupoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ModelAndView atualizar(HttpServletRequest request,
			HttpServletResponse response, CadastroEmailGrupoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView editar(HttpServletRequest request,
			HttpServletResponse response, CadastroEmailGrupoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		int itemIndex = form.getItemSelecionado();
		SccGrupoDecorator entity = ((List<SccGrupoDecorator>) request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		
		List<SccEmailCobilling> listEmail = this.sccComposicaoGrupoEmailService.gerarListaEmailCadastrado(entity.getSeqGrupo());
		List<SccEmailDecorator> decoratorList = new ArrayList<SccEmailDecorator>(listEmail.size());
		for (int i = 0; i < listEmail.size(); i++) {
			SccEmailDecorator decorator = new SccEmailDecorator(listEmail.get(i), i);
			decoratorList.add(decorator);
			
		}
		form.setListEmailCobilling(listEmail);
		storeInSession(getClass(), DISPLAY_TAG_SPACE_2, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName(), "filtro", form);
		cacheMyForm(getClass(), form);
		
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	private ModelAndView editarEmailAssociado(HttpServletRequest request,	HttpServletResponse response, CadastroEmailGrupoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		int itemIndex = form.getItemSelecionado();
		SccEmailDecorator entity = ((List<SccEmailDecorator>) request.getSession().getAttribute(DISPLAY_TAG_SPACE_2)).get(itemIndex);
		
		form.setNoEmail(entity.getEmail());
		ModelAndView mav = new ModelAndView(getViewName(), "filtro", form);
		cacheMyForm(getClass(), form);
		
				return mav;
		
	}

	@Override
	protected String getViewName() {
		
		return FWD_EMAIL_GRUPO;
	}

	@Override
	protected CadastroEmailGrupoForm getForm() {
		
		return new CadastroEmailGrupoForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}

	@Override
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void atualizarDadosTabela(HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@ModelAttribute("grupos")
	public List<SccGrupoCobilling> gerarComboGrupo() throws DAOException{
		
		return this.sccGrupoCobillingService.findAll();
		
	}

	public SccGrupoCobillingService getSccGrupoCobillingService() {
		return sccGrupoCobillingService;
	}

	public void setSccGrupoCobillingService(
			SccGrupoCobillingService sccGrupoCobillingService) {
		this.sccGrupoCobillingService = sccGrupoCobillingService;
	}

	public void setSccComposicaoGrupoEmailService(
			SccComposicaoGrupoEmailService sccComposicaoGrupoEmailService) {
		this.sccComposicaoGrupoEmailService = sccComposicaoGrupoEmailService;
	}
	
	


}
