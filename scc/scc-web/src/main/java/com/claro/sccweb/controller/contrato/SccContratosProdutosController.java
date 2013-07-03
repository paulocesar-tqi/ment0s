package com.claro.sccweb.controller.contrato;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.entity.SccMemo;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccTipoEvento;
import com.claro.cobillingweb.persistence.utils.DateUtils;
import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;
import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.SccContratoProdutoValidator;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoCobillingDecorator;
import com.claro.sccweb.decorator.rownum.entity.SccContratoProduto2Decorator;
import com.claro.sccweb.form.SccContratoProdutoForm;

@Controller
@RequestMapping(value="/user/contrato/produtos/pre")
public class SccContratosProdutosController extends BaseCRUDAndMethodController<SccContratoProdutoForm> {
	
	
	
	private final SccContratoProdutoValidator validator = new SccContratoProdutoValidator();
	
	private static final String FWD_CONTRATO_PRODUTO = "relatorio_contrato_produto";
	
	private static final String PESQUISAR = "CRUD.pesquisar";
	
	private static final String EDITAR = "CRUD.editar";
	
	private static final String ATUALIZAR ="CRUD.atualizar";
	
	private static final String SALVAR = "CRUD.salvar"; 

	@Override
	protected ModelAndView limpar(HttpServletRequest request,
			HttpServletResponse response, SccContratoProdutoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ModelAndView pesquisar(HttpServletRequest request,	HttpServletResponse response, SccContratoProdutoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		List<SccContratoCobillingSearchView> list = getServiceManager().getContratoPrePagoService().pesquisarProdutosContratos(form.getCdEotClaro(), form.getCdEotLd());

		List<SccContratoProduto2Decorator> decoratorList = new ArrayList<SccContratoProduto2Decorator>(list.size());
		form.setListContratoProdutos(list);
		for (int i = 0; i < list.size(); i++) {
			SccContratoProduto2Decorator deocarator = new SccContratoProduto2Decorator(list.get(i), i);
			decoratorList.add(deocarator);
			
		}

		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName(), "filtro", form);
		cacheMyForm(getClass(), form);
		
		
		return mav;
	}

	@Override
	protected ModelAndView salvar(HttpServletRequest request,
			HttpServletResponse response, SccContratoProdutoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		
		return null;
	}

	@Override
	protected ModelAndView remover(HttpServletRequest request,
			HttpServletResponse response, SccContratoProdutoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ModelAndView atualizar(HttpServletRequest request,
			HttpServletResponse response, SccContratoProdutoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		SccMemo memo = new SccMemo();
		memo.setCdUsuarioManut(form.getEntity().getCdUsuarioManut());
		memo.setDtCriacao(new Date());
		memo.setDtMemo(form.getEntity().getDtInicioContrato());
		memo.setDsOperacao(form.getOperacao());
		
		getServiceManager().getPagamentoRepasseService().insertMemo(memo);
		
		ModelAndView mav = new ModelAndView(getViewName(), "filtro", form);
		return null;
	}
	
	@RequestMapping(value="/execute" , method=RequestMethod.POST)
	public ModelAndView executa(HttpServletRequest request, HttpServletResponse response,@ModelAttribute(FORM_NAME)  SccContratoProdutoForm form,BindingResult bindingResult,Model model) throws Exception {
		
		ModelAndView mav = null;
		
		String operacao = form.getOperacao();
		if (operacao.equalsIgnoreCase(PESQUISAR)) {
			mav = pesquisar(request, response, form, bindingResult, model);
			 
		} else if(operacao.equalsIgnoreCase(EDITAR)){
			mav = editar(request, response, form, bindingResult, model);
		} else if(operacao.equalsIgnoreCase(ATUALIZAR)){
			mav = atualizar(request, response, form, bindingResult, model);
		}
		return mav;		  
	}


	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView editar(HttpServletRequest request,	HttpServletResponse response, SccContratoProdutoForm form,	BindingResult bindingResult, Model model) throws Exception {
		
		int itemIndex = form.getItemSelecionado();
		
		SccContratoProduto2Decorator entity = ((List<SccContratoProduto2Decorator>) request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		
		form.setListProdutosContratados(getServiceManager().getContratoPrePagoService().pesquisarProdutoContatatado(entity.getCdEotClaro(), entity.getCdEotLd(), DateUtils.lowDateTime(entity.getDtInicioContrato())));
		form.setEntity(entity);
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_2, form.getListProdutosContratados(), request);
		
		cacheMyForm(getClass(), form);

		ModelAndView mav = new ModelAndView(getViewName(), "filtro", form);
		return mav;

	}
	
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());
		return comboList;
	}

	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	@ModelAttribute("eventos")
	public List<SccTipoEvento> popularEventos() throws Exception{
		
		List<SccTipoEvento> rows = getServiceManager().getProdutoPrepagoService().getAllTipoEvento();
		return rows;
		
	}
	
	
	@ModelAttribute("produtos")
	public List<SccProdutoCobilling> populaProdutos() throws Exception {
		List<SccProdutoCobilling> comboList = getServiceManager().getContratoService().listaTodosProdutos();
		return comboList;
	}
	

	@Override
	protected String getViewName() {

		return FWD_CONTRATO_PRODUTO;
	}

	@Override
	protected SccContratoProdutoForm getForm() {
		
		return new SccContratoProdutoForm();
	}

	@Override
	protected Validator getValidator() {

		return this.validator;
	}

	@Override
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccContratoCobillingSearchView entity = (SccContratoCobillingSearchView) entidadeSelecionada;
		return (Serializable) entity;
	}

	@Override
	protected void atualizarDadosTabela(HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
