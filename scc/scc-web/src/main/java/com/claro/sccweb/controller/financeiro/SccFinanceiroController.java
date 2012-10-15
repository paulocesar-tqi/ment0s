package com.claro.sccweb.controller.financeiro;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.SccFinanceiroValidator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccFinanceiroForm;
import com.claro.sccweb.form.SolicitacaoRepassePosPagoForm;
import com.claro.sccweb.service.SccFinanceiroService;

@Controller
@RequestMapping(value="/user/financeiro/relatorio")
public class SccFinanceiroController extends BaseOperationController<SccFinanceiroForm> {
	
	@Autowired
	private SccFinanceiroService sccFinanceiroService;

	private final SccFinanceiroValidator validator = new SccFinanceiroValidator();
	
	@Override
	protected String getViewName() {
		
		return "relatorio_financeiro";
	}

	@Override
	protected SccFinanceiroForm getForm() {
		
		return new SccFinanceiroForm();
	}

	@Override
	protected Validator getValidator() {
		
		return this.validator;
	}
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm _form, BindingResult bindingResult, Model model) throws Exception {
/*		
		SccFinanceiroForm form = (SccFinanceiroForm)_form;
		SccFinanceiroFiltro filtro = getFiltro(form);
		
		List<SccFinanceiroView> rows = gerarRelatorioControleFaturas(filtro);
		
		List<SccFinanceiroViewDecorator> decoratorList = new ArrayList<SccFinanceiroViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccFinanceiroViewDecorator decorator = new SccFinanceiroViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		ModelAndView mav = new ModelAndView(getViewName());
		return mav;
*/
		return null;
	}

	
	/**
	 * Popula combo com a lista de operadoras LD (externas).
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora nullValue = new SccOperadora();
		nullValue.setCdEot(BasicDAO.NULL_STRING);
		nullValue.setDsOperadora("Selecione...");
		comboList.add(0,nullValue);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}

	
	
	
	/**
	 * Carrega uma lista vazia de produtos de cobilling. 
	 * Essa lista só será populada após seleção da Operadora LD.
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute("produtos")
	public List<SccProdutoCobilling> populaProdutosCobilling() throws Exception {
		List<SccProdutoCobilling> comboList = new ArrayList<SccProdutoCobilling>();
		SccProdutoCobilling nullValue = new SccProdutoCobilling();
		nullValue.setNoProdutoCobilling("Selecione...");
		nullValue.setCdProdutoCobilling(BasicDAO.NULL);
		comboList.add(0,nullValue);
		return comboList;
	}
	
	
	@RequestMapping(value="/json/lista_produtos/{cdEOT}" , method=RequestMethod.GET)
	public void pesquisaProdutosLD(@PathVariable("cdEOT") String cdEOT,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("form") SolicitacaoRepassePosPagoForm form) throws Exception {		
		List<SccProdutoCobilling> produtos = getServiceManager().getContratoService().pesquisaProdutosOperadoraLD(cdEOT);		
		JSONObject jsonResponse = new JSONObject();				
		jsonResponse.put("0L","Selecione....");		
		for (int i=0;i<produtos.size();i++) {			
			jsonResponse.put(produtos.get(i).getCdProdutoCobilling().toString(), produtos.get(i).getNoProdutoCobilling());			
		}
		response.setContentType("application/json");
		response.getWriter().print(jsonResponse.toString());
	}


	public void setSccFinanceiroService(SccFinanceiroService sccFinanceiroService) {
		this.sccFinanceiroService = sccFinanceiroService;
	}
	
	

}
