package com.claro.sccweb.controller.arquivo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.stereotype.Controller;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccStatusCdr;
import com.claro.cobillingweb.persistence.view.RelEventosArquivoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.validator.EventosArquivoRetornoValidator;
import com.claro.sccweb.decorator.rownum.view.RelEventosArquivoViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.EventosArquivoRetornoForm;

@Controller
@RequestMapping(value="/user/arquivo/retorno/eventos")
public class EventosArquivoRetornoController extends BaseOperationController<EventosArquivoRetornoForm> {
	
	private final EventosArquivoRetornoValidator validator = new EventosArquivoRetornoValidator();
	
	public static final String FWD_VIEW_EXCEL ="arquivos_retorno_eventos_excel";
	
	public ModelAndView pesquisar(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME) BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName());
		EventosArquivoRetornoForm form = (EventosArquivoRetornoForm)_form;
		List<RelEventosArquivoView> rows = getServiceManager().getArquivosService().geraRelatorioEventos(form.getCdEOTLD(), form.getCdEOTClaro(), form.getCdStatusCdr(), form.getDataInicial(), form.getDataFinal());
		List<RelEventosArquivoViewDecorator> decoratorList = new ArrayList<RelEventosArquivoViewDecorator>(rows.size());
		for (int i=0;i<rows.size();i++) {
			RelEventosArquivoViewDecorator decorator = new RelEventosArquivoViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		rows = null;
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		return mav;
	}
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_EXCEL);
		return mav;
	}
	
	protected String getViewName() {
		return "arquivos_retorno_eventos";
	}
	
	protected EventosArquivoRetornoForm getForm() {
		return new EventosArquivoRetornoForm();
	}
	
	protected Validator getValidator() {
		return this.validator;
	}
	
	@ModelAttribute("eventos")
	public List<SccStatusCdr> populaEventos() throws Exception {
		List<SccStatusCdr> comboList = new ArrayList<SccStatusCdr>();
		SccStatusCdr allValues = new SccStatusCdr();
		allValues.setCdStatusCdr(BasicDAO.GET_ALL);
		allValues.setDsStatusCdr("Todos");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().getAllStatus());
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
	
}
