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

import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccServicoAdicional;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.CadastroChargeCodeValidator;
import com.claro.sccweb.decorator.rownum.entity.SccServicoAdicionalDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.CadastroChargeCodeForm;


@Controller
@RequestMapping(value="/user/produto/chargecode")
public class CadastroChargeCodeController extends BaseCRUDAndMethodController<CadastroChargeCodeForm> {

	private final CadastroChargeCodeValidator validator = new CadastroChargeCodeValidator();
	
	 
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, CadastroChargeCodeForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		form = getForm();
		mav.addObject(FORM_NAME, form);		
		return mav;
	}

	 
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, CadastroChargeCodeForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		List<SccServicoAdicional> rows = getServiceManager().getProdutoService().pesquisaByProduto(form.getCdProdutoCobilling());
		List<SccServicoAdicionalDecorator> decoratorList = new ArrayList<SccServicoAdicionalDecorator>();
		for (int i=0;i<rows.size();i++)
			{
			SccServicoAdicionalDecorator decorator = new SccServicoAdicionalDecorator(rows.get(i), i);
			decoratorList.add(decorator);			
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), form);
		mav.addObject(FORM_NAME, form);
		return mav;
	}

	 
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, CadastroChargeCodeForm form,BindingResult bindingResult, Model model) throws Exception {
		SccServicoAdicional entity = form.getEntity();
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		entity.getSccProdutoCobilling().setCdProdutoCobilling(form.getCdProdutoCobilling());
		getServiceManager().getProdutoService().create(entity);		
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, CadastroChargeCodeForm form,BindingResult bindingResult, Model model) throws Exception {
		SccServicoAdicionalDecorator decorator = (SccServicoAdicionalDecorator)form.getEntidadeSelecionada();
		SccServicoAdicional entity = decorator.getRow();
		getServiceManager().getProdutoService().delete(entity);		
		atualizarDadosTabela(request);
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, CadastroChargeCodeForm form,BindingResult bindingResult, Model model) throws Exception {		
		CadastroChargeCodeForm cachedForm = (CadastroChargeCodeForm)getMyFormFromCache(getClass());
		SccServicoAdicional entity = form.getEntity();
		entity.setDtAtualizacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		entity.setSccProdutoCobilling(new SccProdutoCobilling());
		entity.getSccProdutoCobilling().setCdProdutoCobilling(cachedForm.getCdProdutoCobilling());
		getServiceManager().getProdutoService().update(entity);		
		atualizarDadosTabela(request);
		return limpar(request, response, form, bindingResult, model);
	}

	 
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, CadastroChargeCodeForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccServicoAdicionalDecorator decorator = (SccServicoAdicionalDecorator)form.getEntidadeSelecionada();
		SccServicoAdicional entity = decorator.getRow();
		form.setEntity(entity);		
		mav.addObject(FORM_NAME, form);
		return mav;
	}

	 
	protected String getViewName() {		
		return "produto_pos_chargecode";
	}

	 
	protected CadastroChargeCodeForm getForm() {		
		return new CadastroChargeCodeForm();
	}

	 
	protected Validator getValidator() {		
		return this.validator;
	}

	 
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccServicoAdicionalDecorator decorator = (SccServicoAdicionalDecorator)entidadeSelecionada;
		return decorator.getRow().getCdCharge();		
	}

	 
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		CadastroChargeCodeForm form = (CadastroChargeCodeForm)getMyFormFromCache(getClass());
		 if (form == null){
			 cacheMyForm(getClass(), form);
			 form = (CadastroChargeCodeForm)getMyFormFromCache(getClass());
		 }		
		
		List<SccServicoAdicional> rows = getServiceManager().getProdutoService().pesquisaByProduto(form.getCdProdutoCobilling());
		List<SccServicoAdicionalDecorator> decoratorList = new ArrayList<SccServicoAdicionalDecorator>();
		for (int i=0;i<rows.size();i++)
			{
			SccServicoAdicionalDecorator decorator = new SccServicoAdicionalDecorator(rows.get(i), i);
			decoratorList.add(decorator);			
			}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		
	}
	
	@ModelAttribute("tiposCharge")
	public List<BasicStringItem> populaTiposCharge()
	{
		 List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		 comboList.add(new BasicStringItem("R", "Recorrente"));
		 comboList.add(new BasicStringItem("U", "Uso"));
		 return comboList;
	}
	
	@ModelAttribute("produtos")
	public List<SccProdutoCobilling> populaProdutosCobilling() throws Exception
	{
		return getServiceManager().getProdutoService().getAll();		
	}
	
	public ModelAndView novo(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView(getViewName());
		CadastroChargeCodeForm form = (CadastroChargeCodeForm)_form;
		form.setOperacao("novo");
		form.setEntity(new SccServicoAdicional());
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
}
