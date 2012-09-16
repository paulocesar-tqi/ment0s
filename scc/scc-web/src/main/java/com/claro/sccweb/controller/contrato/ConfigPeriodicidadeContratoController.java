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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccContratoCobl;
import com.claro.cobillingweb.persistence.entity.SccPeriodProdContr;
import com.claro.cobillingweb.persistence.entity.SccPeriodProdContrPK;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratado;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPK;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.ConfigPeriodicidadeContratoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccPeriodProdContrDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.ConfigPeriodicidadeContratoForm;

@Controller
@RequestMapping(value="/user/contrato/produtos/periodicidade")
public class ConfigPeriodicidadeContratoController extends BaseCRUDAndMethodController<ConfigPeriodicidadeContratoForm>{
	
	public static final String PRODUTO_CONTRATO_CACHE = "_PRODUTO_CONTRATO_CACHE_";	
	
	private final ConfigPeriodicidadeContratoValidator validator = new ConfigPeriodicidadeContratoValidator();
	
	@RequestMapping(value = "/select/{cdContrato}/{cdProduto}", method = RequestMethod.POST)
	public ModelAndView selecionaProduto(@PathVariable("cdContrato") Long cdContrato,@PathVariable("cdProduto") Long cdProduto,HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = null;	
		SccProdutoContratadoPK pk = new SccProdutoContratadoPK();
		pk.setCdContratoCobilling(cdContrato);
		pk.setCdProdutoCobilling(cdProduto);
		storeInSession(getClass(), PRODUTO_CONTRATO_CACHE, pk, request);
		List<SccPeriodProdContr> rows = getServiceManager().getContratoService().carregaPeriodicidadesContrato(pk);
		List<SccPeriodProdContrDecorator> decoratorList = new ArrayList<SccPeriodProdContrDecorator>();
		if (rows != null)
			{
			for (int i=0;i<rows.size();i++)
				{
				SccPeriodProdContrDecorator decorator = new SccPeriodProdContrDecorator(rows.get(i), i);
				decoratorList.add(decorator);
				}
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
			}
		ConfigPeriodicidadeContratoForm cachedForm = getForm();
		cachedForm.setPkEntidadeSelecionada(pk);		
		cacheMyForm(getClass(), cachedForm);
		mav = limpar(request, response, getForm(),null, null);
		return mav;
	}
	
	 
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, ConfigPeriodicidadeContratoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		form = getForm();
		form.setEntity(new SccPeriodProdContr());
		SccProdutoContratadoPK pk = (SccProdutoContratadoPK)request.getSession().getAttribute(PRODUTO_CONTRATO_CACHE);
		SccContratoCobl cobl = getServiceManager().getContratoService().getByPk(pk.getCdContratoCobilling());
		SccProdutoCobilling produtoCobilling = getServiceManager().getProdutoService().getByPk(pk.getCdProdutoCobilling());
		form.getEntity().setSccProdutoContratado(new SccProdutoContratado());
		form.getEntity().getSccProdutoContratado().setSccContratoCobl(cobl);
		form.getEntity().getSccProdutoContratado().setSccProdutoCobilling(produtoCobilling);
		SccPeriodProdContrPK id = new SccPeriodProdContrPK();
		form.getEntity().setId(id);
		form.getEntity().getId().setCdContratoCobilling(pk.getCdContratoCobilling());
		form.getEntity().getId().setCdProdutoCobilling(pk.getCdProdutoCobilling());
		mav.addObject("filtro", form);
		return mav;
	}

	 
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, ConfigPeriodicidadeContratoForm form,BindingResult bindingResult, Model model) throws Exception {
		return null;
	}

	 
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, ConfigPeriodicidadeContratoForm form,BindingResult bindingResult, Model model) throws Exception {
		SccPeriodProdContrDecorator decorator = (SccPeriodProdContrDecorator)form.getEntidadeSelecionada();		
		SccPeriodProdContr entity = form.getEntity();
		SccProdutoContratadoPK pk = (SccProdutoContratadoPK)request.getSession().getAttribute(PRODUTO_CONTRATO_CACHE);
		SccProdutoContratado produtoContratado = getServiceManager().getContratoService().getProdutoContratatoByPk(pk);		
		entity.setSccProdutoContratado(produtoContratado);
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		entity.getId().setCdContratoCobilling(pk.getCdContratoCobilling());
		entity.getId().setCdProdutoCobilling(pk.getCdProdutoCobilling());
		
		getServiceManager().getContratoService().create(entity);		
		ModelAndView mav = limpar(request, response, form, bindingResult, model);
		return mav;
	}

	 
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, ConfigPeriodicidadeContratoForm form,BindingResult bindingResult, Model model) throws Exception {
		SccPeriodProdContrDecorator decorator = (SccPeriodProdContrDecorator)form.getEntidadeSelecionada();		
		getServiceManager().getContratoService().delete(decorator.getRow());		
		ModelAndView mav = limpar(request, response, form, bindingResult, model);
		return mav;
	}

	 
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, ConfigPeriodicidadeContratoForm form,BindingResult bindingResult, Model model) throws Exception {
		SccPeriodProdContrDecorator decorator = (SccPeriodProdContrDecorator)form.getEntidadeSelecionada();		
		getServiceManager().getContratoService().update(form.getEntity());		
		ModelAndView mav = limpar(request, response, form, bindingResult, model);
		return mav;
	}

	 
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, ConfigPeriodicidadeContratoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccPeriodProdContrDecorator decorator = (SccPeriodProdContrDecorator)form.getEntidadeSelecionada();
		form.setEntity(decorator.getRow());
		mav.addObject("filtro", form);
		return mav;
	}

	 
	protected String getViewName() {	
		return "produtos_periodo_contrato_pos_cadastro";
	}

	 
	protected ConfigPeriodicidadeContratoForm getForm() {
		return new ConfigPeriodicidadeContratoForm();
	}

	 
	protected Validator getValidator() {
		return this.validator;
	}

	 
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccPeriodProdContrDecorator decorator = (SccPeriodProdContrDecorator)entidadeSelecionada;
		return decorator.getRow().getId();
	}

	@ModelAttribute("periodos")
	public List<SccPeriodicidadeRepasse> populaPeriodos() throws Exception
	{
		return getServiceManager().getPeriodicidadeService().getAll();
	}
	
	
	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception
	{
		ConfigPeriodicidadeContratoForm cachedForm = (ConfigPeriodicidadeContratoForm)getMyFormFromCache(getClass());		
		List<SccPeriodProdContr> rows = getServiceManager().getContratoService().carregaPeriodicidadesContrato(cachedForm.getProdutoContratadoPK());
		List<SccPeriodProdContrDecorator> decoratorList = new ArrayList<SccPeriodProdContrDecorator>();
		if (rows != null)
			{
			for (int i=0;i<rows.size();i++)
				{
				SccPeriodProdContrDecorator decorator = new SccPeriodProdContrDecorator(rows.get(i), i);
				decoratorList.add(decorator);
				}
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
			}
	}
	
	 
	public ModelAndView novo(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccProdutoContratadoPK pk = (SccProdutoContratadoPK)request.getSession().getAttribute(PRODUTO_CONTRATO_CACHE);
		ConfigPeriodicidadeContratoForm form = (ConfigPeriodicidadeContratoForm)_form;
		form.setEntity(new SccPeriodProdContr());
		SccContratoCobl cobl = getServiceManager().getContratoService().getByPk(pk.getCdContratoCobilling());
		SccProdutoCobilling produtoCobilling = getServiceManager().getProdutoService().getByPk(pk.getCdProdutoCobilling());
		form.getEntity().setSccProdutoContratado(new SccProdutoContratado());
		form.getEntity().getSccProdutoContratado().setId(pk);
		form.getEntity().getSccProdutoContratado().setSccProdutoCobilling(produtoCobilling);
		form.getEntity().getSccProdutoContratado().setSccContratoCobl(cobl);		
		form.setOperacao("novo");
		mav.addObject("filtro", form);
		return mav;
	}
	
}
