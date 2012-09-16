package com.claro.sccweb.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.EstornoDebitoValidator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.EstornoDebitoForm;

@Controller
@RequestMapping(value="/user/admin/estorno")
public class EstornoDebitoController extends BaseOperationController<EstornoDebitoForm> {
	
	public static final String OPERACAO_SALVAR = "salvar";
	
	private final EstornoDebitoValidator validator = new EstornoDebitoValidator();
	
	public ModelAndView salvar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView(getViewName());
		
		EstornoDebitoForm form = (EstornoDebitoForm)_form;
		
		SccOperadora operadora = getServiceManager().getOperadoraService().pesquisaOperadoraByPk(form.getCdEOTClaro());
		
		Date dataInicial = calculaDataInicialPeriodo(form.getMes(), form.getAno());
		
		Date dataFinal = calculaDataFinalPeriodo(form.getMes(), form.getAno());
		
		boolean ok = getServiceManager().getEstornoMobileService().verificaRequisicaoEstorno(form.getCdEOTLD(), form.getCdEOTClaro(), operadora.getSgUf(), dataInicial, dataFinal);
		
		form.setOperacao("salvar");
		
		if (ok) {
			getServiceManager().getEstornoMobileService().insereRequisicaoEstorno(form.getCdEOTLD(), form.getCdEOTClaro(), operadora.getSgUf(), dataInicial, dataFinal);
			form.setMensagem("Solicitação inserida com sucesso.");
		} else {			
			form.setMensagem("Não existe uma solicitação que corresponda aos parâmetros informados.");
		}
		mav.addObject(FORM_NAME, form);
		return mav;
	}
	
	protected String getViewName() {
		return "estorno_debito_filtro";
	}
	
	protected EstornoDebitoForm getForm() {
		return new EstornoDebitoForm();
	}
	
	protected Validator getValidator() {
		return this.validator;
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
	public List<SccOperadora> populaOperadoras() throws Exception {
		return super.populaOperadorasClaro(false);
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
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		return super.populaOperadorasExternas(false);
	}
	*/
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() {
		return _populaComboMeses();
	}
	
}
