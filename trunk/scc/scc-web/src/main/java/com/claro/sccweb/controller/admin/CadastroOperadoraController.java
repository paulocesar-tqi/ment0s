package com.claro.sccweb.controller.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.CadastroOperadoraValidator;
import com.claro.sccweb.decorator.rownum.entity.SccOperadoraDecorator;
import com.claro.sccweb.form.CadastroOperadoraForm;

@Controller
@RequestMapping(value="/user/admin/operadora")
public class CadastroOperadoraController extends BaseCRUDAndMethodController<CadastroOperadoraForm> {
	
	private final CadastroOperadoraValidator validator = new CadastroOperadoraValidator();
	
	public ModelAndView iniciar(HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<SccOperadora> operadoras = getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaro();
		List<SccOperadoraDecorator> decoratorList = new ArrayList<SccOperadoraDecorator>(operadoras.size());
		for (int i=0;i<operadoras.size();i++) {
			SccOperadoraDecorator decorator = new SccOperadoraDecorator(operadoras.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, CadastroOperadoraForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = iniciar(request, response);
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}
	
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, CadastroOperadoraForm form,BindingResult bindingResult, Model model) throws Exception {		
		return null;
	}
	
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, CadastroOperadoraForm form,BindingResult bindingResult, Model model) throws Exception {		 
		ModelAndView mav = new ModelAndView(getViewName());
		SccOperadora operadora = form.getEntity();
		SccOperadora testeEOT =  getServiceManager().getOperadoraService().pesquisaOperadoraByPk(operadora.getCdEot());
		if (testeEOT != null) {
			bindingResult.addError(new ObjectError("CD EOT já existe!","cdEot"));
			form.setMensagem("Código EOT em uso!");
			form.setOperacao("erro_validacao");
			mav.addObject(FORM_NAME, form);
		} else {
			operadora.setDtCriacao(Calendar.getInstance().getTime());
			operadora.setDtAtualizacao(Calendar.getInstance().getTime());
			operadora.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());			
			getServiceManager().getOperadoraService().create(operadora);
			return iniciar(request, response);	
		}
		return mav;
	}
	
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, CadastroOperadoraForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccOperadoraDecorator decorator = (SccOperadoraDecorator)form.getEntidadeSelecionada();
		getServiceManager().getOperadoraService().delete(decorator.getRow());
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, CadastroOperadoraForm form,BindingResult bindingResult, Model model) throws Exception {		
		SccOperadora operadora = form.getEntity();
		operadora.setDtAtualizacao(Calendar.getInstance().getTime());
		operadora.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		operadora.setCdEot((String)getPkEntidade(getItem(request, form)));
		getServiceManager().getOperadoraService().update(operadora);		
		return limpar(request, response, getForm(), bindingResult, model);
	}
	
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, CadastroOperadoraForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		int itemIndex = form.getItemSelecionado();
		SccOperadoraDecorator decorator = ((List<SccOperadoraDecorator>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		SccOperadora entity = decorator.getRow();
		form.setEntity(entity);
		mav.addObject("filtro",form);
		return mav;
	}
	
	protected String getViewName() {		
		return "cadastro_operadora_filtro";
	}
	
	protected CadastroOperadoraForm getForm() {		
		return new CadastroOperadoraForm();
	}
	
	protected Validator getValidator() {	
		return this.validator;
	}
	
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccOperadoraDecorator decorator = (SccOperadoraDecorator)entidadeSelecionada;
		return decorator.getRow().getCdEot();
	}
	
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception {
		List<SccOperadora> operadoras = getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaro();
		List<SccOperadoraDecorator> decoratorList = new ArrayList<SccOperadoraDecorator>(operadoras.size());
		for (int i=0;i<operadoras.size();i++) {
			SccOperadoraDecorator decorator = new SccOperadoraDecorator(operadoras.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
	}
	
	@ModelAttribute("tiposServico")
	public List<BasicStringItem> populaTiposServico() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("M", "Operadora Claro"));
		comboList.add(new BasicStringItem("C", "Operadora Externa"));
		return comboList;
	}
	
	@ModelAttribute("holdingClaro")
	public List<SccOperadora> populaHolding() throws Exception {
		return super.populaHoldingClaro(true);
	}
	
	@ModelAttribute("empresasContabeis")
	public List<BasicStringItem> populaEmpresasContabeis() {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("001", "001 - BCP"));
		comboList.add(new BasicStringItem("300", "300 - Americel"));
		return comboList;
	}
	
}
