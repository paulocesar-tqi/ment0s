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

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccContratoAcordado;
import com.claro.cobillingweb.persistence.entity.SccContratoCobl;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.OperadorasContratoPosValidator;
import com.claro.sccweb.decorator.rownum.entity.SccContratoAcordadoDecorator;
import com.claro.sccweb.form.OperadorasContratoPosForm;

@Controller
@RequestMapping(value="/user/contrato/operadoras")
public class OperadorasContratoPosController extends BaseCRUDAndMethodController<OperadorasContratoPosForm>{
	
	private final OperadorasContratoPosValidator validator = new OperadorasContratoPosValidator();
	
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, OperadorasContratoPosForm form, BindingResult bindingResult, Model model) throws Exception 
	{
		cacheMyForm(getClass(), form);
		List<SccContratoAcordado> rows = getServiceManager().getContratoService().pesquisaAcordosContrato(form.getCdContratoCobilling());
		List<SccContratoAcordadoDecorator> decoratorList = new ArrayList<SccContratoAcordadoDecorator>();
		for (int i=0;i<rows.size();i++)
		{
			SccContratoAcordadoDecorator decorator = new SccContratoAcordadoDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		cacheMyForm(getClass(), form);
		return new ModelAndView(getViewName());
	}
	
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, OperadorasContratoPosForm form,BindingResult bindingResult, Model model) throws Exception 
	{
		Long cdContratoCobilling = ((OperadorasContratoPosForm)getMyFormFromCache(getClass())).getCdContratoCobilling();
		OperadorasContratoPosForm cachedForm = (OperadorasContratoPosForm)getMyFormFromCache(getClass());
		SccContratoCobl contratoCobl = getServiceManager().getContratoService().getByPk(cachedForm.getCdContratoCobilling());
		form.getEntity().getId().setCdContratoCobilling(cdContratoCobilling);
		form.getEntity().setDtCriacao(Calendar.getInstance().getTime());
		form.getEntity().setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		form.getEntity().setDtFimVigencia(contratoCobl.getDtFimVigencia());
		form.getEntity().getId().setDtInicioVigencia(contratoCobl.getDtInicioVigencia());
		form.getEntity().getId().setCdContratoCobilling(contratoCobl.getCdContratoCobilling());
		getServiceManager().getContratoService().create(form.getEntity());
		form.setEntity(new SccContratoAcordado());
		ModelAndView mav = pesquisar(request, response, (OperadorasContratoPosForm)getMyFormFromCache(getClass()), bindingResult, model);
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, OperadorasContratoPosForm form,BindingResult bindingResult, Model model) throws Exception 
	{
		SccContratoAcordado entity = ((SccContratoAcordadoDecorator)form.getEntidadeSelecionada()).getRow();	
		getServiceManager().getContratoService().delete(entity);
		ModelAndView mav = pesquisar(request, response, (OperadorasContratoPosForm)getMyFormFromCache(getClass()), bindingResult, model);
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, OperadorasContratoPosForm form,BindingResult bindingResult, Model model) throws Exception 
	{
		cacheMyForm(getClass(), form);
		Long cdContratoCobilling = ((OperadorasContratoPosForm)getMyFormFromCache(getClass())).getCdContratoCobilling();
		OperadorasContratoPosForm cachedForm = (OperadorasContratoPosForm)getMyFormFromCache(getClass());
		SccContratoCobl contratoCobl = getServiceManager().getContratoService().getByPk(cachedForm.getCdContratoCobilling());
		
		SccContratoAcordado entity = form.getEntity();
		
		request.setAttribute("confirmacao", "SIM");
		
		//getServiceManager().getContratoService().update(form.getEntity());
		//getServiceManager().getContratoService().atualizarAcordosContrato(form.getEntity());
		
		ModelAndView mav = pesquisar(request, response, (OperadorasContratoPosForm)getMyFormFromCache(getClass()), bindingResult, model);
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, OperadorasContratoPosForm form,BindingResult bindingResult, Model model) throws Exception 
	{
		SccContratoAcordado entity = ((SccContratoAcordadoDecorator)form.getEntidadeSelecionada()).getRow();
		form.setEntity(entity);
		request.setAttribute("cdEotClaroAnt", entity.getOperadoraClaro().getCdEot());
		request.setAttribute("cdEotExternaAnt", entity.getOperadoraExterna().getCdEot());
		
		return new ModelAndView(getViewName());
	}
	
	protected String getViewName() 
	{		
		return "operadoras_contrato_pos_cadastro";
	}
	
	protected OperadorasContratoPosForm getForm() 
	{
		return new OperadorasContratoPosForm();
	}
	
	protected Validator getValidator() 
	{
		return validator;
	}
	
	protected Serializable getPkEntidade(Object entidadeSelecionada) 
	{
		SccContratoAcordado entity = ((SccContratoAcordadoDecorator)entidadeSelecionada).getRow();
		return entity.getId();
	}
	
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, OperadorasContratoPosForm form,BindingResult bindingResult, Model model) throws Exception 
	{
		ModelAndView mav = new ModelAndView(getViewName());
		form.setEntity(new SccContratoAcordado());
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	@ModelAttribute("contratos")
	public List<SccContratoCobl> populaContratos() throws Exception
	{
		List<SccContratoCobl> comboList = getServiceManager().getContratoService().getAll();
		return comboList;
	}
	
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());
		return comboList;
	}
	/*
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception
	{
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaro());
		return comboList;
	}
	*/
	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	/*
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception
	{
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();		
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	*/
	
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception 
	{
		// TODO Auto-generated method stub
		
	}
	
}
