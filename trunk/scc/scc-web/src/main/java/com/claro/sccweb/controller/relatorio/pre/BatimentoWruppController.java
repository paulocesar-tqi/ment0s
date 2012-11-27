package com.claro.sccweb.controller.relatorio.pre;

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
import com.claro.cobillingweb.persistence.view.BatimentoWruppPrePagoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.validator.BatimentoWruppPreValidator;
import com.claro.sccweb.decorator.rownum.entity.SccBatimentoWruppPrePagoViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.BatimentoWruppPreForm;

@Controller
@RequestMapping(value="/user/relatorio/batimento/wrupp/pre")
public class BatimentoWruppController extends BaseOperationController<BatimentoWruppPreForm> {

	private final BatimentoWruppPreValidator validator = new BatimentoWruppPreValidator();
	@Override
	protected String getViewName() {
		return "relatorio_batimento_wrupp_pre_filtro";
	}

	@Override
	protected BatimentoWruppPreForm getForm() {
		return new BatimentoWruppPreForm();
	}

	@Override
	protected Validator getValidator() {
		return this.validator;
	}


	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.NULL_STRING);
		allValues.setDsOperadora("Selecione...");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());
		
		return comboList;
	}
	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.NULL_STRING);
		allValues.setDsOperadora("Selecione...");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() {
		return _populaComboMeses();
	}
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		cleanDisplayTag(request);
		ModelAndView mav = new ModelAndView(getViewName());
		BatimentoWruppPreForm myForm = (BatimentoWruppPreForm)form;
		info("Pesquisando batimento wrupp pré-pago:  "+myForm.toString());
		
		List<BatimentoWruppPrePagoView> rows = null;
		Date dataInicio = calculaDataInicialPeriodo(myForm.getMesRelatorio().longValue(), myForm.getAnoRelatorio().longValue());
		Date dataFim = calculaDataFinalPeriodo(myForm.getMesRelatorio().longValue(), myForm.getAnoRelatorio().longValue());
		rows = getServiceManager().getSccBatimentoWruppService().listarBatimentos(myForm.getCdEOTLD(), myForm.getCdEOTClaro(), dataInicio, dataFim);
		
		debug("Pesquisa de batimento wrup pré-pago retornou "+rows.size()+" linhas com filtro "+myForm.toString());
		List<SccBatimentoWruppPrePagoViewDecorator> decoratorList = new ArrayList<SccBatimentoWruppPrePagoViewDecorator>(rows.size());	
		for (int i=0;i<rows.size();i++) {
			SccBatimentoWruppPrePagoViewDecorator decorator = new SccBatimentoWruppPrePagoViewDecorator(rows.get(i), i);
			decoratorList.add(decorator); 
		}
		debug("Resultado de pequisa batimento wrupp pré-pago armazenada em DISPLAY_TAG_SPACE_1");
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		mav.addObject(FORM_NAME, form);
		cacheMyForm(getClass(), form);
		return mav;	
	}
	
	public ModelAndView excel(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		info("Iniciando geração de Excel para pesquisa de batimento wrupp pré-pago");
		ModelAndView mav = new ModelAndView("relatorio_batimento_wrupp_pre_excel");
		return mav;
	}
}
