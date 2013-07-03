package com.claro.sccweb.controller.repasse.pre;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SolicitacaoRepassePrePagoValidator;
import com.claro.sccweb.form.SolicitacaoRepassePrePagoForm;
import com.claro.sccweb.service.RepassePreService;

/**
 * Controller para página de solicitação repasse pré-pago.
 *
 */
@Controller
@RequestMapping(value="/user/repasse/pre/solicitacao")
public class SolicitacaoRepassePrePagoController extends BaseFormController {

	private SolicitacaoRepassePrePagoValidator validator = new SolicitacaoRepassePrePagoValidator();
	
	 
	public void initBinder(WebDataBinder binder) {		
		binder.setValidator(validator);
		super.initBinder(binder);
	}
	
	/**
	 * Handler para quando o usuário inicia a navegação na página de solicitação de repasse pré-pago. 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/new" , method = RequestMethod.GET)
	public ModelAndView novaSolicitacao(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("form", new SolicitacaoRepassePrePagoForm());
		mav.setViewName("repasse_pre_solicitacao");
		request.getSession().setAttribute(DISPLAY_TAG_SPACE_1, getServiceManager().getRepassePreService().pesquisaSolicitacoesRepassePre(RepassePreService.VALOR_TO_LOAD_PRE,-1));
		request.getSession().setAttribute(DISPLAY_TAG_SPACE_2, getServiceManager().getRepassePreService().pesquisaSolicitacoesRepassePre(RepassePreService.VALOR_LOADING_PRE,50));
		request.getSession().setAttribute(DISPLAY_TAG_SPACE_3, getServiceManager().getRepassePreService().pesquisaSolicitacoesRepassePre(RepassePreService.VALOR_LOADED_PRE,50));
		return mav;
	}
	
	
	/**
	 * Handler para inserir uma nova requisição de repasse do pré-pago.
	 * @param request
	 * @param response
	 * @param form
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insere" , method = RequestMethod.POST)
	public ModelAndView insere(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("form")  SolicitacaoRepassePrePagoForm form,BindingResult bindingResult,Model model) throws Exception
		{
		debug("Processando requisição para criação de novo relatório de repasse pré-pago.");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("repasse_pre_solicitacao");
		form.setUserName(getSessionDataManager().getUserPrincipal());
		if (bindingResult.hasErrors()){
			debug("Impossível processar requisição para criação de novo relatório de repasse pré-pago : Erros de validação (binding).");
		}else{
			int criados = getServiceManager().getRepassePreService().insereSolicitacaoRepassePre(form);
			SolicitacaoRepassePrePagoForm responseForm = new SolicitacaoRepassePrePagoForm();
			responseForm.setCriados(criados);
			request.getSession().setAttribute(DISPLAY_TAG_SPACE_1, getServiceManager().getRepassePreService().pesquisaSolicitacoesRepassePre(RepassePreService.VALOR_TO_LOAD_PRE,-1));
			request.getSession().setAttribute(DISPLAY_TAG_SPACE_2, getServiceManager().getRepassePreService().pesquisaSolicitacoesRepassePre(RepassePreService.VALOR_LOADING_PRE,50));
			request.getSession().setAttribute(DISPLAY_TAG_SPACE_3, getServiceManager().getRepassePreService().pesquisaSolicitacoesRepassePre(RepassePreService.VALOR_LOADED_PRE,50));
			mav.addObject("form", responseForm);
		}
		return mav;
		}
	
	@RequestMapping(value="/delete" , method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute("form")  SolicitacaoRepassePrePagoForm form,BindingResult bindingResult,Model model) throws Exception
	{
		getServiceManager().getRepassePreService().deleteSolicitacaoRepassePre(form.getNmParam());
		return novaSolicitacao(request, response);
	}
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() 
	{
		return _populaComboMeses();
	}
	
	@ModelAttribute("criterios")
	public List<BasicStringItem> populaCriterios()
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("00", "Por Chamada"));
		comboList.add(new BasicStringItem("01", "Por Minuto"));
		return comboList;
	}
	
	@ModelAttribute("produtos")
	public List<SccProdutoPrepago> populaProdutosCobilling() throws Exception
	{
		List<SccProdutoPrepago> comboList = new ArrayList<SccProdutoPrepago>();
		SccProdutoPrepago nullValue = new SccProdutoPrepago();
		nullValue.setNoProdutoPrepago("Selecione...");
		nullValue.setCdProdutoPrepago(BasicDAO.NULL_STRING);
		comboList.add(0,nullValue);
		return comboList;
	}
	
	/**
	 * Popula combo com a lista de operadoras LD (externas).
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception
	{
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora nullValue = new SccOperadora();
		nullValue.setCdEot(BasicDAO.NULL_STRING);
		nullValue.setDsOperadora("Selecione...");
		comboList.add(0,nullValue);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
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
	
	
	@RequestMapping(value="/json/lista_produtos/{cdEOTLD}/{cdEOTClaro}" , method=RequestMethod.GET)
	public void pesquisaProdutosLD(@PathVariable("cdEOTLD") String cdEOTLD,@PathVariable("cdEOTClaro") String cdEOTClaro,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("form") SolicitacaoRepassePrePagoForm form) throws Exception
	{
		List<SccProdutoPrepago> produtos = getServiceManager().getContratoPrePagoService().pesquisaProdutosContratadosEmpresa(cdEOTClaro, cdEOTLD, false);		
		JSONObject jsonResponse = new JSONObject();				
		jsonResponse.put(BasicDAO.NULL_STRING,"Selecione....");		
		for (int i=0;i<produtos.size();i++)
			{			
			jsonResponse.put(produtos.get(i).getCdProdutoPrepago().toString(), produtos.get(i).getNoProdutoPrepago());			
			}
		 response.setContentType("application/json;charset=UTF-8");
	     response.getWriter().print(jsonResponse.toString());
	}
	
}
