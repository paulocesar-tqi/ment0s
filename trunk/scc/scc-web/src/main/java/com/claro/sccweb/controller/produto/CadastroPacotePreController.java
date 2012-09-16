package com.claro.sccweb.controller.produto;

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
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccPacotePrepago;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.CadastroPacotePreValidator;
import com.claro.sccweb.decorator.rownum.entity.SccPacotePrepagoDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.CadastroPacotePreForm;

@Controller
@RequestMapping(value="/user/produto/pre/pacote")
public class CadastroPacotePreController extends BaseCRUDAndMethodController<CadastroPacotePreForm>{
	
	private final CadastroPacotePreValidator validator = new CadastroPacotePreValidator(); 
	
	 
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, CadastroPacotePreForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		form = new CadastroPacotePreForm();
		mav.addObject(FORM_NAME, form);
		return mav;
	}

	 
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, CadastroPacotePreForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccPacotePrepago> rows = getServiceManager().getProdutoPrepagoService().pesquisaPacotes(form.getCdPacotePrepago(), form.getNoPacotePrepago(), form.getCdProdutoPrepago());
		List<SccPacotePrepagoDecorator> decoratorList = new ArrayList<SccPacotePrepagoDecorator>();
		for (int i=0;i<rows.size();i++)
			{
			SccPacotePrepagoDecorator decorator = new SccPacotePrepagoDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		mav.addObject(FORM_NAME, form);
		return mav;
	}

	 
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, CadastroPacotePreForm form,BindingResult bindingResult, Model model) throws Exception {
		SccPacotePrepago entity = form.getEntity();
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		getServiceManager().getProdutoPrepagoService().create(entity);
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, CadastroPacotePreForm form,BindingResult bindingResult, Model model) throws Exception {
		SccPacotePrepagoDecorator decorator = (SccPacotePrepagoDecorator)form.getEntidadeSelecionada();
		getServiceManager().getProdutoPrepagoService().delete(decorator.getRow());		
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, CadastroPacotePreForm form,BindingResult bindingResult, Model model) throws Exception {
		SccPacotePrepago entity = form.getEntity();
		entity.setDtAtualizacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		getServiceManager().getProdutoPrepagoService().update(entity);		
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, CadastroPacotePreForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccPacotePrepagoDecorator decorator = (SccPacotePrepagoDecorator)form.getEntidadeSelecionada();
		form.setEntity(decorator.getRow());
		mav.addObject(FORM_NAME, form);
		return mav;
	}

	 
	protected String getViewName() {		
		return "produtos_pre_pacote";
	}

	 
	protected CadastroPacotePreForm getForm() {
		return new CadastroPacotePreForm();
	}

	 
	protected Validator getValidator() {
		return this.validator;
	}

	 
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccPacotePrepagoDecorator decorator = (SccPacotePrepagoDecorator)entidadeSelecionada;
		return decorator.getRow().getCdPacotePrepago();
	}

	 
	protected void atualizarDadosTabela(HttpServletRequest request)throws Exception {
		CadastroPacotePreForm form = (CadastroPacotePreForm)getMyFormFromCache(getClass());
		if (form != null)
			{
			List<SccPacotePrepago> rows = getServiceManager().getProdutoPrepagoService().pesquisaPacotes(form.getCdPacotePrepago(), form.getNoPacotePrepago(), form.getCdProdutoPrepago());
			List<SccPacotePrepagoDecorator> decoratorList = new ArrayList<SccPacotePrepagoDecorator>();
			for (int i=0;i<rows.size();i++)
				{
				SccPacotePrepagoDecorator decorator = new SccPacotePrepagoDecorator(rows.get(i), i);
				decoratorList.add(decorator);
				}
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
			}
		
	}

	public ModelAndView novo(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView(getViewName());
		CadastroPacotePreForm form = new CadastroPacotePreForm();
		form.setOperacao("novo");
		form.setEntity(new SccPacotePrepago());
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	@ModelAttribute("produtos")
	public List<SccProdutoPrepago> populaProdutosCobilling() throws Exception
	{
		List<SccProdutoPrepago> comboList = new ArrayList<SccProdutoPrepago>();
		SccProdutoPrepago nullValue = new SccProdutoPrepago();
		nullValue.setNoProdutoPrepago("Todos");
		nullValue.setCdProdutoPrepago(BasicDAO.GET_ALL_STRING);
		comboList.add(0,nullValue);
		List<SccProdutoPrepago> rows = getServiceManager().getProdutoPrepagoService().getAll();
		comboList.addAll(rows);
		return comboList;
	}
	
	
}
