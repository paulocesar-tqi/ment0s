/**
 * 
 */
package com.claro.sccweb.controller.contestacao;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccContestacaoPrePago;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.filtro.SccFiltroContestacaoPrePago;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SccContestacaoPrePagoValidator;
import com.claro.sccweb.form.SccContestacaoPrePagoForm;
import com.claro.sccweb.service.SccContestacaoPrePagoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author vagner.souza
 *
 */
@Controller
@RequestMapping(value="/user/contestacao/pre/cadastro")
public class SccContestacaoPrePagoController extends BaseOperationController<SccContestacaoPrePagoForm> {
	
	private static final String FWD_VIEW_LISTA_CONTESTACAO_PRE_PAGO = "relatorio_contestacao_pre_pago";
	private static final String FWD_EXCEL_LISTA_CONTESTACAO_PRE_PAGO = "relatorio_contestacao_pre_pago_excel";
	
	private final SccContestacaoPrePagoValidator validator = new SccContestacaoPrePagoValidator();
	
	@Autowired
	private SccContestacaoPrePagoService sccContestacaoPrePagoService;
	
	
	@RequestMapping(value="listar", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, SccContestacaoPrePagoForm form) throws Exception {
		
		form.setListSccContestacaoPrePago(gerarListaContestacaoPrePago(form.getFiltro()));
		
		ModelAndView mav = null;
		if(form.getOperacao().equals("pesquisar")){
			mav = new ModelAndView(FWD_VIEW_LISTA_CONTESTACAO_PRE_PAGO, "filtro", form);
		}else{
			mav = new ModelAndView(FWD_EXCEL_LISTA_CONTESTACAO_PRE_PAGO, "filtro", form);
		}
		
		return mav;
		
	}
	
	private List<SccContestacaoPrePago> gerarListaContestacaoPrePago(SccFiltroContestacaoPrePago filtro) throws ServiceException, DAOException{
		
		return (List<SccContestacaoPrePago>) this.sccContestacaoPrePagoService.pesquisarByFiltro(filtro);
	}
	
	
	@RequestMapping(value = "/editarContestacao", method=RequestMethod.GET)
	public @ResponseBody SccContestacaoPrePago editarEntity(@RequestParam("sqContestacaoPrePago") Long sqContestacaoPrePago , HttpServletRequest request,HttpServletResponse response, SccContestacaoPrePagoForm form, BindingResult bindingResult, Model model) throws Exception {
		
		SccContestacaoPrePago entity = (SccContestacaoPrePago) this.sccContestacaoPrePagoService.findBySqContestacaoPrePago(sqContestacaoPrePago);
		
		form.setEntity(entity);
		
		return form.getEntity();

	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/editarContestacao2", method=RequestMethod.POST)
	public ModelAndView editarLinha(HttpServletRequest request, HttpServletResponse response, SccContestacaoPrePagoForm form ) throws Exception{
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_CONTESTACAO_PRE_PAGO);
		
		int itemIndex = form.getItemSelecionado();

		SccContestacaoPrePago entity = ((List<SccContestacaoPrePago>)request.getSession().getAttribute(DISPLAY_TAG_SPACE_1)).get(itemIndex);
		
		form.setEntity(entity);
		mav.addObject("filtro",form);
		return mav;
		
	}
	
	@RequestMapping(value="/salvarEntity", method=RequestMethod.POST)
	public @ResponseBody ModelAndView salvarEntity(HttpServletRequest request, HttpServletResponse response, SccContestacaoPrePagoForm form) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_CONTESTACAO_PRE_PAGO);
		
		this.sccContestacaoPrePagoService.saveOrUpdate(form.getEntity());
		montarFiltro(form);
		
		List<SccContestacaoPrePago> listContestacaoPrePago = (List<SccContestacaoPrePago>) this.sccContestacaoPrePagoService.pesquisarByFiltro(form.getFiltro());
		form.setListSccContestacaoPrePago(listContestacaoPrePago);
		form.setOperacao("CRUD.salvar");
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, form.getListSccContestacaoPrePago(), request);
		mav.addObject("filtro", form);	
		return mav;
		
		
	}
	
	private void montarFiltro(SccContestacaoPrePagoForm form){
		
		form.getFiltro().setCdEOTLD(form.getCdEOTLD());
		form.getFiltro().setMes(form.getMes());
		form.getFiltro().setAno(form.getAno());
	}
	
	
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() {
		return _populaComboMeses();
	}

	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot("*");
		//allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	@ModelAttribute("statusContestacao")
	public List<BasicStringItem> popularListaStatusContestacao(){
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("1", "Registro"));
		
		return comboList;
		
	}
	
	@Override
	protected String getViewName() {
		
		return FWD_VIEW_LISTA_CONTESTACAO_PRE_PAGO;
	}

	@Override
	protected SccContestacaoPrePagoForm getForm() {
		
		return new SccContestacaoPrePagoForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}

	public void setSccContestacaoPrePagoService(SccContestacaoPrePagoService sccContestacaoPrePagoService) {
		this.sccContestacaoPrePagoService = sccContestacaoPrePagoService;
	}



/*
	@Override
	protected ModelAndView limpar(HttpServletRequest request, HttpServletResponse response, SccContestacaoPrePagoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		mav.addObject(FORM_NAME, getForm());
		return mav;
	}


	@Override
	protected ModelAndView pesquisar(HttpServletRequest request,
			HttpServletResponse response, SccContestacaoPrePagoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected ModelAndView salvar(HttpServletRequest request,
			HttpServletResponse response, SccContestacaoPrePagoForm form,
			BindingResult bindingResult, Model model) throws Exception {

		@SuppressWarnings("unused")
		ModelAndView mav = new ModelAndView(getViewName());
		SccContestacaoPrePago entity = form.getEntity();
		this.sccContestacaoPrePagoService.saveOrUpdate(entity);		
		return limpar(request, response, form, bindingResult, model);

	}


	@Override
	protected ModelAndView remover(HttpServletRequest request,	HttpServletResponse response, SccContestacaoPrePagoForm form,
			BindingResult bindingResult, Model model) throws Exception {

		ModelAndView mav = new ModelAndView(getViewName());		
		SccContestacaoPrePago entity = (SccContestacaoPrePago)form.getEntidadeSelecionada();
		this.sccContestacaoPrePagoService.delete(entity);		
		mav.addObject(FORM_NAME, new SccContestacaoPrePagoForm());
		return mav;		

	}


	@Override
	protected ModelAndView atualizar(HttpServletRequest request,
			HttpServletResponse response, SccContestacaoPrePagoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		this.sccContestacaoPrePagoService.update(form.getEntity());
		mav.addObject(FORM_NAME, new SccContestacaoPrePagoForm());
		
		return mav;
	}

	@RequestMapping(value = "/editarContestacao", method=RequestMethod.POST)
	public ModelAndView editar(HttpServletRequest request,	HttpServletResponse response, SccContestacaoPrePagoForm form,
			BindingResult bindingResult, Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(FWD_VIEW_LISTA_CONTESTACAO_PRE_PAGO);
		SccContestacaoPrePago entity = (SccContestacaoPrePago) form.getEntidadeSelecionada();
		mav.addObject(FORM_NAME, form);
		return mav;
	}


	@Override
	protected Serializable getPkEntidade(Object entidadeSelecionada) {
		SccContestacaoPrePago entity = (SccContestacaoPrePago) entidadeSelecionada; 
		return entity.getSqContestacaoPrePago();
	}


	@Override
	protected void atualizarDadosTabela(HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
*/	
}
