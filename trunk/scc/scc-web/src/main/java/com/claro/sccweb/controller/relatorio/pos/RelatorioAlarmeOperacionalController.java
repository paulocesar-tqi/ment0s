package com.claro.sccweb.controller.relatorio.pos;

import java.util.ArrayList;
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

import com.claro.cobillingweb.persistence.view.RelAlarmeOperacionalView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.RelatorioAlarmeOperacionalValidator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioAlarmeOperacionalForm;

@Controller
@RequestMapping(value="/user/relatorio/alarmeoperacional")
public class RelatorioAlarmeOperacionalController extends BaseOperationController<RelatorioAlarmeOperacionalForm>{

	private final RelatorioAlarmeOperacionalValidator validator = new RelatorioAlarmeOperacionalValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		cleanDisplayTag(request);
		ModelAndView mav = new ModelAndView(getViewName());
		RelatorioAlarmeOperacionalForm myForm = (RelatorioAlarmeOperacionalForm)form;
		
		List<RelAlarmeOperacionalView> rows = null;
		rows = getServiceManager().getRelatorioAlarmeOperacionalService().listarAlarmeOperacional(myForm.getDataInicial(), myForm.getDataFinal() , myForm.getNomeRelatorio());
		
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, rows, request);
		mav.addObject(FORM_NAME, form);
		cacheMyForm(getClass(), form);
		return mav;	
	}
	
	
	public ModelAndView excel(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm form,BindingResult bindingResult,Model model) throws Exception {
		ModelAndView mav = new ModelAndView("relatorio_alarme_operacional_excel");
		return mav;
	}
	
	protected String getViewName() {
		return "relatorio_alarme_operacional";
	}	
	
	protected RelatorioAlarmeOperacionalForm getForm() {
		return new RelatorioAlarmeOperacionalForm();
	}
	
	protected Validator getValidator() {		
		return this.validator;
	}
	
	@ModelAttribute("nomesRelatorios")
	public List<BasicStringItem> populaTiposArquivo() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		//comboList.add(new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos"));
		comboList.add(new BasicStringItem("6", "Alteração de Vencimento"));
		comboList.add(new BasicStringItem("7", "Chamadas Faturadas"));
		comboList.add(new BasicStringItem("8", "Chamadas em Reversão"));
		comboList.add(new BasicStringItem("9", "Chamadas em Reversão de Pagamento"));
		comboList.add(new BasicStringItem("10", "Chamadas em Parcelamento"));

		return comboList;
	}
	
}
