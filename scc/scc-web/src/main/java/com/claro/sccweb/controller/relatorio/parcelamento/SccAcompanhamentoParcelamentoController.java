package com.claro.sccweb.controller.relatorio.parcelamento;

import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.utils.DateUtils;
import com.claro.cobillingweb.persistence.view.SccAcordoParcelamentoView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.SccAcordoParcelamentoValidator;
import com.claro.sccweb.decorator.view.SccAcordoParcelamentoViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.SccAcordoParcelamentoForm;
import com.claro.sccweb.service.SccAcordoParcelamentoService;
import com.claro.sccweb.service.ServiceException;

@Controller
@RequestMapping(value="/user/relatorio/parcelamento/acompanhamento")
public class SccAcompanhamentoParcelamentoController extends BaseOperationController<SccAcordoParcelamentoForm> {
	
	private static final String FWD_VIEW_EXCEL ="relatorio_acompanhamento_parcelamento_excel";
	private static final String FWD_VIEW_EXCEL2 ="relatorio_acompanhamento_parcelamento_analitico_excel";
	public static final String SINTETICO = "S";
	public static final String ANALITICO = "A";
	public static final String ACORDO = "P";
	
	@Autowired
	private SccAcordoParcelamentoService sccAcordoParcelamentoService;
	
	private final SccAcordoParcelamentoValidator validator = new SccAcordoParcelamentoValidator();
	
	public ModelAndView pesquisar(HttpServletRequest request, HttpServletResponse response, BaseForm _form, BindingResult bindingResult, Model model) throws Exception {
		
		SccAcordoParcelamentoForm form = (SccAcordoParcelamentoForm)_form;
		SccFiltro filtro = getFiltro(form);
		cleanDisplayTag(request);
		List<SccAcordoParcelamentoView> rows = (List<SccAcordoParcelamentoView>) gerarRelatorioParcelamentoAcompanhamento(filtro);
		
		List <SccAcordoParcelamentoViewDecorator> decoratorList = new ArrayList<SccAcordoParcelamentoViewDecorator>(rows.size());
		
		for (int i = 0; i < rows.size(); i++) {
			
			SccAcordoParcelamentoViewDecorator decorator = new SccAcordoParcelamentoViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
		}
		
		if(filtro.getIsSintetico()){
			
			form.setListSintetico(decoratorList);
			storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		}else {
			form.setListAnalitico(decoratorList);
			storeInSession(getClass(), DISPLAY_TAG_SPACE_2, decoratorList, request);
		}
		
		ModelAndView mav = new ModelAndView(getViewName(), "filtro", form);
		return mav;
		
	}
	
	
	@RequestMapping(value="/tab1" , method = RequestMethod.GET)
	public ModelAndView tab1(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView(getViewName());
			Object form = getMyFormFromCache(getClass());
			if (form != null)
				mav.addObject(FORM_NAME, form);
			else
				mav.addObject(FORM_NAME, getForm());
	    	return mav;  
	}
	
	
	public ModelAndView excel(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_EXCEL);
		return mav;
	}
	
	public ModelAndView excel2(HttpServletRequest request,HttpServletResponse response, BaseForm _form,BindingResult bindingResult, Model model) throws Exception {
		ModelAndView mav = new ModelAndView(FWD_VIEW_EXCEL2);
		return mav;
	}
	
	private SccFiltro getFiltro(SccAcordoParcelamentoForm form){
		
		SccFiltro filtro = new SccFiltro();
		filtro.setOperadoraClaro(form.getOperadoraClaro());
		filtro.setCdCsp(form.getOperadoraLd());
		filtro.setDataInicialPeriodo(DateUtils.lowDateTime(form.getDataInicialPeriodo()));
		filtro.setDataFinalPeriodo(DateUtils.highDateTime(form.getDataFinalPeriodo()));
		filtro.setStatus(form.getStatus());
		filtro.setIsSintetico(form.getTipoRelatorio().equalsIgnoreCase(SINTETICO));
		filtro.setNumeroAcordo(form.getNumeroAcordo());
		filtro.setNumeroConta(form.getNumeroConta());
		return filtro;
		
	}
	
	private Collection<SccAcordoParcelamentoView> gerarRelatorioParcelamentoAcompanhamento(SccFiltro filtro) throws DAOException, ServiceException {
		
		return this.sccAcordoParcelamentoService.findByFilterRelAcompanhamento(filtro);
	}

	

	@Override
	protected String getViewName() {
		
		return "relatorio_acompanhamento_parcelamento";
	}

	@Override
	protected SccAcordoParcelamentoForm getForm() {
		
		return new SccAcordoParcelamentoForm();
	}

	@Override
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

	
	
	@ModelAttribute("tipoRelatorio")
	public List<BasicStringItem> popularTipoRelatorio() throws Exception {
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("S", "Sintético"));
		comboList.add(new BasicStringItem("A", "Analitico"));
		comboList.add(new BasicStringItem("F", "Por Fatura"));
		return comboList;
	}

	@ModelAttribute("status")
	public List<BasicStringItem> popularStatus() throws Exception {
		List<BasicStringItem>comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem(BasicDAO.GET_ALL_STRING, "Todos"));
		comboList.add(new BasicStringItem("N", 	"Novo"));
		comboList.add(new BasicStringItem("H", "Honrado"));
		comboList.add(new BasicStringItem("C", "Fechado"));
		comboList.add(new BasicStringItem("A", "Cancelado"));
		comboList.add(new BasicStringItem("B", "Quebrado"));
		comboList.add(new BasicStringItem("E", "Expirado"));
		comboList.add(new BasicStringItem("X", "Outros"));
		return comboList;
	}
	

	public void setSccAcordoParcelamentoService(SccAcordoParcelamentoService sccAcordoParcelamentoService) {
		this.sccAcordoParcelamentoService = sccAcordoParcelamentoService;
	}
	
	
	

}
