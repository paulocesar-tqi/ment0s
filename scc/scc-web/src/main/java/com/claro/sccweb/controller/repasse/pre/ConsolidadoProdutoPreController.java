package com.claro.sccweb.controller.repasse.pre;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.codehaus.jettison.json.JSONObject;
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
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.cobillingweb.persistence.view.ConsolidadoProdutoPreView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.decorator.rownum.view.ConsolidadoProdutoPreViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.ConsolidadoProdutoPreForm;

@Controller
@RequestMapping(value = "/user/repasse/consolidado/produto/pre")
public class ConsolidadoProdutoPreController extends BaseOperationController<ConsolidadoProdutoPreForm> {

	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm _form, BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		ConsolidadoProdutoPreForm form = (ConsolidadoProdutoPreForm) _form;

		List<ConsolidadoProdutoPreView> rows = getServiceManager().getRepassePreService().gerarRelatorioConsolidadoProdutoPre(form.getCdEOTLD(), form.getCdEOTClaro(), form.getCdProdutoPrepago(),
				form.getDataInicial(), form.getDataFinal());

		List<ConsolidadoProdutoPreViewDecorator> decoratorList = new ArrayList<ConsolidadoProdutoPreViewDecorator>(rows.size());
		for (int i = 0; i < rows.size(); i++) {
			ConsolidadoProdutoPreViewDecorator decorator = new ConsolidadoProdutoPreViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		cacheMyForm(getClass(), form);
		mav.addObject(FORM_NAME, form);
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		return mav;
	}

	public ModelAndView excel(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute(FORM_NAME) BaseForm _form, BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView("repasse_consolidado_produto_pre_excel");
		return mav;
	}

	protected String getViewName() {
		return "repasse_consolidado_produto_pre_filtro";
	}

	protected ConsolidadoProdutoPreForm getForm() {
		return new ConsolidadoProdutoPreForm();
	}

	protected Validator getValidator() {
		return null;
	}

	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0, allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());
		return comboList;
	}

	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0, allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}

	@ModelAttribute("produtos")
	public List<SccProdutoPrepago> populaProdutos(@ModelAttribute("form") ConsolidadoProdutoPreForm form) throws Exception {
		List<SccProdutoPrepago> comboList = new ArrayList<SccProdutoPrepago>();
		String cdEOT = form.getCdEOTLD();
		String cdEOTClaro = form.getCdEOTClaro();

		SccProdutoPrepago nullValue = new SccProdutoPrepago();
		nullValue.setNoProdutoPrepago("Todos");
		nullValue.setCdProdutoPrepago(BasicDAO.GET_ALL_STRING);
		comboList.add(0, nullValue);

		if (cdEOT == null)
			cdEOT = BasicDAO.GET_ALL_STRING;
		if (cdEOTClaro == null)
			cdEOTClaro = BasicDAO.GET_ALL_STRING;

		List<SccProdutoPrepago> produtos = getServiceManager().getContratoPrePagoService().pesquisaProdutosContratadosEmpresa(cdEOTClaro, cdEOT, false);
		Set<String> s = new HashSet<String>();

		for (SccProdutoPrepago prod : produtos) {
			if (!s.contains(prod.getCdProdutoPrepago())) {
				SccProdutoPrepago v = new SccProdutoPrepago();
				v.setNoProdutoPrepago(prod.getNoProdutoPrepago());
				v.setCdProdutoPrepago(prod.getCdProdutoPrepago());
				comboList.add(v);
				s.add(prod.getCdProdutoPrepago());
			}
		}
		return comboList;
	}

	@RequestMapping(value = "/json/lista_produtos/{cdEOTLD}/{cdEOTClaro}", method = RequestMethod.GET)
	public void pesquisaProdutosLD(@PathVariable("cdEOTLD") String cdEOT, @PathVariable("cdEOTClaro") String cdEOTClaro, HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("form") ConsolidadoProdutoPreForm form) throws Exception {
		debug("Executando atualização de produtos pré-pago através de AJAX para LD " + cdEOT + " e Claro " + cdEOTClaro);
		List<SccProdutoPrepago> produtos = getServiceManager().getContratoPrePagoService().pesquisaProdutosContratadosEmpresa(cdEOTClaro, cdEOT, false);
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("-1", "Todos");
		for (int i = 0; i < produtos.size(); i++) {
			jsonResponse.put(produtos.get(i).getCdProdutoPrepago().toString(), produtos.get(i).getNoProdutoPrepago());
		}
		response.setContentType("application/json");
		response.getWriter().print(jsonResponse.toString());
	}
}
