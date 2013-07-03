/**
 * 
 */
package com.claro.sccweb.controller.produto;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccComposicaoProduto;
import com.claro.cobillingweb.persistence.entity.SccItemCobilling;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.service.ServiceException;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.ComposicaoProdutoValidator;
import com.claro.sccweb.form.ComposicaoProdutoForm;
import com.claro.sccweb.service.ProdutoService;
import com.claro.sccweb.service.SessionDataManager;

/**
 * @author rodvagne
 *
 */
@Controller
@RequestMapping(value="/user/produto/item")
public class SccComposicaoProdutoController extends BaseOperationController<ComposicaoProdutoForm> {
	
	private final ComposicaoProdutoValidator validator = new ComposicaoProdutoValidator();
	
	private static final String FWD_VIEW_COMPOSICAO = "produto_pos_item_cadastro";
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private SessionDataManager sessionDataManager;

	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView pesquisarByFiltro(HttpServletRequest request, HttpServletResponse response, ComposicaoProdutoForm form) throws Exception {
		
		ModelAndView mav = null;
		List<SccComposicaoProduto> lstComposicaoProduto = this.produtoService.carregaComposicaoProduto(form.getCdProdutoCobilling());
		
		form.setLstComposicaoProduto(lstComposicaoProduto);
		mav = new ModelAndView(FWD_VIEW_COMPOSICAO, "filtro", form);
		return mav;
		
	}
	
	@RequestMapping(value = "/editarSccComposicao", method=RequestMethod.GET)
	public @ResponseBody SccComposicaoProduto editarSccComposicaoProduto(@RequestParam("cdComponente") Long cdComponente , HttpServletRequest request,HttpServletResponse response, ComposicaoProdutoForm form) throws Exception {
		
		form.setEntity(this.produtoService.findById(cdComponente));
		form.setCdProdutoCobilling(form.getEntity().getSccProdutoCobilling().getCdProdutoCobilling());
		return form.getEntity();
		
	}
	
	@RequestMapping(value="/updateEntity", method=RequestMethod.POST)
	public @ResponseBody ModelAndView updateEntity(HttpServletRequest request, HttpServletResponse response, ComposicaoProdutoForm form) throws Exception {

		ModelAndView mav = new ModelAndView(FWD_VIEW_COMPOSICAO);
		String usuario = this.sessionDataManager.getUserPrincipal();
		form.getEntity().setCdUsuarioManut(usuario);
		form.getEntity().setDtAtualizacao(new Date());
		form.getEntity().setSccProdutoCobilling(form.getProdutoCobilling());
		Long cdProdutoCobilling = form.getCdProdutoCobilling();
		this.produtoService.update(form.getEntity());
		
		form.setCdProdutoCobilling(cdProdutoCobilling);
		mav.addObject("filtro", form);	
		return mav;

	}

	@RequestMapping(value="/saveEntity", method=RequestMethod.POST)
	public @ResponseBody ModelAndView saveEntity(HttpServletRequest request, HttpServletResponse response, ComposicaoProdutoForm form) throws Exception {

		ModelAndView mav = new ModelAndView(FWD_VIEW_COMPOSICAO);
		String usuario = this.sessionDataManager.getUserPrincipal();
		form.getEntity().setCdUsuarioManut(usuario);
		form.getEntity().setDtCriacao(new Date());
		form.getEntity().setDtAtualizacao(new Date());
		Long cdProdutoCobilling = form.getCdProdutoCobilling();
		this.produtoService.create(form.getEntity());
		
		form.setCdProdutoCobilling(cdProdutoCobilling);
		mav.addObject("filtro", form);	
		return mav;

	}
	
	@RequestMapping(value="/removeEntity", method=RequestMethod.DELETE)
	public @ResponseBody ModelAndView removerEntity(@RequestParam("cdComponente")Long cdComponente, HttpServletRequest request,HttpServletResponse response, ComposicaoProdutoForm form) throws DAOException, ServiceException{
		ModelAndView mav = new ModelAndView(FWD_VIEW_COMPOSICAO);
		this.produtoService.deleteEntity(cdComponente);
		mav.addObject("filtro", form);
		return mav;
	}
	


	
	@ModelAttribute("itemsCobilling")
	public List<SccItemCobilling> populaItemsCobilling() throws Exception {
		return this.produtoService.getAllItem();
	}
	
	@ModelAttribute("produtos")
	public List<SccProdutoCobilling> populaProdutosCobilling() throws Exception {
		return this.produtoService.getAll();
	}
	
	@Override
	protected String getViewName() {
		
		return FWD_VIEW_COMPOSICAO;
	}

	@Override
	protected ComposicaoProdutoForm getForm() {
		
		return new ComposicaoProdutoForm();
	}

	@Override
	protected Validator getValidator() {
		return this.validator;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public void setSessionDataManager(SessionDataManager sessionDataManager) {
		this.sessionDataManager = sessionDataManager;
	}
	
	


}
