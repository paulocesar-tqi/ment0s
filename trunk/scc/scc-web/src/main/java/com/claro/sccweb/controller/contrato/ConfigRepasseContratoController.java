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

import com.claro.cobillingweb.persistence.entity.SccComposicaoProduto;
import com.claro.cobillingweb.persistence.entity.SccConfigRepasseCobranca;
import com.claro.cobillingweb.persistence.entity.SccContratoCobl;
import com.claro.cobillingweb.persistence.entity.SccItemCobilling;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratado;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPK;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.ConfigRepasseContratoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccConfigRepasseCobrancaDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.ConfigRepasseContratoForm;

@Controller
@RequestMapping(value="/user/contrato/produtos/repasse")
public class ConfigRepasseContratoController extends BaseCRUDAndMethodController<ConfigRepasseContratoForm>{
	
	private ConfigRepasseContratoValidator validator = new ConfigRepasseContratoValidator();
	
	public static final String PRODUTO_CONTRATO_CACHE = "_PRODUTO_CONTRATO_CACHE_";
	
	@RequestMapping(value = "/select/{cdContrato}/{cdProduto}", method = RequestMethod.POST)
	public ModelAndView selecionaProduto(@PathVariable("cdContrato") Long cdContrato,@PathVariable("cdProduto") Long cdProduto,HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView(getViewName());		
		List<SccComposicaoProduto> composicaoProduto = getServiceManager().getProdutoService().carregaComposicaoProduto(cdProduto);
		List<SccItemCobilling> items = new ArrayList<SccItemCobilling>();
		if (composicaoProduto != null)
			{
			for (int i=0;i<composicaoProduto.size();i++)
				{
				items.add(composicaoProduto.get(i).getSccItemCobilling());
				}
			storeInSession(getClass(), "itemsCobilling", items, request);						
			}
		SccProdutoContratadoPK pk = new SccProdutoContratadoPK();
		pk.setCdContratoCobilling(cdContrato);
		pk.setCdProdutoCobilling(cdProduto);
		storeInSession(getClass(), PRODUTO_CONTRATO_CACHE, pk, request);
		List<SccConfigRepasseCobranca> rows = getServiceManager().getContratoService().carregaConfiguracaoRepasse(pk);
		List<SccConfigRepasseCobrancaDecorator> decoratorList = new ArrayList<SccConfigRepasseCobrancaDecorator>();
		if (rows != null)
			{
			for (int i=0;i<rows.size();i++)
				{
				SccConfigRepasseCobrancaDecorator decorator = new SccConfigRepasseCobrancaDecorator(rows.get(i), i);
				decoratorList.add(decorator);
				}
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
			}
		ConfigRepasseContratoForm form = getForm();
		form.setProdutoContratadoPK(pk);
		cacheMyForm(getClass(), form);
		form.setEntity(new SccConfigRepasseCobranca());
		SccContratoCobl cobl = getServiceManager().getContratoService().getByPk(pk.getCdContratoCobilling());
		SccProdutoCobilling produtoCobilling = getServiceManager().getProdutoService().getByPk(pk.getCdProdutoCobilling());
		form.getEntity().setSccProdutoContratado(new SccProdutoContratado());
		form.getEntity().getSccProdutoContratado().setId(pk);
		form.getEntity().getSccProdutoContratado().setSccProdutoCobilling(produtoCobilling);
		form.getEntity().getSccProdutoContratado().setSccContratoCobl(cobl);
		mav.addObject("filtro", form);		
		return mav;
	}
	
	 
	protected ModelAndView limpar(HttpServletRequest request,HttpServletResponse response, ConfigRepasseContratoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccProdutoContratadoPK pk = (SccProdutoContratadoPK)request.getSession().getAttribute(PRODUTO_CONTRATO_CACHE);
		form.setEntity(new SccConfigRepasseCobranca());
		SccContratoCobl cobl = getServiceManager().getContratoService().getByPk(pk.getCdContratoCobilling());
		SccProdutoCobilling produtoCobilling = getServiceManager().getProdutoService().getByPk(pk.getCdProdutoCobilling());
		form.getEntity().setSccProdutoContratado(new SccProdutoContratado());
		form.getEntity().getSccProdutoContratado().setId(pk);
		form.getEntity().getSccProdutoContratado().setSccProdutoCobilling(produtoCobilling);
		form.getEntity().getSccProdutoContratado().setSccContratoCobl(cobl);
		form.setOperacao("");
		mav.addObject("filtro", form);
		return mav;
	}

	 
	protected ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response, ConfigRepasseContratoForm form,BindingResult bindingResult, Model model) throws Exception {
		return null;
	}
	
	public ModelAndView novo(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception {	
		ModelAndView mav = new ModelAndView(getViewName());
		SccProdutoContratadoPK pk = (SccProdutoContratadoPK)request.getSession().getAttribute(PRODUTO_CONTRATO_CACHE);
		ConfigRepasseContratoForm form = (ConfigRepasseContratoForm)_form;
		form.setEntity(new SccConfigRepasseCobranca());
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

	 
	protected ModelAndView salvar(HttpServletRequest request,HttpServletResponse response, ConfigRepasseContratoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccConfigRepasseCobranca entity = form.getEntity();		
		
		SccProdutoContratadoPK pk = (SccProdutoContratadoPK)request.getSession().getAttribute(PRODUTO_CONTRATO_CACHE);
		SccProdutoContratado produtoContratado = getServiceManager().getContratoService().getProdutoContratatoByPk(pk);		
		entity.setSccProdutoContratado(produtoContratado);
		entity.setDtCriacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		List<SccComposicaoProduto> composicaoProduto = getServiceManager().getProdutoService().carregaComposicaoProduto(pk.getCdProdutoCobilling(), form.getEntity().getSccComposicaoProduto().getSccItemCobilling().getCdItemCobilling());
		entity.setSccComposicaoProduto(composicaoProduto.get(0));
		getServiceManager().getContratoService().create(entity);
		mav = limpar(request, response, form, bindingResult, model);				
		return mav;
	}

	 
	protected ModelAndView remover(HttpServletRequest request,HttpServletResponse response, ConfigRepasseContratoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject("form", getForm());
		SccConfigRepasseCobrancaDecorator decorator = (SccConfigRepasseCobrancaDecorator)form.getEntidadeSelecionada();
		getServiceManager().getContratoService().delete(decorator.getRow());		
		return mav;
	}

	protected void atualizarDadosTabela(HttpServletRequest request) throws Exception
	{
		ConfigRepasseContratoForm cachedForm = (ConfigRepasseContratoForm)getMyFormFromCache(getClass());
		List<SccConfigRepasseCobranca> rows = getServiceManager().getContratoService().carregaConfiguracaoRepasse(cachedForm.getProdutoContratadoPK());
		List<SccConfigRepasseCobrancaDecorator> decoratorList = new ArrayList<SccConfigRepasseCobrancaDecorator>();
		if (rows != null)
			{
			for (int i=0;i<rows.size();i++)
				{
				SccConfigRepasseCobrancaDecorator decorator = new SccConfigRepasseCobrancaDecorator(rows.get(i), i);
				decoratorList.add(decorator);
				}
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
			}
	}
	
	 
	protected ModelAndView atualizar(HttpServletRequest request,HttpServletResponse response, ConfigRepasseContratoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = null;				
		SccConfigRepasseCobranca entity = form.getEntity();
		entity.setDtAtualizacao(Calendar.getInstance().getTime());
		entity.setCdUsuarioManut(getSessionDataManager().getUserPrincipal());
		getServiceManager().getContratoService().update(entity);
		mav = limpar(request, response, form, bindingResult, model);
		atualizarDadosTabela(request);
		return mav;
	}

	 
	protected ModelAndView editar(HttpServletRequest request,HttpServletResponse response, ConfigRepasseContratoForm form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		SccConfigRepasseCobrancaDecorator decorator =  (SccConfigRepasseCobrancaDecorator)form.getEntidadeSelecionada();
		form.setEntity(decorator.getRow());		
		mav.addObject("filtro", form);
		return mav;
	}

	 
	protected String getViewName() {
		return "produtos_repasse_contrato_pos_cadastro";
	}

	 
	protected ConfigRepasseContratoForm getForm() {
		return new ConfigRepasseContratoForm();
	}

	 
	protected Validator getValidator() {
		return validator;
	}

	 
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		return null;
	}

	
	@ModelAttribute("cdrsRepasse")
	public List<BasicIntegerItem> populaCdrsRepasse()	throws Exception
	{
		 List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		 comboList.add(new BasicIntegerItem(21L, "Faturado no Mobile"));
		 comboList.add(new BasicIntegerItem(6L, "Encaminhado para Mobile"));
		 comboList.add(new BasicIntegerItem(70L, "Chamada Arrecadada"));
		return comboList;
	}
	
	
	@ModelAttribute("cdrsCobranca")
	public List<BasicIntegerItem> populaCdrsCobranca()	throws Exception
	{
		List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		 comboList.add(new BasicIntegerItem(21L, "Faturado no Mobile"));
		 comboList.add(new BasicIntegerItem(6L, "Encaminhado para Mobile"));
		 comboList.add(new BasicIntegerItem(70L, "Chamada Arrecadada"));
		return comboList;
	}
	
	@ModelAttribute("formasPagamento")
	public List<BasicIntegerItem> populaFormasPagamento()	throws Exception
	{
		 List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		 comboList.add(new BasicIntegerItem(1L, "Dinheiro"));
		 comboList.add(new BasicIntegerItem(2L, "Cheque"));
		 comboList.add(new BasicIntegerItem(3L, "Boleto"));
		 comboList.add(new BasicIntegerItem(4L, "Tranfêrencia Bancaria"));
		 comboList.add(new BasicIntegerItem(5L, "Conciliação de Conta"));
		return comboList;
	}
	
	@ModelAttribute("formasCobranca")
	public List<BasicIntegerItem> populaFormasCobranca()	throws Exception
	{
		 List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		 comboList.add(new BasicIntegerItem(1L, "Cobrança por Percentual de Valor Bruto"));
		 comboList.add(new BasicIntegerItem(2L, "Cobrança por Percentual de Valor Líquido"));
		 comboList.add(new BasicIntegerItem(3L, "Cobrança por Valor Fixo"));
		 comboList.add(new BasicIntegerItem(4L, "Cobrança por Quantidade de CDRs"));
		 comboList.add(new BasicIntegerItem(5L, "Cobrança por Minuto Tarifado"));		 
		return comboList;
	}
	
	
	@ModelAttribute("formasRepasse")
	public List<BasicIntegerItem> populaFormasRepasse()	throws Exception
	{
		 List<BasicIntegerItem> comboList = new ArrayList<BasicIntegerItem>();
		 comboList.add(new BasicIntegerItem(1L, "Repasse por Percentual de Valor Bruto"));
		 comboList.add(new BasicIntegerItem(2L, "Repasse por Percentual de Valor Líquido"));
		 comboList.add(new BasicIntegerItem(3L, "Repasse por Valor Fixo"));
		 comboList.add(new BasicIntegerItem(4L, "Repasse por Quantidade de CDRs"));
		 comboList.add(new BasicIntegerItem(5L, "Repasse por Minuto Tarifado"));		 
		return comboList;
	}
	
}
