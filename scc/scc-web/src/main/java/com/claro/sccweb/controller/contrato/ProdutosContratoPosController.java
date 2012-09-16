package com.claro.sccweb.controller.contrato;

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

import com.claro.cobillingweb.persistence.entity.SccContratoCobl;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratado;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.ControllerExecutionException;
import com.claro.sccweb.controller.validator.ProdutosContratoPosValidator;
import com.claro.sccweb.decorator.rownum.entity.SccProdutoContratadoDecorator;
import com.claro.sccweb.form.ProdutosContratoPosForm;

@Controller
@RequestMapping(value="/user/contrato/produtos")
public class ProdutosContratoPosController extends BaseCRUDAndMethodController<ProdutosContratoPosForm>{
	
	private final ProdutosContratoPosValidator validator = new ProdutosContratoPosValidator();
	
	@RequestMapping(value="/back") 
	public ModelAndView back(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ProdutosContratoPosForm cachedForm = (ProdutosContratoPosForm)getMyFormFromCache(getClass());		 		
		 if (cachedForm == null) {
			 throw new ControllerExecutionException("Navegação Inválida! ProdutosContratoPosForm not found");
		 }
		 ModelAndView mav = pesquisar(request, response, cachedForm, null, null);
		 mav.addObject(FORM_NAME, cachedForm);
		 return mav;	 
	}
	
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, ProdutosContratoPosForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		form.setEntity(new SccProdutoContratado());
		mav.addObject(FORM_NAME, form);
		return mav; 
	}
	
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, ProdutosContratoPosForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		
		List<SccProdutoContratado> rows = getServiceManager().getContratoService().pesquisaProdutosContratados(form.getCdContratoCobilling());
		List<SccProdutoContratadoDecorator> decoratorList = new ArrayList<SccProdutoContratadoDecorator>();
		
		for (int i=0;i<rows.size();i++) {			
			SccProdutoContratadoDecorator decorator = new SccProdutoContratadoDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), form);
		return mav;
	}
	
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, ProdutosContratoPosForm form,BindingResult bindingResult, Model model) throws Exception {
		ProdutosContratoPosForm cachedForm = (ProdutosContratoPosForm)getMyFormFromCache(getClass());
		SccProdutoContratado entity = form.getEntity();
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		entity.getId().setCdContratoCobilling(cachedForm.getCdContratoCobilling());
		
		SccContratoCobl cobl = new SccContratoCobl();
		cobl.setCdContratoCobilling(cachedForm.getCdContratoCobilling());
		entity.setSccContratoCobl(cobl);		
		if (entity.getFgProdutoPadrao() == null) {
			entity.setFgProdutoPadrao("N");
		}
		getServiceManager().getContratoService().create(entity);
		ModelAndView mav = pesquisar(request, response, cachedForm, bindingResult, model);
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, ProdutosContratoPosForm form,BindingResult bindingResult, Model model) throws Exception {
		ProdutosContratoPosForm cachedForm = (ProdutosContratoPosForm)getMyFormFromCache(getClass());
		SccProdutoContratado entity = ((SccProdutoContratadoDecorator)form.getEntidadeSelecionada()).getRow();
		getServiceManager().getContratoService().delete(entity);
		ModelAndView mav = pesquisar(request, response, cachedForm, bindingResult, model);
		return mav;		
	}
	
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, ProdutosContratoPosForm form,BindingResult bindingResult, Model model) throws Exception {
		ProdutosContratoPosForm cachedForm = (ProdutosContratoPosForm)getMyFormFromCache(getClass());
		SccProdutoContratado entity = form.getEntity();
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		if(entity.getFgProdutoPadrao() == null){
			entity.setFgProdutoPadrao("N");
		}
		getServiceManager().getContratoService().update(entity);		
		ModelAndView mav = pesquisar(request, response, cachedForm, bindingResult, model);
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, ProdutosContratoPosForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccProdutoContratadoDecorator decorator = (SccProdutoContratadoDecorator)form.getEntidadeSelecionada();
		form.setEntity(decorator.getRow());
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	protected String getViewName() {
		return "produtos_contrato_pos_cadastro";
	}
	
	protected ProdutosContratoPosForm getForm() {
		ProdutosContratoPosForm form = new ProdutosContratoPosForm();
		SccProdutoContratado entity = new SccProdutoContratado();
		form.setEntity(entity);
		return form;
	}
	
	protected Validator getValidator() {
		return validator;
	}
	
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccProdutoContratadoDecorator decorator = (SccProdutoContratadoDecorator)entidadeSelecionada;
		return decorator.getRow().getId();
	}
	
	@ModelAttribute("contratos")
	public List<SccContratoCobl> populaContratos() throws Exception {
		List<SccContratoCobl> comboList = getServiceManager().getContratoService().getAll();
		return comboList;
	}
	
	@ModelAttribute("produtos")
	public List<SccProdutoCobilling> populaProdutos() throws Exception {
		List<SccProdutoCobilling> comboList = getServiceManager().getContratoService().listaTodosProdutos();
		return comboList;
	}
	
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
	}
	
}
